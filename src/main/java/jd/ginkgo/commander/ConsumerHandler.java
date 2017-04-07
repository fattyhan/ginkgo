package jd.ginkgo.commander;

import com.hazelcast.core.IMap;
import jd.ginkgo.constant.ConfigHelper;
import jd.ginkgo.consumer.ConsumerLeader;
import jd.ginkgo.consumer.HitEventConsumer;
import jd.ginkgo.consumer.PaidEventConsumer;
import jd.ginkgo.consumer.TriggerEventConsumer;
import jd.ginkgo.data.*;
import jd.ginkgo.data.calculation.HitWindowMean;
import jd.ginkgo.data.calculation.PaidWindowMean;
import jd.ginkgo.data.calculation.TriggerWindowMean;
import jd.ginkgo.data.entity.HitEntity;
import jd.ginkgo.data.entity.PaidEntity;
import jd.ginkgo.data.entity.TriggerEntity;
import jd.ginkgo.data.selector.HitSelector;
import jd.ginkgo.data.selector.PaidSelector;
import jd.ginkgo.data.selector.TriggerSelector;
import jd.ginkgo.db.HazelcastMapHelper;
import jd.ginkgo.sink.CommonIMCacheSinkFunction;
import jd.ginkgo.sink.HazelcastSink;
import jd.ginkgo.sink.PromotionDBSinkFunction;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

import java.util.Properties;

/**
 * 处理模型定义->转流
 * Created by hanxiaofei on 2017/4/7.
 */
public class ConsumerHandler {
    final static StreamExecutionEnvironment env = StreamExecutionEnvironment
            .getExecutionEnvironment();
    public static void main() {
        //STEP1 设置运行环境
        env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime);
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", ConfigHelper.BOOT_STRAP_SERVERS);
        properties.setProperty("zookeeper.connect", ConfigHelper.ZOOKEEPER_CONNECT);

        //STEP2 启动消费数据源
        DataStream<Trigger> triggerDataStream = (DataStream<Trigger>) new ConsumerLeader(new TriggerEventConsumer(),env,properties,ConfigHelper.PRO_TRIGGER_TOPIC).move();
        DataStream<Paid> paidDataStream = (DataStream<Paid>) new ConsumerLeader(new PaidEventConsumer(),env,properties,ConfigHelper.PRO_PAID_TOPIC).move();
        DataStream<Hit> hitDataStream = (DataStream<Hit>) new ConsumerLeader(new HitEventConsumer(),env,properties,ConfigHelper.PRO_HIT_TOPIC).move();

        //STEP3 定义模式
        //---1触发模式，触发模式中需要过滤关键参数不为空就可以
        Pattern<Trigger,?> triggerPattern = Pattern.<Trigger>begin("First Event")
                .subtype(Trigger.class)
                .where(evt->evt.getPromotionId()!=null);
        Pattern<Hit,?> hitPattern = Pattern.<Hit>begin("First Event")
                .subtype(Hit.class)
                .where(evt->(evt.getPromotionId()!=null)
                &&(evt.getDiscAmount()>1000));
        Pattern<Paid,?> paidPattern = Pattern.<Paid>begin("First Event")
                .subtype(Paid.class)
                .where(evt->evt.getOrderId()!=null);

        //STEP3 过滤流数据
        KeyedStream<Trigger,String> keyedTriggerStream = CEP.pattern(
                triggerDataStream.keyBy("PromotionId"),triggerPattern)
                .getInputStream()
                .keyBy(new TriggerSelector());
        KeyedStream<Hit,String> keyedHitStream = CEP.pattern(
                hitDataStream.keyBy("PromotionId"),hitPattern)
                .getInputStream()
                .keyBy(new HitSelector());
        KeyedStream<Paid,String> keyedPaidStream = CEP.pattern(
                paidDataStream.keyBy("PromotionId"),paidPattern)
                .getInputStream()
                .keyBy(new PaidSelector());

        //STEP4 计算统计数据
        //----计算数据按照约定频率刷新存储器里的数据
        DataStream<TriggerEntity> triggerStreamper2Second = keyedTriggerStream
                .window(TumblingEventTimeWindows.of(Time.seconds(Long.parseLong(ConfigHelper.INDEX_STATISTICS))))
                .apply(new TriggerWindowMean());
        DataStream<HitEntity> hitStreamper2Second = keyedHitStream
                .window(TumblingEventTimeWindows.of(Time.seconds(Long.parseLong(ConfigHelper.INDEX_STATISTICS))))
                .apply(new HitWindowMean());
        DataStream<PaidEntity> paidStreamper2Second = keyedPaidStream
                .window(TumblingEventTimeWindows.of(Time.seconds(Long.parseLong(ConfigHelper.INDEX_STATISTICS))))
                .apply(new PaidWindowMean());

        //STEP5 入库
        //--暂时不考虑数据库放到分布式缓存中
        IMap<String,Object> iMap = HazelcastMapHelper.getIMap(ConfigHelper.PRO_TRIGGER_TOPIC);
        IMap<String,Object> iMapHit = HazelcastMapHelper.getIMap(ConfigHelper.PRO_HIT_TOPIC);
        IMap<String,Object> iMapPaid = HazelcastMapHelper.getIMap(ConfigHelper.PRO_PAID_TOPIC);

        triggerStreamper2Second.addSink(new HazelcastSink(iMap,new PromotionDBSinkFunction()));
        hitStreamper2Second.addSink(new HazelcastSink(iMapHit,new PromotionDBSinkFunction()));
        paidStreamper2Second.addSink(new HazelcastSink(iMapPaid,new PromotionDBSinkFunction()));


    }
}

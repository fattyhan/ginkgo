package jd.ginkgo.consumer;

import jd.ginkgo.data.BaseData;
import jd.ginkgo.data.Trigger;
import jd.ginkgo.data.parse.TriggerParse;
import jd.ginkgo.data.selector.TriggerSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;

import java.util.Properties;

/**
 * <p>
 *     触发事件消费
 * </p>
 * Created by hanxiaofei on 2017/4/7.
 */
public class TriggerEventConsumer implements Consumer{

    @Override
    public DataStream<? extends BaseData> move(StreamExecutionEnvironment env, Properties properties,String topic) {
        DataStream<Trigger> triggerDataStream = env
                .addSource(new FlinkKafkaConsumer010<>(topic, new SimpleStringSchema(), properties))
                .assignTimestampsAndWatermarks(new AscendingTimestampExtractor<String>() {
                    @Override
                    public long extractAscendingTimestamp(String s) {
                        return System.currentTimeMillis();
                    }
                })
                .flatMap(new TriggerParse())
                .keyBy(new TriggerSelector());

        return triggerDataStream;
    }
}

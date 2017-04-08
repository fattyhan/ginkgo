package jd.ginkgo.consumer;

import jd.ginkgo.data.BaseData;
import jd.ginkgo.data.Paid;
import jd.ginkgo.data.parse.PaidParse;
import jd.ginkgo.data.selector.PaidSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;

import java.util.Properties;

/**
 * Created by hanxiaofei on 2017/4/7.
 */
public class PaidEventConsumer implements Consumer{
    @Override
    public DataStream<? extends BaseData> move(StreamExecutionEnvironment env, Properties properties, String topic) {
        DataStream<Paid> paidDataStream = env
                .addSource(new FlinkKafkaConsumer010<>("traffic", new SimpleStringSchema(), properties))
                .assignTimestampsAndWatermarks(new AscendingTimestampExtractor<String>() {
                    @Override
                    public long extractAscendingTimestamp(String s) {
                        return System.currentTimeMillis();
                    }
                })
                .flatMap(new PaidParse())
                .keyBy(new PaidSelector());
        return paidDataStream;
    }
}

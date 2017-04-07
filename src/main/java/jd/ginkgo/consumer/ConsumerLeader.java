package jd.ginkgo.consumer;


import jd.ginkgo.data.BaseData;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Properties;

/**
 * Created by hanxiaofei on 2017/4/7.
 */
public class ConsumerLeader extends AbstractConsumer {
    public ConsumerLeader(Consumer consumer, StreamExecutionEnvironment env, Properties properties, String topic){
        super(consumer,env,properties,topic);
    }
    @Override
    public DataStream<? extends BaseData> move() {
        return consumer.move(env,properties,topic);
    }
}

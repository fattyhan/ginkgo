package jd.ginkgo.consumer;

import jd.ginkgo.data.BaseData;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Properties;

/**
 * Created by hanxiaofei on 2017/4/7.
 */
public abstract class AbstractConsumer{
    protected Consumer consumer;
    protected StreamExecutionEnvironment env;
    protected Properties properties;
    protected String topic;
    AbstractConsumer(Consumer consumer,StreamExecutionEnvironment env,Properties properties,String topic){
        this.consumer = consumer;
        this.env = env;
        this.properties = properties;
        this.topic = topic;
    }
    public abstract DataStream<? extends BaseData> move();
}

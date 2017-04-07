package jd.ginkgo.consumer;


import jd.ginkgo.data.BaseData;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Properties;

/**
 * Created by hanxiaofei on 2017/4/7.
 */
public interface Consumer {
    DataStream<? extends BaseData> move(StreamExecutionEnvironment env, Properties properties,String topic);
}

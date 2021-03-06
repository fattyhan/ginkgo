package jd.ginkgo.sink;

import com.hazelcast.core.IMap;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.util.Properties;

/**
 * 定义一个插槽用于存储数据
 * Created by hanxiaofei on 2017/4/7.
 */
public class HazelcastSink<T> extends RichSinkFunction<T> {
    private final CommonIMCacheSinkFunction<T> commonIMCacheSinkFunction;
    @Override
    public void invoke(T value) throws Exception {
        commonIMCacheSinkFunction.process(value);
    }

    public HazelcastSink(CommonIMCacheSinkFunction<T> commonIMCacheSinkFunction) {
        this.commonIMCacheSinkFunction = commonIMCacheSinkFunction;
    }
}

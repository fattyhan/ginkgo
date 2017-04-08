package jd.ginkgo.sink;

import com.hazelcast.core.IMap;
import org.apache.flink.api.common.functions.Function;
import java.io.Serializable;

/**
 * 通用内存级缓存插槽
 * Created by hanxiaofei on 2017/4/7.
 */
public interface CommonIMCacheSinkFunction<T> extends Serializable, Function {
    void process(T element);
}

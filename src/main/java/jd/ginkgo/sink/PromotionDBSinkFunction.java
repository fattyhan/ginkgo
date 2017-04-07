package jd.ginkgo.sink;

import com.hazelcast.core.IMap;

import java.util.Properties;

/**
 * Created by hanxiaofei on 2017/4/7.
 */
public class PromotionDBSinkFunction implements CommonIMCacheSinkFunction<Object> {

    @Override
    public void process(Object element, Properties properties, IMap<String,Object> iMap) {
        String key = properties.getProperty("key");
        //直接覆盖
        iMap.put(key,element);
    }
}

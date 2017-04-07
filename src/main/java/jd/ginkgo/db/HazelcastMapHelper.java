package jd.ginkgo.db;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.util.MapUtil;

/**
 * Created by hanxiaofei on 2017/4/7.
 */
public class HazelcastMapHelper {
    private static final HazelcastInstance hz = Hazelcast.newHazelcastInstance();

    public static boolean createIfNo(String name){
        return MapUtil.isNullOrEmpty(hz.getMap(name));
    }

    public static Object takeObj(String name,String key){
        return hz.getMap(name).get(key);
    }

    public static IMap<String,Object> getIMap(String name){
        return hz.getMap(name);
    }
}

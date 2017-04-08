package jd.ginkgo.db;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.util.MapUtil;
import jd.ginkgo.constant.ConfigHelper;

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
        IMap<String,Object> iMap;
        switch (name) {
            case "trigger":
                iMap = HazelcastMapHelper.getIMap(ConfigHelper.PRO_TRIGGER_TOPIC);
                break;
            case "hit":
                iMap = HazelcastMapHelper.getIMap(ConfigHelper.PRO_HIT_TOPIC);
                break;
            case "paid":
                iMap = HazelcastMapHelper.getIMap(ConfigHelper.PRO_PAID_TOPIC);
                break;
            default:
                iMap = HazelcastMapHelper.getIMap("error");
        }
        return iMap;
    }
}

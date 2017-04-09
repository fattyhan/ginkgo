package jd.ginkgo.db;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.util.MapUtil;
import jd.ginkgo.constant.ConfigHelper;

/**
 * Created by hanxiaofei on 2017/4/7.
 */
public class HazelcastMapHelper {
    private static  HazelcastInstance hz;

    public HazelcastMapHelper(HazelcastInstance hz) {
        this.hz = hz;
    }

    public static boolean createIfNo(String name){
        boolean ie = MapUtil.isNullOrEmpty(hz.getMap(name));
         if(ie){
             hz.getMap(name);
         }
        return ie;
    }

    public static Object takeObj(String name,String key){
        return hz.getMap(name).get(key);
    }

    public static IMap<String,Object> getIMap(String name){
        IMap<String,Object> iMap;
        switch (name) {
            case "trigger":
                iMap = hz.getMap(ConfigHelper.PRO_TRIGGER_TOPIC);
                break;
            case "hit":
                iMap = hz.getMap(ConfigHelper.PRO_HIT_TOPIC);
                break;
            case "paid":
                iMap = hz.getMap(ConfigHelper.PRO_PAID_TOPIC);
                break;
            default:
                iMap = hz.getMap("error");
        }
        return iMap;
    }
}

package jd.ginkgo.db;

import com.alibaba.fastjson.JSON;
import jd.ginkgo.data.Trigger;

/**
 * Created by admin on 2017/4/9.
 */
public class TestFastJson {
    public static void main(String[] args) {
        Trigger trigger = JSON.parseObject("{\"PromotionId\":1,\"orderID\": 1,\"userPin\": \"1\"}",Trigger.class);
        System.out.println(HazelcastMapHelper.getIMap("trigger").containsKey("1"));
    }
}

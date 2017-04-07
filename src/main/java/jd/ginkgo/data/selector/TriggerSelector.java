package jd.ginkgo.data.selector;

import jd.ginkgo.data.Trigger;
import org.apache.flink.api.java.functions.KeySelector;

/**
 * Created by hanxiaofei on 2017/4/7.
 */
public class TriggerSelector  implements KeySelector<Trigger, String> {
    @Override
    public String getKey(Trigger value) throws Exception {
        //同一订单连续触发为一次有效触发
        return value.getOrderID();
    }
}

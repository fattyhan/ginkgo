package jd.ginkgo.data.selector;

import jd.ginkgo.data.Hit;
import org.apache.flink.api.java.functions.KeySelector;

/**
 * Created by hanxiaofei on 2017/4/7.
 */
public class HitSelector implements KeySelector<Hit, String> {
    @Override
    public String getKey(Hit value) throws Exception {
        return value.getOrderId();
    }
}

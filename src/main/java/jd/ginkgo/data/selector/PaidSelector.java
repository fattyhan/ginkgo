package jd.ginkgo.data.selector;

import jd.ginkgo.data.Paid;
import org.apache.flink.api.java.functions.KeySelector;

/**
 * Created by hanxiaofei on 2017/4/7.
 */
public class PaidSelector implements KeySelector<Paid,String> {

    @Override
    public String getKey(Paid value) throws Exception {
        return value.getOrderId();
    }
}

package jd.ginkgo.data.parse;

import jd.ginkgo.data.Paid;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

/**
 * Created by hanxiaofei on 2017/4/7.
 */
public final class PaidParse   implements FlatMapFunction<String, Paid> {
    @Override
    public void flatMap(String value, Collector<Paid> out) throws Exception {

    }
}

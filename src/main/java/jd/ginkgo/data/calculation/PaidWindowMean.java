package jd.ginkgo.data.calculation;

import jd.ginkgo.data.Paid;
import jd.ginkgo.data.entity.PaidEntity;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

/**
 * Created by hanxiaofei on 2017/4/7.
 */
public class PaidWindowMean implements WindowFunction<Paid, PaidEntity, String, TimeWindow> {
    @Override
    public void apply(String s, TimeWindow window, Iterable<Paid> input, Collector<PaidEntity> out) throws Exception {

    }
}

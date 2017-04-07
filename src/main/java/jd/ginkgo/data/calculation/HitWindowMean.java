package jd.ginkgo.data.calculation;

import jd.ginkgo.data.Hit;
import jd.ginkgo.data.entity.HitEntity;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

/**
 * Created by hanxiaofei on 2017/4/7.
 */
public class HitWindowMean implements WindowFunction<Hit, HitEntity, String, TimeWindow> {
    @Override
    public void apply(String s, TimeWindow window, Iterable<Hit> input, Collector<HitEntity> out) throws Exception {

    }
}

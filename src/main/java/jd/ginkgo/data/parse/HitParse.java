package jd.ginkgo.data.parse;

import jd.ginkgo.data.Hit;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

/**
 * Created by hanxiaofei on 2017/4/7.
 */
public final class HitParse   implements FlatMapFunction<String, Hit> {

    @Override
    public void flatMap(String value, Collector<Hit> out) throws Exception {
        out.collect(Hit.parseHit(value));
    }
}

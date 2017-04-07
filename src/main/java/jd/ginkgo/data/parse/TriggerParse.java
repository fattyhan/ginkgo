package jd.ginkgo.data.parse;

import jd.ginkgo.data.Trigger;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

/**
 * Created by hanxiaofei on 2017/4/7.
 */
public final class TriggerParse  implements FlatMapFunction<String, Trigger> {

    @Override
    public void flatMap(String value, Collector<Trigger> out) throws Exception {
        out.collect(Trigger.parseTrigger(value));
    }
}

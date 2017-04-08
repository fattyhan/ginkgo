package jd.ginkgo.sink;

import jd.ginkgo.data.BaseData;
import jd.ginkgo.db.HazelcastMapHelper;


/**
 * Created by hanxiaofei on 2017/4/7.
 */
public class PaidDBSinkFunction implements CommonIMCacheSinkFunction<Object> {

    @Override
    public void process(Object element) {
        //直接覆盖 TODO需要取key
        BaseData baseData = (BaseData) element;
        HazelcastMapHelper.getIMap("trigger").put(baseData.getPromotionId(),element);
    }
}

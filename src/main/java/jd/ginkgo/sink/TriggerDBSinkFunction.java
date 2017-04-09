package jd.ginkgo.sink;

import jd.ginkgo.data.entity.TriggerEntity;
import jd.ginkgo.db.HazelcastMapHelper;
import org.apache.flink.hadoop.shaded.org.apache.http.impl.cookie.DateUtils;

import java.util.Date;


/**
 * Created by hanxiaofei on 2017/4/7.
 */
public class TriggerDBSinkFunction implements CommonIMCacheSinkFunction<Object> {

    @Override
    public void process(Object element) {
        //直接覆盖 TODO需要取key
        TriggerEntity baseData = (TriggerEntity) element;
        String time = DateUtils.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss");
        System.out.println(time+"---> 总数："+baseData.getSum()+"--->临时数据是："+baseData.getCurrentQuantity());
        HazelcastMapHelper.getIMap("trigger").put(baseData.getPromotionID(),element);
    }
}

package jd.ginkgo.data.calculation;

import jd.ginkgo.constant.ConfigHelper;
import jd.ginkgo.data.Trigger;
import jd.ginkgo.data.entity.TriggerEntity;
import jd.ginkgo.db.HazelcastMapHelper;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

/**
 * 触发滚动窗口，用来统计约定时间内活动被触发的次数
 * 总数总是累加，临时数据每两秒更新一次，所以查询的时候直接取值就是当前的值
 * Created by hanxiaofei on 2017/4/7.
 */
public final class TriggerWindowMean implements WindowFunction<Trigger, TriggerEntity, String, TimeWindow> {
    @Override
    public void apply(String s, TimeWindow window, Iterable<Trigger> input, Collector<TriggerEntity> out) throws Exception {
        //STEP1 定义实体
        //-----维护的只是统计项，先从存储器拿，没有的话再新增 主键是活动ID
        //主题与map同名 true 不存在 false存在
        boolean beExit = HazelcastMapHelper.createIfNo(ConfigHelper.PRO_TRIGGER_TOPIC);
        TriggerEntity triggerEntity = new TriggerEntity();
        int sum = 0;
//        input.forEach(trigger->{
        for(Trigger trigger:input) {
            if (!beExit)
                triggerEntity = (TriggerEntity) HazelcastMapHelper.takeObj(ConfigHelper.PRO_TRIGGER_TOPIC, trigger.getPromotionId());
            //设置属性 TODO 注意注意这个方法是实时调用的但是会保存约定的时长，这里临时数据需要处理
            triggerEntity.setPromotionID(trigger.getPromotionId());
            triggerEntity.setOrderID(trigger.getOrderID());
            triggerEntity.setUserPin(trigger.getUserPin());
            sum +=1;
        }
        //-----设置临时数据,每次打开窗口将上次记录的临时数据归零 当前活动总数累加
        triggerEntity.setSum(triggerEntity.getSum() + sum);
        triggerEntity.setCurrentQuantity(sum);
//        });
        //----此方法会调用对应插槽的process方法
        out.collect(triggerEntity);

    }
}

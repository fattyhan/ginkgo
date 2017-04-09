package jd.ginkgo.controller;

import jd.ginkgo.data.entity.TriggerEntity;
import jd.ginkgo.db.HazelcastMapHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

/**
 * Created by admin on 2017/4/6.
 */
@Controller
public class WelcomeController {

    private String message = "Hello World";

    @RequestMapping("/trigger")
    public String welcome(Map<String, Object> model) {
        TriggerEntity triggerEntity = (TriggerEntity)HazelcastMapHelper.getIMap("trigger").get("1");
        model.put("sum", triggerEntity.getSum());
        model.put("concurrent", triggerEntity.getCurrentQuantity());
        model.put("time", new Date());
        return "welcome";
    }

}

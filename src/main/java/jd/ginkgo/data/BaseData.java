package jd.ginkgo.data;

import java.io.Serializable;

/**
 * Created by hanxiaofei on 2017/4/7.
 */
public  class BaseData implements Serializable {
    private String PromotionId;

    public String getPromotionId() {
        return PromotionId;
    }

    public void setPromotionId(String promotionId) {
        PromotionId = promotionId;
    }
}

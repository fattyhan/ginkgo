package jd.ginkgo.data;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

/**
 * 需要为营销使用情况做数据提供源
 * 命中+1 使用率不动 命中率调整
 * 根据命中状态失败则需要保存没有命中的原因
 * Created by hanxiaofei on 2017/4/7.
 */
public class Hit extends BaseData{
    private String userPin;
    private String orderId;
    private Double discAmount;
    private String code;
    private String mark;

    public Hit(String userPin, String orderId, Double discAmount, String code, String mark) {
        this.userPin = userPin;
        this.orderId = orderId;
        this.discAmount = discAmount;
        this.code = code;
        this.mark = mark;
    }

    public Hit() {
    }

    public static Hit parseHit(String json) throws IOException {
        return JSON.parseObject(json,Hit.class);
    }

    public Double getDiscAmount() {
        return discAmount;
    }

    public void setDiscAmount(Double discAmount) {
        this.discAmount = discAmount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getUserPin() {
        return userPin;
    }

    public void setUserPin(String userPin) {
        this.userPin = userPin;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}

package com.csy.domain;


//十一运夺金
public class SyytjPropery extends BaseDomain{
    private String sKey;

    private String iSum;

    private String sSumBigSmall;

    private String sSumSingleDouble;

    private String sDragonTiger;

    private String sTopThree;

    private String sMiddleThree;

    private String sPostThree;

    public String getsKey() {
        return sKey;
    }

    public void setsKey(String sKey) {
        this.sKey = sKey == null ? null : sKey.trim();
    }

    public String getiSum() {
        return iSum;
    }

    public void setiSum(String iSum) {
        this.iSum = iSum == null ? null : iSum.trim();
    }

    public String getsSumBigSmall() {
        return sSumBigSmall;
    }

    public void setsSumBigSmall(String sSumBigSmall) {
        this.sSumBigSmall = sSumBigSmall == null ? null : sSumBigSmall.trim();
    }

    public String getsSumSingleDouble() {
        return sSumSingleDouble;
    }

    public void setsSumSingleDouble(String sSumSingleDouble) {
        this.sSumSingleDouble = sSumSingleDouble == null ? null : sSumSingleDouble.trim();
    }

    public String getsDragonTiger() {
        return sDragonTiger;
    }

    public void setsDragonTiger(String sDragonTiger) {
        this.sDragonTiger = sDragonTiger == null ? null : sDragonTiger.trim();
    }

    public String getsTopThree() {
        return sTopThree;
    }

    public void setsTopThree(String sTopThree) {
        this.sTopThree = sTopThree == null ? null : sTopThree.trim();
    }

    public String getsMiddleThree() {
        return sMiddleThree;
    }

    public void setsMiddleThree(String sMiddleThree) {
        this.sMiddleThree = sMiddleThree == null ? null : sMiddleThree.trim();
    }

    public String getsPostThree() {
        return sPostThree;
    }

    public void setsPostThree(String sPostThree) {
        this.sPostThree = sPostThree == null ? null : sPostThree.trim();
    }
}
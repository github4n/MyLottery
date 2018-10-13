package com.csy.domain;


//天津时时彩
public class TjsscPropery extends BaseDomain{
    private String sKey;

    private Integer sSum;

    private String sSingleDouble;

    private String sBigSmall;

    private String sDagonTiger;

    private String sTopThree;

    private String sMiddleThree;

    private String sPostThree;

    public String getsKey() {
        return sKey;
    }

    public void setsKey(String sKey) {
        this.sKey = sKey == null ? null : sKey.trim();
    }

    public Integer getsSum() {
        return sSum;
    }

    public void setsSum(Integer sSum) {
        this.sSum = sSum;
    }

    public String getsSingleDouble() {
        return sSingleDouble;
    }

    public void setsSingleDouble(String sSingleDouble) {
        this.sSingleDouble = sSingleDouble == null ? null : sSingleDouble.trim();
    }

    public String getsBigSmall() {
        return sBigSmall;
    }

    public void setsBigSmall(String sBigSmall) {
        this.sBigSmall = sBigSmall == null ? null : sBigSmall.trim();
    }

    public String getsDagonTiger() {
        return sDagonTiger;
    }

    public void setsDagonTiger(String sDagonTiger) {
        this.sDagonTiger = sDagonTiger == null ? null : sDagonTiger.trim();
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
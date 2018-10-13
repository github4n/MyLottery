package com.csy.domain;

public class Pl3Property {
    private String sKey;

    private Integer iSum;

    private String sSumBigSmall;

    private String sSumSingleDouble;

    public String getsKey() {
        return sKey;
    }

    public void setsKey(String sKey) {
        this.sKey = sKey == null ? null : sKey.trim();
    }

    public Integer getiSum() {
        return iSum;
    }

    public void setiSum(Integer iSum) {
        this.iSum = iSum;
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
}
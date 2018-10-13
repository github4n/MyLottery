package com.csy.domain;

public class PcddProperty {
    private String sKey;

    private Integer sSum;

    private String sSingleDouble;

    private String sBigSmall;

    private String sBoColor;

    private String sExValue;

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

    public String getsBoColor() {
        return sBoColor;
    }

    public void setsBoColor(String sBoColor) {
        this.sBoColor = sBoColor == null ? null : sBoColor.trim();
    }

    public String getsExValue() {
        return sExValue;
    }

    public void setsExValue(String sExValue) {
        this.sExValue = sExValue == null ? null : sExValue.trim();
    }
}
package com.csy.domain;


//广东快乐十分
public class GdklsfProperty extends BaseDomain{
    private String sKey;

    private Integer iSum;

    private String sSumBigSmall;

    private String sSumSingleDouble;

    private String sSumTailBig;

    private String sDragonTigerNum1;

    private String sDragonTigerNum2;

    private String sDragonTigerNum3;

    private String sDragonTigerNum4;

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

    public String getsSumTailBig() {
        return sSumTailBig;
    }

    public void setsSumTailBig(String sSumTailBig) {
        this.sSumTailBig = sSumTailBig == null ? null : sSumTailBig.trim();
    }

    public String getsDragonTigerNum1() {
        return sDragonTigerNum1;
    }

    public void setsDragonTigerNum1(String sDragonTigerNum1) {
        this.sDragonTigerNum1 = sDragonTigerNum1 == null ? null : sDragonTigerNum1.trim();
    }

    public String getsDragonTigerNum2() {
        return sDragonTigerNum2;
    }

    public void setsDragonTigerNum2(String sDragonTigerNum2) {
        this.sDragonTigerNum2 = sDragonTigerNum2 == null ? null : sDragonTigerNum2.trim();
    }

    public String getsDragonTigerNum3() {
        return sDragonTigerNum3;
    }

    public void setsDragonTigerNum3(String sDragonTigerNum3) {
        this.sDragonTigerNum3 = sDragonTigerNum3 == null ? null : sDragonTigerNum3.trim();
    }

    public String getsDragonTigerNum4() {
        return sDragonTigerNum4;
    }

    public void setsDragonTigerNum4(String sDragonTigerNum4) {
        this.sDragonTigerNum4 = sDragonTigerNum4 == null ? null : sDragonTigerNum4.trim();
    }
}
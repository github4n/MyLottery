package com.csy.domain;


//安徽快3
public class Ahk3Propery extends BaseDomain{
    private String sKey;

    private Integer sSum;

    private String sBigSmall;

    private String sSingleDouble;

    private String sFishShrimpCrab1;

    private String sFishShrimpCrab2;

    private String sFishShrimpCrab3;

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
        this.sSum = sSum ;
    }

    public String getsBigSmall() {
        return sBigSmall;
    }

    public void setsBigSmall(String sBigSmall) {
        this.sBigSmall = sBigSmall == null ? null : sBigSmall.trim();
    }

    public String getsSingleDouble() {
        return sSingleDouble;
    }

    public void setsSingleDouble(String sSingleDouble) {
        this.sSingleDouble = sSingleDouble == null ? null : sSingleDouble.trim();
    }

    public String getsFishShrimpCrab1() {
        return sFishShrimpCrab1;
    }

    public void setsFishShrimpCrab1(String sFishShrimpCrab1) {
        this.sFishShrimpCrab1 = sFishShrimpCrab1 == null ? null : sFishShrimpCrab1.trim();
    }

    public String getsFishShrimpCrab2() {
        return sFishShrimpCrab2;
    }

    public void setsFishShrimpCrab2(String sFishShrimpCrab2) {
        this.sFishShrimpCrab2 = sFishShrimpCrab2 == null ? null : sFishShrimpCrab2.trim();
    }

    public String getsFishShrimpCrab3() {
        return sFishShrimpCrab3;
    }

    public void setsFishShrimpCrab3(String sFishShrimpCrab3) {
        this.sFishShrimpCrab3 = sFishShrimpCrab3 == null ? null : sFishShrimpCrab3.trim();
    }
}
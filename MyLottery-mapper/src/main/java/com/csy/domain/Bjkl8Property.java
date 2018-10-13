package com.csy.domain;


import java.util.HashMap;
import java.util.Map;

//北京快乐8
public class Bjkl8Property extends BaseDomain{
    private String sKey;

    private String sFrisbeeNumber;

    private String sSumSingleDouble;

    private String sSumBigSmall;

    private String sTopAndBottom;

    private Integer sSum;

    private String sSumFiveElements;

    private String sOpenNums;

    private Map<String , Integer> resultMap = new HashMap<>();//存放开奖结果

    public String getsKey() {
        return sKey;
    }

    public void setsKey(String sKey) {
        this.sKey = sKey == null ? null : sKey.trim();
    }

    public String getsFrisbeeNumber() {
        return sFrisbeeNumber;
    }

    public void setsFrisbeeNumber(String sFrisbeeNumber) {
        this.sFrisbeeNumber = sFrisbeeNumber == null ? null : sFrisbeeNumber.trim();
    }

    public String getsSumSingleDouble() {
        return sSumSingleDouble;
    }

    public void setsSumSingleDouble(String sSumSingleDouble) {
        this.sSumSingleDouble = sSumSingleDouble == null ? null : sSumSingleDouble.trim();
    }

    public String getsSumBigSmall() {
        return sSumBigSmall;
    }

    public void setsSumBigSmall(String sSumBigSmall) {
        this.sSumBigSmall = sSumBigSmall == null ? null : sSumBigSmall.trim();
    }

    public String getsTopAndBottom() {
        return sTopAndBottom;
    }

    public void setsTopAndBottom(String sTopAndBottom) {
        this.sTopAndBottom = sTopAndBottom == null ? null : sTopAndBottom.trim();
    }

    public Integer getsSum() {
        return sSum;
    }

    public void setsSum(Integer sSum) {
        this.sSum = sSum;
    }

    public String getsSumFiveElements() {
        return sSumFiveElements;
    }

    public void setsSumFiveElements(String sSumFiveElements) {
        this.sSumFiveElements = sSumFiveElements == null ? null : sSumFiveElements.trim();
    }

    public void setsOpenNums(String sOpenNums){
        this.sOpenNums = sOpenNums == null ? null : sOpenNums.trim();
    }

    public  String getsOpenNums (){
        return this.sOpenNums;
    }

    public Map<String , Integer> getResultMap(){
        return this.resultMap;
    }

    public void setResultMap(Map<String ,Integer> resultMap){
        this.resultMap = resultMap;
    }
}
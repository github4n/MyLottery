package com.csy.domain.generate;

import java.io.Serializable;

/**
 * 用于封装返回的彩种数据
 * ps:1.倒计时所需数据封装
 *    2.最近一期开奖结果
 **/
public class LatestAward implements Serializable {
    private Integer Code; // 1
    private Integer id;// 4168250,
    private Integer gameId;
    private String gamePeriod;
    private Integer status;
    private Integer open;
    private Integer manuralopen;
    private Long startTime;
    private Long closeTime;
    private Long openTime;
    private Long realTime;
    private String openNum;
    private Long openNum1;
    private Long openNum2;
    private Long openNum3;
    private Long openNum4;
    private Long openNum5;
    private Long openNum6;
    private Long openNum7;
    private Long openNum8;
    private Long openNum9;
    private Long openNum10;
    private String remark;
    private Long currentTime;
    private String lastContext;
    private Integer totalPeriodCount;
    private Integer overPeriodCount;
    private Long nextPeriodOpenTime;
    private Long nextOpenTime;
    private Long nextPeriod;
    private Long nextCloseTime;
    private Long openNumSum;
    private Long recordCount;
    private String desc;
    private String error;
    private String result;

    public Integer getCode() {
        return Code;
    }
    public void setCode(Integer code) {
        Code = code;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getGameId() {
        return gameId;
    }
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }
    public String getGamePeriod() {
        return gamePeriod;
    }
    public void setGamePeriod(String gamePeriod) {
        this.gamePeriod = gamePeriod;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getOpen() {
        return open;
    }
    public void setOpen(Integer open) {
        this.open = open;
    }
    public Integer getManuralopen() {
        return manuralopen;
    }
    public void setManuralopen(Integer manuralopen) {
        this.manuralopen = manuralopen;
    }
    public Long getStartTime() {
        return startTime;
    }
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }
    public Long getCloseTime() {
        return closeTime;
    }
    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }
    public Long getOpenTime() {
        return openTime;
    }
    public void setOpenTime(Long openTime) {
        this.openTime = openTime;
    }
    public Long getRealTime() {
        return realTime;
    }
    public void setRealTime(Long realTime) {
        this.realTime = realTime;
    }
    public String getOpenNum() {
        return openNum;
    }
    public void setOpenNum(String openNum) {
        this.openNum = openNum;
    }
    public Long getOpenNum1() {
        return openNum1;
    }
    public void setOpenNum1(Long openNum1) {
        this.openNum1 = openNum1;
    }
    public Long getOpenNum2() {
        return openNum2;
    }
    public void setOpenNum2(Long openNum2) {
        this.openNum2 = openNum2;
    }
    public Long getOpenNum3() {
        return openNum3;
    }
    public void setOpenNum3(Long openNum3) {
        this.openNum3 = openNum3;
    }
    public Long getOpenNum4() {
        return openNum4;
    }
    public void setOpenNum4(Long openNum4) {
        this.openNum4 = openNum4;
    }
    public Long getOpenNum5() {
        return openNum5;
    }
    public void setOpenNum5(Long openNum5) {
        this.openNum5 = openNum5;
    }
    public Long getOpenNum6() {
        return openNum6;
    }
    public void setOpenNum6(Long openNum6) {
        this.openNum6 = openNum6;
    }
    public Long getOpenNum7() {
        return openNum7;
    }
    public void setOpenNum7(Long openNum7) {
        this.openNum7 = openNum7;
    }
    public Long getOpenNum8() {
        return openNum8;
    }
    public void setOpenNum8(Long openNum8) {
        this.openNum8 = openNum8;
    }
    public Long getOpenNum9() {
        return openNum9;
    }
    public void setOpenNum9(Long openNum9) {
        this.openNum9 = openNum9;
    }
    public Long getOpenNum10() {
        return openNum10;
    }
    public void setOpenNum10(Long openNum10) {
        this.openNum10 = openNum10;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Long getCurrentTime() {
        return currentTime;
    }
    public void setCurrentTime(Long currentTime) {
        this.currentTime = currentTime;
    }
    public String getLastContext() {
        return lastContext;
    }
    public void setLastContext(String lastContext) {
        this.lastContext = lastContext;
    }
    public Integer getTotalPeriodCount() {
        return totalPeriodCount;
    }
    public void setTotalPeriodCount(Integer totalPeriodCount) {
        this.totalPeriodCount = totalPeriodCount;
    }
    public Integer getOverPeriodCount() {
        return overPeriodCount;
    }
    public void setOverPeriodCount(Integer overPeriodCount) {
        this.overPeriodCount = overPeriodCount;
    }
    public Long getNextPeriodOpenTime() {
        return nextPeriodOpenTime;
    }
    public void setNextPeriodOpenTime(Long nextPeriodOpenTime) {
        this.nextPeriodOpenTime = nextPeriodOpenTime;
    }
    public Long getNextOpenTime() {
        return nextOpenTime;
    }
    public void setNextOpenTime(Long nextOpenTime) {
        this.nextOpenTime = nextOpenTime;
    }
    public Long getNextPeriod() {
        return nextPeriod;
    }
    public void setNextPeriod(Long nextPeriod) {
        this.nextPeriod = nextPeriod;
    }
    public Long getNextCloseTime() {
        return nextCloseTime;
    }
    public void setNextCloseTime(Long nextCloseTime) {
        this.nextCloseTime = nextCloseTime;
    }
    public Long getOpenNumSum() {
        return openNumSum;
    }
    public void setOpenNumSum(Long openNumSum) {
        this.openNumSum = openNumSum;
    }
    public Long getRecordCount() {
        return recordCount;
    }
    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }

}

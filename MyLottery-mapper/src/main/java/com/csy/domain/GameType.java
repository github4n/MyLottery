package com.csy.domain;

import java.util.Date;

public class GameType {
    private Long id;

    private String sgamename;

    private Short ienable;

    private Integer iclosetimeset;

    private Integer iorder;

    private String screator;

    private Date dcreatetime;

    private String screateip;

    private String smodifier;

    private Date dmodifytime;

    private String smodifierip;

    private Boolean iisdeleted;

    private Long iversion;

    private String sremark;

    private Integer periodcount;

    private Integer iovertime;

    private String smodifyip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSgamename() {
        return sgamename;
    }

    public void setSgamename(String sgamename) {
        this.sgamename = sgamename == null ? null : sgamename.trim();
    }

    public Short getIenable() {
        return ienable;
    }

    public void setIenable(Short ienable) {
        this.ienable = ienable;
    }

    public Integer getIclosetimeset() {
        return iclosetimeset;
    }

    public void setIclosetimeset(Integer iclosetimeset) {
        this.iclosetimeset = iclosetimeset;
    }

    public Integer getIorder() {
        return iorder;
    }

    public void setIorder(Integer iorder) {
        this.iorder = iorder;
    }

    public String getScreator() {
        return screator;
    }

    public void setScreator(String screator) {
        this.screator = screator == null ? null : screator.trim();
    }

    public Date getDcreatetime() {
        return dcreatetime;
    }

    public void setDcreatetime(Date dcreatetime) {
        this.dcreatetime = dcreatetime;
    }

    public String getScreateip() {
        return screateip;
    }

    public void setScreateip(String screateip) {
        this.screateip = screateip == null ? null : screateip.trim();
    }

    public String getSmodifier() {
        return smodifier;
    }

    public void setSmodifier(String smodifier) {
        this.smodifier = smodifier == null ? null : smodifier.trim();
    }

    public Date getDmodifytime() {
        return dmodifytime;
    }

    public void setDmodifytime(Date dmodifytime) {
        this.dmodifytime = dmodifytime;
    }

    public String getSmodifierip() {
        return smodifierip;
    }

    public void setSmodifierip(String smodifierip) {
        this.smodifierip = smodifierip == null ? null : smodifierip.trim();
    }

    public Boolean getIisdeleted() {
        return iisdeleted;
    }

    public void setIisdeleted(Boolean iisdeleted) {
        this.iisdeleted = iisdeleted;
    }

    public Long getIversion() {
        return iversion;
    }

    public void setIversion(Long iversion) {
        this.iversion = iversion;
    }

    public String getSremark() {
        return sremark;
    }

    public void setSremark(String sremark) {
        this.sremark = sremark == null ? null : sremark.trim();
    }

    public Integer getPeriodcount() {
        return periodcount;
    }

    public void setPeriodcount(Integer periodcount) {
        this.periodcount = periodcount;
    }

    public Integer getIovertime() {
        return iovertime;
    }

    public void setIovertime(Integer iovertime) {
        this.iovertime = iovertime;
    }

    public String getSmodifyip() {
        return smodifyip;
    }

    public void setSmodifyip(String smodifyip) {
        this.smodifyip = smodifyip == null ? null : smodifyip.trim();
    }
}
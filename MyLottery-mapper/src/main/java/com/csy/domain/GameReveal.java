package com.csy.domain;

import java.util.Date;

//玩彩技巧
public class GameReveal extends BaseDomain{
    private Integer id;

    private String sshowId;

    private Integer igameId;

    private String srevealTitle;

    private String srevealUrl;

    private String srevealImg;

    private String srevealImg2;

    private Date dCreateTime;

    private Date dModifyTime;

    private String sModifier;

    private String sModifyIp;

    private String sCreator;

    private String sCreateIp;

    private Byte iIsDeleted;

    private Long iVersion;

    private Integer iviewCount;

    private String srevealSource;

    private String srevealAuthor;

    private Date drevealEditTime;

    private String srevealContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSshowId() {
        return sshowId;
    }

    public void setSshowId(String sshowId) {
        this.sshowId = sshowId == null ? null : sshowId.trim();
    }

    public Integer getIgameId() {
        return igameId;
    }

    public void setIgameId(Integer igameId) {
        this.igameId = igameId;
    }

    public String getSrevealTitle() {
        return srevealTitle;
    }

    public void setSrevealTitle(String srevealTitle) {
        this.srevealTitle = srevealTitle == null ? null : srevealTitle.trim();
    }

    public String getSrevealUrl() {
        return srevealUrl;
    }

    public void setSrevealUrl(String srevealUrl) {
        this.srevealUrl = srevealUrl == null ? null : srevealUrl.trim();
    }

    public String getSrevealImg() {
        return srevealImg;
    }

    public void setSrevealImg(String srevealImg) {
        this.srevealImg = srevealImg == null ? null : srevealImg.trim();
    }

    public String getSrevealImg2() {
        return srevealImg2;
    }

    public void setSrevealImg2(String srevealImg2) {
        this.srevealImg2 = srevealImg2 == null ? null : srevealImg2.trim();
    }

    public Date getdCreateTime() {
        return dCreateTime;
    }

    public void setdCreateTime(Date dCreateTime) {
        this.dCreateTime = dCreateTime;
    }

    public Date getdModifyTime() {
        return dModifyTime;
    }

    public void setdModifyTime(Date dModifyTime) {
        this.dModifyTime = dModifyTime;
    }

    public String getsModifier() {
        return sModifier;
    }

    public void setsModifier(String sModifier) {
        this.sModifier = sModifier == null ? null : sModifier.trim();
    }

    public String getsModifyIp() {
        return sModifyIp;
    }

    public void setsModifyIp(String sModifyIp) {
        this.sModifyIp = sModifyIp == null ? null : sModifyIp.trim();
    }

    public String getsCreator() {
        return sCreator;
    }

    public void setsCreator(String sCreator) {
        this.sCreator = sCreator == null ? null : sCreator.trim();
    }

    public String getsCreateIp() {
        return sCreateIp;
    }

    public void setsCreateIp(String sCreateIp) {
        this.sCreateIp = sCreateIp == null ? null : sCreateIp.trim();
    }

    public Byte getiIsDeleted() {
        return iIsDeleted;
    }

    public void setiIsDeleted(Byte iIsDeleted) {
        this.iIsDeleted = iIsDeleted;
    }

    public Long getiVersion() {
        return iVersion;
    }

    public void setiVersion(Long iVersion) {
        this.iVersion = iVersion;
    }

    public Integer getIviewCount() {
        return iviewCount;
    }

    public void setIviewCount(Integer iviewCount) {
        this.iviewCount = iviewCount;
    }

    public String getSrevealSource() {
        return srevealSource;
    }

    public void setSrevealSource(String srevealSource) {
        this.srevealSource = srevealSource == null ? null : srevealSource.trim();
    }

    public String getSrevealAuthor() {
        return srevealAuthor;
    }

    public void setSrevealAuthor(String srevealAuthor) {
        this.srevealAuthor = srevealAuthor == null ? null : srevealAuthor.trim();
    }

    public Date getDrevealEditTime() {
        return drevealEditTime;
    }

    public void setDrevealEditTime(Date drevealEditTime) {
        this.drevealEditTime = drevealEditTime;
    }

    public String getSrevealContent() {
        return srevealContent;
    }

    public void setSrevealContent(String srevealContent) {
        this.srevealContent = srevealContent == null ? null : srevealContent.trim();
    }
}
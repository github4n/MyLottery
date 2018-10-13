package com.csy.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
//所有时间为 时间戳 格式
public class GamePeriodVO{
	private static final long serialVersionUID = 1953122227517282721L;

	private Long id;

    private String skey;
	
    private Long igameid;

    private String sgameperiod;

    private Short istatus;

    private Short iopen;

    private Short imanualopen;
    
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Long dstarttime;
    
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Long dclosetime;
    
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Long dopentime;
    
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Long drealopen;

    private String sopennum;

    private Short iopennum1;

    private Short iopennum2;

    private Short iopennum3;

    private Short iopennum4;

    private Short iopennum5;

    private Short iopennum6;

    private Short iopennum7;

    private Short iopennum8;

    private Short iopennum9;

    private Short iopennum10;

    private String sremark;
    
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Long dcreatetime;
    
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Long dmodifytime;

    private String smodifier;

    private String smodifyip;

    private String screator;

    private String screateip;

    private Byte iisdeleted;

    private Long iversion;
    
    private Object property;

    
    
	public Long getDstarttime() {
		return dstarttime;
	}

	public void setDstarttime(Long dstarttime) {
		this.dstarttime = dstarttime;
	}

	public Long getDclosetime() {
		return dclosetime;
	}

	public void setDclosetime(Long dclosetime) {
		this.dclosetime = dclosetime;
	}

	public Long getDopentime() {
		return dopentime;
	}

	public void setDopentime(Long dopentime) {
		this.dopentime = dopentime;
	}

	public Long getDrealopen() {
		return drealopen;
	}

	public void setDrealopen(Long drealopen) {
		this.drealopen = drealopen;
	}

	public Long getDcreatetime() {
		return dcreatetime;
	}

	public void setDcreatetime(Long dcreatetime) {
		this.dcreatetime = dcreatetime;
	}

	public Long getDmodifytime() {
		return dmodifytime;
	}

	public void setDmodifytime(Long dmodifytime) {
		this.dmodifytime = dmodifytime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Object getProperty() {
		return property;
	}

	public void setProperty(Object property) {
		this.property = property;
	}

	public Long getId() {
        return id;
    }
	
	public void setId(Long id) {
        this.id = id;
    }

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey == null ? null : skey.trim();
    }

    public Long getIgameid() {
        return igameid;
    }

    public void setIgameid(Long igameid) {
        this.igameid = igameid;
    }

    public String getSgameperiod() {
        return sgameperiod;
    }

    public void setSgameperiod(String sgameperiod) {
        this.sgameperiod = sgameperiod == null ? null : sgameperiod.trim();
    }

    public Short getIstatus() {
        return istatus;
    }

    public void setIstatus(Short istatus) {
        this.istatus = istatus;
    }

    public Short getIopen() {
        return iopen;
    }

    public void setIopen(Short iopen) {
        this.iopen = iopen;
    }

    public Short getImanualopen() {
        return imanualopen;
    }

    public void setImanualopen(Short imanualopen) {
        this.imanualopen = imanualopen;
    }

    public String getSopennum() {
        return sopennum;
    }

    public void setSopennum(String sopennum) {
        this.sopennum = sopennum == null ? null : sopennum.trim();
    }

    public Short getIopennum1() {
        return iopennum1;
    }

    public void setIopennum1(Short iopennum1) {
        this.iopennum1 = iopennum1;
    }

    public Short getIopennum2() {
        return iopennum2;
    }

    public void setIopennum2(Short iopennum2) {
        this.iopennum2 = iopennum2;
    }

    public Short getIopennum3() {
        return iopennum3;
    }

    public void setIopennum3(Short iopennum3) {
        this.iopennum3 = iopennum3;
    }

    public Short getIopennum4() {
        return iopennum4;
    }

    public void setIopennum4(Short iopennum4) {
        this.iopennum4 = iopennum4;
    }

    public Short getIopennum5() {
        return iopennum5;
    }

    public void setIopennum5(Short iopennum5) {
        this.iopennum5 = iopennum5;
    }

    public Short getIopennum6() {
        return iopennum6;
    }

    public void setIopennum6(Short iopennum6) {
        this.iopennum6 = iopennum6;
    }

    public Short getIopennum7() {
        return iopennum7;
    }

    public void setIopennum7(Short iopennum7) {
        this.iopennum7 = iopennum7;
    }

    public Short getIopennum8() {
        return iopennum8;
    }

    public void setIopennum8(Short iopennum8) {
        this.iopennum8 = iopennum8;
    }

    public Short getIopennum9() {
        return iopennum9;
    }

    public void setIopennum9(Short iopennum9) {
        this.iopennum9 = iopennum9;
    }

    public Short getIopennum10() {
        return iopennum10;
    }

    public void setIopennum10(Short iopennum10) {
        this.iopennum10 = iopennum10;
    }

    public String getSremark() {
        return sremark;
    }

    public void setSremark(String sremark) {
        this.sremark = sremark == null ? null : sremark.trim();
    }

    public String getSmodifier() {
        return smodifier;
    }

    public void setSmodifier(String smodifier) {
        this.smodifier = smodifier == null ? null : smodifier.trim();
    }

    public String getSmodifyip() {
        return smodifyip;
    }

    public void setSmodifyip(String smodifyip) {
        this.smodifyip = smodifyip == null ? null : smodifyip.trim();
    }

    public String getScreator() {
        return screator;
    }

    public void setScreator(String screator) {
        this.screator = screator == null ? null : screator.trim();
    }

    public String getScreateip() {
        return screateip;
    }

    public void setScreateip(String screateip) {
        this.screateip = screateip == null ? null : screateip.trim();
    }

    public Byte getIisdeleted() {
        return iisdeleted;
    }

    public void setIisdeleted(Byte iisdeleted) {
        this.iisdeleted = iisdeleted;
    }

    public Long getIversion() {
        return iversion;
    }

    public void setIversion(Long iversion) {
        this.iversion = iversion;
    }
}
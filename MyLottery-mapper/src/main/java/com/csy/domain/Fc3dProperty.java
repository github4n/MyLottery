package com.csy.domain;

//福彩3d
public class Fc3dProperty extends BaseDomain{
    private String sKey;

    private Integer iSum;

    private String sSumBigSmall;

    private String sSumSingleDouble;
    
    

	@Override
	public String toString() {
		return "Fc3dProperty [sKey=" + sKey + ", iSum=" + iSum + ", sSumBigSmall=" + sSumBigSmall
				+ ", sSumSingleDouble=" + sSumSingleDouble + "]";
	}

	public String getsKey() {
		return sKey;
	}

	public void setsKey(String sKey) {
		this.sKey = sKey;
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
		this.sSumBigSmall = sSumBigSmall;
	}

	public String getsSumSingleDouble() {
		return sSumSingleDouble;
	}

	public void setsSumSingleDouble(String sSumSingleDouble) {
		this.sSumSingleDouble = sSumSingleDouble;
	}

    
}
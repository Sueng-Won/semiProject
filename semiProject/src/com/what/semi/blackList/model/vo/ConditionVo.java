package com.what.semi.blackList.model.vo;

public class ConditionVo {
	String isReport;
	String memberType;
	String keyword;
	
	public ConditionVo() {
	}

	public ConditionVo(String isReport, String memberType, String keyword) {
		this.isReport = isReport;
		this.memberType = memberType;
		this.keyword = keyword;
	}

	public String getIsReport() {
		return isReport;
	}

	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "ConditionVo [isReport=" + isReport + ", memberType=" + memberType + ", keyword=" + keyword + "]";
	}
	
	
}

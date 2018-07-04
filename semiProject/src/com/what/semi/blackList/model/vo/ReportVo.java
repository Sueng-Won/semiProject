package com.what.semi.blackList.model.vo;

import java.sql.Date;

public class ReportVo {
	private String reason;		//신고 사유(카테고리제공)
	private int count;			//해당사항에 대한 신고횟수
	private Date b_date;		//신고일
	
	public ReportVo() {
	}
	
	public ReportVo(String reason, int count, Date b_date) {
		super();
		this.reason = reason;
		this.count = count;
		this.b_date = b_date;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getB_date() {
		return b_date;
	}

	public void setB_date(Date b_date) {
		this.b_date = b_date;
	}

	@Override
	public String toString() {
		return "ReportVo [reason=" + reason + ", count=" + count + ", b_date=" + b_date + "]";
	}
	
	
}

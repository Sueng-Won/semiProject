package com.what.semi.blackList.model.vo;

import java.util.ArrayList;

public class BlackListVo {
	
	private ArrayList<ReportVo> report;		//신고 사유와 해당 사유에 대한 신고횟수
	private String m_id;					//아이디
	private String m_type;					//회원타입
	private String m_name;					//회원명
	private int totalCount;					//총 신고횟수
	
	public BlackListVo() {
	}

	public BlackListVo(String m_id, String m_type, String m_name, int totalCount) {
		super();
		this.m_id = m_id;
		this.m_type = m_type;
		this.m_name = m_name;
		this.totalCount = totalCount;
	}

	public ArrayList<ReportVo> getReport() {
		return report;
	}

	public void setReport(ArrayList<ReportVo> report) {
		this.report = report;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_type() {
		return m_type;
	}

	public void setM_type(String m_type) {
		this.m_type = m_type;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		return "BlackListVo [report=" + report + ", m_id=" + m_id + ", m_type=" + m_type + ", m_name=" + m_name
				+ ", totalCount=" + totalCount + "]";
	}

}

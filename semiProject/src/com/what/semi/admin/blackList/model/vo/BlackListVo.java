package com.what.semi.admin.blackList.model.vo;

import java.util.ArrayList;

public class BlackListVo {
	
	private ArrayList<ReportVo> report;		//신고 사유와 해당 사유에 대한 신고횟수
	private String m_id;					//아이디
	private String m_type;					//회원타입
	private String m_name;					//회원명
	private int totalCount;					//총 신고횟수
	private int is_black_list;				//블랙 리스트 등록 여부
	public BlackListVo() {
	}

	public BlackListVo(String m_id, String m_type, String m_name, int totalCount, int is_black_list) {
		this.m_id = m_id;
		this.m_type = m_type;
		this.m_name = m_name;
		this.totalCount = totalCount;
		this.is_black_list = is_black_list;
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

	
	public int getIs_black_list() {
		return is_black_list;
	}

	public void setIs_black_list(int is_black_list) {
		this.is_black_list = is_black_list;
	}

	@Override
	public String toString() {
		return "BlackListVo [report=" + report + ", m_id=" + m_id + ", m_type=" + m_type + ", m_name=" + m_name
				+ ", totalCount=" + totalCount + ", is_black_list=" + is_black_list + "]";
	}


}

package com.what.semi.contract.model.vo;

import java.sql.Date;

public class ContractVo {
	private int c_no; // 계약사항 고유번호
	private int state; // 계약 상태[요청중-0|진행중-1|완료-2]
	private Date contract_date; // 계약일(NOTNULL이므로 요청시에도 저장해야함)
	private String start_work_time; // 근무시작시간
	private String end_work_time; // 근무종료시간
	private String recruitment_id; // 업주게시물 ID
	private int resume_id; // 이력서 ID
	private String bo_id; // 카카오아이디
	private String js_id; // 카카오아이디

	public ContractVo() {
		// TODO Auto-generated constructor stub
	}



	public ContractVo(int c_no, int state, Date contract_date, String start_work_time, String end_work_time,
			String recruitment_id, int resume_id, String bo_id, String js_id) {
		super();
		this.c_no = c_no;
		this.state = state;
		this.contract_date = contract_date;
		this.start_work_time = start_work_time;
		this.end_work_time = end_work_time;
		this.recruitment_id = recruitment_id;
		this.resume_id = resume_id;
		this.bo_id = bo_id;
		this.js_id = js_id;
	}



	public int getC_no() {
		return c_no;
	}

	public void setC_no(int c_no) {
		this.c_no = c_no;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getContract_date() {
		return contract_date;
	}

	public void setContract_date(Date contract_date) {
		this.contract_date = contract_date;
	}

	public String getStart_work_time() {
		return start_work_time;
	}

	public void setStart_work_time(String start_work_time) {
		this.start_work_time = start_work_time;
	}

	public String getEnd_work_time() {
		return end_work_time;
	}

	public void setEnd_work_time(String end_work_time) {
		this.end_work_time = end_work_time;
	}

	public String getRecruitment_id() {
		return recruitment_id;
	}

	public void setRecruitment_id(String recruitment_id) {
		this.recruitment_id = recruitment_id;
	}

	public int getResume_id() {
		return resume_id;
	}

	public void setResume_id(int resume_id) {
		this.resume_id = resume_id;
	}


	public String getBo_id() {
		return bo_id;
	}



	public void setBo_id(String bo_id) {
		this.bo_id = bo_id;
	}



	public String getJs_id() {
		return js_id;
	}



	public void setJs_id(String js_id) {
		this.js_id = js_id;
	}



	@Override
	public String toString() {
		return "ContractVo [c_no=" + c_no + ", state=" + state + ", contract_date=" + contract_date
				+ ", start_work_time=" + start_work_time + ", end_work_time=" + end_work_time + ", recruitment_id="
				+ recruitment_id + ", resume_id=" + resume_id + ", bo_id=" + bo_id + ", js_id=" + js_id + "]";
	}
}

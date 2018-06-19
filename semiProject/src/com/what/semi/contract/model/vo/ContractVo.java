package com.what.semi.contract.model.vo;

import java.sql.Date;

public class ContractVo {
	private int c_no;				//계약사항 고유번호
	private int state;				//계약 상태[요청중-0|진행중-1|완료-2]
	private Date contract_date;		//계약일(NOTNULL이므로 요청시에도 저장해야함)
	private Date start_work_time;	//근무시작시간
	private Date end_work_time;		//근무종료시간
	private String recruitment_id;	//업주게시물 ID
	private String resume_id;		//이력서 ID
	private String kakao_id;		//카카오아이디
	
	public ContractVo() {
	}

	public ContractVo(int c_no, int state, String recruitment_id, String resume_id, String kakao_id) {
		this.c_no = c_no;
		this.state = state;
		this.recruitment_id = recruitment_id;
		this.resume_id = resume_id;
		this.kakao_id = kakao_id;
	}

	public int getNumber() {
		return c_no;
	}

	public void setNumber(int number) {
		this.c_no = number;
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

	public Date getStart_work_time() {
		return start_work_time;
	}

	public void setStart_work_time(Date start_work_time) {
		this.start_work_time = start_work_time;
	}

	public Date getEnd_work_time() {
		return end_work_time;
	}

	public void setEnd_work_time(Date end_work_time) {
		this.end_work_time = end_work_time;
	}

	public String getRecruitment_id() {
		return recruitment_id;
	}

	public void setRecruitment_id(String recruitment_id) {
		this.recruitment_id = recruitment_id;
	}

	public String getKakao_id() {
		return kakao_id;
	}

	public void setKakao_id(String kakao_id) {
		this.kakao_id = kakao_id;
	}

	public String getResume_id() {
		return resume_id;
	}

	public void setResume_id(String resume_id) {
		this.resume_id = resume_id;
	}
	
	
}

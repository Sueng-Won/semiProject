package com.what.semi.contract.model.vo;

public class ContractConditionVo {
	private String start_work_time;	//QR코드로 근무 시작 시간을 입력하라는 명령을 저장할 변수
	private String end_work_time;	//QR코드로 근무 종료 시간을 입력하라는 명령을 저장할 변수
	private int state;				//현재 계약상태를 저장할 변수
	
	public ContractConditionVo() {
	}

	public ContractConditionVo(String start_work_time, String end_work_time, int state) {
		this.start_work_time = start_work_time;
		this.end_work_time = end_work_time;
		this.state = state;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "ContractConditionVo [start_work_time=" + start_work_time + ", end_work_time=" + end_work_time
				+ ", state=" + state + "]";
	}
	
	
}

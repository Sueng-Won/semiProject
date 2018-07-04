package com.what.semi.blackList.model.vo;

public class BlackListVo {
	private int b_no;			//블랙리스트 고유 넘버
	private int count;			//신고 횟수
	private String reason;		//신고 사유(카테고리제공)
	private String kakao_id;	//카카오 아이디
	
	public BlackListVo() {
	}

	public BlackListVo(int b_no, int count, String reason, String kakao_id) {
		super();
		this.b_no = b_no;
		this.count = count;
		this.reason = reason;
		this.kakao_id = kakao_id;
	}

	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getKakao_id() {
		return kakao_id;
	}

	public void setKakao_id(String kakao_id) {
		this.kakao_id = kakao_id;
	}

	@Override
	public String toString() {
		return "BlackListVo [b_no=" + b_no + ", count=" + count + ", reason=" + reason + ", kakao_id=" + kakao_id + "]";
	}
	
	
}

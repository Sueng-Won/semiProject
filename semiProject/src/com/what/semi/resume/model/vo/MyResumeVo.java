package com.what.semi.resume.model.vo;

public class MyResumeVo {
	private String id;
	private int resume_id;
	private String introduce_title;
	private int is_post;
	private String member_type;
	private char pri_resume;
	
	public MyResumeVo(){
		
	}

	
	public MyResumeVo(String id, int resume_id, String introduce_title, int is_post, String member_type,
			char pri_resume) {
		super();
		this.id = id;
		this.resume_id = resume_id;
		this.introduce_title = introduce_title;
		this.is_post = is_post;
		this.member_type = member_type;
		this.pri_resume = pri_resume;
	}


	public char getPri_resume() {
		return pri_resume;
	}


	public void setPri_resume(char pri_resume) {
		this.pri_resume = pri_resume;
	}


	public int getResume_id() {
		return resume_id;
	}

	public void setResume_id(int resume_id) {
		this.resume_id = resume_id;
	}

	public String getMember_type() {
		return member_type;
	}

	public void setMember_type(String member_type) {
		this.member_type = member_type;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIntroduce_title() {
		return introduce_title;
	}
	public void setIntroduce_title(String introduce_title) {
		this.introduce_title = introduce_title;
	}
	public int getIs_post() {
		return is_post;
	}
	public void setIs_post(int is_post) {
		this.is_post = is_post;
	}
	@Override
	public String toString() {
		return "MyInfoTypeVo [id=" + id + ", introduce_title=" + introduce_title + ", is_post=" + is_post + "]";
	}
	
}

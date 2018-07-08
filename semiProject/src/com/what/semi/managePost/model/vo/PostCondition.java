package com.what.semi.managePost.model.vo;


public class PostCondition {
	private String member_type;
	private String userName;
	private String content;
	private int is_post;
	private int delflag;
	
	public PostCondition() {
	}

	public PostCondition(String member_type, String userName, String content, int is_post, int delflag) {
		this.member_type = member_type;
		this.userName = userName;
		this.content = content;
		this.is_post = is_post;
		this.delflag = delflag;
	}

	public String getMember_type() {
		return member_type;
	}

	public void setMember_type(String member_type) {
		this.member_type = member_type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIs_post() {
		return is_post;
	}

	public void setIs_post(int is_post) {
		this.is_post = is_post;
	}

	
	public int getDelflag() {
		return delflag;
	}

	public void setDelflag(int delflag) {
		this.delflag = delflag;
	}

	@Override
	public String toString() {
		return "PostCondition [member_type=" + member_type + ", userName=" + userName + ", content=" + content
				+ ", is_post=" + is_post + ", delflag=" + delflag + "]";
	}

}

package com.what.semi.qna.model.vo;

import java.util.Date;

public class QnaVo {
	private int q_no;
	private String content;
	private String category;
	private String answer;
	private Date reporting_date;
	private int is_checked;
	private String m_id;
	private String name;
	private String member_type;
	
	public QnaVo() {
	}

	public QnaVo(int q_no, String content, String category, Date reporting_date, int is_checked,
			String m_id) {
		super();
		this.q_no = q_no;
		this.content = content;
		this.category = category;
		this.reporting_date = reporting_date;
		this.is_checked = is_checked;
		this.m_id = m_id;
	}

	public int getQ_no() {
		return q_no;
	}

	public void setQ_no(int q_no) {
		this.q_no = q_no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getReporting_date() {
		return reporting_date;
	}

	public void setReporting_date(Date reporting_date) {
		this.reporting_date = reporting_date;
	}

	public int getIs_checked() {
		return is_checked;
	}

	public void setIs_checked(int is_checked) {
		this.is_checked = is_checked;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMember_type() {
		return member_type;
	}

	public void setMember_type(String member_type) {
		this.member_type = member_type;
	}

	
	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "QnaVo [q_no=" + q_no + ", content=" + content + ", category=" + category + ", reporting_date="
				+ reporting_date + ", is_checked=" + is_checked + ", m_id=" + m_id + ", name=" + name + ", member_type="
				+ member_type + "]";
	}

	
}

package com.what.semi.qna.model.vo;

import java.util.Date;

public class QnaVo {
	private int q_no;
	private String title;
	private String content;
	private String category;
	private Date reporting_date;
	private int is_checked;
	private String m_id;
	
	public QnaVo() {
	}

	public QnaVo(int q_no, String title, String content, String category, Date reporting_date, int is_checked,
			String m_id) {
		super();
		this.q_no = q_no;
		this.title = title;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	@Override
	public String toString() {
		return "QnaVo [q_no=" + q_no + ", title=" + title + ", content=" + content + ", category=" + category
				+ ", reporting_date=" + reporting_date + ", is_checked=" + is_checked + ", m_id=" + m_id + "]";
	}
}

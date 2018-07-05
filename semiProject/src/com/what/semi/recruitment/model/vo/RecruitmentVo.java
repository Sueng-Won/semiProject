package com.what.semi.recruitment.model.vo;

import java.sql.Date;

public class RecruitmentVo {
	private String recruitment_id;			//구인게시물 ID
	private String recruitment_image_src;	//업체이미지
	private String recruitment_name;		//업체명
	private String recruitment_phone;		//업체연락처
	private String recruitment_email;		//업체이메일
	private String address;					//주소
	private String address_detail;			//상세주소
	private String zipcode;					//우편번호
	private double r_latitude;				//위도
	private double r_longitude;				//경도
	private String business_type;			//업종
	private int career;						//경력
	private Date work_day;					//근무일
	private String start_work_time;			//근무시작시간
	private String end_work_time;				//근무종료시간
	private int pay;						//급여
	private char gender;					//희망성별
	private int military_service;			//병역여부
	private String achievement;				//학력
	private String recruitment_title;		//게시물 이름
	private String introduce;				//업체 소개
	private String m_id;				//카카오 ID
	private int is_post;					//게시여부
	private int delflag;					//삭제여부
	private String name;					//업주명
	private String phone;					//업주연락처
	
	
	public RecruitmentVo() {
	}

	public RecruitmentVo(String recruitment_id, String recruitment_image_src, String recruitment_name,
			String recruitment_phone, String recruitment_email, String address, String address_detail, String zipcode,
			double r_latitude, double r_longitude, String business_type, int career, Date work_day,
			String start_work_time, String end_work_time, int pay, char gender, int military_service,
			String achievement, String recruitment_title, String introduce, String m_id, int is_post, String name,
			String phone) {
		super();
		this.recruitment_id = recruitment_id;
		this.recruitment_image_src = recruitment_image_src;
		this.recruitment_name = recruitment_name;
		this.recruitment_phone = recruitment_phone;
		this.recruitment_email = recruitment_email;
		this.address = address;
		this.address_detail = address_detail;
		this.zipcode = zipcode;
		this.r_latitude = r_latitude;
		this.r_longitude = r_longitude;
		this.business_type = business_type;
		this.career = career;
		this.work_day = work_day;
		this.start_work_time = start_work_time;
		this.end_work_time = end_work_time;
		this.pay = pay;
		this.gender = gender;
		this.military_service = military_service;
		this.achievement = achievement;
		this.recruitment_title = recruitment_title;
		this.introduce = introduce;
		this.m_id = m_id;
		this.is_post = is_post;
		this.name = name;
		this.phone = phone;
	}

	public String getRecruitment_id() {
		return recruitment_id;
	}


	public void setRecruitment_id(String recruitment_id) {
		this.recruitment_id = recruitment_id;
	}


	public String getRecruitment_image_src() {
		return recruitment_image_src;
	}


	public void setRecruitment_image_src(String recruitment_image_src) {
		this.recruitment_image_src = recruitment_image_src;
	}


	public String getRecruitment_title() {
		return recruitment_title;
	}


	public void setRecruitment_title(String recruitment_title) {
		this.recruitment_title = recruitment_title;
	}


	public String getRecruitment_name() {
		return recruitment_name;
	}


	public void setRecruitment_name(String recruitment_name) {
		this.recruitment_name = recruitment_name;
	}


	public String getRecruitment_phone() {
		return recruitment_phone;
	}


	public void setRecruitment_phone(String recruitment_phone) {
		this.recruitment_phone = recruitment_phone;
	}


	public String getRecruitment_email() {
		return recruitment_email;
	}


	public void setRecruitment_email(String recruitment_email) {
		this.recruitment_email = recruitment_email;
	}


	public String getBusiness_type() {
		return business_type;
	}


	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getAddress_detail() {
		return address_detail;
	}


	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}


	public String getZipcode() {
		return zipcode;
	}


	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}


	public double getR_latitude() {
		return r_latitude;
	}


	public void setR_latitude(double r_latitude) {
		this.r_latitude = r_latitude;
	}


	public double getR_longitude() {
		return r_longitude;
	}


	public void setR_longitude(double r_longitude) {
		this.r_longitude = r_longitude;
	}


	public Date getWork_day() {
		return work_day;
	}


	public void setWork_day(Date work_day) {
		this.work_day = work_day;
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


	public int getPay() {
		return pay;
	}


	public void setPay(int pay) {
		this.pay = pay;
	}


	public char getGender() {
		return gender;
	}


	public void setGender(char gender) {
		this.gender = gender;
	}


	public int getMilitary_service() {
		return military_service;
	}


	public void setMilitary_service(int military_service) {
		this.military_service = military_service;
	}


	public String getIntroduce() {
		return introduce;
	}


	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}


	public String getM_id() {
		return m_id;
	}


	public void setM_id(String m_id) {
		this.m_id = m_id;
	}


	public int getIs_post() {
		return is_post;
	}


	public void setIs_post(int is_post) {
		this.is_post = is_post;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAchievement() {
		return achievement;
	}



	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}



	public int getCareer() {
		return career;
	}



	public void setCareer(int career) {
		this.career = career;
	}
	
	public int getDelflag() {
		return delflag;
	}

	public void setDelflag(int delflag) {
		this.delflag = delflag;
	}

	@Override
	public String toString() {
		return "RecruitmentVo [recruitment_id=" + recruitment_id + ", recruitment_image_src=" + recruitment_image_src
				+ ", recruitment_name=" + recruitment_name + ", recruitment_phone=" + recruitment_phone
				+ ", recruitment_email=" + recruitment_email + ", address=" + address + ", address_detail="
				+ address_detail + ", zipcode=" + zipcode + ", r_latitude=" + r_latitude + ", r_longitude="
				+ r_longitude + ", business_type=" + business_type + ", career=" + career + ", work_day=" + work_day
				+ ", start_work_time=" + start_work_time + ", end_work_time=" + end_work_time + ", pay=" + pay
				+ ", gender=" + gender + ", military_service=" + military_service + ", achievement=" + achievement
				+ ", recruitment_title=" + recruitment_title + ", introduce=" + introduce + ", m_id=" + m_id
				+ ", is_post=" + is_post + ", delflag=" + delflag + ", name=" + name + ", phone=" + phone + "]";
	}

}
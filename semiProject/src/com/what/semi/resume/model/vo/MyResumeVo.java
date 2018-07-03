package com.what.semi.resume.model.vo;

import java.sql.Date;

public class MyResumeVo {
	private String id;
	private int resume_id;
	private String introduce_title;
	private int is_post;
	private String member_type;
	private char pri_resume;
	private String profile_image_src;
	private String achievement;
	private int disability;
	private int miltary_service;
	private int career;
	private String business_type;
	private java.sql.Date workable_days;
	private String name;
	private String birth;
	private String phone;
	private String email;
	private String address;
	private String introduce;
	private String workTime;
	private String gender;
	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public MyResumeVo() {

	}

	public MyResumeVo(String id, int resume_id, String introduce_title, int is_post, String member_type,
			char pri_resume, String profile_image_src, String achievement, int disability, int miltary_service,
			int career, String business_type, Date workable_days, String name, String birth, String phone, String email,
			String address) {
		super();
		this.id = id;
		this.resume_id = resume_id;
		this.introduce_title = introduce_title;
		this.is_post = is_post;
		this.member_type = member_type;
		this.pri_resume = pri_resume;
		this.profile_image_src = profile_image_src;
		this.achievement = achievement;
		this.disability = disability;
		this.miltary_service = miltary_service;
		this.career = career;
		this.business_type = business_type;
		this.workable_days = workable_days;
		this.name = name;
		this.birth = birth;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}

	public String getProfile_image_src() {
		return profile_image_src;
	}

	public void setProfile_image_src(String profile_image_src) {
		this.profile_image_src = profile_image_src;
	}

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	public int getDisability() {
		return disability;
	}

	public void setDisability(int disability) {
		this.disability = disability;
	}

	public int getMiltary_service() {
		return miltary_service;
	}

	public void setMiltary_service(int miltary_service) {
		this.miltary_service = miltary_service;
	}

	public int getCareer() {
		return career;
	}

	public void setCareer(int career) {
		this.career = career;
	}

	public String getBusiness_type() {
		return business_type;
	}

	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
	}

	public Date getWorkable_days() {
		return workable_days;
	}

	public void setWorkable_days(Date workable_days) {
		this.workable_days = workable_days;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
	
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "MyResumeVo [id=" + id + ", resume_id=" + resume_id + ", introduce_title=" + introduce_title
				+ ", is_post=" + is_post + ", member_type=" + member_type + ", pri_resume=" + pri_resume
				+ ", profile_image_src=" + profile_image_src + ", achievement=" + achievement + ", disability="
				+ disability + ", miltary_service=" + miltary_service + ", career=" + career + ", business_type="
				+ business_type + ", workable_days=" + workable_days + ", name=" + name + ", birth=" + birth
				+ ", phone=" + phone + ", email=" + email + ", address=" + address + ", introduce=" + introduce
				+ ", workTime=" + workTime + ", gender=" + gender + "]";
	}

	

}

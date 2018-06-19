package com.what.semi.member.model.vo;

public class MemberVo {
	private String kakao_id;		//카카오ID
	private String name;			//사용자 이름
	private String birth;			//
	private String phone;
	private String email;
	private String address;
	private String address_detail;
	private int zipcode;
	private String member_type;
	private double latitude;
	private double longitude;
	private char gender;
	private int is_black_list;
	
	public MemberVo(){
		
	}
	
	public MemberVo(String name) {
		this.name = name;
	}
	
	public MemberVo(String kakao_id, String name, String birth, String phone, String email, String address,
			String address_detail, int zipcode, String member_type, double latitude, double longitude, char gender,
			int is_black_list) {
		super();
		this.kakao_id = kakao_id;
		this.name = name;
		this.birth = birth;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.address_detail = address_detail;
		this.zipcode = zipcode;
		this.member_type = member_type;
		this.latitude = latitude;
		this.longitude = longitude;
		this.gender = gender;
		this.is_black_list = is_black_list;
	}
	public String getKakao_id() {
		return kakao_id;
	}
	public void setKakao_id(String kakao_id) {
		this.kakao_id = kakao_id;
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
	public String getAddress_detail() {
		return address_detail;
	}
	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public String getMember_type() {
		return member_type;
	}
	public void setMember_type(String member_type) {
		this.member_type = member_type;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public int getIs_black_list() {
		return is_black_list;
	}
	public void setIs_black_list(int is_black_list) {
		this.is_black_list = is_black_list;
	}
	
	
}

package com.what.semi.common.template;

public class LocalPageInfo {
	private double minLatitude;		//request로 받아온 지도의 최상단 위도를 저장할 변수선언
	private double maxLatitude;		//request로 받아온 지도의 최하단 위도를 저장할 변수선언
	private double minLongitude;	//request로 받아온 지도의 좌측 경도를 저장할 변수선언
	private double maxLongitude;	//request로 받아온 지도의 좌측 경도를 저장할 변수선언
	
	
	public LocalPageInfo() {
	}


	public LocalPageInfo(double minLatitude, double maxLatitude, double minLongitude, double maxLongitude) {
		this.minLatitude = minLatitude;
		this.maxLatitude = maxLatitude;
		this.minLongitude = minLongitude;
		this.maxLongitude = maxLongitude;
	}


	public double getMinLatitude() {
		return minLatitude;
	}


	public void setMinLatitude(double minLatitude) {
		this.minLatitude = minLatitude;
	}


	public double getMaxLatitude() {
		return maxLatitude;
	}


	public void setMaxLatitude(double maxLatitude) {
		this.maxLatitude = maxLatitude;
	}


	public double getMinLongitude() {
		return minLongitude;
	}


	public void setMinLongitude(double minLongitude) {
		this.minLongitude = minLongitude;
	}


	public double getMaxLongitude() {
		return maxLongitude;
	}


	public void setMaxLongitude(double maxLongitude) {
		this.maxLongitude = maxLongitude;
	}


	@Override
	public String toString() {
		return "LocalPageInfo [minLatitude=" + minLatitude + ", maxLatitude=" + maxLatitude + ", minLongitude="
				+ minLongitude + ", maxLongitude=" + maxLongitude + "]";
	}
}

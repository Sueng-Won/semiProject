package com.what.semi.common.template;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	public static Connection getConnection() {
		Connection con = null;
		try {
			//properties로 설정 정보를 분리
			//파일 경로를 변수에 저장
			//project ->properties-> java build path ->add folder 설정 필수!!
			String filePath = 
					JDBCTemplate.class.getResource("/oracleDriver.properties").getPath();
			//프로퍼티 생성
			Properties prop = new Properties();
			//저장한 파일 경로를 이용하여 파일을 불러옴
			prop.load(new FileReader(filePath));
			
			//키값을 이용하여 해당 값을 불러옴
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String id = prop.getProperty("id");
			String pwd = prop.getProperty("pwd");
			
			//실행 할 jdbc 라이브러리 등록
			Class.forName(driver);
			//접속 정보 설정
			con = DriverManager.getConnection(
					url, id, pwd);
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static void close(Connection con) {
		try {
			//커넥션이 null일 경우 close 호출 시 nullpointexception이 발생
			//이미 close 상태에서 close 요쳥 할 경우 sqlexception이 발생
			if(con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			if(rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void commit(Connection con) {
		try {
			if(con != null && !con.isClosed()) {
				con.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection con) {
		try {
			if(con != null && !con.isClosed()) {
				con.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

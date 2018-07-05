package com.what.semi.contract.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.contract.model.service.ContractService;
import com.what.semi.contract.model.vo.ContractConditionVo;
import com.what.semi.contract.model.vo.ContractVo;

@WebServlet("/updateContract.do")
public class UpdateContractServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateContractServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContractVo cv = null;
		ContractService cs = new ContractService();
		String recruitment_id = null;
		String resume_id = null;
		
		String start_work_time = null;	//QR코드로 근무 시작 시간을 입력하라는 명령을 저장할 변수
		String end_work_time = null;	//QR코드로 근무 종료 시간을 입력하라는 명령을 저장할 변수
		int state = -1;					//현재 계약상태를 저장할 변수
		
		if(null != request.getParameter("start_work_time")) {
			start_work_time = request.getParameter("start_work_time");
		}
		if(null != request.getParameter("end_work_time")) {
			end_work_time = request.getParameter("end_work_time");
		}
		if(null != request.getParameter("state")) {
			state = Integer.parseInt(request.getParameter("state"));
		}
		
		//넘어온 값을 통해 
		if(null != request.getParameter("recruitment_id")
				&& null != request.getParameter("resume_id")) {
			recruitment_id = request.getParameter("recruitment_id");
			resume_id = request.getParameter("resume_id");
			
			ContractConditionVo ccv = new ContractConditionVo(start_work_time, end_work_time, state);
			cv = new ContractVo(recruitment_id, resume_id);
			cs.updateContract(cv, ccv);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

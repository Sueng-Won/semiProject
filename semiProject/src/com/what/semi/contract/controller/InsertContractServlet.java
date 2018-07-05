package com.what.semi.contract.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.contract.model.service.ContractService;
import com.what.semi.contract.model.vo.ContractVo;


@WebServlet("/insertContract.do")
public class InsertContractServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertContractServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContractVo cv = null;
		ContractService cs = new ContractService();
		String recruitment_id = null;
		String resume_id = null;
		
		//이력서와 구인게시글의 아이디값이 있을경우 insert실행
		if(null != request.getParameter("recruitment_id")
				&& null != request.getParameter("resume_id")) {
			recruitment_id = request.getParameter("recruitment_id");
			resume_id = request.getParameter("resume_id");
			cv = new ContractVo(recruitment_id, resume_id);
			cs.insertContract(cv);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.what.semi.contract.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.what.semi.common.template.PageInfo;
import com.what.semi.common.template.PageTemplate;
import com.what.semi.contract.model.service.ContractService;
import com.what.semi.contract.model.vo.ContractVo;
import com.what.semi.resume.model.service.MyResumeService;
import com.what.semi.resume.model.vo.MyResumeVo;

/**
 * Servlet implementation class MyHiredListServlet
 */
@WebServlet("/myHiredList.do")
public class MyHiredListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyHiredListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		int contId = -1;
		if (request.getParameter("contId") != null) {
			contId = Integer.parseInt(request.getParameter("contId"));
		}

		ContractService cs = new ContractService();

		PageInfo pi = PageTemplate.myContractPaging(request, cs, id);

		ArrayList<ContractVo> myConList = new ContractService().selectMyContractList(pi.getCurrentPage(), pi.getLimit(),
				id);
		ArrayList<MyResumeVo> conResumeList = new ArrayList<MyResumeVo>();

		MyResumeVo temp = null;
		int flag = -1;
		for (int i = 0; i < myConList.size(); i++) {
			temp = new MyResumeService().selectMyResume(myConList.get(i).getJs_id(), myConList.get(i).getResume_id());
			for (int j = 0; j < conResumeList.size(); j++) {
				if (conResumeList.get(j).getResume_id()==temp.getResume_id()) {
					flag = 1;
				}
			}
			if (flag == -1) {
				conResumeList.add(temp);
			} else {
				flag = -1;
			}
		}

		RequestDispatcher view = null;
		String url = "";
		if (myConList != null) {
			url = "/views/member/hiredList.jsp";
			request.setAttribute("myConList", myConList);
			request.setAttribute("conResumeList", conResumeList);
			request.setAttribute("pi", pi);
			request.setAttribute("contId", contId);
		} else {
			System.out.println("고용내역오류");
		}
		view = request.getRequestDispatcher(url);
		view.forward(request, response);
	}

}

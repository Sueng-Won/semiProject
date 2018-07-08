package com.what.semi.qna.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.what.semi.common.template.PageInfo;
import com.what.semi.qna.model.service.QnaService;
import com.what.semi.qna.model.vo.QnaVo;

@WebServlet("/answerQna.do")
public class AnswerQnaServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    public AnswerQnaServlet() {
        super();
    }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       QnaService qs = new QnaService();   //서비스를 호출할 객체 선언
       String content = "";
       String answer = null;
       int q_no = -1;
       
         //=============답변을 올렸을때 수행될 로직============================
         if(null != request.getParameter("q_no")) {
            q_no = Integer.parseInt(request.getParameter("q_no"));
         }
         
         if(null != request.getParameter("answer")) {
            answer = request.getParameter("answer");
            qs.updateAnswer(answer, q_no);
         }
         
         //==========================================================
         content = qs.getContent(q_no);
         String url= "";
         if(-1 != q_no){
            request.setAttribute("q_no", q_no);
            request.setAttribute("answer", answer);
            request.setAttribute("content", content);
           url = "views/admin/answer.jsp";
         
         }
         RequestDispatcher view = request.getRequestDispatcher(url);
         view.forward(request, response);
         
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
package com.project.videos.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.videos.dao.MainDao;
import com.project.videos.vo.MainVO;

/**
 * Servlet implementation class VideoUpdate
 */
@WebServlet("/videoUpdate")
public class VideoUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int v_no = Integer.parseInt(request.getParameter("v_no"));
		String v_title = request.getParameter("v_title");
		String v_hashtag = request.getParameter("v_hashtag");
		String v_category = request.getParameter("v_category");
		String v_content = request.getParameter("v_content");
		
		MainVO vo = new MainVO(v_title, v_hashtag, v_content, v_category, v_no);
		
		response.setContentType("text/html; charset=utf-8;");
		PrintWriter out = response.getWriter();
		
		if (v_content == null || v_content.equals("") || v_content.equals("<p></p>")) {
			out.println("<script>alert('본문이 입력되지 않았습니다.'); history.go(-1);</script>");
		} else {
			int res = MainDao.getInstance().update(vo);
			if(res == 0) {
				System.out.println("수정 실패");
				response.sendRedirect("main");
			}else {
				System.out.println("수정 성공");
				response.sendRedirect("main");
			}
		}
		
		
	}

}

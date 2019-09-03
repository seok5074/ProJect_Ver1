package com.project.videos.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.videos.dao.MainDao;

/**
 * Servlet implementation class VideoDelete
 */
@WebServlet("/delete")
public class VideoDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int v_no = Integer.parseInt(request.getParameter("v_no"));
		int res = MainDao.getInstance().delete(v_no);
		if(res == 0) {
			System.out.println("삭제 실패");
			response.sendRedirect("main");
		}else {
			System.out.println("삭제 성공");
			response.sendRedirect("main");
		}
		
	}

}

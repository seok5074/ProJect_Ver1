package com.project.videos.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.videos.dao.MainDao;

/**
 * Servlet implementation class BoardRecUpdate
 */
@WebServlet("/video_recUpdate")
public class RecUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L; 

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		
		int v_no=Integer.parseInt(request.getParameter("v_no"));
		
		MainDao dao = MainDao.getInstance();
		
		int v_like = dao.recUpdate(v_no);
		
		PrintWriter out = response.getWriter();
		out.println(v_like);
	}

}

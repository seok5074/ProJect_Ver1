package com.project.videos.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.videos.dao.MainDao;
import com.project.videos.vo.MainVO;

/**
 * Servlet implementation class VideoUpdateForm
 */
@WebServlet("/videoUpdateForm")
public class VideoUpdateForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int v_no = Integer.parseInt(request.getParameter("v_no"));
		MainVO vo = MainDao.getInstance().selectOne(v_no);
		request.setAttribute("vo", vo);
		String forward_page = "videos/video_update_form.jsp"; // 경로 
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);
	}

}

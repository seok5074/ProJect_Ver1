package com.project.videos.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.videos.dao.MainDao;
import com.project.videos.vo.MainVO;
import com.project.videos.vo.V_ReplyVO;

/**
 * Servlet implementation class VideoDetail
 */
@WebServlet("/videoDetail")
public class VideoDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String PrevPage = request.getHeader("referer");
		int v_no = Integer.parseInt(request.getParameter("v_no"));
		
		if(v_no < 0) {
			response.sendRedirect(PrevPage);
		}
		
		MainVO vo = MainDao.getInstance().selectOne(v_no);
		request.setAttribute("vo", vo);
		
		MainDao dao = MainDao.getInstance();
		ArrayList<V_ReplyVO> replyList = dao.getReplyList(v_no);
		request.setAttribute("replyList", replyList);
		
		String forward_page = "videos/video_detail.jsp"; // 경로 
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);
	}

}

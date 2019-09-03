package com.project.videos.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project.videos.dao.MainDao;
import com.project.videos.vo.MainVO;

/**
 * Servlet implementation class VideoInsert
 */
@WebServlet("/videoInsert")
public class VideoInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		ServletContext application = request.getServletContext();
		
		String sumnail_path = "/upload/";
		String dir = application.getRealPath(sumnail_path);
		String sumnail_file = "no_file";
		String video_file = "no_file";
		
		int maxSize = 1024 * 1024 * 1000;
		
		MultipartRequest mr = new MultipartRequest(request, dir, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		File v_videoloc = mr.getFile("v_videoloc");
		File v_sumnailloc = mr.getFile("v_sumnailloc");
		
		if (v_sumnailloc != null) {
			sumnail_file = v_sumnailloc.getName();
		}
		if (v_videoloc != null) {
			video_file = v_videoloc.getName();
		}
		
		String v_title = mr.getParameter("v_title");
		String v_hashtag = mr.getParameter("v_hashtag");
		String v_content = mr.getParameter("v_content");
		String v_uploader = mr.getParameter("v_uploader");
		String v_category = mr.getParameter("v_category");
		
		System.out.println("v_uploader : " + v_uploader);
		
		if (v_uploader == null || v_uploader.equals("")) {
			v_uploader = "익명";
		}

		System.out.println("v_title : " + v_title);
		System.out.println("v_hashtag : " + v_hashtag);
		System.out.println("v_videoloc : " + v_videoloc);
		System.out.println("v_sumnailloc : " + v_sumnailloc);
		System.out.println("v_content : " + v_content);
		System.out.println("v_uploader : " + v_uploader);
		System.out.println("v_category : " + v_category);

		response.setContentType("text/html; charset=utf-8;");
		PrintWriter out = response.getWriter();
		MainVO main = new MainVO(v_uploader, video_file, sumnail_file, v_title, v_hashtag, v_content, v_category);
		if (v_content == null || v_content.equals("") || v_content.equals("<p></p>")) {
			out.println("<script>alert('본문이 입력되지 않았습니다.'); history.go(-1);</script>");
		} else {
			int res = MainDao.getInstance().insert(main);
			response.sendRedirect("main");
		}
	}
}
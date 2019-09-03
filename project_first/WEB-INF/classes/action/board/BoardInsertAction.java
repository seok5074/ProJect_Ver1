package action.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.bbs.beans.Board;
import com.jspstudy.bbs.dao.BoardDao;


/**
 * Servlet implementation class BoardInsertAction
 */
@WebServlet("/addProcess")
public class BoardInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * System.out.println("/addProcess ��û - get ��û�� ���� ó��"); }
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("/addProcess ��û - post ��û�� ���� ó��");
		
		
		request.setCharacterEncoding("utf-8");

		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		content =content.replaceAll("\r\n", "<br>");
		Board vo = new Board(name, subject, content);
		System.out.println("content"+content);
		
		response.setContentType("text/html;charset=utf-8;");
		PrintWriter out= response.getWriter();
		if(content == null || content.equals("") || content.equals("<p></p>")) {
			out.println("<script>alert('내용입력');history.go(-1);</script>");
		}else {
			//DB Insert
			int res =BoardDao.getInstance().insert(vo);
			System.out.println(content+"재입력");
			response.sendRedirect("mainBoardList");	
		}
		
		
	}
}

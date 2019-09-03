package action.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.bbs.beans.Board;
import com.jspstudy.bbs.dao.BoardDao;

/**
 * Servlet implementation class BoardModifyAction
 */
@WebServlet("/modify")
public class BoardModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		int idx = Integer.parseInt(request.getParameter("idx"));
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");

		Board vo = new Board(subject, content, idx);

		response.setContentType("text/html;charset=utf-8;");
		PrintWriter out = response.getWriter();
		if (content == null || content.equals("") || content.equals("<p></p>")) {
			out.println("<script>alert('내용입력');history.go(-1);</script>");
		} else {
			// DB modify
			int res = BoardDao.getInstance().modify(vo);
			System.out.println(content + "재입력");
			response.sendRedirect("mainBoardList");
		}
	}
}

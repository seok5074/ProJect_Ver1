package action.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.bbs.beans.Board;
import com.jspstudy.bbs.dao.BoardDao;


/**
 * Servlet implementation class BoardDeleteAction
 */
@WebServlet("/delete.do")
public class BoardDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		//DB delete
		int res =BoardDao.getInstance().delete(idx);
		
		response.sendRedirect("mainBoardList");
	}
}

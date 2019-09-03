package action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.bbs.beans.Board;
import com.jspstudy.bbs.dao.BoardDao;

/**
 * Servlet implementation class BoardInsertFormAction
 */
// board/addForm, boardAddForm  noticeAddForm, 
@WebServlet("/modifyForm")
public class BoardModifyFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	// get ��� ��û ó��
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");		
		int idx	= Integer.parseInt(request.getParameter("idx"));
		
		Board vo = BoardDao.getInstance().getBoard(idx);		
		request.setAttribute("board", vo);		
		
		RequestDispatcher disp = request.getRequestDispatcher("/board/boardInsertForm.jsp");
		disp.forward(request, response);
	}
}

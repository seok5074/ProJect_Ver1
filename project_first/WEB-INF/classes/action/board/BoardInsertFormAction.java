package action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardInsertFormAction
 */
// board/addForm, boardAddForm  noticeAddForm, 
@WebServlet("/addForm")
public class BoardInsertFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	// get ��� ��û ó��
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher disp = request.getRequestDispatcher("/board/addForm.jsp");
		disp.forward(request, response);
	}
}

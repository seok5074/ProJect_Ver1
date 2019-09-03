package action.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspstudy.bbs.beans.Board;
import com.jspstudy.bbs.beans.Reply;
import com.jspstudy.bbs.dao.BoardDao;


/**
 * Servlet implementation class BoardViewAction
 */
@WebServlet("/boardDetail")
public class BoardViewAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//view.do?idx=1
		int idx = Integer.parseInt(request.getParameter("idx"));
		BoardDao dao = BoardDao.getInstance();
		Board vo = dao.getBoard(idx);
		
		request.setAttribute("board", vo);
		
		ArrayList<Reply> replyList = dao.getReplyList(idx);
		request.setAttribute("replyList", replyList);
		
		//HttpSession session = request.getSession();
		/*
		if(session.getAttribute("show")==null) {
			BoardDao.getInstance().update_readhit(idx);
			session.setAttribute("show", true);
		}
		*/
		
		//forward
		String forward_page = "/board/boardDetail.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);

	}

}


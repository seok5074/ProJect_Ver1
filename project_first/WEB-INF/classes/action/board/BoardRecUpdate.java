package action.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.bbs.dao.BoardDao;

/**
 * Servlet implementation class BoardRecUpdate
 */
@WebServlet("/recUpdate")
public class BoardRecUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		
		int idx=Integer.parseInt(request.getParameter("idx"));
		
		BoardDao dao = BoardDao.getInstance();
		
		int likehit = dao.recUpdate(idx);
		
		//request.setAttribute("likehit", likehit);		
		// response.sendRedirect("boardDetail");
		PrintWriter out = response.getWriter();
		out.println(likehit);
	}

}

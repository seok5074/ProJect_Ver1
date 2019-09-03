package action.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.bbs.dao.MemDao;

/**
 * Servlet implementation class MemDeleteAction
 */
@WebServlet("/mem/delete")
public class MemDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int idx = Integer.parseInt(request.getParameter("idx"));
		
		int res = MemDao.getInstance().delete(idx);
		
		response.sendRedirect("list");


	}

}

package action.mem;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.bbs.beans.MemVo;
import com.jspstudy.bbs.dao.MemDao;

/**
 * Servlet implementation class MemListAction
 */
@WebServlet("/mem/list")
public class MemListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//회원정보 가져오기
				List<MemVo> list = MemDao.getInstance().selectList();
				
				//request binding
				request.setAttribute("list", list);

		//forward
		String forward_page = "mem_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);

	}

}
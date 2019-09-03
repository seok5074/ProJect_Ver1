package action.mem;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.bbs.beans.MemVo;
import com.jspstudy.bbs.dao.MemDao;

/**
 * Servlet implementation class MemMyPageModifyFormAction
 */
@WebServlet("/mem/mypage_modify_form")
public class MemMyPageModifyFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
request.setCharacterEncoding("utf-8");
		
		 String id = request.getParameter("id");
		
		//수정할 게시물의 id를 이용해서 얻어오기
		MemVo vo = MemDao.getInstance().selectOne(id);
		
	
				
		//request binding
		request.setAttribute("vo", vo);

		//forward
		String forward_page = "mem_mypage_modify_form.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);

	}

}

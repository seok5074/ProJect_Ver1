package action.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.bbs.beans.MemVo;
import com.jspstudy.bbs.dao.MemDao;

/**
 * Servlet implementation class MemModifyAction
 */
@WebServlet("/mem/modify")
public class MemModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

request.setCharacterEncoding("utf-8");
		
		
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String zipcode = request.getParameter("zipcode");
		String addr = request.getParameter("addr");
		String email = request.getParameter("email");
		String phon = request.getParameter("phon");
		String birth = request.getParameter("birth");
		String gender = request.getParameter("gender");
		
		
		int op = Integer.parseInt(request.getParameter("op"));
		int idx = Integer.parseInt(request.getParameter("idx"));
		
	
	
		
		MemVo  vo = new MemVo(idx, name, id, pwd, zipcode, addr, email, 
												phon, birth, gender, op);
		
		int res = MemDao.getInstance().update(vo);
		
		response.sendRedirect("list");

	}

}

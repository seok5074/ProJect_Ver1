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
 * Servlet implementation class MemInserForm
 */
@WebServlet("/mem/insert")
public class MemInserAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//수신인코딩
		request.setCharacterEncoding("utf-8");
		
		String name	 	= request.getParameter("name");
		String id		= request.getParameter("id");
		String pwd		= request.getParameter("pwd");	
		String zipcode	= request.getParameter("zipcode");
		String addr		= request.getParameter("addr");		
		String email = request.getParameter("email");
		String phon = request.getParameter("phon");
		String birth = request.getParameter("birth");		
		String gender = request.getParameter("gender");
		
		MemVo vo = new MemVo(name, id, pwd, zipcode, addr, email, phon, birth, gender);
		
		int res = MemDao.getInstance().insert(vo);
		
		response.sendRedirect("../mainBoardList");

	}

}
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
 * Servlet implementation class MemberCheckIdAction
 */
@WebServlet("/mem/check_id")
public class MemCheckIdAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//check_id.do?id=hong333
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		
		//id가 DB에 있냐?
		MemVo vo = MemDao.getInstance().selectOne(id);
		
		String result="no";
		if(vo==null) 
			result="yes"; //사용가능(id가 없다)
		
		String resultJSON = String.format("{\"result\":\"%s\"}", result);
		
		response.setContentType("text/json; charset=utf-8;");
		response.getWriter().print(resultJSON);

	}

}
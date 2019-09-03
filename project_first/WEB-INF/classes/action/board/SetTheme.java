package action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/setTheme")
public class SetTheme extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SetTheme() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 인코딩 설정
		request.setCharacterEncoding("utf-8");
		// 이전 페이지 얻어오기 
		String PrevPage = request.getHeader("referer");
		System.out.println(PrevPage);

		// theme의 값을 받아옴
		String theme = request.getParameter("theme");

		// theme가 없거나 light면 theme를 light로 설정
		if (theme == null || theme.equals("") || theme.equals("light")) {
			theme = "light";

		} else {
			// 아니면 dark로 설정
			theme = "dark";
		}
		HttpSession session = request.getSession();

		// 세션 생성
		session.setAttribute("nowTheme", theme);

		// 이전 페이지로 보내기 
		response.sendRedirect(PrevPage);
	}
}
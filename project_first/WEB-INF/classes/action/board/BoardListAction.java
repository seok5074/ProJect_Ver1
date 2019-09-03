package action.board;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.bbs.beans.Board;
import com.jspstudy.bbs.dao.BoardDao;

@WebServlet("/mainBoardList")
public class BoardListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	final int PAGE_SIZE = 10;
	final int PAGE_GROUP = 10;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");

		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int startRow = currentPage * PAGE_SIZE - (PAGE_SIZE - 1);
		// 1(startRow) = 1page *10 (page_size) -(10-1)
		int endRow = startRow + PAGE_SIZE - 1;
		int listCount = 0;
		ArrayList<Board> bList = null;

		BoardDao dao = BoardDao.getInstance();

		boolean searchOption = (type == null || type.equals("") || keyword == null || keyword.equals("")) ? false
				: true;
		System.out.println("searchOption:" + searchOption);

		if (!searchOption) {
			listCount = dao.getBoardCount();
		} else {
			listCount = dao.getBoardCount(type, keyword);
		}

		System.out.println("listCount=" + listCount);
		if (listCount > 0) {
			if (!searchOption) {
				bList = dao.boardList(startRow, endRow);
			} else {
				bList = dao.searchList(type, keyword, startRow, endRow);

				request.setAttribute("word", keyword);
				System.out.println("keyword : " + keyword);
				keyword = URLEncoder.encode(keyword, "utf-8");
				request.setAttribute("keyword", keyword);
				request.setAttribute("type", type);
			}
		}
		System.out.println("keyword" + keyword);
		int pageCount = listCount / PAGE_SIZE + (listCount % PAGE_SIZE == 0 ? 0 : 1);

		int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1 - (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);

		int endPage = startPage + PAGE_GROUP - 1;

		if (endPage > pageCount) {
			endPage = pageCount;
		}

		/*
		 * <c:set var="bList" value="<%= bList %>" /> <c:set var="currentPage"
		 * value="<%= currentPage %>" /> <c:set var="pageGroup"
		 * value="<%= PAGE_GROUP %>" /> <c:set var="pageCount" value="<%= pageCount %>"
		 * /> <c:set var="startPage" value="<%= startPage %>" /> <c:set var="endPage"
		 * value="<%= endPage %>" /> <c:set var="searchOption"
		 * value="<%= searchOption %>" />
		 * 
		 */

		request.setAttribute("bList", bList);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("PAGE_GROUP", PAGE_GROUP);
		request.setAttribute("PAGE_SIZE", PAGE_SIZE);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("searchOption", searchOption);

		String forward_page = "/board/mainBoardList.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// POST 요청에 대한 문자 셋 처리
		request.setCharacterEncoding("utf-8");

		doGet(request, response);
	}

}

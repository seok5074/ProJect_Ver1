<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/* 로그아웃 폼 내부에 hidden 필드로 currentURL을 지정하고 로그인과
	 * 로그아웃 요청시 URL을 자바스크립트를 이용해 currentURL의 값을
	 * window.location.href 값으로 지정하였다.
 	 **/
	String currentUrl = request.getParameter("currentURL");
	String PrevPage = request.getHeader("referer");
	/* 현재 세션을 삭제한다.
	 * 현재 세션이 삭제되기 때문에 세션 영역에 저장된 모든 데이터가 삭제된다. 
	 **/
	session.invalidate();
	
	/*
	// 아래와 같이 세션에 저장된 개별 속성만 삭제 할 수 있다.
	session.removeAttribute("isLogin");
	session.removeAttribute("id");
	*/	

	// 요청할 때 당시의 URL이 존재하지 않으면 boardList.jsp를 지정 한다.
	if(currentUrl == null || currentUrl.equals("")) {
		currentUrl = PrevPage;
	}
	
	 // 게시글 리스트 페이지로 Redirect 시킨다.	
	response.sendRedirect(currentUrl);
%>
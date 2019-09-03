<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.jspstudy.bbs.dao.*, com.jspstudy.bbs.beans.*" %>    
<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String PrevPage = request.getHeader("referer");
	System.out.println(PrevPage);
	
	
	String currentUrl = request.getParameter("currentURL");
		

	MemDao dao = MemDao.getInstance();
	int checkLogin = dao.checkMem(id, pwd);
	
	// 존재하지 않는 아이디면
	if(checkLogin == -1) {		
		response.setContentType("text/html; charset=utf-8");		
		out.println("<script>");
		out.println("	alert('" + id + "는 가입되지 않은 아이디 입니다.');");
		out.println("	window.history.back();");
		out.println("</script>");
		return;
	
	// 비밀번호가 틀리면
	} else if(checkLogin == 0) {		
		response.setContentType("text/html; charset=utf-8");
		out.println("<script>");
		out.println("	alert('비밀번호가 맞지 않습니다.');");
		out.println("	window.history.back();");
		out.println("</script>");
		return;
	
	// 로그인 성공이면
	} else if(checkLogin == 1) {	
		
		// 세션 영역의 속성에 id와 로그인 상태 정보를 저장 한다.
		session.setAttribute("id", id);
		session.setAttribute("isLogin", true);
	}
	
	// 요청할 때 당시의 URL이 존재하지 않으면 boardList.jsp를 지정 한다.
	if(currentUrl == null || currentUrl.equals("")) {
		currentUrl = "../main";
	}	

	 // 로그인 요청 할 당시의 URL로 Redirect 시킨다.
	System.out.println("currentUrl : " + currentUrl);
	response.sendRedirect(currentUrl);
%>
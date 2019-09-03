package com.jspstudy.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// ajax 요청을 처리하는 모든 모델 클래스의 슈퍼 인터페이스
public interface AjaxProcess {
	
	public void ajaxProcess(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException;
		
}

package com.jspstudy.board.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.board.action.AjaxProcess;
import com.jspstudy.board.action.ReplyDeleteAction;
import com.jspstudy.board.action.ReplyUpdateAction;
import com.jspstudy.board.action.ReplyWriteAction;
import com.project.videos.action.VideoReplyDeleteAction;
import com.project.videos.action.VideoReplyUpdateAction;
import com.project.videos.action.VideoReplyWriteAction;

// ajax 요청을 처리하는 Controller 클래스 
@WebServlet(name = "ajaxController", urlPatterns = "*.ajax")
public class AjaxController extends HttpServlet {

	public void doAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		 * Request 객체로 부터 요청 URI를 구한다. /20190819/replyWrite.ajax
		 * /20190819/replyUpdate.ajax
		 **/
		String command = request.getRequestURI();

		/*
		 * 요청 URI에서 ContextPath를 제외한 요청 명령어를 추출 한다. /replyUpdate.ajax 만 추출
		 */
		command = command.substring(request.getContextPath().length());
		System.out.println("command : " + command);

		/*
		 * 모든 모델 클래스의 슈퍼 인터페이스인 CommandProcess 타입의 변수를 선언 하고 null로 초기화 한다.
		 **/
		AjaxProcess ajaxProcess = null;

		/*
		 * /passCheck.ajax=com.jspstudy.bbs.ajax.PassCheckAction
		 * /recommend.ajax=com.jspstudy.bbs.ajax.RecommendAction
		 * /replyWrite.ajax=com.jspstudy.bbs.ajax.ReplyWriteAction
		 * /replyDelete.ajax=com.jspstudy.bbs.ajax.ReplyDeleteAction
		 * /replyUpdate.ajax=com.jspstudy.bbs.ajax.ReplyUpdateAction
		 */

		// 각각의 요청 명령에 맞는 클래스를 호출- 어떤명령인지 판단
		if (command.equals("/replyWrite.ajax")) {
			// 댓글 쓰기 기능을 제공하는 action 클래스 사용
			ajaxProcess = new ReplyWriteAction();

		} else if (command.equals("/replyUpdate.ajax")) {
			ajaxProcess = new ReplyUpdateAction();

		} else if (command.equals("/replyDelete.ajax")) {
			ajaxProcess = new ReplyDeleteAction();

		} else if (command.equals("/videoReplyWrite.ajax")) {
			ajaxProcess = new VideoReplyWriteAction();
			
		} else if (command.equals("/videoReplyUpdate.ajax")) {
			ajaxProcess = new VideoReplyUpdateAction();
			
		} else if (command.equals("/videoReplyDelete.ajax")) {
			ajaxProcess = new VideoReplyDeleteAction();
		}

		if (ajaxProcess != null) {
			ajaxProcess.ajaxProcess(request, response);
		}

	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doAjax(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doAjax(request, response);
	}
}

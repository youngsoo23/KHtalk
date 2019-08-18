package semiProject.action;

import javax.servlet.http.HttpServletRequest;

import semiProject.model.noticeDAO;

public class ViewAction {

	public void execute(HttpServletRequest req) {
		int num = Integer.parseInt(req.getParameter("num"));
		noticeDAO dao = noticeDAO.getInstance();
		dao.readCountMethod(num);//조회수 증가 메서드 호출
		req.setAttribute("dto", dao.viewMethod(num));
		/////////////////조회수 증가 추가
		

	}//end execute

}//end class

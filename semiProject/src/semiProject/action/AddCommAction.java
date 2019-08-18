package semiProject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semiProject.model.ChatDAO;

public class AddCommAction implements BoardActionImp{

	@Override
	public void execute(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");
		int studyid = Integer.parseInt(req.getParameter("studyid"));
		String content = req.getParameter("content");
		
		ChatDAO chatdao = ChatDAO.getInstance();
		chatdao.insertChat(studyid, userId, content);
		req.setAttribute("studyid",studyid);
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}
	

}

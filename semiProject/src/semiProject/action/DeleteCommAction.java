package semiProject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semiProject.model.ChatDAO;

public class DeleteCommAction implements BoardActionImp {

	@Override
	public void execute(HttpServletRequest req) {
		int chatNum= Integer.parseInt(req.getParameter("chatNum"));
		int studyid = Integer.parseInt(req.getParameter("studyid"));
		ChatDAO dao = ChatDAO.getInstance();
		dao.deleteChat(chatNum);
		req.setAttribute("studyid",studyid);
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

}

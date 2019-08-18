package semiProject.action;

import javax.servlet.http.HttpServletRequest;

import semiProject.model.noticeDAO;

public class deleteAction {

	public void execute(HttpServletRequest req) {
		int num = Integer.parseInt(req.getParameter("num"));
		noticeDAO dao = noticeDAO.getInstance();

		dao.deleteMethod(num);

	}
}

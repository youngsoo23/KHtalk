package semiProject.action;


import javax.servlet.http.HttpServletRequest;

import semiProject.model.noticeDAO;
import semiProject.model.noticeDTO;

public class updateFormAction {
	
	public void execute(HttpServletRequest req) {
		int num = Integer.parseInt(req.getParameter("num"));
		noticeDAO dao = noticeDAO.getInstance();
		noticeDTO dto = dao.viewMethod(num);
		req.setAttribute("dto", dto);
		
	}
}

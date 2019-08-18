package semiProject.action;


import javax.servlet.http.HttpServletRequest;

import semiProject.model.noticeDAO;
import semiProject.model.noticeDTO;

public class WriteAction {
	public void execute(HttpServletRequest req) {
		
		noticeDAO dao = noticeDAO.getInstance();
		noticeDTO dto = new noticeDTO();
		dto.setUserId(req.getParameter("userId"));
		dto.setSubject(req.getParameter("subject"));
		dto.setContent(req.getParameter("content"));
		dao.insertMethod(dto);
	}
	
}

package semiProject.action;

import javax.servlet.http.HttpServletRequest;

import semiProject.model.noticeDAO;
import semiProject.model.noticeDTO;

public class updateAction {
	public void execute(HttpServletRequest req) {
		noticeDTO dto = new noticeDTO();
		noticeDAO dao = noticeDAO.getInstance();
		int num = Integer.parseInt(req.getParameter("num"));
		
		dto.setSubject(req.getParameter("subject"));
		dto.setContent(req.getParameter("content"));
		dto.setNum(num);
		dao.updateMethod(dto);
		
		
	}
}

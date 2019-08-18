package semiProject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semiProject.model.RoomDTO;
import semiProject.model.StudyDAO;

public class StudyDeleteAction implements BoardActionImp {

	@Override
	public void execute(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");
		int studyid = Integer.parseInt(req.getParameter("studyid"));
		
		StudyDAO dao = StudyDAO.getInstance();
		RoomDTO dto = new RoomDTO();
		dto.setUserId(userId);
		dto.setStudyid(studyid);
		dao.deleteRoom(dto);
		req.setAttribute("studyid", studyid);
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

	}

}

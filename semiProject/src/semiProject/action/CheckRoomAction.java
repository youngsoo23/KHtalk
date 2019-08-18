package semiProject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semiProject.model.StudyDAO;

public class CheckRoomAction implements BoardActionImp {

	@Override
	public void execute(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");
		int studyid = Integer.parseInt(req.getParameter("studyid"));
		StudyDAO dao = StudyDAO.getInstance();
		int chkRoom = dao.checkRoom(userId, studyid);
		req.setAttribute("chkRoom",chkRoom);
		req.setAttribute("studyid",studyid );
		
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

}

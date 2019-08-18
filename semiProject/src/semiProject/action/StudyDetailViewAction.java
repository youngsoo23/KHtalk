package semiProject.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semiProject.model.ChatDAO;
import semiProject.model.ChatDTO;
import semiProject.model.RoomDAO;
import semiProject.model.StudyDAO;
import semiProject.model.UserDTO;

public class StudyDetailViewAction implements BoardActionImp {
	@Override
	public void execute(HttpServletRequest req) {

		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");
		int studyid = Integer.parseInt(req.getParameter("studyid"));
		String leaderId = (String) req.getParameter("leaderId");
		StudyDAO dao = StudyDAO.getInstance();
		dao.readCountMethod(studyid);
		int chkRoom = dao.checkRoom(userId, studyid);

		req.setAttribute("subject", req.getParameter("subject"));
		req.setAttribute("dto", dao.viewMethod(studyid));
		req.setAttribute("studyid", studyid);
		req.setAttribute("chkRoom", chkRoom);

		ChatDAO chatdao = ChatDAO.getInstance();
		List<ChatDTO> chatList = new ArrayList<ChatDTO>();

		chatList = chatdao.viewChat(studyid);
		req.setAttribute("chatList", chatList);
		req.setAttribute("leaderId", leaderId);

////////////////추가 방장인지 아닌지 체크
		RoomDAO rdao = RoomDAO.getInstance();
		
		rdao.getStudyManager(studyid);
		req.setAttribute("roommanagernum", rdao.getStudyManager(studyid));

		rdao.getStudyChk(userId, studyid );
		req.setAttribute("roomnum", rdao.getStudyChk(userId,studyid));

	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {

	}
}
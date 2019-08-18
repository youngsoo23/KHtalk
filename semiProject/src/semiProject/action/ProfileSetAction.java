package semiProject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semiProject.model.UserDAO;

public class ProfileSetAction implements SemiActionImp{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");
		UserDAO dao = UserDAO.getInstance();
		String content = dao.getContent(userId);
		req.setAttribute("content", content);
		String mypicture = dao.showUserPicture(userId);
		req.setAttribute("mypicture", mypicture);
		
	}

}//end class

package semiProject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semiProject.model.UserDAO;

public class IdCheckAction implements SemiActionImp{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException {
		UserDAO dao = UserDAO.getInstance();
		int idcheck = dao.idCheck(req.getParameter("userId"));
		req.setAttribute("idchk", idcheck);
	}

}

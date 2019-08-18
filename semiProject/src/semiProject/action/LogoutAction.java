package semiProject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements SemiActionImp{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");
		if (userId != null) {
			session.invalidate();
		}	
	}
}//end class

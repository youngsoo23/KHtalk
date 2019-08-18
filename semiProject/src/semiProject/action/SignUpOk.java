package semiProject.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semiProject.model.UserDAO;
import semiProject.model.UserDTO;

public class SignUpOk implements SemiActionImp{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException {
		String userId= req.getParameter("userId");
		String userPassword = req.getParameter("userPassword");
		String userName = req.getParameter("userName");
		String userEmail = req.getParameter("userEmail");
		resp.setCharacterEncoding("UTF-8");
		UserDAO dao = UserDAO.getInstance();
		UserDTO dto = new UserDTO();
		dto.setUserId(userId);
		dto.setUserPassword(userPassword);
		dto.setUserName(userName);
		dto.setEmail(userEmail);
		
		dao.insertUser(dto);
	}
}//end class

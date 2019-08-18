package semiProject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semiProject.model.freeDAO;

public class fUpdateFormAction implements BoardActionImp{

	@Override
	public void execute(HttpServletRequest req) {
		freeDAO dao = freeDAO.getInstance();
		int num=Integer.parseInt(req.getParameter("num"));
		req.setAttribute("dto", dao.viewMethod(num));
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		
	}

}//end class

package semiProject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import semiProject.model.StudyDAO;

public class StudyUpdateFormAction implements BoardActionImp{

	@Override
	public void execute(HttpServletRequest req) {
		int num = Integer.parseInt(req.getParameter("num"));
		StudyDAO dao = StudyDAO.getInstance();
		req.setAttribute("dto", dao.viewMethod(num));
	} // end execute()

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
	}

}

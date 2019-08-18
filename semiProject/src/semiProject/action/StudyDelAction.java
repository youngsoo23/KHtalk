package semiProject.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semiProject.model.StudyDAO;

public class StudyDelAction implements BoardActionImp {

	@Override
	public void execute(HttpServletRequest req) {
		int num = Integer.parseInt(req.getParameter("num"));
		String saveDirectory= req.getSession().getServletContext().getRealPath("/");
		StudyDAO dao = StudyDAO.getInstance();
		String filename = dao.fileMethod(num);
		if(filename != null) {
			File file = new File(saveDirectory, filename);
			file.delete();
		}
		
		dao.deleteMethod(num);
	} // end execute() /////////////////////


	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {

	}

} // end class

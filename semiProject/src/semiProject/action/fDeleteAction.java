package semiProject.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semiProject.model.freeDAO;

public class fDeleteAction implements BoardActionImp {

	@Override
	public void execute(HttpServletRequest req) {
		String saveDirectory = "c:/temp";
//		String saveDirectory= req.getSession().getServletContext().getRealPath("/");
		freeDAO dao = freeDAO.getInstance();
		int num=Integer.parseInt(req.getParameter("num"));
		String filename = dao.fileMethod(num);
		
		//첨부파일 삭제
		if(filename!=null) {
			File oldFile = new File(saveDirectory, filename);
			oldFile.delete();
		}
		
		dao.deleteMethod(num);
		
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		
	}

}//end class

package semiProject.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import semiProject.model.freeDAO;
import semiProject.model.freeDTO;

public class fUpdateProAction implements BoardMultiImp {

	@Override
	public MultipartRequest executeMulti(HttpServletRequest req) {
		MultipartRequest multi = null;
		String saveDirectory = "c:/temp";
//		String saveDirectory= req.getSession().getServletContext().getRealPath("/");
		File file = new File(saveDirectory);
		
		if(!file.exists())
			file.mkdir();
		
		int maxPostSize = 1000000000;
		String encoding = "UTF-8";
		
		try {
			multi=new MultipartRequest(req, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		freeDAO dao = freeDAO.getInstance();
		freeDTO dto = new freeDTO();
		
		int num = Integer.parseInt(multi.getParameter("num"));
		String password = multi.getParameter("password");
		
		String filename = dao.fileMethod(num);
		
		if(multi.getFilesystemName("upload")!=null) {
			if(filename!=null) {
				File oldFile = new File(saveDirectory, filename);
				oldFile.delete();
			}
			dto.setUpload(multi.getFilesystemName("upload"));
		}else {
			if(filename!=null) {
				dto.setUpload(filename);
			}
		}
		
		dto.setSubject(multi.getParameter("subject"));
		dto.setContent(multi.getParameter("content"));
		dto.setNum(num);
		
		dao.updateMethod(dto);
		
		return multi;
	}//end executeMulti

}//end class

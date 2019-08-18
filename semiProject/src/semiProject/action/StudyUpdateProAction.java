package semiProject.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.swing.border.Border;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import semiProject.model.StudyDAO;
import semiProject.model.StudyDTO;

public class StudyUpdateProAction implements BoardMultiImp{

	@Override
	public MultipartRequest executeMulti(HttpServletRequest req) {
		MultipartRequest multi = null;
//		String saveDirectory = "C:\\study\\eclipse-workspace\\semiProject\\WebContent\\khtalk\\tableimage";
		String saveDirectory= req.getSession().getServletContext().getRealPath("/");
		File file = new File(saveDirectory);
		if(!file.exists())
			file.mkdir();
		int maxPostSize = 100000000; // 1GB
		String encoding = "UTF-8";
		
		try {
			multi = new MultipartRequest(req, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StudyDAO dao = StudyDAO.getInstance();
		StudyDTO dto = new StudyDTO();

		int num = Integer.parseInt(multi.getParameter("num"));

		String filename = dao.fileMethod(num);	// 기존에 board 테이블에 저장되어 있는 첨부파일명
		
		if(multi.getFilesystemName("main_photo") != null) {	// 수정 첨부파일이 있으면
			if(filename != null) { // 기존 첨부파일이 있으면
				File oldFile = new File(saveDirectory, filename);
				oldFile.delete();	// 파일을 삭제한다. (c:/temp)
			}
			dto.setMain_photo(multi.getFilesystemName("main_photo"));
		} else {	// 새로 수정한 첨부파일이 없으면
			if(filename != null) { // 기존 첨부파일이 있으면
				dto.setMain_photo(filename);
			}
		}
		
		dto.setUserId(multi.getParameter("userId"));
		dto.setSubject(multi.getParameter("subject"));
		dto.setSemicontent(multi.getParameter("semicontent"));
		dto.setContent(multi.getParameter("content"));
		dto.setMain_photo(multi.getFilesystemName("main_photo"));
		dto.setNum(num);
		
		dao.updateMethod(dto);
		
		return multi;
	}
}

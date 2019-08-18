package semiProject.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import semiProject.model.RoomDTO;
import semiProject.model.StudyDAO;
import semiProject.model.StudyDTO;

public class StudyAddAction implements BoardMultiImp{
	@Override
	public MultipartRequest executeMulti(HttpServletRequest req) {
		MultipartRequest multi = null;
		String saveDirectory= req.getSession().getServletContext().getRealPath("/");
		File file = new File(saveDirectory);
		if(!file.isDirectory()) {
			file.mkdir();
		}
		
		String encoding = "UTF-8";
		int maxPostSize = 1*1000*1000*1000; // 1GB
		
		
		try {
			multi=new MultipartRequest(req ,saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StudyDAO dao = StudyDAO.getInstance();
		StudyDTO dto = new StudyDTO();
		
		dto.setUserId(multi.getParameter("userId"));
		dto.setSubject(multi.getParameter("subject"));
		dto.setSemicontent(multi.getParameter("semicontent"));
		dto.setContent(multi.getParameter("content"));
		dto.setMain_photo(multi.getFilesystemName("main_photo"));
		
		String leaderId = multi.getParameter("userId").toString();
//		String param = "pageNum="+multi.getParameter("pageNum");
//				param += "&searchKey="+multi.getParameter("searchKey");
//				param += "&searchWord="+multi.getParameter("searchWord");
		dao.insertMethod(dto);
		int roomNum = dao.getStudyId(leaderId);
		RoomDTO rdto = new RoomDTO();
		rdto.setStudyid(roomNum);
		rdto.setUserId(leaderId);
		try {
			dao.joinRoom(rdto);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return multi;
	}
}

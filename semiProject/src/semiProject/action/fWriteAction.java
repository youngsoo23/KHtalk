package semiProject.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import semiProject.model.freeDAO;
import semiProject.model.freeDTO;

public class fWriteAction implements BoardMultiImp {

	@Override
	public MultipartRequest executeMulti(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("userId");
		/* int userNum = (int) session.getAttribute("userNum"); */
		
		MultipartRequest multi = null;
		String saveDirectory = "c:/temp";
//		String saveDirectory= req.getSession().getServletContext().getRealPath("/");
		File file = new File(saveDirectory);
//		System.out.println(req.getSession().getServletContext().getRealPath("/"));
		
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
		
//		dto.setUserNum(userNum);
		dto.setUserId(multi.getParameter("userId"));
		dto.setSubject(multi.getParameter("subject"));
		dto.setContent(multi.getParameter("content"));
		dto.setUpload(multi.getFilesystemName("upload"));
		dto.setIp(req.getRemoteAddr());
		
		//답변글 처리
		if(multi.getParameter("re_level")!=null) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("ref", Integer.parseInt(multi.getParameter("ref")));
			map.put("re_step", Integer.parseInt(multi.getParameter("re_step")));
			
			//re_step값 증가
			dao.reStepMethod(map);
			
			dto.setRef(Integer.parseInt(multi.getParameter("ref")));
			dto.setRe_step(Integer.parseInt(multi.getParameter("re_step"))+1);
			dto.setRe_level(Integer.parseInt(multi.getParameter("re_level"))+1);
		}
		dao.insertMethod(dto);
		return multi;
	}//end executeMulti()

}//end class

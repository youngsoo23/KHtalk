package semiProject.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import semiProject.model.UserDAO;
import semiProject.model.UserDTO;

public class ProfileUpdateAction implements SemiActionImp {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException {
		MultipartRequest multi = null;
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");
		UserDAO dao = UserDAO.getInstance();
		UserDTO dto = new UserDTO();		
		String saveDirectory= req.getSession().getServletContext().getRealPath("/");
		File file = new File(saveDirectory);
		if (!file.isDirectory())
			file.mkdir();
		
		int maxPostSize = 1 * 1000 * 1000 * 1000; // 1GB
		String encoding = "UTF-8";
		try {
			multi = new MultipartRequest(req, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
		} catch (IOException e) {
			e.printStackTrace();
		}
		String filepath = multi.getFilesystemName("filepath");
		String content = multi.getParameter("content");
		String oldpicture = multi.getParameter("oldpicture");
		if(oldpicture != null) {
			 File deletefile = new File("C:\\study\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\semiProject\\",oldpicture);
			 	deletefile.delete();
		}
		dto.setUserId(userId);
		dto.setContent(content);
		dao.updatePicture(filepath,userId);
		dao.updateContent(dto);
		
	}
}// end class

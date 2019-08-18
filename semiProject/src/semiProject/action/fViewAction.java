package semiProject.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semiProject.model.freeDAO;
import semiProject.model.freeDTO;

public class fViewAction implements BoardActionImp {

	@Override
	public void execute(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");
		
		/* int chk = 0; */
		
		freeDAO dao = freeDAO.getInstance();
		List<freeDTO> fList = new ArrayList<freeDTO>();
		
		
		/*chk=dao.searchUser(userId);
		aList=dao.selectNum(chk);
		
		
		 */
		
		int num=Integer.parseInt(req.getParameter("num"));
		
		for(int i=0; i<fList.size(); i++) {
			if(fList.get(i).getNum()==num) {
				req.setAttribute("aList", fList);
			}
		}
		dao.readCountMethod(num);
		req.setAttribute("dto", dao.viewMethod(num));
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

}//end class
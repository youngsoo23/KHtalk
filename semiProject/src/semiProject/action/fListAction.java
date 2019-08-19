package semiProject.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semiProject.model.fPageDTO;
import semiProject.model.freeDAO;

public class fListAction implements BoardActionImp{

	@Override
	public void execute(HttpServletRequest req) {
		freeDAO dao = freeDAO.getInstance();
		
		String pageNum=req.getParameter("pageNum");
		
		if(pageNum==null || pageNum.equals("null")|| pageNum.equals("")) {
			pageNum="1";
		}
		System.out.println("fdsdf");
		
		int currentPage = Integer.parseInt(pageNum);
		
		String searchKey = req.getParameter("searchKey");
		String searchWord = req.getParameter("searchWord");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("searchKey", searchKey);
		map.put("searchWord", searchWord);
		
		int cnt = dao.TotalCount(map);
		if(cnt>0) {
			fPageDTO pdto = new fPageDTO(currentPage, cnt, searchKey, searchWord);
			req.setAttribute("fpdto", pdto);
			req.setAttribute("fList", dao.listMethod(pdto));
		}
		
	}//end execute()

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}
}// end class

package semiProject.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semiProject.model.PageDTO;
import semiProject.model.SpageDTO;
import semiProject.model.StudyDAO;

public class StudyAction implements BoardActionImp{
	@Override
	public void execute(HttpServletRequest req) {
		StudyDAO dao = StudyDAO.getInstance();
		String pageNum = req.getParameter("pageNum");
		if(pageNum==null || pageNum.equals("null")|| pageNum.equals("")) {
			pageNum="1";
		}
		HttpSession session = req.getSession();
		String userIdd = (String) session.getAttribute("userId");
		
		int currentPage = Integer.parseInt(pageNum);
		String searchKey = req.getParameter("searchKey");
		String searchWord = req.getParameter("searchWord");
		String userId = req.getParameter("userId");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("searchKey", searchKey);
		map.put("searchWord", searchWord);
		int cnt = dao.rowTotalCount(map);
		
		
		if(cnt>0) {
			PageDTO pdto = new PageDTO(currentPage, cnt, searchKey, searchWord);
			SpageDTO spdto = new SpageDTO(currentPage, cnt, searchKey,searchWord);
			req.setAttribute("pdto", pdto);
			req.setAttribute("aList", dao.listMethod(pdto));
			
			req.setAttribute("spdto", spdto);
			req.setAttribute("sList", dao.listMethod(spdto));
			req.setAttribute("uList", dao.userListMethod(userIdd));
		}
		req.setAttribute("userId", userId);
	}
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
	}
}

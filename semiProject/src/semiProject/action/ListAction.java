package semiProject.action;

import javax.servlet.http.HttpServletRequest;

import semiProject.model.PageDTO;
import semiProject.model.noticeDAO;

public class ListAction {
	
	public void execute(HttpServletRequest req) {
		noticeDAO dao = noticeDAO.getInstance();
		
		String pageNum=req.getParameter("pageNum");

		if(pageNum==null || pageNum.equals("null")|| pageNum.equals("")) {
			pageNum="1";
		}
		int currentPage=Integer.parseInt(pageNum);

		int cnt=dao.rowTotalCount();
		
		if(cnt>0) {
			PageDTO pdto=new PageDTO(currentPage, cnt);
			req.setAttribute("npdto", pdto);
			req.setAttribute("nList", dao.listMethod(pdto));
		}
	}// end execute()

}// end class

package semiProject.action;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;

public interface BoardMultiImp {
	public MultipartRequest executeMulti(HttpServletRequest req);
}//end interface

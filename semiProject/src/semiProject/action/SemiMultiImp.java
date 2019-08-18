package semiProject.action;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;

public interface SemiMultiImp {
	public MultipartRequest executeMulti(HttpServletRequest req);
} // end interface

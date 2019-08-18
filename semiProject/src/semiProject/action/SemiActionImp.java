package semiProject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SemiActionImp {
	public void execute(HttpServletRequest req, 
			HttpServletResponse resp)throws ClassNotFoundException;
}

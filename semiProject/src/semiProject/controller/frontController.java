package semiProject.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;

import semiProject.action.AddCommAction;
import semiProject.action.CheckRoomAction;
import semiProject.action.DeleteCommAction;
import semiProject.action.IdCheckAction;
import semiProject.action.ListAction;
import semiProject.action.LogoutAction;
import semiProject.action.ProfileSetAction;
import semiProject.action.ProfileUpdateAction;
import semiProject.action.QuitAction;
import semiProject.action.SignUpOk;
import semiProject.action.StudyAction;
import semiProject.action.StudyAddAction;
import semiProject.action.StudyDelAction;
import semiProject.action.StudyDeleteAction;
import semiProject.action.StudyDetailViewAction;
import semiProject.action.StudyJoinAction;
import semiProject.action.StudyUpdateFormAction;
import semiProject.action.StudyUpdateProAction;
import semiProject.action.ViewAction;
import semiProject.action.WriteAction;
import semiProject.action.deleteAction;
import semiProject.action.fDeleteAction;
import semiProject.action.fFileDownloadAction;
import semiProject.action.fListAction;
import semiProject.action.fUpdateFormAction;
import semiProject.action.fUpdateProAction;
import semiProject.action.fViewAction;
import semiProject.action.fWriteAction;
import semiProject.action.updateAction;
import semiProject.action.updateFormAction;
import semiProject.model.PageDTO;
import semiProject.model.UserDAO;
import semiProject.model.UserDTO;
import semiProject.model.noticeDAO;

@WebServlet("/semi/*")
public class frontController extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			doProcess(req, resp);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// end doGet()

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			doProcess(req, resp);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// end doPost()

	protected void doProcess(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, ClassNotFoundException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json;charset=utf-8");
		String uri = req.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"));
		String path = "";

		// login
		String userId = req.getParameter("userId");
		String userPassword = req.getParameter("userPassword");

		JSONObject object = new JSONObject();

		UserDTO dto = new UserDTO();
		dto.setUserId(userId);
		dto.setUserPassword(userPassword);

		UserDAO userdao = UserDAO.getInstance();
		int cnt = userdao.userCheck(dto);
		String userName = userdao.getUserName(dto);
		String userEmail = userdao.getUserEmail(dto);
		int userNum = userdao.getUserNum(userId);
		int userStudyid = userdao.getUserStudyid(dto);
		// HttpSession session = null;
		// ArrayList<Integer> userStudyid = userdao.getUserStudyid(dto);

		// 공지사항 목록 보기
		PageDTO pdto = new PageDTO(1, 3);

		// session 로그인
		if (cnt == 1) {
			HttpSession session = req.getSession();
			session.setAttribute("userDTO", dto);
			session.setAttribute("userId", userId);
			session.setAttribute("userName", userName);
			session.setAttribute("userEmail", userEmail);
			session.setAttribute("userNum", userNum);
			session.setAttribute("userStudyid", userStudyid);

			session.setMaxInactiveInterval(30 * 60);
		} else {
			req.setAttribute("userId", userId);
			// req.setAttribute("userStudyid", userStudyid);
		}

		if (action.equals("/*") || action.equals("/index")) {
			noticeDAO dao = noticeDAO.getInstance();
			req.setAttribute("noticelist", dao.listMethod(pdto));
			ProfileSetAction psa = new ProfileSetAction();
			psa.execute(req, resp);
			StudyAction study = new StudyAction();
			study.execute(req);
			path = "/khtalk/index.jsp";
		} else if (action.equals("/SignUpOk")) {
			SignUpOk suo = new SignUpOk();
			suo.execute(req, resp);
			resp.sendRedirect("index");
		} else if (action.equals("/logOut")) {
			LogoutAction logout = new LogoutAction();
			logout.execute(req, resp);
			resp.sendRedirect("index");
		} else if (action.equals("/idchk.do")) {
			IdCheckAction ica = new IdCheckAction();
			ica.execute(req, resp);
			object.put("res", req.getAttribute("idchk"));
			PrintWriter out = resp.getWriter();
			out.print(object);
			out.flush();
		} else if (action.equals("/notice")) {
			path = "/khtalk/notice.jsp";
		} else if (action.equals("/profile")) {
			ProfileSetAction psa = new ProfileSetAction();
			psa.execute(req, resp);
			StudyAction study = new StudyAction();
			study.execute(req);
			path = "/khtalk/profile.jsp";
		} else if (action.equals("/setting")) {
			ProfileSetAction psa = new ProfileSetAction();
			psa.execute(req, resp);
			StudyAction study = new StudyAction();
			study.execute(req);
			path = "/khtalk/setting.jsp";
		} else if (action.equals("/fixedProfile")) {
			ProfileUpdateAction pua = new ProfileUpdateAction();
			pua.execute(req, resp);
			StudyAction study = new StudyAction();
			study.execute(req);
			resp.sendRedirect("profile");
		} else if (action.equals("/quit")) {
			QuitAction qa = new QuitAction();
			qa.execute(req, resp);
			LogoutAction logout = new LogoutAction();
			logout.execute(req, resp);
			resp.sendRedirect("index");	
		}
			
		
		// ------------------------자유게시판 ------------------------------

		else if (action.equals("/freeboard") || action.equals("/freelist.do")) {
			ProfileSetAction psa = new ProfileSetAction();
			psa.execute(req, resp);
			StudyAction study = new StudyAction();
			study.execute(req);
			fListAction list = new fListAction();
			list.execute(req);
			path = "/khtalk/freeboard.jsp";
		} else if (action.equals("/setting")) {
			ProfileSetAction psa = new ProfileSetAction();
			psa.execute(req, resp);
			StudyAction study = new StudyAction();
			study.execute(req);
			path = "/khtalk/setting.jsp";
		} else if (action.equals("/fixedProfile")) {
			ProfileUpdateAction pua = new ProfileUpdateAction();
			pua.execute(req, resp);
			StudyAction study = new StudyAction();
			study.execute(req);
			resp.sendRedirect("profile");
		} else if (action.equals("/freeboard")) {
			ProfileSetAction psa = new ProfileSetAction();
			psa.execute(req, resp);
			StudyAction study = new StudyAction();
			study.execute(req);
			path = "path=/khtalk/freeboard.jsp";
		} else if (action.equals("/freeview.do")) {
			fViewAction view = new fViewAction();
			view.execute(req);
			ProfileSetAction psa = new ProfileSetAction();
			psa.execute(req, resp);
			StudyAction study = new StudyAction();
			study.execute(req);
			path = "/khtalk/freeview.jsp";
		} else if (action.equals("/freewriteForm.do")) {
			ProfileSetAction psa = new ProfileSetAction();
			psa.execute(req, resp);
			StudyAction study = new StudyAction();
			study.execute(req);
			path = "/khtalk/freewrite.jsp";
		} else if (action.equals("/freewrite.do")) {
			fWriteAction write = new fWriteAction();
			MultipartRequest multi = write.executeMulti(req);
			String param = "pageNum=" + multi.getParameter("pageNum");
			param += "&searchKey=" + multi.getParameter("searchKey");
			param += "&searchWord=" + multi.getParameter("searchWord");
			resp.sendRedirect("freelist.do?" + param);
		} else if (action.equals("/freedownload.do")) {
			fFileDownloadAction download = new fFileDownloadAction();
			download.execute(req, resp);
			// 아래 메소드 수행할 필요 없으므로 현재메소드 빠져나옴
			return;
		} else if (action.equals("/freeupdateForm.do")) {
			fUpdateFormAction updateForm = new fUpdateFormAction();
			updateForm.execute(req);
			ProfileSetAction psa = new ProfileSetAction();
			psa.execute(req, resp);
			StudyAction study = new StudyAction();
			study.execute(req);
			path = "/khtalk/freeupdate.jsp";
		} else if (action.equals("/freeupdatePro.do")) {
			fUpdateProAction updatePro = new fUpdateProAction();
			MultipartRequest multi = updatePro.executeMulti(req);
			String param = "pageNum=" + multi.getParameter("pageNum");
			param += "&searchKey=" + multi.getParameter("searchKey");
			param += "&searchWord=" + multi.getParameter("searchWord");
			resp.sendRedirect("freelist.do?" + param);
		} else if (action.equals("/freedelete.do")) {
			fDeleteAction delete = new fDeleteAction();
			delete.execute(req);
			String param = "pageNum=" + req.getParameter("pageNum");
			param += "&searchKey=" + req.getParameter("searchKey");
			param += "&searchWord=" + req.getParameter("searchWord");
			resp.sendRedirect("freelist.do?" + param);
		}

		// -------------------공지사항 ---------------------------
		else if (action.equals("/notice.do")) {
			StudyAction study = new StudyAction();
			study.execute(req);
			ListAction list = new ListAction();
			list.execute(req);
			ProfileSetAction psa = new ProfileSetAction();
			psa.execute(req, resp);

			path = "/khtalk/notice.jsp";
		} else if (action.equals("/nview.do")) {
			StudyAction study = new StudyAction();
			study.execute(req);
			ViewAction view = new ViewAction();
			view.execute(req);
			ProfileSetAction psa = new ProfileSetAction();
			psa.execute(req, resp);
			path = "/khtalk/nview.jsp";
		} else if (action.equals("/nwriteform.do")) {
			StudyAction study = new StudyAction();
			study.execute(req);
			ProfileSetAction psa = new ProfileSetAction();
			psa.execute(req, resp);
			path = "/khtalk/nwrite.jsp";

		} else if (action.equals("/nwrite.do")) {
			WriteAction write = new WriteAction();
			write.execute(req);

			resp.sendRedirect("notice.do");
		} else if (action.equals("/ndelete.do")) {
			deleteAction delete = new deleteAction();
			delete.execute(req);

			resp.sendRedirect("notice.do");
		} else if (action.equals("/nupdateForm.do")) {
			updateFormAction update = new updateFormAction();
			update.execute(req);
			ProfileSetAction psa = new ProfileSetAction();
			psa.execute(req, resp);
			StudyAction study = new StudyAction();
			study.execute(req);
			path = "/khtalk/nupdate.jsp";
		} else if (action.equals("/nupdate.do")) {
			updateAction up = new updateAction();
			up.execute(req);

			resp.sendRedirect("notice.do");
		}

		// -----------------------스터디방 --------------------------------

		else if (action.equals("/studyList")) {
			StudyAction study = new StudyAction();
			study.execute(req);
			ProfileSetAction psa = new ProfileSetAction();
			psa.execute(req, resp);
			path = "/khtalk/groups.jsp";
		} else if (action.equals("/StudyDetailView.do")) {
			StudyDetailViewAction view = new StudyDetailViewAction();
			view.execute(req);
			StudyAction study = new StudyAction();
			study.execute(req);
			ProfileSetAction psa = new ProfileSetAction();
			psa.execute(req, resp);
			path = "/khtalk/room.jsp";
		} else if (action.equals("/StudyAddForm.do")) {
			StudyAction study = new StudyAction();
			study.execute(req);
			ProfileSetAction psa = new ProfileSetAction();
			psa.execute(req, resp);
			path = "/khtalk/StudyAdd.jsp";
		} else if (action.equals("/StudyAdd.do")) {
			StudyAddAction write = new StudyAddAction();
			MultipartRequest multi = write.executeMulti(req);
			String param = "pageNum=" + multi.getParameter("pageNum");
			param += "&searchKey=" + multi.getParameter("searchKey");
			param += "&searchWord=" + multi.getParameter("searchWord");
			resp.sendRedirect("studyList?" + param);
		} else if (action.equals("/StudyDelete.do")) {
			StudyDelAction delete = new StudyDelAction();
			delete.execute(req);

			String param = "pageNum=" + req.getParameter("pageNum");
			param += "&searchKey=" + req.getParameter("searchKey");
			param += "&searchWord=" + req.getParameter("searchWord");

			resp.sendRedirect("studyList?" + param);
		} else if (action.equals("/StudyUpdateForm.do")) {
			StudyAction study = new StudyAction();
			study.execute(req);
			StudyUpdateFormAction update = new StudyUpdateFormAction();
			update.execute(req);
			ProfileSetAction psa = new ProfileSetAction();
			psa.execute(req, resp);
			path = "/khtalk/StudySetting.jsp";
		} else if (action.equals("/StudyUpdatePro.do")) {
			StudyAction study = new StudyAction();
			study.execute(req);
			StudyUpdateProAction updatePro = new StudyUpdateProAction();
			MultipartRequest multi = updatePro.executeMulti(req);
			String param = "pageNum=" + multi.getParameter("pageNum");
			param += "&searchKey=" + multi.getParameter("searchKey");
			param += "&searchWord=" + multi.getParameter("searchWord");
			resp.sendRedirect("studyList?" + param);

		} else if (action.equals("/StudyJoin.do")) {
			StudyAction study = new StudyAction();
			study.execute(req);
			StudyJoinAction join = new StudyJoinAction();
			join.execute(req);
			req.getAttribute("studyid");
			resp.sendRedirect("StudyDetailView.do?studyid=" + req.getAttribute("studyid"));

		} else if (action.equals("/StudyDeleteRoom.do")) {
			StudyAction study = new StudyAction();
			study.execute(req);
			StudyDeleteAction sda = new StudyDeleteAction();
			sda.execute(req);
			req.getAttribute("studyid");
			resp.sendRedirect("StudyDetailView.do?studyid=" + req.getAttribute("studyid"));

		} else if (action.equals("/checkRoom.do")) {
			StudyAction study = new StudyAction();
			study.execute(req);
			CheckRoomAction cra = new CheckRoomAction();
			cra.execute(req);
			path = "/khtalk/room.jsp";
		}

//-----------------------------뎃글
		else if (action.equals("/chatcomm")) {
			StudyAction study = new StudyAction();
			study.execute(req);
			AddCommAction aca = new AddCommAction();
			aca.execute(req);
			req.getAttribute("studyid");
			resp.sendRedirect("StudyDetailView.do?studyid=" + req.getAttribute("studyid"));
		} else if (action.equals("/deleteComm")) {
			StudyAction study = new StudyAction();
			study.execute(req);
			DeleteCommAction dca = new DeleteCommAction();
			dca.execute(req);
			req.getAttribute("studyid");
			resp.sendRedirect("StudyDetailView.do?studyid=" + req.getAttribute("studyid"));
		}

		if (path != "") {
			RequestDispatcher dis = req.getRequestDispatcher(path);
			dis.forward(req, resp);
		}

	}// end doProcess

}// end MainController class

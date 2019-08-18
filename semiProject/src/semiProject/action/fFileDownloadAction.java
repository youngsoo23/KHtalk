package semiProject.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semiProject.model.freeDAO;

public class fFileDownloadAction implements BoardActionImp {
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		freeDAO dao = freeDAO.getInstance();
		//파일 다운로드 처리
		String filename=req.getParameter("filename");
		
		String convName;
		try {
			convName = URLEncoder.encode(filename,"UTF-8");
			convName=convName.replace("+", " ");
			
			resp.setContentType("application/octet-stream");
			
			resp.setHeader("Content-Disposition", "attachment;filename="+convName+";");
			
			File file = new File("C:/temp/",filename);
			
			FileInputStream is = new FileInputStream(file);
			BufferedInputStream bs = new BufferedInputStream(is);
			
			BufferedOutputStream bo = new BufferedOutputStream(resp.getOutputStream());
			
			byte buffer[] = new byte[1024];
			int len=0;
			while((len=bs.read(buffer))>0) {
				bo.write(buffer,0,len);
				bo.flush();
			}
			
			bs.close();
			is.close();
			bo.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void execute(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
	}

		
	}//end class


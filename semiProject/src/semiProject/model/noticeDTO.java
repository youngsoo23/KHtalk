package semiProject.model;

import java.util.Date;

public class noticeDTO {
	private int num, readcount;
	private String subject, userId, content, upload;
	private Date reg_date;
	
	public noticeDTO() {
		
	}
	
	
	public String getUpload() {
		return upload;
	}


	public void setUpload(String upload) {
		this.upload = upload;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	
	
}

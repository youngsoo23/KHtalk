package semiProject.model;

import java.sql.SQLException;

public class UserDTO {
	
	private int userNum;
	private String userId;
	private String userName;
	private String userPassword;
	private String email;
	private String content;
	private String mypicture;
	private int studyid;
	
	
	public int getStudyid() {
		return studyid;
	}
	public void setStudyid(int studyid) {
		this.studyid = studyid;
	}
	public String getMypicture() {
		return mypicture;
	}
	public void setMypicture(String mypicture) {
		this.mypicture = mypicture;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}//end class

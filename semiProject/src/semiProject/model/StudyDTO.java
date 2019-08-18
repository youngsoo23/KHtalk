package semiProject.model;

import java.util.Date;

public class StudyDTO {

		private int num, readcount, studyid;
		private String userId, subject, semicontent, content, main_photo;
		private Date reg_date;
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
		public int getStudyid() {
			return studyid;
		}
		public void setStudyid(int studyid) {
			this.studyid = studyid;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getSemicontent() {
			return semicontent;
		}
		public void setSemicontent(String semicontent) {
			this.semicontent = semicontent;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getMain_photo() {
			return main_photo;
		}
		public void setMain_photo(String main_photo) {
			this.main_photo = main_photo;
		}
		public Date getReg_date() {
			return reg_date;
		}
		public void setReg_date(Date reg_date) {
			this.reg_date = reg_date;
		}
		
	} // end class

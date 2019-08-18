package semiProject.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudyDAO {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private StudyDAO() {

	}

	private static StudyDAO dao = new StudyDAO();

	public static StudyDAO getInstance() {
		return dao;
	}

	private Connection init() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");

		String url = "jdbc:oracle:thin://@localhost:1521:xe";
		String user = "hr";
		String password = "a1234";
		return DriverManager.getConnection(url, user, password);
	} // end init()

	private void exit() throws SQLException {
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	} // end exit()

	// 즐겨찾기 수 증가
	public void readCountMethod(int num) {
		try {
			conn = init();
			String sql = "update study set readcount = readcount+1 where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	} // end readCountMethod() /////////////////
	
	public List<StudyDTO> listMethod(PageDTO pdto) {
		List<StudyDTO> aList = new ArrayList<StudyDTO>();
		
		try {
			conn = init();
					String 	sql = "select b.* ";
					sql += "from (select rownum as rm, a.* ";
					sql += "from (select * from study ";
					if(pdto.getSearchKey()!=null) {		
						String data = pdto.getSearchKey();
						if(data.equals("subject") || data.equals("semicontent") || data.equals("userId")) {
							sql += "where studyid in (select studyid from study ";
							sql	+= "where lower("+pdto.getSearchKey()+") ";
							sql	+= "like lower('%"+pdto.getSearchWord()+"%')) ";
						}
					}
					sql += "order by studyid desc)a)b ";
					sql += "where b.rm >=? and b.rm<=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pdto.getStartRow());
			pstmt.setInt(2, pdto.getEndRow());
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				StudyDTO dto = new StudyDTO();
				dto.setNum(rs.getInt("num"));
				dto.setSubject(rs.getString("subject"));
				dto.setUserId(rs.getString("userId"));
				dto.setReg_date(rs.getDate("reg_date"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setSemicontent(rs.getString("semicontent"));
				dto.setSemicontent(rs.getString("content"));
				dto.setMain_photo(rs.getString("main_photo"));

				aList.add(dto);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return aList;
		
	} // end listMethod() ///////////////////

	public void insertMethod(StudyDTO dto) {
		try {
			conn = init();

			String sql = "insert into study(num, studyid, userId, subject, reg_date, "
					+ "semicontent, content, main_photo) "
					+ "values (study_num_seq.nextval, study_num_seq.nextval, ?, ?, sysdate, ?, ?, ?)"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getSemicontent());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getMain_photo());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	} // end insertMethod() ///////////////////////////////////

	public int rowTotalCount(HashMap<String, String> map) {
		int cnt = 0;
		
		try {
			conn = init();
			String sql = "select count(*) from study";
			
			if(map.get("searchKey") != null) {
				String data = map.get("searchKey");
				if(data.equals("subject") || data.equals("content") || data.equals("userId")) {
					sql += " where studyid in(select studyid from study where ";
					if(map.get("searchKey").equals("subject")) {
						sql += "lower(subject) like ?)";
					} else if(map.get("searchKey").equals("content")) {
						sql += "lower(content) like ?)";
					} else if(map.get("searchKey").equals("userId")) {
						sql += "lower(userId) like ?)";
					}
				}
			}
			
			pstmt = conn.prepareStatement(sql);
			
			if(map.get("searchKey")!=null) {
				String data = map.get("searchKey");
				if(data.equals("subject") || data.equals("content") || data.equals("userId")) {
					pstmt.setString(1, "%"+map.get("searchWord").toLowerCase()+"%");
				}
			}
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	} // end rowTotalCount() /////////////////////////////////////
	
	public StudyDTO viewMethod(int num) {
		StudyDTO dto = null;
		
		try {
			conn = init();
			String sql = "select * from study where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new StudyDTO();
				dto.setNum(rs.getInt("num"));
				dto.setUserId(rs.getString("userId"));
				dto.setSubject(rs.getString("subject"));
				dto.setSemicontent(rs.getString("semicontent"));
				dto.setReg_date(rs.getDate("reg_date"));
				dto.setContent(rs.getString("content"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setMain_photo(rs.getString("main_photo"));
				dto.setStudyid(rs.getInt("studyid"));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return dto;
	} // end viewMethod() /////////////////////////////////////
	
	public void deleteMethod(int num) {
		try {
			conn = init();
			String sql = "delete from study where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
	} // end deleteMethod() ///////////////////////////////////
	
	public String fileMethod(int num) {
		String fileName = null;
		try {
			conn = init();
			String sql = "select main_photo from study where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				fileName = rs.getString("main_photo");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return fileName;
	} // end fileMethod() ///////////////////////////////////
	
	public void updateMethod(StudyDTO dto) {
		try {
			conn = init();
			String sql = "update study set subject = ?, semicontent=?, content=?, main_photo=? where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getSemicontent());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getMain_photo());
			pstmt.setInt(5, dto.getNum());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	} // end updateMethod() /////////////////////////////////
	
	public int searchRoom(String userId) {
		int cnt = 0;
		try {
			conn=init();
			String sql="select studyid from room where userId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cnt = 1;
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				exit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cnt;
	}
public void joinRoom(RoomDTO dto) throws ClassNotFoundException {
		
		try {
			conn=init();
			String sql ="insert into room values(room_num_seq.nextval,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserId());
			pstmt.setInt(2,dto.getStudyid());
			pstmt.executeUpdate();		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
public void deleteRoom(RoomDTO dto) {
	try {
		conn = init();
		String sql = "delete from room where userId = ? and studyid=? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, dto.getUserId());
		pstmt.setInt(2, dto.getStudyid());
		pstmt.execute();
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			exit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}// end deleteRoom

public int checkRoom(String userId, int studyid) {
	int cnt=0;
	try {
		conn=init();
		String sql ="select studyid from room where userId=? and studyid=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userId);
		pstmt.setInt(2,studyid);
		rs=pstmt.executeQuery();
		if(rs.next()) {
			cnt=1;
		}
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			exit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return cnt;
}

public int getStudyId (String userId) {
	int cnt=0;
	try {
		conn =init();
		String sql = "select max(studyid) from study where userId=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userId);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
			
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			exit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return cnt;
}//end getStudyId

public List<StudyDTO> listMethod(SpageDTO pdto) {
	List<StudyDTO> aList = new ArrayList<StudyDTO>();
	
	try {
		conn = init();
				String 	sql = "select b.* ";
				sql += "from (select rownum as rm, a.* ";
				sql += "from (select * from study ";
				if(pdto.getSearchKey()!=null) {		
					String data = pdto.getSearchKey();
					if(data.equals("subject") || data.equals("semicontent") || data.equals("userId")) {
						sql += "where studyid in (select studyid from study ";
						sql	+= "where lower("+pdto.getSearchKey()+") ";
						sql	+= "like lower('%"+pdto.getSearchWord()+"%')) ";
					}
				}
				sql += "order by studyid desc)a)b ";
				sql += "where b.rm >=? and b.rm<=?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pdto.getStartRow());
		pstmt.setInt(2, pdto.getEndRow());
		
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			StudyDTO dto = new StudyDTO();
			dto.setNum(rs.getInt("num"));
			dto.setSubject(rs.getString("subject"));
			dto.setUserId(rs.getString("userId"));
			dto.setReg_date(rs.getDate("reg_date"));
			dto.setReadcount(rs.getInt("readcount"));
			dto.setSemicontent(rs.getString("semicontent"));
			dto.setSemicontent(rs.getString("content"));
			dto.setMain_photo(rs.getString("main_photo"));

			aList.add(dto);
		}
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			exit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	return aList;
	
}

public List<StudyDTO> userListMethod(String userId) {
List<StudyDTO> aList = new ArrayList<StudyDTO>();
	
	try {
		conn = init();
		String sql="select distinct s.num, s.studyid, s.userid, s.subject, s.reg_date, s.readcount, s.main_photo "
				+ "from study s, room r "
				+ "where r.studyid=s.studyid and r.userid=? order by studyid desc";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userId);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			StudyDTO dto = new StudyDTO();
			dto.setNum(rs.getInt("num"));
			dto.setStudyid(rs.getInt("studyid"));
			dto.setUserId(rs.getString("userid"));
			dto.setSubject(rs.getString("subject"));
			dto.setReg_date(rs.getDate("reg_date"));
			dto.setReadcount(rs.getInt("readcount"));
			dto.setMain_photo(rs.getString("main_photo"));
			aList.add(dto);
		}
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			exit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	return aList;
}

}

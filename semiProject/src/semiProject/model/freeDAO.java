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

public class freeDAO {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public freeDAO() {
	}
	
	private static freeDAO dao = new freeDAO();
	
	public static freeDAO getInstance() {
		return dao;
	}

	private Connection init() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		String url = "jdbc:oracle:thin://@localhost:1521:xe";
		String user = "hr";
		String password = "a1234";
		return DriverManager.getConnection(url, user, password);
	}	// end init()

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
	
	public List<freeDTO> listMethod(fPageDTO pdto) {
		List<freeDTO> fList = new ArrayList<freeDTO>();
		
		try {
			conn=init();
			String sql = "select b.* from "; 
				sql+="(select rownum as rn, a.* from ";
				sql+="(select * from freeboard ";
				
				if(pdto.getSearchKey()!=null) {
					String data=pdto.getSearchKey();
					if(data.equals("subject")||data.equals("content")||data.contentEquals("userId")) {
						

							sql += "where ref in(select ref from freeboard ";
							sql += "where re_level=0 ";
							sql += "and lower(" + pdto.getSearchKey() + ") ";
							sql += "like lower('%" + pdto.getSearchWord() + "%')) ";

					}
				}
				
				sql+="order by ref desc, re_step asc)a)b ";
				sql+="where b.rn>=? and b.rn<=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pdto.getStartRow());
			pstmt.setInt(2, pdto.getEndRow());
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				freeDTO dto = new freeDTO(); 
				dto.setNum(rs.getInt("num"));
				dto.setSubject(rs.getString("subject"));
				dto.setUserId(rs.getString("userId"));
				dto.setReg_date(rs.getDate("reg_date"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setUpload(rs.getString("upload"));
				dto.setRe_level(rs.getInt("re_level"));
				
				fList.add(dto);
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
		
		
		return fList;
	}//end listMethod()/////////////////
	
	public int TotalCount(HashMap<String, String> map) {
		int cnt = 0 ;
		freeDTO dto = new freeDTO();
		try {
			conn=init();
			String sql = "select count(*) from freeboard ";
			if(map.get("searchKey")!=null) {
				String data=map.get("searchKey");
				if(data.equals("subject") || data.equals("content") || data.equals("userId")) {
					 

						sql+="where ref in(select ref from freeboard "; 
						sql+="where re_level=0 and ";

					 
					if (map.get("searchKey").equals("subject"))
						sql += "lower(subject) like ? ";
					else if (map.get("searchKey").equals("content"))
						sql += "lower(content) like ? ";
					else if (map.get("searchKey").equals("userId"))
						sql += "lower(userId) like ? ";
					 sql+=")";
					}
			}
			pstmt = conn.prepareStatement(sql);
			if (map.get("searchKey") != null) {
			String data=map.get("searchKey");
			if(data.equals("subject") || data.equals("content") || data.equals("userId"))
							pstmt.setString(1, "%" + map.get("searchWord").toLowerCase() + "%");
			}
			rs=pstmt.executeQuery();
			if(rs.next())
				cnt=rs.getInt(1);
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
	}//end TotalCount()
	
	// 조회수 증가
	public void readCountMethod(int num) {
		try {
			conn = init();
			String sql = "update freeboard set readcount=readcount+1" + " where num=?";
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
	}// end readCountMethod()/////////////////////

	// 게시물 view
	public freeDTO viewMethod(int num) {
		freeDTO dto = null;
		try {
			conn = init();
			String sql = "select * from freeboard where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new freeDTO();
				dto.setNum(rs.getInt("num"));
				dto.setUserId(rs.getString("userId"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setReg_date(rs.getDate("reg_date"));
				dto.setUpload(rs.getString("upload"));
				dto.setRef(rs.getInt("ref"));
				dto.setRe_step(rs.getInt("re_step"));
				dto.setRe_level(rs.getInt("re_level"));
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
	}// end viewMethod()///////////////////
	
	public void reStepMethod(HashMap<String, Integer> map) {
		try {
			conn=init();
			String sql="update freeboard set re_step=re_step+1 where ref=? and re_step>?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, map.get("ref"));
			pstmt.setInt(2, map.get("re_step"));
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
	}//end reStepMethod()//////////////////
	
	public void insertMethod(freeDTO dto) {
		try {
			conn=init();
			if(dto.getRe_level()==0) {
				//제목글
				String sql = "insert into freeboard(num,userId,subject,"
						+ "reg_date,ref,re_step,re_level,content,ip,upload) "
						+ "values(freeboard_num_seq.nextval,?,?,sysdate,freeboard_num_seq.nextval,0,0,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getUserId());
				pstmt.setString(2, dto.getSubject());
				pstmt.setString(3, dto.getContent());
				pstmt.setString(4, dto.getIp());
				pstmt.setString(5, dto.getUpload());
			}else {
				//답변글
		      String sql = "insert into freeboard(num,userId,subject,"
		      		+ "reg_date,ref,re_step,re_level,content,ip,upload) "
		      		+ "values(freeboard_num_seq.nextval,?,?,sysdate,?,?,?,?,?,?)";
		      pstmt = conn.prepareStatement(sql);
		      pstmt.setString(1, dto.getUserId());
		      pstmt.setString(2, dto.getSubject());
		      pstmt.setInt(3, dto.getRef());
		      pstmt.setInt(4, dto.getRe_step());
		      pstmt.setInt(5, dto.getRe_level());
		      pstmt.setString(6, dto.getContent());
		      pstmt.setString(7, dto.getIp());
		      pstmt.setString(8, dto.getUpload());
			}
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
		
	}//end insertMethod()////////////////
	
	public String fileMethod(int num) {
		String fileName = null;
		
		try {
			conn=init();
			String sql = "select upload from freeboard where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next())
				fileName = rs.getString("upload");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return fileName;
	}//end fileMethod()
	
	public void updateMethod(freeDTO dto) {
		try {
			conn=init();
			String sql = "update freeboard set subject=?, content=?, upload=? where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getUpload());
			pstmt.setInt(4, dto.getNum());
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
		
	}//end updateMethod()////////////////////
	
	public void deleteMethod(int num) {
		try {
			conn=init();
			String sql = "delete from freeboard where num=?";
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
	}//end deleteMethod()//////////////////////
	
	
	/*
	public List<freeDTO> selectNum(int usernum){
		List<freeDTO> fList = new ArrayList<freeDTO>();
		
		try {
			conn=init();
			String sql = "select num from freeboard where usernum=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, usernum);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				freeDTO dto = new freeDTO();
				dto.setNum(rs.getInt(1));
				fList.add(dto);
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
		return fList;
	}//end selectNum
	
	public int searchUser(String userId) {
		
		int user = 0;
		
		try {
			conn=init();
			String sql = "select usernum from userTable where userid=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				user=rs.getInt(1);
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
		
		return user;
	}//end searchUser()////////////////////
	*/
}//end class

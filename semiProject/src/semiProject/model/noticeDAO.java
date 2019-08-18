package semiProject.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class noticeDAO {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private noticeDAO() {

	}

	private static noticeDAO dao = new noticeDAO();
	
	public static noticeDAO getInstance() {
		return dao;
	}
	private Connection init() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");

		String url = "jdbc:oracle:thin://@127.0.0.1:1521:xe";
		String user = "hr";
		String password = "a1234";

		return DriverManager.getConnection(url, user, password);
	}
	
	

	private void exit() throws SQLException {
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}// end exit

	
	public List<noticeDTO> listMethod(PageDTO pdto) {
		List<noticeDTO> nList = new ArrayList<noticeDTO>();
		try {
			conn = init();
			String sql = "select b.* ";
			sql += "from(select rownum as rm, a.* ";
			sql += "from(select *from notice ";
			sql += " order by num desc)a)b ";
			sql += "where b.rm>=? and b.rm<=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pdto.getStartRow());
			pstmt.setInt(2, pdto.getEndRow());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				noticeDTO dto = new noticeDTO();
				dto.setNum(rs.getInt("num"));
				dto.setUserId(rs.getString("userId"));
				dto.setSubject(rs.getString("subject"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setReg_date(rs.getDate("reg_date"));
				nList.add(dto);///////////////////////////////////////////////// import!
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
		return nList;
	}// end list Method()
	
	// 조회수 증가
	public void readCountMethod(int num) {
		try {
			conn = init();
			String sql = "UPDATE notice SET readcount=readcount+1 where num=?";
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

	}// end readCountMethod
	
	public noticeDTO viewMethod(int num) {
		noticeDTO dto = null;

		try {
			conn = init();

			String sql = "select*from notice where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new noticeDTO();
				dto.setNum(rs.getInt("num"));
				dto.setUserId(rs.getString("userId"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setReg_date(rs.getDate("reg_date"));
				dto.setReadcount(rs.getInt("readcount"));
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
	}// end viewMethod()//
	
	public void writeMethod(noticeDTO dto) {
		try {
			conn = init();
			String sql = "update notice set subject=?, ";
			sql += "content=? where num=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNum());
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
	}// end updateMethod()////////////////////////////////
	
	
	public void insertMethod(noticeDTO dto) {
		try {
			conn=init();
			String sql = "insert into notice(num, userId, subject, reg_date, content) values(notice_num_seq.nextval, ?, ?, sysdate, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}//end insertMethod.//////////////////
	
	
	
	public void deleteMethod(int num) {
		
		try {
			conn = init();
			String sql = "delete from notice where num=?";
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
	}//end delete
	
	
	public void updateMethod(noticeDTO dto) {
		try {
			conn = init();
			String sql = "update notice set subject=?,";
			sql += " content=? where num=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNum());
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
	}// end updateMethod()////////////////////////////////
	
	
	
	public int rowTotalCount() {
		int cnt = 0;

		try {
			conn = init();
			String sql = "select count(*) from notice";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				cnt = rs.getInt(1);

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
	
	}//end rowTotalCount
	
}//end class

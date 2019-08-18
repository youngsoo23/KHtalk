package semiProject.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChatDAO {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private ChatDAO() {

	}

	private static ChatDAO dao = new ChatDAO();

	public static ChatDAO getInstance() {
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

	public List<ChatDTO> viewChat(int studyId) {
		
		List<ChatDTO> aList = new ArrayList<ChatDTO>();
		try {
			conn=init();
			String sql="select * from Chat where studyid=? order by chatnum desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, studyId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ChatDTO dto = new ChatDTO();
				dto.setChatNum(rs.getInt("chatnum"));
				dto.setStudyId(rs.getInt("studyid"));
				dto.setUserId(rs.getString("userid"));
				dto.setToday(rs.getDate("today"));
				dto.setContent(rs.getString("content"));
				aList.add(dto);
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
		return aList;
	}//end viewChat
	
	public void insertChat(int studyid, String userId, String content) {
		try {
			conn=init();
			String sql = "insert into chat values(chat_num_seq.nextval,?,?,sysdate,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, studyid);
			pstmt.setString(2, userId);
			pstmt.setString(3, content);
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
		
	}//end insertChat
	
	public void deleteChat(int chatNum) {
		try {
			conn=init();
			String sql ="delete from chat where chatNum=?";
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, chatNum);
			pstmt.executeUpdate();
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
	}
}//end class

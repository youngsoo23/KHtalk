package semiProject.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAO {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public UserDAO() {
	}

	private static UserDAO dao = new UserDAO();

	public static UserDAO getInstance() {
		return dao;
	}

	private Connection init() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		String url = "jdbc:oracle:thin://@localhost:1521:xe";
		String username = "hr";
		String password = "a1234";
		return DriverManager.getConnection(url, username, password);
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
	}

	public void insertUser(UserDTO dto) throws ClassNotFoundException {
		try {
			conn = init();
			String sql = "insert into userTable(usernum,userid,username,userpassword,useremail) values(user_num_seq.nextval ,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getUserName());
			pstmt.setString(3, dto.getUserPassword());
			pstmt.setString(4, dto.getEmail());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}// end insertUser()

	public int userCheck(UserDTO dto) throws ClassNotFoundException {
		int cnt = 0;
		try {
			conn = init();
			String sql = "select count(*) from userTable where userid= ? and userpassword= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getUserPassword());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cnt;
	}

	public int idCheck(String userId) throws ClassNotFoundException {
		int cnt = 0;
		try {
			conn = init();
			String sql = "select count(*) from userTable where userid= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cnt;
	}

	public String getUserName(UserDTO dto) throws ClassNotFoundException {
		String userName = "";

		try {
			conn = init();
			String sql = "select username from userTable where userId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				userName = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userName;
	}

	public String getUserEmail(UserDTO dto) throws ClassNotFoundException {
		String userEmail = "";

		try {
			conn = init();
			String sql = "select useremail from userTable where userId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				userEmail = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userEmail;
	}

	public int getUserNum(String userId) throws ClassNotFoundException {
		int userNum = 0;

		try {
			conn = init();
			String sql = "select usernum from userTable where userid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				userNum = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userNum;
	}

	public void updateContent(UserDTO dto) throws ClassNotFoundException {
		try {
			conn = init();
			String sql = "update userTable set content=? where userId =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getContent());
			pstmt.setString(2, dto.getUserId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}// end updateContent

	public String getContent(String userId) throws ClassNotFoundException {
		String content = "";
		try {
			conn = init();
			String sql = "select content from userTable where userid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				content = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return content;
	}

	public void updatePicture(String filepath, String userId) throws ClassNotFoundException {
		try {
			conn = init();
			String sql = "update userTable set mypicture=? where userId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, filepath);
			pstmt.setString(2, userId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}// end updatePicture

	public String showUserPicture(String userId) throws ClassNotFoundException {
		String picture = "";
		try {
			conn = init();
			String sql = "select mypicture from userTable where userId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				picture = rs.getString("mypicture");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return picture;
	}

	public int getUserStudyid(UserDTO dto) throws ClassNotFoundException {
		int userStudyid = 0;

		try {
			conn = init();
			String sql = "select studyid from room where userid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				userStudyid = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userStudyid;
	}

	public void deleteAccount(String userId) {
		try {
			conn = init();
			String sql ="delete from userTable where userId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
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
		
	}//end deleteAccount



}// end class

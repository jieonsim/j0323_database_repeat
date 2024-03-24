package crud1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAO {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;

	String sql;

	public UserDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/javaclass";
			String user = "atom";
			String password = "1234";
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("데이터베이스 연동 실패" + e.getMessage());
		}
	}

	public void connClose() {
		try {
			conn.close();
		} catch (SQLException e) {
		}
	}

	public void stmtClose() {
		try {
			if (stmt != null) {
				stmt.close();
			}
			stmt.close();
		} catch (SQLException e) {
		}
	}

	public void rsClose() {
		try {
			if (rs != null) {
				rs.close();
			}
			stmtClose();
		} catch (SQLException e) {
		}
	}

	public void setSignUp(UserVO vo) {
		try {
			stmt = conn.createStatement();
			sql = "insert into User values (default, '" + vo.getName() + "', " + vo.getAge() + ", '" + vo.getGender()
					+ "', '" + vo.getAddress() + "')";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			stmtClose();
		}
	}

	public ArrayList<UserVO> getFullList() {
		ArrayList<UserVO> vos = new ArrayList<UserVO>();

		try {
			stmt = conn.createStatement();
			sql = "select * from User";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				UserVO vo = new UserVO();

				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));

				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	public UserVO getSearch(String name) {
		UserVO vo = new UserVO();

		try {
			stmt = conn.createStatement();
			sql = "select * from User where name = '" + name + "'";
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

	public int setUpdate(UserVO vo) {
		int result = 0;

		try {
			stmt = conn.createStatement();
			sql = "update User set name = '" + vo.getName() + "', age = " + vo.getAge() + ", gender = '"
					+ vo.getGender() + "', address = '" + vo.getAddress() + "' where idx = " + vo.getIdx();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			stmtClose();
		}
		return result;
	}

	public void setDelete(String name) {
		try {
			stmt = conn.createStatement();
			sql = "delete from User where name = '" + name + "'";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			stmtClose();
		}
	}
}

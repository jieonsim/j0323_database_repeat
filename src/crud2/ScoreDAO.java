package crud2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScoreDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private String sql;

	public ScoreDAO() {
		String url = "jdbc:mysql://localhost:3306/javaclass";
		String user = "atom";
		String password = "1234";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패 " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("데이터 베이스 연동 실패 " + e.getMessage());
		}
	}

	public void connClose() {
		try {
			conn.close();
		} catch (SQLException e) {
		}
	}

	public void pstmtClose() {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
		}
	}

	public void rsClose() {
		try {
			if (rs != null) {
				rs.close();
			}
			pstmtClose();
		} catch (SQLException e) {
		}
	}

	public int setInput(ScoreVO vo) {
		int result = 0;
		try {
			sql = "insert into score values (default, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getKorean());
			pstmt.setInt(3, vo.getEnglish());
			pstmt.setInt(4, vo.getMath());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return result;
	}

	public ScoreVO getSearch(String name) {
		ScoreVO vo = new ScoreVO();
		try {
			sql = "select * from score where name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setKorean(rs.getInt("korean"));
				vo.setEnglish(rs.getInt("english"));
				vo.setMath(rs.getInt("math"));
			} else {
				vo = null;
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 " + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

	public ArrayList<ScoreVO> getList() {
		ArrayList<ScoreVO> vos = new ArrayList<ScoreVO>();

		try {
			sql = "select * from score order by name";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ScoreVO vo = new ScoreVO();

				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setKorean(rs.getInt("korean"));
				vo.setEnglish(rs.getInt("english"));
				vo.setMath(rs.getInt("math"));

				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	public int setUpdate(ScoreVO vo) {
		int result = 0;

		try {
			sql = "update score set name = ?, korean = ?, english = ?, math = ? where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getKorean());
			pstmt.setInt(3, vo.getEnglish());
			pstmt.setInt(4, vo.getMath());
			pstmt.setInt(5, vo.getIdx());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return result;
	}

	public int setDelete(int idx) {
		int result = 0;

		try {
			sql = "delete from score where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return result;
	}
}

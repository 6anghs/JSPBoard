package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.JdbcUtil;

public class BoardDao {
	private JdbcUtil ju;
	
	public BoardDao() {
		ju = JdbcUtil.getInstance();
	}
	
	// 삽입 (C)
	public int insert(BoardDto dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO \"BOARD\" (\"NUM\", \"TITLE\", \"WRITER\", \"CONTENT\", \"REGDATE\", \"CNT\") VALUES (\"BOARD_SEQ\".NEXTVAL, ?, ?, ?, sysdate, 0)";
		int ret = -1;
		
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getContent());
			ret = pstmt.executeUpdate(); // 한 행 삽입
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close(); // 풀에 반환
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return ret;
	}
	
	// 전체 조회 (R)
	public List<BoardDto> selectAll() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "SELECT \"NUM\", \"TITLE\", \"WRITER\", \"CONTENT\", \"REGDATE\", \"CNT\" FROM \"BOARD\" ORDER BY \"NUM\" DESC";
		ArrayList<BoardDto> ls = new ArrayList<>();
		
		try {
			con = ju.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				BoardDto dto = new BoardDto(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					new Date(rs.getDate(5).getTime()),
					rs.getInt(6));
				
				ls.add(dto);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return ls;
	}
	
	// 부분 조회 (R)
	public BoardDto selectOne(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT \"NUM\", \"TITLE\", \"WRITER\", \"CONTENT\", \"REGDATE\", \"CNT\" FROM \"BOARD\" WHERE \"NUM\"=?";
		BoardDto dto = null;
		
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				updateCnt(num); // 조회수 증가
				dto = new BoardDto(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					new Date(rs.getDate(5).getTime()),
					rs.getInt(6) + 1);
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return dto;
	}
	
	// 수정 (U)
	public int update(BoardDto dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "UPDATE \"BOARD\" SET \"TITLE\"=?, \"CONTENT\"=? WHERE \"NUM\"=?";
		int ret = -1;
		
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNum());
			ret = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return ret;
	}
	
	public int updateCnt(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "UPDATE \"BOARD\" SET \"CNT\"=\"CNT\"+1 WHERE \"NUM\"=?";
		int ret = -1;
		
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			ret = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return ret;
	}
	
	// 삭제 (D)
	public int delete(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "DELETE FROM \"BOARD\" WHERE \"NUM\"=?";
		int ret = -1;
		
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			ret = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return ret;
	}
}

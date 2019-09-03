package com.project.videos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.project.videos.vo.MainVO;
import com.project.videos.vo.V_ReplyVO;

public class MainDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static DataSource ds;
	private static MainDao instance = new MainDao();

	private MainDao() {

	}

	public static MainDao getInstance() {
		return instance;
	}

	static {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			// java:/comp/env/jdbc/oracle_test
			ds = (DataSource) envContext.lookup("jdbc/oracle_test");
		} catch (Exception e) {
			System.out.println("BoardDao static {}");
			e.printStackTrace();
		}
	}

	private Connection getConnection() {
		try {
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public ArrayList<MainVO> videoList(int startRow, int endRow) {
		String sql = "SELECT * FROM (SELECT ROWNUM num,"
				+ " v_no, v_uploader, v_like, v_views, v_title, v_videoloc, v_sumnailloc, v_hashtag,"
				+ " v_regdate, v_content, v_category FROM (SELECT * FROM videos ORDER BY v_no DESC))"
				+ " WHERE num >= ? AND num <= ?";
		System.out.println(sql);
		ArrayList<MainVO> videoList = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			videoList = new ArrayList<MainVO>();
			while (rs.next()) {
				MainVO main = new MainVO();
				main.setV_no(rs.getInt("v_no"));
				main.setV_like(rs.getInt("v_like"));
				main.setV_views(rs.getInt("v_views"));
				main.setV_uploader(rs.getString("v_uploader"));
				main.setV_title(rs.getString("v_title"));
				main.setV_videoloc(rs.getString("v_videoloc"));
				main.setV_sumnailloc(rs.getString("v_sumnailloc"));
				main.setV_hashtag(rs.getString("v_hashtag"));
				main.setV_regdate(rs.getString("v_regdate"));
				main.setV_content(rs.getString("v_content"));
				main.setV_category(rs.getString("v_category"));
				// v_category
				videoList.add(main);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
				System.out.println("Main SQLException");
			}
		}
		return videoList;
	}

	public int videoCount() {

		String sql = "SELECT COUNT(*) FROM videos";
		int count = 0;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
			}
		}
		return count;
	}

	public ArrayList<MainVO> videoLikeList(int startRow, int endRow) {
		// 목요일까지 인기 데이터 조회
		String sql = "SELECT * FROM (SELECT ROWNUM num, v_no, v_uploader, v_like, v_views, v_title, "
				+ "v_videoloc, v_sumnailloc, v_hashtag, v_regdate, v_content, v_category FROM "
				+ "(SELECT * FROM videos where v_regdate <= (select TRUNC(SYSDATE, 'IW')+3 from dual) "
				+ "ORDER BY videos.v_like DESC)) WHERE num >= ? AND num <= ? ";
		System.out.println(sql);
		ArrayList<MainVO> videoList = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			videoList = new ArrayList<MainVO>();
			while (rs.next()) {
				MainVO main = new MainVO();
				main.setV_no(rs.getInt("v_no"));
				main.setV_like(rs.getInt("v_like"));
				main.setV_views(rs.getInt("v_views"));
				main.setV_uploader(rs.getString("v_uploader"));
				main.setV_title(rs.getString("v_title"));
				main.setV_videoloc(rs.getString("v_videoloc"));
				main.setV_sumnailloc(rs.getString("v_sumnailloc"));
				main.setV_hashtag(rs.getString("v_hashtag"));
				main.setV_regdate(rs.getString("v_regdate"));
				main.setV_content(rs.getString("v_content"));
				main.setV_category(rs.getString("v_category"));
				videoList.add(main);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
				System.out.println("Main SQLException");
			}
		}
		return videoList;
	}

	public int videoCount(String keyword) {

		String sql = "SELECT COUNT(*) FROM videos WHERE v_title LIKE '%' || ? || '%' or v_uploader LIKE '%' || ? || '%'";
		int count = 0;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
			}
		}
		return count;
	}

	public ArrayList<MainVO> searchVideoList(String keyword, int startRow, int endRow) {
		String sql = "SELECT * FROM (SELECT ROWNUM num,"
				+ " v_no, v_uploader, v_like, v_views, v_title, v_videoloc, v_sumnailloc, v_hashtag,"
				+ " v_regdate, v_content, v_category FROM (SELECT * FROM videos WHERE v_title LIKE ? or v_uploader like ? "
				+ "ORDER BY v_no DESC)) WHERE num >= ? AND num <= ?";
		ArrayList<MainVO> videoList = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			rs = pstmt.executeQuery();
			videoList = new ArrayList<MainVO>();
			while (rs.next()) {
				MainVO main = new MainVO();
				main.setV_no(rs.getInt("v_no"));
				main.setV_like(rs.getInt("v_like"));
				main.setV_views(rs.getInt("v_views"));
				main.setV_uploader(rs.getString("v_uploader"));
				main.setV_title(rs.getString("v_title"));
				main.setV_videoloc(rs.getString("v_videoloc"));
				main.setV_sumnailloc(rs.getString("v_sumnailloc"));
				main.setV_hashtag(rs.getString("v_hashtag"));
				main.setV_regdate(rs.getString("v_regdate"));
				main.setV_content(rs.getString("v_content"));
				main.setV_category(rs.getString("v_category"));
				videoList.add(main);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
			}
		}
		return videoList;
	}

	public int insert(MainVO main) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;

		int res = 0;// parameter index=>
		String sql = "insert into videos values(video_seq.nextval, 0, 0, ?, ?, ?, ?, ?, sysdate, ?, ?)";
		// v_no v_like v_views v_uploader v_title v_videoloc v_sumnailloc v_hashtag v_regdate v_content v_category
		try {
			// 1.Connection 얻기
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, main.getV_uploader());
			pstmt.setString(2, main.getV_title());
			pstmt.setString(3, main.getV_videoloc());
			pstmt.setString(4, main.getV_sumnailloc());
			pstmt.setString(5, main.getV_hashtag());
			pstmt.setString(6, main.getV_content());
			pstmt.setString(7, main.getV_category());
			// res : 처리된 행수
			res = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	public int update(MainVO main) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;

		int res = 0;// parameter index=>
		String sql = "update videos set v_title=?, v_hashtag=?, v_content=?, v_category=? where v_no=?";

		try {
			// 1.Connection 얻기
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, main.getV_title());
			pstmt.setString(2, main.getV_hashtag());
			pstmt.setString(3, main.getV_content());
			pstmt.setString(4, main.getV_category());
			pstmt.setInt(5, main.getV_no());
			// res : 처리된 행수
			res = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	public MainVO selectOne(int v_no) {

		MainVO vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from videos where v_no=?";
		String updateSql = "update videos set v_views= v_views+1 where v_no=?";

		try {
			// 1.Connection 얻어오기
			conn = getConnection();
			// 2.SQL명령처리객체 얻어오기
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, v_no);

			// 3.명령수행후=>결과행 처리
			rs = pstmt.executeQuery();

			pstmt = conn.prepareStatement(updateSql);
			pstmt.setInt(1, v_no);
			pstmt.executeUpdate();

			if (rs.next()) {
				vo = new MainVO();
				vo.setV_no(rs.getInt("v_no"));
				vo.setV_like(rs.getInt("v_like"));
				vo.setV_views(rs.getInt("v_views"));
				vo.setV_uploader(rs.getString("v_uploader"));
				vo.setV_title(rs.getString("v_title"));
				vo.setV_sumnailloc(rs.getString("v_sumnailloc"));
				vo.setV_videoloc(rs.getString("v_videoloc"));
				vo.setV_hashtag(rs.getString("v_hashtag"));
				vo.setV_regdate(rs.getString("v_regdate"));
				vo.setV_content(rs.getString("v_content"));
				vo.setV_category(rs.getString("v_category"));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				// 닫기(열린역순)
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vo;
	}

	public int recUpdate(int v_no) {
		// TODO Auto-generated method stub
		int result = 0;
		String updateSql = "update videos set v_like=v_like+1 where v_no=?";
		String selectSql = "SELECT v_like FROM videos where v_no=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(updateSql);
			pstmt.setInt(1, v_no);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(selectSql);
			pstmt.setInt(1, v_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int delete(int v_no) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;

		int res = 0;// parameter index=>
		String sql = "delete from videos where v_no=?";

		try {
			// 1.Connection 얻기
			conn = getConnection();
			// 2.SQL처리객체 얻기
			pstmt = conn.prepareStatement(sql);
			// 3.pstmt parameter 셋팅
			pstmt.setInt(1, v_no);
			// 4.DB DML명령
			// res : 처리된 행수
			res = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	// 특정 게시글에 해당하는 댓글 리스트를 반환하는 메소드
	public ArrayList<V_ReplyVO> getReplyList(int v_no) {

		String replyListSql = "SELECT * FROM v_reply WHERE v_no = ?" + " ORDER BY vr_no DESC";

		V_ReplyVO reply = null;
		ArrayList<V_ReplyVO> replyList = null;

		try {
			// DBManager을 이용해 DBCP로 부터 Connection을 대여한다.
			conn = getConnection();

			// Comment 테이블에 저장된 댓글 번호중 제일 큰 댓글 번호를 읽어온다.
			pstmt = conn.prepareStatement(replyListSql);
			pstmt.setInt(1, v_no);
			rs = pstmt.executeQuery();

			replyList = new ArrayList<V_ReplyVO>();

			while (rs.next()) {
				reply = new V_ReplyVO();
				reply.setVr_no(rs.getInt("vr_no"));
				reply.setV_no(rs.getInt("v_no"));
				reply.setVr_reply(rs.getString("vr_reply"));
				reply.setVr_writer(rs.getString("vr_writer"));
				reply.setVr_regdate(rs.getString("vr_regdate"));
				replyList.add(reply);
			}
		} catch (Exception e) {
			System.out.println("BoardDao - replyList(no)");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return replyList;
	}

	/*
	 * 특정 게시 글에 대한 댓글 쓰기 요청시 호출되는 메소드 댓글 쓰기 요청이 들어오면 ReplyWriteAction 클래스에서 호출되어
	 * 사용자가 입력한 댓글을 DB에 추가하는 메소드
	 **/
	public void addReply(V_ReplyVO reply) {

		String replyInsertSql = "INSERT INTO v_reply" + " VALUES(v_reply_seq.NEXTVAL, ?, ?, ?, SYSDATE)";

		try {
			// DBManager을 이용해 DBCP로 부터 Connection을 대여한다.
			conn = getConnection();
			pstmt = conn.prepareStatement(replyInsertSql);

			// insertSql 쿼리의 플레이스홀더(?)에 대응하는 데이터를 설정한다.
			pstmt.setInt(1, reply.getV_no());
			pstmt.setString(2, reply.getVr_reply());
			pstmt.setString(3, reply.getVr_writer());

			// DB에 쿼리를 전송하여 댓글 추가 작업을 완료한다.
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("BoardDao - addReply(reply)");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * 특정 게시 글에 대한 댓글 수정 요청시 호출되는 메소드 댓글 수정 요청이 들어오면 ReplyUpdateAction 클래스에서 호출되어
	 * 사용자가 입력한 댓글을 DB에 추가하는 메소드
	 **/
	public void updateReply(V_ReplyVO reply) {

		String replyUpdateSql = "UPDATE v_reply SET vr_reply=?," + " vr_regdate=SYSDATE WHERE vr_no=?";

		try {
			// DBManager을 이용해 DBCP로 부터 Connection을 대여한다.
			conn = getConnection();
			pstmt = conn.prepareStatement(replyUpdateSql);

			// insertSql 쿼리의 플레이스홀더(?)에 대응하는 데이터를 설정한다.
			pstmt.setString(1, reply.getVr_reply());
			pstmt.setInt(2, reply.getVr_no());

			// DB에 쿼리를 전송하여 댓글 추가 작업을 완료한다.
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("BoardDao - updateReply(reply)");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * 특정 게시 글에 대한 댓글 삭제 요청시 호출되는 메소드 댓글 삭제 요청이 들어오면 ReplyDeleteAction 클래스에서 호출되어
	 * 사용자가 입력한 댓글을 DB에 추가하는 메소드
	 **/
	public void deleteReply(V_ReplyVO reply) {

		String replyDeleteSql = "DELETE FROM v_reply WHERE vr_no = ?";

		try {
			// DBManager을 이용해 DBCP로 부터 Connection을 대여한다.
			conn = getConnection();
			pstmt = conn.prepareStatement(replyDeleteSql);

			// insertSql 쿼리의 플레이스홀더(?)에 대응하는 데이터를 설정한다.
			pstmt.setInt(1, reply.getVr_no());

			// DB에 쿼리를 전송하여 댓글 추가 작업을 완료한다.
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("BoardDao - deleteReply(reply)");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
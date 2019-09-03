package com.jspstudy.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.jspstudy.bbs.beans.Board;
import com.jspstudy.bbs.beans.Reply;

public class BoardDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static DataSource ds;
	private static BoardDao instance = new BoardDao();

	private BoardDao() {
	}

	public static BoardDao getInstance() {
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

	// ��ȸ
	public ArrayList<Board> getBoardList() {
		String select = "SELECT * FROM mainboard ORDER BY idx DESC";
		ArrayList<Board> bList = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(select);
			rs = pstmt.executeQuery();
			bList = new ArrayList<Board>();

			while (rs.next()) {
				Board mainboard = new Board();
				mainboard.setIdx(rs.getInt("idx"));
				mainboard.setName(rs.getString("name"));
				mainboard.setSubject(rs.getString("subject"));
				mainboard.setContent(rs.getString("content"));
				mainboard.setRegDate(rs.getTimestamp("regdate"));
				mainboard.setReadHit(rs.getInt("readHit"));
				mainboard.setLikeHit(rs.getInt("likeHit"));				
				bList.add(mainboard);
			}

		} catch (SQLException e) {
			System.out.println("BoardDao - getBoardList() ");
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

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bList;
	} // end getBoardList()
		// ��ȣ�ΰ˻�

	public Board getBoard(int idx) {
		String update = "UPDATE mainboard set readHit = readHit + 1 WHERE idx=?";
		String select = "SELECT * FROM mainboard WHERE idx=?";
		Board mainboard = null;

		try {
			// �Խ� �� ���� �о���� ���� ���� ȸ���� ����
			conn = getConnection();
			pstmt = conn.prepareStatement(update);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();

			// �Խ� �� ���� �б�
			pstmt = conn.prepareStatement(select);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				mainboard = new Board();
				mainboard.setIdx(rs.getInt("idx"));
				mainboard.setName(rs.getString("name"));
				mainboard.setSubject(rs.getString("subject"));
				mainboard.setContent(rs.getString("content"));
				mainboard.setRegDate(rs.getTimestamp("regdate"));
				mainboard.setReadHit(rs.getInt("readHit"));
				mainboard.setLikeHit(rs.getInt("likeHit"));			

			}
		} catch (SQLException e) {
			System.out.println("BoardDao - getBoard(idx) : " + e.getMessage());
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return mainboard;
	} // end getBoard(no)
		// 새글쓰기

	public int insert(Board vo) {
		int res = 0;// parameter index=> 1 2 3 4 5
		String sql = "insert into mainboard values(seq_mainboard_idx.nextVal,?,?,?,sysdate,0,0)";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getSubject());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getContent());

			res = pstmt.executeUpdate();

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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}

//?
	public List<Board> selectList() {

		List<Board> list = new ArrayList<Board>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from mainboard order by ref desc,step asc";

		try {
			// 1.Connection ������
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ���ڵ�1�� ���尴ü
				Board vo = new Board();
				// ArrayList�߰�
				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				// �ݱ�(��������)
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
		return list;
	}

	// ��ȸ�� ����
	public int update_readhit(int idx) {
		// TODO Auto-generated method stub
		int res = 0;// parameter index=>
		String sql = "update mainboard set readHit=readHit+1 where idx=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			res = pstmt.executeUpdate();

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
		return res;
	}

	// �����Ϻ���
	public Board selectOne(int idx) {
		Board vo = null;
		String sql = "select * from mainboard where idx=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			pstmt.setInt(1, idx);
			if (rs.next()) {
				// ���ڵ�1�� ���尴ü
				vo = new Board();
				// ���� rs�� ��ġ(Cursor)
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setSubject(rs.getString("subject"));
				vo.setContent(rs.getString("content"));
				vo.setRegDate(rs.getTimestamp("regdate"));
				vo.setReadHit(rs.getInt("readHit"));
				vo.setLikeHit(rs.getInt("likeHit"));				
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				// �ݱ�(��������)
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

	public int delete(int idx) {
		int res = 0;// parameter index=>
		String sql = "DELETE FROM mainboard WHERE idx=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
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

	// 임시 수정
	public int modify(Board vo) {
		// TODO Auto-generated method stub
		int res = 0;// parameter index=> 1 2 3 4 5
		String sql = "update mainboard set subject=?,content=?,regdate=sysdate where idx=?";
		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getSubject());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getIdx());

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

	public int recUpdate(int idx) {
		// TODO Auto-generated method stub
		int result = 0;
		String updateSql = "update mainboard set likeHit=likeHit+1 where idx=?";
		String selectSql = "SELECT likehit FROM mainboard where idx=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(updateSql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(selectSql);
			pstmt.setInt(1, idx);
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

	// 좋아요 없데이트
	public int getBoardCount() {
		String countSql = "SELECT COUNT (*) FROM mainboard";
		int count = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(countSql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return count;
	}

	// 글갯수
	public int getBoardCount(String type, String keyword) {
		System.out.println(type + "-" + keyword);
		String countSql = "SELECT COUNT (*) FROM mainboard WHERE " + type + " LIKE '%' || ? || '%'";
		int count = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(countSql);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return count;
	}

	// 페이징
	public ArrayList<Board> boardList(int startRow, int endRow) {
		String boardListSql = "SELECT * FROM(SELECT ROWNUM num,"
				+ "idx,name,subject,content,regdate,readhit,likehit FROM"
				+ "(SELECT * FROM mainboard ORDER BY idx DESC))" + "WHERE num>=? AND num <=?";
		ArrayList<Board> boardList = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(boardListSql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			boardList = new ArrayList<Board>();
			while (rs.next()) {
				Board board = new Board();
				board.setIdx(rs.getInt("idx"));
				board.setName(rs.getString("name"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getTimestamp("regdate"));
				board.setReadHit(rs.getInt("readhit"));
				board.setLikeHit(rs.getInt("likehit"));
				boardList.add(board);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return boardList;
	}

	// 검색
	public ArrayList<Board> searchList(String type, String keyword, int startRow, int endRow) {

		String searchListSql = "SELECT * FROM (SELECT ROWNUM num,"
				+ " idx,name,subject,content,regdate,readhit,likehit" 
				+ " FROM (SELECT * FROM mainboard WHERE "
				+ type + " LIKE ?" + " ORDER BY idx DESC)) WHERE num >=? AND num <=?";
		ArrayList<Board> boardList = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(searchListSql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			boardList = new ArrayList<Board>();

			while (rs.next()) {
				Board board = new Board();
				board.setIdx(rs.getInt("idx"));
				board.setName(rs.getString("name"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getTimestamp("regdate"));
				board.setReadHit(rs.getInt("readhit"));
				board.setLikeHit(rs.getInt("likehit"));

				boardList.add(board);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return boardList;

	}

//특정 게시글에 해당하는 댓글 리스트를 반환하는 메소드
	public ArrayList<Reply> getReplyList(int bbsNo) {

		String replyListSql = "SELECT * FROM reply WHERE bbs_no = ?" + " ORDER BY no DESC";

		Reply reply = null;
		ArrayList<Reply> replyList = null;

		try {
			// DBManager을 이용해 DBCP로 부터 Connection을 대여한다.
			conn = getConnection();

			// Comment 테이블에 저장된 댓글 번호중 제일 큰 댓글 번호를 읽어온다.
			pstmt = conn.prepareStatement(replyListSql);
			pstmt.setInt(1, bbsNo);
			rs = pstmt.executeQuery();

			replyList = new ArrayList<Reply>();

			while (rs.next()) {

				reply = new Reply();
				reply.setNo(rs.getInt("no"));
				reply.setBbsNo(rs.getInt("bbs_no"));
				reply.setReply(rs.getString("reply"));
				reply.setWriter(rs.getString("writer"));
				reply.setRegDate(rs.getTimestamp("reg_date"));
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
	public void addReply(Reply reply) {

		String replyInsertSql = "INSERT INTO reply" + " VALUES(reply_seq.NEXTVAL, ?, ?, ?, SYSDATE)";

		try {
			// DBManager을 이용해 DBCP로 부터 Connection을 대여한다.
			conn = getConnection();
			pstmt = conn.prepareStatement(replyInsertSql);

			// insertSql 쿼리의 플레이스홀더(?)에 대응하는 데이터를 설정한다.
			pstmt.setInt(1, reply.getBbsNo());
			pstmt.setString(2, reply.getReply());
			pstmt.setString(3, reply.getWriter());

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
	public void updateReply(Reply reply) {

		String replyUpdateSql = "UPDATE reply SET reply=?," + " reg_date=SYSDATE WHERE no=?";

		try {
			// DBManager을 이용해 DBCP로 부터 Connection을 대여한다.
			conn = getConnection();
			pstmt = conn.prepareStatement(replyUpdateSql);

			// insertSql 쿼리의 플레이스홀더(?)에 대응하는 데이터를 설정한다.
			pstmt.setString(1, reply.getReply());
			pstmt.setInt(2, reply.getNo());

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
	public void deleteReply(Reply reply) {

		String replyDeleteSql = "DELETE FROM reply WHERE no = ?";

		try {
			// DBManager을 이용해 DBCP로 부터 Connection을 대여한다.
			conn = getConnection();
			pstmt = conn.prepareStatement(replyDeleteSql);

			// insertSql 쿼리의 플레이스홀더(?)에 대응하는 데이터를 설정한다.
			pstmt.setInt(1, reply.getNo());

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

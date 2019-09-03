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

import com.jspstudy.bbs.beans.MemVo;

public class MemDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static DataSource ds;
	private static MemDao instance = new MemDao();

	private MemDao() {
	}

	public static MemDao getInstance() {
		return instance;
	}

	static {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			// java:/comp/env/jdbc/oracle_test
			ds = (DataSource) envContext.lookup("jdbc/oracle_test");
		} catch (Exception e) {
			System.out.println("MemDao static {}");
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
	
	public List<MemVo> selectList() {

		List<MemVo> list = new ArrayList<MemVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from mem order by idx desc";

		try {
			//1.Connection ������
			conn =getConnection();
			//2.SQL���ó����ü ������
			pstmt = conn.prepareStatement(sql);
			//3.��ɼ�����=>����� ó��
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				//���ڵ�1�� ���尴ü
				MemVo vo = new MemVo();
				//���� rs�� ��ġ(Cursor)
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setId(rs.getString("id"));
				vo.setPwd(rs.getString("pwd"));
				vo.setZipcode(rs.getString("zipcode"));
				vo.setAddr(rs.getString("addr"));
				vo.setEmail(rs.getString("email"));
				vo.setPhon(rs.getString("phon"));
				vo.setBirth(rs.getString("birth"));
				vo.setGender(rs.getString("gender"));
				vo.setOp(rs.getInt("op"));

				//ArrayList�߰�
				list.add(vo);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//�ݱ�(��������)
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
	
	public MemVo selectOne(int idx) {

		MemVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from mem where idx=?";

		try {
			//1.Connection ������
			conn = getConnection();
			//2.SQL���ó����ü ������
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			//3.��ɼ�����=>����� ó��
			rs = pstmt.executeQuery();

			if (rs.next()) {
				//���ڵ�1�� ���尴ü
				vo = new MemVo();
				//���� rs�� ��ġ(Cursor)
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setId(rs.getString("id"));
				vo.setPwd(rs.getString("pwd"));
				vo.setZipcode(rs.getString("zipcode"));
				vo.setAddr(rs.getString("addr"));
				vo.setPhon(rs.getString("phon"));
				vo.setEmail(rs.getString("email"));
				vo.setBirth(rs.getString("birth"));
				vo.setGender(rs.getString("gender"));
				vo.setOp(rs.getInt("op"));
			

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//�ݱ�(��������)
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
	
	public int insert(MemVo vo) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;

		int res = 0;//  parameter index=>           						 12 3 4 5 6 7 8 9 10
		String sql = "insert into mem values(seq_mem_idx.nextVal,?,?,?,?,?,?,?,?,?,?)";

		try {
			//1.Connection ���
			conn = getConnection();
			//2.SQLó����ü ���
			pstmt = conn.prepareStatement(sql);
			//3.pstmt parameter ����
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3,vo.getPwd());
			pstmt.setString(4,vo.getZipcode());
			pstmt.setString(5,vo.getAddr());
			pstmt.setString(6,vo.getEmail());
			pstmt.setString(7,vo.getPhon());
			pstmt.setString(8,vo.getBirth());
			pstmt.setString(9,vo.getGender());
			pstmt.setInt(10, vo.getOp());

			//4.DB DML���
			// res : ó���� ��� 
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
	
	public int delete(int idx) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;

		int res = 0;//  parameter index=>           1
		String sql = "delete from mem where idx=?";

		try {
			//1.Connection ���
			conn = getConnection();
			//2.SQLó����ü ���
			pstmt = conn.prepareStatement(sql);
			//3.pstmt parameter ����
			pstmt.setInt(1, idx);

			//4.DB DML���
			// res : ó���� ��� 
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
	public int update(MemVo vo) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;

		int res = 0;//  parameter index=>           
		String sql = "update mem set name=?, id=?, pwd=?, zipcode=?, addr=?, email=?, phon=?, birth=?, gender=?, op=? where idx=?";

		try {
			//1.Connection ���
			conn = getConnection();
			//2.SQLó����ü ���
			pstmt = conn.prepareStatement(sql);
			//3.pstmt parameter ����
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getPwd());
			pstmt.setString(4, vo.getZipcode());
			pstmt.setString(5, vo.getAddr());
			pstmt.setString(6, vo.getEmail());
			pstmt.setString(7, vo.getPhon());
			pstmt.setString(8, vo.getBirth());
			pstmt.setString(9, vo.getGender());			
			pstmt.setInt(10, vo.getOp());	
			pstmt.setInt(11, vo.getIdx());	

			//4.DB DML���
			// res : ó���� ��� 
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
	
	public MemVo selectOne(String id) {

		MemVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from mem where id=?";

		try {
			//1.Connection ������
			conn = getConnection();
			//2.SQL���ó����ü ������
			pstmt = conn.prepareStatement(sql);
			//2-1
			pstmt.setString(1, id);
			//3.��ɼ�����=>����� ó��
			rs = pstmt.executeQuery();

			if (rs.next()) {
				//���ڵ�1�� ���尴ü
				vo = new MemVo();
				//���� rs�� ��ġ(Cursor)
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setId(rs.getString("id"));
				vo.setPwd(rs.getString("pwd"));
				vo.setZipcode(rs.getString("zipcode"));
				vo.setAddr(rs.getString("addr"));
				vo.setEmail(rs.getString("email"));
				vo.setPhon(rs.getString("phon"));
				vo.setBirth(rs.getString("birth"));
				vo.setGender(rs.getString("gender"));
				vo.setOp(rs.getInt("op"));

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//�ݱ�(��������)
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

	public int checkMem(String id, String pwd) {
		// TODO Auto-generated method stub
		String loginSql = "SELECT pwd FROM mem WHERE id = ?";
		
		int result = -1;
		String password = "";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();
			
			pstmt = conn.prepareStatement(loginSql);
			
			
			pstmt.setString(1, id);
			
			
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {				
			
				password = rs.getString("pwd");
			} else {
				return result;
			}
			
			
			if(password.equals(pwd)) {
				result = 1;
				
			} else {
				result = 0;
			}
			
		} catch(Exception e) {				
			e.printStackTrace();
			
		} finally {			
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch(SQLException se) {}
		}
		
		return result;
		
		
	}

}

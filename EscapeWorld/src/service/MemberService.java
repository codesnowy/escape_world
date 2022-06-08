package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.MemberDao;
import jdbc.ConnectionPool;
import model.MemberVO;

public class MemberService {

	private MemberDao dao = MemberDao.getInstance();
	private ConnectionPool cp = ConnectionPool.getInstance();

	private static MemberService instance = new MemberService();

	public static MemberService getInstance() {
		return instance;
	}

	private MemberService() {
	}

	// 회원가입 (INSERT)
	public int insertMem(MemberVO mem) {
		Connection conn = cp.getConnetion();

		try {
			return dao.insertMem(conn, mem);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				cp.releaseConnection(conn);
		}
		return 0;
	}

	// 중복 체크용
	public boolean dupleCheck(String id) {
		Connection conn = cp.getConnetion();

		try {
			return dao.dupleCheck(conn, id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				cp.releaseConnection(conn);
		}

		return false;
	}

	// 로그인
	public MemberVO loginMem(String id) {
		Connection conn = cp.getConnetion();

		try {
			return dao.loginMem(conn, id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				cp.releaseConnection(conn);
			}
		}

		return new MemberVO();
	}

	// 멤버 랭킹 조회(SELECT)

	public ArrayList<MemberVO> getMemList() {
		Connection conn = cp.getConnetion();

		try {
			return dao.getMemList(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				cp.releaseConnection(conn);
			} 
		}

		return new ArrayList<MemberVO>();
	}

	// 점수와 스코어 올리기(UPDATE)
	public int win(String id, int score, int money) {

		Connection conn = cp.getConnetion();

		try {
			return dao.win(conn, id, score, money);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				cp.releaseConnection(conn);
		}

		return 0;

	}

	// 방어막 아이템 구매(UPDATE)
	public int buyItem(String id, int money, int guard) {

		Connection conn = cp.getConnetion();

		try {
			return dao.buyItem(conn, id, money, guard);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				cp.releaseConnection(conn);
		}
		return 0;

	}
	
	//부활(UPDATE)
	
	public int revive(String id, int guard) {
		
		Connection conn = cp.getConnetion();

		
		try {
			return dao.revive(conn, id, guard);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}

}
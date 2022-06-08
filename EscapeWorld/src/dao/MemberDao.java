package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.MemberVO;

public class MemberDao {

	private static MemberDao instance = new MemberDao();
	
	private MemberDao() {
		
	}
	
	public static MemberDao getInstance() {
		return instance;
	}
	
	// 회원가입 (INSERT)
	public int insertMem(Connection conn, model.MemberVO mem) throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO			");
		query.append("			gamemember	");  
		query.append("VALUES	(			");
		query.append("		  ?				");
		query.append("		, ?				");
		query.append("		, ?				");
		query.append("		, ?				");
		query.append("		, ?				");
		query.append("		)				");
		
		PreparedStatement ps = conn.prepareStatement(query.toString());
		
		int idx = 1;
		ps.setString(idx++, mem.getMemId());
		ps.setString(idx++, mem.getMemPw());
		ps.setInt(idx++, mem.getMemscore());
		ps.setInt(idx++, mem.getMemoney());
		ps.setInt(idx++, mem.getMemguard());
		
		int cnt = ps.executeUpdate();
		
		if(ps != null){ ps.close(); }
		
		return cnt;
	}
	
	// 중복 체크용
	public boolean dupleCheck(Connection conn, String id) throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT					");
		query.append("		  COUNT(*) AS cnt	");
		query.append("FROM						");
		query.append("		gamemember			");
		query.append("WHERE 1=1					");
		query.append("	AND	mem_id = ?			");
		
		PreparedStatement ps = conn.prepareStatement(query.toString());
		
		int idx = 1;
		ps.setString(idx++, id);
		
		ResultSet rs = ps.executeQuery();
		int cnt = 0;
		
		while(rs.next()) {
			cnt = rs.getInt("cnt");
		}
		
		if(ps != null) ps.close();
		if(rs != null) rs.close();
		
		return (cnt == 1);
	}
	
	// 로그인(SELECT, WHERE)
	
		public MemberVO loginMem(Connection conn, String id) throws SQLException {
			
			StringBuffer query = new StringBuffer();
			query.append("SELECT						");
			query.append("		mem_id		AS id		");
			query.append("	  , MEM_PW		AS pw		");
			query.append("	  , mem_score				");
			query.append("	  , mem_money				");
			query.append("	  , MEM_GUARD				");
			query.append("FROM							");
			query.append("		gamemember				");
			query.append("WHERE 1=1						");
			query.append("	AND mem_id = ?				");
			
			PreparedStatement ps = conn.prepareStatement(query.toString());
			
			int idx = 1;
			ps.setString(idx++, id);
			
			ResultSet rs = ps.executeQuery();
			
			MemberVO result = new MemberVO();
			
			while(rs.next()) {
				result.setMemId(rs.getString("id"));
				result.setMemPw(rs.getString("pw"));
				result.setMemscore(rs.getInt("mem_score"));
				result.setMemoney(rs.getInt("mem_money"));;
				result.setMemguard(rs.getInt("MEM_GUARD"));
			}
			
			if(ps != null) {ps.close();}
			if(rs != null) {rs.close();}
			
			return result;
		}
	
	// 멤버 목록 조회(SELECT)
	public ArrayList<MemberVO> getMemList(Connection conn) throws SQLException{
		StringBuffer query = new StringBuffer();
		query.append("SELECT							");
		query.append("		mem_id		AS id			");
		query.append("	  , mem_score					");
		query.append("	  , mem_money					");
		query.append("	  , MEM_GUARD					");
		query.append("FROM								");
		query.append("		gamemember					");
		query.append(" ORDER	BY	mem_score	DESC	");
		
		PreparedStatement ps = conn.prepareStatement(query.toString());
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList<MemberVO> result = new ArrayList<>();
		
		while(rs.next()) {
			
			MemberVO temp = new MemberVO();
			temp.setMemId(rs.getString("id"));
			temp.setMemscore(rs.getInt("mem_score"));
			temp.setMemoney(rs.getInt("mem_money"));
			temp.setMemguard(rs.getInt("MEM_GUARD"));
			result.add(temp);
			
		}
		
		if(ps != null) {ps.close();}
		if(rs != null) {rs.close();}
		
		return result;
	}
	
	// 점수와 스코어 올리기(UPDATE)
	public int win(Connection conn, String id, int score, int money) throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE						");
		query.append("		  gamemember			");
		query.append("SET							");
		query.append("			mem_score = ?		");
		query.append("		  , mem_money = ?		");
		query.append("WHERE 1=1						");
		query.append("	AND		mem_id = ?			");
		
		PreparedStatement ps = conn.prepareStatement(query.toString());
				
		int idx = 1;
		ps.setInt(idx++, score);
		ps.setInt(idx++, money);
		ps.setString(idx++, id);
		
		int cnt = ps.executeUpdate();
		
		if(ps != null){ ps.close(); }
		
		return cnt;
		
	}
	
	// 방어막 아이템 구매(UPDATE)
	public int buyItem(Connection conn, String id, int money, int guard) throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE						");
		query.append("		  gamemember			");
		query.append("SET							");
		query.append("			mem_money = ?		");
		query.append("		  , mem_guard = ?		");
		query.append("WHERE 1=1						");
		query.append("	AND		mem_id = ?			");
		
		PreparedStatement ps = conn.prepareStatement(query.toString());
		
		int idx = 1;
		ps.setInt(idx++, money);
		ps.setInt(idx++, guard);
		ps.setString(idx++, id);
		
		int cnt = ps.executeUpdate();
		
		if(ps != null){ ps.close(); }
		
		return cnt;
		
	}
	
	//부활(UPDATE)
	
	public int revive(Connection conn, String id, int guard) throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE					");
		query.append("		  gamemember		");
		query.append("SET						");
		query.append("		  mem_guard = ?		");
		query.append("WHERE 1=1					");
		query.append("	AND		mem_id = ?		");
		
		PreparedStatement ps = conn.prepareStatement(query.toString());
		
		int idx = 1;
		ps.setInt(idx++, guard);
		ps.setString(idx++, id);

		int cnt = ps.executeUpdate();
		
		if(ps != null){ ps.close(); }
		
		return cnt;
	}
	
	
	
	
	
	
	
	
	
	
}
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DestinationVO;

public class DestiDao {
	
	private static DestiDao instance = new DestiDao();
	
	public static DestiDao getInstance() {
		return instance;
	}
	
	private DestiDao() {
		
	}
	
	//2. 출력된 Situation의 SITU_NO와 같은 SITU_NO에 연결된 DEST_TXT 가져오기
//	(SELECT, WHERE, JOIN)
	
	public ArrayList<DestinationVO> getDestiList (Connection conn, int situNo) throws SQLException {
		
		StringBuffer query = new StringBuffer();
		query.append("SELECT								");
		query.append("		  a.choice						");
		query.append("		, a.dest_txt					");
		query.append("		, a.dest_result		AS result	");
		query.append("FROM									");
		query.append("			destination a				");
		query.append("		  , situation b					");
		query.append("WHERE 1=1								");
		query.append("	AND	a.situ_no = b.situ_no			");
		query.append("	AND b.situ_no = ?					");
		
		PreparedStatement ps = conn.prepareStatement(query.toString());
		
		int idx = 1;
		ps.setInt(idx++, situNo);
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList<DestinationVO> Question = new ArrayList<>();
		
		while(rs.next()) {
			DestinationVO temp = new DestinationVO();			
			temp.setChoice(rs.getInt("choice"));
			temp.setDest_txt(rs.getString("dest_txt"));
			temp.setDest_result(rs.getString("result"));
			
			Question.add(temp);
		}
		
		if(ps != null) {ps.close();}
		if(rs != null) {rs.close();}
		
		return Question;
		
	}
	

}
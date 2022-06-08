package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.SituationVO;

public class SituationDao {
	
	private static SituationDao instance = new SituationDao();
	
	public static SituationDao getInstance() {
		return instance;
	}
	
	private SituationDao() {
		
	}
	
	//랜덤 index로 선택 받을 전체 목록 만들기
	public ArrayList<SituationVO> getSituList(Connection conn) throws SQLException{
		StringBuffer query = new StringBuffer();
		query.append("SELECT							");
		query.append("	  	situ_no as no				");
		query.append("	  , situ_txt as txt				");
		query.append("	  , live						");
		query.append("FROM								");
		query.append("		situation					");
		
		PreparedStatement ps = conn.prepareStatement(query.toString());
		ResultSet rs = ps.executeQuery();
		
		ArrayList<SituationVO> situation = new ArrayList<>();
		
		while(rs.next()) {
			SituationVO temp = new SituationVO();
			
			temp.setSitu_no(rs.getInt("no"));
			temp.setSitu_txt(rs.getString("txt"));
			temp.setLive(rs.getInt("live"));
			
			situation.add(temp);
		}
		
		if(ps != null) {ps.close();}
		if(rs != null) {rs.close();}
		
		return situation;
		
	}
	

}
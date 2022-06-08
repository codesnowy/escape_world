package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.SituationDao;
import jdbc.ConnectionPool;
import model.SituationVO;

public class SituService {
	
	private SituationDao dao = SituationDao.getInstance();
	private ConnectionPool cp = ConnectionPool.getInstance();
	
	private static SituService instance = new SituService();
	
	public static SituService getInstance() {
		return instance;
	}
	
	private SituService() {
		
	}
	
	//랜덤 index로 선택 받을 전체 목록 만들기
	public ArrayList<SituationVO> getSituList(){
		
		Connection conn = cp.getConnetion();
		
		try {
			return dao.getSituList(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn != null) {cp.releaseConnection(conn);}
		}
		
		return new ArrayList<SituationVO>();
		
	}
	
	

}
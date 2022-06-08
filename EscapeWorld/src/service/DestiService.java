package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DestiDao;
import jdbc.ConnectionPool;
import model.DestinationVO;

public class DestiService {

	private DestiDao dao = DestiDao.getInstance();
	private ConnectionPool cp = ConnectionPool.getInstance();
	
	private static DestiService instance = new DestiService();
	
	public static DestiService getInstance() {
		return instance;
	}
	
	private DestiService() {
		
	}
	
	
	//2. 출력된 Situation의 SITU_NO와 같은 SITU_NO에 연결된 DEST_TXT 가져오기
//	(SELECT, WHERE, JOIN)
	
	public ArrayList<DestinationVO> getDestiList (int situNo){
		Connection conn = cp.getConnetion();
		
		try {
			return dao.getDestiList(conn, situNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn != null) {cp.releaseConnection(conn);}
		}
		
		return new ArrayList<DestinationVO>();
	}
	
	
}
package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

// ConnectionFactory로부터 maxConn만큼 Connection 객체를 생성해서 풀(pool)에 보관하는 클래스
public class ConnectionPool {
	
	//Vector는 동기화(Synchronized)가 적용된 ArrayList이다.
	
	private static Vector<Connection> pool = new Vector<>();
	private static ConnectionPool instance = new ConnectionPool();
	ConnectionFactory cf = ConnectionFactory.getInstance();
	
	public static ConnectionPool getInstance() {
		return instance;
	}
	
	private ConnectionPool() {
		try {
			initPool();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//synchronized로 동기화 
	
	private synchronized void initPool() throws SQLException {
		destroyPool();
		ConnectionFactory factory = ConnectionFactory.getInstance();
		int maxConn = factory.getMaxConn();
		
		for(int i = 0 ; i < maxConn; i++) {
			pool.add(factory.getConnection());
		}
		
	}
	
	private synchronized void destroyPool() throws SQLException {
		for(int i = 0 ; i < pool.size() ; i++) {
			pool.get(i).close();
		}
		//pool 비우기
		pool.clear();
	}
	
	public synchronized Connection getConnetion() {
		if(pool.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Connection conn = pool.get(0);
		pool.remove(0);  //커넥션을 계속 지워나간다 
		return conn;
	}
	
	
	public synchronized void releaseConnection(Connection conn) {
		pool.add(conn);
		notify();
	}
	
	
	
	
	
	
	
	
	
	

}

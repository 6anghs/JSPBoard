package common;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JdbcUtil {
	private static JdbcUtil instance = new JdbcUtil();
	private static DataSource ds;
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 성공");
			InitialContext ctx = new InitialContext(); // JNDI 에서 제공하는 리소스에 접근
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myOracle"); // 해당하는 이름의 자원을 가져와서 DataSource 생성
			System.out.println("Connection Pool 생성");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	private JdbcUtil() {}
	
	public static JdbcUtil getInstance() {
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		return ds.getConnection(); // 풀에서 커넥션 반환
	}
	
}

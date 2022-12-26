package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnecterMe {
	// Connection 이라는 객체를 사용해서 연결객체를 가져올 수 있다.
	public static Connection getConnection() {
		// connection 객체 선언
		Connection connection = null;
		
		try {
			// 연결에 필요한 정보
			String userName = "hr";
			String passWord = "hr";
			
			// 자바쪽에서 dbeaver를 연결하기위해서 사용하는 경로
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			
			// 드라이버를 메모리에 할당
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 정보를 전달하여 연결 객체 가져오기
			connection = DriverManager.getConnection(url, userName, passWord);
			
			// 문제없이 연결이 성공했다면 "연결성공" 출력
			System.out.println("연결 성공");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("연결실패");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("알 수 없는 오류");
		}
		
		return connection;
	}
}

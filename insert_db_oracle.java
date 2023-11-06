package db_test;
import java.sql.*;

public class insert_db_oracle {
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USERNAME = "sys as sysdba";
	private static final String PASSWORD = "1234";
	
	public static void main(String[] args) {
		//Oracle DB 검색
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 검색 성공");
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버 검색 실패");
			System.exit(0);
		}
		
		// 데이터베이스 연결
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
        	System.out.println("데이터베이스 연결 성공");
        	
        	String selectSql = "SELECT * FROM jdbc_table_oracle";
        	Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectSql);
        	while (rs.next()) {
                String name = rs.getString(1);
                int age = rs.getInt(2);
                int id = rs.getInt(3);
                String phone = rs.getString(4);
                
                
                System.out.println("Name: " + name + ", Age: " + age + ", ID: " + id + ", Phone: " + phone);
            }
        	
        	String sql = ("INSERT INTO jdbc_table_oracle VALUES(?, ?, ?, ?)");
        	PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 변수들 정의
			pstmt.clearParameters();
			pstmt.setString(1, "Horatio"); 	
			pstmt. setInt(2, 20);
			pstmt.setInt(3, 18);	
			pstmt. setString(4, "010-1111-1111");
			
			int numRows = pstmt.executeUpdate();
			System.out.println("");
			System.out.println("Inserted " + numRows + " rows");
			
			 // SELECT 데이터
            String selectSql1 = "SELECT * FROM jdbc_table_oracle";
            Statement stmt1 = conn.createStatement();
            ResultSet rs1 = stmt1.executeQuery(selectSql1);
            System.out.println("");
            while (rs1.next()) {
                String name = rs1.getString(1);
                int age = rs1.getInt(2);
                int id = rs1.getInt(3);
                String phone = rs1.getString(4);
                
                
                System.out.println("Name: " + name + ", Age: " + age + ", ID: " + id + ", Phone: " + phone);
            }
        }
			 catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

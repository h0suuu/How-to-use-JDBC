package db_test;
import java.sql.*;


public class select_db_mysql {
	 public static final String URL = "jdbc:mysql://127.0.0.1:3306/jdbc_db_mysql";
	    public static final String USERNAME = "root";
	    public static final String PASSWORD = "1234qwer";

	    public static void main(String[] args) {
	        // MySQL JDBC driver 검색
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            System.out.println("MySQL 드라이버 검색 성공");
	        } catch (ClassNotFoundException e) {
	            System.err.println("MySQL 드라이버 검색 실패");
	            System.exit(0);
	        }

	        // 데이터베이스 연결
	        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
	            System.out.println("데이터베이스 연결 성공");
	            
	            // 검색할 ID 지정
	            int targetId = 18;

	            // SELECT 데이터 based on the ID
	            String selectSql = "SELECT phonenumber, name  FROM jdbc_table_mysql WHERE sid = ?";
	            
	            PreparedStatement pstmt = conn.prepareStatement(selectSql);
	            pstmt.setInt(1, targetId);
	           
	            ResultSet rs = pstmt.executeQuery();

	            if (rs.next()) {
	                String phone = rs.getString("phonenumber");
	                String name = rs.getString("name");
	                System.out.println("Name: " + name + ", ID: " + targetId + ", Phone: " + phone);
	            } else {
	                System.out.println("No record found for ID " + targetId);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    } 
}

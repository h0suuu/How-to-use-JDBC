package db_test;
import java.sql.*;

public class insert_db_mysql {
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
            
            
         // SELECT 데이터
            String selectSql1 = "SELECT * FROM jdbc_table_mysql";
            Statement stmt1 = conn.createStatement();
            ResultSet rs1 = stmt1.executeQuery(selectSql1);

            while (rs1.next()) {
                String name = rs1.getString("name");
                int age = rs1.getInt("age");
                int sid = rs1.getInt("sid");
                String phone = rs1.getString("phonenumber");

                System.out.println("Name: " + name + ", Age: " + age + ", ID: " + sid + ", Phone: " + phone);
            }
            
            System.out.println("");
            
            // INSERT 데이터
            String insertSql = "INSERT INTO jdbc_table_mysql (name, age, sid, phonenumber) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);

            // 변수들 정의
            pstmt.clearParameters();
            pstmt.setString(1, "Horatio");
            pstmt.setInt(2, 20);
            pstmt.setInt(3, 18);
            pstmt.setString(4, "010-1111-1111");

            int numRows = pstmt.executeUpdate();
            System.out.println("Inserted " + numRows + " rows");
            
            System.out.println("");
            
            // SELECT 데이터
            String selectSql = "SELECT * FROM jdbc_table_mysql";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectSql);

            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                int sid = rs.getInt("sid");
                String phone = rs.getString("phonenumber");

                System.out.println("Name: " + name + ", Age: " + age + ", ID: " + sid + ", Phone: " + phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
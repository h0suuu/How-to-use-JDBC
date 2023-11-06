package db_test;
import java.sql.*;

public class delete_db_mysql {
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
            
            
            // DELETE 데이터
            String deleteSql = "DELETE FROM jdbc_table_mysql WHERE sid = ?";
            PreparedStatement deleteStatement = conn.prepareStatement(deleteSql);
            deleteStatement.setString(1, "18"); // Specify the ID of the row to delete

            int deletedRows = deleteStatement.executeUpdate();
            System.out.println("");
            System.out.println("Deleted " + deletedRows + " rows");

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

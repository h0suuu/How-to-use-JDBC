package db_test;

import java.sql.*;

public class delete_db_oracle {
    public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    public static final String USERNAME = "sys as sysdba";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        // Oracle DB 검색
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
            
            // DELETE 데이터
            String deleteSql = "DELETE FROM jdbc_table_oracle WHERE sid = ?";
            PreparedStatement deleteStatement = conn.prepareStatement(deleteSql);
            deleteStatement.setString(1, "18"); // Specify the ID of the row to delete

            int deletedRows = deleteStatement.executeUpdate();
            System.out.println("");
            System.out.println("Deleted " + deletedRows + " rows");

            // SELECT 데이터
            String selectSql = "SELECT * FROM jdbc_table_oracle";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectSql);
            System.out.println("");

            while (rs.next()) {
                String name = rs.getString(1);
                int age = rs.getInt(2);
                int id = rs.getInt(3);
                String phone = rs.getString(4);

                System.out.println("Name: " + name + ", Age: " + age + ", ID: " + id + ", Phone: " + phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

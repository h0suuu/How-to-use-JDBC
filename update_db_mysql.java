package db_test;
import java.sql.*;

public class update_db_mysql {
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/jdbc_db_mysql";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "1234qwer";

    public static void main(String[] args) {
        // MySQL JDBC driver �˻�
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL ����̹� �˻� ����");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL ����̹� �˻� ����");
            System.exit(0);
        }

        // �����ͺ��̽� ����
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            System.out.println("�����ͺ��̽� ���� ����");
            
            
         // SELECT ������
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
            
            // UPDATE ������
            
            String updateQuery = "UPDATE jdbc_table_mysql SET age = ?, phonenumber = ? WHERE sid = ?";
            PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
            updateStatement.setInt(1, 25);
            updateStatement.setString(2, "010-7777-7777");
            updateStatement.setString(3, "18");
            int updatedRows = updateStatement.executeUpdate();
            System.out.println("");
            System.out.println("Updated " + updatedRows + " rows");
            
            String insertSql = "INSERT INTO jdbc_table_mysql (name, age, sid, phonenumber) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);

            
            System.out.println("");
            
            // SELECT ������
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
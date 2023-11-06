package db_test;
import java.sql.*;

public class delete_db_mysql {
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
            
            
            // DELETE ������
            String deleteSql = "DELETE FROM jdbc_table_mysql WHERE sid = ?";
            PreparedStatement deleteStatement = conn.prepareStatement(deleteSql);
            deleteStatement.setString(1, "18"); // Specify the ID of the row to delete

            int deletedRows = deleteStatement.executeUpdate();
            System.out.println("");
            System.out.println("Deleted " + deletedRows + " rows");

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

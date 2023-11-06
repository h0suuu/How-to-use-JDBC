package db_test;

import java.sql.*;

public class update_db_oracle {
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USERNAME = "sys as sysdba";
	private static final String PASSWORD = "1234";
	
	public static void main(String[] args) {
		//Oracle DB �˻�
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("����̹� �˻� ����");
		} catch (ClassNotFoundException e) {
			System.err.println("����̹� �˻� ����");
			System.exit(0);
		}
		// �����ͺ��̽� ����
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
        	System.out.println("�����ͺ��̽� ���� ����");
        	
        	// SELECT ������
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
            
            // update�� ����
            String updateQuery = "UPDATE jdbc_table_oracle SET age = ?, phonenumber = ? WHERE sid = ?";
            PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
            updateStatement.setInt(1, 25);
            updateStatement.setString(2, "010-7777-7777");
            updateStatement.setString(3, "18");
            int updatedRows = updateStatement.executeUpdate();
            System.out.println("");
            System.out.println("Updated " + updatedRows + " rows");
            
            // SELECT ������
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

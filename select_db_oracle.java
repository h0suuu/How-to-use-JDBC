package db_test;
import java.sql.*;

public class select_db_oracle {
	  	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	    public static final String USERNAME = "sys as sysdba";
	    private static final String PASSWORD = "1234";

	    public static void main(String[] args) {
	        // Oracle DB �˻�
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

	            // �˻��� ID ����
	            int targetId = 18;

	            // SELECT ������ based on the ID
	            String selectSql = "SELECT phonenumber, name  FROM jdbc_table_oracle WHERE sid = ?";
	            
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


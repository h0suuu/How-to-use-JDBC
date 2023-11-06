package db_test;
import java.sql.*;

public class insert_db_oracle {
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
        	while (rs.next()) {
                String name = rs.getString(1);
                int age = rs.getInt(2);
                int id = rs.getInt(3);
                String phone = rs.getString(4);
                
                System.out.println("Name: " + name + ", Age: " + age + ", ID: " + id + ", Phone: " + phone);
            }
        	System.out.println("");
        	
            // �ߺ����� �˻�
            String selectSql1 = "SELECT * FROM jdbc_table_oracle WHERE sid = ?";
            PreparedStatement selectStatement = conn.prepareStatement(selectSql1);
            selectStatement.setInt(1, 18);
            ResultSet rs1 = selectStatement.executeQuery();
            int targetid =32;

            if (rs1.next()) {
                System.out.println("Row with sid "+ targetid +" already exists. Insertion skipped.");
            } else {
                System.out.println("");
                
                // INSERT ������
                String insertSql = "INSERT INTO jdbc_table_oracle (name, age, sid, phonenumber) VALUES (?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(insertSql);

                // ������ ����
                pstmt.clearParameters();
                pstmt.setString(1, "Horatio");
                pstmt.setInt(2, 20);
                pstmt.setInt(3, targetid);
                pstmt.setString(4, "010-1111-1111");

                int numRows = pstmt.executeUpdate();
                System.out.println("Inserted " + numRows + " rows");
            }

            System.out.println("");
            
			 // SELECT ������
            String selectSql2 = "SELECT * FROM jdbc_table_oracle";
            Statement stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery(selectSql2);
            System.out.println("");
            while (rs2.next()) {
                String name = rs2.getString(1);
                int age = rs2.getInt(2);
                int id = rs2.getInt(3);
                String phone = rs2.getString(4);
                
                
                System.out.println("Name: " + name + ", Age: " + age + ", ID: " + id + ", Phone: " + phone);
            }
        }
			 catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

package JDBC;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	
public class DBUtil {//���ݿ⹤����
	

	    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	    String url = "jdbc:sqlserver://127.0.0.1;DatabaseName=Test";
	        
	    
	    Connection con = null;
	    ResultSet res = null;

	    public void DataBase() {
	            try {
	                Class.forName(driver);
	                //System.out.println("���������ɹ���");
	                con = DriverManager.getConnection(url, "sa", "123456");
	                //System.out.println("�������ݿ�ɹ���");
	            } catch (ClassNotFoundException e) {
	                // TODO Auto-generated catch block
	                  System.err.println("װ�� JDBC/ODBC ��������ʧ�ܡ�" );  
	                e.printStackTrace();
	            } catch (SQLException e) {
	                // TODO Auto-generated catch block
	                System.err.println("�޷��������ݿ�" ); 
	                e.printStackTrace();
	            }
	    }

	    // ��ѯ
	    public ResultSet Search(String sql, String str[]) {
	        DataBase();
	        try {
	            PreparedStatement pst =con.prepareStatement(sql);
	            if (str != null) {
	                for (int i = 0; i < str.length; i++) {
	                    pst.setString(i + 1, str[i]);
	                }
	            }
	            res = pst.executeQuery();

	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return res;
	    }

	    // ���ӻ����
	    public int AddOrUpdate(String sql, String str[]) {
	        int a = 0;
	        DataBase();
	        try {
	            PreparedStatement pst = con.prepareStatement(sql);
	            if (str != null) {
	                for (int i = 0; i < str.length; i++) {
	                    pst.setString(i + 1, str[i]);
	                }
	            }
	            a = pst.executeUpdate();
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return a;
	    }

}

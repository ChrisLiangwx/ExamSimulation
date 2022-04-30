package JDBC;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	
public class DBUtil {//数据库工具类
	

	    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	    String url = "jdbc:sqlserver://127.0.0.1;DatabaseName=Test";
	        
	    
	    Connection con = null;
	    ResultSet res = null;

	    public void DataBase() {
	            try {
	                Class.forName(driver);
	                //System.out.println("加载驱动成功！");
	                con = DriverManager.getConnection(url, "sa", "123456");
	                //System.out.println("连接数据库成功！");
	            } catch (ClassNotFoundException e) {
	                // TODO Auto-generated catch block
	                  System.err.println("装载 JDBC/ODBC 驱动程序失败。" );  
	                e.printStackTrace();
	            } catch (SQLException e) {
	                // TODO Auto-generated catch block
	                System.err.println("无法连接数据库" ); 
	                e.printStackTrace();
	            }
	    }

	    // 查询
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

	    // 增加或更改
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

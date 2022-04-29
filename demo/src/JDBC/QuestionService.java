package JDBC;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jxl.Sheet;
import jxl.Workbook;


public class QuestionService {


	    public static List<QuestionEntity> getAllByExcel(String filepath) {//�����б���excel�����е���Ŀ��Ϣ�����б�Ȼ����Խ��б������ݿ�
	        List<QuestionEntity> list = new ArrayList<QuestionEntity>();
	        try {
	            Workbook rwb = Workbook.getWorkbook(new File(filepath));
	            Sheet rs = rwb.getSheet("Sheet1");
	            int clos = rs.getColumns();// �õ����е���
	            int rows = rs.getRows();// �õ����е���
	            System.out.println("clos:" +clos + " rows:" + rows);
	            for (int i = 1; i < rows; i++) {
	                for (int j = 0; j < clos; j++) {
	                    // ��һ�����������ڶ���������
	                    String QuestionID = rs.getCell(j++, i).getContents();// Ĭ������߱��Ҳ��һ��
	                    if (QuestionID == null || "".equals(QuestionID))
	                    	QuestionID = "0";
	                    // ���������j++
	                    
	                    String QuestionStem = rs.getCell(j++, i).getContents();                    
	                    
	                    String A = rs.getCell(j++, i).getContents();
	                    
	                    String B = rs.getCell(j++, i).getContents();
	                    
	                    String C = rs.getCell(j++, i).getContents();
	                    
	                    String D = rs.getCell(j++, i).getContents();
	                    
	                    String Answer = rs.getCell(j++, i).getContents();
	                    
	                    list.add(new QuestionEntity(QuestionID,QuestionStem, A,
	                            B, C, D,Answer));
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
	    }
	    
	    public static List<QuestionEntity> getAllByDb(){//�����б������ݿ������е���Ŀ��Ϣ�����б�Ȼ����Խ��б�����excel
	    	int i=0;
	        List<QuestionEntity> list=new ArrayList<QuestionEntity>();
	        try {
	            DBUtil db=new DBUtil();
	            String sql="select QuestionStem,A,B,C,D,Answer from result";
	            ResultSet rs= db.Search(sql, null);
	            while (rs.next()) {
	                String QuestionID=String.valueOf(i+1);
	                String QuestionStem=rs.getString(1);
	                String A=rs.getString(2);
	                String B=rs.getString(3);
	                String C=rs.getString(4);
	                String D=rs.getString(5);
	                String Answer=rs.getString(6);
	                i++;
	                list.add(new QuestionEntity(QuestionID,QuestionStem,A,B,C,D,Answer));
	            }
	            
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return list;
	    }


	    public static boolean isExist(String id) {//��֤������û������
	        try {
	        	DBUtil db = new DBUtil();
	            ResultSet rs = db.Search("select * from question where QuestionID=?",
	                    new String[] { id + "" });
	            if (rs.next()) {
	                return true;//����������
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;//����������
	    }

	    public static void main(String[] args) {
	        System.out.println(isExist("1"));
	    }
}

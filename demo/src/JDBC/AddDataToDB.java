package JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import user.User;


public class AddDataToDB {//׷�ӵ�����Ŀ�����е����
	//��Ҫһ������������ļ���·��
	    
	    public void addQB(String filepath){
	          //�õ���������е�����
	          List<QuestionEntity> listExcel=QuestionService.getAllByExcel(filepath);
	          //idӦΪԭ���id�Ļ�����˳������
	          DBUtil db=new DBUtil();
        	  String sql="select count(*) from question where owner=? and QuestionBankName=?";
        	  String[] str=new String[] {User.userName,User.qbName};
        	  int content = 0;
        	  ResultSet rs = null;
        	  rs=db.Search(sql, str);//�����ݿ��л�ò�ѯ���
	            try {
	            	 while(rs.next()) {
	                 	 System.out.println(rs.getInt(1));
	            		 content=rs.getInt(1);//ֻ�鵽һ�����ݣ���ȡ��һ��
	                 }
	                
	            } catch (Exception e) {
	                e.printStackTrace();
	               }
	             finally {
	                try {
	                    rs.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
        	  
        	
        	  
	          for (QuestionEntity qe : listExcel) {
	        	  int j=Integer.parseInt(qe.getQuestionID());
	        	  String questionID=Integer.toString(j+content);
	        	  String id=User.qbName+User.userID+questionID;
	        	  System.out.println("�������ĿID:");
	        	  System.out.println(id);
	              if (QuestionService.isExist(id)==false) {
	                  //���в����ڶ�ӦID�����ݾ����
	                  sql="insert into question (QuestionID,QuestionStem,A,B,C,D,Answer,Owner,QuestionBankName,QuestionBankType) values(?,?,?,?,?,?,?,?,?,?)";//����������
	                  str=new String[]{User.qbName+User.userID+questionID,qe.getQuestionStem(),qe.getA(),qe.getB(),qe.getC(),qe.getD(),qe.getAnswer(),User.userName,User.qbName,User.qbType};
	                  db.AddOrUpdate(sql, str);
	                  System.out.println("�ѵ���");
	              }
	              else {
	                  //���д��ڶ�ӦID�����ݾ͸���
	            	  sql="update question set QuestionStem=?,A=?,B=?,C=?,D=?,Answer=?,Owner=?,QuestionBankName=?,QuestionBankType=? where QuestionID=?";//�����Ѵ��ڵ�����
	                  str=new String[]{qe.getQuestionStem(),qe.getA(),qe.getB(),qe.getC(),qe.getD(),qe.getAnswer(),User.userName,User.qbName,User.qbType,User.qbName+User.userID+questionID+""};
	                  db.AddOrUpdate(sql, str);
	                  System.out.println("�Ѹ���");
	              }
	          }
	      }

}

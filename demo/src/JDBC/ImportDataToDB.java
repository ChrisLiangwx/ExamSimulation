package JDBC;

import java.util.List;

import user.User;


public class ImportDataToDB {//���б��е��������ݵ������ݿ�
	//��Ҫһ������������ļ���·��
	    
	    public void importQB(String filepath){
	          //�õ���������е�����
	          List<QuestionEntity> listExcel=QuestionService.getAllByExcel(filepath);
	          
	          
	          DBUtil db=new DBUtil();
	          for (QuestionEntity qe : listExcel) {
	        	  String id=User.qbName+User.userID+qe.getQuestionID();
	        	  System.out.println("�������ĿID:");
	        	  System.out.println(id);
	              if (QuestionService.isExist(id)==false) {
	                  //���в����ڶ�ӦID�����ݾ����
	                  String sql="insert into question (QuestionID,QuestionStem,A,B,C,D,Answer,Owner,QuestionBankName,QuestionBankType) values(?,?,?,?,?,?,?,?,?,?)";//����������
	                  String[] str=new String[]{User.qbName+User.userID+qe.getQuestionID(),qe.getQuestionStem(),qe.getA(),qe.getB(),qe.getC(),qe.getD(),qe.getAnswer(),User.userName,User.qbName,User.qbType};
	                  db.AddOrUpdate(sql, str);
	                  System.out.println("�ѵ���");
	              }
	              else {
	                  //���д��ڶ�ӦID�����ݾ͸���
	            	  String sql="update question set QuestionStem=?,A=?,B=?,C=?,D=?,Answer=?,Owner=?,QuestionBankName=?,QuestionBankType=? where QuestionID=?";//�����Ѵ��ڵ�����
	                  String[] str=new String[]{qe.getQuestionStem(),qe.getA(),qe.getB(),qe.getC(),qe.getD(),qe.getAnswer(),User.userName,User.qbName,User.qbType,User.qbName+User.userID+qe.getQuestionID()+""};
	                  db.AddOrUpdate(sql, str);
	                  System.out.println("�Ѹ���");
	              }
	          }
	      }

}

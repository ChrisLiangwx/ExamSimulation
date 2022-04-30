package JDBC;

import java.util.List;

import user.User;


public class ImportDataToDB {//将列表中的所有内容导入数据库
	//需要一个参数：题库文件的路径
	    
	    public void importQB(String filepath){
	          //得到表格中所有的数据
	          List<QuestionEntity> listExcel=QuestionService.getAllByExcel(filepath);
	          
	          
	          DBUtil db=new DBUtil();
	          for (QuestionEntity qe : listExcel) {
	        	  String id=User.qbName+User.userID+qe.getQuestionID();
	        	  System.out.println("导入的题目ID:");
	        	  System.out.println(id);
	        	  if(User.userID.equals("0000")) {//如果用户为管理员，则上传时的题号包含guest的userID，且所有者为guest
	        		  if (QuestionService.isExist(id)==false) {
		                  //表中不存在对应ID的数据就添加
		                  String sql="insert into question (QuestionID,QuestionStem,A,B,C,D,Answer,Owner,QuestionBankName,QuestionBankType,IsPublic) values(?,?,?,?,?,?,?,?,?,?,?)";//插入新数据
		                  String[] str=new String[]{User.qbName+"9999"+qe.getQuestionID(),qe.getQuestionStem(),qe.getA(),qe.getB(),qe.getC(),qe.getD(),qe.getAnswer(),"guest",User.qbName,User.qbType,"Yes"};
		                  db.AddOrUpdate(sql, str);
		                  System.out.println("已导入");
		              }
		              else {
		                  //表中存在对应ID的数据就更新
		            	  String sql="update question set QuestionStem=?,A=?,B=?,C=?,D=?,Answer=?,Owner=?,QuestionBankName=?,QuestionBankType=?,IsPublic=? where QuestionID=?";//更新已存在的数据
		                  String[] str=new String[]{qe.getQuestionStem(),qe.getA(),qe.getB(),qe.getC(),qe.getD(),qe.getAnswer(),User.userName,User.qbName,"Yes",User.qbType,"guest"+"9999"+qe.getQuestionID()+""};
		                  db.AddOrUpdate(sql, str);
		                  System.out.println("已更新");
		              }
	        	  }else {
	        		  if (QuestionService.isExist(id)==false) {
		                  //表中不存在对应ID的数据就添加
		                  String sql="insert into question (QuestionID,QuestionStem,A,B,C,D,Answer,Owner,QuestionBankName,QuestionBankType) values(?,?,?,?,?,?,?,?,?,?)";//插入新数据
		                  String[] str=new String[]{User.qbName+User.userID+qe.getQuestionID(),qe.getQuestionStem(),qe.getA(),qe.getB(),qe.getC(),qe.getD(),qe.getAnswer(),User.userName,User.qbName,User.qbType};
		                  db.AddOrUpdate(sql, str);
		                  System.out.println("已导入");
		              }
		              else {
		                  //表中存在对应ID的数据就更新
		            	  String sql="update question set QuestionStem=?,A=?,B=?,C=?,D=?,Answer=?,Owner=?,QuestionBankName=?,QuestionBankType=? where QuestionID=?";//更新已存在的数据
		                  String[] str=new String[]{qe.getQuestionStem(),qe.getA(),qe.getB(),qe.getC(),qe.getD(),qe.getAnswer(),User.userName,User.qbName,User.qbType,User.qbName+User.userID+qe.getQuestionID()+""};
		                  db.AddOrUpdate(sql, str);
		                  System.out.println("已更新");
		              }
	        	  }
	              
	          }
	      }

}

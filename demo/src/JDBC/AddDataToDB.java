package JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import user.User;


public class AddDataToDB {//追加导入题目到现有的题库
	//需要一个参数：题库文件的路径
	    
	    public void addQB(String filepath){
	          //得到表格中所有的数据
	          List<QuestionEntity> listExcel=QuestionService.getAllByExcel(filepath);
	          //id应为原题库id的基础上顺序生成
	          DBUtil db=new DBUtil();
        	  String sql="select count(*) from question where owner=? and QuestionBankName=?";
        	  String[] str=new String[] {User.userName,User.qbName};
        	  int content = 0;
        	  ResultSet rs = null;
        	  rs=db.Search(sql, str);//从数据库中获得查询结果
	            try {
	            	 while(rs.next()) {
	                 	 System.out.println(rs.getInt(1));
	            		 content=rs.getInt(1);//只查到一行数据，获取第一行
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
	        	  System.out.println("导入的题目ID:");
	        	  System.out.println(id);
	              if (QuestionService.isExist(id)==false) {
	                  //表中不存在对应ID的数据就添加
	                  sql="insert into question (QuestionID,QuestionStem,A,B,C,D,Answer,Owner,QuestionBankName,QuestionBankType) values(?,?,?,?,?,?,?,?,?,?)";//插入新数据
	                  str=new String[]{User.qbName+User.userID+questionID,qe.getQuestionStem(),qe.getA(),qe.getB(),qe.getC(),qe.getD(),qe.getAnswer(),User.userName,User.qbName,User.qbType};
	                  db.AddOrUpdate(sql, str);
	                  System.out.println("已导入");
	              }
	              else {
	                  //表中存在对应ID的数据就更新
	            	  sql="update question set QuestionStem=?,A=?,B=?,C=?,D=?,Answer=?,Owner=?,QuestionBankName=?,QuestionBankType=? where QuestionID=?";//更新已存在的数据
	                  str=new String[]{qe.getQuestionStem(),qe.getA(),qe.getB(),qe.getC(),qe.getD(),qe.getAnswer(),User.userName,User.qbName,User.qbType,User.qbName+User.userID+questionID+""};
	                  db.AddOrUpdate(sql, str);
	                  System.out.println("已更新");
	              }
	          }
	      }

}

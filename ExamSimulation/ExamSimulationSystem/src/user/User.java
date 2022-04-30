package user;

public class User {//用户ID，在登录时设定userID，在考试时调用该userID对应的题库
	public static String userID="9999";//账号或学号
	public static String userName="guest";//姓名或昵称
	public static String qbType=null;//题库类型单选\多选\判断
	public static String qbName=null;//题库名称
	public static int userid = Integer.parseInt(userID);

}

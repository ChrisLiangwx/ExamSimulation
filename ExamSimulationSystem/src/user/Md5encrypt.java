package user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5encrypt {
	public static String md5Password(String password){
		 try {
			MessageDigest digest=MessageDigest.getInstance("md5");
			byte[]result=digest.digest(password.getBytes());
			 StringBuffer buffer=new StringBuffer();
			 for(byte b:result){
				 int number=b&0xff; 
				 String str=Integer.toHexString(number);
				 if(str.length()==1){
					 buffer.append("0");
				 }
				 buffer.append(str);
			 }
			 return buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		
		 
	 }

}

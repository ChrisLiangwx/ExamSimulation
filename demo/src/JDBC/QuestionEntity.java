package JDBC;

public class QuestionEntity {//表的实体
	
	    private String QuestionID;//题号 为了保证每人导入的每个不同名称题库的QuestionID在数据库single_choice_question表中不重复，这里QuestionID由文件名、用户名以及文件内的题目序号组成
	    private String QuestionStem;//题干
	    //四个选项
	    private String A;
	    private String B;
	    private String C;
	    private String D;
	    private String Answer;//答案
	    
	    
	    
	    public QuestionEntity() {
	    }
	    public QuestionEntity(String QuestionID,String QuestionStem, String A, String B, String C,String D,String Answer) {
	        this.QuestionID=QuestionID;
	    	this.QuestionStem=QuestionStem;
	        this.A=A;
	        this.B=B;
	        this.C=C;
	        this.D=D;
	        this.Answer=Answer;
	    }
	    
	    @Override
	    public String toString() {
	        return "QuestionEntity [题号=" + QuestionID + ", 题干=" + QuestionStem + ", A=" + A + ", B=" + B+ ", C" + C + ", D" + D + ", 答案=" + Answer + "]";
	    }
	    public String getQuestionID() {
	        return QuestionID;
	    }
	    public String getQuestionStem() {
	        return QuestionStem;
	    }
	    public String getA() {
	        return A;
	    }
	    public String getB() {
	        return B;
	    }
	    public String getC() {
	        return C;
	    }
	    public String getD() {
	        return D;
	    }
	    public String getAnswer() {
	        return Answer;
	    }
	    
	    
	    
	    public void SetQuestionID(String QuestionID) {
	        this.QuestionID=QuestionID;
	    }
	    public void SetQuestionStem(String QuestionStem) {
	        this.QuestionStem = QuestionStem;
	    }
	    public void SetA(String A) {
	        this.A = A;
	    }
	    public void SetB(String B) {
	        this.B = B;
	    }
	    public void SetC(String C) {
	        this.C = C;
	    }
	    public void SetD(String D) {
	        this.D = D;
	    }
	    public void SetAnswer(String Answer) {
	        this.Answer = Answer;
	    }
	    

}

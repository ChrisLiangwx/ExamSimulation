package JDBC;

public class QuestionEntity {//���ʵ��
	
	    private String QuestionID;//��� Ϊ�˱�֤ÿ�˵����ÿ����ͬ��������QuestionID�����ݿ�single_choice_question���в��ظ�������QuestionID���ļ������û����Լ��ļ��ڵ���Ŀ������
	    private String QuestionStem;//���
	    //�ĸ�ѡ��
	    private String A;
	    private String B;
	    private String C;
	    private String D;
	    private String Answer;//��
	    
	    
	    
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
	        return "QuestionEntity [���=" + QuestionID + ", ���=" + QuestionStem + ", A=" + A + ", B=" + B+ ", C" + C + ", D" + D + ", ��=" + Answer + "]";
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

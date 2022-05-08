package examUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;

import JDBC.DBUtil;
import JDBC.QuestionService;
import user.User;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.JRadioButton;

public class MultipleChoiceExamJframe {//���Դ���
//�������洢��result���У����ÿ�δ���֮ǰ��Ҫ��ɾ��result���е���������
	private JFrame frame;
	private JTextField textField_ShowQuestionNo;
	private JLabel Label_QuestionStem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MultipleChoiceExamJframe window = new MultipleChoiceExamJframe(args);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
//	public ExamJframe(String[] args) {
//		initialize();
//	}

	/**
	 * Initialize the contents of the frame.
	 */
	public MultipleChoiceExamJframe(String[] args){
		frame = new JFrame();
		frame.setTitle("\u591A\u9879\u9009\u62E9");
		//ʹ��ʾ����Ļ����
		java.awt.Toolkit toolkit=java.awt.Toolkit.getDefaultToolkit();
		Dimension screenSize=toolkit.getScreenSize();
		double screenWidth=screenSize.getWidth();
		double screenHeight=screenSize.getHeight();
		frame.setSize(1125,624);
		frame.setLocation((int)(screenWidth-frame.getWidth())/2,(int)(screenHeight-frame.getHeight())/2);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		//�ϲ����
		JPanel panel_top = new JPanel();
		frame.getContentPane().add(panel_top, BorderLayout.NORTH);
		panel_top.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel Label_ShowQuestionNo = new JLabel("\u5F53\u524D\u8003\u8BD5\u8FDB\u5EA6\uFF1A");
		Label_ShowQuestionNo.setFont(new Font("����", Font.PLAIN, 30));
		panel_top.add(Label_ShowQuestionNo);
		
		textField_ShowQuestionNo = new JTextField();
		textField_ShowQuestionNo.setEditable(false);
		textField_ShowQuestionNo.setFont(new Font("����", Font.PLAIN, 30));
		panel_top.add(textField_ShowQuestionNo);
		textField_ShowQuestionNo.setColumns(20);
		int count=0;
		textField_ShowQuestionNo.setText("��"+count+"��/��"+args[1]+"��");
		
		
		
		//�в����
		JPanel panel_mid = new JPanel();
		frame.getContentPane().add(panel_mid, BorderLayout.CENTER);
		CardLayout cardlayout = new CardLayout();
		panel_mid.setLayout(cardlayout);

		
		//ɾ��result��
		DBUtil exam=new DBUtil();
		String sql="TRUNCATE Table result";
		exam.AddOrUpdate(sql, null);
        System.out.println("ɾ��dbo.result�ɹ�");
		
		
		String seq = args[0];
		String num = args[1];
		System.out.print("����˳��Ϊ��");
		if(seq.equals("0"))
			System.out.println("˳�����");
		else
			System.out.println("�������");
		System.out.println("���ο��Ե���Ŀ����Ϊ��"+num);
		int QuestionSeq = Integer.parseInt(seq);//����˳�򣬴Ӵ�������л��
		int QuestionNo = Integer.parseInt(num);//��Ŀ�������Ӵ�������л��
/*----------------------------------------------------------------------------------------------------*/		
		if(QuestionSeq==0)//����˳�����
			for(int i = 1 ; i <= QuestionNo ; i++) {
				int j=i;//���Թ����е����
				JPanel p = new JPanel();
				p.setLayout(null);
										
				
				String content_stem=null;
				sql="select QuestionStem from question where QuestionID = ?";
	            String[] str=new String[]{User.qbName+User.userID+String.valueOf(i)};
	            ResultSet rs = null;
	            rs=exam.Search(sql, str);
	            //�����ݿ��л�ò�ѯ���
	            try {
	            	 while(rs.next()) {
	                 	 //System.out.println(rs.getString(1));
	            		 content_stem=rs.getString(1);//ֻ�鵽һ�����ݣ���ȡ��һ��
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
	            //��ɵı�ǩ
	            //System.out.println(s);
	            Label_QuestionStem = new JLabel("\u9898\u5E72");
				String content="<html>"+i+"."+content_stem+"<html>";//html��ǩ��������ʵ���Զ�����
				Label_QuestionStem.setText(content);
	            Label_QuestionStem.setFont(new Font("����", Font.PLAIN, 20));
				Label_QuestionStem.setBounds(29, 25, 850, 150);
				//select QuestionStem from question where QuestionID = i

	            
	            
	            
	            //�ĸ�ѡ��ı�ǩ
				
				String content_A=null;
				sql="select A from question where QuestionID = ?";
	            rs = null;
	            rs=exam.Search(sql, str);
	            //�����ݿ��л�ò�ѯ���
	            try {
	            	 while(rs.next()) {
	            		 content_A=rs.getString(1);//ֻ�鵽һ�����ݣ���ȡ��һ��
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
	            
				
				JLabel Label_A = new JLabel("A.");						
				Label_A.setFont(new Font("����", Font.PLAIN, 20));
				Label_A.setText("A."+content_A);
				Label_A.setBounds(29, 175, 3000, 24);
				
				String content_B=null;
				sql="select B from question where QuestionID = ?";
	            rs = null;
	            rs=exam.Search(sql, str);
	            //�����ݿ��л�ò�ѯ���
	            try {
	            	 while(rs.next()) {
	            		 content_B=rs.getString(1);//ֻ�鵽һ�����ݣ���ȡ��һ��
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
				
				JLabel Label_B = new JLabel("B.");
				Label_B.setFont(new Font("����", Font.PLAIN, 20));
				Label_B.setText("B."+content_B);
				Label_B.setBounds(29, 225, 3000, 24);
				
				String content_C=null;
				sql="select C from question where QuestionID = ?";
	            rs = null;
	            rs=exam.Search(sql, str);
	            //�����ݿ��л�ò�ѯ���
	            try {
	            	 while(rs.next()) {
	            		 content_C=rs.getString(1);//ֻ�鵽һ�����ݣ���ȡ��һ��
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
				
				JLabel Label_C = new JLabel("C.");
				Label_C.setFont(new Font("����", Font.PLAIN, 20));
				Label_C.setText("C."+content_C);
				Label_C.setBounds(29, 275, 3000, 24);
				
				String content_D=null;
				sql="select D from question where QuestionID = ?";
	            rs = null;
	            rs=exam.Search(sql, str);
	            //�����ݿ��л�ò�ѯ���
	            try {
	            	 while(rs.next()) {
	            		 content_D=rs.getString(1);//ֻ�鵽һ�����ݣ���ȡ��һ��
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
				
				JLabel Label_D = new JLabel("D.");
				Label_D.setFont(new Font("����", Font.PLAIN, 20));
				Label_D.setText("D."+content_D);
				Label_D.setBounds(29, 325, 3000, 24);
				
				//�ĸ�ѡ��ĵ�ѡ��ť
				JRadioButton RadioButton_A = new JRadioButton("");
				RadioButton_A.setBounds(850, 175, 121, 23);
				
				JRadioButton RadioButton_B = new JRadioButton("");
				RadioButton_B.setBounds(850, 225, 121, 23);
				
				JRadioButton RadioButton_C = new JRadioButton("");
				RadioButton_C.setBounds(850, 275, 121, 23);
				
				JRadioButton RadioButton_D = new JRadioButton("");
				RadioButton_D.setBounds(850, 325, 121, 23);
				
					
				//�����ݿ��л����ȷ��
				String content_answer=null;
				sql="select Answer from question where QuestionID = ?";
	            rs = null;
	            rs=exam.Search(sql, str);
	            //�����ݿ��л�ò�ѯ���
	            try {
	            	 while(rs.next()) {
	            		 content_answer=rs.getString(1);//ֻ�鵽һ�����ݣ���ȡ��һ��
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
	            String stem=content_stem;
	            String A=content_A;
	            String B=content_B;
	            String C=content_C;
	            String D=content_D;
	            String answer=content_answer;
	            String []myanswer=new String[4];
	            int cnt=count+i;//ͳ�ƴ��������
	            
				//Ϊ�ĸ���ѡ��ť�趨ѡ���¼�
				RadioButton_A.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						//����š������е���Ŀ��š��˽������ȷ�𰸴������ݿ�result���� ���Ϊ����i
 						//���Ѵ��ڽ������£��������򴴽�	
						if(myanswer[0]=="A") {
							myanswer[0]=null;
						}else {
							myanswer[0]="A";
						}
			            String MyAnswer=StringUtils.join(myanswer);
						if(cnt<=QuestionNo)
							textField_ShowQuestionNo.setText("��"+cnt+"��/��"+args[1]+"��");//ѡ����һ��ѡ�������+1
						if(!MultipleChoiceExamJframe.isExist(User.qbName+User.userID+String.valueOf(j))) {//�����ڣ�����������
							String sql="insert into result (QuestionNo,QuestionID,QuestionStem,A,B,C,D,Answer,MyAnswer,UserID) values(?,?,?,?,?,?,?,?,?,?)";
							String[] str=new String[]{String.valueOf(j), User.qbName+User.userID+String.valueOf(j), stem, A, B, C, D, answer,MyAnswer,User.userID};
							exam.AddOrUpdate(sql, str);
							System.out.println("��"+j+"��ѡ��"+MyAnswer);	
						}
						else {//�Ѵ��ڣ���������
							String sql="update result set MyAnswer=? where QuestionID=?";
							String[] str=new String[]{MyAnswer,User.qbName+User.userID+String.valueOf(j)};
							exam.AddOrUpdate(sql, str);
							System.out.println("��"+j+"�����Ϊ��"+MyAnswer);	
						}							
					}
				});
				
				RadioButton_B.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if(myanswer[1]=="B") {
							myanswer[1]=null;
						}else {
							myanswer[1]="B";
						}
			            String MyAnswer=StringUtils.join(myanswer);
						if(cnt<=QuestionNo)
							textField_ShowQuestionNo.setText("��"+cnt+"��/��"+args[1]+"��");
						if(!MultipleChoiceExamJframe.isExist(User.qbName+User.userID+String.valueOf(j))) {//�����ڣ�����������
							String sql="insert into result (QuestionNo,QuestionID,QuestionStem,A,B,C,D,Answer,MyAnswer,UserID) values(?,?,?,?,?,?,?,?,?,?)";
							String[] str=new String[]{String.valueOf(j), User.qbName+User.userID+String.valueOf(j), stem, A, B, C, D, answer,MyAnswer,User.userID};
							exam.AddOrUpdate(sql, str);
							System.out.println("��"+j+"��ѡ��"+MyAnswer);	
						}
						else {//�Ѵ��ڣ���������
							String sql="update result set MyAnswer=? where QuestionID=?";
							String[] str=new String[]{MyAnswer,User.qbName+User.userID+String.valueOf(j)};
							exam.AddOrUpdate(sql, str);
							System.out.println("��"+j+"�����Ϊ��"+MyAnswer);	
						}			
					}
				});
				
				RadioButton_C.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if(myanswer[2]=="C") {
							myanswer[2]=null;
						}else {
							myanswer[2]="C";
						}
			            String MyAnswer=StringUtils.join(myanswer);
						if(cnt<=QuestionNo)
							textField_ShowQuestionNo.setText("��"+cnt+"��/��"+args[1]+"��");
						if(!MultipleChoiceExamJframe.isExist(User.qbName+User.userID+String.valueOf(j))) {//�����ڣ�����������
							String sql="insert into result (QuestionNo,QuestionID,QuestionStem,A,B,C,D,Answer,MyAnswer,UserID) values(?,?,?,?,?,?,?,?,?,?)";
							String[] str=new String[]{String.valueOf(j), User.qbName+User.userID+String.valueOf(j), stem, A, B, C, D, answer,MyAnswer,User.userID};
							exam.AddOrUpdate(sql, str);
							System.out.println("��"+j+"��ѡ��"+MyAnswer);	
						}
						else {//�Ѵ��ڣ���������
							String sql="update result set MyAnswer=? where QuestionID=?";
							String[] str=new String[]{MyAnswer,User.qbName+User.userID+String.valueOf(j)};
							exam.AddOrUpdate(sql, str);
							System.out.println("��"+j+"�����Ϊ��"+MyAnswer);	
						}			
					}
				});
				
				RadioButton_D.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if(myanswer[3]=="D") {
							myanswer[3]=null;
						}else {
							myanswer[3]="D";
						}
			            String MyAnswer=StringUtils.join(myanswer);
						if(cnt<=QuestionNo)
						textField_ShowQuestionNo.setText("��"+cnt+"��/��"+args[1]+"��");
						if(!MultipleChoiceExamJframe.isExist(User.qbName+User.userID+String.valueOf(j))) {//�����ڣ�����������
							String sql="insert into result (QuestionNo,QuestionID,QuestionStem,A,B,C,D,Answer,MyAnswer,UserID) values(?,?,?,?,?,?,?,?,?,?)";
							String[] str=new String[]{String.valueOf(j), User.qbName+User.userID+String.valueOf(j), stem, A, B, C, D, answer,MyAnswer,User.userID};
							exam.AddOrUpdate(sql, str);
							System.out.println("��"+j+"��ѡ��"+MyAnswer);	
						}
						else {//�Ѵ��ڣ���������
							String sql="update result set MyAnswer=? where QuestionID=?";
							String[] str=new String[]{MyAnswer,User.qbName+User.userID+String.valueOf(j)};
							exam.AddOrUpdate(sql, str);
							System.out.println("��"+j+"�����Ϊ��"+MyAnswer);	
						}				
					}
				});

					
				
				//ͨ��ѭ��,�������ӵ�CardLayout�����ſ�Ƭ��
				p.add(Label_QuestionStem);
				p.add(Label_A);
				p.add(Label_B);
				p.add(Label_C);
				p.add(Label_D);
				p.add(RadioButton_A);
				p.add(RadioButton_B);
				p.add(RadioButton_C);
				p.add(RadioButton_D);
				
				//����Щ��Ƭ��ӵ��в������
				panel_mid.add(p);
			}
/*----------------------------------------------------------------------------------------------------*/
		else//�������
		{//������Ҫ��ȡ������ж��ٵ��⣬�Լ����ο��Ե�������Ȼ�����RandomNum�еķ������ɴ�����ŵ��������
			int QuestionNoInQB=0;//����е���Ŀ����
			
			sql="select count(*) from  question where owner=? and QuestionBankName=? ";
			String[] str= {User.userName,User.qbName};
            ResultSet rs = null;
            rs=exam.Search(sql, str);//sql������޲�������˴˴�Ϊnull
            //�����ݿ��л������е���Ŀ����
            try {
            	 while(rs.next()) {
                 	 //System.out.println("����е���Ŀ����Ϊ��"+rs.getInt(1));
                 	 QuestionNoInQB=rs.getInt(1);//ֻ�鵽һ�����ݣ���ȡ��һ��
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
            
            RandomNum r=new RandomNum();
    		int[]  arr = new int[QuestionNo];
    		arr=r.getRand(QuestionNoInQB,QuestionNo);//�������ɵ��������ΧΪ0-��QuestionNoInQB-1����������Ҫ����Ҫȫ��+1
    		
    		System.out.print("ѡ���questionIDΪ��");
    		for(int i=0;i<QuestionNo;i++) {
    			arr[i]++;
    			System.out.print(" "+arr[i]);
    		}
    		
    		
    		int[]  array=arr;
			
			for(int i = 0 ; i < QuestionNo ; i++) {
				int j=i+1;//���Թ����е����
				JPanel p = new JPanel();
				p.setLayout(null);
				
				
				String content_stem=null;
				sql="select QuestionStem from question where QuestionID = ?";
	            str=new String[]{User.qbName+User.userID+String.valueOf(arr[i])};//��ѯ�ڣ����������ŵ���������
	            rs = null;
	            rs=exam.Search(sql, str);
	            //�����ݿ��л�ò�ѯ���
	            try {
	            	 while(rs.next()) {
	                 	 //System.out.println(rs.getString(1));
	            		 content_stem=rs.getString(1);//ֻ�鵽һ�����ݣ���ȡ��һ��
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
	            //��ɵı�ǩ
	            //System.out.println(s);
	            Label_QuestionStem = new JLabel("\u9898\u5E72");
				String content="<html>"+(i+1)+"."+content_stem+"<html>";//html��ǩ��������ʵ���Զ�����
				Label_QuestionStem.setText(content);
	            Label_QuestionStem.setFont(new Font("����", Font.PLAIN, 20));
				Label_QuestionStem.setBounds(29, 25, 850, 150);
				//select QuestionStem from question where QuestionID = i

	            
	            
	            
	            //����ѡ��ı�ǩ
				
				String content_A=null;
				sql="select A from question where QuestionID = ?";
	            rs = null;
	            rs=exam.Search(sql, str);
	            //�����ݿ��л�ò�ѯ���
	            try {
	            	 while(rs.next()) {
	            		 content_A=rs.getString(1);//ֻ�鵽һ�����ݣ���ȡ��һ��
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
	            
				
				JLabel Label_A = new JLabel("A.");						
				Label_A.setFont(new Font("����", Font.PLAIN, 20));
				Label_A.setText("A."+content_A);
				Label_A.setBounds(29, 175, 3000, 24);
				
				String content_B=null;
				sql="select B from question where QuestionID = ?";
	            rs = null;
	            rs=exam.Search(sql, str);
	            //�����ݿ��л�ò�ѯ���
	            try {
	            	 while(rs.next()) {
	            		 content_B=rs.getString(1);//ֻ�鵽һ�����ݣ���ȡ��һ��
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
				
				JLabel Label_B = new JLabel("B.");
				Label_B.setFont(new Font("����", Font.PLAIN, 20));
				Label_B.setText("B."+content_B);
				Label_B.setBounds(29, 225, 3000, 24);
				
				String content_C=null;
				sql="select C from question where QuestionID = ?";
	            rs = null;
	            rs=exam.Search(sql, str);
	            //�����ݿ��л�ò�ѯ���
	            try {
	            	 while(rs.next()) {
	            		 content_C=rs.getString(1);//ֻ�鵽һ�����ݣ���ȡ��һ��
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
				
				JLabel Label_C = new JLabel("C.");
				Label_C.setFont(new Font("����", Font.PLAIN, 20));
				Label_C.setText("C."+content_C);
				Label_C.setBounds(29, 275, 3000, 24);
				
				String content_D=null;
				sql="select D from question where QuestionID = ?";
	            rs = null;
	            rs=exam.Search(sql, str);
	            //�����ݿ��л�ò�ѯ���
	            try {
	            	 while(rs.next()) {
	            		 content_D=rs.getString(1);//ֻ�鵽һ�����ݣ���ȡ��һ��
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
				
				JLabel Label_D = new JLabel("D.");
				Label_D.setFont(new Font("����", Font.PLAIN, 20));
				Label_D.setText("D."+content_D);
				Label_D.setBounds(29, 325, 3000, 24);
				
				//�ĸ�ѡ��ĵ�ѡ��ť
				JRadioButton RadioButton_A = new JRadioButton("");
				RadioButton_A.setBounds(850, 175, 121, 23);
				
				JRadioButton RadioButton_B = new JRadioButton("");
				RadioButton_B.setBounds(850, 225, 121, 23);
				
				JRadioButton RadioButton_C = new JRadioButton("");
				RadioButton_C.setBounds(850, 275, 121, 23);
				
				JRadioButton RadioButton_D = new JRadioButton("");
				RadioButton_D.setBounds(850, 325, 121, 23);
				
				
				//�����ݿ��л����ȷ��
				String content_answer=null;
				sql="select Answer from question where QuestionID = ?";
	            rs = null;
	            rs=exam.Search(sql, str);
	            //�����ݿ��л�ò�ѯ���
	            try {
	            	 while(rs.next()) {
	            		 content_answer=rs.getString(1);//ֻ�鵽һ�����ݣ���ȡ��һ��
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
	            String stem=content_stem;
	            String A=content_A;
	            String B=content_B;
	            String C=content_C;
	            String D=content_D;
	            String answer=content_answer;
	            String []myanswer=new String[4];
				
	            int cnt=count+i+1;//ͳ�ƴ��������
	            
				//Ϊ�ĸ���ѡ��ť�趨ѡ���¼�
				RadioButton_A.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						//����š������е���Ŀ��š��˽������ȷ�𰸴������ݿ�result����
 						//���Ѵ��ڽ������£��������򴴽�
						if(myanswer[0]=="A") {
							myanswer[0]=null;
						}else {
							myanswer[0]="A";
						}
						String MyAnswer=StringUtils.join(myanswer);
						if(cnt<=QuestionNo)
							textField_ShowQuestionNo.setText("��"+cnt+"��/��"+args[1]+"��");
						if(!MultipleChoiceExamJframe.isExist(User.qbName+User.userID+String.valueOf(array[j-1]))) {//�����ڣ�����������
							String sql="insert into result (QuestionNo,QuestionID,QuestionStem,A,B,C,D,Answer,MyAnswer,UserID) values(?,?,?,?,?,?,?,?,?,?)";
							String[] str=new String[]{String.valueOf(j), User.qbName+User.userID+String.valueOf(array[j-1]), stem, A, B, C, D, answer,MyAnswer,User.userID};
							exam.AddOrUpdate(sql, str);
							System.out.println("��"+j+"��ѡ��"+MyAnswer);
						}
						else {//�Ѵ��ڣ���������
							String sql="update result set MyAnswer=? where QuestionID=?";
							String[] str=new String[]{MyAnswer,User.qbName+User.userID+String.valueOf(array[j-1])};
							exam.AddOrUpdate(sql, str);
							System.out.println("��"+j+"�����Ϊ��"+MyAnswer);	
						}			
					}
				});
				
				RadioButton_B.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if(myanswer[1]=="B") {
							myanswer[1]=null;
						}else {
							myanswer[1]="B";
						}
						String MyAnswer=StringUtils.join(myanswer);
						if(cnt<=QuestionNo)
							textField_ShowQuestionNo.setText("��"+cnt+"��/��"+args[1]+"��");
						if(!MultipleChoiceExamJframe.isExist(User.qbName+User.userID+String.valueOf(array[j-1]))) {//�����ڣ�����������
							String sql="insert into result (QuestionNo,QuestionID,QuestionStem,A,B,C,D,Answer,MyAnswer,UserID) values(?,?,?,?,?,?,?,?,?,?)";
							String[] str=new String[]{String.valueOf(j), User.qbName+User.userID+String.valueOf(array[j-1]), stem, A, B, C, D, answer,MyAnswer,User.userID};
							exam.AddOrUpdate(sql, str);
							System.out.println("��"+j+"��ѡ��"+MyAnswer);	
						}
						else {//�Ѵ��ڣ���������
							String sql="update result set MyAnswer=? where QuestionID=?";
							String[] str=new String[]{MyAnswer,User.qbName+User.userID+String.valueOf(array[j-1])};
							exam.AddOrUpdate(sql, str);
							System.out.println("��"+j+"�����Ϊ��"+MyAnswer);	
						}
					}
				});
				
				RadioButton_C.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if(myanswer[2]=="C") {
							myanswer[2]=null;
						}else {
							myanswer[2]="C";
						}
						String MyAnswer=StringUtils.join(myanswer);
						if(cnt<=QuestionNo)
							textField_ShowQuestionNo.setText("��"+cnt+"��/��"+args[1]+"��");
						if(!MultipleChoiceExamJframe.isExist(User.qbName+User.userID+String.valueOf(array[j-1]))) {//�����ڣ�����������
							String sql="insert into result (QuestionNo,QuestionID,QuestionStem,A,B,C,D,Answer,MyAnswer,UserID) values(?,?,?,?,?,?,?,?,?,?)";
							String[] str=new String[]{String.valueOf(j), User.qbName+User.userID+String.valueOf(array[j-1]), stem, A, B, C, D, answer,MyAnswer,User.userID};
							exam.AddOrUpdate(sql, str);
							System.out.println("��"+j+"��ѡ��"+MyAnswer);	
						}
						else {//�Ѵ��ڣ���������
							String sql="update result set MyAnswer=? where QuestionID=?";
							String[] str=new String[]{MyAnswer,User.qbName+User.userID+String.valueOf(array[j-1])};
							exam.AddOrUpdate(sql, str);
							System.out.println("��"+j+"�����Ϊ��"+MyAnswer);	
						}				
					}
				});
				
				RadioButton_D.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if(myanswer[3]=="D") {
							myanswer[3]=null;
						}else {
							myanswer[3]="D";
						}
						String MyAnswer=StringUtils.join(myanswer);
						if(cnt<=QuestionNo)
							textField_ShowQuestionNo.setText("��"+cnt+"��/��"+args[1]+"��");
						if(!MultipleChoiceExamJframe.isExist(User.qbName+User.userID+String.valueOf(array[j-1]))) {//�����ڣ�����������
							String sql="insert into result (QuestionNo,QuestionID,QuestionStem,A,B,C,D,Answer,MyAnswer,UserID) values(?,?,?,?,?,?,?,?,?,?)";
							String[] str=new String[]{String.valueOf(j), User.qbName+User.userID+String.valueOf(array[j-1]), stem, A, B, C, D, answer,MyAnswer,User.userID};
							exam.AddOrUpdate(sql, str);
							System.out.println("��"+j+"��ѡ��"+MyAnswer);	
						}
						else {//�Ѵ��ڣ���������
							String sql="update result set MyAnswer=? where QuestionID=?";
							String[] str=new String[]{MyAnswer,User.qbName+User.userID+String.valueOf(array[j-1])};
							exam.AddOrUpdate(sql, str);
							System.out.println("��"+j+"�����Ϊ��"+MyAnswer);	
						}				
					}
				});
				
				
				//ͨ��ѭ��,�������ӵ�CardLayout�����ſ�Ƭ��
				p.add(Label_QuestionStem);
				p.add(Label_A);
				p.add(Label_B);
				p.add(Label_C);
				p.add(Label_D);
				p.add(RadioButton_A);
				p.add(RadioButton_B);
				p.add(RadioButton_C);
				p.add(RadioButton_D);
				
				//����Щ��Ƭ��ӵ��в������
				panel_mid.add(p);
			}
		}

			
		
		
		
		
		//�����
				JPanel panel_left = new JPanel();
				frame.getContentPane().add(panel_left, BorderLayout.WEST);
				panel_left.setLayout(new GridLayout(0, 1, 0, 0));
				
				JButton button_LastQuestion = new JButton("\u4E0A\u4E00\u9898");
				button_LastQuestion.setFont(new Font("����", Font.PLAIN, 24));
				panel_left.add(button_LastQuestion);
				button_LastQuestion.addActionListener(new ActionListener()
				{
				public void actionPerformed(ActionEvent e)
				{
					cardlayout.previous(panel_mid);
				}
		  
			  });	
				
				
				
				//�����
				JPanel panel_right = new JPanel();
				frame.getContentPane().add(panel_right, BorderLayout.EAST);
				panel_right.setLayout(new GridLayout(0, 1, 0, 0));
				
				JButton button_NextQuestion = new JButton("\u4E0B\u4E00\u9898");
				button_NextQuestion.setFont(new Font("����", Font.PLAIN, 24));
				panel_right.add(button_NextQuestion);
				button_NextQuestion.addActionListener(new ActionListener()
				{
				public void actionPerformed(ActionEvent e)
				{
					cardlayout.next(panel_mid);
				}
		  
			  });
				
				
				//�ײ����
				JPanel panel_bottom = new JPanel();
				frame.getContentPane().add(panel_bottom, BorderLayout.SOUTH);
				
				JButton button_Submit = new JButton("\u63D0\u4EA4");
				button_Submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
					}
				});
				button_Submit.setFont(new Font("����", Font.PLAIN, 30));
				panel_bottom.add(button_Submit);
		
		
		
	}
	
	public static boolean isExist(String id) {//��֤������û������
        try {
        	DBUtil db = new DBUtil();
            ResultSet rs = db.Search("select * from result where QuestionID=?",
                    new String[] { id + "" });
            if (rs.next()) {
                return true;//����������
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;//����������
    }
}

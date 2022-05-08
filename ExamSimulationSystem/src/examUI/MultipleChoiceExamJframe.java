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

public class MultipleChoiceExamJframe {//考试窗口
//答题结果存储在result表中，因此每次答题之前需要先删除result表中的所有内容
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
		//使显示在屏幕中央
		java.awt.Toolkit toolkit=java.awt.Toolkit.getDefaultToolkit();
		Dimension screenSize=toolkit.getScreenSize();
		double screenWidth=screenSize.getWidth();
		double screenHeight=screenSize.getHeight();
		frame.setSize(1125,624);
		frame.setLocation((int)(screenWidth-frame.getWidth())/2,(int)(screenHeight-frame.getHeight())/2);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		//上部面板
		JPanel panel_top = new JPanel();
		frame.getContentPane().add(panel_top, BorderLayout.NORTH);
		panel_top.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel Label_ShowQuestionNo = new JLabel("\u5F53\u524D\u8003\u8BD5\u8FDB\u5EA6\uFF1A");
		Label_ShowQuestionNo.setFont(new Font("宋体", Font.PLAIN, 30));
		panel_top.add(Label_ShowQuestionNo);
		
		textField_ShowQuestionNo = new JTextField();
		textField_ShowQuestionNo.setEditable(false);
		textField_ShowQuestionNo.setFont(new Font("宋体", Font.PLAIN, 30));
		panel_top.add(textField_ShowQuestionNo);
		textField_ShowQuestionNo.setColumns(20);
		int count=0;
		textField_ShowQuestionNo.setText("第"+count+"题/共"+args[1]+"题");
		
		
		
		//中部面板
		JPanel panel_mid = new JPanel();
		frame.getContentPane().add(panel_mid, BorderLayout.CENTER);
		CardLayout cardlayout = new CardLayout();
		panel_mid.setLayout(cardlayout);

		
		//删除result表
		DBUtil exam=new DBUtil();
		String sql="TRUNCATE Table result";
		exam.AddOrUpdate(sql, null);
        System.out.println("删除dbo.result成功");
		
		
		String seq = args[0];
		String num = args[1];
		System.out.print("答题顺序为：");
		if(seq.equals("0"))
			System.out.println("顺序答题");
		else
			System.out.println("随机答题");
		System.out.println("本次考试的题目数量为："+num);
		int QuestionSeq = Integer.parseInt(seq);//答题顺序，从传入参数中获得
		int QuestionNo = Integer.parseInt(num);//题目数量，从传入参数中获得
/*----------------------------------------------------------------------------------------------------*/		
		if(QuestionSeq==0)//若是顺序答题
			for(int i = 1 ; i <= QuestionNo ; i++) {
				int j=i;//考试过程中的题号
				JPanel p = new JPanel();
				p.setLayout(null);
										
				
				String content_stem=null;
				sql="select QuestionStem from question where QuestionID = ?";
	            String[] str=new String[]{User.qbName+User.userID+String.valueOf(i)};
	            ResultSet rs = null;
	            rs=exam.Search(sql, str);
	            //从数据库中获得查询结果
	            try {
	            	 while(rs.next()) {
	                 	 //System.out.println(rs.getString(1));
	            		 content_stem=rs.getString(1);//只查到一行数据，获取第一行
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
	            //题干的标签
	            //System.out.println(s);
	            Label_QuestionStem = new JLabel("\u9898\u5E72");
				String content="<html>"+i+"."+content_stem+"<html>";//html标签的作用是实现自动换行
				Label_QuestionStem.setText(content);
	            Label_QuestionStem.setFont(new Font("宋体", Font.PLAIN, 20));
				Label_QuestionStem.setBounds(29, 25, 850, 150);
				//select QuestionStem from question where QuestionID = i

	            
	            
	            
	            //四个选项的标签
				
				String content_A=null;
				sql="select A from question where QuestionID = ?";
	            rs = null;
	            rs=exam.Search(sql, str);
	            //从数据库中获得查询结果
	            try {
	            	 while(rs.next()) {
	            		 content_A=rs.getString(1);//只查到一行数据，获取第一行
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
				Label_A.setFont(new Font("宋体", Font.PLAIN, 20));
				Label_A.setText("A."+content_A);
				Label_A.setBounds(29, 175, 3000, 24);
				
				String content_B=null;
				sql="select B from question where QuestionID = ?";
	            rs = null;
	            rs=exam.Search(sql, str);
	            //从数据库中获得查询结果
	            try {
	            	 while(rs.next()) {
	            		 content_B=rs.getString(1);//只查到一行数据，获取第一行
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
				Label_B.setFont(new Font("宋体", Font.PLAIN, 20));
				Label_B.setText("B."+content_B);
				Label_B.setBounds(29, 225, 3000, 24);
				
				String content_C=null;
				sql="select C from question where QuestionID = ?";
	            rs = null;
	            rs=exam.Search(sql, str);
	            //从数据库中获得查询结果
	            try {
	            	 while(rs.next()) {
	            		 content_C=rs.getString(1);//只查到一行数据，获取第一行
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
				Label_C.setFont(new Font("宋体", Font.PLAIN, 20));
				Label_C.setText("C."+content_C);
				Label_C.setBounds(29, 275, 3000, 24);
				
				String content_D=null;
				sql="select D from question where QuestionID = ?";
	            rs = null;
	            rs=exam.Search(sql, str);
	            //从数据库中获得查询结果
	            try {
	            	 while(rs.next()) {
	            		 content_D=rs.getString(1);//只查到一行数据，获取第一行
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
				Label_D.setFont(new Font("宋体", Font.PLAIN, 20));
				Label_D.setText("D."+content_D);
				Label_D.setBounds(29, 325, 3000, 24);
				
				//四个选项的单选按钮
				JRadioButton RadioButton_A = new JRadioButton("");
				RadioButton_A.setBounds(850, 175, 121, 23);
				
				JRadioButton RadioButton_B = new JRadioButton("");
				RadioButton_B.setBounds(850, 225, 121, 23);
				
				JRadioButton RadioButton_C = new JRadioButton("");
				RadioButton_C.setBounds(850, 275, 121, 23);
				
				JRadioButton RadioButton_D = new JRadioButton("");
				RadioButton_D.setBounds(850, 325, 121, 23);
				
					
				//从数据库中获得正确答案
				String content_answer=null;
				sql="select Answer from question where QuestionID = ?";
	            rs = null;
	            rs=exam.Search(sql, str);
	            //从数据库中获得查询结果
	            try {
	            	 while(rs.next()) {
	            		 content_answer=rs.getString(1);//只查到一行数据，获取第一行
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
	            int cnt=count+i;//统计答题的数量
	            
				//为四个单选按钮设定选择事件
				RadioButton_A.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						//将题号、考试中的题目序号、此结果和正确答案存入数据库result表中 题号为参数i
 						//若已存在结果则更新，不存在则创建	
						if(myanswer[0]=="A") {
							myanswer[0]=null;
						}else {
							myanswer[0]="A";
						}
			            String MyAnswer=StringUtils.join(myanswer);
						if(cnt<=QuestionNo)
							textField_ShowQuestionNo.setText("第"+cnt+"题/共"+args[1]+"题");//选择了一个选项，答题数+1
						if(!MultipleChoiceExamJframe.isExist(User.qbName+User.userID+String.valueOf(j))) {//不存在，插入新数据
							String sql="insert into result (QuestionNo,QuestionID,QuestionStem,A,B,C,D,Answer,MyAnswer,UserID) values(?,?,?,?,?,?,?,?,?,?)";
							String[] str=new String[]{String.valueOf(j), User.qbName+User.userID+String.valueOf(j), stem, A, B, C, D, answer,MyAnswer,User.userID};
							exam.AddOrUpdate(sql, str);
							System.out.println("第"+j+"题选："+MyAnswer);	
						}
						else {//已存在，更改数据
							String sql="update result set MyAnswer=? where QuestionID=?";
							String[] str=new String[]{MyAnswer,User.qbName+User.userID+String.valueOf(j)};
							exam.AddOrUpdate(sql, str);
							System.out.println("第"+j+"题更改为："+MyAnswer);	
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
							textField_ShowQuestionNo.setText("第"+cnt+"题/共"+args[1]+"题");
						if(!MultipleChoiceExamJframe.isExist(User.qbName+User.userID+String.valueOf(j))) {//不存在，插入新数据
							String sql="insert into result (QuestionNo,QuestionID,QuestionStem,A,B,C,D,Answer,MyAnswer,UserID) values(?,?,?,?,?,?,?,?,?,?)";
							String[] str=new String[]{String.valueOf(j), User.qbName+User.userID+String.valueOf(j), stem, A, B, C, D, answer,MyAnswer,User.userID};
							exam.AddOrUpdate(sql, str);
							System.out.println("第"+j+"题选："+MyAnswer);	
						}
						else {//已存在，更改数据
							String sql="update result set MyAnswer=? where QuestionID=?";
							String[] str=new String[]{MyAnswer,User.qbName+User.userID+String.valueOf(j)};
							exam.AddOrUpdate(sql, str);
							System.out.println("第"+j+"题更改为："+MyAnswer);	
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
							textField_ShowQuestionNo.setText("第"+cnt+"题/共"+args[1]+"题");
						if(!MultipleChoiceExamJframe.isExist(User.qbName+User.userID+String.valueOf(j))) {//不存在，插入新数据
							String sql="insert into result (QuestionNo,QuestionID,QuestionStem,A,B,C,D,Answer,MyAnswer,UserID) values(?,?,?,?,?,?,?,?,?,?)";
							String[] str=new String[]{String.valueOf(j), User.qbName+User.userID+String.valueOf(j), stem, A, B, C, D, answer,MyAnswer,User.userID};
							exam.AddOrUpdate(sql, str);
							System.out.println("第"+j+"题选："+MyAnswer);	
						}
						else {//已存在，更改数据
							String sql="update result set MyAnswer=? where QuestionID=?";
							String[] str=new String[]{MyAnswer,User.qbName+User.userID+String.valueOf(j)};
							exam.AddOrUpdate(sql, str);
							System.out.println("第"+j+"题更改为："+MyAnswer);	
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
						textField_ShowQuestionNo.setText("第"+cnt+"题/共"+args[1]+"题");
						if(!MultipleChoiceExamJframe.isExist(User.qbName+User.userID+String.valueOf(j))) {//不存在，插入新数据
							String sql="insert into result (QuestionNo,QuestionID,QuestionStem,A,B,C,D,Answer,MyAnswer,UserID) values(?,?,?,?,?,?,?,?,?,?)";
							String[] str=new String[]{String.valueOf(j), User.qbName+User.userID+String.valueOf(j), stem, A, B, C, D, answer,MyAnswer,User.userID};
							exam.AddOrUpdate(sql, str);
							System.out.println("第"+j+"题选："+MyAnswer);	
						}
						else {//已存在，更改数据
							String sql="update result set MyAnswer=? where QuestionID=?";
							String[] str=new String[]{MyAnswer,User.qbName+User.userID+String.valueOf(j)};
							exam.AddOrUpdate(sql, str);
							System.out.println("第"+j+"题更改为："+MyAnswer);	
						}				
					}
				});

					
				
				//通过循环,将组件添加到CardLayout的数张卡片中
				p.add(Label_QuestionStem);
				p.add(Label_A);
				p.add(Label_B);
				p.add(Label_C);
				p.add(Label_D);
				p.add(RadioButton_A);
				p.add(RadioButton_B);
				p.add(RadioButton_C);
				p.add(RadioButton_D);
				
				//将这些卡片添加到中部面板中
				panel_mid.add(p);
			}
/*----------------------------------------------------------------------------------------------------*/
		else//随机答题
		{//首先需要获取题库中有多少道题，以及本次考试的题量，然后调用RandomNum中的方法生成代表题号的随机数组
			int QuestionNoInQB=0;//题库中的题目数量
			
			sql="select count(*) from  question where owner=? and QuestionBankName=? ";
			String[] str= {User.userName,User.qbName};
            ResultSet rs = null;
            rs=exam.Search(sql, str);//sql语句中无参数，因此此处为null
            //从数据库中获得题库中的题目数量
            try {
            	 while(rs.next()) {
                 	 //System.out.println("题库中的题目数量为："+rs.getInt(1));
                 	 QuestionNoInQB=rs.getInt(1);//只查到一行数据，获取第一行
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
    		arr=r.getRand(QuestionNoInQB,QuestionNo);//这样生成的随机数范围为0-（QuestionNoInQB-1），不符合要求，需要全部+1
    		
    		System.out.print("选择的questionID为：");
    		for(int i=0;i<QuestionNo;i++) {
    			arr[i]++;
    			System.out.print(" "+arr[i]);
    		}
    		
    		
    		int[]  array=arr;
			
			for(int i = 0 ; i < QuestionNo ; i++) {
				int j=i+1;//考试过程中的题号
				JPanel p = new JPanel();
				p.setLayout(null);
				
				
				String content_stem=null;
				sql="select QuestionStem from question where QuestionID = ?";
	            str=new String[]{User.qbName+User.userID+String.valueOf(arr[i])};//查询第（随机数）题号的数据内容
	            rs = null;
	            rs=exam.Search(sql, str);
	            //从数据库中获得查询结果
	            try {
	            	 while(rs.next()) {
	                 	 //System.out.println(rs.getString(1));
	            		 content_stem=rs.getString(1);//只查到一行数据，获取第一行
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
	            //题干的标签
	            //System.out.println(s);
	            Label_QuestionStem = new JLabel("\u9898\u5E72");
				String content="<html>"+(i+1)+"."+content_stem+"<html>";//html标签的作用是实现自动换行
				Label_QuestionStem.setText(content);
	            Label_QuestionStem.setFont(new Font("宋体", Font.PLAIN, 20));
				Label_QuestionStem.setBounds(29, 25, 850, 150);
				//select QuestionStem from question where QuestionID = i

	            
	            
	            
	            //两个选项的标签
				
				String content_A=null;
				sql="select A from question where QuestionID = ?";
	            rs = null;
	            rs=exam.Search(sql, str);
	            //从数据库中获得查询结果
	            try {
	            	 while(rs.next()) {
	            		 content_A=rs.getString(1);//只查到一行数据，获取第一行
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
				Label_A.setFont(new Font("宋体", Font.PLAIN, 20));
				Label_A.setText("A."+content_A);
				Label_A.setBounds(29, 175, 3000, 24);
				
				String content_B=null;
				sql="select B from question where QuestionID = ?";
	            rs = null;
	            rs=exam.Search(sql, str);
	            //从数据库中获得查询结果
	            try {
	            	 while(rs.next()) {
	            		 content_B=rs.getString(1);//只查到一行数据，获取第一行
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
				Label_B.setFont(new Font("宋体", Font.PLAIN, 20));
				Label_B.setText("B."+content_B);
				Label_B.setBounds(29, 225, 3000, 24);
				
				String content_C=null;
				sql="select C from question where QuestionID = ?";
	            rs = null;
	            rs=exam.Search(sql, str);
	            //从数据库中获得查询结果
	            try {
	            	 while(rs.next()) {
	            		 content_C=rs.getString(1);//只查到一行数据，获取第一行
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
				Label_C.setFont(new Font("宋体", Font.PLAIN, 20));
				Label_C.setText("C."+content_C);
				Label_C.setBounds(29, 275, 3000, 24);
				
				String content_D=null;
				sql="select D from question where QuestionID = ?";
	            rs = null;
	            rs=exam.Search(sql, str);
	            //从数据库中获得查询结果
	            try {
	            	 while(rs.next()) {
	            		 content_D=rs.getString(1);//只查到一行数据，获取第一行
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
				Label_D.setFont(new Font("宋体", Font.PLAIN, 20));
				Label_D.setText("D."+content_D);
				Label_D.setBounds(29, 325, 3000, 24);
				
				//四个选项的单选按钮
				JRadioButton RadioButton_A = new JRadioButton("");
				RadioButton_A.setBounds(850, 175, 121, 23);
				
				JRadioButton RadioButton_B = new JRadioButton("");
				RadioButton_B.setBounds(850, 225, 121, 23);
				
				JRadioButton RadioButton_C = new JRadioButton("");
				RadioButton_C.setBounds(850, 275, 121, 23);
				
				JRadioButton RadioButton_D = new JRadioButton("");
				RadioButton_D.setBounds(850, 325, 121, 23);
				
				
				//从数据库中获得正确答案
				String content_answer=null;
				sql="select Answer from question where QuestionID = ?";
	            rs = null;
	            rs=exam.Search(sql, str);
	            //从数据库中获得查询结果
	            try {
	            	 while(rs.next()) {
	            		 content_answer=rs.getString(1);//只查到一行数据，获取第一行
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
				
	            int cnt=count+i+1;//统计答题的数量
	            
				//为四个单选按钮设定选择事件
				RadioButton_A.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						//将题号、考试中的题目序号、此结果和正确答案存入数据库result表中
 						//若已存在结果则更新，不存在则创建
						if(myanswer[0]=="A") {
							myanswer[0]=null;
						}else {
							myanswer[0]="A";
						}
						String MyAnswer=StringUtils.join(myanswer);
						if(cnt<=QuestionNo)
							textField_ShowQuestionNo.setText("第"+cnt+"题/共"+args[1]+"题");
						if(!MultipleChoiceExamJframe.isExist(User.qbName+User.userID+String.valueOf(array[j-1]))) {//不存在，插入新数据
							String sql="insert into result (QuestionNo,QuestionID,QuestionStem,A,B,C,D,Answer,MyAnswer,UserID) values(?,?,?,?,?,?,?,?,?,?)";
							String[] str=new String[]{String.valueOf(j), User.qbName+User.userID+String.valueOf(array[j-1]), stem, A, B, C, D, answer,MyAnswer,User.userID};
							exam.AddOrUpdate(sql, str);
							System.out.println("第"+j+"题选："+MyAnswer);
						}
						else {//已存在，更改数据
							String sql="update result set MyAnswer=? where QuestionID=?";
							String[] str=new String[]{MyAnswer,User.qbName+User.userID+String.valueOf(array[j-1])};
							exam.AddOrUpdate(sql, str);
							System.out.println("第"+j+"题更改为："+MyAnswer);	
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
							textField_ShowQuestionNo.setText("第"+cnt+"题/共"+args[1]+"题");
						if(!MultipleChoiceExamJframe.isExist(User.qbName+User.userID+String.valueOf(array[j-1]))) {//不存在，插入新数据
							String sql="insert into result (QuestionNo,QuestionID,QuestionStem,A,B,C,D,Answer,MyAnswer,UserID) values(?,?,?,?,?,?,?,?,?,?)";
							String[] str=new String[]{String.valueOf(j), User.qbName+User.userID+String.valueOf(array[j-1]), stem, A, B, C, D, answer,MyAnswer,User.userID};
							exam.AddOrUpdate(sql, str);
							System.out.println("第"+j+"题选："+MyAnswer);	
						}
						else {//已存在，更改数据
							String sql="update result set MyAnswer=? where QuestionID=?";
							String[] str=new String[]{MyAnswer,User.qbName+User.userID+String.valueOf(array[j-1])};
							exam.AddOrUpdate(sql, str);
							System.out.println("第"+j+"题更改为："+MyAnswer);	
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
							textField_ShowQuestionNo.setText("第"+cnt+"题/共"+args[1]+"题");
						if(!MultipleChoiceExamJframe.isExist(User.qbName+User.userID+String.valueOf(array[j-1]))) {//不存在，插入新数据
							String sql="insert into result (QuestionNo,QuestionID,QuestionStem,A,B,C,D,Answer,MyAnswer,UserID) values(?,?,?,?,?,?,?,?,?,?)";
							String[] str=new String[]{String.valueOf(j), User.qbName+User.userID+String.valueOf(array[j-1]), stem, A, B, C, D, answer,MyAnswer,User.userID};
							exam.AddOrUpdate(sql, str);
							System.out.println("第"+j+"题选："+MyAnswer);	
						}
						else {//已存在，更改数据
							String sql="update result set MyAnswer=? where QuestionID=?";
							String[] str=new String[]{MyAnswer,User.qbName+User.userID+String.valueOf(array[j-1])};
							exam.AddOrUpdate(sql, str);
							System.out.println("第"+j+"题更改为："+MyAnswer);	
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
							textField_ShowQuestionNo.setText("第"+cnt+"题/共"+args[1]+"题");
						if(!MultipleChoiceExamJframe.isExist(User.qbName+User.userID+String.valueOf(array[j-1]))) {//不存在，插入新数据
							String sql="insert into result (QuestionNo,QuestionID,QuestionStem,A,B,C,D,Answer,MyAnswer,UserID) values(?,?,?,?,?,?,?,?,?,?)";
							String[] str=new String[]{String.valueOf(j), User.qbName+User.userID+String.valueOf(array[j-1]), stem, A, B, C, D, answer,MyAnswer,User.userID};
							exam.AddOrUpdate(sql, str);
							System.out.println("第"+j+"题选："+MyAnswer);	
						}
						else {//已存在，更改数据
							String sql="update result set MyAnswer=? where QuestionID=?";
							String[] str=new String[]{MyAnswer,User.qbName+User.userID+String.valueOf(array[j-1])};
							exam.AddOrUpdate(sql, str);
							System.out.println("第"+j+"题更改为："+MyAnswer);	
						}				
					}
				});
				
				
				//通过循环,将组件添加到CardLayout的数张卡片中
				p.add(Label_QuestionStem);
				p.add(Label_A);
				p.add(Label_B);
				p.add(Label_C);
				p.add(Label_D);
				p.add(RadioButton_A);
				p.add(RadioButton_B);
				p.add(RadioButton_C);
				p.add(RadioButton_D);
				
				//将这些卡片添加到中部面板中
				panel_mid.add(p);
			}
		}

			
		
		
		
		
		//左面板
				JPanel panel_left = new JPanel();
				frame.getContentPane().add(panel_left, BorderLayout.WEST);
				panel_left.setLayout(new GridLayout(0, 1, 0, 0));
				
				JButton button_LastQuestion = new JButton("\u4E0A\u4E00\u9898");
				button_LastQuestion.setFont(new Font("宋体", Font.PLAIN, 24));
				panel_left.add(button_LastQuestion);
				button_LastQuestion.addActionListener(new ActionListener()
				{
				public void actionPerformed(ActionEvent e)
				{
					cardlayout.previous(panel_mid);
				}
		  
			  });	
				
				
				
				//右面板
				JPanel panel_right = new JPanel();
				frame.getContentPane().add(panel_right, BorderLayout.EAST);
				panel_right.setLayout(new GridLayout(0, 1, 0, 0));
				
				JButton button_NextQuestion = new JButton("\u4E0B\u4E00\u9898");
				button_NextQuestion.setFont(new Font("宋体", Font.PLAIN, 24));
				panel_right.add(button_NextQuestion);
				button_NextQuestion.addActionListener(new ActionListener()
				{
				public void actionPerformed(ActionEvent e)
				{
					cardlayout.next(panel_mid);
				}
		  
			  });
				
				
				//底部面板
				JPanel panel_bottom = new JPanel();
				frame.getContentPane().add(panel_bottom, BorderLayout.SOUTH);
				
				JButton button_Submit = new JButton("\u63D0\u4EA4");
				button_Submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
					}
				});
				button_Submit.setFont(new Font("宋体", Font.PLAIN, 30));
				panel_bottom.add(button_Submit);
		
		
		
	}
	
	public static boolean isExist(String id) {//验证表中有没有数据
        try {
        	DBUtil db = new DBUtil();
            ResultSet rs = db.Search("select * from result where QuestionID=?",
                    new String[] { id + "" });
            if (rs.next()) {
                return true;//表中有数据
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;//表中无数据
    }
}

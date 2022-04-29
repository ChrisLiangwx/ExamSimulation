package resultUI;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import JDBC.DBUtil;
import user.User;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import java.awt.Color;

public class ResultJframe {

	private JFrame frame;
	private JTextField JTextField_Accuracy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultJframe window = new ResultJframe();
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
	public ResultJframe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u8003\u8BD5\u7ED3\u679C");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//使窗口显示在屏幕中央
		java.awt.Toolkit toolkit=java.awt.Toolkit.getDefaultToolkit();
		Dimension screenSize=toolkit.getScreenSize();
		double screenWidth=screenSize.getWidth();
		double screenHeight=screenSize.getHeight();
		frame.setSize(800,620);
		frame.setLocation((int)(screenWidth-frame.getWidth())/2,(int)(screenHeight-frame.getHeight())/2);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		//创建两个jpanel，分别位于窗口的上下部分
		JPanel panel_top = new JPanel();
		
		JPanel panel_bottom = new JPanel();
		JScrollPane jscrollpane_bottom = new JScrollPane();
		jscrollpane_bottom.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jscrollpane_bottom.setViewportView(panel_bottom);
		
		JLabel label_WrongAnswers = new JLabel("\u9519\u9898");
		label_WrongAnswers.setForeground(Color.RED);
		label_WrongAnswers.setFont(new Font("宋体", Font.PLAIN, 24));
		panel_bottom.add(label_WrongAnswers);
		
		JTextArea JTextArea_Content = new JTextArea();
		JTextArea_Content.setFont(new Font("Monospaced", Font.PLAIN, 16));
		panel_bottom.add(JTextArea_Content);
		
		//分割窗口
		JSplitPane jsplitpane_main = new JSplitPane(0, panel_top, jscrollpane_bottom);
		panel_top.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel JLabel_Accuracy = new JLabel("\u7B54\u9898\u6B63\u786E\u7387");
		JLabel_Accuracy.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel_Accuracy.setFont(new Font("宋体", Font.PLAIN, 30));
		panel_top.add(JLabel_Accuracy);
		
		JTextField_Accuracy = new JTextField();
		JTextField_Accuracy.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField_Accuracy.setFont(new Font("宋体", Font.PLAIN, 30));
		panel_top.add(JTextField_Accuracy);
		JTextField_Accuracy.setColumns(10);
		jsplitpane_main.setDividerSize(1);//设置分割线的宽度
		jsplitpane_main.setDividerLocation(100);//设置分割线的位置
		frame.getContentPane().add(jsplitpane_main);
		
		
		//将考试结果显示在下部jpanel中
		//分别查询result表中的题号，正确答案和我的答案
		//先查询题目数量
		DBUtil exam=new DBUtil();
		int QuestionNum=0;//result中的题目数量
		String sql="select count(*) from  result";
        ResultSet rs = null;
        rs=exam.Search(sql, null);//sql语句中无参数，因此此处为null
        //从数据库中获得result中的题目数量
        try {
        	 while(rs.next()) {
             	 //System.out.println("题库中的题目数量为："+rs.getInt(1));
        		 QuestionNum=rs.getInt(1);//只查到一行数据，获取第一行
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
        System.out.println("result中的题目数量为"+QuestionNum);
		
		//创建数组分别存储题号，我的答案和正确答案
        String QuestionID[]=new String [QuestionNum];
        String QuestionNo[]=new String [QuestionNum];
        String MyAnswer[]=new String [QuestionNum];
        String Answer[]=new String [QuestionNum];
        
        //从数据库中查询题目ID
        int i=0;
		sql="select QuestionID from result";
        rs = null;
        rs=exam.Search(sql, null);
        try {
        	 while(rs.next()) {
        		 QuestionID[i]=rs.getString(1);
        		 i++;
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
        
        //从数据库中查询题号
        i=0;
		sql="select QuestionNo from result";
        rs = null;
        rs=exam.Search(sql, null);
        try {
        	 while(rs.next()) {
        		 QuestionNo[i]=rs.getString(1);
        		 i++;
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
        
        
        //从数据库中查询我的答案 
        i=0;
		sql="select MyAnswer from result";
        rs = null;
        rs=exam.Search(sql, null);
        try {
        	 while(rs.next()) {
        		 MyAnswer[i]=rs.getString(1);
        		 i++;
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
        
        //从数据库中查询正确答案 
        i=0;
		sql="select Answer from result";
        rs = null;
        rs=exam.Search(sql, null);
        try {
        	 while(rs.next()) {
        		 Answer[i]=rs.getString(1);
        		 i++;
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
        
 
        int count=0;//统计错题数量
        String content[]=new String[QuestionNum];
        for(i=0;i<QuestionNum;i++) {
        	sql="select QuestionStem,A,B,C,D from result where QuestionID = ?";
        	String[] str=new String[]{QuestionID[i]};//查询第（随机数）题号的数据内容
            rs = null;
            rs=exam.Search(sql, str);
            
            try {
            	 while(rs.next()) {
            		 content[i]=rs.getString(1)+"\nA."+rs.getString(2)+"\nB."+rs.getString(3)+"\nC."+rs.getString(4)+"\nD."+rs.getString(5);
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
        	if(Answer[i].equals(MyAnswer[i]))
        		count++;
        	
        	System.out.print(i+".");
        	System.out.println(content[i]);
        	System.out.print("正确答案"+Answer[i]);
        	System.out.println("我的答案"+MyAnswer[i]);
        }
        
        JTextField_Accuracy.setText(count+"/"+QuestionNum);
        JTextField_Accuracy.setEditable(false);
        
        for(i=0;i<QuestionNum;i++) {
        	if(!Answer[i].equals(MyAnswer[i]))
        	JTextArea_Content.setText(JTextArea_Content.getText()+QuestionNo[i]+"."+content[i]+"\n正确答案："+Answer[i]+" 我的答案："+MyAnswer[i]+"\n");
        }
        	
        
	}

}

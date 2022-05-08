package examUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import user.User;

import javax.swing.JButton;
import javax.swing.ComboBoxModel;

public class ExamOptionJframe {//考试选项窗口

	private JFrame frame;
	private JTextField JTextField_QuestionNum;
	private JLabel Label_QuestionSeq;
	private JButton button_Return;
	private JButton button_Start;
	ActionListener listener1;//listener1:点击开始按钮时打开考试窗口

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExamOptionJframe window = new ExamOptionJframe();
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
	public ExamOptionJframe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u8003\u8BD5\u9009\u9879");

		//使显示在屏幕中央
		java.awt.Toolkit toolkit=java.awt.Toolkit.getDefaultToolkit();
		Dimension screenSize=toolkit.getScreenSize();
		double screenWidth=screenSize.getWidth();
		double screenHeight=screenSize.getHeight();
		

		frame.setLocation((int)(screenWidth-frame.getWidth())/2,(int)(screenHeight-frame.getHeight())/2);
		frame.setSize(300,150);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(3, 2, 0, 0));
		
		//第一行
		//答题顺序标签
		Label_QuestionSeq = new JLabel("\u7B54\u9898\u987A\u5E8F");
		Label_QuestionSeq.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(Label_QuestionSeq);
		
		//答题顺序下拉列表
		String[] questionSeqList = new String[]{"顺序答题", "随机答题"};
		DefaultComboBoxModel<String> questionSeqModel  = new DefaultComboBoxModel<String>(questionSeqList);
		JComboBox JComboBox_QuestionSeq = new JComboBox(questionSeqModel);//这种写法能解决Windowbuilder不支持泛型的问题
		JComboBox_QuestionSeq.setSelectedIndex(0);//设置默认选项
		frame.getContentPane().add(JComboBox_QuestionSeq);
		//System.out.println("选中: " + JComboBox_QuestionSeq.getSelectedIndex() + " = " + JComboBox_QuestionSeq.getSelectedItem());
                

		
		//第二行
		//题目数量标签
		//题目数量文本框
		JLabel Label_QuestionNum = new JLabel("\u9898\u76EE\u6570\u91CF");
		Label_QuestionNum.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(Label_QuestionNum);
		
		JTextField_QuestionNum = new JTextField();
		frame.getContentPane().add(JTextField_QuestionNum);
		JTextField_QuestionNum.setColumns(10);
		
		
		String[] questionTypeList = new String[]{"单选", "多选","判断"};
		DefaultComboBoxModel<String> questionTypeModel  = new DefaultComboBoxModel<String>(questionTypeList);
		
		
		//第三行
		//返回按钮
		button_Return = new JButton("\u8FD4\u56DE");
		frame.getContentPane().add(button_Return);
		button_Return.addActionListener(new ActionListener()
		  {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				frame.dispose();//关闭一个GUI界面
				
			}
	  
		  });	

		
		//开始按钮
		button_Start = new JButton("\u8003\u8BD5");
		frame.getContentPane().add(button_Start);
		button_Start.addActionListener(new ActionListener()
		  {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				
				String str[]= {null,null};
				int i=JComboBox_QuestionSeq.getSelectedIndex();
				str[0]=String.valueOf(i);
				//System.out.println(str[0]);
				str[1]=JTextField_QuestionNum.getText();
				//System.out.println(str[1]);
				
				if(User.qbType=="单选") {//若选择了单选题，则打开单选题考试界面
					SingleChoiceExamJframe.main(str);
				}else if(User.qbType=="多选") {
					MultipleChoiceExamJframe.main(str);
				}else {
					TrueOrFalseExamJframe.main(str);
				}
				frame.dispose();
				
			}
	  
		  });	
	}

}

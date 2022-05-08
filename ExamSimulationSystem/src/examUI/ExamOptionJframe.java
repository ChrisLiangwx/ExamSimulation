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

public class ExamOptionJframe {//����ѡ���

	private JFrame frame;
	private JTextField JTextField_QuestionNum;
	private JLabel Label_QuestionSeq;
	private JButton button_Return;
	private JButton button_Start;
	ActionListener listener1;//listener1:�����ʼ��ťʱ�򿪿��Դ���

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

		//ʹ��ʾ����Ļ����
		java.awt.Toolkit toolkit=java.awt.Toolkit.getDefaultToolkit();
		Dimension screenSize=toolkit.getScreenSize();
		double screenWidth=screenSize.getWidth();
		double screenHeight=screenSize.getHeight();
		

		frame.setLocation((int)(screenWidth-frame.getWidth())/2,(int)(screenHeight-frame.getHeight())/2);
		frame.setSize(300,150);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(3, 2, 0, 0));
		
		//��һ��
		//����˳���ǩ
		Label_QuestionSeq = new JLabel("\u7B54\u9898\u987A\u5E8F");
		Label_QuestionSeq.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(Label_QuestionSeq);
		
		//����˳�������б�
		String[] questionSeqList = new String[]{"˳�����", "�������"};
		DefaultComboBoxModel<String> questionSeqModel  = new DefaultComboBoxModel<String>(questionSeqList);
		JComboBox JComboBox_QuestionSeq = new JComboBox(questionSeqModel);//����д���ܽ��Windowbuilder��֧�ַ��͵�����
		JComboBox_QuestionSeq.setSelectedIndex(0);//����Ĭ��ѡ��
		frame.getContentPane().add(JComboBox_QuestionSeq);
		//System.out.println("ѡ��: " + JComboBox_QuestionSeq.getSelectedIndex() + " = " + JComboBox_QuestionSeq.getSelectedItem());
                

		
		//�ڶ���
		//��Ŀ������ǩ
		//��Ŀ�����ı���
		JLabel Label_QuestionNum = new JLabel("\u9898\u76EE\u6570\u91CF");
		Label_QuestionNum.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(Label_QuestionNum);
		
		JTextField_QuestionNum = new JTextField();
		frame.getContentPane().add(JTextField_QuestionNum);
		JTextField_QuestionNum.setColumns(10);
		
		
		String[] questionTypeList = new String[]{"��ѡ", "��ѡ","�ж�"};
		DefaultComboBoxModel<String> questionTypeModel  = new DefaultComboBoxModel<String>(questionTypeList);
		
		
		//������
		//���ذ�ť
		button_Return = new JButton("\u8FD4\u56DE");
		frame.getContentPane().add(button_Return);
		button_Return.addActionListener(new ActionListener()
		  {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				frame.dispose();//�ر�һ��GUI����
				
			}
	  
		  });	

		
		//��ʼ��ť
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
				
				if(User.qbType=="��ѡ") {//��ѡ���˵�ѡ�⣬��򿪵�ѡ�⿼�Խ���
					SingleChoiceExamJframe.main(str);
				}else if(User.qbType=="��ѡ") {
					MultipleChoiceExamJframe.main(str);
				}else {
					TrueOrFalseExamJframe.main(str);
				}
				frame.dispose();
				
			}
	  
		  });	
	}

}

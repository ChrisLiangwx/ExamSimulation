package mainUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import JDBC.ExportDataToExcel;
import JDBC.ImportDataToDB;
import examUI.ExamOptionJframe;
import manageQBUI.ManageQBJtable;
import publicQBUI.PublicQBJtable;
import resultUI.ResultJframe;
import user.Login;
import user.Register;
import user.User;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;

public class MainJframe {
	private JFrame frame;
	private JPanel Panel_Main;
	private JTextField textField_ShowQuestionBankName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainJframe window = new MainJframe();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public MainJframe() {
		initialize();
	}
	
	public void initialize() {
		//����������Ĳ���
		frame = new JFrame();
		frame.setTitle("\u6A21\u62DF\u8003\u8BD5\u7CFB\u7EDF");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//ʹ��ʾ����Ļ����
		java.awt.Toolkit toolkit=java.awt.Toolkit.getDefaultToolkit();
		Dimension screenSize=toolkit.getScreenSize();
		double screenWidth=screenSize.getWidth();
		double screenHeight=screenSize.getHeight();
		frame.setSize(680,620);
		frame.setLocation((int)(screenWidth-frame.getWidth())/2,(int)(screenHeight-frame.getHeight())/2);
				
		
		
		//�˵�
		JMenuBar menuBar = new JMenuBar();//��¼�˵���
		frame.setJMenuBar(menuBar);
		
		JMenu Menu_LogIn = new JMenu("\u767B\u5F55");
		menuBar.add(Menu_LogIn);
		
		JButton Button_Register = new JButton("\u6CE8\u518C");//ע�ᰴť
		Menu_LogIn.add(Button_Register);
		Button_Register.addActionListener(new ActionListener()//��ת��ע�����
		  {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				Register.main(null);
			}
	  
		  });	  
		
		
		JButton Button_LogIn = new JButton("\u767B\u5F55");//��¼��ť
		Menu_LogIn.add(Button_LogIn);
		Button_LogIn.addActionListener(new ActionListener()//��ת����¼����
		  {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				Login.main(null);
				frame.dispose();
			}
	  
		  });
		
		JButton Button_LogOut = new JButton("\u6CE8\u9500");//ע����ť
		Menu_LogIn.add(Button_LogOut);
		Button_LogOut.addActionListener(new ActionListener()//����userID״̬
		  {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				
				User.userID="9999";
				User.userName="guest";
				frame.dispose();
				MainJframe.main(null);
			}
	  
		  });
		
		
		JMenu Menu_Help = new JMenu("\u5E2E\u52A9");//�����˵���
		menuBar.add(Menu_Help);
		//����ָ�ϰ�ť��������¼�������listenerOI
		
		JButton Button_OperationInstruction = new JButton("\u64CD\u4F5C\u6307\u5357");//����ָ�ϰ�ť
		Menu_Help.add(Button_OperationInstruction);
		Button_OperationInstruction.addActionListener(new ActionListener()
		  {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "δ��¼����¿����е������/ʹ�ù������->���п���->�鿴���->�������⡣\n"
						+ "��¼������е������/ʹ�ù������/������⣨ʹ��������⡢ɾ����⡢׷�ӵ��룩->���п���->�鿴���->��������", "����ָ��",JOptionPane.INFORMATION_MESSAGE );
			}
	  
		  });

		
		
		
		//����壨���������������������壩
		Panel_Main = new JPanel();
		Panel_Main.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(Panel_Main);
		Panel_Main.setLayout(null);
		
		JLabel Label_Caption = new JLabel("\u6A21\u62DF\u8003\u8BD5\u7CFB\u7EDF");
		Label_Caption.setBounds(5, 6, 654, 137);
		Label_Caption.setForeground(new Color(153, 204, 255));
		Label_Caption.setHorizontalAlignment(SwingConstants.CENTER);
		Label_Caption.setFont(new Font("����", Font.PLAIN, 40));
		Panel_Main.add(Label_Caption);
		
		JLabel Label_LoginID = new JLabel();
		Label_LoginID.setText("\u767B\u5F55\u72B6\u6001");
		Label_LoginID.setFont(new Font("����", Font.PLAIN, 14));
		if(User.userID.equals("9999")) {
			Label_LoginID.setText("����ǰδ��¼ ");
			
		}			
		else {
			Label_LoginID.setText("��ӭ��: "+User.userName);
		}
		
		
		
		Label_LoginID.setBounds(481, 10, 177, 26);
		Panel_Main.add(Label_LoginID);
		
		//�ϲ���壨����������ⰴť�����������ǩ����ʾ�ı���
		JPanel Panel_Top = new JPanel();
		Panel_Top.setBounds(5, 143, 654, 137);
		Panel_Main.add(Panel_Top);
		Panel_Top.setLayout(null);
		
		JButton button_ImportQuestionBank = new JButton("\u5BFC\u5165\u9898\u5E93");//����
		button_ImportQuestionBank.setBounds(94, 11, 159, 38);
		Panel_Top.add(button_ImportQuestionBank);
		button_ImportQuestionBank.setFont(new Font("����", Font.PLAIN, 20));
		button_ImportQuestionBank.addActionListener(new ActionListener()
		  {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ImportOptionJframe.main(null);
				frame.dispose();
			}
	  
		  });
		
		
		JButton button_PublicQuestionBank = new JButton("\u516C\u5171\u9898\u5E93");//����
		button_PublicQuestionBank.setFont(new Font("����", Font.PLAIN, 20));
		button_PublicQuestionBank.setBounds(263, 11, 159, 38);
		Panel_Top.add(button_PublicQuestionBank);
		
		button_PublicQuestionBank.addActionListener(new ActionListener()
		  {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				//�򿪹������ѡ�����
				if(User.userName!="guest")
					JOptionPane.showMessageDialog(null, "����������δ��¼�û�ʹ�ã���ע��", "����",JOptionPane.ERROR_MESSAGE );
				else {
					//�򿪹���������
					PublicQBJtable.main(null);
				}
			}
	  
		  });
		
		
		JButton button_ManageQuestionBank = new JButton("\u7BA1\u7406\u9898\u5E93");//����
		button_ManageQuestionBank.setFont(new Font("����", Font.PLAIN, 20));
		button_ManageQuestionBank.setBounds(432, 11, 159, 38);
		Panel_Top.add(button_ManageQuestionBank);
		
		
		button_ManageQuestionBank.addActionListener(new ActionListener()
				  {

					@Override
					public void actionPerformed(ActionEvent e)
					{
						// TODO Auto-generated method stub
						if(User.userName=="guest")
							JOptionPane.showMessageDialog(null, "δ��¼�����¼��鿴", "����",JOptionPane.ERROR_MESSAGE );
						else {
							//�򿪹���������
							ManageQBJtable.main(null);
						}
					}
			  
				  });
		
		
		JLabel Label_ShowQuestionBankName = new JLabel("\u5BFC\u5165\u7684\u9898\u5E93\uFF1A");
		Label_ShowQuestionBankName.setFont(new Font("����", Font.PLAIN, 16));
		Label_ShowQuestionBankName.setBounds(58, 64, 106, 32);
		Panel_Top.add(Label_ShowQuestionBankName);
		
		
		
		textField_ShowQuestionBankName = new JTextField();
		textField_ShowQuestionBankName.setFont(new Font("����", Font.PLAIN, 16));
		textField_ShowQuestionBankName.setBounds(174, 67, 391, 29);
		Panel_Top.add(textField_ShowQuestionBankName);
		textField_ShowQuestionBankName.setColumns(10);
		if(User.qbName==null) {
			textField_ShowQuestionBankName.setText("δ�������");
		}else {
			textField_ShowQuestionBankName.setText(User.qbName);
		}

		
		
		
		
		
		
		
		//�в���壨������ʼ���԰�ť��
		JPanel Panel_Middle = new JPanel();
		Panel_Middle.setBounds(5, 280, 654, 137);
		Panel_Main.add(Panel_Middle);
		
		JButton button_StartExam = new JButton("\u5F00\u59CB\u8003\u8BD5");
		button_StartExam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExamOptionJframe.main(null);
			}
		});
		Panel_Middle.setLayout(new GridLayout(0, 1, 0, 0));
		Panel_Middle.add(button_StartExam);
		button_StartExam.setFont(new Font("����", Font.PLAIN, 20));


		
		//�ײ���壨�����鿴�����ť��
		JPanel Panel_Bottom = new JPanel();
		Panel_Bottom.setBounds(5, 417, 654, 137);
		Panel_Main.add(Panel_Bottom);
		Panel_Bottom.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton button_ShowResults = new JButton("\u67E5\u770B\u7ED3\u679C");
		button_ShowResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultJframe.main(null);
			}
		});
		Panel_Bottom.add(button_ShowResults);
		button_ShowResults.setFont(new Font("����", Font.PLAIN, 20));
		
		JButton button_Export = new JButton("\u5BFC\u51FA\u9519\u9898");
		button_Export.setFont(new Font("����", Font.PLAIN, 20));
		button_Export.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExportDataToExcel.main(null);
			}
		});
		Panel_Bottom.add(button_Export);

	}
}

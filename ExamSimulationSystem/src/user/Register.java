package user;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;

import JDBC.DBUtil;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DropMode;

public class Register {

	private JFrame frame;
	private JTextField jtextfield_InputAccount;
	private JPasswordField jpasswordfield_InputPWD;
	private JPasswordField jpasswordfield_InputConfirmPWD;
	private JTextField jtextfield_InputName;
	private JLabel jlabel_Name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
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
	public Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u6CE8\u518C");
		//ʹ��ʾ����Ļ����
		java.awt.Toolkit toolkit=java.awt.Toolkit.getDefaultToolkit();
		Dimension screenSize=toolkit.getScreenSize();
		double screenWidth=screenSize.getWidth();
		double screenHeight=screenSize.getHeight();
		frame.setSize(440,514);
		frame.setLocation((int)(screenWidth-frame.getWidth())/2,(int)(screenHeight-frame.getHeight())/2);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel jlabel_Register = new JLabel("\u6CE8\u518C\u8D26\u53F7");
		jlabel_Register.setForeground(Color.CYAN);
		jlabel_Register.setHorizontalAlignment(SwingConstants.CENTER);
		jlabel_Register.setFont(new Font("����", Font.PLAIN, 30));
		jlabel_Register.setBounds(82, 50, 295, 57);
		frame.getContentPane().add(jlabel_Register);
		
		//�˺�
		JLabel jlabel_Account = new JLabel("\u8D26\u53F7/\u5B66\u53F7");
		jlabel_Account.setHorizontalAlignment(SwingConstants.CENTER);
		jlabel_Account.setFont(new Font("����", Font.PLAIN, 16));
		jlabel_Account.setBounds(39, 135, 95, 27);
		frame.getContentPane().add(jlabel_Account);
		
		jtextfield_InputAccount = new JTextField();
		jtextfield_InputAccount.setFont(new Font("����", Font.PLAIN, 16));
		jtextfield_InputAccount.setText("�������˺Ż�ѧ��");
		jtextfield_InputAccount.setBounds(162, 135, 200, 27);
		frame.getContentPane().add(jtextfield_InputAccount);
		jtextfield_InputAccount.setColumns(10);
		MouseListener listenerInputAccount = new MouseListener(){//����굥���¼�ʵ�֣������ı���ʱ�ı����ڵ���ʾ���ֱ����
			@Override
			public void  mouseClicked(MouseEvent e)
			{
				String str="�������˺Ż�ѧ��";
				String str1="";
				if(jtextfield_InputAccount.getText().equals(str))
					jtextfield_InputAccount.setText(str1);
			}	
			public void  mouseEntered(MouseEvent e)
			{}
			public void  mousePressed(MouseEvent e)
			{}	
			public void  mouseReleased(MouseEvent e)
			{}	
			public void  mouseExited(MouseEvent e)
			{}	
		};
		jtextfield_InputAccount.addMouseListener(listenerInputAccount);
		
		
		//����
		
		jlabel_Name = new JLabel("\u59D3\u540D/\u6635\u79F0");
		jlabel_Name.setHorizontalAlignment(SwingConstants.CENTER);
		jlabel_Name.setFont(new Font("����", Font.PLAIN, 16));
		jlabel_Name.setBounds(39, 196, 95, 27);
		frame.getContentPane().add(jlabel_Name);
		
		jtextfield_InputName = new JTextField();
		jtextfield_InputName.setText("\u8BF7\u8F93\u5165\u59D3\u540D\u6216\u6635\u79F0");
		jtextfield_InputName.setFont(new Font("����", Font.PLAIN, 16));
		jtextfield_InputName.setColumns(10);
		jtextfield_InputName.setBounds(162, 196, 200, 27);
		frame.getContentPane().add(jtextfield_InputName);
		MouseListener listenerInputName = new MouseListener(){//����굥���¼�ʵ�֣������ı���ʱ�ı����ڵ���ʾ���ֱ����
			@Override
			public void  mouseClicked(MouseEvent e)
			{
				String str="�������������ǳ�";
				String str1="";
				if(jtextfield_InputName.getText().equals(str))
					jtextfield_InputName.setText(str1);
			}	
			public void  mouseEntered(MouseEvent e)
			{}
			public void  mousePressed(MouseEvent e)
			{}	
			public void  mouseReleased(MouseEvent e)
			{}	
			public void  mouseExited(MouseEvent e)
			{}	
		};
		jtextfield_InputName.addMouseListener(listenerInputName);
		
		
		
		//����
		JLabel jlabel_PWD = new JLabel("\u5BC6\u7801");
		jlabel_PWD.setHorizontalAlignment(SwingConstants.CENTER);
		jlabel_PWD.setFont(new Font("����", Font.PLAIN, 16));
		jlabel_PWD.setBounds(39, 262, 95, 27);
		frame.getContentPane().add(jlabel_PWD);
		
		jpasswordfield_InputPWD = new JPasswordField();
		jpasswordfield_InputPWD.setFont(new Font("����", Font.PLAIN, 16));
		jpasswordfield_InputPWD.setColumns(10);
		jpasswordfield_InputPWD.setBounds(162, 262, 200, 27);
		frame.getContentPane().add(jpasswordfield_InputPWD);
		
		
		//ȷ������
		JLabel jlabel_ConfirmPWD = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		jlabel_ConfirmPWD.setHorizontalAlignment(SwingConstants.CENTER);
		jlabel_ConfirmPWD.setFont(new Font("����", Font.PLAIN, 16));
		jlabel_ConfirmPWD.setBounds(39, 325, 95, 27);
		frame.getContentPane().add(jlabel_ConfirmPWD);
		
		jpasswordfield_InputConfirmPWD = new JPasswordField();
		jpasswordfield_InputConfirmPWD.setFont(new Font("����", Font.PLAIN, 16));
		jpasswordfield_InputConfirmPWD.setColumns(10);
		jpasswordfield_InputConfirmPWD.setBounds(162, 325, 200, 27);
		frame.getContentPane().add(jpasswordfield_InputConfirmPWD);
		
		
		
		JButton jbutton_Submit = new JButton("\u63D0\u4EA4");
		jbutton_Submit.setFont(new Font("����", Font.PLAIN, 18));
		jbutton_Submit.setBounds(187, 381, 83, 27);

		frame.getContentPane().add(jbutton_Submit);
		
		
		ActionListener listenerSubmit = new ActionListener() {//�ύʱ�ж������Ƿ�Ϸ������������ݿ�
			public void actionPerformed(ActionEvent e) {
				boolean accountlegal = false, namelegal = false, pwdlegal = false;
				
				
				//�ж��˻���ѧ���Ƿ�Ϸ�
				String account=jtextfield_InputAccount.getText();
				if (account.equals("")||account.equals("�������˺Ż�ѧ��")) {
					JOptionPane.showMessageDialog(null, "�˻���ѧ�Ų���Ϊ��", "����",JOptionPane.ERROR_MESSAGE );
					return ;
				}else if(!account.matches("[0-9]+")) {
					JOptionPane.showMessageDialog(null, "�˻���ѧ�Ÿ�ʽ��������������", "����",JOptionPane.ERROR_MESSAGE );
					return ;
				}else if(account.length()>10||account.length()<4) {
					JOptionPane.showMessageDialog(null, "�˻���ѧ�ų��Ȳ�����Ҫ��������4-10λ����", "����",JOptionPane.ERROR_MESSAGE );
					return ;
				}else {
					accountlegal=true;
					
				}
					
				
				//�ж��������ǳ��Ƿ�Ϸ�
				String name=jtextfield_InputName.getText();
				if (name.equals("")||name.equals("�������������ǳ�")) {
					JOptionPane.showMessageDialog(null, "�������ǳƲ���Ϊ��", "����",JOptionPane.ERROR_MESSAGE );
					return ;
				}else if(name.length()>5||name.length()<2) {
					JOptionPane.showMessageDialog(null, "�������ǳƳ��Ȳ�����Ҫ��������2-5λ�ַ�", "����",JOptionPane.ERROR_MESSAGE );
					return ;
				}else {
					namelegal=true;
				}
					
				
				
				
				String pwd=new String(jpasswordfield_InputPWD.getPassword());
				String confirmpwd=new String(jpasswordfield_InputConfirmPWD.getPassword());
				//�ж��������������Ƿ�Ϸ�
				if (!pwd.equals(confirmpwd)) {
					JOptionPane.showMessageDialog(null, "�����������벻һ��", "����",JOptionPane.ERROR_MESSAGE );
					return ;	
				}else if(pwd.equals("")){
					JOptionPane.showMessageDialog(null, "���벻��Ϊ��", "����",JOptionPane.ERROR_MESSAGE );
					return ;
				}else if(pwd.length()<4||pwd.length()>10){
					JOptionPane.showMessageDialog(null, "���볤�Ȳ�����Ҫ��������4-10λ�ַ�", "����",JOptionPane.ERROR_MESSAGE );
					return ;
				}else {
					pwdlegal=true;
				}
					
				//������Ϸ�����������ݿ�
				if(accountlegal&&namelegal&&pwdlegal) {
					String encryptpwd=Md5encrypt.md5Password(pwd);
					JOptionPane.showMessageDialog(null, "ע��ɹ�", "�ɹ�",JOptionPane.INFORMATION_MESSAGE);
					DBUtil user=new DBUtil();
					String sql="insert into [user] (UserID,UserName,UserPWD) values(?,?,?)";//user�����ݿ��еĹؼ��֣������Ҫ����[]
					String[] str=new String[]{account,name,encryptpwd};
					user.AddOrUpdate(sql, str);
					System.out.println("�ѽ��û�"+name+"���������ݿ���user��");
					frame.dispose();
				}
				
			}
		};
		jbutton_Submit.addActionListener(listenerSubmit);
	}
}

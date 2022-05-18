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
import mainUI.MainJframe;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DropMode;

public class Login {

	private JFrame frame;
	private JTextField jtextfield_InputAccount;
	private JPasswordField jpasswordfield_InputPWD;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u767B\u5F55");
		//ʹ��ʾ����Ļ����
		java.awt.Toolkit toolkit=java.awt.Toolkit.getDefaultToolkit();
		Dimension screenSize=toolkit.getScreenSize();
		double screenWidth=screenSize.getWidth();
		double screenHeight=screenSize.getHeight();
		frame.setSize(440,375);
		frame.setLocation((int)(screenWidth-frame.getWidth())/2,(int)(screenHeight-frame.getHeight())/2);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel jlabel_Register = new JLabel("\u767B\u5F55\u8D26\u53F7");
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
		JLabel jlabel_PWD = new JLabel("\u5BC6\u7801");
		jlabel_PWD.setHorizontalAlignment(SwingConstants.CENTER);
		jlabel_PWD.setFont(new Font("����", Font.PLAIN, 16));
		jlabel_PWD.setBounds(39, 198, 95, 27);
		frame.getContentPane().add(jlabel_PWD);
		
		jpasswordfield_InputPWD = new JPasswordField();
		jpasswordfield_InputPWD.setFont(new Font("����", Font.PLAIN, 16));
		jpasswordfield_InputPWD.setColumns(10);
		jpasswordfield_InputPWD.setBounds(162, 198, 200, 27);
		frame.getContentPane().add(jpasswordfield_InputPWD);
		
		
		
		JButton jbutton_Login = new JButton("\u767B\u5F55");
		jbutton_Login.setFont(new Font("����", Font.PLAIN, 18));
		jbutton_Login.setBounds(187, 273, 83, 27);

		frame.getContentPane().add(jbutton_Login);
		
		
		ActionListener listenerSubmit = new ActionListener() {//�ύʱ�ж������Ƿ�Ϸ����������ݿ��б�����˺Ž��бȽ���֤
			public void actionPerformed(ActionEvent e) {
				boolean accountlegal = false, pwdlegal = false;
				
				
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
					
				
				//���Ҹ��˺Ŷ�Ӧ�����룬�������������бȽ�
				String rightPWD=null;
				DBUtil user=new DBUtil();
				String sql="select UserPWD from [user] where UserID = ?";//user�����ݿ��еĹؼ��֣������Ҫ����[]
	            String[] str=new String[]{account};
	            ResultSet rs = null;
	            rs=user.Search(sql, str);
	            //�����ݿ��л�ò�ѯ���
	            try {
	            	 while(rs.next()) {
	            		 rightPWD=rs.getString(1);//ֻ�鵽һ�����ݣ���ȡ��һ��
	                 }
	                
	            } catch (Exception e1) {
	                e1.printStackTrace();
	               }
	             finally {
	                try {
	                    rs.close();
	                } catch (SQLException e1) {
	                    e1.printStackTrace();
	                }
	            }
	            
				
				String pwd=new String(jpasswordfield_InputPWD.getPassword());
				String encryptpwd=Md5encrypt.md5Password(pwd);
				//�ж����������Ƿ�Ϸ�
				if(pwd.equals("")){
					JOptionPane.showMessageDialog(null, "���벻��Ϊ��", "����",JOptionPane.ERROR_MESSAGE );
					return ;
				}else if(pwd.length()<4||pwd.length()>10){
					JOptionPane.showMessageDialog(null, "���볤�Ȳ�����Ҫ��������4-10λ�ַ�", "����",JOptionPane.ERROR_MESSAGE );
					return ;
				}else if(!encryptpwd.equals(rightPWD)) {
					JOptionPane.showMessageDialog(null, "�˺Ż��������", "����",JOptionPane.ERROR_MESSAGE );
					return ;
				}else {
					pwdlegal=true;
				}
					
				//��������ȷ�����¼�ɹ����ı�user�е���Ϣ
				if(accountlegal&&pwdlegal) {
					
					JOptionPane.showMessageDialog(null, "��¼�ɹ�", "�ɹ�",JOptionPane.INFORMATION_MESSAGE);
					
					//����userName��Ϣ
					String name=null;
					sql="select UserName from [user] where UserID = ?";
		            str=new String[]{account};
		            rs = null;
		            rs=user.Search(sql, str);
		            try {
		            	 while(rs.next()) {
		            		 name=rs.getString(1);//ֻ�鵽һ�����ݣ���ȡ��һ��
		                 }
		                
		            } catch (Exception e1) {
		                e1.printStackTrace();
		               }
		             finally {
		                try {
		                    rs.close();
		                } catch (SQLException e1) {
		                    e1.printStackTrace();
		                }
		            }
					
					User.userID=account;
					User.userName=name;
					User.qbName=null;
					User.qbType=null;
					System.out.println("�û�"+name+"��¼�ɹ�");
					frame.dispose();
					MainJframe.main(null);
				}
				
			}
		};
		jbutton_Login.addActionListener(listenerSubmit);
	}
}

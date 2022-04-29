package manageQBUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import JDBC.AddDataToDB;
import JDBC.DBUtil;
import JDBC.ImportDataToDB;
import mainUI.MyFileFilter;
import user.User;

public class ManageQBJtable extends JFrame
{

	public static String []SelectedQB=new String[100];//��¼ѡ�е����
	public static String []QBType=new String[100];//��¼ѡ����������
	public static boolean[]ChangedQB=new boolean[100];//�ź������ж�ѡ���Ƿ�ȡ��
	
	
	public ManageQBJtable()
	{
		intiComponent();
	}
 
	/**
	 * ��ʼ���������
	 */
	private void intiComponent()
	{
		JTable jTable_QBList = new JTable(new MyTableModel());
		/* ��JScrollPaneװ��JTable������������Χ���оͿ���ͨ�����������鿴 */
		JScrollPane jScrollPanel_main = new JScrollPane(jTable_QBList);
		getContentPane().add(jScrollPanel_main);
		
		JPanel panel = new JPanel();
		jScrollPanel_main.setRowHeaderView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton button_DeleteQB = new JButton("\u5220\u9664");//ɾ����⣬����ѡ����
		panel.add(button_DeleteQB);
		button_DeleteQB.addActionListener(new ActionListener()
		  {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				int i=0;
				for(i=0;i<100;i++) {
					if(SelectedQB[i]!=null) {
						//ɾ�������
						DBUtil delete=new DBUtil();
						String sql="delete��from��question��where��Owner=? and QuestionBankName=?";//ɾ��question��
						String []str= {User.userName,SelectedQB[i]};
						delete.AddOrUpdate(sql, str);
					}
				}
			}
	  
		  });

		
		JButton button_SelectQB = new JButton("\u9009\u62E9");//ѡ�������п��ԣ�ֻ��ѡ��һ��
		panel.add(button_SelectQB);
		button_SelectQB.addActionListener(new ActionListener()
		  {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				int i=0;
				int count=0;//��¼ѡ����������������1ʱ���ܽ���ѡ�����
				int flag=0;//��¼ѡ��������
				for(i=0;i<100;i++) {
					if(SelectedQB[i]!=null) {
						flag=i;
						count++;
					}
				}
				if(count>1) {
					//����ѡ�������
					JOptionPane.showMessageDialog(null, "����ѡ�������", "����",JOptionPane.ERROR_MESSAGE );
				}else {
					User.qbName=SelectedQB[flag];
					//��Ҫ��ø�������Ŀ���ͣ�ΪUser.qbType��ֵ
					System.out.println("�������:"+QBType[flag]);
					if(QBType[flag].equals("��ѡ")) {
						User.qbType="��ѡ";
					}else if(QBType[flag].equals("��ѡ")) {
						User.qbType="��ѡ";
					}else {
						User.qbType="�ж�";
					}
					System.out.println("ѡ�����:"+SelectedQB[flag]);
				}
			}
	  
		  });
		
		
		JButton button_AddToQB = new JButton("\u8FFD\u52A0");//׷����Ŀ����⣬ֻ��ѡ��һ��
		panel.add(button_AddToQB);

		button_AddToQB.addActionListener(new ActionListener()
		  {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				int i=0;
				int count=0;//��¼ѡ����������������1ʱ���ܽ���׷�Ӳ���
				int flag=0;//��¼ѡ��������
				for(i=0;i<100;i++) {
					if(SelectedQB[i]!=null) {
						flag=i;
						count++;
					}
				}
				if(count>1) {
					//����ѡ�������
					JOptionPane.showMessageDialog(null, "����ѡ�������", "����",JOptionPane.ERROR_MESSAGE );
				}else {
					User.qbName=SelectedQB[flag];
					
					//׷�ӵ���
					JFileChooser chooser=new JFileChooser(); //��ʼ���ļ�ѡ����
					int state; //�ļ�ѡ��������״̬
					String ends="xls";//�����ļ���׺
					String description="Excel�ļ�";//�����ļ���������
					
					state=chooser.showOpenDialog(null); //��ʾ���ļ��Ի���
					chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter()); //��ȥ�����ļ�������
					chooser.addChoosableFileFilter(new MyFileFilter(ends,description)); //�����ļ�������,����ָ�����͵��ļ�
					File file = chooser.getSelectedFile(); //�õ�ѡ����ļ�
					
					if(file != null && state == JFileChooser.APPROVE_OPTION) { //���ļ�
						JOptionPane.showMessageDialog(null, "��ѡ����ļ�·��Ϊ��"+file.getPath()); //��ʾ�򿪵��ļ�·��
					    String filePath=file.getPath();
					    AddDataToDB addQB=new AddDataToDB();
					    addQB.addQB(filePath);
					}else if(state == JFileChooser.CANCEL_OPTION) { //����˳�����ť
						JOptionPane.showMessageDialog(null, "�˳�!"); //��ʾ��ʾ��Ϣ
					}
				}
			}
	  
		  });
		
		JButton btnNewButton = new JButton("\u5237\u65B0");
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener()
		  {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				dispose();
				ManageQBJtable.main(null);
			}
	  
		  });
		
		java.awt.Toolkit toolkit=java.awt.Toolkit.getDefaultToolkit();
		Dimension screenSize=toolkit.getScreenSize();
		double screenWidth=screenSize.getWidth();
		double screenHeight=screenSize.getHeight();
		this.setLocation((int)(screenWidth-500)/2,(int)(screenHeight-500)/2);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
	}
 
	
 
	public static void main(String[] args)
	{
		new ManageQBJtable();
	}
}
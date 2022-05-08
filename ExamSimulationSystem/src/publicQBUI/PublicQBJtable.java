package publicQBUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import user.User;

public class PublicQBJtable extends JFrame
{

	public static String []SelectedQB=new String[100];//��¼ѡ�е����
	public static String []QBType=new String[100];//��¼ѡ����������
	public static boolean[]ChangedQB=new boolean[100];//�ź������ж�ѡ���Ƿ�ȡ��
	
	
	public PublicQBJtable()
	{
		intiComponent();
	}
 
	/**
	 * ��ʼ���������
	 */
	private void intiComponent()
	{
		JTable jTable_QBList = new JTable(new PublicTableModel());
		/* ��JScrollPaneװ��JTable������������Χ���оͿ���ͨ�����������鿴 */
		JScrollPane jScrollPanel_main = new JScrollPane(jTable_QBList);
		getContentPane().add(jScrollPanel_main);
		
		JPanel panel = new JPanel();
		jScrollPanel_main.setRowHeaderView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		
		JButton button_SelectQB = new JButton("\u9009\u62E9");//ѡ�������п��ԣ�ֻ��ѡ��һ��
		panel.add(button_SelectQB);
		button_SelectQB.addActionListener(new ActionListener()
		  {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				int i=0;
				int count=0;
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
		new PublicQBJtable();
	}
}
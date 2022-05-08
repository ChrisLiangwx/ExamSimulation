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

	public static String []SelectedQB=new String[100];//记录选中的题库
	public static String []QBType=new String[100];//记录选中题库的类型
	public static boolean[]ChangedQB=new boolean[100];//信号量，判断选中是否取消
	
	
	public PublicQBJtable()
	{
		intiComponent();
	}
 
	/**
	 * 初始化窗体组件
	 */
	private void intiComponent()
	{
		JTable jTable_QBList = new JTable(new PublicTableModel());
		/* 用JScrollPane装载JTable，这样超出范围的列就可以通过滚动条来查看 */
		JScrollPane jScrollPanel_main = new JScrollPane(jTable_QBList);
		getContentPane().add(jScrollPanel_main);
		
		JPanel panel = new JPanel();
		jScrollPanel_main.setRowHeaderView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		
		JButton button_SelectQB = new JButton("\u9009\u62E9");//选择题库进行考试，只能选择一个
		panel.add(button_SelectQB);
		button_SelectQB.addActionListener(new ActionListener()
		  {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				int i=0;
				int count=0;
				int flag=0;//记录选择的题库名
				for(i=0;i<100;i++) {
					if(SelectedQB[i]!=null) {
						flag=i;
						count++;
					}
				}
				if(count>1) {
					//不能选择多个题库
					JOptionPane.showMessageDialog(null, "不能选择多个题库", "错误",JOptionPane.ERROR_MESSAGE );
				}else {
					User.qbName=SelectedQB[flag];
					//需要获得该题库的题目类型，为User.qbType赋值
					System.out.println("题库类型:"+QBType[flag]);
					if(QBType[flag].equals("单选")) {
						User.qbType="单选";
					}else if(QBType[flag].equals("多选")) {
						User.qbType="多选";
					}else {
						User.qbType="判断";
					}
					System.out.println("选择题库:"+SelectedQB[flag]);
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
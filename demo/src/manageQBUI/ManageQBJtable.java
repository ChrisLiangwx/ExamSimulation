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

	public static String []SelectedQB=new String[100];//记录选中的题库
	public static String []QBType=new String[100];//记录选中题库的类型
	public static boolean[]ChangedQB=new boolean[100];//信号量，判断选中是否取消
	
	
	public ManageQBJtable()
	{
		intiComponent();
	}
 
	/**
	 * 初始化窗体组件
	 */
	private void intiComponent()
	{
		JTable jTable_QBList = new JTable(new MyTableModel());
		/* 用JScrollPane装载JTable，这样超出范围的列就可以通过滚动条来查看 */
		JScrollPane jScrollPanel_main = new JScrollPane(jTable_QBList);
		getContentPane().add(jScrollPanel_main);
		
		JPanel panel = new JPanel();
		jScrollPanel_main.setRowHeaderView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton button_DeleteQB = new JButton("\u5220\u9664");//删除题库，可以选择多个
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
						//删除该题库
						DBUtil delete=new DBUtil();
						String sql="delete　from　question　where　Owner=? and QuestionBankName=?";//删除question表
						String []str= {User.userName,SelectedQB[i]};
						delete.AddOrUpdate(sql, str);
					}
				}
			}
	  
		  });

		
		JButton button_SelectQB = new JButton("\u9009\u62E9");//选择题库进行考试，只能选择一个
		panel.add(button_SelectQB);
		button_SelectQB.addActionListener(new ActionListener()
		  {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				int i=0;
				int count=0;//记录选择的题库数量，超过1时不能进行选择操作
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
		
		
		JButton button_AddToQB = new JButton("\u8FFD\u52A0");//追加题目至题库，只能选择一个
		panel.add(button_AddToQB);

		button_AddToQB.addActionListener(new ActionListener()
		  {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				int i=0;
				int count=0;//记录选择的题库数量，超过1时不能进行追加操作
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
					
					//追加导入
					JFileChooser chooser=new JFileChooser(); //初始化文件选择器
					int state; //文件选择器返回状态
					String ends="xls";//设置文件后缀
					String description="Excel文件";//设置文件描述文字
					
					state=chooser.showOpenDialog(null); //显示打开文件对话框
					chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter()); //移去所有文件过滤器
					chooser.addChoosableFileFilter(new MyFileFilter(ends,description)); //增加文件过滤器,接受指定类型的文件
					File file = chooser.getSelectedFile(); //得到选择的文件
					
					if(file != null && state == JFileChooser.APPROVE_OPTION) { //打开文件
						JOptionPane.showMessageDialog(null, "您选择的文件路径为："+file.getPath()); //显示打开的文件路径
					    String filePath=file.getPath();
					    AddDataToDB addQB=new AddDataToDB();
					    addQB.addQB(filePath);
					}else if(state == JFileChooser.CANCEL_OPTION) { //点击了撤销按钮
						JOptionPane.showMessageDialog(null, "退出!"); //显示提示信息
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
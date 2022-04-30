package mainUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import JDBC.ImportDataToDB;
import user.User;

import javax.swing.JButton;
import javax.swing.ComboBoxModel;

public class ImportOptionJframe {//导入选项窗口

	private JFrame frame;
	private JLabel Label_QuestionType;
	private JComboBox JComboBox_QuestionType;
	private JButton button_Confirm;
	private JButton button_Cancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImportOptionJframe window = new ImportOptionJframe();
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
	public ImportOptionJframe() {
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
		
		//frame.setBounds(1000, 400, 300, 150);

		frame.setLocation((int)(screenWidth-frame.getWidth())/2,(int)(screenHeight-frame.getHeight())/2);
		frame.setSize(300,150);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(2, 2, 0, 0));
		
		//答题顺序下拉列表
		String[] questionSeqList = new String[]{"顺序答题", "随机答题"};
		DefaultComboBoxModel<String> questionSeqModel  = new DefaultComboBoxModel<String>(questionSeqList);
		
		Label_QuestionType = new JLabel("\u9898\u76EE\u7C7B\u578B");
		Label_QuestionType.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(Label_QuestionType);
		
		
		String[] questionTypeList = new String[]{"单选", "多选","判断"};
		DefaultComboBoxModel<String> questionTypeModel  = new DefaultComboBoxModel<String>(questionTypeList);
		JComboBox JComboBox_QuestionType = new JComboBox(questionTypeModel);//这种写法能解决Windowbuilder不支持泛型的问题
		JComboBox_QuestionType.setSelectedIndex(0);//设置默认选项
		frame.getContentPane().add(JComboBox_QuestionType);
		
		button_Cancel = new JButton("\u8FD4\u56DE");
		frame.getContentPane().add(button_Cancel);
		button_Cancel.addActionListener(new ActionListener()
		  {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				frame.dispose();
				MainJframe.main(null);
			}
	  
		  });
		
		button_Confirm = new JButton("\u786E\u5B9A");
		frame.getContentPane().add(button_Confirm);
		button_Confirm.addActionListener(new ActionListener()
		  {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				if(JComboBox_QuestionType.getSelectedIndex()==0) {
					User.qbType="单选";
				}else if(JComboBox_QuestionType.getSelectedIndex()==1) {
					User.qbType="多选";
				}else {
					User.qbType="判断";
				}
				
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
					File tmpFile=new File(file.getPath());
				    String fileName=tmpFile.getName();
				    String filePath=file.getPath();
				    User.qbName=fileName;
				    
				    ImportDataToDB importQB=new ImportDataToDB();
				    importQB.importQB(filePath);
				}
				else if(state == JFileChooser.CANCEL_OPTION) { //点击了撤销按钮
					JOptionPane.showMessageDialog(null, "退出!"); //显示提示信息
				}
				frame.dispose();
				MainJframe.main(null);
				
			}
	  
		  });
	}

}

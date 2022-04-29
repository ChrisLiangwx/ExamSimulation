package backup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import JDBC.DBUtil;
import JDBC.ImportDataToDB;
import mainUI.MyFileFilter;
import user.User;

public class Button_ImportQuestionBank_Listener implements CombinedListener1{//主窗口中“导入题库”按钮的事件监听器
//可以打开一个文件选择器，使用了MyFileFilter来限制文件类型

	JTextField textshow;
	JTextField QBName;
	public void actionPerformed(ActionEvent e) {
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
		    textshow.setText(fileName);
		    User.qbName=fileName;
		    
		    //实现题库导入数据库
//		    //先删除数据库表中已有的内容
//		    DBUtil exam=new DBUtil();
//			String sql="TRUNCATE Table question";//删除question表
//			exam.AddOrUpdate(sql, null);
//	        System.out.println("删除dbo.single_choice_question成功");
		    ImportDataToDB importQB=new ImportDataToDB();
		    importQB.importQB(filePath);
		}
		else if(state == JFileChooser.CANCEL_OPTION) { //点击了撤销按钮
			JOptionPane.showMessageDialog(null, "退出!"); //显示提示信息
		}
		
		
	}
	
	public void setJTextField(JTextField jtf) {//将文件名显示在文本框中
		textshow=jtf;
	}
	
	
}

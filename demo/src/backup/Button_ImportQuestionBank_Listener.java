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

public class Button_ImportQuestionBank_Listener implements CombinedListener1{//�������С�������⡱��ť���¼�������
//���Դ�һ���ļ�ѡ������ʹ����MyFileFilter�������ļ�����

	JTextField textshow;
	JTextField QBName;
	public void actionPerformed(ActionEvent e) {
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
			File tmpFile=new File(file.getPath());
		    String fileName=tmpFile.getName();
		    String filePath=file.getPath();
		    textshow.setText(fileName);
		    User.qbName=fileName;
		    
		    //ʵ����⵼�����ݿ�
//		    //��ɾ�����ݿ�������е�����
//		    DBUtil exam=new DBUtil();
//			String sql="TRUNCATE Table question";//ɾ��question��
//			exam.AddOrUpdate(sql, null);
//	        System.out.println("ɾ��dbo.single_choice_question�ɹ�");
		    ImportDataToDB importQB=new ImportDataToDB();
		    importQB.importQB(filePath);
		}
		else if(state == JFileChooser.CANCEL_OPTION) { //����˳�����ť
			JOptionPane.showMessageDialog(null, "�˳�!"); //��ʾ��ʾ��Ϣ
		}
		
		
	}
	
	public void setJTextField(JTextField jtf) {//���ļ�����ʾ���ı�����
		textshow=jtf;
	}
	
	
}

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

public class ImportOptionJframe {//����ѡ���

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

		//ʹ��ʾ����Ļ����
		java.awt.Toolkit toolkit=java.awt.Toolkit.getDefaultToolkit();
		Dimension screenSize=toolkit.getScreenSize();
		double screenWidth=screenSize.getWidth();
		double screenHeight=screenSize.getHeight();
		
		//frame.setBounds(1000, 400, 300, 150);

		frame.setLocation((int)(screenWidth-frame.getWidth())/2,(int)(screenHeight-frame.getHeight())/2);
		frame.setSize(300,150);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(2, 2, 0, 0));
		
		//����˳�������б�
		String[] questionSeqList = new String[]{"˳�����", "�������"};
		DefaultComboBoxModel<String> questionSeqModel  = new DefaultComboBoxModel<String>(questionSeqList);
		
		Label_QuestionType = new JLabel("\u9898\u76EE\u7C7B\u578B");
		Label_QuestionType.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(Label_QuestionType);
		
		
		String[] questionTypeList = new String[]{"��ѡ", "��ѡ","�ж�"};
		DefaultComboBoxModel<String> questionTypeModel  = new DefaultComboBoxModel<String>(questionTypeList);
		JComboBox JComboBox_QuestionType = new JComboBox(questionTypeModel);//����д���ܽ��Windowbuilder��֧�ַ��͵�����
		JComboBox_QuestionType.setSelectedIndex(0);//����Ĭ��ѡ��
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
					User.qbType="��ѡ";
				}else if(JComboBox_QuestionType.getSelectedIndex()==1) {
					User.qbType="��ѡ";
				}else {
					User.qbType="�ж�";
				}
				
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
				    User.qbName=fileName;
				    
				    ImportDataToDB importQB=new ImportDataToDB();
				    importQB.importQB(filePath);
				}
				else if(state == JFileChooser.CANCEL_OPTION) { //����˳�����ť
					JOptionPane.showMessageDialog(null, "�˳�!"); //��ʾ��ʾ��Ϣ
				}
				frame.dispose();
				MainJframe.main(null);
				
			}
	  
		  });
	}

}

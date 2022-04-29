package backup;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTable;

import JDBC.DBUtil;
import user.User;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JScrollPane;

public class ManageQBJframe {

	private JFrame frame;
	private JTable jtable_qbinfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageQBJframe window = new ManageQBJframe();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ManageQBJframe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//�Ȳ��ҳ����˼�����ͬ����⣬Ȼ���������������ɶ�Ӧ������jtable
		//SELECT DISTINCT questionbankname FROM single_choice_question where Owner='xxx'  ��ѯ����
		//SELECT DISTINCT COUNT(DISTINCT questionbankname) FROM single_choice_question where Owner='xxx'	ͳ�Ʊ���
		
		int qbnum = 0;//�������
		DBUtil questionbank = new DBUtil();
		String sql="SELECT DISTINCT COUNT(DISTINCT questionbankname) FROM single_choice_question where Owner=?";
		String[] str=new String[]{User.userName};
		ResultSet rs = null;
		rs=questionbank.Search(sql, str);
		try {
       	 while(rs.next()) {
             System.out.println(rs.getString(1));
       		 qbnum=Integer.parseInt(rs.getString(1));
            }
           
       } catch (Exception e) {
           e.printStackTrace();
          }
        finally {
           try {
               rs.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
		
		int i=0;
		String[] qbname = new String[qbnum];
		sql="SELECT DISTINCT questionbankname FROM single_choice_question where Owner=?";
        str=new String[]{User.userName};
        rs=questionbank.Search(sql, str);
        //�����ݿ��л�ò�ѯ���
        try {
        	 while(rs.next()) {
             	 System.out.println(rs.getString(1));
        		 qbname[i]=rs.getString(1);
        		 i++;
             }
            
        } catch (Exception e) {
            e.printStackTrace();
           }
         finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        
//        frame = new JFrame();
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		java.awt.Toolkit toolkit=java.awt.Toolkit.getDefaultToolkit();
//		Dimension screenSize=toolkit.getScreenSize();
//		double screenWidth=screenSize.getWidth();
//		double screenHeight=screenSize.getHeight();
//		frame.setSize(680,300);
//		frame.setLocation((int)(screenWidth-frame.getWidth())/2,(int)(screenHeight-frame.getHeight())/2);
//		frame.getContentPane().setLayout(null);
		
		JScrollPane scroll = new JScrollPane(jtable_qbinfo);  
        scroll.setSize(100, 200);  
        
        
		
		jtable_qbinfo = new JTable(qbnum+1,4);
		//frame.getContentPane().add(jtable_qbinfo);
		jtable_qbinfo.setFont(new Font("����", Font.PLAIN, 16));
		jtable_qbinfo.setBounds(114, 69, 457, 115);
		jtable_qbinfo.getColumnModel().getColumn(0).setPreferredWidth(100);
		i=0;
		for(i=0;i<qbnum;i++) {
			jtable_qbinfo.setValueAt(qbname[i], i, 0);//�����õ�ֵ��row��column��


		}
		
		
	}
}

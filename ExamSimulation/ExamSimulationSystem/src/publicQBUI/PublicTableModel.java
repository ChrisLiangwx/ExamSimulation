package publicQBUI;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

import JDBC.DBUtil;
import user.User;

public class PublicTableModel extends AbstractTableModel
{
	
	public int getQBnum() {//ͳ�������
		//�Ȳ��ҳ����˼�����ͬ����⣬Ȼ���������������ɶ�Ӧ������jtable
		//SELECT DISTINCT COUNT(DISTINCT questionbankname) FROM question where Owner='xxx'	ͳ�������
		int qbnum = 0;//�������
		DBUtil questionbank = new DBUtil();
		String sql="SELECT DISTINCT COUNT(DISTINCT questionbankname) FROM question where Owner='guest' and IsPublic='Yes'";
		//String[] str=new String[]{User.userName};
		ResultSet rs = null;
		rs=questionbank.Search(sql, null);
		try {
	       	 while(rs.next()) {
	             //System.out.println(rs.getString(1));
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
		return qbnum;
	}

			
	public String[] getQBname() {//��ѯ�����
		//SELECT DISTINCT questionbankname FROM question where Owner='xxx'  ��ѯ�����
		int i=0;
		String[] qbname = new String[getQBnum()];
		DBUtil questionbank = new DBUtil();
		String sql="SELECT DISTINCT questionbankname FROM question where Owner='guest' and IsPublic='Yes'";
        //String []str=new String[]{User.userName};
        ResultSet rs=questionbank.Search(sql, null);
        //�����ݿ��л�ò�ѯ���
        try {
        	 while(rs.next()) {
             	 //System.out.println(rs.getString(1));
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
        return qbname;
	}
	
	
	public String[] getQnum() {//��ѯÿ������ڵ���Ŀ��
		int i=0;
		String[] qname = new String[getQBnum()];
		DBUtil questionbank = new DBUtil();
		String sql="SELECT DISTINCT QuestionBankName,COUNT(DISTINCT QuestionID) FROM question \r\n" + 
				"WHERE Owner='guest' and IsPublic='Yes'\r\n" + 
				"GROUP BY QuestionBankName";
        //String []str=new String[]{User.userName};
        ResultSet rs=questionbank.Search(sql, null);
        //�����ݿ��л�ò�ѯ���
        try {
        	 while(rs.next()) {
             	 //System.out.println(rs.getString(2));
        		 qname[i]=rs.getString(2);
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
        return qname;
	}
	
	public String[] getQBtype() {//��ѯÿ������ڵ���Ŀ����
		int i=0;
		String[] qtype = new String[getQBnum()];
		DBUtil questionbank = new DBUtil();
		String sql="SELECT DISTINCT questionbankname,questionbanktype FROM question where Owner='guest' and IsPublic='Yes'";
        //String []str=new String[]{User.userName};
        ResultSet rs=questionbank.Search(sql, null);
        //�����ݿ��л�ò�ѯ���
        try {
        	 while(rs.next()) {
             	//System.out.println(rs.getString(2));
             	qtype[i]=rs.getString(2);
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
        return qtype;
	}
	
    
	
    /*
	 * ����͸ղ�һ��������������ÿ�����ݵ�ֵ
	 */
	String[] columnNames = { "�����", "������", "��Ŀ����", "��Ŀ����", "ѡ��" };
	Object[][] data = new Object[getQBnum()][5];

	/**
	 * ���췽������ʼ����ά����data��Ӧ������
	 */
	public PublicTableModel()
	{
		int qbnum=getQBnum();
		String []qbname=getQBname();
		String []qnum=getQnum();
		String []qtype=getQBtype();
		for(int i=0;i<qbnum;i++) {//i����
			data[i][0]=qbname[i];//��һ�������
			data[i][1]="����";//�ڶ����û���
			data[i][2]=qnum[i];//��������Ŀ����
			data[i][3]=qtype[i];//��������Ŀ����
			if(PublicQBJtable.SelectedQB[i]!=null)
			data[i][4]=new Boolean(true);//������ѡ��
			else
				data[i][4]=new Boolean(false);
		}
		
	}

	// ����Ϊ�̳���AbstractTableModle�ķ����������Զ���
	/**
	 * �õ�����
	 */
	@Override
	public String getColumnName(int column)
	{
		return columnNames[column];
	}
	
	/**
	 * ��д�������õ��������
	 */
	@Override
	public int getColumnCount()
	{
		return columnNames.length;
	}

	/**
	 * �õ��������
	 */
	@Override
	public int getRowCount()
	{
		return data.length;
	}

	/**
	 * �õ���������Ӧ����
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		return data[rowIndex][columnIndex];
	}

	/**
	 * �õ�ָ���е���������
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		return data[0][columnIndex].getClass();
	}
	
	/** 
     * ָ���������ݵ�Ԫ�Ƿ�ɱ༭.��������ǰ4�в��ɱ༭ 
     */  
	public boolean isCellEditable(int rowIndex, int columnIndex)  
    {  
        if (columnIndex < 4)  
            return false;  
        else  
            return true;  
    }  
	
	/** 
     * ������ݵ�ԪΪ�ɱ༭���򽫱༭���ֵ�滻ԭ����ֵ 
     */  
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex)
	{
		data[rowIndex][columnIndex] = aValue;
		/*֪ͨ���������ݵ�Ԫ�����Ѿ��ı�*/
		//boolean i = new Boolean(data[rowIndex][0].toString());
		if(PublicQBJtable.ChangedQB[rowIndex]==false) {
			PublicQBJtable.SelectedQB[rowIndex]=(String) data[rowIndex][0];
			PublicQBJtable.QBType[rowIndex]=(String) data[rowIndex][3];
			PublicQBJtable.ChangedQB[rowIndex]=true;
		}else {
			PublicQBJtable.SelectedQB[rowIndex]=null;
			PublicQBJtable.ChangedQB[rowIndex]=false;
		}
		
		

		
	}

}

package publicQBUI;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

import JDBC.DBUtil;
import user.User;

public class PublicTableModel extends AbstractTableModel
{
	
	public int getQBnum() {//统计题库数
		//先查找出现了几个不同的题库，然后根据题库数量生成对应格数的jtable
		//SELECT DISTINCT COUNT(DISTINCT questionbankname) FROM question where Owner='xxx'	统计题库数
		int qbnum = 0;//题库数量
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

			
	public String[] getQBname() {//查询题库名
		//SELECT DISTINCT questionbankname FROM question where Owner='xxx'  查询题库名
		int i=0;
		String[] qbname = new String[getQBnum()];
		DBUtil questionbank = new DBUtil();
		String sql="SELECT DISTINCT questionbankname FROM question where Owner='guest' and IsPublic='Yes'";
        //String []str=new String[]{User.userName};
        ResultSet rs=questionbank.Search(sql, null);
        //从数据库中获得查询结果
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
	
	
	public String[] getQnum() {//查询每个题库内的题目数
		int i=0;
		String[] qname = new String[getQBnum()];
		DBUtil questionbank = new DBUtil();
		String sql="SELECT DISTINCT QuestionBankName,COUNT(DISTINCT QuestionID) FROM question \r\n" + 
				"WHERE Owner='guest' and IsPublic='Yes'\r\n" + 
				"GROUP BY QuestionBankName";
        //String []str=new String[]{User.userName};
        ResultSet rs=questionbank.Search(sql, null);
        //从数据库中获得查询结果
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
	
	public String[] getQBtype() {//查询每个题库内的题目类型
		int i=0;
		String[] qtype = new String[getQBnum()];
		DBUtil questionbank = new DBUtil();
		String sql="SELECT DISTINCT questionbankname,questionbanktype FROM question where Owner='guest' and IsPublic='Yes'";
        //String []str=new String[]{User.userName};
        ResultSet rs=questionbank.Search(sql, null);
        //从数据库中获得查询结果
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
	 * 这里和刚才一样，定义列名和每个数据的值
	 */
	String[] columnNames = { "题库名", "所有者", "题目数量", "题目类型", "选中" };
	Object[][] data = new Object[getQBnum()][5];

	/**
	 * 构造方法，初始化二维数组data对应的数据
	 */
	public PublicTableModel()
	{
		int qbnum=getQBnum();
		String []qbname=getQBname();
		String []qnum=getQnum();
		String []qtype=getQBtype();
		for(int i=0;i<qbnum;i++) {//i是行
			data[i][0]=qbname[i];//第一列题库名
			data[i][1]="公用";//第二列用户名
			data[i][2]=qnum[i];//第三列题目数量
			data[i][3]=qtype[i];//第四列题目类型
			if(PublicQBJtable.SelectedQB[i]!=null)
			data[i][4]=new Boolean(true);//第五列选择
			else
				data[i][4]=new Boolean(false);
		}
		
	}

	// 以下为继承自AbstractTableModle的方法，可以自定义
	/**
	 * 得到列名
	 */
	@Override
	public String getColumnName(int column)
	{
		return columnNames[column];
	}
	
	/**
	 * 重写方法，得到表格列数
	 */
	@Override
	public int getColumnCount()
	{
		return columnNames.length;
	}

	/**
	 * 得到表格行数
	 */
	@Override
	public int getRowCount()
	{
		return data.length;
	}

	/**
	 * 得到数据所对应对象
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		return data[rowIndex][columnIndex];
	}

	/**
	 * 得到指定列的数据类型
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		return data[0][columnIndex].getClass();
	}
	
	/** 
     * 指定设置数据单元是否可编辑.这里设置前4列不可编辑 
     */  
	public boolean isCellEditable(int rowIndex, int columnIndex)  
    {  
        if (columnIndex < 4)  
            return false;  
        else  
            return true;  
    }  
	
	/** 
     * 如果数据单元为可编辑，则将编辑后的值替换原来的值 
     */  
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex)
	{
		data[rowIndex][columnIndex] = aValue;
		/*通知监听器数据单元数据已经改变*/
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

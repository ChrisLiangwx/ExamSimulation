package JDBC;
import java.io.File;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExportDataToExcel {

    public static void main(String[] args) {
        try {
            WritableWorkbook wwb = null;
             
               // 创建可写入的Excel工作簿
               String fileName = "D://桌面//错题.xls";
               File file=new File(fileName);
               if (!file.exists()) {
                   file.createNewFile();
               }
               //以fileName为文件名来创建一个Workbook
               wwb = Workbook.createWorkbook(file);

               // 创建工作表
               WritableSheet ws = wwb.createSheet("Sheet1", 0);
               
               //查询数据库中所有的数据
               List<QuestionEntity> list= QuestionService.getAllByDb();
               //要插入到的Excel表格的行号，默认从0开始
               Label labelQuestionId= new Label(0, 0, "题号");
               Label labelQuestionStem= new Label(1, 0, "题干");
               Label labelA= new Label(2, 0, "A");
               Label labelB= new Label(3, 0, "B");
               Label labelC= new Label(4, 0, "C");
               Label labelD= new Label(5, 0, "D");
               Label labelAnswer= new Label(6, 0, "答案");
               
               ws.addCell(labelQuestionId);
               ws.addCell(labelQuestionStem);
               ws.addCell(labelA);
               ws.addCell(labelB);
               ws.addCell(labelC);
               ws.addCell(labelD);
               ws.addCell(labelAnswer);
               
               for (int i = 0; i < list.size(); i++) {
                   Label labelQuestionId_i= new Label(0, i+1, list.get(i).getQuestionID()+"");
                   Label labelQuestionStem_i= new Label(1, i+1, list.get(i).getQuestionStem()+"");
                   Label labelA_i= new Label(2, i+1, list.get(i).getA()+"");
                   Label labelB_i= new Label(3, i+1, list.get(i).getB()+"");
                   Label labelC_i= new Label(4, i+1, list.get(i).getC()+"");
                   Label labelD_i= new Label(5, i+1, list.get(i).getD()+"");
                   Label labelAnswer_i= new Label(6, i+1, list.get(i).getAnswer()+"");
                   
                   
                   ws.addCell(labelQuestionId_i);
                   ws.addCell(labelQuestionStem_i);
                   ws.addCell(labelA_i);
                   ws.addCell(labelB_i);
                   ws.addCell(labelC_i);
                   ws.addCell(labelD_i);
                   ws.addCell(labelAnswer_i);
            	   
               }
               System.out.println("已创建文档");
              //写进文档
               wwb.write();
              // 关闭Excel工作簿对象
               wwb.close();
               
             
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }
}
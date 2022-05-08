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
             
               // ������д���Excel������
               String fileName = "D://����//����.xls";
               File file=new File(fileName);
               if (!file.exists()) {
                   file.createNewFile();
               }
               //��fileNameΪ�ļ���������һ��Workbook
               wwb = Workbook.createWorkbook(file);

               // ����������
               WritableSheet ws = wwb.createSheet("Sheet1", 0);
               
               //��ѯ���ݿ������е�����
               List<QuestionEntity> list= QuestionService.getAllByDb();
               //Ҫ���뵽��Excel�����кţ�Ĭ�ϴ�0��ʼ
               Label labelQuestionId= new Label(0, 0, "���");
               Label labelQuestionStem= new Label(1, 0, "���");
               Label labelA= new Label(2, 0, "A");
               Label labelB= new Label(3, 0, "B");
               Label labelC= new Label(4, 0, "C");
               Label labelD= new Label(5, 0, "D");
               Label labelAnswer= new Label(6, 0, "��");
               
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
               System.out.println("�Ѵ����ĵ�");
              //д���ĵ�
               wwb.write();
              // �ر�Excel����������
               wwb.close();
               
             
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }
}
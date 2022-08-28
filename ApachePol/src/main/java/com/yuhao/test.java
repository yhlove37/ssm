package com.yuhao;



import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class test {


//    從excel中讀取文件
    @Test
    public  void  test1() throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook("C:\\Users\\yh139\\Desktop\\工作簿1.xlsx");
//获取工作表，既可以根据工作表的顺序获取，也可以根据工作表的名称获取
        XSSFSheet sheet = workbook.getSheetAt(0);
//遍历工作表获得行对象
        for (Row row : sheet) {
            //遍历行对象获取单元格对象
            for (Cell cell : row) {
                //获得单元格中的值
                String value = cell.getStringCellValue();
                System.out.println(value);
            }
        }
        workbook.close();
}



@Test
public  void test2() throws IOException {
    //在内存中创建一个Excel文件
    XSSFWorkbook workbook = new XSSFWorkbook();
//创建工作表，指定工作表名称
    XSSFSheet sheet = workbook.createSheet("传智播客");

//创建行，0表示第一行
    XSSFRow row = sheet.createRow(0);
//创建单元格，0表示第一个单元格
    row.createCell(0).setCellValue("编号");
    row.createCell(1).setCellValue("名称");
    row.createCell(2).setCellValue("年龄");

    XSSFRow row1 = sheet.createRow(1);
    row1.createCell(0).setCellValue("1");
    row1.createCell(1).setCellValue("小明");
    row1.createCell(2).setCellValue("10");

    XSSFRow row2 = sheet.createRow(2);
    row2.createCell(0).setCellValue("2");
    row2.createCell(1).setCellValue("小王");
    row2.createCell(2).setCellValue("20");

//通过输出流将workbook对象下载到磁盘
    FileOutputStream out = new FileOutputStream("C:\\Users\\yh139\\Desktop\\工作簿2.xlsx");
    workbook.write(out);
    out.flush();
    out.close();
    workbook.close();
//    通過輸入流

}

}

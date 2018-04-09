package com.skyfaith.util;

import com.skyfaith.domain.EmsOrder;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExcelHelper {
    public static List<EmsOrder> ImportEmsOrderInfo1(String xlsPath) throws IOException {
        List temp = new ArrayList();
        FileInputStream fileIn = new FileInputStream(xlsPath);
        //根据指定的文件输入流导入Excel从而产生Workbook对象
        Workbook wb0;
        String ext = xlsPath.substring(xlsPath.lastIndexOf(".") + 1);
        if (ext.equals("xls")) {
            wb0 = new HSSFWorkbook(fileIn);
        } else {
            ZipSecureFile.setMinInflateRatio(-1.0d);
            wb0 = new XSSFWorkbook(fileIn);
//            wb0 = new HSSFWorkbook(fileIn);
        }
        //获取Excel文档中的第一个表单
        Sheet sht0 = wb0.getSheetAt(0);
        //对Sheet中的每一行进行迭代
        for (Row r : sht0) {
            //如果当前行的行号（从0开始）未达到2（第三行）则从新循环
            if (r.getRowNum() < 1) {
                continue;
            }
            //创建实体类
            EmsOrder info = new EmsOrder();
            //取出当前行第1个单元格数据，并封装在info实体stuName属性上
            info.setSender(r.getCell(0).getStringCellValue());
            info.setSenderphone(r.getCell(1).getStringCellValue());
            info.setSenderaddress(r.getCell(2).getStringCellValue());
            info.setReceiver(r.getCell(3).getStringCellValue());
            info.setReceiverphone(r.getCell(4).getStringCellValue());
            info.setReceiveraddress(r.getCell(5).getStringCellValue());
            info.setBrandname(r.getCell(6).getStringCellValue());
            info.setWeight(r.getCell(7).getStringCellValue());
            String v = r.getCell(8).getStringCellValue();
            if (!v.isEmpty()){
                info.setDeclaredvalue(BigDecimal.valueOf(r.getCell(8).getNumericCellValue()));
            }
//            info.setDeclaredvalue(BigDecimal.valueOf(r.getCell(8).getNumericCellValue()));
            info.setPosttalcode(r.getCell(9).getStringCellValue());
            info.setOrderno(r.getCell(10).getStringCellValue());
            info.setSenderland(r.getCell(11).getStringCellValue());
            info.setClearanceport(r.getCell(12).getStringCellValue());

            temp.add(info);
        }
        fileIn.close();
        return temp;
    }

    public static List<EmsOrder> ImportEmsOrderInfo2(String xlsPath) throws IOException {
        List temp = new ArrayList();
        FileInputStream fileIn = new FileInputStream(xlsPath);
        //根据指定的文件输入流导入Excel从而产生Workbook对象
        Workbook wb0;
        String ext = xlsPath.substring(xlsPath.lastIndexOf(".") + 1);

        if (ext.equals("xls")) {
            wb0 = new HSSFWorkbook(fileIn);
        } else {
            ZipSecureFile.setMinInflateRatio(-1.0d);
            wb0 = new XSSFWorkbook(fileIn);
//            wb0 = new HSSFWorkbook(fileIn);
        }
        //获取Excel文档中的第一个表单
        Sheet sht0 = wb0.getSheetAt(0);
        //对Sheet中的每一行进行迭代
        for (Row r : sht0) {
            //如果当前行的行号（从0开始）未达到2（第三行）则从新循环
            if (r.getRowNum() < 1) {
                continue;
            }
            //创建实体类
            EmsOrder info = new EmsOrder();
            //取出当前行第1个单元格数据，并封装在info实体stuName属性上
            info.setEorderno(r.getCell(0).getStringCellValue());
            temp.add(info);
        }
        fileIn.close();
        return temp;
    }
}

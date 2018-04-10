package com.skyfaith.util;

import com.skyfaith.domain.EmsOrder;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ExcelHelper {
    public static List<EmsOrder> ImportEmsOrderInfo1(String xlsPath) throws Exception {
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
                if (r.getLastCellNum() != 13) {
                    throw new Exception("导入的文件模板不正确");
                }
                continue;
            }
            //创建实体类
            EmsOrder info = new EmsOrder();
            //取出当前行第1个单元格数据，并封装在info实体stuName属性上
            info.setSender(getCellValue(r.getCell(0)));
            info.setSenderphone(getCellValue(r.getCell(1)));

            info.setSenderaddress(getCellValue(r.getCell(2)));
            info.setReceiver(getCellValue(r.getCell(3)));
            info.setReceiverphone(getCellValue(r.getCell(4)));

            info.setReceiveraddress(getCellValue(r.getCell(5)));
            info.setBrandname(getCellValue(r.getCell(6)));
            info.setWeight(getCellValue(r.getCell(7)));
            String tmp = getCellValue(r.getCell(8));
            if (!tmp.isEmpty()) {
                info.setDeclaredvalue(BigDecimal.valueOf(Double.valueOf(tmp)));
            }
            info.setPosttalcode(getCellValue(r.getCell(9)));
            info.setOrderno(getCellValue(r.getCell(10)));
            info.setSenderland(getCellValue(r.getCell(11)));
            info.setClearanceport(getCellValue(r.getCell(12)));

            temp.add(info);
        }
        fileIn.close();
        return temp;
    }

    private static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }

        DecimalFormat df = new DecimalFormat("#");
        switch (cell.getCellTypeEnum()) {
            case STRING:
                cellValue = cell.getRichStringCellValue().getString().trim();
                break;
            case NUMERIC:
                cellValue = df.format(cell.getNumericCellValue()).toString();
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
                break;
            case FORMULA:
                cellValue = cell.getCellFormula();
                break;
            default:
        }
        return cellValue;
    }

    public static List<EmsOrder> ImportEmsOrderInfo2(String xlsPath) throws Exception {
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
                if (r.getLastCellNum() != 1) {
                    throw new Exception("导入的文件模板不正确");
                }
                continue;
            }
            //创建实体类
            EmsOrder info = new EmsOrder();
            //取出当前行第1个单元格数据，并封装在info实体stuName属性上
            info.setEorderno(getCellValue(r.getCell(0)));
            temp.add(info);
        }
        fileIn.close();
        return temp;
    }
}

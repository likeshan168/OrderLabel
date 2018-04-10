package com.skyfaith.controller;

import com.skyfaith.MainApp;
import com.skyfaith.domain.EmsOrder;
import com.skyfaith.service.EmsOrderService;
import com.skyfaith.util.BarCodeUtils;
import com.skyfaith.util.ExcelHelper;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@FXMLController
public class MainStageController implements Initializable {
//public class MainStageController {

    private ObservableList<EmsOrder> orders = FXCollections.observableArrayList();
    @Resource
    private EmsOrderService emsOrderService;

    @FXML
    private TextField orderNoField;

    @FXML
    private TableView<EmsOrder> orderTable;

    @FXML
    private TableColumn<EmsOrder, String> orderNoColumn;

    private XSSFWorkbook workbook;

    //显示打印的标签
//    @FXML
//    private ImageView emsOrderNoImageView;
    @FXML
    private Label receiverLabel;
    @FXML
    private Label receiverPhoneLabel;
    @FXML
    private Label receiverAddressLabel;
    @FXML
    private Label receiverLabel2;
    @FXML
    private Label receiverPhoneLabel2;
    @FXML
    private Label receiverAddressLabel2;
    @FXML
    private Label descriptionLabel;
    //    @FXML
//    private Label receiverSignature;
    @FXML
    private Label weightLabel;
    @FXML
    private Label valueLabel;
    @FXML
    private Label originLabel;
    @FXML
    private Label senderLabel;
    @FXML
    private Label senderPhoneLabel;
    @FXML
    private Label senderAddressLabel;
    @FXML
    private Label customerCodeLabel;
    @FXML
    private Label orderNoLabel;
    @FXML
    private Label eorderNoLabel1;
    @FXML
    private Label eorderNoLabel2;
    @FXML
    private Label portLabel;
    @FXML
    private Label yanshiLabel;

    @FXML
    private TextField customeCodeField;
    @FXML
    private TextField yanshiField;

    @FXML
    private ImageView emsOrderNoBarCode;
    @FXML
    private ImageView emsOrderNoBarCode2;

    @FXML
    private Label avaliableEmsOrderCount;

    @FXML
    private Pane emsLabel;


    public void initialize(URL location, ResourceBundle resources) {
        orderNoColumn.setCellValueFactory(cellData -> cellData.getValue().ordernoProperty());

        orders.addAll(emsOrderService.getOrderList());
        avaliableEmsOrderCount.setText(String.valueOf(emsOrderService.getAvaliableEmsOrderCount()));
        orderTable.setItems(orders);
        showEMSLabel(null);
        orderTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    showEMSLabel(newValue);
                }
        );
    }

    @FXML
    private void handleOrderNoChange() {

        if (orderNoField.getText().isEmpty()) {
            //获取所有的数据
            orders.clear();
            orders.addAll(emsOrderService.getOrderList());
//            orderTable.setItems(orders);
            return;
        }

        if (customeCodeField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("提示");
            alert.setHeaderText("错误信息");
            alert.setContentText("客户编码不能为空！");
            alert.show();
            return;
        } else if (yanshiField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("提示");
            alert.setHeaderText("错误信息");
            alert.setContentText("验视的内容不能为空！");
            alert.show();
            return;
        }
        //搜索
        EmsOrder order = emsOrderService.searchEmsOrderByOrderNo(this.orderNoField.getText());
        if (order == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("消息");
            alert.setHeaderText("提示信息");
            alert.setContentText("没有找到该订单信息");
            alert.showAndWait();
        } else {
            orders.clear();
            orders.add(order);
//            orderTable.setItems(orders);
            //showEMSLabel(order);
            orderTable.getSelectionModel().select(order);
            printPage();
        }
    }

    private void showEMSLabel(EmsOrder emsOrder) {
        if (emsOrder != null) {
//            if (customeCodeField.getText().isEmpty()) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("提示");
//                alert.setHeaderText("错误信息");
//                alert.setContentText("客户编码不能为空！");
//                alert.show();
//            } else if (customeCodeField.getText().isEmpty()) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("提示");
//                alert.setHeaderText("错误信息");
//                alert.setContentText("验视的内容不能为空！");
//                alert.show();
//
//            } else {
            receiverLabel.setText(emsOrder.getReceiver() == null ? "" : emsOrder.getReceiver());
            receiverPhoneLabel.setText(emsOrder.getReceiverphone() == null ? "" : emsOrder.getReceiverphone());
            receiverAddressLabel.setText(emsOrder.getReceiveraddress() == null ? "" : emsOrder.getReceiveraddress());
//            receiverSignature.setText(emsOrder.getrece());
            descriptionLabel.setText(emsOrder.getBrandname() == null ? "" : emsOrder.getBrandname());
            weightLabel.setText(emsOrder.getWeight() == null ? "" : emsOrder.getWeight());
            valueLabel.setText(emsOrder.getDeclaredvalue() == null ? "" : emsOrder.getDeclaredvalue().toString());
            originLabel.setText(emsOrder.getSenderland() == null ? "" : emsOrder.getSenderland());
            senderAddressLabel.setText(emsOrder.getSenderaddress() == null ? "" : emsOrder.getSenderaddress());
            senderLabel.setText(emsOrder.getSender() == null ? "" : emsOrder.getSender());
            senderPhoneLabel.setText(emsOrder.getSenderphone() == null ? "" : emsOrder.getSenderphone());
            receiverAddressLabel2.setText(emsOrder.getReceiveraddress() == null ? "" : emsOrder.getReceiveraddress());
            receiverLabel2.setText(emsOrder.getReceiverphone() == null ? "" : emsOrder.getReceiver());
            receiverPhoneLabel2.setText(emsOrder.getReceiverphone() == null ? "" : emsOrder.getReceiverphone());
//            customerCodeLabel
            orderNoLabel.setText(emsOrder.getOrderno() == null ? "" : emsOrder.getOrderno());
            portLabel.setText(emsOrder.getClearanceport() == null ? "" : emsOrder.getClearanceport());
            customerCodeLabel.setText(customeCodeField.getText());
            yanshiLabel.setText(yanshiField.getText());
//            BarCodeUtils.encode(emsOrder.getEorderno(), 200, 65,"/images/"+emsOrder.getEorderno()+".png");
//            emsOrderNoImageView.setImage(new Image("/images/"+emsOrder.getEorderno()+".png"));


            WritableImage wr = BarCodeUtils.encode(emsOrder.getEorderno(), 200, 65);
            emsOrderNoBarCode.setImage(wr);
            emsOrderNoBarCode2.setImage(wr);
            eorderNoLabel1.setText(emsOrder.getEorderno() == null ? "" : emsOrder.getEorderno());
            eorderNoLabel2.setText(emsOrder.getEorderno() == null ? "" : emsOrder.getEorderno());
//            }

        } else {
            receiverLabel.setText("");
            receiverPhoneLabel.setText("");
            receiverAddressLabel.setText("");
//            receiverSignature.setText(emsOrder.getrece());
            descriptionLabel.setText("");
            weightLabel.setText("");
            valueLabel.setText("");
            originLabel.setText("");
            eorderNoLabel1.setText("");
            eorderNoLabel2.setText("");
            senderAddressLabel.setText("");
            senderLabel.setText("");
            senderPhoneLabel.setText("");
            receiverAddressLabel2.setText("");
            receiverLabel2.setText("");
            receiverPhoneLabel2.setText("");
//            customerCodeLabel
            orderNoLabel.setText("");
            portLabel.setText("");
            customerCodeLabel.setText("");
            yanshiLabel.setText("");
        }
    }

    @FXML
    private void importOrderList() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("导入清单列表");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                    "Excel files (*.xls|*.xlsx)", "*.xls", "*.xlsx");

            fileChooser.getExtensionFilters().add(extFilter);

            File file = fileChooser.showOpenDialog(MainApp.getStage());

            if (file != null) {
                List<EmsOrder> list = ExcelHelper.ImportEmsOrderInfo1(file.getPath());
                if (emsOrderService.updateOrderList(list)) {
                    avaliableEmsOrderCount.setText(String.valueOf(emsOrderService.getAvaliableEmsOrderCount()));
                    orders.clear();
                    orders.addAll(emsOrderService.getOrderList());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("消息");
                    alert.setHeaderText("提示信息");
                    alert.setContentText("导入清单列表成功");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("消息");
                    alert.setHeaderText("错误信息");
                    alert.setContentText("导入清单列表失败, 请查看是否有足够的EMS的快递单号");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("提示");
            alert.setHeaderText("错误信息");
            alert.setContentText(String.format("导入清单列表失败:%s", e.getMessage()));
            alert.showAndWait();
            e.printStackTrace();
        }

    }

    @FXML
    private void importEorderList() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("导入Ems快递单号");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                    "Excel files (*.xls|*.xlsx)", "*.xls", "*.xlsx");

            fileChooser.getExtensionFilters().add(extFilter);

            File file = fileChooser.showOpenDialog(MainApp.getStage());

            if (file != null) {
                List<EmsOrder> list = ExcelHelper.ImportEmsOrderInfo2(file.getPath());
                if (emsOrderService.updateEorderList(list)) {
                    avaliableEmsOrderCount.setText(String.valueOf(emsOrderService.getAvaliableEmsOrderCount()));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("消息");
                    alert.setHeaderText("提示信息");
                    alert.setContentText("导入EMS订单成功");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("消息");
                    alert.setHeaderText("错误信息");
                    alert.setContentText("导入EMS订单失败");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("消息");
            alert.setHeaderText("错误信息");
            alert.setContentText(String.format("导入EMS订单异常:%s", e.getMessage()));
            alert.showAndWait();
        }
    }

    @FXML
    private void handleExportExcel() {
        File file = saveFile("导出清单列表", "订单列表.xlsx");
        if (file != null) {
            exportExcel(file.getAbsolutePath());
        }
    }

    @FXML
    private void handleOutputOrderListTemplate() {
        File file = saveFile("下载订单模板", "订单模板.xlsx");
        if (file != null) {
            exportOrderListTemplate(file.getAbsolutePath());
        }
    }

    @FXML
    private void handleOutputEOrderListTemplate() {
        File file = saveFile("下载EMS快递单号模板", "EMS快递单号模板.xlsx");
        if (file != null) {
            exportEOrderListTemplate(file.getAbsolutePath());
        }
    }

    private File saveFile(String title, String fileName) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.setInitialFileName(fileName);
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "Excel files (*.xls|*.xlsx)", "*.xls", "*.xlsx");

        fileChooser.getExtensionFilters().add(extFilter);
        return fileChooser.showSaveDialog(MainApp.getStage());
    }

    private void exportExcel(String fileName) {
        // Declare a work sheet
        workbook = new XSSFWorkbook();
        // Generate a form
        XSSFSheet sheet = workbook.createSheet("订单信息");
        // Set the table for 15 byte default column width
        sheet.setDefaultColumnWidth((short) 15);
        // Create a style
        XSSFCellStyle style = workbook.createCellStyle();
        // The style settings
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        // Create a font
        XSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        // The font applied to the current style
        style.setFont(font);
        // Create first row
        XSSFRow row = sheet.createRow(0);
        //Create first column cell
        XSSFCell cell1 = row.createCell(0);
        cell1.setCellType(CellType.STRING);
        cell1.setCellValue(new XSSFRichTextString("寄件人"));
        //Create second column cell
        XSSFCell cell2 = row.createCell(1);
        cell2.setCellType(CellType.STRING);
        cell2.setCellValue(new XSSFRichTextString("寄件人电话"));
        //Create thrid column cell
        XSSFCell cell3 = row.createCell(2);
        cell3.setCellType(CellType.STRING);
        cell3.setCellValue(new XSSFRichTextString("地址"));
        //Create four column cell
        XSSFCell cell4 = row.createCell(3);
        cell4.setCellType(CellType.STRING);
        cell4.setCellValue("收件人");
        //Create five column cell
        XSSFCell cell5 = row.createCell(4);
        cell5.setCellType(CellType.STRING);
        cell5.setCellValue(new XSSFRichTextString("收件人电话"));
        //Create six column cell
        XSSFCell cell6 = row.createCell(5);
        cell6.setCellType(CellType.STRING);
        cell6.setCellValue("收件人地址");
        //Create seven column cell
        XSSFCell cell7 = row.createCell(6);
        cell7.setCellType(CellType.STRING);
        cell7.setCellValue(new XSSFRichTextString("品名"));
        XSSFCell cell8 = row.createCell(7);
        cell8.setCellType(CellType.STRING);
        cell8.setCellValue(new XSSFRichTextString("重量"));
        XSSFCell cell9 = row.createCell(8);
        cell9.setCellType(CellType.NUMERIC);
        cell9.setCellValue(new XSSFRichTextString("申报价值"));
        XSSFCell cell10 = row.createCell(9);
        cell10.setCellType(CellType.NUMERIC);
        cell10.setCellValue(new XSSFRichTextString("邮编"));
        XSSFCell cell11 = row.createCell(10);
        cell11.setCellType(CellType.NUMERIC);
        cell11.setCellValue(new XSSFRichTextString("联单号"));
        XSSFCell cell12 = row.createCell(11);
        cell12.setCellType(CellType.NUMERIC);
        cell12.setCellValue(new XSSFRichTextString("寄地"));
        XSSFCell cell13 = row.createCell(12);
        cell13.setCellType(CellType.NUMERIC);
        cell13.setCellValue(new XSSFRichTextString("清关口岸"));
        XSSFCell cell14 = row.createCell(13);
        cell14.setCellType(CellType.NUMERIC);
        cell14.setCellValue(new XSSFRichTextString("快递单号"));

        List<EmsOrder> orders = emsOrderService.getOrderList();
        for (int i = 0; i < orders.size(); i++) {
            EmsOrder order = orders.get(i);
            XSSFRow datarow = sheet.createRow(i + 1);

            XSSFCell datacell1 = datarow.createCell(0);
            datacell1.setCellType(CellType.STRING);
            datacell1.setCellValue(order.getSender());

            XSSFCell datacell2 = datarow.createCell(1);
            datacell2.setCellType(CellType.STRING);
            datacell2.setCellValue(order.getSenderphone());

            XSSFCell datacell3 = datarow.createCell(2);
            datacell3.setCellType(CellType.STRING);
            datacell3.setCellValue(order.getSenderaddress());

            XSSFCell datacell4 = datarow.createCell(3);
            datacell4.setCellType(CellType.STRING);
            datacell4.setCellValue(order.getReceiver());

            XSSFCell datacell5 = datarow.createCell(4);
            datacell5.setCellType(CellType.STRING);
            datacell5.setCellValue(order.getReceiverphone());

            XSSFCell datacell6 = datarow.createCell(5);
            datacell6.setCellType(CellType.STRING);
            datacell6.setCellValue(order.getReceiveraddress());

            XSSFCell datacell7 = datarow.createCell(6);
            datacell7.setCellType(CellType.STRING);
            datacell7.setCellValue(order.getBrandname());

            XSSFCell datacell8 = datarow.createCell(7);
            datacell8.setCellType(CellType.STRING);
            datacell8.setCellValue(order.getWeight());

            XSSFCell datacell9 = datarow.createCell(8);
            datacell9.setCellType(CellType.STRING);
            datacell9.setCellValue(String.valueOf(order.getDeclaredvalue()));

            XSSFCell datacell10 = datarow.createCell(9);
            datacell10.setCellType(CellType.STRING);
            datacell10.setCellValue(order.getPosttalcode());

            XSSFCell datacell11 = datarow.createCell(10);
            datacell11.setCellType(CellType.STRING);
            datacell11.setCellValue(order.getOrderno());

            XSSFCell datacell12 = datarow.createCell(11);
            datacell12.setCellType(CellType.STRING);
            datacell12.setCellValue(order.getSenderland());

            XSSFCell datacell13 = datarow.createCell(12);
            datacell13.setCellType(CellType.STRING);
            datacell13.setCellValue(order.getClearanceport());

            XSSFCell datacell14 = datarow.createCell(13);
            datacell14.setCellType(CellType.STRING);
            datacell14.setCellValue(order.getClearanceport());
        }

        outputExcel(fileName);
    }

    private void exportOrderListTemplate(String fileName) {
        // Declare a work sheet
        workbook = new XSSFWorkbook();
        // Generate a form
        XSSFSheet sheet = workbook.createSheet("订单信息");
        // Set the table for 15 byte default column width
        sheet.setDefaultColumnWidth((short) 15);
        // Create a style
        XSSFCellStyle style = workbook.createCellStyle();
        // The style settings
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        // Create a font
        XSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        // The font applied to the current style
        style.setFont(font);
        // Create first row
        XSSFRow row = sheet.createRow(0);
        //Create first column cell
        XSSFCell cell1 = row.createCell(0);
        cell1.setCellType(CellType.STRING);
        cell1.setCellValue(new XSSFRichTextString("寄件人"));
        //Create second column cell
        XSSFCell cell2 = row.createCell(1);
        cell2.setCellType(CellType.STRING);
        cell2.setCellValue(new XSSFRichTextString("寄件人电话"));
        //Create thrid column cell
        XSSFCell cell3 = row.createCell(2);
        cell3.setCellType(CellType.STRING);
        cell3.setCellValue(new XSSFRichTextString("地址"));
        //Create four column cell
        XSSFCell cell4 = row.createCell(3);
        cell4.setCellType(CellType.STRING);
        cell4.setCellValue("收件人");
        //Create five column cell
        XSSFCell cell5 = row.createCell(4);
        cell5.setCellType(CellType.STRING);
        cell5.setCellValue(new XSSFRichTextString("收件人电话"));
        //Create six column cell
        XSSFCell cell6 = row.createCell(5);
        cell6.setCellType(CellType.STRING);
        cell6.setCellValue("收件人地址");
        //Create seven column cell
        XSSFCell cell7 = row.createCell(6);
        cell7.setCellType(CellType.STRING);
        cell7.setCellValue(new XSSFRichTextString("品名"));
        XSSFCell cell8 = row.createCell(7);
        cell8.setCellType(CellType.STRING);
        cell8.setCellValue(new XSSFRichTextString("重量"));
        XSSFCell cell9 = row.createCell(8);
        cell9.setCellType(CellType.NUMERIC);
        cell9.setCellValue(new XSSFRichTextString("申报价值"));
        XSSFCell cell10 = row.createCell(9);
        cell10.setCellType(CellType.NUMERIC);
        cell10.setCellValue(new XSSFRichTextString("邮编"));
        XSSFCell cell11 = row.createCell(10);
        cell11.setCellType(CellType.NUMERIC);
        cell11.setCellValue(new XSSFRichTextString("联单号"));
        XSSFCell cell12 = row.createCell(11);
        cell12.setCellType(CellType.NUMERIC);
        cell12.setCellValue(new XSSFRichTextString("寄地"));
        XSSFCell cell13 = row.createCell(12);
        cell13.setCellType(CellType.NUMERIC);
        cell13.setCellValue(new XSSFRichTextString("清关口岸"));

        outputExcel(fileName);
    }

    private void exportEOrderListTemplate(String fileName) {
        // Declare a work sheet
        workbook = new XSSFWorkbook();
        // Generate a form
        XSSFSheet sheet = workbook.createSheet("快递单号信息");
        // Set the table for 15 byte default column width
        sheet.setDefaultColumnWidth((short) 15);
        // Create a style
        XSSFCellStyle style = workbook.createCellStyle();
        // The style settings
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        // Create a font
        XSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        // The font applied to the current style
        style.setFont(font);
        // Create first row
        XSSFRow row = sheet.createRow(0);
        //Create first column cell
        XSSFCell cell1 = row.createCell(0);
        cell1.setCellType(CellType.STRING);
        cell1.setCellValue(new XSSFRichTextString("快递单号"));
        outputExcel(fileName);
    }

    public void outputExcel(String fileName) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(fileName));
            workbook.write(fos);
            fos.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setHeaderText("成功信息");
            alert.setContentText("保存成功");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("提示");
            alert.setHeaderText("错误信息");
            alert.setContentText("保存失败");
            alert.showAndWait();
        }
    }

    @FXML
    private void printPage() {
        System.out.println("............开始打印..............");
        Printer printer = Printer.getDefaultPrinter();
        //设置打印纸张和打印样式（PageOrientation.LANDSCAPE：横版打印）
        PageLayout paper = printer.getDefaultPageLayout();
//        PageLayout paper = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
        //创建打印任务
        PrinterJob job = PrinterJob.createPrinterJob();
        JobSettings jobSetting = null;
        if (job != null) {
//            //调取打印页面
//            Scene scene = new Scene(createContent3());
//            //加载打印页面CSS样式
//            scene.getStylesheets().add(getClass().getResource("application_print.css").toExternalForm());
            jobSetting = job.getJobSettings();
            jobSetting.setCopies(1);   //设置一次打印张数
            job.printPage(paper, emsLabel);
            // job.printPage(createContent3());
            // job.printPage(getTableView());
            job.endJob();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("提示");
            alert.setHeaderText("错误信息");
            alert.setContentText("创建打印任务失败");
            alert.showAndWait();
        }
    }

    @FXML
    private void handlAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示");
        alert.setHeaderText("软件简介");
        alert.setContentText("该软件提供EMS面单打印的功能: 1. 先下载订单模板，填写数据再点击导入清单列表 2. 下载EMS模板，填写数据再点击导入EMS单号 3. 光标放在左上角输入框，扫描单号进行面单打印");
        alert.showAndWait();
    }
}

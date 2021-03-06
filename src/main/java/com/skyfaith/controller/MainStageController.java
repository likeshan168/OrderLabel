package com.skyfaith.controller;

import com.skyfaith.MainApp;
import com.skyfaith.domain.EmsOrder;
import com.skyfaith.domain.EmsOrderExample;
import com.skyfaith.service.EmsOrderService;
import com.skyfaith.util.BarCodeUtils;
import com.skyfaith.util.ExcelHelper;
import com.sun.javafx.print.Units;
import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.swing.text.DateFormatter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@FXMLController
public class MainStageController implements Initializable {
    //public class MainStageController {
    private final String pattern = "yyyy-MM-dd";

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
    private Group emsLabel;
    @FXML
    private Pane gridPane;
    @FXML
    private DatePicker datePicker;

    @FXML
    private AnchorPane acpane;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label printMsg;


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
        orderNoField.selectAll();
        if (orderNoField.getText().isEmpty()) {
            //获取所有的数据
            orders.clear();
            orders.addAll(emsOrderService.getOrderList());
//            orderTable.setItems(orders);
            return;
        }

        if (!checkRequiredField()) return;
        //搜索
        EmsOrder order = emsOrderService.searchEmsOrderByOrderNo(this.orderNoField.getText());
        if (order == null) {
            showMessageDialog(Alert.AlertType.INFORMATION, "提示", "提示信息", "没有找到该订单信息");
        } else {
            orders.clear();
            orders.add(order);
//            orderTable.setItems(orders);
            //showEMSLabel(order);
            orderTable.getSelectionModel().select(order);
            printPage();
            //更新打印时间
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            order.setPrintdate(ft.format(new Date()));
            emsOrderService.updateOrder(order);
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
                    showMessageDialog(Alert.AlertType.INFORMATION, "提示", "提示信息", "导入清单列表成功");
                } else {
                    showMessageDialog(Alert.AlertType.ERROR, "提示", "错误信息", "导入清单列表失败, 请查看是否有足够的EMS的快递单号");
                }
            }
        } catch (Exception e) {
            showMessageDialog(Alert.AlertType.ERROR, "提示", "错误信息", String.format("导入清单列表失败:%s", e.getMessage()));
        }

    }

    @FXML
    private void importOrderListAndBatchPrint() {
        try {
            if (!checkRequiredField()) return;
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("导入清单列表");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                    "Excel files (*.xls|*.xlsx)", "*.xls", "*.xlsx");

            fileChooser.getExtensionFilters().add(extFilter);

            File file = fileChooser.showOpenDialog(MainApp.getStage());

            if (file != null) {
                Task<Void> batchPrintTask = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        Platform.runLater(() -> {
                            try {
                                updateMessage("正在读取数据...");
                                List<EmsOrder> list = ExcelHelper.ImportEmsOrderInfo1(file.getPath());
                                updateMessage("正在导入数据...");
                                if (emsOrderService.updateOrderList(list)) {
                                    avaliableEmsOrderCount.setText(String.valueOf(emsOrderService.getAvaliableEmsOrderCount()));
                                    orders.clear();
                                    orders.addAll(emsOrderService.getOrderList());
                                    //批量打印
                                    updateMessage("正在打印标签...");
                                    BatchPrint(list);
                                } else {
                                    updateMessage("导入清单列表失败, 请查看是否有足够的EMS的快递单号");
                                }
                            } catch (Exception e) {
                                updateMessage(e.getMessage());
                            }
                        });

                        return null;
                    }
                };

                printMsg.textProperty().bind(batchPrintTask.messageProperty());
                batchPrintTask.setOnSucceeded(e -> {
                    printMsg.textProperty().unbind();
                    printMsg.setText("批量打印完成...");
                });

                Thread thread = new Thread(batchPrintTask);
                thread.setDaemon(true);
                thread.start();

            }
        } catch (Exception e) {
            showMessageDialog(Alert.AlertType.ERROR, "提示", "错误信息", String.format("导入清单列表失败:%s", e.getMessage()));
        }

    }

    //导出数据到pdf （暂时不用）
    private void ExportPDF(List<EmsOrder> orders) {
        PDDocument doc = new PDDocument();
        PDPage page;
        PDImageXObject pdimage;
        PDPageContentStream content;
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("选择导出pdf的文件夹路径");
        File chosenDir = directoryChooser.showDialog(MainApp.getStage());
        if (chosenDir != null) {
            try {
                String path = chosenDir.getAbsolutePath();

                for (EmsOrder order : orders) {
                    page = new PDPage();
                    showEMSLabel(order);
                    WritableImage nodeshot = emsLabel.snapshot(new SnapshotParameters(), null);
                    File file = new File(path + String.format("\\%s.png", order.getOrderno()));
                    ImageIO.write(SwingFXUtils.fromFXImage(nodeshot, null), "png", file);
                    pdimage = PDImageXObject.createFromFileByExtension(file, doc);
                    content = new PDPageContentStream(doc, page);
                    content.drawImage(pdimage, 0, 220);
                    content.close();
                    doc.addPage(page);
                }

                doc.save(String.format("%s\\pdf_file.pdf", path));
                doc.close();
            } catch (IOException ex) {
                showMessageDialog(Alert.AlertType.ERROR, "提示", "错误信息", String.format("导出pdf异常:%s", ex.getMessage()));
            }
        }
    }


    //导入订单批量打印
    private void BatchPrint(List<EmsOrder> orders) {
        try {
            for (EmsOrder order : orders) {
                Printer printer = Printer.getDefaultPrinter();
                PageLayout paper = printer.createPageLayout(Paper.JAPANESE_POSTCARD, PageOrientation.PORTRAIT, 0, 0, 0, 0);
                //创建打印任务
                PrinterJob job = PrinterJob.createPrinterJob();
                JobSettings jobSetting = null;
                if (job != null) {
                    showEMSLabel(order);
                    jobSetting = job.getJobSettings();
                    jobSetting.setCopies(1);   //设置一次打印张数
                    double width = gridPane.getPrefWidth();
                    double height = gridPane.getPrefHeight();
                    PrintResolution resolution = job.getJobSettings().getPrintResolution();
                    width /= resolution.getFeedResolution();
                    height /= resolution.getCrossFeedResolution();
                    double scaleX = paper.getPrintableWidth() / width / resolution.getFeedResolution();
                    double scaleY = paper.getPrintableHeight() / height / resolution.getFeedResolution();
                    Scale scale = new Scale(scaleX, scaleY);
                    emsLabel.getTransforms().add(scale);

                    if (job.printPage(paper, emsLabel)) {
                        job.endJob();
                    }

                    emsLabel.getTransforms().remove(scale);
                    //更新打印时间
                    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    order.setPrintdate(ft.format(new Date()));
                    emsOrderService.updateOrder(order);
                } else {
                    showMessageDialog(Alert.AlertType.ERROR, "提示", "错误信息", "创建打印任务失败");
                }
            }
        } catch (Exception ex) {
            showMessageDialog(Alert.AlertType.ERROR, "提示", "错误信息", String.format("批量打异常:%s", ex.getMessage()));
        }
    }

    private boolean checkRequiredField() {
        if (customeCodeField.getText().isEmpty()) {
            showMessageDialog(Alert.AlertType.ERROR, "提示", "错误信息", "客户的编号不能为空！");
            return false;
        } else if (yanshiField.getText().isEmpty()) {
            showMessageDialog(Alert.AlertType.ERROR, "提示", "错误信息", "验视的内容不能为空！");
            return false;
        }
        return true;
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
                    showMessageDialog(Alert.AlertType.INFORMATION, "提示", "提示信息", "导入EMS订单成功");
                } else {
                    showMessageDialog(Alert.AlertType.ERROR, "提示", "错误信息", "导入EMS订单失败");
                }
            }
        } catch (Exception e) {
            showMessageDialog(Alert.AlertType.ERROR, "提示", "错误信息", String.format("导入EMS订单异常:%s", e.getMessage()));
        }
    }

    @FXML
    private void handleExportExcel() {
        if (datePicker.getValue() == null) {
            showMessageDialog(Alert.AlertType.ERROR, "提示", "时间未选择", String.format("请选择导出订单列表的日期"));
            return;
        }
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
        XSSFCell cell15 = row.createCell(14);
        cell15.setCellType(CellType.NUMERIC);
        cell15.setCellValue(new XSSFRichTextString("打单日期"));

        DateTimeFormatter ft = DateTimeFormatter.ofPattern(pattern);
        EmsOrderExample emsOrderExample = new EmsOrderExample();
        EmsOrderExample.Criteria criteria = emsOrderExample.createCriteria();
        criteria.andPrintdateGreaterThanOrEqualTo(ft.format(datePicker.getValue()));
        emsOrderExample.getOredCriteria().add(criteria);
        List<EmsOrder> orders = emsOrderService.getOrderList(emsOrderExample);
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
            datacell14.setCellValue(order.getEorderno());

            XSSFCell datacell15 = datarow.createCell(14);
            datacell15.setCellType(CellType.STRING);
            datacell15.setCellValue(order.getPrintdate());
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
            showMessageDialog(Alert.AlertType.INFORMATION, "提示", "成功信息", "保存成功");
        } catch (IOException e) {
            e.printStackTrace();
            showMessageDialog(Alert.AlertType.ERROR, "提示", "错误信息", "保存失败");
        }
    }

    @FXML
    private void printPage() {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout paper = printer.createPageLayout(Paper.JAPANESE_POSTCARD, PageOrientation.PORTRAIT, 0, 0, 0, 0);
        //创建打印任务
        PrinterJob job = PrinterJob.createPrinterJob();
        JobSettings jobSetting = null;
        if (job != null) {
//            //调取打印页面
//            Scene scene = new Scene(emsLabel);
//            //加载打印页面CSS样式
//            scene.getStylesheets().add(getClass().getResource("/css/MainStage.css").toExternalForm());
            jobSetting = job.getJobSettings();
            jobSetting.setCopies(1);   //设置一次打印张数

            double width = gridPane.getPrefWidth();
            double height = gridPane.getPrefHeight();
            PrintResolution resolution = job.getJobSettings().getPrintResolution();
            width /= resolution.getFeedResolution();
            height /= resolution.getCrossFeedResolution();
            double scaleX = paper.getPrintableWidth() / width / resolution.getFeedResolution();
            double scaleY = paper.getPrintableHeight() / height / resolution.getFeedResolution();
            Scale scale = new Scale(scaleX, scaleY);
            emsLabel.getTransforms().add(scale);

            if (job.printPage(paper, emsLabel)) {
                job.endJob();
            }

            emsLabel.getTransforms().remove(scale);

        } else {
            showMessageDialog(Alert.AlertType.ERROR, "提示", "错误信息", "创建打印任务失败");
        }
    }

    @FXML
    private void deleteData() {
        try {
            if (datePicker.getValue() != null) {
                DateTimeFormatter dateFormatter =
                        DateTimeFormatter.ofPattern(pattern);
                if (emsOrderService.deleteDataByPrintDate(dateFormatter.format(datePicker.getValue()))) {
                    showMessageDialog(Alert.AlertType.INFORMATION, "提示", "提示信息", "删除成功");
                    //从新获取数据
                    refresh();
                }
            } else {
                showMessageDialog(Alert.AlertType.ERROR, "提示", "时间为必填项", "请选择要时间，删除指定时间之前的数据");
            }
        } catch (Exception e) {
            showMessageDialog(Alert.AlertType.ERROR, "提示", "错误信息", String.format("删除数据失败：%s", e.getMessage()));
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

    @FXML
    private void refresh() {
        orders.clear();
        orders.addAll(emsOrderService.getOrderList());
        avaliableEmsOrderCount.setText(String.valueOf(emsOrderService.getAvaliableEmsOrderCount()));
    }

    @FXML
    private void clearAll() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("警告");
        alert.setHeaderText("清空所有数据？");
        alert.setContentText("确认清空所有的数据吗，不可恢复");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (emsOrderService.clearAllData()) {
                refresh();
                showMessageDialog(Alert.AlertType.INFORMATION, "提示", "", "数据已经清空");
            } else {
                showMessageDialog(Alert.AlertType.ERROR, "错误", "", "删除数据失败");
            }
        }
    }

    private void showMessageDialog(Alert.AlertType alertType, String title, String headerContent, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerContent);
        alert.setContentText(content);
        alert.showAndWait();
    }

    //暂时不能用
    Service<Integer> service = new Service<Integer>() {
        @Override
        protected Task<Integer> createTask() {
            return new Task<Integer>() {

                @Override
                protected Integer call() throws Exception {
                    try {
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.setTitle("导入Ems快递单号");
                        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                                "Excel files (*.xls|*.xlsx)", "*.xls", "*.xlsx");

                        fileChooser.getExtensionFilters().add(extFilter);

                        File file = fileChooser.showOpenDialog(MainApp.getStage());

                        if (file != null) {
                            updateProgress(50, 100);
                            List<EmsOrder> list = ExcelHelper.ImportEmsOrderInfo2(file.getPath());
                            if (emsOrderService.updateEorderList(list)) {
                                avaliableEmsOrderCount.setText(String.valueOf(emsOrderService.getAvaliableEmsOrderCount()));
                                showMessageDialog(Alert.AlertType.INFORMATION, "提示", "提示信息", "导入EMS订单成功");
                            } else {
                                showMessageDialog(Alert.AlertType.ERROR, "提示", "错误信息", "导入EMS订单失败");
                            }
                        }
                    } catch (Exception e) {
                        showMessageDialog(Alert.AlertType.ERROR, "提示", "错误信息", String.format("导入EMS订单异常:%s", e.getMessage()));
                    }
                    return null;
                }

                ;
            };
        }
    };

}

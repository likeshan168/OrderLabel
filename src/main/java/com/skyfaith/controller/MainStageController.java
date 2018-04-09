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

import javax.annotation.Resource;
import java.io.File;
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
            alert.setContentText("导入清单列表失败");
            alert.showAndWait();
            e.printStackTrace();
        }

    }

    @FXML
    private void importEorderList() {
        try {
            FileChooser fileChooser = new FileChooser();

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
            alert.setContentText("导入EMS订单异常");
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
}

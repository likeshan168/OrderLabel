package com.skyfaith;

import com.skyfaith.view.CustomSplash;
import com.skyfaith.view.MainStageView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
//public class MainApp extends Application
//{
//    private Stage primaryStage;
//    private BorderPane rootLayout;
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    public void start(Stage primaryStage) throws Exception {
//        this.primaryStage = primaryStage;
//        this.primaryStage.setTitle("EMS Label");
//        initRootLayout();
////        this.primaryStage.show();
//    }
//
//    private void initRootLayout() {
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/view/RootLayout.fxml"));
//            rootLayout = (BorderPane) loader.load();
//
//            Scene scene = new Scene(rootLayout);
//            primaryStage.setScene(scene);
//
//            RootLayoutController controller = loader.getController();
//            controller.setMainApp(this);
//
//            primaryStage.show();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}

@SpringBootApplication
@MapperScan("com.skyfaith.dao")
public class MainApp extends AbstractJavaFxApplicationSupport{
    private static final String URL = "jdbc:sqlite:C:/db/emslabel.db";
    public static void main(String[] args) {
        launchApp(MainApp.class, MainStageView.class,new CustomSplash(),args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
//        stage.setMaximized(true);
        stage.setResizable(false);
        //判断数据库文件是否存在
        try
        {
            String path = "C:\\db";
            File file = new File(path);
            if (!file.exists()){
                file.mkdir();
                String f = path+"\\emslabel.db";
                file = new File(f);
                if (!file.exists()){
                    file.createNewFile();
                    //加载启动程序
                    Class.forName("org.sqlite.JDBC");
                    //获取数据库的连接
                    Connection connection = DriverManager.getConnection(URL);
                    //通过数据库的连接操作数据库
                    Statement stmt=connection.createStatement();
                    stmt.execute("create table EMSOrder\n" +
                            "(\n" +
                            "  EorderNo        nvarchar(50) not null\n" +
                            "    constraint EMSOrder_EorderNo_pk\n" +
                            "    primary key,\n" +
                            "  OrderNo         nvarchar(50),\n" +
                            "  Sender          nvarchar(30),\n" +
                            "  SenderPhone     nvarchar(20),\n" +
                            "  SenderAddress   nvarchar(1000),\n" +
                            "  Receiver        nvarchar(30),\n" +
                            "  ReceiverPhone   nvarchar(30),\n" +
                            "  ReceiverAddress nvarchar(1000),\n" +
                            "  BrandName       nvarchar(400),\n" +
                            "  Weight          nvarchar(30),\n" +
                            "  DeclaredValue   decimal(19, 5),\n" +
                            "  PosttalCode     nvarchar(6),\n" +
                            "  ClearancePort   nvarchar(100),\n" +
                            "  SenderLand      nvarchar(100)\n" +
                            ");");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("提示");
            alert.setHeaderText("错误信息");
            alert.setContentText("初始化数据库失败");
            alert.showAndWait();
            System.exit(0);
        }
    }
}
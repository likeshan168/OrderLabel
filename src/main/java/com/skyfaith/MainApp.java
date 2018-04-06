package com.skyfaith;

import com.skyfaith.controller.RootLayoutController;
import com.skyfaith.view.MainStageView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

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
public class MainApp extends AbstractJavaFxApplicationSupport{
    public static void main(String[] args) {
        launchApp(MainApp.class, MainStageView.class, args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
    }
}
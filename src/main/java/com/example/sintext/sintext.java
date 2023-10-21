package com.example.sintext;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
public class sintext extends Application {
    @Override
    public void start(Stage stage){
        Group root = new Group();
        Scene scene = new Scene(root, 960, 540);
        scene.getStylesheets().add("./app.css");


        VBox vb1 = new VBox();
        vb1.setStyle("-fx-min-width:900px;-fx-background-color:red;");
        Button b1= new Button("Click Me!");
        b1.setStyle("-fx-min-width:90px");
        vb1.getChildren().addAll(b1);
        VBox vb2 = new VBox();
        HBox master = new HBox();
        master.setStyle("-fx-min-height:100%;-fx-min-width:100%;-fx-background-color:gray");
        master.getChildren().addAll(vb1,vb2);


        root.getChildren().addAll(master);

        stage.setTitle("SINTEXT 2.0");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
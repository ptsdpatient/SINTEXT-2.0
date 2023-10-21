package com.example.sintext;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class sintext extends Application {
    @Override
    public void start(Stage stage){

        VBox vb1 = new VBox();
        VBox vb2 = new VBox();
        HBox master = new HBox();
        master.getChildren().addAll(vb1,vb2);

        Group root = new Group();
        root.getChildren().addAll(master);
        Scene scene = new Scene(root, 960, 540);
        stage.setTitle("SINTEXT 2.0");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
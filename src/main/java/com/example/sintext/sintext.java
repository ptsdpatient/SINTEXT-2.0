package com.example.sintext;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import java.util.Stack;

public class sintext extends Application {
    @Override
    public void start(Stage stage){
        StackPane root = new StackPane();
        boolean darkTheme=false;
        Scene scene = new Scene(root, 960, 540);
        scene.getStylesheets().add("./app.css");
        final int[] lcount = {1};

        VBox vb1 = new VBox();
        vb1.setStyle("-fx-pref-width:120px;-fx-background-color:red;");
        Button b1= new Button();
        b1.setText(String.valueOf(lcount[0]));
        b1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        } );
        vb1.getChildren().addAll(b1);
        VBox vb2 = new VBox();
        Label ins = new Label("Tanishq");
        TextArea editor = new TextArea();
        TextField cmd = new TextField();
        editor.setWrapText(false);
        editor.textProperty().addListener((observable, oldValue, newValue) -> {
            int lineCount = editor.getText().split("\n").length;
            System.out.println("Number of lines: " + lineCount);
            lcount[0] =lineCount;
            b1.setText(String.valueOf(lineCount));
        });


        vb2.setStyle("-fx-pref-width:100%;-fx-background-color:green");
        VBox.setVgrow(editor,Priority.ALWAYS);
        editor.setStyle("-fx-pref-width:Infinity");
        vb2.getChildren().addAll(ins,editor,cmd);


        HBox master = new HBox();
        HBox.setHgrow(vb2, Priority.ALWAYS);

        master.getChildren().addAll(vb1,vb2);
        master.setStyle("-fx-pref-height: 100%; -fx-pref-width: 100%;-fx-background-color: gray;");



        root.getChildren().addAll(master);

        stage.setTitle("sintext 2.0");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
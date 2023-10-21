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
        final int[] lcount = {1};
        String indexValue;
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
        vb1.setVisible(false);
        vb1.setManaged(false);
        VBox vb2 = new VBox();
        Label ins = new Label("Tanishq");

        HBox editor = new HBox();

        TextArea indexLines = new TextArea();
        indexLines.setStyle("-fx-background-color: transparent; -fx-text-box-border: transparent;");
        indexLines.setWrapText(true);
        indexLines.setMaxWidth(55);
        indexLines.setEditable(false);
        TextArea teditor = new TextArea();
        HBox.setHgrow(teditor,Priority.ALWAYS);
        teditor.setWrapText(false);

        teditor.textProperty().addListener((observable, oldValue, newValue) -> {
            int lineCount = teditor.getText().split("\n").length;
            System.out.println("Number of lines: " + lcount[0]);

            b1.setText(String.valueOf(lineCount));

            String output = "00000 ";
            if(lineCount>=lcount[0]){
            lcount[0] =lineCount;

            if(lineCount>26){
            for(int i = lineCount-25;i<=lineCount+1;i++){


                if(i>9&&i<100) {
                    output += "000" + String.valueOf(i) + " ";
                }
                if(i>99&&i<1000) {
                    output += "00" + String.valueOf(i) + " ";
                }
                if(i>999&&i<10000) {
                    output += "0" + String.valueOf(i) + " ";
                }
                if(output.equals("00000 ")){
                 output="";
                }else indexLines.setText(output);
            }
                }else{
                    for(int i = 0;i<lineCount;i++){
                    if(i>0&&i<10) {
                        output += "0000" + String.valueOf(i) + " ";
                    }
                        if(i>9) {
                            output += "000" + String.valueOf(i) + " ";
                        }
                        indexLines.setText(output);
                }
                }

            }
        });

        editor.getChildren().addAll(indexLines,teditor);

        TextField cmd = new TextField();



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
package com.example.sintext;

import javafx.application.Application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;
import javafx.stage.StageStyle;

import java.io.*;
import java.util.Objects;

public class sintext extends Application {
    int fontSize=4;
    //Font fontFamily = Font.loadFont(getClass().getResourceAsStream("arial.ttf"), 20);
    String theme;
    @Override
    public void start(Stage stage){
        final File[] currentFile = new File[1];
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 640, 480);
        MenuBar m1 = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem newfile = new MenuItem("new file");
        MenuItem openfile = new MenuItem("open file   ");
        MenuItem savefile = new MenuItem("save file");
        MenuItem saveasfile = new MenuItem("save as");
        MenuItem exit = new MenuItem("exit");
        Menu editMenu = new Menu("Edit");
        MenuItem cut = new MenuItem("cut");
        MenuItem copy = new MenuItem("copy");
        MenuItem paste = new MenuItem("paste");
        MenuItem settings = new MenuItem("settings  ");



        editMenu.setStyle("-fx-min-width:55;-fx-alignment: center;-fx-border-radius:10;-fx-background-radius: 10 10 0 0;");
        fileMenu.setStyle("-fx-min-width:55;-fx-text-alignment: center;-fx-border-radius:10;-fx-background-radius: 10 10 0 0;");

        editMenu.getItems().addAll(cut,copy,paste,settings);
        fileMenu.getItems().addAll(newfile,openfile,savefile,saveasfile,exit);
        m1.getMenus().addAll(fileMenu,editMenu);

        VBox vb2 = new VBox();
        HBox ins = new HBox(10);
        ins.setMinHeight(30);
        Label empty = new Label(" ");
        Label l0 = new Label("s save");
        Label l1 = new Label("o open ");
        Label l2 = new Label("fs fontsize");
        Label l3 = new Label("ff fontfamily");
        Label l4 = new Label("t theme");
        ins.getChildren().addAll(empty,l0,l1,l2,l3,l4);
        HBox.setHgrow(empty,Priority.ALWAYS);
        HBox.setHgrow(ins,Priority.ALWAYS);
        HBox.setHgrow(l0,Priority.ALWAYS);
        HBox.setHgrow(l1,Priority.ALWAYS);
        HBox.setHgrow(l2,Priority.ALWAYS);
        HBox.setHgrow(l3,Priority.ALWAYS);
        HBox.setHgrow(l4,Priority.ALWAYS);
        ins.setStyle("-fx-background-color:white");
        // ins.setAlignment(Pos.CENTER);


        TextArea editor = new TextArea();
        HBox.setHgrow(editor,Priority.ALWAYS);
        editor.setWrapText(false);
        TextField cmd = new TextField();
        cmd.setPromptText("press esc to enter prompt");
        cmd.setStyle("-fx-prompt-text-fill:gray");
        vb2.setStyle("-fx-pref-width:100%;-fx-background-color:green");
        VBox.setVgrow(editor, Priority.ALWAYS);
        editor.setStyle("-fx-pref-width:Infinity;-fx-font-size:20;-fx-control-inner-background: #282c34;");

        Font editorFont = Font.loadFont(getClass().getResourceAsStream("poppins.ttf"), 36);
        editor.setFont(editorFont);


        vb2.getChildren().addAll(m1,editor,ins, cmd);


        HBox master = new HBox();
        HBox.setHgrow(vb2, Priority.ALWAYS);

        master.getChildren().addAll(vb2);
        master.setStyle("-fx-pref-height: 100%; -fx-pref-width: 100%;");

        root.getChildren().addAll(master);


        editor.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                empty.setText("  " + editor.getParagraphs().size());
            }
        });
        m1.setOnMouseEntered(mouseEvent -> m1.setCursor(Cursor.HAND));
        newfile.setOnAction(event -> {
            if(currentFile[0]!=null){
                try (FileWriter fileWriter = new FileWriter(currentFile[0])) {
                    fileWriter.write(editor.getText()+" ");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else{
                editor.setText(" ");
            }
        });
        savefile.setOnAction(event -> {
            if(currentFile[0]!=null){
                try (FileWriter fileWriter = new FileWriter(currentFile[0])) {
                    fileWriter.write(editor.getText()+" ");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else{
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showSaveDialog(stage);
                try (FileWriter fileWriter = new FileWriter(file)) {
                    fileWriter.write(editor.getText()+" ");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        saveasfile.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showSaveDialog(stage);
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(editor.getText()+" ");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        openfile.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("open file");
            File file = fileChooser.showOpenDialog(stage);
            currentFile[0] = file;

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                editor.setText(content.toString());
            } catch (IOException ex) {
                System.out.println(ex + "");
            }

        });

        paste.setOnAction(event->{
            Clipboard clipboard = Clipboard.getSystemClipboard();
            if (clipboard.hasString()) {
                String clipboardText = clipboard.getString();
                editor.appendText(clipboardText);
            }
        });
        cut.setOnAction(event->{
            String selectedText = editor.getSelectedText();
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(selectedText);
            clipboard.setContent(content);
            editor.deleteText(editor.getSelection());
        });
        settings.setOnAction(event ->{
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initStyle(StageStyle.UTILITY);
            popupStage.setTitle("Settings");

            // Create the content for the popup
            StackPane group = new StackPane();
            VBox cluster = new VBox(16);

            HBox fontSize = new HBox();
            Label fontSizeLabel = new Label("font size : ");
            Slider fontSizeSlider = new Slider(16,64,1);
            fontSizeSlider.setValue(editor.getFont().getSize());
            Label fontSizeValue = new Label(fontSizeSlider.getValue()+"");
            fontSize.getChildren().addAll(fontSizeLabel,fontSizeSlider,fontSizeValue);
            fontSize.setAlignment(Pos.CENTER);


            HBox fontFamily = new HBox(10);
            Label fontFamilyLabel = new Label("font family : ");
            MenuButton fontFamilyMenu = new MenuButton(editor.getFont().getFamily());
            MenuItem arial = new MenuItem("arial");
            MenuItem avenir = new MenuItem("avenir");
            MenuItem cascadia = new MenuItem("cascadia");
            MenuItem dejavusans = new MenuItem("dejavusans");
            MenuItem futura = new MenuItem("futura");
            MenuItem helvetica = new MenuItem("helvetica");
            MenuItem jetbrains = new MenuItem("jetbrains");
            MenuItem poppins = new MenuItem("poppins");
            MenuItem quicksandbook = new MenuItem("quicksandbook");
            MenuItem sourcecodepro = new MenuItem("sourcecodepro");
            MenuItem timesnewroman = new MenuItem("timesnewroman");
            MenuItem univers = new MenuItem("univers");
            MenuItem verdana = new MenuItem("verdana");


            Label fontFamilyValue = new Label(editor.getFont().getFamily());
            jetbrains.setOnAction(eventClick->{fontFamilyMenu.setText("jetbrains");fontFamilyValue.setText(fontFamilyMenu.getText());});
            poppins.setOnAction(eventClick->{fontFamilyMenu.setText("poppins");fontFamilyValue.setText(fontFamilyMenu.getText());});
            quicksandbook.setOnAction(eventClick->{fontFamilyMenu.setText("quicksandbook");fontFamilyValue.setText(fontFamilyMenu.getText());});
            sourcecodepro.setOnAction(eventClick->{fontFamilyMenu.setText("sourcecodepro");fontFamilyValue.setText(fontFamilyMenu.getText());});
            timesnewroman.setOnAction(eventClick->{fontFamilyMenu.setText("timesnewroman");fontFamilyValue.setText(fontFamilyMenu.getText());});
            univers.setOnAction(eventClick->{fontFamilyMenu.setText("univers");fontFamilyValue.setText(fontFamilyMenu.getText());});
            verdana.setOnAction(eventClick->{fontFamilyMenu.setText("verdana");fontFamilyValue.setText(fontFamilyMenu.getText());});
            helvetica.setOnAction(eventClick->{fontFamilyMenu.setText("helvetica");fontFamilyValue.setText(fontFamilyMenu.getText());});
            arial.setOnAction(eventClick->{fontFamilyMenu.setText("arial");fontFamilyValue.setText(fontFamilyMenu.getText());});
            avenir.setOnAction(eventClick->{fontFamilyMenu.setText("avenir");fontFamilyValue.setText(fontFamilyMenu.getText());});
            cascadia.setOnAction(eventClick->{fontFamilyMenu.setText("cascadia");fontFamilyValue.setText(fontFamilyMenu.getText());});
            dejavusans.setOnAction(eventClick->{fontFamilyMenu.setText("dejavu sans");fontFamilyValue.setText(fontFamilyMenu.getText());});
            futura.setOnAction(eventClick->{fontFamilyMenu.setText("futura");fontFamilyValue.setText(fontFamilyMenu.getText());});

            fontFamilyMenu.getItems().addAll(verdana,helvetica,jetbrains,poppins,univers,timesnewroman,sourcecodepro,quicksandbook,arial,avenir,cascadia,dejavusans,futura);
            fontFamily.getChildren().addAll(fontFamilyLabel,fontFamilyMenu,fontFamilyValue);
            fontFamily.setAlignment(Pos.CENTER);


            HBox theme = new HBox(10);
            Label themeLabel = new Label("theme : ");
            MenuButton themeMenu = new MenuButton("onedark");

            MenuItem atomOne = new MenuItem("onedark");
            MenuItem light = new MenuItem("light");
            MenuItem dark = new MenuItem("dark");
            MenuItem butter = new MenuItem("butter");
            MenuItem blood = new MenuItem("blood");
            MenuItem lime = new MenuItem("lime");
            MenuItem cobalt = new MenuItem("cobalt");

            Label themeValue = new Label("onedark");

            themeMenu.getItems().addAll(atomOne,light,dark,butter,blood,lime,cobalt);
            theme.getChildren().addAll(themeLabel,themeMenu,themeValue);
            theme.setAlignment(Pos.CENTER);

            atomOne.setOnAction(eventClick->{themeMenu.setText("onedark");themeValue.setText(themeMenu.getText());});
            butter.setOnAction(eventClick->{themeMenu.setText("butter");themeValue.setText(themeMenu.getText());});
            blood.setOnAction(eventClick->{themeMenu.setText("blood");themeValue.setText(themeMenu.getText());});
            lime.setOnAction(eventClick->{themeMenu.setText("lime");themeValue.setText(themeMenu.getText());});
            cobalt.setOnAction(eventClick->{themeMenu.setText("cobalt");themeValue.setText(themeMenu.getText());});
            dark.setOnAction(eventClick->{themeMenu.setText("dark");themeValue.setText(themeMenu.getText());});
            light.setOnAction(eventClick->{themeMenu.setText("light");themeValue.setText(themeMenu.getText());});


            HBox textBox = new HBox(10);
            Label textBoxLabel = new Label("text format : ");
            CheckBox boldBox = new CheckBox("bold ");
            CheckBox italicBox = new CheckBox("italic ");
            textBox.getChildren().addAll(textBoxLabel,boldBox,italicBox);
            textBox.setAlignment(Pos.CENTER);
            HBox close = new HBox(20);
            Button resetButton = new Button("reset");
            Button saveButton = new Button("save");
            Button closeButton = new Button("close");


            saveButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Font newFont = Font.loadFont(getClass().getResourceAsStream(fontFamilyValue.getText()+".ttf"), 36);
                    editor.setFont(newFont);
                    String boldString="";
                    if(boldBox.isSelected()){
                        boldString="-fx-font-weight:bold;";
                    }
                    String italicString="";
                    if(italicBox.isSelected()){
                        italicString="-fx-font-style:italic;";
                    }

                    switch(themeValue.getText()) {
                        case "light":
                            editor.setStyle("-fx-font-size:"+fontSizeValue.getText()+";-fx-control-inner-background:#fafdff;"+boldString+italicString);break;
                        case "onedark":
                            editor.setStyle("-fx-font-size:"+fontSizeValue.getText()+";-fx-control-inner-background:#282c34;"+boldString+italicString);break;
                        case "butter":
                            editor.setStyle("-fx-font-size:"+fontSizeValue.getText()+";-fx-control-inner-background:#FFD700;"+boldString+italicString);break;
                        case "blood":
                            editor.setStyle("-fx-font-size:"+fontSizeValue.getText()+";-fx-control-inner-background:#DC143C;"+boldString+italicString);break;
                        case "lime":
                            editor.setStyle("-fx-font-size:"+fontSizeValue.getText()+";-fx-control-inner-background:#98c379;"+boldString+italicString);break;
                        case "cobalt":
                            editor.setStyle("-fx-font-size:"+fontSizeValue.getText()+";-fx-control-inner-background:#61afef;"+boldString+italicString);break;
                        case "dark":
                            editor.setStyle("-fx-font-size:"+fontSizeValue.getText()+";-fx-control-inner-background:#1b1b1b;"+boldString+italicString);break;
                        default:
                            editor.setStyle("-fx-font-size:"+fontSizeValue.getText()+";-fx-control-inner-background:#a8a8a8;"+boldString+italicString);break;

                    }
                    popupStage.close();
                }
            });
            closeButton.setOnAction(eventClick->{popupStage.close();});
            resetButton.setOnAction(eventClick->{
                fontSizeValue.setText("20");
                fontSizeSlider.setValue(20);
                fontFamilyMenu.setText("arial");
                fontFamilyValue.setText("arial");
                themeMenu.setText("onedark");
                themeValue.setText("onedark");
            });



            close.getChildren().addAll(resetButton,saveButton,closeButton);
            close.setAlignment(Pos.CENTER);
            cluster.getChildren().addAll(fontSize,fontFamily,theme,textBox,close);
            cluster.setAlignment(Pos.CENTER);
            group.getChildren().add(cluster);


            fontSizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                fontSizeValue.setText(String.valueOf((int) newValue.doubleValue()));
            });
            popupStage.setResizable(false);
            Scene popupScene = new Scene(group, 350, 210);
            popupStage.setScene(popupScene);
            popupStage.show();
        });
        copy.setOnAction(event->{
            String selectedText = editor.getSelectedText();
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(selectedText);
            clipboard.setContent(content);
        });
        exit.setOnAction(event -> {
            if (currentFile[0] != null) {
                try (FileWriter fileWriter = new FileWriter(currentFile[0])) {
                    fileWriter.write(editor.getText() + " ");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            stage.close();
        });
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode()== KeyCode.ESCAPE){
                System.out.println("escape key pressed");
                cmd.requestFocus();
            }
        });

        cmd.setOnKeyPressed(KeyEvent->{
            if(KeyEvent.getCode()==KeyCode.ENTER && cmd.isFocused()){
                String[] parsedCMD = cmd.getText().split(" ");
                if(Objects.equals(parsedCMD[0], "s")){
                    if(currentFile[0]!=null){
                        try (FileWriter fileWriter = new FileWriter(currentFile[0])) {
                            fileWriter.write(editor.getText()+" ");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else{
                        FileChooser fileChooser = new FileChooser();
                        File file = fileChooser.showSaveDialog(stage);
                        try (FileWriter fileWriter = new FileWriter(file)) {
                            fileWriter.write(editor.getText()+" ");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }
                if(Objects.equals(parsedCMD[0], "o")){
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("open file");
                    File file = fileChooser.showOpenDialog(stage);
                    currentFile[0] = file;

                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        StringBuilder content = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            content.append(line).append("\n");
                        }
                        editor.setText(content.toString());
                    } catch (IOException ex) {
                        System.out.println(ex + "");
                    }
                }
                if(Objects.equals(parsedCMD[0], "e")){
                    stage.close();
                }

            }
        });

        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("sintext-icon.png")));
        stage.getIcons().add(icon);
        stage.setTitle("sintext");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

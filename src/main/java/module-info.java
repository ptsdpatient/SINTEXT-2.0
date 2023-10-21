module com.example.sintext {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sintext to javafx.fxml;
    exports com.example.sintext;
}
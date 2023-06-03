module com.example.graphic {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires javafx.swing;


    opens javafx to javafx.fxml;
    exports javafx;
    exports db;
    opens db to javafx.fxml;
    exports service;
    opens service to javafx.fxml;
}
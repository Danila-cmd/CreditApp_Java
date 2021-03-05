module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires commons.io;
    requires hsqldb;
    requires commons.math3;

    opens org.example to javafx.fxml;
    opens org.example.dao to javafx.base;
    exports org.example;
}
module com.example.pharmacysystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.pharmacysystem to javafx.fxml;
    exports com.example.pharmacysystem;
}
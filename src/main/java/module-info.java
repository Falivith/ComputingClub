module com.example.computingclub {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.computingclub to javafx.fxml;
    exports com.example.computingclub;
}
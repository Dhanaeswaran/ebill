module com.example.ebill {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;
    requires mysql.connector.j;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.ebill to javafx.fxml;
    exports com.example.ebill;
}
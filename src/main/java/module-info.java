module com.example.exampracticeapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.exampracticeapp to javafx.fxml;
    exports com.example.exampracticeapp;
    requires java.sql;
    opens com.example.exampracticeapp.Domain to javafx.base;
//    opens com.example.exampracticeapp to javafx.fxml;
    opens com.example.exampracticeapp.Controller to javafx.fxml;
    exports com.example.exampracticeapp.Controller;
//    exports com.example.exampracticeapp;
    opens com.example.exampracticeapp.Repository.DBRepositories.Factory to javafx.base,javafx.fxml;
    opens com.example.exampracticeapp.Repository.DBRepositories.Util to javafx.base,javafx.fxml;
//    opens com.example.exampracticeapp.Service to java.base, javafx.fxml;
//    exports com.example.exampracticeapp.Service;
//    opens com.example.exampracticeapp.Domain.Message to javafx.base;
//    opens com.example.exampracticeapp.Domain.Friendships to javafx.base;
}
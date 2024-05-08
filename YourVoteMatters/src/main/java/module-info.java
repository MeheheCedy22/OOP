module com.projekt.oop.yourvotematters {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.projekt.oop.yourvotematters to javafx.fxml;
    exports com.projekt.oop.yourvotematters;
    exports com.projekt.oop.yourvotematters.view;
    opens com.projekt.oop.yourvotematters.view to javafx.fxml;
}
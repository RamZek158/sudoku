module ru.andy.sudocu {
    requires javafx.controls;
    requires javafx.fxml;

    opens ru.andy.sudocu to javafx.fxml;
    exports ru.andy.sudocu;
}

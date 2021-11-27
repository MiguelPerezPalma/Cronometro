module PSP.Cronometro {
    requires javafx.controls;
    requires javafx.fxml;

    opens PSP.Cronometro to javafx.fxml;
    exports PSP.Cronometro;
}

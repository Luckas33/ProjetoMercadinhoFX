module com.programa.projetomercadofx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.programa.projetomercadofx to javafx.fxml;
    exports com.programa.projetomercadofx;
}
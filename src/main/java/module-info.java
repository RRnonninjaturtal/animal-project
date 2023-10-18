module com.example.animalproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.animalproject to javafx.fxml;
    exports com.example.animalproject;
}
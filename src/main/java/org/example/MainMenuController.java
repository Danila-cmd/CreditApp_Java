package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button clientButton;

    @FXML
    private Button bankClient;

    @FXML
    private Button creditButton;

    @FXML
    private Button creditOfferButton;

    @FXML
    void initialize() {
        clientButton.setOnAction(even -> {

                    Stage stage = (Stage) clientButton.getScene().getWindow();
                    stage.close();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/clientMenu.fxml"));

                    Parent root = null;
                    try {
                        root = (Parent) loader.load();
                        stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setTitle("Клиент");
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        creditButton.setOnAction(even ->
                System.out.println("Credit")
        );

        creditOfferButton.setOnAction(even ->
                System.out.println("Credit offer")
        );

        bankClient.setOnAction(even ->
                System.out.println("Bank")
        );
    }
}

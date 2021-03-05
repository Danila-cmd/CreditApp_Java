package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.dao.Credit;
import org.example.dao.DatabaseHandler;

public class CreditController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField limitCreditField;

    @FXML
    private TextField interestRateField;

    @FXML
    private Button addCreditButton;

    @FXML
    private Button editCreditButton;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        backButton.setOnAction(actionEvent -> {
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/mainMenu.fxml"));

            Parent root = null;
            try {
                root = (Parent) loader.load();
                stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Меню");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addCreditButton.setOnAction(actionEvent -> {

            UUID creditId = UUID.randomUUID();
            int limitCredit = Integer.parseInt(limitCreditField.getText());
            double interestRate = Double.parseDouble(interestRateField.getText());

            Credit credit = new Credit(creditId, limitCredit, interestRate);

            DatabaseHandler db = new DatabaseHandler();
            db.addCredit(credit);

            limitCreditField.clear();
            interestRateField.clear();

        });

        editCreditButton.setOnAction(actionEvent -> {
            System.out.println("Edit");

            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/creditEdit.fxml"));

            Parent root = null;
            try {
                root = (Parent) loader.load();
                stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Редактировать");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }
}


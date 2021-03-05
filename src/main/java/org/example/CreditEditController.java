package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.dao.Credit;
import org.example.dao.DatabaseHandler;

public class CreditEditController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField limitCreditField;

    @FXML
    private TextField interestRateField;

    @FXML
    private Button removeCreditButton;

    @FXML
    private Button editCreditButton;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Credit> creditTableView;

    @FXML
    private TableColumn<Credit, SimpleIntegerProperty> limitCredit;

    @FXML
    private TableColumn<Credit, SimpleIntegerProperty> interestRate;

    @FXML
    private TableColumn<Credit, Object> credit_id;

    @FXML
    void initialize() {

        backButton.setOnAction(actionEvent -> {

            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/creditMenu.fxml"));

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
        });

        credit_id.setCellValueFactory(new PropertyValueFactory<>("creditId"));
        credit_id.setVisible(false);

        limitCredit.setCellValueFactory(new PropertyValueFactory<>("creditLimit"));
        interestRate.setCellValueFactory(new PropertyValueFactory<>("interestRate"));

        creditTableView.setItems(getCredit());

        creditTableView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                int creditLimit = creditTableView.getSelectionModel().getSelectedItem().getCreditLimit();
                double interestRate = creditTableView.getSelectionModel().getSelectedItem().getInterestRate();

                limitCreditField.setText(String.valueOf(creditLimit));
                interestRateField.setText(String.valueOf(interestRate));

            }
        });

        editCreditButton.setOnAction(actionEvent -> {

            UUID id = creditTableView.getSelectionModel().getSelectedItem().getCreditId();

            String creditId = id.toString();
            String creditLimit = limitCreditField.getText();
            String interestRate = interestRateField.getText();

            DatabaseHandler db = new DatabaseHandler();
            db.updateCredit(creditId, creditLimit, interestRate);

            Stage stage = (Stage) editCreditButton.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/creditEdit.fxml"));

            Parent root = null;
            try {
                root = (Parent) loader.load();
                stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Изменить");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        removeCreditButton.setOnAction(actionEvent -> {

            System.out.println("delete");

            UUID id = creditTableView.getSelectionModel().getSelectedItem().getCreditId();

            String creditId = id.toString();

            DatabaseHandler db = new DatabaseHandler();
            db.deleteCreditById(creditId);

            Stage stage = (Stage) editCreditButton.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/creditEdit.fxml"));

            Parent root = null;
            try {
                root = (Parent) loader.load();
                stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Изменить");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }

    private ObservableList<Credit> getCredit() {
        DatabaseHandler db = new DatabaseHandler();
        ObservableList<Credit> credits = db.selectAllCredit();
        return credits;
    }
}


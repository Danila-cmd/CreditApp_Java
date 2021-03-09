package org.example;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import org.example.dao.CreditOffer;
import org.example.dao.DatabaseHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreditOfferEditController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField limitCreditField;

    @FXML
    private Button deleteCreditOffer;

    @FXML
    private Button editCreditOffer;

    @FXML
    private Button backButton;

    @FXML
    private TextField nameClientField;

    @FXML
    private TextField loanAmountField;

    @FXML
    private TextField yearForPayCreditField;

    @FXML
    private TableView<CreditOffer> schedulePayment;

    @FXML
    private TableColumn<CreditOffer, SimpleStringProperty> client;

    @FXML
    private TableColumn<CreditOffer, SimpleStringProperty> credit;

    @FXML
    private TableColumn<CreditOffer, SimpleIntegerProperty> loanAmount;

    @FXML
    private TableColumn<CreditOffer, SimpleIntegerProperty> yearForPayCredit;


    @FXML
    void initialize() {

        client.setCellValueFactory(new PropertyValueFactory<>("clientNameTelephoneEmailPassport"));
        credit.setCellValueFactory(new PropertyValueFactory<>("bankOffer"));
        loanAmount.setCellValueFactory(new PropertyValueFactory<>("creditAmount"));
        yearForPayCredit.setCellValueFactory(new PropertyValueFactory<>("year"));

        schedulePayment.setItems(getCreditOffer());

        schedulePayment.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                String clientNameTelephoneEmailPassport = schedulePayment.getSelectionModel().getSelectedItem().getClientNameTelephoneEmailPassport();
                String bankOffer = schedulePayment.getSelectionModel().getSelectedItem().getBankOffer();
                String creditAmount = String.valueOf(schedulePayment.getSelectionModel().getSelectedItem().getCreditAmount());
                String year = String.valueOf(schedulePayment.getSelectionModel().getSelectedItem().getYear());

                nameClientField.setText(clientNameTelephoneEmailPassport);
                limitCreditField.setText(bankOffer);
                loanAmountField.setText(creditAmount);
                yearForPayCreditField.setText(year);

            }
        });

        editCreditOffer.setOnAction(actionEvent -> {

            String id = String.valueOf(schedulePayment.getSelectionModel().getSelectedItem().getId());
            String nameTelephonePassport = nameClientField.getText();
            String credit = limitCreditField.getText();
            String loanAmount = loanAmountField.getText();
            String year = yearForPayCreditField.getText();

            DatabaseHandler databaseHandler = new DatabaseHandler();
            databaseHandler.updateCreditOffer(id, nameTelephonePassport, credit, loanAmount, year);

            Stage stage = (Stage) editCreditOffer.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/editCreditOffer.fxml"));

            Parent root = null;
            try {
                root = (Parent) loader.load();
                stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Редактирование");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        deleteCreditOffer.setOnAction(actionEvent -> {

            String id = String.valueOf(schedulePayment.getSelectionModel().getSelectedItem().getId());

            DatabaseHandler db = new DatabaseHandler();
            db.deleteCreditOffer(id);

            Stage stage = (Stage) deleteCreditOffer.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/editCreditOffer.fxml"));

            Parent root = null;
            try {
                root = (Parent) loader.load();
                stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Редактирование");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        backButton.setOnAction(actionEvent -> {
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/creditOffer.fxml"));

            Parent root = null;
            try {
                root = (Parent) loader.load();
                stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Кредитное предложение");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private ObservableList<CreditOffer> getCreditOffer() {
        DatabaseHandler db = new DatabaseHandler();
        ObservableList<CreditOffer> creditsOffer = db.selectAllCreditOffer();
        return creditsOffer;
    }

}

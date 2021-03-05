package org.example;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.dao.*;

public class CreditOfferController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField limitCreditField;

    @FXML
    private TextField interestRateField;

    @FXML
    private Button paymentScheduleButton;

    @FXML
    private Button getLoanButton;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Credit> creditTableView;

    @FXML
    private TableColumn<Credit, Object> credit_id;

    @FXML
    private TableColumn<Credit, SimpleIntegerProperty> limitCredit;

    @FXML
    private TableColumn<Credit, SimpleDoubleProperty> interestRate;

    @FXML
    private TextField nameClientField;

    @FXML
    private TextField loanAmountField;

    @FXML
    private TextField yearForPayCreditField;

    @FXML
    private TableView<Client> clientsTable;

    @FXML
    private TableColumn<Client, SimpleStringProperty> nameClient;

    @FXML
    private TableColumn<Client, Object> client_id;

    @FXML
    private TableView<DataSchedule> schedulePayment;

    @FXML
    private TableColumn<DataSchedule, SimpleIntegerProperty> datePayment;

    @FXML
    private TableColumn<DataSchedule, SimpleDoubleProperty> countPayment;

    @FXML
    private TableColumn<DataSchedule, SimpleDoubleProperty> loanPaymentAmount;

    @FXML
    private TableColumn<DataSchedule, SimpleDoubleProperty> summaProzentov;

    @FXML
    void initialize() {

        getLoanButton.setDisable(true);

        clientsTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String nameSerName = clientsTable.getSelectionModel().getSelectedItem().getNameSerName();
                nameClientField.setText(nameSerName);
            }
        });


        creditTableView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int creditLimit = creditTableView.getSelectionModel().getSelectedItem().getCreditLimit();
                double interestRate = creditTableView.getSelectionModel().getSelectedItem().getInterestRate();

                interestRateField.setText(String.valueOf(interestRate));
                limitCreditField.setText(String.valueOf(creditLimit));

            }
        });

        client_id.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        nameClient.setCellValueFactory(new PropertyValueFactory<>("nameSerName"));

        clientsTable.setItems(getClient());

        credit_id.setCellValueFactory(new PropertyValueFactory<>("creditId"));
        limitCredit.setCellValueFactory(new PropertyValueFactory<>("creditLimit"));
        interestRate.setCellValueFactory(new PropertyValueFactory<>("interestRate"));

        creditTableView.setItems(getCredit());

        paymentScheduleButton.setOnAction(actionEvent -> {

            System.out.println("Create schedule");

            UUID id = UUID.randomUUID();
            String name = nameClientField.getText();
            int limitCredit = Integer.parseInt(limitCreditField.getText());
            double interestRate = Double.parseDouble(interestRateField.getText());
            int loanAmountFieldText = Integer.parseInt(loanAmountField.getText());
            int yearForPayCredit = Integer.parseInt(yearForPayCreditField.getText());

            Schedule schedule = new Schedule(id, limitCredit, interestRate, loanAmountFieldText, yearForPayCredit);
            ObservableList<DataSchedule> dataSchedules = schedule.creditPayment();

            datePayment.setCellValueFactory(new PropertyValueFactory<>("month"));
            countPayment.setCellValueFactory(new PropertyValueFactory<>("summaPlatezha"));
            loanPaymentAmount.setCellValueFactory(new PropertyValueFactory<>("teloKredita"));
            summaProzentov.setCellValueFactory(new PropertyValueFactory<>("teloProzentov"));

            schedulePayment.setItems(dataSchedules);

            getLoanButton.setDisable(false);

            getLoanButton.setOnAction(actionEvent1 -> {
                System.out.println("Button");

                String text = nameClientField.getText();
                String loanAmountFieldText1 = loanAmountField.getText();
                String limitCreditFieldText = limitCreditField.getText();
                String interestRateFieldText = interestRateField.getText();
                String loanAmountFieldText2 = loanAmountField.getText();
                String yearForPayCreditFieldText = yearForPayCreditField.getText();

                System.out.println(text);
                System.out.println(loanAmountFieldText1);
                System.out.println(limitCreditFieldText);
                System.out.println(interestRateFieldText);
                System.out.println(loanAmountFieldText2);
                System.out.println(yearForPayCreditFieldText);
            });

        });

    }

    private ObservableList<Credit> getCredit() {
        DatabaseHandler db = new DatabaseHandler();
        ObservableList<Credit> credits = db.selectAllCredit();
        return credits;
    }

    private ObservableList<Client> getClient() {
        DatabaseHandler db = new DatabaseHandler();
        ObservableList<Client> clients = db.selectAllClient();
        return clients;
    }

}

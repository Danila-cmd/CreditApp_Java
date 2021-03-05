package org.example;

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
import org.example.dao.Client;
import org.example.dao.DatabaseHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class BankClientsEditController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameSernameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passportField;

    @FXML
    private Button removeButton;

    @FXML
    private Button editClientButton;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Client> clientTableView;

    @FXML
    private TableColumn<Client, SimpleStringProperty> firstName;

    @FXML
    private TableColumn<Client, SimpleStringProperty> phone;

    @FXML
    private TableColumn<Client, SimpleStringProperty> email;

    @FXML
    private TableColumn<Client, SimpleStringProperty> passport;

    @FXML
    private TableColumn<Client, Object> client_id;

    @FXML
    void initialize() {

        client_id.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        client_id.setVisible(false);

        firstName.setCellValueFactory(new PropertyValueFactory<Client, SimpleStringProperty>("nameSerName"));
        phone.setCellValueFactory(new PropertyValueFactory<Client, SimpleStringProperty>("phoneNumber"));
        email.setCellValueFactory(new PropertyValueFactory<Client, SimpleStringProperty>("email"));
        passport.setCellValueFactory(new PropertyValueFactory<Client, SimpleStringProperty>("passportNumber"));

        clientTableView.setItems(getClient());

        clientTableView.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {

                String nameSerName = clientTableView.getSelectionModel().getSelectedItem().getNameSerName();
                String phoneNumber = clientTableView.getSelectionModel().getSelectedItem().getPhoneNumber();
                String email = clientTableView.getSelectionModel().getSelectedItem().getEmail();
                String passportNumber = clientTableView.getSelectionModel().getSelectedItem().getPassportNumber();

                nameSernameField.setText(nameSerName);
                phoneField.setText(phoneNumber);
                emailField.setText(email);
                passportField.setText(passportNumber);

            }
        });

        backButton.setOnAction(actionEvent -> {
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bankMenu.fxml"));

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

        editClientButton.setOnAction(actionEvent -> {

            UUID clientId = clientTableView.getSelectionModel().getSelectedItem().getClientId();

            String id = clientId.toString();
            String name = nameSernameField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String passport = passportField.getText();

            DatabaseHandler databaseHandler = new DatabaseHandler();
            databaseHandler.updateClient(id, name, phone, email, passport);

            Stage stage = (Stage) editClientButton.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/clientEdit.fxml"));

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

        removeButton.setOnAction(actionEvent -> {

            UUID clientId = clientTableView.getSelectionModel().getSelectedItem().getClientId();

            String id = clientId.toString();

            DatabaseHandler databaseHandler = new DatabaseHandler();
            databaseHandler.deleteClientById(id);

            Stage stage = (Stage) removeButton.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/clientEdit.fxml"));

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

    private ObservableList<Client> getClient() {
        DatabaseHandler db = new DatabaseHandler();
        ObservableList<Client> clients = db.selectAllClient();
        return clients;
    }
}

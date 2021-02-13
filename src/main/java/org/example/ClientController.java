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
import org.example.dao.Client;
import org.example.dao.DatabaseHandler;

public class ClientController {

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
    private Button addClientButton;

    @FXML
    private Button editClientButton;

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
                }
        );

        editClientButton.setOnAction(actionEvent -> {
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
                }
        );

        addClientButton.setOnAction(actionEvent -> {
            createNewClient();
        });


    }

    private void createNewClient() {

        DatabaseHandler databaseHandler = new DatabaseHandler();

        UUID id = UUID.randomUUID();
        String nameSername = nameSernameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        String passport = passportField.getText();

        Client client = new Client(id, nameSername, phone, email, passport);

        System.out.println(client.getClientId());

        databaseHandler.addClient(client);
        clearAllFields();

    }

    private void clearAllFields() {
        nameSernameField.clear();
        phoneField.clear();
        emailField.clear();
        passportField.clear();
    }

}

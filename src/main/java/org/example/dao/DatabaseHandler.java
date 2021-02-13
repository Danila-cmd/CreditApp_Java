package org.example.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.UUID;

public class DatabaseHandler extends Config {
    Connection connection = null;

    public Connection getConnection() {

        String connectionString = "jdbc:hsqldb:" + fileConnection;

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            System.out.println("Connect ready ...");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(connectionString, dbUser, dbPassword);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connection failed...");
        return null;
    }

    public void addClient(Client client) {
        String createClient = readToString("sql/client.sql");

        try {
            getConnection().createStatement().executeUpdate(createClient);
            String insert = "INSERT INTO client values (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = getConnection().prepareStatement(insert);
            preparedStatement.setObject(1, client.getClientId());
            preparedStatement.setString(2, client.getNameSerName());
            preparedStatement.setString(3, client.getPhoneNumber());
            preparedStatement.setString(4, client.getEmail());
            preparedStatement.setString(5, client.getPassportNumber());

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public ObservableList<Client> selectAllClient() {
        ObservableList<Client> clients = FXCollections.observableArrayList();
        String selectAllClient = "SELECT * FROM client";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(selectAllClient);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                clients.add(new Client(
                                (UUID) rs.getObject(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5)
                        )
                );
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return clients;
    }

    public void updateClient(String id, String name, String phone, String email, String passport) {

        String getClientById = "SELECT * FROM client WHERE client_id = '" + id + "'";
        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = getConnection().prepareStatement(getClientById);

            String updateClientById = "UPDATE client SET" +
                    " client_name = '" + name + "'," +
                    " phoneNumber = '" + phone + "'," +
                    " email = '" + email + "'," +
                    " passportNumber = '" + passport + "'" +
                    " WHERE client_id = '" + id + "'";

            PreparedStatement preparedStatement1 = getConnection().prepareStatement(updateClientById);
            preparedStatement1.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private String readToString(String fileName) {
        File file = new File(fileName);
        String string = null;
        try {
            string = FileUtils.readFileToString(file, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }

}
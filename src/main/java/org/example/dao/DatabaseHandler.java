package org.example.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
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

    public void addCredit(Credit credit) {

        String createCredit = readToString("sql/credit.sql");

        try {

            getConnection().createStatement().executeUpdate(createCredit);

            String addCredit = "INSERT INTO credit VALUES (?,?,?)";

            PreparedStatement preparedStatement = getConnection().prepareStatement(addCredit);

            preparedStatement.setObject(1, credit.getCreditId());
            preparedStatement.setInt(2, credit.getCreditLimit());
            preparedStatement.setDouble(3, credit.getInterestRate());

            preparedStatement.executeUpdate();

            System.out.println("Credit add");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Client addCreditOffer(String id, String idBankOffer, int creditAmount, int yearCredit) {

        String createCreditOffer = readToString("sql/creditOffer.sql");

        Client client = null;
        Credit credit = null;

        try {

            getConnection().createStatement().executeUpdate(createCreditOffer);

            String getClientById = "SELECT * FROM client WHERE client_id = '" + id + "'";
            String getBankOfferById = "SELECT * FROM credit WHERE credit_id = '" + idBankOffer + "'";

            PreparedStatement preparedStatement = getConnection().prepareStatement(getClientById);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                client = new Client(
                        (UUID) rs.getObject(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)

                );
            }

            PreparedStatement preparedStatementBankOffer = getConnection().prepareStatement(getBankOfferById);
            ResultSet resultSetBankOffer = preparedStatementBankOffer.executeQuery();

            while (resultSetBankOffer.next()) {
                credit = new Credit(
                        (UUID) resultSetBankOffer.getObject(1),
                        resultSetBankOffer.getInt(2),
                        resultSetBankOffer.getDouble(3)
                );
            }

            System.out.println("CreditOffer add");

            String insertClient = "INSERT INTO creditOffer VALUES (?,?,?,?,?)";

            PreparedStatement preparedInsertClient = getConnection().prepareStatement(insertClient);

            UUID uuid = UUID.randomUUID();
            String nameTelephonePassport = client.getNameSerName() + " " + client.getPhoneNumber() + " " + client.getEmail() + " " + client.getPassportNumber();

            String bankOffer = String.valueOf(credit.getCreditLimit()) + " " + String.valueOf(credit.getInterestRate());

            System.out.println(bankOffer);

            CreditOffer creditOffer1 = new CreditOffer(uuid, nameTelephonePassport, bankOffer, creditAmount, yearCredit);

            preparedInsertClient.setObject(1, creditOffer1.getId());
            preparedInsertClient.setString(2, creditOffer1.getClientNameTelephoneEmailPassport());
            preparedInsertClient.setString(3, creditOffer1.getBankOffer());
            preparedInsertClient.setInt(4, creditOffer1.getCreditAmount());
            preparedInsertClient.setInt(5, creditOffer1.getYear());

            preparedInsertClient.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return client;

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

    public ObservableList<Credit> selectAllCredit() {

        ObservableList<Credit> credits = FXCollections.observableArrayList();
        String selectAllCredit = "SELECT * FROM credit";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(selectAllCredit);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                credits.add(new Credit(
                        (UUID) rs.getObject(1),
                        rs.getInt(2),
                        rs.getDouble(3)
                ));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return credits;
    }

    public ObservableList<CreditOffer> selectAllCreditOffer() {

        ObservableList<CreditOffer> creditsOffer = FXCollections.observableArrayList();
        String selectAllCreditOffer = "SELECT * FROM creditOffer";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(selectAllCreditOffer);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                creditsOffer.add(
                        new CreditOffer(
                                (UUID) rs.getObject(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getInt(4),
                                rs.getInt(5)
                        )
                );
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return creditsOffer;

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

    public void updateCredit(String creditId, String creditLimit, String interestRateField) {

        String getCreditById = "SELECT * FROM credit WHERE credit_id = '" + creditId + "'";

        try {

            String updateCreditById = "UPDATE credit SET" +
                    " credit_limit = '" + creditLimit + "'," +
                    " credit_interest_rate = '" + interestRateField + "'" +
                    " WHERE credit_id = '" + creditId + "'";

            PreparedStatement preparedStatement1 = getConnection().prepareStatement(updateCreditById);
            preparedStatement1.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void deleteClientById(String id) {
        String deleteById = "DELETE FROM client WHERE client_id = '" + id + "'";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = getConnection().prepareStatement(deleteById);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteCreditById(String creditId) {
        String deleteCreditById = "DELETE FROM credit WHERE credit_id = '" + creditId + "'";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = getConnection().prepareStatement(deleteCreditById);
            preparedStatement.executeUpdate();
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

    public void updateCreditOffer(String id, String nameTelephonePassport, String credit, String loanAmount, String year) {
        String getCreditOfferById = "SELECT * FROM creditOffer WHERE creditOffer_id = '" + id + "'";
        PreparedStatement preparedStatement;

    }
}

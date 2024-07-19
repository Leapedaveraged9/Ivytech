import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {

    private TextField idField, lastNameField, firstNameField, miField, addressField, cityField, stateField, telephoneField, emailField;
    private Button viewButton, insertButton, updateButton;

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:staff.db"; // Change this to your database URL
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Staff Management");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Create UI components
        idField = new TextField();
        lastNameField = new TextField();
        firstNameField = new TextField();
        miField = new TextField();
        addressField = new TextField();
        cityField = new TextField();
        stateField = new TextField();
        telephoneField = new TextField();
        emailField = new TextField();

        viewButton = new Button("View");
        insertButton = new Button("Insert");
        updateButton = new Button("Update");

        // Add components to grid
        grid.add(new Label("ID:"), 0, 0);
        grid.add(idField, 1, 0);
        grid.add(new Label("Last Name:"), 0, 1);
        grid.add(lastNameField, 1, 1);
        grid.add(new Label("First Name:"), 0, 2);
        grid.add(firstNameField, 1, 2);
        grid.add(new Label("MI:"), 0, 3);
        grid.add(miField, 1, 3);
        grid.add(new Label("Address:"), 0, 4);
        grid.add(addressField, 1, 4);
        grid.add(new Label("City:"), 0, 5);
        grid.add(cityField, 1, 5);
        grid.add(new Label("State:"), 0, 6);
        grid.add(stateField, 1, 6);
        grid.add(new Label("Telephone:"), 0, 7);
        grid.add(telephoneField, 1, 7);
        grid.add(new Label("Email:"), 0, 8);
        grid.add(emailField, 1, 8);

        grid.add(viewButton, 0, 9);
        grid.add(insertButton, 1, 9);
        grid.add(updateButton, 2, 9);

        // Add event handlers
        viewButton.setOnAction(e -> viewRecord());
        insertButton.setOnAction(e -> insertRecord());
        updateButton.setOnAction(e -> updateRecord());

        // Create scene and show stage
        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void viewRecord() {
        String sql = "SELECT * FROM Staff WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, idField.getText());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                lastNameField.setText(rs.getString("lastName"));
                firstNameField.setText(rs.getString("firstName"));
                miField.setText(rs.getString("mi"));
                addressField.setText(rs.getString("address"));
                cityField.setText(rs.getString("city"));
                stateField.setText(rs.getString("state"));
                telephoneField.setText(rs.getString("telephone"));
                emailField.setText(rs.getString("email"));
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Record Not Found");
                alert.setHeaderText(null);
                alert.setContentText("No record found with ID " + idField.getText());
                alert.showAndWait();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void insertRecord() {
        String sql = "INSERT INTO Staff(id, lastName, firstName, mi, address, city, state, telephone, email) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idField.getText());
            pstmt.setString(2, lastNameField.getText());
            pstmt.setString(3, firstNameField.getText());
            pstmt.setString(4, miField.getText());
            pstmt.setString(5, addressField.getText());
            pstmt.setString(6, cityField.getText());
            pstmt.setString(7, stateField.getText());
            pstmt.setString(8, telephoneField.getText());
            pstmt.setString(9, emailField.getText());
            pstmt.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Insert Successful");
            alert.setHeaderText(null);
            alert.setContentText("Record inserted successfully");
            alert.showAndWait();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateRecord() {
        String sql = "UPDATE Staff SET lastName = ?, firstName = ?, mi = ?, address = ?, city = ?, state = ?, telephone = ?, email = ? WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, lastNameField.getText());
            pstmt.setString(2, firstNameField.getText());
            pstmt.setString(3, miField.getText());
            pstmt.setString(4, addressField.getText());
            pstmt.setString(5, cityField.getText());
            pstmt.setString(6, stateField.getText());
            pstmt.setString(7, telephoneField.getText());
            pstmt.setString(8, emailField.getText());
            pstmt.setString(9, idField.getText());
            pstmt.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Successful");
            alert.setHeaderText(null);
            alert.setContentText("Record updated successfully");
            alert.showAndWait();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

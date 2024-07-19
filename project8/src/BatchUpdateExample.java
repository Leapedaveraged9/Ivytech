import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Random;

public class BatchUpdateExample extends Application {

    private Connection connection;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Connect to DB");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        Label lblUrl = new Label("Database URL:");
        TextField txtUrl = new TextField("jdbc:mysql://localhost/javabook");
        Label lblUser = new Label("Username:");
        TextField txtUser = new TextField("scott");
        Label lblPassword = new Label("Password:");
        PasswordField txtPassword = new PasswordField();

        Button btnConnect = new Button("Connect to DB");
        btnConnect.setOnAction(e -> connectToDB(txtUrl.getText(), txtUser.getText(), txtPassword.getText()));

        Button btnBatchUpdate = new Button("Batch Update");
        btnBatchUpdate.setOnAction(e -> performBatchUpdate());

        Button btnNonBatchUpdate = new Button("Non-Batch Update");
        btnNonBatchUpdate.setOnAction(e -> performNonBatchUpdate());

        grid.add(lblUrl, 0, 0);
        grid.add(txtUrl, 1, 0);
        grid.add(lblUser, 0, 1);
        grid.add(txtUser, 1, 1);
        grid.add(lblPassword, 0, 2);
        grid.add(txtPassword, 1, 2);
        grid.add(btnConnect, 1, 3);
        grid.add(btnBatchUpdate, 1, 4);
        grid.add(btnNonBatchUpdate, 1, 5);

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void connectToDB(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void performBatchUpdate() {
        try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Temp (num1, num2, num3) VALUES (?, ?, ?)")) {
            connection.setAutoCommit(false);
            Random random = new Random();
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < 1000; i++) {
                pstmt.setDouble(1, random.nextDouble());
                pstmt.setDouble(2, random.nextDouble());
                pstmt.setDouble(3, random.nextDouble());
                pstmt.addBatch();
            }

            pstmt.executeBatch();
            connection.commit();
            long endTime = System.currentTimeMillis();
            System.out.println("Batch update completed. Elapsed time: " + (endTime - startTime) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void performNonBatchUpdate() {
        try (Statement stmt = connection.createStatement()) {
            Random random = new Random();
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < 1000; i++) {
                String sql = String.format("INSERT INTO Temp (num1, num2, num3) VALUES (%f, %f, %f)",
                        random.nextDouble(), random.nextDouble(), random.nextDouble());
                stmt.executeUpdate(sql);
            }

            long endTime = System.currentTimeMillis();
            System.out.println("Non-Batch update completed. Elapsed time: " + (endTime - startTime) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

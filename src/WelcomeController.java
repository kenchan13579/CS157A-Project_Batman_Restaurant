import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.mysql.jdbc.Connection;

import java.io.IOException;

public class WelcomeController {
    Connection connection = ConnectionFactory.getMYSQLConnection();
    Operation operation = new Operation(connection);
    @FXML
    private Button adminButton;
    @FXML
    private Button customerButton;
    @FXML
    private Button signupButton;
    @FXML
    private Button signinButton;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label emailErrorLabel;
    @FXML
    private Label passwordErrorLabel;
    
    private boolean adminSelected = true;

    @FXML
    void toggleAdminCustomer(ActionEvent event) {

        if (event.getSource() == customerButton) {
            // turn on signup button
            signupButton.setDisable(false);

            // change css of adminButton to deselected
            adminButton.setStyle(" -fx-background-radius: 7;\n" +
                    "    -fx-background-insets: 0;\n" +
                    "    -fx-background-color: #d5d5d5;\n" +
                    "    -fx-text-fill: #000000a9;");

            // change css of customerButton to selected
            customerButton.setStyle(" -fx-background-radius: 7;\n" +
                    "    -fx-background-insets: 0;\n" +
                    "    -fx-background-color:  #45BCFA;\n" +
                    "    -fx-text-fill: white;");

            adminSelected = !adminSelected;

        } else if (event.getSource() == adminButton) {
            //turn off signup button
            signupButton.setDisable(true);

            adminButton.setStyle(" -fx-background-radius: 7;\n" +
                    "    -fx-background-insets: 0;\n" +
                    "    -fx-background-color:  #45BCFA;\n" +
                    "    -fx-text-fill: white;");

            customerButton.setStyle(" -fx-background-radius: 7;\n" +
                    "    -fx-background-insets: 0;\n" +
                    "    -fx-background-color: #d5d5d5;\n" +
                    "    -fx-text-fill: #000000a9;");

            adminSelected = !adminSelected;
        }

    }

    @FXML
    void signinButtonClicked(ActionEvent event) {
        String email = emailTextField.getText().trim();
        String password = passwordField.getText().trim();

        if (email.length() == 0) {
            emailErrorLabel.setText("Please enter your email");
            emailErrorLabel.setVisible(true);
        }
        if (password.length() == 0) {
            passwordErrorLabel.setText("Please enter your password");
            passwordErrorLabel.setVisible(true);
            return;
        
        }

        if (email.length() > 0 && password.length() > 0) {
            emailErrorLabel.setVisible(false);
            passwordErrorLabel.setVisible(false);

            if (adminSelected) {
                if (email.equals("admin") && password.equals("admin")) {
                    transitionToAdminScene();
                    return;
                } else {
                    emailErrorLabel.setText("Please check your email and password");
                    emailErrorLabel.setVisible(true);

                }
            } else { // if adminSelected is not true, customerButton is selected
                if (email.equals("jon@abc.com") && password.equals("123")) {
                    transitionToCustomerScene();
                    return;
                } else {
                    emailErrorLabel.setText("Check Your Email!");
                    emailErrorLabel.setVisible(true);
                    passwordErrorLabel.setText("Check Your Password!");
                    passwordErrorLabel.setVisible(true);

                }
            }
        }

    }

    private void transitionToCustomerScene() {
        //get reference to WelcomeScreen stage
        Stage stage = (Stage) customerButton.getScene().getWindow();

        //load up CustomerScene FXML document
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("CustomerScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 1024, 720);
        stage.setTitle("Customer Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    private void transitionToAdminScene() {
        //get reference to WelcomeScreen stage
        Stage stage = (Stage) customerButton.getScene().getWindow();

        //load up AdminScene FXML document
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("AdminScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 1024, 720);
        stage.setTitle("Admin Dashboard");
        stage.setScene(scene);
        stage.show();
    }


}

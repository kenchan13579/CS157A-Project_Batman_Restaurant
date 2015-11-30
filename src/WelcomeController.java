import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
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
    @FXML
    private VBox vboxForLabels;
    @FXML
    private VBox vboxForTextfields;

    @FXML
    private VBox vboxForErrorLabels;

    private TextField fnTextfield;
    private TextField lnTextfield;
    private Label fnErrorLabel;
    private Label lnErrorLabel;


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



            // set up sign in or sign up interface for customer

            //Set up labels
            Label fnLabel = new Label("First Name");
            fnLabel.setFont(new Font("System", 20));
            Label lnLabel = new Label("Last Name");
            lnLabel.setFont(new Font("System", 20));
            Label emailLabel = new Label("Email");
            emailLabel.setFont(new Font("System", 20));

            vboxForLabels.getChildren().clear();
            vboxForLabels.getChildren().addAll(emailLabel, fnLabel, lnLabel);

            //set up text fields
            vboxForTextfields.getChildren().clear();
            emailTextField = new TextField();
            emailTextField.setPromptText("Email");
            fnTextfield = new TextField();
            fnTextfield.setPromptText("First Name");
            lnTextfield = new TextField();
            lnTextfield.setPromptText("Last Name");

            vboxForTextfields.getChildren().addAll(emailTextField, fnTextfield, lnTextfield);

            //set up error labels
            vboxForErrorLabels.getChildren().clear();
            emailErrorLabel = new Label();
            emailErrorLabel.setTextFill(Paint.valueOf("#f5515f"));

            fnErrorLabel = new Label();
            fnErrorLabel.setTextFill(Paint.valueOf("#f5515f"));
            lnErrorLabel = new Label();
            lnErrorLabel.setTextFill(Paint.valueOf("#f5515f"));

            vboxForErrorLabels.setSpacing(63);
            vboxForErrorLabels.getChildren().addAll(emailErrorLabel, fnErrorLabel, lnErrorLabel);
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
            passwordErrorLabel.setText("Enter your password");
            passwordErrorLabel.setVisible(true);
        }




        if (email.length() > 0 && password.length() > 0 ) {
            emailErrorLabel.setText("");
            fnErrorLabel.setText("");
            lnErrorLabel.setText("");
            passwordErrorLabel.setText("");

            if (adminSelected) {
                if (email.equals("admin") && password.equals("admin")) {
                    transitionToAdminScene();
                    return;
                } else {
                    emailErrorLabel.setText("Please use 'admin' for email and pass");
                }
            } else { // if adminSelected is not true, customerButton is selected

                if (operation.getCID(email) != -1) // find the right customer in the database
                {
                    transitionToCustomerScene();
                    return;
                } else { // if not then clear the fields and ask the users to input or sign up
                    emailErrorLabel.setText("Recheck or Sign up");
                    emailErrorLabel.setVisible(true);
                    passwordErrorLabel.setText("Recheck or Sign up");
                    passwordErrorLabel.setVisible(true);
                }


            }
        } else {
            return;
        }

    }

    @FXML
    void signupButtonClicked(ActionEvent event) {
        String email = emailTextField.getText().trim();
        String firstName = fnTextfield.getText().trim();
        String lastName = lnTextfield.getText().trim();


        if (email.length() == 0) {
            emailErrorLabel.setText("Please enter your email");
            emailErrorLabel.setVisible(true);
            return;
        }

        if (firstName.length() == 0) {
            fnErrorLabel.setText("Enter your first name");
        }

        if (lastName.length() == 0) {
            lnErrorLabel.setText("Enter your last name");
        }

        if (email.length() > 0 && firstName.length() > 0 && lastName.length() > 0) {
            emailErrorLabel.setText("");
            fnErrorLabel.setText("");
            lnErrorLabel.setText("");

            if (!adminSelected) {
                boolean success = operation.addCustomer(lastName, firstName, email);
                emailErrorLabel.setText(String.valueOf(success));

                if (success) {
                    transitionToCustomerScene();
                } else {
                    emailErrorLabel.setText("Sign Up Error! Try Again!");
                    return;
                }

            }
        } else {
            return;
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

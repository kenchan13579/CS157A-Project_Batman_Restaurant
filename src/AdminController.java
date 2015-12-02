import Model.Employee;
import Model.Rating;
import Model.Reservation;
import com.mysql.jdbc.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by phucnguyen on 11/10/15.
 */
public class AdminController {

    private Connection connection = ConnectionFactory.getMYSQLConnection();
    private Operation operation = new Operation(connection);

    @FXML
    private Label titleLabel;
    @FXML
    private GridPane mainGridPane;
    @FXML
    private Button restaurantButton;
    @FXML
    private Button reservationsButton;

    @FXML
    private Button customersButton;
    @FXML
    private Button employeesButton;
    @FXML
    private Button receiptButton;
    @FXML
    private Button ratingButton;
    @FXML
    private Button archiveButton;

    @FXML
    private GridPane contentPane;

    // all the images for the buttons
    private ImageView restaurantIMV;
    private ImageView reservationsIMV;
    private ImageView customersIMV;
    private ImageView employeesIMV;
    private ImageView receiptIMV;
    private ImageView ratingIMV;
    private ImageView archiveIMV;


    // all the images for selected buttons
    private ImageView reservationsSelectedIMV;
    private ImageView customersSelectedIMV;
    private ImageView employeesSelectedIMV;
    private ImageView receiptSelectedIMV;
    private ImageView ratingSelectedIMV;
    private ImageView archiveSelectedIMV;

    @FXML
    void initialize() {
        getAllImageViewsForButtons();
        configureWidthHeightForImageViews();
        configureButtons();
        contentPane.setAlignment(Pos.CENTER);
    }

    private void configureWidthHeightForImageViews() {
        //Restaurant
        restaurantIMV.setFitWidth(51);
        restaurantIMV.setFitHeight(41);

        //reservations
        reservationsIMV.setFitWidth(63);
        reservationsIMV.setFitHeight(84);
        reservationsSelectedIMV.setFitWidth(63);
        reservationsSelectedIMV.setFitHeight(84);

        //customers
        customersIMV.setFitWidth(53);
        customersIMV.setFitHeight(65);
        customersSelectedIMV.setFitWidth(53);
        customersSelectedIMV.setFitHeight(65);

        // employees
        employeesIMV.setFitWidth(53);
        employeesIMV.setFitHeight(53);
        employeesSelectedIMV.setFitWidth(53);
        employeesSelectedIMV.setFitHeight(53);

        // receipt
        receiptIMV.setFitWidth(82);
        receiptIMV.setFitHeight(57);
        receiptSelectedIMV.setFitWidth(82);
        receiptSelectedIMV.setFitHeight(57);

        // rating
        ratingIMV.setFitWidth(32);
        ratingIMV.setFitHeight(69);
        ratingSelectedIMV.setFitWidth(32);
        ratingSelectedIMV.setFitHeight(69);

        // archive
        archiveIMV.setFitWidth(37);
        archiveIMV.setFitHeight(51);
        archiveSelectedIMV.setFitWidth(37);
        archiveSelectedIMV.setFitHeight(51);

    }

    private void configureButtons() {
        //Restaurant button
        restaurantButton.setGraphic(restaurantIMV);
        restaurantButton.setStyle("-fx-background-color: transparent");

        //reservations button
        reservationsButton.setGraphic(reservationsIMV);
        reservationsButton.setStyle("-fx-background-color: transparent");

        //customers button
        customersButton.setGraphic(customersIMV);
        customersButton.setStyle("-fx-background-color: transparent");

        //employees button
        employeesButton.setGraphic(employeesIMV);
        employeesButton.setStyle("-fx-background-color: transparent");

        //receipt button
        receiptButton.setGraphic(receiptIMV);
        receiptButton.setStyle("-fx-background-color: transparent");

        //rating button
        ratingButton.setGraphic(ratingIMV);
        ratingButton.setStyle("-fx-background-color: transparent");


        //archive button
        archiveButton.setGraphic(archiveIMV);
        archiveButton.setStyle("-fx-background-color: transparent");

    }

    private void getAllImageViewsForButtons() {
        restaurantIMV = new ImageView(new Image(getClass().getResourceAsStream("Graphics/Restaurant.png")));
        reservationsIMV = new ImageView(new Image(getClass().getResourceAsStream("Graphics/a_reservations.png")));
        customersIMV = new ImageView(new Image(getClass().getResourceAsStream("Graphics/a_customers.png")));
        employeesIMV = new ImageView(new Image(getClass().getResourceAsStream("Graphics/a_employees.png")));
        receiptIMV = new ImageView(new Image(getClass().getResourceAsStream("Graphics/a_receipt.png")));
        ratingIMV = new ImageView(new Image(getClass().getResourceAsStream("Graphics/a_rating.png")));
        archiveIMV = new ImageView(new Image(getClass().getResourceAsStream("Graphics/a_archive.png")));


        reservationsSelectedIMV = new ImageView(new Image(getClass().getResourceAsStream("Graphics/a_reservations_selected.png")));
        customersSelectedIMV = new ImageView(new Image(getClass().getResourceAsStream("Graphics/a_customers_selected.png")));
        employeesSelectedIMV = new ImageView(new Image(getClass().getResourceAsStream("Graphics/a_employees_selected.png")));
        receiptSelectedIMV = new ImageView(new Image(getClass().getResourceAsStream("Graphics/a_receipt_selected.png")));
        ratingSelectedIMV = new ImageView(new Image(getClass().getResourceAsStream("Graphics/a_rating_selected.png")));
        archiveSelectedIMV = new ImageView(new Image(getClass().getResourceAsStream("Graphics/a_archive_selected.png")));


    }

    @FXML
    private void goHomeScreen(ActionEvent event) {
        //get reference to WelcomeScreen stage
        Stage stage = (Stage) mainGridPane.getScene().getWindow();

        //load up WelcomeScene FXML document
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("WelcomeScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 1024, 720);
        stage.setTitle("Restaurant Reservation System");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void reservationsButtonClicked(ActionEvent event) {
        configureButtons();
        reservationsButton.setGraphic(reservationsSelectedIMV);

        //Clear the content of contentPane
        contentPane.getChildren().clear();

        //Set title
        titleLabel.setText("All Reservations");



        //Table view set up
        TableView table = new TableView();
        TableColumn tidCol = new TableColumn("tID");
        tidCol.setCellValueFactory(new PropertyValueFactory<>("tID"));
        TableColumn cidCol = new TableColumn("cID");
        cidCol.setCellValueFactory(new PropertyValueFactory<>("cID"));
        TableColumn partySizeCol = new TableColumn("Party Size");
        partySizeCol.setCellValueFactory(new PropertyValueFactory<>("partySize"));
        TableColumn dateCol = new TableColumn("Reservation Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));

        //set width for all cols
        tidCol.setMinWidth(100);
        cidCol.setMinWidth(100);
        partySizeCol.setMinWidth(100);
        dateCol.setMinWidth(300);


        table.getColumns().addAll(tidCol, cidCol, partySizeCol, dateCol);
        //Populate data to the table view
        ObservableList<Reservation> data = FXCollections.observableArrayList(operation.getAllReservations());
        table.setItems(data);

        //add table view to content pane
        contentPane.getChildren().add(table);
    }

    @FXML
    private void customersButtonClicked(ActionEvent event) {
        configureButtons();
        customersButton.setGraphic(customersSelectedIMV);

        //Clear old content
        contentPane.getChildren().clear();

        //Set up food menu details
        ImageView foodMenuDetails = new ImageView(new Image(getClass().getResourceAsStream("Graphics/FoodMenuDetails.png")));
        foodMenuDetails.setFitWidth(774/1.5);
        foodMenuDetails.setFitHeight(934/1.5);
        VBox box = new VBox();
        box.getChildren().add(foodMenuDetails);
        box.setAlignment(Pos.TOP_CENTER);

        contentPane.getChildren().add(box);
        titleLabel.setText("Food Model.Menu");

    }

    @FXML
    private void employeesButtonClicked(ActionEvent event) {
        configureButtons();
        employeesButton.setGraphic(employeesSelectedIMV);

        //Clear old content
        contentPane.getChildren().clear();

        titleLabel.setText("Employees");

        // 2 horizontal boxes for All Employees and Employees are Customers
        HBox hbox = new HBox();
        hbox.setSpacing(5);

        //2 vboxes for each hbox
        VBox leftBox = new VBox();
        VBox rightBox = new VBox();


        // Set up All employees
        ObservableList<Employee> data = FXCollections.observableArrayList(operation.getAllEmployees());
        TableView table = new TableView();


        //Add title for all employees
        Text leftTitle = new Text("All Employees");
        leftTitle.setFont(new Font("System",24));

        TableColumn fnCol = new TableColumn("First Name");
        fnCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn lnCol = new TableColumn("Last Name");
        lnCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn positionCol = new TableColumn("Position");
        positionCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        TableColumn emailCol = new TableColumn("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn lastworkedCol = new TableColumn("Last Worked");
        lastworkedCol.setCellValueFactory(new PropertyValueFactory<>("lastWorked"));


        table.getColumns().addAll(fnCol, lnCol, positionCol, emailCol, lastworkedCol);
        table.setItems(data);

        //add left table and title to left box
        leftBox.getChildren().addAll(leftTitle, table);
        leftBox.setSpacing(5);


        // Set up for employees and also customers
        ObservableList<Employee> rightData = FXCollections.observableArrayList(operation.getEmployeesWhoAreCustomers());
        TableView rightTable = new TableView();


        //Add title for all employees
        Text rightTitle = new Text("Employees Are Customers");
        rightTitle.setFont(new Font("System",24));

        TableColumn RfnCol = new TableColumn("First Name");
        RfnCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn RlnCol = new TableColumn("Last Name");
        RlnCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn RpositionCol = new TableColumn("Position");
        RpositionCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        TableColumn RemailCol = new TableColumn("Email");
        RemailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn RlastworkedCol = new TableColumn("Last Worked");
        RlastworkedCol.setCellValueFactory(new PropertyValueFactory<>("lastWorked"));


        rightTable.getColumns().addAll(RfnCol, RlnCol, RpositionCol, RemailCol, RlastworkedCol);
        rightTable.setItems(rightData);

        //add right table to the right box
        rightBox.getChildren().addAll(rightTitle, rightTable);
        rightBox.setSpacing(5);

        hbox.getChildren().addAll(leftBox, rightBox);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);

        // add hbox to content pane
        contentPane.getChildren().add(hbox);
    }

    @FXML
    private void receiptButtonClicked(ActionEvent event) {
        configureButtons();
        receiptButton.setGraphic(receiptSelectedIMV);

        //clear old content
        contentPane.getChildren().clear();

        //set up receipt details
        ImageView receiptDetails = new ImageView(new Image(getClass().getResourceAsStream("Graphics/ReceiptDetails.png")));
        receiptDetails.setFitWidth(774/1.5);
        receiptDetails.setFitHeight(419 /1.5);
        VBox box = new VBox();
        box.getChildren().add(receiptDetails);
        box.setAlignment(Pos.TOP_CENTER);

        contentPane.getChildren().add(box);
        titleLabel.setText("Model.Receipt");

    }


    @FXML
    private void ratingButtonClicked(ActionEvent event) throws SQLException {
        configureButtons();
        ratingButton.setGraphic(ratingSelectedIMV);

        //clear old content
        contentPane.getChildren().clear();

        // set up
        titleLabel.setText("Rating and Feedback");

        // rating box
        HBox ratingBox = new HBox();
        ratingBox.setAlignment(Pos.CENTER);
        ratingBox.setSpacing(20);
        ratingBox.setPadding(new Insets(10, 0, 10, 0));

        //Rating label
        Label ratingTitle = new Label("Average Rating: ");
        ratingTitle.setFont(new Font("System", 24));

        // 5 stars
        String yellowStarURL = "Graphics/StarYellow.png";
        String blankStarURL = "Graphics/StarBlank.png";

        ImageView starBlank1 = new ImageView(new Image(getClass().getResourceAsStream("Graphics/StarBlank.png")));
        ImageView starBlank2 = new ImageView(new Image(getClass().getResourceAsStream("Graphics/StarBlank.png")));
        ImageView starBlank3 = new ImageView(new Image(getClass().getResourceAsStream("Graphics/StarBlank.png")));
        ImageView starBlank4 = new ImageView(new Image(getClass().getResourceAsStream("Graphics/StarBlank.png")));
        ImageView starBlank5 = new ImageView(new Image(getClass().getResourceAsStream("Graphics/StarBlank.png")));

        Button star1 = new Button(); star1.setGraphic(starBlank1); star1.setStyle("-fx-background-color: transparent");
        Button star2 = new Button(); star2.setGraphic(starBlank2); star2.setStyle("-fx-background-color: transparent");
        Button star3 = new Button(); star3.setGraphic(starBlank3); star3.setStyle("-fx-background-color: transparent");
        Button star4 = new Button(); star4.setGraphic(starBlank4); star4.setStyle("-fx-background-color: transparent");
        Button star5 = new Button(); star5.setGraphic(starBlank5); star5.setStyle("-fx-background-color: transparent");


        //get average rating
        double avgRating = 0;
        try {
            avgRating = operation.getAverageRating();
        } catch (SQLException e) {
            e.printStackTrace();
            ratingTitle.setText("Error! Can't get average rating");
        }


        Button[] buttonlist = new Button[5];
        buttonlist[0] = star1;
        buttonlist[1] = star2;
        buttonlist[2] = star3;
        buttonlist[3] = star4;
        buttonlist[4] = star5;


        for (int i = 0; i < Math.floor(avgRating); i++) {
            ImageView image = new ImageView(new Image(getClass().getResourceAsStream(yellowStarURL)));
            buttonlist[i].setGraphic(image);
        }



        // feedback box
        VBox feedBackBox = new VBox();
        feedBackBox.setAlignment(Pos.CENTER);
        feedBackBox.setSpacing(20);

        //Feedback title
        Label feedBackLabel = new Label("Feedback:");
        feedBackLabel.setFont(new Font("System", 24));

        //get feedback string
        String feedback = "";
        ArrayList<Rating> list = operation.getRatingsAndFeedbacks();


        for (Rating r : list) {
            feedback += r.getFeedback() + "\n\n";
        }

        Text feedbackText = new Text(feedback);
        feedbackText.setFont(new Font("System", 14));

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(feedbackText);


        //Add children nodes to appropriate boxes
        ratingBox.getChildren().addAll(ratingTitle, star1, star2, star3, star4, star5);
        feedBackBox.getChildren().addAll(feedBackLabel, scrollPane);

        //main box
        VBox mainBox = new VBox();
        mainBox.setSpacing(40);
        mainBox.getChildren().addAll(ratingBox, feedBackBox);

        contentPane.getChildren().add(mainBox);

    }



    @FXML
    private void archiveButtonClicked(ActionEvent event) {
        configureButtons();
        archiveButton.setGraphic(archiveSelectedIMV);

        //clear old content
        contentPane.getChildren().clear();

        // set up
        titleLabel.setText("Archives");
    }

}

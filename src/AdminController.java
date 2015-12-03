import Model.*;
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
import java.util.concurrent.TimeoutException;

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

        titleLabel.setText("Customer Information");

        ScrollPane scrollPane = new ScrollPane();

        contentPane.getChildren().add(scrollPane);

        VBox box = new VBox();
        box.setSpacing(10);
        box.setAlignment(Pos.TOP_CENTER);
        box.setPrefWidth(900);

        //set up for ALL CUSTOMERS
        Text cHeader = new Text("All Customers");
        cHeader.setFont(new Font("System", 24));

        ObservableList<Customer> data = FXCollections.observableArrayList(operation.getAllCustomers());
        TableView cTable = new TableView();

        TableColumn cfnCol = new TableColumn("First Name");
        cfnCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn clnCol = new TableColumn("Last Name");
        clnCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn cEmailCol = new TableColumn("Email");
        cEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn cLastVisitedCol = new TableColumn("Last Visited");
        cLastVisitedCol.setCellValueFactory(new PropertyValueFactory<>("lastVisited"));
        TableColumn cDiscountCol = new TableColumn("Discount");
        cDiscountCol.setCellValueFactory(new PropertyValueFactory<>("discount"));

        cTable.getColumns().addAll(cfnCol, clnCol, cEmailCol, cLastVisitedCol, cDiscountCol);
        cTable.setItems(data);

        //Set up for Customers who do not tip
        Text header2 = new Text("Customer who do not tip");
        header2.setFont(new Font("System", 24));

        ObservableList<Customer> data2 = FXCollections.observableArrayList(operation.getCustomersWhoDoNotTip());
        TableView table2 = new TableView();

        TableColumn cfnCol2 = new TableColumn("First Name");
        cfnCol2.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn clnCol2 = new TableColumn("Last Name");
        clnCol2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn cEmailCol2 = new TableColumn("Email");
        cEmailCol2.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn cLastVisitedCol2 = new TableColumn("Last Visited");
        cLastVisitedCol2.setCellValueFactory(new PropertyValueFactory<>("lastVisited"));
        TableColumn cDiscountCol2 = new TableColumn("Discount");
        cDiscountCol2.setCellValueFactory(new PropertyValueFactory<>("discount"));

        table2.getColumns().addAll(cfnCol2, clnCol2, cEmailCol2, cLastVisitedCol2, cDiscountCol2);
        table2.setItems(data2);


        //Set up for customers who spends more than 100
        Text header3 = new Text("Customers who spends more than $100");
        header3.setFont(new Font("System", 24));

        ObservableList<Customer> data3 = FXCollections.observableArrayList(operation.getCustomersWhoSpendsMoreThan100());
        TableView table3 = new TableView();

        TableColumn cfnCol3 = new TableColumn("First Name");
        cfnCol3.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn clnCol3 = new TableColumn("Last Name");
        clnCol3.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn cEmailCol3 = new TableColumn("Email");
        cEmailCol3.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn cLastVisitedCol3 = new TableColumn("Last Visited");
        cLastVisitedCol3.setCellValueFactory(new PropertyValueFactory<>("lastVisited"));
        TableColumn cDiscountCol3 = new TableColumn("Discount");
        cDiscountCol3.setCellValueFactory(new PropertyValueFactory<>("discount"));

        table3.getColumns().addAll(cfnCol3, clnCol3, cEmailCol3, cLastVisitedCol3, cDiscountCol3);
        table3.setItems(data3);


        box.getChildren().addAll(cHeader, cTable, header2, table2, header3, table3);
        scrollPane.setContent(box);
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



        //add left table and title to left box
        leftBox.getChildren().addAll(leftTitle, table);
        leftBox.setSpacing(5);

        TableColumn efnCol = new TableColumn("First Name");
        efnCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn elnCol = new TableColumn("Last Name");
        elnCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn epositionCol = new TableColumn("Position");
        epositionCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        TableColumn eemailCol = new TableColumn("Email");
        eemailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn elastworkedCol = new TableColumn("Last Worked");
        elastworkedCol.setCellValueFactory(new PropertyValueFactory<>("lastWorked"));

        table.getColumns().addAll(efnCol, elnCol, epositionCol, eemailCol, elastworkedCol);
        table.setItems(data);

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
        titleLabel.setText("Receipt");

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

        // Scroll pane
        ScrollPane scrollPane = new ScrollPane();

        // add scroll pane to the content pane
        contentPane.getChildren().add(scrollPane);

        // create vbox
        VBox box = new VBox();
        box.setSpacing(5);

        //create archive table for Employees
        Text eTitle = new Text("Archived Employees");
        eTitle.setFont(new Font("System", 24));

        if (operation.archive()) {
            //Table for employees
            ObservableList<Employee> edata = FXCollections.observableArrayList(operation.getArchivedEmployees());
            TableView eTable = new TableView();

            TableColumn efnCol = new TableColumn("First Name");
            efnCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            TableColumn elnCol = new TableColumn("Last Name");
            elnCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            TableColumn epositionCol = new TableColumn("Position");
            epositionCol.setCellValueFactory(new PropertyValueFactory<>("position"));
            TableColumn eemailCol = new TableColumn("Email");
            eemailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
            TableColumn elastworkedCol = new TableColumn("Last Worked");
            elastworkedCol.setCellValueFactory(new PropertyValueFactory<>("lastWorked"));


            eTable.getColumns().addAll(efnCol, elnCol, epositionCol, eemailCol, elastworkedCol);
            eTable.setItems(edata);
            box.getChildren().addAll(eTitle, eTable);

            scrollPane.setContent(box);

        } else {
            titleLabel.setText("Can't Archive");
            return;
        }

    }

}

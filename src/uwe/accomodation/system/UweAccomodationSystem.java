/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uwe.accomodation.system;

import javafx.application.Application;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Modality;
import javafx.geometry.Pos;
import javafx.scene.text.TextAlignment;
import java.util.stream.Collectors;

/**
 *
 * @author Brice
 */
import java.nio.file.FileSystems;
import java.nio.file.Path;
public class UweAccomodationSystem extends Application {

    //List used for storing table data
    private ObservableList tableList
            = FXCollections.observableArrayList();

    //Lists used for storing object instances
    private ArrayList<Hall> hallList = new ArrayList();
    private ArrayList<Room> roomList = new ArrayList();
    private ArrayList<Student> studentList = new ArrayList();
    private ArrayList<StudentLease> leaseList = new ArrayList();

    @Override
    public void start(Stage primaryStage) {

        //Load file data
        try {
            getFileData();
        } catch (IOException ex) {
            Logger.getLogger(UweAccomodationSystem.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Fill tableList with data               
        getTableData();

        // Load image from file
        Image image = new Image("resources/uwe-logo.png");

        ImageView logo = new ImageView(image);
        logo.setLayoutX(34.0);
        logo.setLayoutY(14.0);
        logo.setFitHeight(71.0);
        logo.setFitWidth(108.0);
        logo.setPreserveRatio(true);
        logo.setPickOnBounds(true);

        // Main stage texts
        Label label1 = new Label();
        label1.setLayoutX(165.0);
        label1.setLayoutY(30.0);
        label1.setText("UWE Bristol Accomodation System");
        label1.setFont(Font.font("System", FontWeight.BOLD, 15));

        Label label2 = new Label();
        label2.setLayoutX(80.0);
        label2.setLayoutY(83.0);
        label2.setFont(Font.font("System", FontWeight.BOLD, 12));
        label2.setText("Please select wich user interface you wish to use");

        //Main stage buttons
        Button btn = new Button();
        btn.setText("Manager");
        btn.setPrefSize(100, 40);
        btn.setLayoutX(15.0);
        btn.setLayoutY(125.0);

        Button btn2 = new Button();
        btn2.setText("Warden");
        btn2.setPrefSize(100, 40);
        btn2.setLayoutX(165);
        btn2.setLayoutY(125.0);

        Button btn3 = new Button();
        btn3.setText("All");
        btn3.setPrefSize(100, 40);
        btn3.setLayoutX(315.0);
        btn3.setLayoutY(125.0);

        //Manager,Warden, All stage labels
        Label titleLabel = new Label("Lease update");
        Label titleLabel2 = new Label("Room Status information ");
        Label titleLabel3 = new Label("Lease/Room status update");
        Label hallNameLabel = new Label("Hall Name:");
        Label roomNumberLabel = new Label("Room Number:");
        Label occupancyLabel = new Label("Occupancy:");
        Label leaseNumberLabel = new Label("Lease Number:");
        Label leaseDurationLabel = new Label("Lease Duration (Months):");
        Label studentNameLabel = new Label("Student Name:");
        Label cleanStatusLabel = new Label("Clean Status:");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 15));
        titleLabel2.setFont(Font.font("System", FontWeight.BOLD, 15));
        titleLabel3.setFont(Font.font("System", FontWeight.BOLD, 15));
        titleLabel.setLayoutX(20);
        titleLabel.setLayoutY(340);
        titleLabel2.setLayoutX(20);
        titleLabel2.setLayoutY(340);
        titleLabel3.setLayoutX(20);
        titleLabel3.setLayoutY(340);
        hallNameLabel.setFont(Font.font("System", FontWeight.BOLD, 11));
        hallNameLabel.setLayoutX(20);
        hallNameLabel.setLayoutY(380);
        roomNumberLabel.setFont(Font.font("System", FontWeight.BOLD, 11));
        roomNumberLabel.setLayoutX(20);
        roomNumberLabel.setLayoutY(400);
        occupancyLabel.setFont(Font.font("System", FontWeight.BOLD, 11));
        occupancyLabel.setLayoutX(20);
        occupancyLabel.setLayoutY(420);
        leaseNumberLabel.setFont(Font.font("System", FontWeight.BOLD, 11));
        leaseNumberLabel.setLayoutX(250);
        leaseNumberLabel.setLayoutY(380);
        leaseDurationLabel.setFont(Font.font("System", FontWeight.BOLD, 11));
        leaseDurationLabel.setLayoutX(250);
        leaseDurationLabel.setLayoutY(400);
        studentNameLabel.setFont(Font.font("System", FontWeight.BOLD, 11));
        studentNameLabel.setLayoutX(250);
        studentNameLabel.setLayoutY(420);
        cleanStatusLabel.setFont(Font.font("System", FontWeight.BOLD, 11));
        cleanStatusLabel.setLayoutX(250);
        cleanStatusLabel.setLayoutY(400);

        //Text Areas & Combo Boxes
        TextArea hallNameArea = new TextArea();
        TextArea roomNumberArea = new TextArea();
        ComboBox occupancyCombo = new ComboBox();
        TextArea occupancyArea = new TextArea();
        TextArea leaseNumberArea = new TextArea();
        TextArea leaseDurationArea = new TextArea();
        ComboBox studentNameCombo = new ComboBox();
        TextArea studentNameArea = new TextArea();
        TextArea cleanStatusArea = new TextArea();
        ComboBox cleanStatusCombo = new ComboBox();
        hallNameArea.setEditable(false);
        hallNameArea.setPrefSize(110, 2);
        hallNameArea.setLayoutX(120);
        hallNameArea.setLayoutY(380);
        roomNumberArea.setEditable(false);
        roomNumberArea.setPrefSize(110, 5);
        roomNumberArea.setLayoutX(120);
        roomNumberArea.setLayoutY(400);
        studentNameArea.setEditable(false);
        studentNameArea.setPrefSize(120, 5);
        studentNameArea.setLayoutX(400);
        studentNameArea.setLayoutY(420);
        occupancyArea.setEditable(false);
        occupancyArea.setPrefSize(110, 5);
        occupancyArea.setLayoutX(120);
        occupancyArea.setLayoutY(420);
        occupancyCombo.setEditable(true);
        occupancyCombo.setPrefSize(110, 5);
        occupancyCombo.setLayoutX(120);
        occupancyCombo.setLayoutY(420);
        occupancyCombo.getItems().addAll(
                "Occupied",
                "Unoccupied"
        );
        leaseNumberArea.setEditable(true);
        leaseNumberArea.setPrefSize(120, 5);
        leaseNumberArea.setLayoutX(400);
        leaseNumberArea.setLayoutY(380);
        leaseDurationArea.setEditable(true);
        leaseDurationArea.setPrefSize(120, 5);
        leaseDurationArea.setLayoutX(400);
        leaseDurationArea.setLayoutY(400);
        studentNameCombo.setEditable(true);
        studentNameCombo.setPrefSize(120, 5);
        studentNameCombo.setLayoutX(400);
        studentNameCombo.setLayoutY(420);
        for (Student currentStudent : studentList) {
            studentNameCombo.getItems().add(currentStudent.getName());
        }
        cleanStatusArea.setEditable(false);
        cleanStatusArea.setPrefSize(100, 20);
        cleanStatusArea.setLayoutX(150);
        cleanStatusArea.setLayoutY(420);
        cleanStatusArea.setStyle("-fx-control-inner-background: #D6D6D6");
        cleanStatusCombo.setEditable(true);
        cleanStatusCombo.setPrefSize(120, 5);
        cleanStatusCombo.setLayoutX(400);
        cleanStatusCombo.setLayoutY(400);
        cleanStatusCombo.getItems().addAll(
                "Clean",
                "Dirty",
                "Offline"
        );

        //Table label
        Label tableLabel = new Label("Room/lease information:");
        tableLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                Stage managerStage = new Stage();

                //Table label
                final Label label = new Label("Lease information");
                label.setFont(Font.font("System", FontWeight.BOLD, 15));

                //Create table columns and set fill data
                TableView table = new TableView();
                table.setEditable(true);
                TableColumn leaseNumCol = new TableColumn("Lease\nNumber");
                leaseNumCol.setCellValueFactory(
                        new PropertyValueFactory<Table, String>("leaseNumber"));
                TableColumn leaseDurCol = new TableColumn("Lease\nDuration");
                leaseDurCol.setCellValueFactory(
                        new PropertyValueFactory<Table, String>("leaseDuration"));
                TableColumn hallNameCol = new TableColumn("Hall\nName ");
                hallNameCol.setCellValueFactory(
                        new PropertyValueFactory<Table, String>("hallName"));
                TableColumn hallNumberCol = new TableColumn("Hall\nNumber");
                hallNumberCol.setCellValueFactory(
                        new PropertyValueFactory<Table, Integer>("HallNumber"));
                TableColumn roomNumCol = new TableColumn("Room\nNumber");
                roomNumCol.setCellValueFactory(
                        new PropertyValueFactory<Table, Integer>("roomNumber"));
                TableColumn studentNameCol = new TableColumn("Student\nName");
                studentNameCol.setCellValueFactory(
                        new PropertyValueFactory<Table, String>("studentName"));
                TableColumn occupancyCol = new TableColumn("Occupancy\nStatus");
                occupancyCol.setCellValueFactory(
                        new PropertyValueFactory<Table, String>("occupancy"));
                TableColumn cleaningCol = new TableColumn("Cleaning\nStatus");
                cleaningCol.setCellValueFactory(
                        new PropertyValueFactory<Table, String>("cleanStatus"));
                table.setItems(tableList);
                table.getColumns().addAll(leaseNumCol, leaseDurCol, hallNameCol, hallNumberCol, roomNumCol,
                        studentNameCol, occupancyCol, cleaningCol);

                //Detect row selection, fill text areas with corresponding data
                table.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        for (Iterator it = tableList.iterator(); it.hasNext();) {
                            Table currentTable = (Table) it.next();
                            if (currentTable
                                    == table.getSelectionModel()
                                            .getSelectedItem()) {
                                hallNameArea.setText(
                                        currentTable.getHallName());
                                roomNumberArea.setText(Integer.toString(
                                        currentTable.getRoomNumber()));
                                occupancyCombo.getSelectionModel().select(
                                        currentTable.getOccupancy());
                                leaseNumberArea.setText(
                                        currentTable.getLeaseNumber());
                                leaseDurationArea.setText(
                                        currentTable.getLeaseDuration()
                                                .replace(" Months", ""));
                                studentNameCombo.getSelectionModel().select(
                                        currentTable.getStudentName());
                                cleanStatusArea.setText(
                                        currentTable.getCleanStatus());
                                break;
                            }
                        }
                    }
                });
                //Form table
                VBox tableVbox = new VBox();
                tableVbox.setSpacing(5);
                tableVbox.getChildren().addAll(label, table);
                tableVbox.setLayoutX(20);
                tableVbox.setLayoutY(20);
                tableVbox.setPrefSize(600, 320);

                // Back button
                Button backBtn = new Button();
                backBtn.setText("Back");
                backBtn.setPrefSize(50, 20);
                backBtn.setStyle("-fx-font-size: 1em; ");
                backBtn.setLayoutX(20);
                backBtn.setLayoutY(470);

                //Back button handling
                backBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        managerStage.close();
                        primaryStage.show();
                    }
                });

                // Delete button
                Button delBtn = new Button();
                delBtn.setText("Delete");
                delBtn.setPrefSize(70, 20);
                delBtn.setStyle("-fx-font-size: 1em; ");
                delBtn.setLayoutX(400);
                delBtn.setLayoutY(470);

                //When Delete button pressed 
                delBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        for (Iterator it = tableList.iterator(); it.hasNext();) {
                            Table currentTable = (Table) it.next();
                            if (currentTable == table.getSelectionModel()
                                    .getSelectedItem()) {
                                //Create popup used for error display
                                Stage errorDialog = new Stage();
                                errorDialog.initModality(Modality.APPLICATION_MODAL);
                                errorDialog.initOwner(primaryStage);
                                errorDialog.setTitle("ERROR");
                                VBox dialogVbox = new VBox(20);
                                Scene dialogScene = new Scene(dialogVbox, 300, 50);
                                errorDialog.setScene(dialogScene);
                                Text errorText = new Text();
                                errorText.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

                                //Check if lease present
                                if (currentTable.getLeaseNumber().equals("N/A")) {
                                    errorText.setText("No lease to delete.");
                                    errorText.setTextAlignment(TextAlignment.CENTER);
                                    dialogVbox.getChildren().add(errorText);
                                    dialogVbox.setAlignment(Pos.CENTER);
                                    errorDialog.show();
                                } else {
                                    //Remove lease from system 
                                    for (Hall currentHall : hallList) {
                                        for (Room currentRoom : roomList) {
                                            if ((hallNameArea.getText().equals(currentHall.getHallName()))
                                                    && (currentRoom.getRoomNumber() == Integer.parseInt(roomNumberArea.getText()))) {
                                                currentRoom.setOccupancy("Unoccupied");
                                                for (StudentLease currentLease : leaseList) {
                                                    if (leaseNumberArea.getText().equals(Integer.toString(currentLease.getLeaseNumber()))) {
                                                        leaseList.remove(currentLease);
//                                                        errorText.setText(" lease deleted.");
//                                    errorText.setTextAlignment(TextAlignment.CENTER);
//                                    dialogVbox.getChildren().add(errorText);
//                                    dialogVbox.setAlignment(Pos.CENTER);
//                                    errorDialog.show();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    try {
                                        saveData();
                                    } catch (IOException ex) {
                                        Logger.getLogger(UweAccomodationSystem.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    //Change data in Table Class
                                    currentTable.setLeaseNumber("N/A");
                                    currentTable.setLeaseDuration("N/A");
                                    currentTable.setOccupancy("Unoccupied");
                                    currentTable.setStudentName("N/A");
                                    table.refresh();
                                }
                            }
                        }
                    }
                });

                // Update button
                Button updateBtn = new Button();
                updateBtn.setText("Update");
                updateBtn.setPrefSize(70, 20);
                updateBtn.setStyle("-fx-font-size: 1em; ");
                updateBtn.setLayoutX(530);
                updateBtn.setLayoutY(470);

                // When Update button pressed
                updateBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        for (Iterator it = tableList.iterator(); it.hasNext();) {
                            Table currentTable = (Table) it.next();
                            if (currentTable == table.getSelectionModel()
                                    .getSelectedItem()) {
                                //Create popup used for error display
                                Stage errorDialog = new Stage();
                                errorDialog.initModality(Modality.APPLICATION_MODAL);
                                errorDialog.initOwner(primaryStage);
                                errorDialog.setTitle("ERROR");
                                VBox dialogVbox = new VBox(20);
                                Scene dialogScene = new Scene(dialogVbox, 300, 50);
                                errorDialog.setScene(dialogScene);
                                Text errorText = new Text();
                                errorText.setFont(Font.font("System", FontWeight.BOLD, 13));

                                try {
                                    //Check if room clean status
                                    if (cleanStatusArea.getText().equals("Offline")) {
                                        errorText.setText("Room is offline.");
                                        throw new NumberFormatException();
                                    }

                                    //Change data in relevant classes                                   
                                    for (Hall currentHall : hallList) {
                                        for (Room currentRoom : roomList) {
                                            if ((hallNameArea.getText().equals(currentHall.getHallName()))
                                                    && (currentRoom.getRoomNumber() == Integer.parseInt(roomNumberArea.getText()))) {
                                                currentRoom.setOccupancy(occupancyCombo.getValue().toString());
                                                //Get student ID
                                                int studentID = 0;
                                                for (Student currentStudent : studentList) {
                                                    if (studentNameCombo.getValue().toString().equals(currentStudent.getName())) {
                                                        studentID = currentStudent.getID();
                                                        break;
                                                    }
                                                }
                                                if (currentTable.getLeaseNumber().equals("N/A")) {//Create new lease                                                                                                    
                                                    int leaseNumber = Integer.parseInt(leaseNumberArea.getText());
                                                    int leaseDuration = Integer.parseInt(leaseDurationArea.getText());
                                                    int hallID = currentHall.getHallID();
                                                    int roomNumber = currentRoom.getRoomNumber();
                                                    leaseList.add(new StudentLease(leaseNumber, leaseDuration, hallID,
                                                            roomNumber, studentID));
                                                    break;
                                                } else { //Append existing lease
                                                    for (StudentLease currentLease : leaseList) {
                                                        if (leaseNumberArea.getText().equals(Integer.toString(currentLease.getLeaseNumber()))) {
                                                            currentLease.setLeaseNumber(Integer.parseInt(leaseNumberArea.getText()));
                                                            currentLease.setLeaseDuration(Integer.parseInt(leaseDurationArea.getText()));
                                                            currentLease.setStudentID(studentID);
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    //Change data in Table Class
                                    currentTable.setLeaseNumber(
                                            leaseNumberArea.getText());
                                    currentTable.setLeaseDuration(
                                            leaseDurationArea.getText());
                                    currentTable.setOccupancy(
                                            occupancyCombo.getValue().toString());
                                    currentTable.setStudentName(studentNameCombo
                                            .getValue().toString());
                                    table.refresh();

                                } catch (NumberFormatException eNum) {
                                    errorText.setTextAlignment(TextAlignment.CENTER);
                                    dialogVbox.getChildren().add(errorText);
                                    dialogVbox.setAlignment(Pos.CENTER);
                                    errorDialog.show();
                                }
                                break;
                            }
                        }
                        try {
                            saveData();
                        } catch (IOException ex) {
                            Logger.getLogger(UweAccomodationSystem.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

                //Create pane and add objects 
                Pane root = new Pane();
                root.getChildren().add(backBtn);
                root.getChildren().add(delBtn);
                root.getChildren().add(updateBtn);
                root.getChildren().addAll(tableVbox);
                root.getChildren().addAll(titleLabel, hallNameLabel, //Labels
                        roomNumberLabel,
                        occupancyLabel,
                        leaseNumberLabel,
                        leaseDurationLabel,
                        studentNameLabel
                );
                root.getChildren().addAll(hallNameArea, //Text areas & Combos
                        roomNumberArea,
                        occupancyCombo,
                        leaseNumberArea,
                        leaseDurationArea,
                        studentNameCombo
                );

                //Set scene dimensions and title
                Scene scene = new Scene(root, 680, 505);
                managerStage.setTitle("UWE Bristol Accomodation System- Manager Interface");

                //Set scene to stage and show
                managerStage.setScene(scene);
                managerStage.show();
            }

        });

        //Warden Burtton handling
        btn2.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event
            ) {
                //Switch stage
                primaryStage.close();
                Stage wardenStage = new Stage();

                //Table label
                final Label label = new Label("Room information");
                label.setFont(Font.font("System", FontWeight.BOLD, 15));

                //Create table columns and set fill data
                TableView table = new TableView();
                table.setEditable(true);
                TableColumn leaseNumCol = new TableColumn("Lease\nNumber");
                leaseNumCol.setCellValueFactory(
                        new PropertyValueFactory<Table, String>("leaseNumber"));
                TableColumn leaseDurCol = new TableColumn("Lease\nDuration");
                leaseDurCol.setCellValueFactory(
                        new PropertyValueFactory<Table, String>("leaseDuration"));
                TableColumn hallNameCol = new TableColumn("Hall\nName ");
                hallNameCol.setCellValueFactory(
                        new PropertyValueFactory<Table, String>("hallName"));
                TableColumn hallNumberCol = new TableColumn("Hall\nNumber");
                hallNumberCol.setCellValueFactory(
                        new PropertyValueFactory<Table, Integer>("HallNumber"));
                TableColumn roomNumCol = new TableColumn("Room\nNumber");
                roomNumCol.setCellValueFactory(
                        new PropertyValueFactory<Table, Integer>("roomNumber"));
                TableColumn studentNameCol = new TableColumn("Student\nName");
                studentNameCol.setCellValueFactory(
                        new PropertyValueFactory<Table, String>("studentName"));
                TableColumn occupancyCol = new TableColumn("Occupancy\nStatus");
                occupancyCol.setCellValueFactory(
                        new PropertyValueFactory<Table, String>("occupancy"));
                TableColumn cleaningCol = new TableColumn("Cleaning\nStatus");
                cleaningCol.setCellValueFactory(
                        new PropertyValueFactory<Table, String>("cleanStatus"));
                table.setItems(tableList);
                table.getColumns().addAll(leaseNumCol, leaseDurCol, hallNameCol, hallNumberCol, roomNumCol,
                        studentNameCol, occupancyCol, cleaningCol);

                //Detect row selection, fill text areas with corresponding data
                table.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        for (Iterator it = tableList.iterator(); it.hasNext();) {
                            Table currentTable = (Table) it.next();
                            if (currentTable
                                    == table.getSelectionModel()
                                            .getSelectedItem()) {
                                hallNameArea.setText(
                                        currentTable.getHallName());
                                roomNumberArea.setText(Integer.toString(
                                        currentTable.getRoomNumber()));
                                occupancyArea.setText(
                                        currentTable.getOccupancy());
                                leaseNumberArea.setText(
                                        currentTable.getLeaseNumber());
                                studentNameArea.setText(
                                        currentTable.getStudentName());
                                cleanStatusCombo.getSelectionModel().select(
                                        currentTable.getCleanStatus());

                                break;
                            }
                        }
                    }
                });
                //Form table
                final VBox tableVbox = new VBox();
                tableVbox.setSpacing(5);
                tableVbox.getChildren().addAll(label, table);
                tableVbox.setLayoutX(20);
                tableVbox.setLayoutY(20);
                tableVbox.setPrefSize(600, 320);

                // Back button
                Button backBtn = new Button();
                backBtn.setText("Back");
                backBtn.setPrefSize(50, 20);
                backBtn.setStyle("-fx-font-size: 1em; ");
                backBtn.setLayoutX(20);
                backBtn.setLayoutY(470);

                //Back button handling
                backBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        wardenStage.close();
                        primaryStage.show();
                    }
                });

                Button applyBtn = new Button();
                applyBtn.setText("Apply change");
                applyBtn.setPrefSize(100, 20);
                applyBtn.setStyle("-fx-font-size: 1em; ");
                applyBtn.setLayoutX(530);
                applyBtn.setLayoutY(470);

                //Apply Button handling  
                applyBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        for (Iterator it = tableList.iterator(); it.hasNext();) {
                            Table currentTable = (Table) it.next();
                            if (currentTable == table.getSelectionModel()
                                    .getSelectedItem()) {
                                //Create popup used for error display
                                Stage errorDialog = new Stage();
                                errorDialog.initModality(Modality.APPLICATION_MODAL);
                                errorDialog.initOwner(primaryStage);
                                errorDialog.setTitle("ERROR");
                                VBox dialogVbox = new VBox(20);
                                Scene dialogScene = new Scene(dialogVbox, 300, 50);
                                errorDialog.setScene(dialogScene);
                                Text errorText = new Text();
                                errorText.setFont(Font.font("System", FontWeight.BOLD, 13));

                                //Update clean status
                                try {

                                    if (leaseNumberArea.getText().equals("N/A")) {
                                    } else {
                                        if (cleanStatusCombo.getValue().toString().equals("Offline")) {
                                            throw new NoSuchMethodException();
                                        }
                                    }
                                    //Change clean status in Room class                                               
                                    int roomNumber = currentTable.getRoomNumber();
                                    int hallID = 0;
                                    for (Hall currentHall : hallList) {
                                        if (currentTable.getHallName().equals(currentHall.getHallName())) {
                                            hallID = currentHall.getHallID();
                                        }
                                    }
                                    for (Room currentRoom : roomList) {
                                        if ((currentRoom.getHallID() == hallID) && (currentRoom.getRoomNumber() == roomNumber)) {
                                            currentRoom.setCleanStatus(cleanStatusCombo.getValue().toString());
                                            break;
                                        }
                                    }
                                    //Save to file
                                    try {
                                        saveData();
                                    } catch (IOException ex) {
                                        Logger.getLogger(UweAccomodationSystem.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    //Change clean status in Table Class
                                    currentTable.setCleanStatus(cleanStatusCombo.getValue().toString());
                                    table.refresh();

                                } catch (NoSuchMethodException offline) {
                                    errorText.setText("An occupied room cannot\nbe set to Offline.");
                                    errorText.setTextAlignment(TextAlignment.CENTER);
                                    dialogVbox.getChildren().add(errorText);
                                    dialogVbox.setAlignment(Pos.CENTER);
                                    errorDialog.show();
                                }
                            }
                        }
                    }
                });

                //Create pane and add objects 
                Pane root = new Pane();
                root.getChildren().add(backBtn);
                root.getChildren().add(applyBtn);
                root.getChildren().addAll(tableVbox);
                root.getChildren().addAll(titleLabel2, hallNameLabel, //Labels
                        roomNumberLabel,
                        occupancyLabel,
                        studentNameLabel,
                        cleanStatusLabel
                );
                root.getChildren().addAll(hallNameArea, //Text areas & Combos
                        roomNumberArea,
                        occupancyArea,
                        studentNameArea,
                        cleanStatusCombo
                );

                //Set scene dimensions and title
                Scene scene = new Scene(root, 680, 505);
                wardenStage.setTitle("UWE Bristol Accomodation System- Warden Interface");

                //Set scene to stage and show
                wardenStage.setScene(scene);
                wardenStage.show();
            }

        });

        //All Burtton handling
        btn3.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event
            ) {
                //Switch stage
                primaryStage.close();
                Stage allStage = new Stage();

                //Table label
                final Label label = new Label("All information");
                label.setFont(Font.font("System", FontWeight.BOLD, 15));

                //Create table columns and set fill data
                TableView table = new TableView();
                table.setEditable(true);
                TableColumn leaseNumCol = new TableColumn("Lease\nNumber");
                leaseNumCol.setCellValueFactory(
                        new PropertyValueFactory<Table, String>("leaseNumber"));
                TableColumn leaseDurCol = new TableColumn("Lease\nDuration");
                leaseDurCol.setCellValueFactory(
                        new PropertyValueFactory<Table, String>("leaseDuration"));
                TableColumn hallNameCol = new TableColumn("Hall\nName ");
                hallNameCol.setCellValueFactory(
                        new PropertyValueFactory<Table, String>("hallName"));
                TableColumn hallNumberCol = new TableColumn("Hall\nNumber");
                hallNumberCol.setCellValueFactory(
                        new PropertyValueFactory<Table, Integer>("HallNumber"));
                TableColumn roomNumCol = new TableColumn("Room\nNumber");
                roomNumCol.setCellValueFactory(
                        new PropertyValueFactory<Table, Integer>("roomNumber"));
                TableColumn studentNameCol = new TableColumn("Student\nName");
                studentNameCol.setCellValueFactory(
                        new PropertyValueFactory<Table, String>("studentName"));
                TableColumn occupancyCol = new TableColumn("Occupancy\nStatus");
                occupancyCol.setCellValueFactory(
                        new PropertyValueFactory<Table, String>("occupancy"));
                TableColumn cleaningCol = new TableColumn("Cleaning\nStatus");
                cleaningCol.setCellValueFactory(
                        new PropertyValueFactory<Table, String>("cleanStatus"));
                table.setItems(tableList);
                table.getColumns().addAll(leaseNumCol, leaseDurCol, hallNameCol, hallNumberCol, roomNumCol,
                        studentNameCol, occupancyCol, cleaningCol);

                //Detect row selection, fill text areas with corresponding data
                table.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        for (Iterator it = tableList.iterator(); it.hasNext();) {
                            Table currentTable = (Table) it.next();
                            if (currentTable
                                    == table.getSelectionModel()
                                            .getSelectedItem()) {
                                hallNameArea.setText(
                                        currentTable.getHallName());
                                roomNumberArea.setText(Integer.toString(
                                        currentTable.getRoomNumber()));
                                occupancyCombo.getSelectionModel().select(
                                        currentTable.getOccupancy());
                                leaseNumberArea.setText(
                                        currentTable.getLeaseNumber());
                                leaseDurationArea.setText(
                                        currentTable.getLeaseDuration()
                                                .replace(" Months", ""));
                                studentNameCombo.getSelectionModel().select(
                                        currentTable.getStudentName());
                                cleanStatusCombo.getSelectionModel().select(
                                        currentTable.getCleanStatus());

                                break;
                            }
                        }
                    }
                });
                //Form table
                final VBox tableVbox = new VBox();
                tableVbox.setSpacing(5);
                tableVbox.getChildren().addAll(label, table);
                tableVbox.setLayoutX(20);
                tableVbox.setLayoutY(20);
                tableVbox.setPrefSize(600, 320);

                // Back button
                Button backBtn = new Button();
                backBtn.setText("Back");
                backBtn.setPrefSize(50, 20);
                backBtn.setStyle("-fx-font-size: 1em; ");
                backBtn.setLayoutX(20);
                backBtn.setLayoutY(470);

                //Back button handling
                backBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        allStage.close();
                        primaryStage.show();
                    }
                });

                // Delete button
                Button delBtn = new Button();
                delBtn.setText("Delete lease");
                delBtn.setPrefSize(100, 20);
                delBtn.setStyle("-fx-font-size: 1em; ");
                delBtn.setLayoutX(400);
                delBtn.setLayoutY(470);

                //When Delete button pressed 
                delBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        for (Iterator it = tableList.iterator(); it.hasNext();) {
                            Table currentTable = (Table) it.next();
                            if (currentTable == table.getSelectionModel()
                                    .getSelectedItem()) {
                                //Create popup used for error display
                                Stage errorDialog = new Stage();
                                errorDialog.initModality(Modality.APPLICATION_MODAL);
                                errorDialog.initOwner(primaryStage);
                                errorDialog.setTitle("ERROR");
                                VBox dialogVbox = new VBox(20);
                                Scene dialogScene = new Scene(dialogVbox, 300, 50);
                                errorDialog.setScene(dialogScene);
                                Text errorText = new Text();
                                errorText.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

                                //Check if lease present
                                if (currentTable.getLeaseNumber().equals("N/A")) {
                                    errorText.setText("No lease to delete.");
                                    errorText.setTextAlignment(TextAlignment.CENTER);
                                    dialogVbox.getChildren().add(errorText);
                                    dialogVbox.setAlignment(Pos.CENTER);
                                    errorDialog.show();
                                } else {
                                    //Remove lease from system 
                                    for (Hall currentHall : hallList) {
                                        for (Room currentRoom : roomList) {
                                            if ((hallNameArea.getText().equals(currentHall.getHallName()))
                                                    && (currentRoom.getRoomNumber() == Integer.parseInt(roomNumberArea.getText()))) {
                                                currentRoom.setOccupancy("Unoccupied");
                                                for (StudentLease currentLease : leaseList) {
                                                    if (leaseNumberArea.getText().equals(Integer.toString(currentLease.getLeaseNumber()))) {
                                                        leaseList.remove(currentLease);
//                                                        
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    try {
                                        saveData();
                                    } catch (IOException ex) {
                                        Logger.getLogger(UweAccomodationSystem.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    //Change data in Table Class
                                    currentTable.setLeaseNumber("N/A");
                                    currentTable.setLeaseDuration("N/A");
                                    currentTable.setOccupancy("Unoccupied");
                                    currentTable.setStudentName("N/A");
                                    table.refresh();
                                }
                            }
                        }
                    }
                });

                // Update button
                Button updateBtn = new Button();
                updateBtn.setText("Update lease");
                updateBtn.setPrefSize(100, 20);
                updateBtn.setStyle("-fx-font-size: 1em; ");
                updateBtn.setLayoutX(530);
                updateBtn.setLayoutY(470);

                // When Update button pressed
                updateBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        for (Iterator it = tableList.iterator(); it.hasNext();) {
                            Table currentTable = (Table) it.next();
                            if (currentTable == table.getSelectionModel()
                                    .getSelectedItem()) {
                                //Create popup used for error display
                                Stage errorDialog = new Stage();
                                errorDialog.initModality(Modality.APPLICATION_MODAL);
                                errorDialog.initOwner(primaryStage);
                                errorDialog.setTitle("ERROR");
                                VBox dialogVbox = new VBox(20);
                                Scene dialogScene = new Scene(dialogVbox, 300, 50);
                                errorDialog.setScene(dialogScene);
                                Text errorText = new Text();
                                errorText.setFont(Font.font("System", FontWeight.BOLD, 13));

                                try {
                                    //Check if room clean status
                                    if (cleanStatusArea.getText().equals("Offline")) {
                                        errorText.setText("Room is offline.");
                                        throw new NumberFormatException();
                                    }

                                    //Change data in relevant classes                                   
                                    for (Hall currentHall : hallList) {
                                        for (Room currentRoom : roomList) {
                                            if ((hallNameArea.getText().equals(currentHall.getHallName()))
                                                    && (currentRoom.getRoomNumber() == Integer.parseInt(roomNumberArea.getText()))) {
                                                currentRoom.setOccupancy(occupancyCombo.getValue().toString());
                                                //Get student ID
                                                int studentID = 0;
                                                for (Student currentStudent : studentList) {
                                                    if (studentNameCombo.getValue().toString().equals(currentStudent.getName())) {
                                                        studentID = currentStudent.getID();
                                                        break;
                                                    }
                                                }
                                                if (currentTable.getLeaseNumber().equals("N/A")) {//Create new lease                                                                                                    
                                                    int leaseNumber = Integer.parseInt(leaseNumberArea.getText());
                                                    int leaseDuration = Integer.parseInt(leaseDurationArea.getText());
                                                    int hallID = currentHall.getHallID();
                                                    int roomNumber = currentRoom.getRoomNumber();
                                                    leaseList.add(new StudentLease(leaseNumber, leaseDuration, hallID,
                                                            roomNumber, studentID));
                                                    break;
                                                } else { //Append existing lease
                                                    for (StudentLease currentLease : leaseList) {
                                                        if (leaseNumberArea.getText().equals(Integer.toString(currentLease.getLeaseNumber()))) {
                                                            currentLease.setLeaseNumber(Integer.parseInt(leaseNumberArea.getText()));
                                                            currentLease.setLeaseDuration(Integer.parseInt(leaseDurationArea.getText()));
                                                            currentLease.setStudentID(studentID);
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    //Change data in Table Class
                                    currentTable.setLeaseNumber(
                                            leaseNumberArea.getText());
                                    currentTable.setLeaseDuration(
                                            leaseDurationArea.getText());
                                    currentTable.setOccupancy(
                                            occupancyCombo.getValue().toString());
                                    currentTable.setStudentName(studentNameCombo
                                            .getValue().toString());
                                    table.refresh();

                                } catch (NumberFormatException eNum) {
                                    errorText.setTextAlignment(TextAlignment.CENTER);
                                    dialogVbox.getChildren().add(errorText);
                                    dialogVbox.setAlignment(Pos.CENTER);
                                    errorDialog.show();
                                }
                                break;
                            }
                        }
                        try {
                            saveData();
                        } catch (IOException ex) {
                            Logger.getLogger(UweAccomodationSystem.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

                Button changeBtn = new Button();
                changeBtn.setText("Update Cleaning Status");
                changeBtn.setPrefSize(100, 20);
                changeBtn.setStyle("-fx-font-size: 1em; ");
                changeBtn.setLayoutX(200);
                changeBtn.setLayoutY(470);

                //Change Button handling  
                changeBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        for (Iterator it = tableList.iterator(); it.hasNext();) {
                            Table currentTable = (Table) it.next();
                            if (currentTable == table.getSelectionModel()
                                    .getSelectedItem()) {
                                //Create popup used for error display
                                Stage errorDialog = new Stage();
                                errorDialog.initModality(Modality.APPLICATION_MODAL);
                                errorDialog.initOwner(primaryStage);
                                errorDialog.setTitle("ERROR");
                                VBox dialogVbox = new VBox(20);
                                Scene dialogScene = new Scene(dialogVbox, 300, 50);
                                errorDialog.setScene(dialogScene);
                                Text errorText = new Text();
                                errorText.setFont(Font.font("System", FontWeight.BOLD, 13));

                                //Update clean status
                                try {

                                    if (leaseNumberArea.getText().equals("N/A")) {
                                    } else {
                                        if (cleanStatusCombo.getValue().toString().equals("Offline")) {
                                            throw new NoSuchMethodException();
                                        }
                                    }
                                    //Change clean status in Room class                                               
                                    int roomNumber = currentTable.getRoomNumber();
                                    int hallID = 0;
                                    for (Hall currentHall : hallList) {
                                        if (currentTable.getHallName().equals(currentHall.getHallName())) {
                                            hallID = currentHall.getHallID();
                                        }
                                    }
                                    for (Room currentRoom : roomList) {
                                        if ((currentRoom.getHallID() == hallID) && (currentRoom.getRoomNumber() == roomNumber)) {
                                            currentRoom.setCleanStatus(cleanStatusCombo.getValue().toString());
                                            break;
                                        }
                                    }
                                    //Save to file
                                    try {
                                        saveData();
                                    } catch (IOException ex) {
                                        Logger.getLogger(UweAccomodationSystem.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    //Change clean status in Table Class
                                    currentTable.setCleanStatus(cleanStatusCombo.getValue().toString());
                                    table.refresh();

                                } catch (NoSuchMethodException offline) {
                                    errorText.setText("An occupied room cannot\nbe set to Offline.");
                                    errorText.setTextAlignment(TextAlignment.CENTER);
                                    dialogVbox.getChildren().add(errorText);
                                    dialogVbox.setAlignment(Pos.CENTER);
                                    errorDialog.show();
                                }
                            }
                        }
                    }
                });
                //Create pane and add objects 
                Pane root = new Pane();
                root.getChildren().add(backBtn);
                root.getChildren().add(delBtn);
                root.getChildren().add(updateBtn);
                root.getChildren().add(changeBtn);
                root.getChildren().addAll(tableVbox);
                root.getChildren().addAll(titleLabel3, hallNameLabel, //Labels
                        roomNumberLabel,
                        occupancyLabel,
                        leaseNumberLabel,
                        cleanStatusLabel,
                        studentNameLabel
                );
                root.getChildren().addAll(hallNameArea, //Text areas & Combos
                        roomNumberArea,
                        occupancyCombo,
                        leaseNumberArea,
                        cleanStatusCombo,
                        studentNameCombo
                );

                //Set scene dimensions and title
                Scene scene = new Scene(root, 680, 505);
                allStage.setTitle("UWE Bristol Accomodation System- All Operations Interface");

                //Set scene to stage and show
                allStage.setScene(scene);
                allStage.show();
            }

        });
        AnchorPane root = new AnchorPane();

        root.getChildren().add(logo);
        root.getChildren().add(btn);
        root.getChildren().add(btn2);
        root.getChildren().add(btn3);

        root.getChildren().add(label1);
        root.getChildren().add(label2);

        Scene scene = new Scene(root, 430, 200);

        primaryStage.setTitle("UWE Bristol Accomodation System");
        primaryStage.setMinHeight(200.0);
        primaryStage.setMinWidth(450.0);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void getFileData() throws IOException {
        //String url = "C:\\Users\\Brice\\Documents\\NetBeansProjects";
      //String url = this.getClass().getResource("").getPath();
      //String hallsFile =url + "\\halls.csv" ;
      Path currentPath = Paths.get("");
      String hallsFile = currentPath.toAbsolutePath().toString()+ "\\src\\resources\\halls.csv";
       //File hallsFile = new File("C:\\Users\\Brice\\Documents\\NetBeansProjects\\UweAccomodationSystem\\src\\resources\\halls.csv");
        String roomsFile = currentPath.toAbsolutePath().toString()+"\\src\\resources\\rooms.csv";
        String studentsFile =currentPath.toAbsolutePath().toString()+ "\\src\\resources\\students.csv";
        String leasesFile = currentPath.toAbsolutePath().toString()+ "\\src\\resources\\student_leases.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        //Read Halls file data
        try {
            br = new BufferedReader(new FileReader(hallsFile));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                int hallID = Integer.parseInt(data[1]);
                hallList.add(new Hall(data[0], hallID));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR --  halls File not found--");
            // System.exit(1);
        }
        //Read Rooms file data
        try {
            br = new BufferedReader(new FileReader(roomsFile));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                int roomNumber = Integer.parseInt(data[0]);
                int hallID = Integer.parseInt(data[1]);
                roomList.add(new Room(roomNumber, hallID,
                        data[2], data[3]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR --  Rooms File not found--");
            // System.exit(1);
        }

        //Read Students file data
        try {
            br = new BufferedReader(new FileReader(studentsFile));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                int studentID = Integer.parseInt(data[1]);
                studentList.add(new Student(data[0], studentID));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR -- Student File not found--");
            //System.exit(1);
        }

        //Read Student leases file data
        try {
            br = new BufferedReader(new FileReader(leasesFile));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                int leaseNumber = Integer.parseInt(data[0]);
                int leaseDuration = Integer.parseInt(data[1]);
                int hallID = Integer.parseInt(data[2]);
                int roomNumber = Integer.parseInt(data[3]);
                int studentID = Integer.parseInt(data[4]);
                leaseList.add(new StudentLease(leaseNumber, leaseDuration, hallID,
                        roomNumber, studentID));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR -- Student lease File not found--");
            //System.exit(1);
        }
    }

    public void getTableData() {
        //Variables
        String hallName, occupancy, cleanStatus, leaseNumber = "N/A",
                studentName = "N/A", leaseDuration = "N/A";
        int hallNumber, roomNumber;

        //Clear table before loading data
        tableList.clear();

        //Load data into table class & list
        for (Room room : roomList) {
            for (Hall hall : hallList) {
                //Find the hall that the room is in
                if (hall.getHallID() == room.getHallID()) {
                    hallName = hall.getHallName();
                    hallNumber = hall.getHallID();
                    roomNumber = room.getRoomNumber();
                    occupancy = room.getOccupancy();
                    cleanStatus = room.getCleanStatus();
                    //Find a lease that may exist for this room
                    for (StudentLease lease : leaseList) {
                        if ((lease.getRoomNumber()
                                == room.getRoomNumber())
                                && (lease.getHallID()
                                == hall.getHallID())) {
                            leaseNumber
                                    = Integer.toString(lease.getLeaseNumber());
                            leaseDuration
                                    = Integer.toString(lease.getLeaseDuration());
                            //Find the student associated with the lease
                            for (Student student : studentList) {
                                if (student.getID()
                                        == lease.getStudentID()) {
                                    studentName = student.getName();
                                    break;
                                }
                            }
                            break;
                        } else {
                            leaseNumber = "N/A";
                            leaseDuration = "N/A";
                            studentName = "N/A";
                        }
                    }
                    tableList.add(new Table(hallName, hallNumber, roomNumber, leaseDuration, occupancy,
                            cleanStatus, leaseNumber,
                            studentName));
                    break;
                }
            }
        }

    }

    public void saveData() throws IOException {
       Path currentPath = Paths.get("");
      String hallsFile = currentPath.toAbsolutePath().toString()+ "\\src\\resources\\halls.csv";
        //String url = "C:\\Users\\Brice\\Documents\\NetBeansProjects";
        //String hallsFile = url +"\\Uwe Accomodation System\\src\\resources\\halls.csv";
        String roomsFile = currentPath.toAbsolutePath().toString()+ "\\src\\resources\\rooms.csv";
        String studentsFile = currentPath.toAbsolutePath().toString()+ "\\src\\resources\\students.csv";
        String leasesFile = currentPath.toAbsolutePath().toString()+ "\\src\\resources\\student_leases.csv";
        FileWriter hallWriter = new FileWriter(hallsFile);
        FileWriter roomWriter = new FileWriter(roomsFile);
        FileWriter studentWriter = new FileWriter(studentsFile);
        FileWriter leaseWriter = new FileWriter(leasesFile);

        //Save HALLS data       
        for (Hall hall : hallList) {

            List<String> hallData = new ArrayList<>();
            hallData.add(hall.getHallName());
            hallData.add(Integer.toString(hall.getHallID()));

            //Write to file
            String hallLine = hallData.stream().collect(Collectors.joining(","));
            hallWriter.write(hallLine);
            hallWriter.write(System.getProperty("line.separator"));
        }
        hallWriter.close();

        //Save ROOMS data
        for (Room room : roomList) {

            List<String> roomData = new ArrayList<>();
            roomData.add(Integer.toString(room.getRoomNumber()));
            roomData.add(Integer.toString(room.getHallID()));
            roomData.add(room.getCleanStatus());
            roomData.add(room.getOccupancy());

            //Write to file
            String roomLine = roomData.stream().collect(Collectors.joining(","));
            roomWriter.write(roomLine);
            roomWriter.write(System.getProperty("line.separator"));
        }
        roomWriter.close();

        //Save STUDENTS data
        for (Student student : studentList) {

            List<String> studentData = new ArrayList<>();
            studentData.add(student.getName());
            studentData.add(Integer.toString(student.getID()));

            //Write to file
            String studentLine = studentData.stream().collect(Collectors.joining(","));
            studentWriter.write(studentLine);
            studentWriter.write(System.getProperty("line.separator"));
        }
        studentWriter.close();

        //Save LEASES data
        for (StudentLease lease : leaseList) {

            List<String> leaseData = new ArrayList<>();
            leaseData.add(Integer.toString(lease.getLeaseNumber()));
            leaseData.add(Integer.toString(lease.getLeaseDuration()));
            leaseData.add(Integer.toString(lease.getHallID()));
            leaseData.add(Integer.toString(lease.getRoomNumber()));
            leaseData.add(Integer.toString(lease.getStudentID()));

            //Write to file
            String leaseLine = leaseData.stream().collect(Collectors.joining(","));
            leaseWriter.write(leaseLine);
            leaseWriter.write(System.getProperty("line.separator"));
        }
        leaseWriter.close();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

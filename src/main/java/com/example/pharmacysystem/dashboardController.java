package com.example.pharmacysystem;
//Timestamp 1:53:00 - 1:54:23
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;


public class dashboardController implements Initializable {

        @FXML
        private Button addMed_btn;

        @FXML
        private Button addMedicines_addBtn;

        @FXML
        private TextField addMedicines_brand;

        @FXML
        private Button addMedicines_clearBtn;

        @FXML
        private TableView<medicineData> addMedicines_tableView;

        @FXML
        private TableColumn<medicineData,String> addMedicines_col_brand;

        @FXML
        private TableColumn<medicineData,String> addMedicines_col_date;

        @FXML
        private TableColumn<medicineData,String> addMedicines_col_medicineID;

        @FXML
        private TableColumn<medicineData,String> addMedicines_col_price;

        @FXML
        private TableColumn<medicineData,String> addMedicines_col_productName;

        @FXML
        private TableColumn<medicineData,String> addMedicines_col_status;

        @FXML
        private TableColumn<medicineData,String> addMedicines_col_type;


        @FXML
        private AnchorPane addMedicines_form;

        @FXML
        private ImageView addMedicines_imageView;

        @FXML
        private Button addMedicines_importBtn;

        @FXML
        private TextField addMedicines_medicineID;

        @FXML
        private TextField addMedicines_price;

        @FXML
        private TextField addMedicines_productName;

        @FXML
        private TextField addMedicines_search;


        @FXML
        private ComboBox<?> addMedicines_status;

        @FXML
        private ComboBox<?> addMedicines_type;

        @FXML
        private Button addMedicines_updateBtn;

        @FXML
        private Button close;

        @FXML
        private Label dashboard_availiableMed;

        @FXML
        private Button dashboard_btn;

        @FXML
        private AreaChart<?, ?> dashboard_chart;

        @FXML
        private Label dashboard_totalCustomer;

        @FXML
        private Label dashboard_totalIncome;

        @FXML
        private Button logout;

        @FXML
        private AnchorPane main_form;
         @FXML
         private AnchorPane dashboard_form;

        @FXML
        private Button minimize;

        @FXML
        private Button purchase_addBtn;

        @FXML
        private TextField purchase_amount;

        @FXML
        private Label purchase_balance;

        @FXML
        private ComboBox<?> purchase_brand;

        @FXML
        private Button purchase_btn;

        @FXML
        private TableColumn<?, ?> purchase_col_brand;

        @FXML
        private TableColumn<?, ?> purchase_col_medicineid;

        @FXML
        private TableColumn<?, ?> purchase_col_price;

        @FXML
        private TableColumn<?, ?> purchase_col_productName;

        @FXML
        private TableColumn<?, ?> purchase_col_qty;

        @FXML
        private TableColumn<?, ?> purchase_col_type;

        @FXML
        private AnchorPane purchase_form;

        @FXML
        private ComboBox<?> purchase_medicineID;

        @FXML
        private Button purchase_payBtn;

        @FXML
        private ComboBox<?> purchase_productName;

        @FXML
        private TableView<?> purchase_tableView;

        @FXML
        private Label purchase_total;

        @FXML
        private ComboBox<?> purchase_type;

        @FXML
        private Label username;


        //DATABASE HELPERS
        private Connection connect;
        private PreparedStatement prepare;
        private Statement statement;
        private ResultSet result;
        public ObservableList<medicineData> addMedicinesListData(){
                String sql = "SELECT * FROM medicine ";
                ObservableList<medicineData> listData = FXCollections.observableArrayList();

                connect = database.connectDatabase();
                try{
                        prepare = connect.prepareStatement(sql);
                        result = prepare.executeQuery();

                        medicineData medData;

                        while (result.next()){
                                medData = new medicineData(result.getInt("medicine_id"),
                                                           result.getString("brand"),
                                                           result.getString("productName"),
                                                           result.getString("type"),
                                                           result.getString("status"),
                                                           result.getDouble("price"),
                                                           result.getDate("date"));
                                listData.add(medData);
                        }
                }catch (Exception e){e.printStackTrace();}
                return listData;
        }

        private ObservableList<medicineData> addMedicineList;
        public void addMedicineShowListData(){
                addMedicineList = addMedicinesListData();

                addMedicines_col_medicineID.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
                addMedicines_col_brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
                addMedicines_col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
                addMedicines_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
                addMedicines_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
                addMedicines_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
                addMedicines_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

                addMedicines_tableView.setItems(addMedicineList);

        }
        public void addMedicineSelect(){ 
                try {
                        medicineData medData = addMedicines_tableView.getSelectionModel().getSelectedItem();
                        int num = addMedicines_tableView.getSelectionModel().getSelectedIndex();

                        if ((num -1) < 1){return;}
                        addMedicines_medicineID.setText(String.valueOf(medData.getMedicineId()));
                } catch (Exception e) {
                        throw new RuntimeException(e);
                }
        }

        public void switchForm(ActionEvent event){
                if (event.getSource() == dashboard_btn){
                        main_form.setVisible(true);
                        addMedicines_form.setVisible(false);
                        purchase_form.setVisible(false);


                } else if (event.getSource() == addMed_btn){
                        main_form.setVisible(false);
                        addMedicines_form.setVisible(true);
                        purchase_form.setVisible(false);

                        addMedicineShowListData();


                }else if (event.getSource() == purchase_btn) {
                        main_form.setVisible(false);
                        addMedicines_form.setVisible(false);
                        purchase_form.setVisible(true);
                }
        }
        public void displayUsername(){
                String user = getData.username;
                username.setText(user.substring(0,1).toUpperCase()+user.substring(1));
        }
        public void logout(){
                try {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Do you wish to logout?");
                        Optional<ButtonType> option = alert.showAndWait();

                        if (option.get().equals(ButtonType.OK)) {
                                logout.getScene().getWindow().hide();
                                Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                                Stage stage = new Stage();
                                Scene scene = new Scene(root);

                                stage.initStyle(StageStyle.TRANSPARENT);
                                stage.setScene(scene);
                                stage.show();
                        }
                }catch (Exception e){e.printStackTrace();}
        }
        public void minimize(){
                Stage stage = (Stage) main_form.getScene().getWindow();
                stage.setIconified(true);
        }
        public void close(){
                System.exit(0);
        }

        @Override
        public void initialize(URL location, ResourceBundle resources){
                displayUsername();
                addMedicineShowListData();
        }

}

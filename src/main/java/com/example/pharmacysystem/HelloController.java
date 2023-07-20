package com.example.pharmacysystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.StackPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HelloController {
    @FXML
    private Button closeBtn;

    @FXML
    private ImageView image;

    @FXML
    private ImageView logo;

    @FXML
    private Button signInBtn;

    @FXML
    private TextField staffId;

    @FXML
    private PasswordField staffPassword;

    @FXML
    private StackPane window;

    public void closeApplication(){
        System.exit(0);
    }

    //Database part
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void loginStaff(){
        String sql = "SELECT * FROM admin WHERE username = ? and password = ? ";
        connect = database.connectDatabase();
        try{
            prepare = connect.prepareStatement(sql);
            prepare.setString(1,staffId.getText());
            prepare.setString(2,staffPassword.getText());

            result = prepare.executeQuery();
            Alert alert;

            if (staffId.getText().isEmpty() || staffPassword.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Credentials Error");
                alert.setHeaderText(null);
                alert.setContentText("You needs to fill in the blanks");
                alert.showAndWait();
            }
            else{
                if (result.next()){
                    getData.username = staffId.getText();
                    //When logins are correct
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Info");
                    alert.setHeaderText(null);
                    alert.setContentText("Login Successful!");
                    alert.showAndWait();
                    //Chale you for specify the file else errors :-)
                    //This links to the dashboard section
                    signInBtn.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();

                }
                else{
                    //When logins are wrong
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Username or Password is wrong");
                    alert.showAndWait();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
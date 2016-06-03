/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.ModelClass;
import Service.ServiceClass;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class Update extends Application{
    VBox menuRoot;
    HBox headingRoot;
    HBox decisionRoot;
    Group root;
    Label changeEmailLabel;
    Label changeMobileLabel;
    TextField changeEmailField;
    TextField changeMobileField;
    Button cancelButton;
    Button confirmButton;
    Scene scene;
    String Id;
    ModelClass model;
    public Update(ModelClass model){
    this.Id = model.getId();
    }     
    public Update(){
    }  
    
    public void start(Stage stage){
        changeEmailLabel = new Label ("Email");
        
        changeMobileLabel = new Label ("Mobile Number");
        
		cancelButton = new Button("Cancel");
        confirmButton = new Button("Confirm");
        
	decisionRoot = new HBox (5,cancelButton,confirmButton);
//''''''''''''''''''''''''''''''''''''
        ModelClass model = new ModelClass();
        ServiceClass service = new ServiceClass();
        try{
            //step1 load the driver class  
            Class.forName("oracle.jdbc.driver.OracleDriver");  
            //step2 create  the connection object  s
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
            //step3 create the statement object  sss
            Statement stmt=con.createStatement();
            ResultSet  rs = stmt.executeQuery(service.getById(Id));
            rs.next();
            changeEmailField = new TextField (rs.getString(4));
            changeMobileField = new TextField (rs.getString(5));
			
             //Confirm Action
            confirmButton.setOnAction((ActionEvent e) -> {
                service.update (Id,changeEmailField.getText (), changeMobileField.getText ());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Update Successful");
            alert.showAndWait().ifPresent(response -> {
             if (response == ButtonType.OK) {
                 try {
                     if(rs.getString(3).equals("Admin")){
                         AdminPage adminPage = new AdminPage ();
                         adminPage.start (stage);
                     }
                     else{
                         MemberPage memberPage = new MemberPage();
                         memberPage.start(stage);
                     }
                 } catch (SQLException ex) {
                     Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
            });
        });
            cancelButton.setOnAction((ActionEvent e) -> {
            
          
                try {
                    if(rs.getString(3).equals("Admin")){
                        AdminPage adminPage = new AdminPage ();
                        adminPage.start (stage);
                    }
                    else{
                        MemberPage memberPage = new MemberPage();
                        memberPage.start(stage);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
                }
             
            });
        
                }
        catch(Exception ex){
                    ex.printStackTrace();
                    }
        
        menuRoot = new VBox(5, changeEmailLabel, changeEmailField, changeMobileLabel, changeMobileField, decisionRoot);
        menuRoot.setPadding(new Insets(20,0,0,20));
        root = new Group();
		root.getChildren ().addAll (menuRoot);
        scene = new Scene(root,300,250);
        //stage = new Stage();
        stage.setScene(scene);
        //Scene scene = new Scene(root, root.getScene().getWindow());
        stage.setTitle ("Update");
        stage.show();
        
        
    }
}

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
import java.sql.Statement;
import java.util.Optional;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;


public class ChangePassword extends Application{

    ModelClass model = new ModelClass();
    String Id;
    public ChangePassword(ModelClass model){
    this.Id = model.getId();
    }     
    public ChangePassword(){
    }
    @Override
    public void start (Stage stage){
       // Create the custom dialog.
Dialog<Pair<String, String>> dialog = new Dialog<>();
dialog.setTitle("Login Dialog");
dialog.setHeaderText("Look, a Custom Login Dialog");

// Set the icon (must be included in the project).
dialog.setGraphic(new ImageView(this.getClass().getResource("images1.png").toString()));

// Set the button types.
ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
GridPane grid = new GridPane();
grid.setHgap(10);
grid.setVgap(10);
grid.setPadding(new Insets(20, 150, 10, 10));

PasswordField changePasswordField = new PasswordField();
changePasswordField.setPromptText("Change Password");
PasswordField confirmPasswordField = new PasswordField();
confirmPasswordField.setPromptText("Confirm Password");

grid.add(new Label("Change Password:"), 0, 0);
grid.add(changePasswordField, 1, 0);
grid.add(new Label("Confirm Password:"), 0, 1);
grid.add(confirmPasswordField, 1, 1);

// Enable/Disable login button depending on whether a username was entered.
Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
changePasswordField.textProperty().addListener((observable, oldValue, newValue) -> {
    loginButton.setDisable(newValue.trim().isEmpty());
});

dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
//sPlatform.runLater(() -> username.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
dialog.setResultConverter(dialogButton -> {
    if (dialogButton == loginButtonType) {
        ResultSet rs;
        
                ServiceClass service = new ServiceClass();
                ModelClass model = new ModelClass();
				SignIn signin = new SignIn ();
                try{
                    //step1 load the driver class  
                    Class.forName("oracle.jdbc.driver.OracleDriver");  
                    //step2 create  the connection object  s
                    Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
                    //step3 create the statement object  sss
                    Statement stmt=con.createStatement();
                    rs = stmt.executeQuery(service.getById(Id));
                    rs.next();
                    if(changePasswordField.getText().equals(confirmPasswordField.getText())) {
//                        if(rs.getString(3).equals("Admin")){
//                                service.changePassword(model.getId (),changePasswordField.getText());
//				System.out.println (model.getMemberType ());
//                                AdminPage adminPage = new AdminPage ();
//                                adminPage.start (stage);
//                        }
//                        else{
//                            service.changePassword(model.getId (),changePasswordField.getText());
//                            System.out.println (model.getMemberType ());
//                            MemberPage memberPage = new MemberPage ();
//                            memberPage.start (stage);
//                        }
                        service.changePassword(model.getId (),changePasswordField.getText());
                        HomePage homePage = new HomePage();
                        homePage.start(stage);
                    }
                    else {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText("INVALID Password");
                    //alert.setContentText("Your Id : "+ model.getId ()+ "\nYour Password : " + getRandomPassword());
                    alert.showAndWait().ifPresent(response -> {
                             if (response == ButtonType.OK) {
//                                    ChangePassword changePassword = new ChangePassword();
//                                    changePassword.start (stage);
                             }
                    });
                }
                }
                catch(Exception ex){
                            ex.printStackTrace();
                            }
                
               
        return new Pair<>(changePasswordField.getText(), confirmPasswordField.getText());
        
    }
    else {
//        HomePage homePage = new HomePage(model);
//        homePage.start(stage);
                
    }
    return null;
});

Optional<Pair<String, String>> result = dialog.showAndWait();

//result.ifPresent(usernamePassword -> {
//    System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
//});
    }
        
}

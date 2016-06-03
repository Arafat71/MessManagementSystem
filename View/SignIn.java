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


public class SignIn extends Application{

    ModelClass model = new ModelClass();
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

TextField username = new TextField();
username.setPromptText("Username");
PasswordField password = new PasswordField();
password.setPromptText("Password");

grid.add(new Label("Username:"), 0, 0);
grid.add(username, 1, 0);
grid.add(new Label("Password:"), 0, 1);
grid.add(password, 1, 1);

// Enable/Disable login button depending on whether a username was entered.
Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
username.textProperty().addListener((observable, oldValue, newValue) -> {
    loginButton.setDisable(newValue.trim().isEmpty());
});

dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
//sPlatform.runLater(() -> username.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
dialog.setResultConverter(dialogButton -> {
    if (dialogButton == loginButtonType) {
        System.out.println("User=" + username.getText() + ", Pass=" + password.getText());
         ServiceClass service = new ServiceClass();				
            String id =username.getText();
            model.setId(id);	
			
            String Password = password.getText();
            try{
                //step1 load the driver class  
                Class.forName("oracle.jdbc.driver.OracleDriver");  
                //step2 create  the connection object  s
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
                //step3 create the statement object  sss
                Statement stmt=con.createStatement();
                ResultSet  rs = stmt.executeQuery(service.getById(id));
                if(rs.next()==true){
                        if(rs.getString(2).equals(Password)){
                                if(rs.getString(3).equals("Admin")){

                                        AdminPage adminPage = new AdminPage (model);
                                        adminPage.start (stage);
                                }
                                else if(rs.getString(3).equals("Member")){
                                        MemberPage memberPage = new MemberPage();
                                        memberPage.start(stage);
                                }
                        }

                        else{
                                Alert alert = new Alert(AlertType.WARNING);
                                alert.setTitle("Information Dialog");
                                alert.setHeaderText("INVALID Password");
                                //alert.setContentText("Your Id : "+ model.getId ()+ "\nYour Password : " + getRandomPassword());
                                alert.showAndWait().ifPresent(response -> {
                                         if (response == ButtonType.OK) {
                                                HomePage homePage = new HomePage ();
                                                homePage.start (stage);
                                         }
                                });
                        }
                }
                else{
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText("INVALID USER");
                    //alert.setContentText("Your Id : "+ model.getId ()+ "\nYour Password : " + getRandomPassword());
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            HomePage homePage = new HomePage ();
                            homePage.start (stage);
                        }
                    });
                }
                    
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        return new Pair<>(username.getText(), password.getText());
        
    }
    else {
        HomePage homePage = new HomePage(model);
        homePage.start(stage);
                
    }
    return null;
});

Optional<Pair<String, String>> result = dialog.showAndWait();

//result.ifPresent(usernamePassword -> {
//    System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
//});
    }
        
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Model.ModelClass;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Optional;
import java.util.Random;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

public class SignUp extends Application {
    LocalDateStringConverter converter;
    Random passwordRand;
    int randPassword; 
	
	public void setRandomPassword (){
		passwordRand = new Random ();
		randPassword = passwordRand.nextInt (10000000)+1;
		
	}
	
	public int getRandomPassword (){
		return randPassword;
	}
    @Override
    public void start(Stage stage) {
       Dialog dialog = new Dialog();
       dialog.setTitle("Sign Up");
       dialog.setHeaderText("Sign Up Carefully and Correctly");
       ButtonType signupButtonType = new ButtonType("Sign Up", ButtonData.OK_DONE);
       dialog.getDialogPane().getButtonTypes().addAll(signupButtonType, ButtonType.CANCEL);
       GridPane grid = new GridPane();
       grid.setHgap(5);
       grid.setVgap(5);
       grid.setPadding(new Insets(20,120,10,10));
       
        Label firstNameLabel = new Label ("First Name");
        Label lastNameLabel = new Label ("Last Name");
        Label mobileLabel = new Label ("Mobile Number");
        Label emailLabel = new Label ("Email");
        Label dateOfBirthLabel = new Label ("Date Of Birth");
        Label bloodGroupLabel = new Label ("Blood Group");
        Label joinDateLabel = new Label ("Join Date");
        
        //Text Field
        TextField firstNameTextField = new TextField ();
        firstNameTextField.setPromptText("First Name");
        TextField lastNameTextField = new TextField ();
        lastNameTextField.setPromptText("Last Name");
        TextField mobileTextField = new TextField ();
        mobileTextField.setPromptText("Mobile");
        TextField emailTextField = new TextField ();
        emailTextField.setPromptText("Email");
        DatePicker dateOfBirthPicker = new DatePicker ();
        dateOfBirthPicker.setPromptText("Date of Birth");
        //bloodGroupTextField = new TextField ();
        DatePicker joinDatePicker = new DatePicker ();
        joinDatePicker.setPromptText("Join Date");
        ChoiceBox bloodGroupChoiceBox = new ChoiceBox();
        bloodGroupChoiceBox.setItems(FXCollections.observableArrayList(
        "O+", "O- ", 
        new Separator(),
        "B+", "B-",
        new Separator(),
        "A+", "A-",
        new Separator(),
        "AB+", "AB-"
        )
    );
    
    grid.add(firstNameLabel,0,0);
    grid.add(firstNameTextField,1,0);
    
    grid.add(lastNameLabel,0,1);
    grid.add(lastNameTextField,1,1);
    
    grid.add(mobileLabel,0,2);
    grid.add(mobileTextField,1,2);
    
    grid.add(emailLabel,0,3);
    grid.add(emailTextField,1,3);
    
    grid.add(dateOfBirthLabel,0,4);
    grid.add(dateOfBirthPicker,1,4);
    
    grid.add(joinDateLabel,0,5);
    grid.add(joinDatePicker,1,5);
    
    grid.add(bloodGroupLabel,0,6);
    grid.add(bloodGroupChoiceBox,1,6);
    
    Node signupButton = dialog.getDialogPane().lookupButton(signupButtonType);
    signupButton.setDisable(true);
    bloodGroupChoiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue)->{
        signupButton.setDisable(false);
    });
    dialog.getDialogPane().setContent(grid);
    converter = new LocalDateStringConverter();
    dialog.setResultConverter(dialogButton->{
         if (dialogButton == signupButtonType) {
        //StringConverter sc = new NumberStringConverter();		
            ModelClass model = new ModelClass();
			//String blood = bloodGroupChoiceBox.setConverter(sc);
			//
            model.setJoinDate(converter.toString(joinDatePicker.getValue()));
			model.setBloodGroup ((String)bloodGroupChoiceBox.getValue()) ;
            if(model.getInstance()!=null){
				setRandomPassword ();
                model.setFirstName(firstNameTextField.getText());
                model.setLastName(lastNameTextField.getText());
                model.setMobile(mobileTextField.getText());
                model.setEmail(emailTextField.getText());
                model.setDateOfBirth(converter.toString(dateOfBirthPicker.getValue()));
                try{
                        String sql=" ";
                        sql+="INSERT INTO ";
                        sql+="Member(ID,FirstName,LastName,MOBILENO,EMAIL,PASSWORD,DATEOFBIRTH,JoinDate,BLOODGROUP,MEMBERType) ";
                        sql+="values('"+model.getId()+"','"+model.getFirstName()+"','"+model.getLastName()+"','"+model.getMobile()+"','"+model.getEmail()+"',"
                             + "'"+Integer.toString(getRandomPassword())+"','"+model.getDateOfBirth()+"','"+model.getJoinDate()+"','"+model.getBloodGroup()+"',"
                             + "'"+model.getMemberType()+"')";
       
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//step2 create  the connection object  s
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
			//step3 create the statement object  
			Statement stmt=con.createStatement();
                        int r = stmt.executeUpdate(sql);
                        System.out.println(r);
                    }
                catch(Exception ex){
                            ex.printStackTrace();
                            }
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText("Congratulation");
					alert.setContentText("Your Id : "+ model.getId ()+ "\nYour Password : " + getRandomPassword());
				

					alert.showAndWait().ifPresent(response -> {
					 if (response == ButtonType.OK) {
						 HomePage homePage = new HomePage ();
						 homePage.start (stage);
					 }
					});
            }
			else if (model.getInstance()==null){
					
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText("Contact With Admin");
					

					alert.showAndWait().ifPresent(response -> {
					 if (response == ButtonType.OK) {
						 HomePage homePage = new HomePage ();
						 homePage.start (stage);
					 }
					});
			}
            //return "firstNameTextField.getText(), lastNameTextField.getText(),mobileTextField.getText())";
         }
          else {
             ModelClass model = new ModelClass();
        HomePage homePage = new HomePage(model);
        homePage.start(stage);
                
    }
             System.out.println(firstNameTextField.getText()+" "+lastNameTextField.getText()+" "+mobileTextField.getText()+" "+emailTextField.getText()+" "
                     +converter.toString(dateOfBirthPicker.getValue())+" "+(String)bloodGroupChoiceBox.getValue()
             +converter.toString(joinDatePicker.getValue()));
         
			
        return null;
    });
    Optional result = dialog.showAndWait();
    //Optional<Pair<String, String>> result = dialog.showAndWait();
    }
}

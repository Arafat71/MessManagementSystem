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

public class AddMemberScene extends Application {
    LocalDateStringConverter converter;
    Random passwordRand;
    int randPassword; 
    String Id;
   public AddMemberScene(ModelClass model){
        this.Id = model.getId();
        System.out.println(Id);
    }
    public AddMemberScene(){
    }
    
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
       dialog.setTitle("Add Member");
       dialog.setHeaderText("Add Member Carefully and Correctly");
       ButtonType signupButtonType = new ButtonType("Add Member", ButtonData.OK_DONE);
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
            ServiceClass service = new ServiceClass();
			setRandomPassword ();
            try{
                    String sql = "SELECT COUNT(1) FROM MEmber WHERE MEMberTYpe = 'Member'";
                    Class.forName("oracle.jdbc.driver.OracleDriver");  
			//step2 create  the connection object  s
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
			//step3 create the statement object  
			Statement stmt=con.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        rs.next();
                        System.out.println(rs.getInt(1));
            model = new ModelClass(rs.getInt(1),firstNameTextField.getText(), lastNameTextField.getText(), mobileTextField.getText(), emailTextField.getText(),
                    converter.toString(dateOfBirthPicker.getValue()),converter.toString(joinDatePicker.getValue()),(String)bloodGroupChoiceBox.getValue()
            );
			//service.addMember(model);
			setRandomPassword ();
			
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//step2 create  the connection object  s
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
			//step3 create the statement object  
			stmt=con.createStatement();
                        int r1 = stmt.executeUpdate(service.addMember(model,getRandomPassword ()));
                        System.out.println(r1);
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
                                ModelClass model1 = new ModelClass();
				 MemberDetails memberDetails = new MemberDetails (model1);
				 memberDetails.start (stage);
			 }
			});
            //return "firstNameTextField.getText(), lastNameTextField.getText(),mobileTextField.getText())";
         }
          else {
             ModelClass model = new ModelClass();
        AdminPage adminPage = new AdminPage(model);
        adminPage.start(stage);
                
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

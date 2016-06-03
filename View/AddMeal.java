/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.ModelAccount;
import Model.ModelClass;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.converter.NumberStringConverter;


public class AddMeal extends Application{
	
	
    VBox menuRoot;
    HBox headingRoot;
    HBox scnery;
    VBox root;
    VBox labelRoot;
    VBox fieldRoot;
    HBox addMealRoot;
    HBox button;
    
    Button addMealButton;
    Button mealHistoryButton;
    Button bazarButton;
    Button bazarHistoryButton;
	Button bazarExpenseButton;
    Button rentButton;
    Button changePasswordButton;
	Button bazarHistoryButtonButton;
    Button updateButton;
    Button signOutButton;
    Label headingLabel;
    String Id;
    //Add Meal Element
    //DatePicker addMealTime;
    Label addMealTimeLabel;
    Label breakfastLabel;
    Label launchLabel;
    Label dinnerLabel;
    
    //TextField addMealTimeTextField;
    TextField breakfastTextField;
    TextField launchTextField;
    TextField dinnerTextField;
	ModelClass model = new ModelClass();
    
    Button confirmButton;
    Button cancelButton;
    Scene scene;
    NumberStringConverter NSC; 
    public AddMeal(ModelClass model){
    this.Id = model.getId();
    }   

	public AddMeal(){
    }  	
    
	
    public void start(Stage stage){
        
       addMealButton = new Button("Add Meal");
        addMealButton.setStyle("-fx-font: 15 arial; -fx-base: #b6e7c9;");
        mealHistoryButton = new Button("Meal History");
        mealHistoryButton.setStyle("-fx-font: 15 arial; -fx-base: #b6e7c9;");
        bazarExpenseButton = new Button("Bazar Expense");
        bazarExpenseButton.setStyle("-fx-font: 15 arial; -fx-base: #b6e7c9;");
	bazarHistoryButton = new Button("Bazar History");
        bazarHistoryButton.setStyle("-fx-font: 15 arial; -fx-base: #b6e7c9;");
        rentButton = new Button("Rent");
        rentButton.setStyle("-fx-font: 15 arial; -fx-base: #b6e7c9;");
	changePasswordButton =new Button ("Change Password");
        changePasswordButton.setStyle("-fx-font: 15 arial; -fx-base: #b6e7c9;");
        updateButton = new Button("Update");
        updateButton.setStyle("-fx-font: 15 arial; -fx-base: #b6e7c9;");
        signOutButton = new Button("Sign Out");
        signOutButton.setStyle("-fx-font: 15 arial; -fx-base: #b6e7c9;");
	headingLabel = new Label ("Member");
	confirmButton = new Button("Confirm");
        confirmButton.setStyle("-fx-font: 15 arial; -fx-base: #b6e7c9;");
	cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-font: 15 arial; -fx-base: #b6e7c9;");
        NSC = new NumberStringConverter();
        //Add Meal Element
        addMealTimeLabel = new Label("Date"); 
        DatePicker addMealTime = new DatePicker(LocalDate.now());
		//addMealTimeTextField = new 
		breakfastLabel = new Label("Breakfast");
		breakfastTextField = new TextField();
		launchLabel = new Label("Launch");
		launchTextField = new TextField();
		dinnerLabel = new Label("Dinner");
		dinnerTextField = new TextField();
		
        //''''''''''''''''''''''''''''''''	
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(20, 0, 20, 20));

        addMealButton.setMaxWidth(Double.MAX_VALUE);
        mealHistoryButton.setMaxWidth(Double.MAX_VALUE);
        bazarExpenseButton.setMaxWidth(Double.MAX_VALUE);
        bazarHistoryButton.setMaxWidth(Double.MAX_VALUE);
        rentButton.setMaxWidth(Double.MAX_VALUE);
        changePasswordButton.setMaxWidth (Double.MAX_VALUE);
		updateButton.setMaxWidth(Double.MAX_VALUE);
        signOutButton.setMaxWidth(Double.MAX_VALUE);
        
//''''''''''''''''''''''''''''''''''''
        
			//Home Action
        signOutButton.setOnAction((ActionEvent e) -> {
            HomePage homePage = new HomePage();
            homePage.start(stage);
        });
	
        //Rent Action
        rentButton.setOnAction((ActionEvent e) -> {
            MemberRentHistory memberRentHistory = new MemberRentHistory(model);
            memberRentHistory.start(stage);
        });
		//Meal History Action
        mealHistoryButton.setOnAction((ActionEvent e) -> {
            MealHistory mealHistory = new MealHistory(model);
            mealHistory.start(stage);
        });
		
		//Bazar Expense Action
        bazarExpenseButton.setOnAction((ActionEvent e) -> {
            BazarExpense bazarExpense = new BazarExpense(model);
            bazarExpense.start(stage);
        });
		
		//Bazar History Action
        bazarHistoryButton.setOnAction((ActionEvent e) -> {
			//model = new Model ();
            BazarHistory bazarHistory = new BazarHistory(model);
            bazarHistory.start(stage);
        });
		
		
		//Add meal Action
        addMealButton.setOnAction((ActionEvent e) -> {
            AddMeal addMeal = new AddMeal(model);
            addMeal.start(stage);
        });
		
		//Change Password Action
        changePasswordButton.setOnAction((ActionEvent e) -> {
            ChangePassword changePassword = new ChangePassword(model);
            changePassword.start(stage);
        });
	
        //Update Action
	updateButton.setOnAction((ActionEvent e) -> {
            Update update = new Update(model);
            update.start(stage);
        });
		
		
		//Confirm Action
	confirmButton.setOnAction((ActionEvent e) -> {
            		
            ModelAccount modelAccount = new ModelAccount();
            //ModelClass model = new ModelClass();
			//String blood = bloodGroupChoiceBox.setConverter(sc);
			//
                modelAccount.setBreakFast(Integer.parseInt(breakfastTextField.getText()));
                modelAccount.setLaunch(Integer.parseInt(launchTextField.getText()));
                modelAccount.setDinner(Integer.parseInt(dinnerTextField.getText()));
                
                Date date = Date.from(addMealTime.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date sqlDate = new Date(date.getTime());
               // modelAccount.setDDate(sqlDate);
		//System.out.println(modelAccount.getBreakFast()+" "+modelAccount.getLaunch()+"','"+modelAccount.getDinner()+"','"+model.getId()+" "+modelAccount.getDDate());
                try{
                        String sql=" ";
                        sql+="INSERT INTO ";
                        sql+="Account(Breakfast, Launch, Dinner, Id, DDate, Account_ID) ";
                        sql+="values('"+modelAccount.getBreakFast()+"','"+modelAccount.getLaunch()+"','"+modelAccount.getDinner()+"','"+model.getId()+"',+TO_DATE('"+addMealTime.getValue()+"', 'YYYY-MM-DD'),seq_person.nextval)";
       
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
					//alert.setContentText("Your Id : "+ model.getId ()+ "\nYour Password : " + getRandomPassword());
				

					alert.showAndWait().ifPresent(response -> {
					 if (response == ButtonType.OK) {
						 MealHistory mealHistory = new MealHistory (model);
						 mealHistory.start (stage);
					 }
					});
            
			
			
			
        });
		
		//cancel Action
	cancelButton.setOnAction((ActionEvent e) -> {
			
            MemberPage memberPage = new MemberPage();
            memberPage.start(stage);
        });
		
        
        
	//scnery = new HBox(5);	
		labelRoot = new VBox(20, addMealTimeLabel, breakfastLabel, launchLabel, dinnerLabel);
		labelRoot.setPadding(new Insets(80,0,0,70));
		fieldRoot = new VBox(10, addMealTime, breakfastTextField, launchTextField, dinnerTextField);
		fieldRoot.setPadding(new Insets(80,0,0,50));
		menuRoot = new VBox(5, addMealButton, mealHistoryButton, bazarExpenseButton, bazarHistoryButton, rentButton,changePasswordButton,
		updateButton,signOutButton);
        menuRoot.setPadding(new Insets(5,0,0,10));
		addMealRoot = new HBox(10, menuRoot,labelRoot,fieldRoot);
		addMealRoot.setPadding(new Insets(50,0,0,30));
        
		headingLabel = new Label("Member");
		headingLabel.setStyle("-fx-font: 18 arial ;");
        headingRoot = new HBox(5,headingLabel);
        headingRoot.setPadding(new Insets(20,0,0,375));
		button = new HBox(5,cancelButton, confirmButton);
		button.setPadding(new Insets(0,0,0,405));
        root = new VBox(headingRoot,addMealRoot, button);
        root.setStyle("-fx-background-color: BURLYWOOD ;");
        scene = new Scene(root,750,650,Color.CYAN);
        //stage = new Stage();
        stage.setScene(scene);
        //Scene scene = new Scene(root, root.getScene().getWindow());
        stage.setTitle ("Add Meal");
        stage.show();
        
        
    }
}

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
import java.util.logging.Level;
import java.util.logging.Logger;
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


public class BazarExpense extends Application{
	String Id;
	ModelClass model;
	
	public BazarExpense(ModelClass model){
            this.Id = model.getId();
            System.out.println(model.getId());
    }  
	
	public BazarExpense(){
    }
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
    Button bazarExpenseButton;
    Button bazarHistoryButton;
    Button rentButton;
    Button changePasswordButton;
    Button updateButton;
    Button signOutButton;
    Label headingLabel;
    //ModelClass model;
    //Add Expense Element
    DatePicker addExpenseTime;
    Label addExpenseTimeLabel;
    Label bazarExpenseLabel;
    
    //TextField addMealTimeTextField;
    TextField bazarExpenseTextField;
 
    Button confirmButton;
    Button cancelButton;
    Scene scene;
    NumberStringConverter NSC; 
        
    
    public void start(Stage stage){
        
        addMealButton = new Button("Add Meal");
        mealHistoryButton = new Button("Meal History");
        bazarExpenseButton = new Button("Bazar Expense");
		bazarHistoryButton = new Button("Bazar History");
        rentButton = new Button("Rent");
	changePasswordButton =new Button ("Change Password");
        updateButton = new Button("Update");
        signOutButton = new Button("Sign Out");
	headingLabel = new Label ("Member");
	confirmButton = new Button("Confirm");
	cancelButton = new Button("Cancel");
        NSC = new NumberStringConverter();
        //Add Meal Element
        addExpenseTimeLabel = new Label("Date"); 
        addExpenseTime = new DatePicker(LocalDate.now());
		//addMealTimeTextField = new 
		bazarExpenseLabel = new Label("Expense");
		bazarExpenseTextField = new TextField();
		
		
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
			model = new ModelClass ();
            HomePage homePage = new HomePage(model);
            homePage.start(stage);
        });
	
        //Rent Action
        rentButton.setOnAction((ActionEvent e) -> {
			model = new ModelClass ();
            MemberRentHistory memberRentHistory = new MemberRentHistory(model);
            memberRentHistory.start(stage);
        });
		//Meal History Action
        mealHistoryButton.setOnAction((ActionEvent e) -> {
			model = new ModelClass ();
            MealHistory mealHistory = new MealHistory(model);
            mealHistory.start(stage);
        });
		
		//Bazar Expense Action
        bazarExpenseButton.setOnAction((ActionEvent e) -> {
			model = new ModelClass ();
            BazarExpense bazarExpense = new BazarExpense(model);
            bazarExpense.start(stage);
        });
		
		//Bazar History Action
        bazarHistoryButton.setOnAction((ActionEvent e) -> {
			model = new ModelClass ();
            BazarHistory bazarHistory = new BazarHistory(model);
            bazarHistory.start(stage);
        });
		
		
		//Add meal Action
        addMealButton.setOnAction((ActionEvent e) -> {
			model = new ModelClass();
            AddMeal addMeal = new AddMeal(model);
            addMeal.start(stage);
        });
		
		//Change Password Action
        changePasswordButton.setOnAction((ActionEvent e) -> {
			model = new ModelClass();
            ChangePassword changePassword = new ChangePassword(model);
            changePassword.start(stage);
        });
	
        //Update Action
	updateButton.setOnAction((ActionEvent e) -> {
                model = new ModelClass();
                Update update = new Update(model);
                update.start(stage);
            
        });
		//Confirm Action
	confirmButton.setOnAction((ActionEvent e) -> {
            		
            ModelAccount modelAccount = new ModelAccount();
            //ModelClass model = new ModelClass();
			//String blood = bloodGroupChoiceBox.setConverter(sc);
			//
                modelAccount.setBazarExpense(Double.parseDouble(bazarExpenseTextField.getText()));
                java.util.Date date = java.util.Date.from(addExpenseTime.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                java.util.Date sqlDate = new java.util.Date(date.getTime());
                modelAccount.setDDate(sqlDate);
		//System.out.println(modelAccount.getBreakFast()+" "+modelAccount.getLaunch()+"','"+modelAccount.getDinner()+"','"+model.getId()+"','"+sqlDate);
                try{
                        String sql=" ";
                        sql+="INSERT INTO ";
                        sql+="BALANCE(Id, BDate, BazarExpense) ";
                        sql+="values('"+modelAccount.getId()+"',+TO_DATE('"+addExpenseTime.getValue()+"', 'YYYY-MM-DD'),'"+modelAccount.getBazarExpense()+"')";
       
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
                                                  model = new ModelClass();
						 BazarHistory bazarHistory = new BazarHistory (model);
						 bazarHistory.start (stage);
					 }
					});
            
			
			
			
        });
		
		//cancel Action
	cancelButton.setOnAction((ActionEvent e) -> {
			
            MemberPage memberPage = new MemberPage();
            memberPage.start(stage);
        });
		
        
        
	//scnery = new HBox(5);	
		labelRoot = new VBox(20, addExpenseTimeLabel, bazarExpenseLabel);
		labelRoot.setPadding(new Insets(80,0,0,70));
		fieldRoot = new VBox(10, addExpenseTime, bazarExpenseTextField);
		fieldRoot.setPadding(new Insets(80,0,0,50));
		menuRoot = new VBox(5, addMealButton, mealHistoryButton, bazarExpenseButton, bazarHistoryButton, rentButton,changePasswordButton,
		updateButton,signOutButton);
        menuRoot.setPadding(new Insets(0,0,0,20));
		addMealRoot = new HBox(10, menuRoot,labelRoot,fieldRoot);
		addMealRoot.setPadding(new Insets(50,0,0,30));
        
		headingLabel = new Label("Member");
        headingRoot = new HBox(5,headingLabel);
        headingRoot.setPadding(new Insets(20,0,0,375));
		button = new HBox(5,cancelButton, confirmButton);
		button.setPadding(new Insets(0,0,0,405));
        root = new VBox(headingRoot,addMealRoot, button);
        scene = new Scene(root,750,650,Color.CYAN);
        //stage = new Stage();
        stage.setScene(scene);
        //Scene scene = new Scene(root, root.getScene().getWindow());
        stage.setTitle ("Bazar Expense");
        stage.show();
        
        
    }
}
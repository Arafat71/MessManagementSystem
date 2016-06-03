/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.ModelAccount;
import Model.ModelClass;
import Service.ServiceClass;
import java.util.Date;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TableColumn;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.text.Text;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class MealHistory extends Application{
    VBox menuRoot;
	VBox tableRoot;
	VBox monthRoot;
    HBox headingRoot;
    HBox scnery;
    Group root;
	String Id;
    
    Button addMealButton;
    Button mealHistoryButton;
    Button bazarButton;
	Button bazarExpenseButton;
    Button bazarHistoryButton;
    Button rentButton;
    Button changePasswordButton;
    Button updateButton;
    Button signOutButton;
    Label headingLabel;
    TableView mealHistoryTable;
    TableView totalMealHistoryTable;
    Scene scene;
	ModelClass model;
	ModelAccount modelAccount;
	Text monthText;
	
	public MealHistory(ModelClass model){
    this.Id = model.getId();
    }  
	
	public MealHistory(){
    } 
	Calendar now = Calendar.getInstance();
        
    
    public void start(Stage stage){
        
        addMealButton = new Button("Add Meal");
        mealHistoryButton = new Button("Meal History");
        bazarExpenseButton = new Button("Bazar");
		bazarHistoryButton = new Button("Bazar History");
        rentButton = new Button("Rent");
		changePasswordButton =new Button ("Change Password");
        updateButton = new Button("Update");
        signOutButton = new Button("Sign Out");
		headingLabel = new Label ("Member");
		
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
		
		
		//Member Table 
        mealHistoryTable = new TableView();
        
        //creating Column for showing Meal Info
        TableColumn dateColumn = new TableColumn("Date");
        TableColumn breakFastColumn = new TableColumn("BreakFast");
        TableColumn launchColumn = new TableColumn("Launch");
        TableColumn dinnerColumn = new TableColumn("Dinner");
   
        //Mapping columns
        dateColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Date>("DDate"));
        breakFastColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Integer>("breakFast"));
        launchColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Integer>("launch"));
        dinnerColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Integer>("dinner"));
  
       
		//Adding the Columns
		mealHistoryTable.getColumns().addAll(dateColumn,breakFastColumn, launchColumn, dinnerColumn);
        
	 
        try{
            ServiceClass service = new ServiceClass();
			modelAccount = new ModelAccount ();
            // Setting contactList to table as a observable list
            mealHistoryTable.setItems(FXCollections.observableArrayList(service.getMealInfo(modelAccount.getId ())));
        }
        catch(Exception ex){
            
        }
		
		
		
		
		//Member Table 
        totalMealHistoryTable = new TableView();
        
        //creating Column for showing Total Meal Info
        TableColumn totalMealColumn = new TableColumn("Total Meal");
        TableColumn totalMealChargeColumn = new TableColumn("Meal Charge");
        TableColumn totalMealExpenseColumn = new TableColumn("Meal Expense");
        TableColumn getColumn = new TableColumn("Get");
        TableColumn payColumn = new TableColumn("Pay");
        TableColumn mealRateColumn = new TableColumn("Meal Rate");
        
        

     
        //Mapping columns
        totalMealColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Integer>("totalMeal"));
        totalMealChargeColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Double>("mealCharge"));
        totalMealExpenseColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Double>("mealExpense"));
        getColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Double>("get"));
        payColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Double>("pay"));
        mealRateColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Double>("mealRate"));
        
		
		//Adding the Columns
		totalMealHistoryTable.getColumns().addAll(totalMealColumn,totalMealChargeColumn,totalMealExpenseColumn,getColumn,payColumn,mealRateColumn);
        
	 
        try{
            ServiceClass service = new ServiceClass();
		modelAccount = new ModelAccount ();
            // Setting contactList to table as a observable list
            totalMealHistoryTable.setItems(FXCollections.observableArrayList(service.getTotalMealInfo(modelAccount.getId ())));
        }
        catch(Exception ex){
            
        }
		
		
		String[] monthName = { "January", "February", "March", "April", "May", "June", "July",
                                "August", "September", "October", "November", "December" };
        Calendar cal = Calendar.getInstance();
        String month = monthName[cal.get(Calendar.MONTH)];
        System.out.println("Month name: " + month);
        monthText = new Text("\t\t"+month+"\t"+cal.get(Calendar.YEAR));
        monthRoot = new VBox (5,monthText);
        
	//scnery = new HBox(5);	
        menuRoot = new VBox(5, addMealButton, mealHistoryButton, bazarExpenseButton, bazarHistoryButton, rentButton,changePasswordButton,
                               updateButton,signOutButton);
		menuRoot.setPadding (new Insets (110,10,10,10));
		headingLabel = new Label("Member");
		headingLabel.setPadding (new Insets (25,0,0,375));
		monthRoot.setPadding (new Insets (0,0,0,90));
		tableRoot = new VBox(10,mealHistoryTable,monthRoot,totalMealHistoryTable);
        tableRoot.setPadding(new Insets(80,10,0,50));
        tableRoot.setPrefWidth(500);
        tableRoot.setPrefHeight(400);
        headingRoot = new HBox(5,menuRoot,tableRoot);
        headingRoot.setPadding(new Insets(0,0,0,10));
	
        root = new Group();
		root.getChildren ().addAll (headingLabel,headingRoot);
        scene = new Scene(root,800,650);
        //stage = new Stage();
        stage.setScene(scene);
        //Scene scene = new Scene(root, root.getScene().getWindow());
        stage.setTitle ("MealHistory");
        stage.show();
        
        
    }
}


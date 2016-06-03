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
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;



public class BazarHistory extends Application{
    VBox menuRoot;
	VBox tableRoot;
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
    TableView bazarHistoryTable;
    Scene scene;
	ModelClass model;
	ModelAccount modelAccount;
	
	public BazarHistory(ModelClass model){
    this.Id = model.getId();
    }  
    public BazarHistory(){
    }   
    
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
        bazarHistoryTable = new TableView();
        
        //creating Column
        //TableColumn dateColumn = new TableColumn("Date");
        TableColumn depositColumn = new TableColumn("Deposit");
        TableColumn expenseColumn = new TableColumn("Expense");
        TableColumn balanceColumn = new TableColumn("Balance");
        
        

     
        //Mapping columns
        //dateColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Date>("dDate"));
        depositColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Double>("bazarCharge"));
        expenseColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Double>("bazarExpense"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Double>("total"));
        
        
		
		//Adding the Columns
		bazarHistoryTable.getColumns().addAll(depositColumn, expenseColumn, balanceColumn);
        
	 
        try{
            ServiceClass service = new ServiceClass();
			modelAccount = new ModelAccount ();
            // Setting contactList to table as a observable list
            bazarHistoryTable.setItems(FXCollections.observableArrayList(service.getBazarHistoryInfo(modelAccount.getId ())));
        }
        catch(Exception ex){
            
        }
		
		
		
        
        
	//scnery = new HBox(5);	
        menuRoot = new VBox(5, addMealButton, mealHistoryButton, bazarExpenseButton, bazarHistoryButton, rentButton,changePasswordButton,
                               updateButton,signOutButton);
    menuRoot.setPadding (new Insets (110,10,10,10));
	headingLabel = new Label("Member");
	headingLabel.setPadding (new Insets (25,0,0,390));
	
	tableRoot = new VBox(5,bazarHistoryTable);
        tableRoot.setPadding(new Insets(80,10,0,20));
        tableRoot.setPrefWidth(600);
        headingRoot = new HBox(5,menuRoot,tableRoot);
        headingRoot.setPadding(new Insets(0,0,0,10));
	
        root = new Group();
	root.getChildren ().addAll (headingLabel,headingRoot);
        scene = new Scene(root,800,650);
        //stage = new Stage();
        stage.setScene(scene);
        //Scene scene = new Scene(root, root.getScene().getWindow());
        stage.setTitle ("Bazar History");
        stage.show();
        
        
    }
}


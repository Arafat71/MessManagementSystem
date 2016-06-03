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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class MemberRentHistory extends Application{
    VBox menuRoot;
    VBox tableRoot;
    HBox headingRoot;
    HBox scnery;
    Group root;
    String Id;
    
    Button addMealButton;
    Button mealHistoryButton;
    Button bazarExpenseButton;
    Button bazarHistoryButton;
    Button rentButton;
    Button changePasswordButton;
    Button updateButton;
    Button signOutButton;
    Label headingLabel;
    TableView memberRentTable;
    Scene scene;
	
    public MemberRentHistory(ModelClass model){
    this.Id = model.getId();
    } 
    public MemberRentHistory(){
   
    } 
        
    
    public void start(Stage stage){
        ModelClass model = new ModelClass();
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
            HomePage homePage = new HomePage();
            homePage.start(stage);
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
		
		//Add Meal Action
		addMealButton.setOnAction ((ActionEvent e)->{
			AddMeal addMeal = new AddMeal (model);
			addMeal.start (stage);
		});
		
		
		//Meal History Action
        mealHistoryButton.setOnAction((ActionEvent e) -> {
            MealHistory mealHistory = new MealHistory(model);
            mealHistory.start(stage);
        });
		
		//Member Table 
        memberRentTable = new TableView();
        
        //creating Column
        TableColumn dateColumn = new TableColumn("Date");
        TableColumn nameColumn = new TableColumn("Name");
        TableColumn houseRentColumn = new TableColumn("House Rent");
        TableColumn gasBillColumn = new TableColumn("Gas Bill");
        TableColumn electricityBillColumn = new TableColumn("Electricity Bill");
        TableColumn internetBillColumn = new TableColumn("Internet Bill");
        TableColumn maidBillColumn = new TableColumn("Maid Bill");
        TableColumn additionalColumn = new TableColumn("Additional");
        TableColumn totalColumn = new TableColumn("Total");
        

     
        //Mapping columns
        dateColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Date>("DDate"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, String>("firstName"));
        houseRentColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Double>("houseRent"));
        gasBillColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Double>("gasBill"));
        electricityBillColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Double>("electricityBill"));
        internetBillColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Double>("internetBill"));
        maidBillColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Double>("maidBill"));
        additionalColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Double>("additional"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Double>("total"));
        
		
		//Adding the Columns
		memberRentTable.getColumns().addAll(dateColumn,nameColumn,houseRentColumn, gasBillColumn, electricityBillColumn,
		internetBillColumn, maidBillColumn, additionalColumn,totalColumn);
        
	 
        try{
            ServiceClass service = new ServiceClass();
            // Setting contactList to table as a observable list
            memberRentTable.setItems(FXCollections.observableArrayList(service.getMemberRentInfo(model.getId ())));
        }
        catch(Exception ex){
            
        }
		
		
		
        
        
	//scnery = new HBox(5);	
        menuRoot = new VBox(5, addMealButton, mealHistoryButton, bazarExpenseButton, bazarHistoryButton, rentButton,changePasswordButton,
                               updateButton,signOutButton);
    menuRoot.setPadding (new Insets (110,10,10,10));
	headingLabel = new Label("Member");
	headingLabel.setPadding (new Insets (25,0,0,390));
	
	tableRoot = new VBox(5,memberRentTable);
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
        stage.setTitle ("Member Rent History");
        stage.show();
        
        
    }
}


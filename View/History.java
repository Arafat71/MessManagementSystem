/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.ModelAccount;
import Model.ModelClass;
import Service.ServiceClass;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;
import java.util.Calendar;
import javafx.scene.text.Text;

public class History extends Application{
    VBox menuRoot;
    VBox headingRoot;
    VBox tableRoot;
    VBox sep;
    HBox scnery;
    HBox deleteRoot;
    VBox mealChargeRoot;
    VBox bazarChargeRoot;
    VBox chargeRoot;
    VBox root;
    TableView mealChargeTable;
    TableView bazarChargeTable;
    Button addMemberButton;
    Button memberDetailsButton;
    Button addRentButton;
    Button rentHistoryButton;
    Button mealChargeButton;
    Button bazarChargeButton;
    Button historyButton;
    Button editMealButton;
    Button changePasswordButton;
    Button updateButton;
    Button mealRateButton;
    Button deleteButton;
    Text headingLabel;
    String Id;
    Scene scene;
    HBox signRoot;
    Button signOutButton;
    ModelClass model;
	TextField idTextField;
	TextField nameTextField;
	Label idLabel;
	Label nameLabel;
	Text monthText;
	Label mealChargeLabel ;
	Label bazarChargeLabel;
	
		public History(ModelClass model){
		this.Id = model.getId();
    }  
	
	public History(){
		
    }  
   
    Calendar now = Calendar.getInstance();
    public void start(Stage stage){
        //AdminPage ob = AdminPage(ModelClass model);
        addMemberButton = new Button("Add Member");
        memberDetailsButton = new Button("Member Details");
        addRentButton = new Button("Add Rent");
        rentHistoryButton = new Button("Rent History");
        mealChargeButton = new Button("Meal Charge");
        bazarChargeButton = new Button("Bazar Charge");
        historyButton = new Button("History");
		changePasswordButton = new Button ("Change Password");
        updateButton = new Button("Update");
        mealRateButton = new Button("Meal Rate");
		signOutButton = new Button("Sign Out");
		deleteButton = new Button("Delete");
		
		idTextField = new TextField();
		nameTextField = new TextField ();
		idLabel = new Label ("ID");
		nameLabel = new Label ("Name");
		mealChargeLabel = new Label ("Meal Charge");
		bazarChargeLabel = new Label ("Bazar Charge");
		
		
	//''''''''''''''''''''''''''''''''	
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(20, 0, 20, 20));
	System.out.println (Id);
        addMemberButton.setMaxWidth(Double.MAX_VALUE);
        memberDetailsButton.setMaxWidth(Double.MAX_VALUE);
        addRentButton.setMaxWidth(Double.MAX_VALUE);
        rentHistoryButton.setMaxWidth(Double.MAX_VALUE);
        mealChargeButton.setMaxWidth(Double.MAX_VALUE);
        bazarChargeButton.setMaxWidth(Double.MAX_VALUE);
        historyButton.setMaxWidth(Double.MAX_VALUE);

        changePasswordButton.setMaxWidth (Double.MAX_VALUE);
		updateButton.setMaxWidth(Double.MAX_VALUE);
        mealRateButton.setMaxWidth(Double.MAX_VALUE);
        signOutButton.setMaxWidth(Double.MAX_VALUE);
//''''''''''''''''''''''''''''''''''''
       
        //add member action
        addMemberButton.setOnAction((ActionEvent e) -> {
            model = new ModelClass();
            AddMemberScene addMemberScene = new AddMemberScene(model);
            addMemberScene.start(stage);
        });
        
        //member Details Action
        memberDetailsButton.setOnAction((ActionEvent e) -> {
            MemberDetails memberDetails = new MemberDetails();
            memberDetails.start(stage);
        });
        
        //Add Rent Action
        addRentButton.setOnAction((ActionEvent e) -> {
            ModelClass model = new ModelClass();
            AddRent addRentPage = new AddRent(model);
            addRentPage.start(stage);
        });
		
		//Rent History Action
        rentHistoryButton.setOnAction((ActionEvent e) -> {
            RentHistory rentHistoryPage = new RentHistory();
            rentHistoryPage.start(stage);
        });
		
		//Meal Charge Action
        mealChargeButton.setOnAction((ActionEvent e) -> {
			model = new ModelClass();
            MealCharge mealCharge = new MealCharge(model);
            mealCharge.start(stage);
        });
		
		//Bazar Charge Action
        bazarChargeButton.setOnAction((ActionEvent e) -> {
			model = new ModelClass();
            BazarCharge bazarCharge = new BazarCharge(model);
            bazarCharge.start(stage);
        });
		
		//History Action
        historyButton.setOnAction((ActionEvent e) -> {
			model = new ModelClass();
            History history = new History(model);
            history.start(stage);
        });
		
		
		//Update Action
        updateButton.setOnAction((ActionEvent e) -> {
                ModelClass model = new ModelClass ();
                Update update = new Update(model);
                update.start(stage);
            
        });
		
        
        //Change Password Action
        changePasswordButton.setOnAction((ActionEvent e) -> {
            ModelClass model = new ModelClass();
            ChangePassword changePassword = new ChangePassword(model);
            changePassword.start(stage);
        });
		
	//Meal Rate Action
        mealRateButton.setOnAction((ActionEvent e) -> {
            ModelClass model = new ModelClass();
            MealRate mealRate = new MealRate(model);
            try {
                mealRate.start(stage);
            } catch (SQLException ex) {
                Logger.getLogger(MealRate.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
		
		//Sign out Action
        signOutButton.setOnAction((ActionEvent e) -> {
			model = new ModelClass ();
            HomePage homePage = new HomePage(model);
            homePage.start(stage);
        });
	
       
        //Member Table 
        mealChargeTable = new TableView();
        bazarChargeTable = new TableView();
        
        //creating Column Meal Charge
        TableColumn nameColumn = new TableColumn("NAME");
        TableColumn depositColumn = new TableColumn("Deposit");
        
        //Mapping columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<ModelClass, String>("firstName"));
        depositColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Double>("mealCharge"));
        
		//Adding the Columns
		mealChargeTable.getColumns().addAll(nameColumn,depositColumn);
        
	 
        try{
            ServiceClass service = new ServiceClass();
            // Setting contactList to table as a observable list
            mealChargeTable.setItems(FXCollections.observableArrayList(service.getMealChargeHistory()));
        }
        
        catch(Exception ex){
            
        }
		
		
		
		
		//creating Column for Bazar Charge
        TableColumn bazarNameColumn = new TableColumn("NAME");
        depositColumn = new TableColumn("Deposit");
        TableColumn expenseColumn = new TableColumn("Expense");
        
        //Mapping columns
        bazarNameColumn.setCellValueFactory(new PropertyValueFactory<ModelClass, String>("firstName"));
        depositColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Double>("bazarCharge"));
        expenseColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Double>("bazarExpense"));
        
		//Adding the Columns
		bazarChargeTable.getColumns().addAll(bazarNameColumn,depositColumn,expenseColumn);
        
	 
        try{
            ServiceClass service = new ServiceClass();
           //Setting contactList to table as a observable list
            bazarChargeTable.setItems(FXCollections.observableArrayList(service.getBazarChargeHistory()));
        }
        catch(Exception ex){
            
        }
		
		
		// meaTable.getSelectionModel().selectedItemProperty().addListener(
              // new ChangeListener<ModelClass>() {
                // @Override
                // public void changed(ObservableValue observable, ModelClass oldValue, ModelClass newValue) {
                    // idTextField.setText(newValue.getTempId());
                    // nameTextField.setText(newValue.getFirstName());
                    //mobileTextField.setText(newValue.getMobile());
                // }
        // }
        // );
		
		
		
		String[] monthName = { "January", "February", "March", "April", "May", "June", "July",
                                "August", "September", "October", "November", "December" };
        Calendar cal = Calendar.getInstance();
        String month = monthName[cal.get(Calendar.MONTH)];
        System.out.println("Month name: " + month);
        monthText = new Text("\t\t"+month+"\t"+cal.get(Calendar.YEAR));
				
		menuRoot = new VBox(5, addMemberButton, memberDetailsButton, addRentButton, rentHistoryButton, mealChargeButton, bazarChargeButton,
							historyButton, changePasswordButton, updateButton, mealRateButton, signOutButton);
        menuRoot.setPadding(new Insets(50,0,0,20));
		
		mealChargeLabel.setPadding (new Insets (0,0,0,90));
		bazarChargeLabel.setPadding (new Insets (0,0,0,90));
		mealChargeRoot = new VBox (10,mealChargeLabel,mealChargeTable);
		bazarChargeRoot = new VBox (10,bazarChargeLabel,bazarChargeTable);
		chargeRoot = new VBox (40,mealChargeRoot,bazarChargeRoot);
		chargeRoot.setPadding (new Insets (30,0,0,100));
        tableRoot = new VBox(5,chargeRoot);
        tableRoot.setPadding(new Insets(20,20,0,20));
        tableRoot.setPrefWidth(400);
		tableRoot.setPrefHeight(400);
		Separator topSeparator = new Separator();
		topSeparator.setPadding(new Insets(10, 0, 0, 0));
        sep = new VBox(topSeparator);
        headingRoot = new VBox(5,monthText);
        headingRoot.setPadding(new Insets(30,0,0,270));
		headingRoot.setStyle("-fx-font: 17 arial ;");
        mealChargeLabel.setStyle("-fx-font: 15 arial ;");
        bazarChargeLabel.setStyle("-fx-font: 15 arial ;");
        scnery = new HBox(5,menuRoot, tableRoot);
		
		
        root = new VBox(headingRoot,scnery);
		root.setStyle("-fx-background-color: #336699;");
       // root.setStyle(Color.AQUAMARINE);
        scene = new Scene(root,840,650,Color.CYAN);
        //stage = new Stage();
	
        stage.setScene(scene);
        stage.setResizable(false);
        //Scene scene = new Scene(root, root.getScene().getWindow());
        stage.setTitle ("History");
        stage.show();
        
        
    }
}

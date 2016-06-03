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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RentHistory extends Application{
    VBox menuRoot;
    VBox headingRoot;
    VBox tableRoot;
    VBox sep;
    HBox scnery;
	HBox deleteRoot;
    VBox root;
    TableView memberTable;
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
    Label headingLabel;
    String Id;
    Scene scene;
    HBox signRoot;
    Button signOutButton;
    ModelClass model;
    public RentHistory(ModelClass model){
    this.Id = model.getId();
    }     
    public RentHistory(){
    }
   
    
    public void start(Stage stage){
        //AdminPage ob = AdminPage(ModelClass model);
        addMemberButton = new Button("Add Member");
        memberDetailsButton = new Button("Member Details");
        addRentButton = new Button("Add Rent");
        rentHistoryButton = new Button("Rent History");
        mealChargeButton = new Button("Meal Charge");
        bazarChargeButton = new Button("Bazar Charge");
        historyButton = new Button("History");
        editMealButton = new Button("Edit Meal");
		changePasswordButton = new Button ("Change Password");
        updateButton = new Button("Update");
        mealRateButton = new Button("Meal Rate");
		signOutButton = new Button("Sign Out");
		deleteButton = new Button("Delete");
		
		
		
		
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
        editMealButton.setMaxWidth(Double.MAX_VALUE);
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
        
        //member Detail Action
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
	
		
        //Delete Action
        deleteButton.setOnAction(e->{            
            ServiceClass service = new ServiceClass();
            service.delete(((ModelClass)memberTable.getSelectionModel().getSelectedItem()).getTempId());
            memberTable.setItems(FXCollections.observableArrayList(service.getAll()));
        });
        //Member Table 
        memberTable = new TableView();
        
        //creating Column
        TableColumn dateColumn = new TableColumn("Date");
        TableColumn nameColumn = new TableColumn("NAME");
        TableColumn houseRentColumn = new TableColumn("House Rent");
        TableColumn gasBillColumn = new TableColumn("Gas Bill");
        TableColumn electricityBillColumn = new TableColumn("Electricity Bill");
        TableColumn internetBillColumn = new TableColumn("Internet Bill");
        TableColumn maidBillColumn = new TableColumn("Maid Bill");
        TableColumn additionalColumn = new TableColumn("Additional");
        TableColumn totalColumn = new TableColumn("Total");
        
        //TableColumn deleteColumn = new TableColumn("DELETE");
        
        //Mapping columns
	dateColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Date>("DDate"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<ModelClass, String>("firstName"));
        houseRentColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Double>("houseRent"));
        gasBillColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Double>("gasBill"));
        electricityBillColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Double>("electricityBill"));
        internetBillColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Double>("internetBill"));
        maidBillColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Double>("maidBill"));
        additionalColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Double>("additional"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Double>("total"));
        
		//Adding the Columns
		memberTable.getColumns().addAll(dateColumn,nameColumn,houseRentColumn, gasBillColumn, electricityBillColumn,
		internetBillColumn, maidBillColumn, additionalColumn,totalColumn);
        
	 
        try{
            ServiceClass service = new ServiceClass();
            // Setting contactList to table as a observable list
            memberTable.setItems(FXCollections.observableArrayList(service.getRentInfo()));
        }
        catch(Exception ex){
            
        }
		/*
		memberTable.getSelectionModel().selectedItemProperty().addListener(
              new ChangeListener<ModelClass>() {
                @Override
                public void changed(ObservableValue observable, ModelClass oldValue, ModelClass newValue) {
                    //idTextField.setText(newValue.getTempId());
                    nameTextField.setText(newValue.getFirstName());
                    //mobileTextField.setText(newValue.getMobile());
                }
        }
        );
		*/
		
		
				
		menuRoot = new VBox(5, addMemberButton, memberDetailsButton, addRentButton, rentHistoryButton, mealChargeButton, bazarChargeButton,
							historyButton, editMealButton, changePasswordButton, updateButton, mealRateButton, signOutButton);
        menuRoot.setPadding(new Insets(60,0,0,20));
		headingLabel = new Label("Admin");	
		headingLabel.setStyle("-fx-font: 18 arial ;");		
        tableRoot = new VBox(5,memberTable);
        tableRoot.setPadding(new Insets(30,20,0,20));
        tableRoot.setPrefWidth(700);
		Separator topSeparator = new Separator();
		topSeparator.setPadding(new Insets(10, 0, 0, 0));
        sep = new VBox(topSeparator);
        headingRoot = new VBox(5,headingLabel);
        headingRoot.setPadding(new Insets(20,0,0,420));
        
        scnery = new HBox(5,menuRoot, tableRoot);
		
		
        root = new VBox(headingRoot,scnery);
		root.setStyle("-fx-background-color: #336699;");
       // root.setStyle(Color.AQUAMARINE);
        scene = new Scene(root,840,650,Color.CYAN);
        //stage = new Stage();
	
        stage.setScene(scene);
        //Scene scene = new Scene(root, root.getScene().getWindow());
        stage.setTitle ("Rent History");
        stage.show();
        
        
    }
}

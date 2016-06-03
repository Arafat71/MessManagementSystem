/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.ModelAccount;
import Model.ModelClass;
import Service.ServiceClass;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Separator;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BazarCharge extends Application{
    VBox menuRoot;
    VBox headingRoot;
	VBox itemRoot;
    HBox addRentRoot;
	HBox rentButtonRoot;
	HBox idRoot;
	HBox depositRoot;
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
	 //Add Rent Element
    Label idBazarChargeLabel;
    ChoiceBox idBazarChargeChoiceBox;
    Label depositLabel;
    TextField depositTextField;
    
    // Label totalLabel;
    // TextField totalTextField;
    Button  confirmBazarChargeButton;
    Button cancelBazarChargeButton;
	 
    //ModelClass model;
    public BazarCharge(ModelClass model){
    this.Id = model.getId();
    }     
    public BazarCharge(){
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
		
		  //Add Rent Element
        idBazarChargeLabel = new Label("ID");
        idBazarChargeChoiceBox = new ChoiceBox();
        ServiceClass service = new ServiceClass();
        idBazarChargeChoiceBox.setItems(FXCollections.observableArrayList(service.getAllId()));
        depositLabel = new Label("Deposit");
        depositTextField = new TextField();
		
        
        confirmBazarChargeButton = new Button("Confirm");
        cancelBazarChargeButton = new Button("Cancel");
        DatePicker addBazarTime = new DatePicker(LocalDate.now());
		
		
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
	
	
       
        //Confirm Bazar Charge Action
        confirmBazarChargeButton.setOnAction((ActionEvent e) -> {
            ModelAccount modelAccount = new ModelAccount();
           model = new ModelClass();
			//String blood = bloodGroupChoiceBox.setConverter(sc);
	
                model.setTempId((String)idBazarChargeChoiceBox.getValue()) ;
                modelAccount.setBazarCharge(Double.parseDouble(depositTextField.getText()));
                Date date = Date.from(addBazarTime.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date sqlDate = new Date(date.getTime());
                modelAccount.setDDate(sqlDate);
                //modelAccount.setdDate((addBazarTime.getValue()).toString());
                System.out.println(modelAccount.getDDate());
                
               try{
                       String sql=" ";
                        sql+="INSERT INTO ";
                        sql+="Balance(BAZARCHARGE,BDATE,ID) ";
                        sql+="values('"+modelAccount.getBazarCharge()+"',TO_DATE('"+addBazarTime.getValue()+"', 'YYYY-MM-DD'),'"+model.getTempId()+"')";
       //System.out.println(modelAccount.getHouseRent()+"','"+modelAccount.getGasBill()+"','"+modelAccount.getInternetBill()+"','"+modelAccount.getMaidBill()+"','"+modelAccount.getAdditional()+"','"+model.getTempId()+"','"+modelAccount.getElectricityBill());
       
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
                        ModelClass model = new ModelClass();
                        History history = new History (model);
                        history.start (stage);
					 }
					});
            
			
        });
        
        //cancel Bazar Charge Action
        cancelBazarChargeButton.setOnAction((ActionEvent e) -> {
            AdminPage adminPage = new AdminPage();
            adminPage.start(stage);
        });
		idRoot = new HBox (40,idBazarChargeLabel,idBazarChargeChoiceBox);
		depositRoot = new HBox (10,depositLabel,depositTextField);
		rentButtonRoot = new HBox(5,cancelBazarChargeButton,confirmBazarChargeButton);
		rentButtonRoot.setPadding (new Insets(10,0,0,50));
		itemRoot = new VBox (5,idRoot,depositRoot,rentButtonRoot);
		itemRoot.setPadding(new Insets(80,0,0,150));
		menuRoot = new VBox(5, addMemberButton, memberDetailsButton, addRentButton, rentHistoryButton, mealChargeButton, bazarChargeButton,
							historyButton, editMealButton, changePasswordButton, updateButton, mealRateButton, signOutButton);
        menuRoot.setPadding(new Insets(60,0,0,20));
		headingLabel = new Label("Admin");		
      
		Separator topSeparator = new Separator();
		topSeparator.setPadding(new Insets(10, 0, 0, 0));
        sep = new VBox(topSeparator);
        headingRoot = new VBox(5,headingLabel);
        headingRoot.setPadding(new Insets(20,0,0,420));
      
        scnery = new HBox(5,menuRoot,itemRoot);
		
		
        root = new VBox(headingRoot,scnery);
		root.setStyle("-fx-background-color: #336699;");
       // root.setStyle(Color.AQUAMARINE);
        scene = new Scene(root,840,650,Color.CYAN);
        //stage = new Stage();
	
        stage.setScene(scene);
        stage.setResizable(false);
        //Scene scene = new Scene(root, root.getScene().getWindow());
        stage.setTitle ("Bazar Charge");
        stage.show();
        
        
    }
}
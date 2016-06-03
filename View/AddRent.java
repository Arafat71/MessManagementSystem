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

public class AddRent extends Application{
    VBox menuRoot;
    VBox headingRoot;
    HBox addRentRoot;
    VBox labelAddRentRoot;
    VBox textAddRentRoot;
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
    Label idRentLabel;
    ChoiceBox idRentChoiceBox;
    Label houseRentLabel;
    TextField houseRentTextField;
    Label gasBillLabel;
    TextField gasBillTextField;
    Label electricityBillLabel;
    TextField electricityBillTextField;
    Label internetBillLabel;
    TextField internetBillTextField;
    Label maidBillLabel;
    TextField maidBillTextField;
    Label additionalLabel;
    TextField additionalTextField;
    // Label totalLabel;
    // TextField totalTextField;
    Button  confirmRentButton;
    Button cancelRentButton;
	 
    //ModelClass model;
    public AddRent(ModelClass model){
    this.Id = model.getId();
    }     
    public AddRent(){
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
        idRentLabel = new Label("ID");
        idRentChoiceBox = new ChoiceBox();
        ServiceClass service = new ServiceClass();
        idRentChoiceBox.setItems(FXCollections.observableArrayList(service.getAllId()));
        houseRentLabel = new Label("House Rent");
        houseRentTextField = new TextField();
        gasBillLabel = new Label("Gas Bill");
        gasBillTextField = new TextField();
        electricityBillLabel = new Label("Electricity Bill");
        electricityBillTextField = new TextField();
        internetBillLabel = new Label("Internet Bill");
        internetBillTextField = new TextField();
        maidBillLabel = new Label("Maid Bill");
        maidBillTextField = new TextField();
        additionalLabel = new Label("Additional");
        additionalTextField = new TextField();
        
        confirmRentButton = new Button("Confirm");
        cancelRentButton = new Button("Cancel");
        DatePicker addRentTime = new DatePicker(LocalDate.now());
		
		
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
	
       
        //Confirm rent Action
        confirmRentButton.setOnAction((ActionEvent e) -> {
            ModelAccount modelAccount = new ModelAccount();
           model = new ModelClass();
			//String blood = bloodGroupChoiceBox.setConverter(sc);
	
                model.setTempId((String)idRentChoiceBox.getValue()) ;
                modelAccount.setHouseRent(Double.parseDouble(houseRentTextField.getText()));
                modelAccount.setGasBill(Double.parseDouble(gasBillTextField.getText()));
                modelAccount.setElectricityBill(Double.parseDouble(electricityBillTextField.getText()));
                modelAccount.setInternetBill(Double.parseDouble(internetBillTextField.getText()));
                modelAccount.setMaidBill(Double.parseDouble(maidBillTextField.getText()));
                modelAccount.setAdditional(Double.parseDouble(additionalTextField.getText()));
                Date date = Date.from(addRentTime.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date sqlDate = new Date(date.getTime());
                modelAccount.setDDate(sqlDate);
                //modelAccount.setdDate((addRentTime.getValue()).toString());
                System.out.println(modelAccount.getDDate());
                
               try{
                       String sql=" ";
                        sql+="INSERT INTO ";
                        sql+="RentAccount(HOUSERENT, GASBILL, INTERNETBILL, MAIDBILL,ADDITIONAL,RDATE,ID,ELECTRICITYBILL) ";
                        sql+="values('"+modelAccount.getHouseRent()+"','"+modelAccount.getGasBill()+"','"+modelAccount.getInternetBill()+"','"+modelAccount.getMaidBill()+"','"+modelAccount.getAdditional()+"',TO_DATE('"+addRentTime.getValue()+"', 'YYYY-MM-DD'),'"+model.getTempId()+"','"+modelAccount.getElectricityBill()+"')";
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
                        RentHistory rentHistory = new RentHistory (model);
                        rentHistory.start (stage);
					 }
					});
            
			
        });
        
        //cancel Rent Action
        cancelRentButton.setOnAction((ActionEvent e) -> {
            AdminPage adminPage = new AdminPage();
            adminPage.start(stage);
        });
	labelAddRentRoot = new VBox(20,idRentLabel,houseRentLabel,gasBillLabel,electricityBillLabel, internetBillLabel,maidBillLabel,additionalLabel);
        textAddRentRoot = new VBox(10,idRentChoiceBox, houseRentTextField, gasBillTextField, electricityBillTextField, internetBillTextField, maidBillTextField, additionalTextField);
        addRentRoot = new HBox(5,labelAddRentRoot,textAddRentRoot);
        addRentRoot.setPadding(new Insets(100,0,5,100));
	menuRoot = new VBox(5, addMemberButton, memberDetailsButton, addRentButton, rentHistoryButton, mealChargeButton, bazarChargeButton,
							historyButton, editMealButton, changePasswordButton, updateButton, mealRateButton, signOutButton);
        menuRoot.setPadding(new Insets(60,0,0,20));
		headingLabel = new Label("Admin");		
      
		Separator topSeparator = new Separator();
		topSeparator.setPadding(new Insets(10, 0, 0, 0));
        sep = new VBox(topSeparator);
        headingRoot = new VBox(5,headingLabel);
		headingLabel.setStyle("-fx-font: 18 arial ;");
        headingRoot.setPadding(new Insets(20,0,0,420));
        HBox rentButtonRoot = new HBox(5,cancelRentButton,confirmRentButton);
        rentButtonRoot.setPadding(new Insets(350,0,0,-80));
        
        scnery = new HBox(5,menuRoot, addRentRoot,rentButtonRoot);
		
		
        root = new VBox(headingRoot,scnery);
		root.setStyle("-fx-background-color: #336699;");
       // root.setStyle(Color.AQUAMARINE);
        scene = new Scene(root,840,650,Color.CYAN);
        //stage = new Stage();
	
        stage.setScene(scene);
        stage.setResizable(false);
        //Scene scene = new Scene(root, root.getScene().getWindow());
        stage.setTitle ("Add Rent");
        stage.show();
        
        
    }
}

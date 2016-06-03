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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;

public class MealRate extends Application{
    VBox menuRoot;
    VBox headingRoot;
    VBox tableRoot;
    VBox sep;
    HBox mealRoot;
    VBox headingMealRoot; 
    HBox scnery;
    HBox deleteRoot;
    VBox mealChargeRoot;
    VBox bazarChargeRoot;
    VBox chargeRoot;
    VBox root;
    HBox dateRoot;
    TableView mealRateTable;
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
    Text totalMealText;
    Text totalExpense;
    Text totalDeposit;
    Text mealRate;
    VBox totalDepositRoot;
    VBox totalMealRoot;
    VBox totalExpenseRoot;
    VBox mealRateRoot;
    VBox totalRoot; 
    Text totalMealLabel;
    Text totalDepositLabel;
    Text totalExpenseLabel;
    Text mealRateLabel;
    VBox mealLabelRoot;
    Text monthLabel;
    VBox monthRoot;
    Label startDateLabel;
    DatePicker startDatePicker;
    Label endDateLabel;
    DatePicker endDatePicker;
    Button okButton;
    public MealRate(ModelClass model){
    this.Id = model.getId();
    }  
	
	public MealRate(){
		
    }
    public boolean getMealRate(MealRate rate){
         ModelAccount modelAccount = new ModelAccount();
        ResultSet rs = null;
        System.out.println(startDatePicker.getValue());
        System.out.println(endDatePicker.getValue());
        try{
			
                String sql = "";
//                sql= "select sum(breakFast)+sum(launch)+sum(dinner), sum(MEALCHARGE), sum(BazarExpense),sum(BazarExpense)/ (sum(breakFast)+sum(launch)+sum(dinner)) from Account,Member where Member.Id = Account.Id AND TRUNC(ddate) BETWEEN SYSDATE - 20 AND SYSDATE";
//		
               // sql= "select sum(breakFast)+sum(launch)+sum(dinner), sum(MEALCHARGE), sum(BazarExpense),sum(BazarExpense)/ (sum(breakFast)+sum(launch)+sum(dinner)) from Account,Member,Balance where Member.Id = Account.Id AND Member.Id = Balance.Id AND ddate BETWEEN TO_DATE('"+startDatePicker.getValue()+"', 'YYYY-MM-DD') AND TO_DATE('"+endDatePicker.getValue()+"', 'YYYY-MM-DD') ";
		sql= "select sum(breakFast)+sum(launch)+sum(dinner) from Account where ddate BETWEEN TO_DATE('"+startDatePicker.getValue()+"', 'YYYY-MM-DD') AND TO_DATE('"+endDatePicker.getValue()+"', 'YYYY-MM-DD') ";
                //step1 load the driver class  
                 Class.forName("oracle.jdbc.driver.OracleDriver");  
                //step2 create  the connection object  s
                 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
                //step3 create the statement object  
                Statement stmt=con.createStatement();
                
                rs = stmt.executeQuery(sql);
                rs.next();
                 String sql1 = "";
//                sql= "select sum(breakFast)+sum(launch)+sum(dinner), sum(MEALCHARGE), sum(BazarExpense),sum(BazarExpense)/ (sum(breakFast)+sum(launch)+sum(dinner)) from Account,Member where Member.Id = Account.Id AND TRUNC(ddate) BETWEEN SYSDATE - 20 AND SYSDATE";
//		
                sql1= "select sum(MEALCHARGE), sum(BazarExpense) from Balance where ddate BETWEEN TO_DATE('"+startDatePicker.getValue()+"', 'YYYY-MM-DD') AND TO_DATE('"+endDatePicker.getValue()+"', 'YYYY-MM-DD') ";
		
                //step1 load the driver class  
                 Class.forName("oracle.jdbc.driver.OracleDriver");  
                //step2 create  the connection object  s
                 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
                //step3 create the statement object  
                stmt=con.createStatement();
                
                ResultSet rs1 = stmt.executeQuery(sql1);
                rs.next();
            System.out.println(rs.getInt(1));
            modelAccount.setTotalMeal(rs.getInt(1));
            totalMealText = new Text("\t:\t"+Integer.toString(modelAccount.getTotalMeal()));
            totalMealRoot = new VBox(10,totalMealText);
            totalMealRoot.setPadding(new Insets(0,0,0,0));
            totalMealLabel = new Text("Total Meal");
            System.out.println(Integer.toString(modelAccount.getTotalMeal()));
            
            totalDeposit = new Text("\t:\t"+Double.toString(rs1.getDouble(1)));
            totalDepositRoot = new VBox(10,totalDeposit);
            totalDepositRoot.setPadding(new Insets(0,0,0,0));
            totalDepositLabel = new Text ("Total Deposit");
            System.out.println(Double.toString(rs.getDouble(2)));
            
            totalExpense = new Text("\t:\t"+Double.toString(rs1.getDouble(2)));
            totalExpenseRoot = new VBox(10,totalExpense);
            totalExpenseRoot.setPadding(new Insets(0,0,0,0));
            totalExpenseLabel = new Text("Total Expense");
            System.out.println(Double.toString(rs1.getDouble(2)));
            double MealRate = rs1.getDouble(2)/rs.getInt(1);
            //mealRate = new Text("\t:\t"+Double.toString(rs.getDouble(4)));
            mealRate = new Text("\t:\t"+MealRate);
            mealRateRoot = new VBox(10,mealRate);
            mealRateRoot.setPadding(new Insets(0,0,0,0));
            mealRateLabel = new Text("Meal Rate");
            
                } 
			catch (Exception e) {
            System.out.println(e);
    }
            return true;
            
        }
   
    Calendar now = Calendar.getInstance();
    public void start(Stage stage) throws SQLException{
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
        startDatePicker  = new DatePicker(LocalDate.now());
        endDatePicker  = new DatePicker(LocalDate.now());
        startDateLabel = new Label("Start Date");
        endDateLabel = new Label("End Date");
        okButton = new Button("OK"); 
        Text headingMeal = new Text("Info about Last 30 Days");
        headingMeal.setStyle("-fx-font: 24 arial ; -fx-font-color: #b6e7c9;");
       // headingMeal.setStyle("-fx-font-color:  ;");
        
        //meal Rate part
         String[] monthName = { "January", "February", "March", "April", "May", "June", "July",
                                "August", "September", "October", "November", "December" };
        Calendar cal = Calendar.getInstance();
        String month = monthName[cal.get(Calendar.MONTH)];
        System.out.println("Month name: " + month);
        monthText = new Text("\t:\t"+month+"\t-"+cal.get(Calendar.YEAR));
        monthRoot = new VBox(5,monthText);
        monthRoot.setPadding(new Insets(0,0,0,0));
        monthLabel = new Text("Month");
        System.out.println(startDatePicker.getValue());
        System.out.println(endDatePicker.getValue());
        ModelAccount modelAccount = new ModelAccount();
        ResultSet rs = null;
        try{
			
                String sql = "";
//                sql= "select sum(breakFast)+sum(launch)+sum(dinner), sum(MEALCHARGE), sum(BazarExpense),sum(BazarExpense)/ (sum(breakFast)+sum(launch)+sum(dinner)) from Account,Member where Member.Id = Account.Id AND TRUNC(ddate) BETWEEN SYSDATE - 20 AND SYSDATE";
//		
                sql= "select sum(breakFast)+sum(launch)+sum(dinner), sum(MEALCHARGE), sum(BazarExpense),sum(BazarExpense)/ (sum(breakFast)+sum(launch)+sum(dinner)) from Account,Member,Balance where Member.Id = Account.Id AND Member.Id = Balance.Id AND ddate BETWEEN TO_DATE('"+startDatePicker.getValue()+"', 'YYYY-MM-DD') AND TO_DATE('"+endDatePicker.getValue()+"', 'YYYY-MM-DD') ";
		
                //step1 load the driver class  
                 Class.forName("oracle.jdbc.driver.OracleDriver");  
                //step2 create  the connection object  s
                 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
                //step3 create the statement object  
                Statement stmt=con.createStatement();
                
                rs = stmt.executeQuery(sql);
                rs.next();
                  System.out.println(rs.getInt(1));
            modelAccount.setTotalMeal(rs.getInt(1));
            totalMealText = new Text("\t:\t"+Integer.toString(modelAccount.getTotalMeal()));
            totalMealRoot = new VBox(10,totalMealText);
            totalMealRoot.setPadding(new Insets(0,0,0,0));
            totalMealLabel = new Text("Total Meal");
            
            totalDeposit = new Text("\t:\t"+Double.toString(rs.getDouble(2)));
            totalDepositRoot = new VBox(10,totalDeposit);
            totalDepositRoot.setPadding(new Insets(0,0,0,0));
            totalDepositLabel = new Text ("Total Deposit");
            
            totalExpense = new Text("\t:\t"+Double.toString(rs.getDouble(3)));
            totalExpenseRoot = new VBox(10,totalExpense);
            totalExpenseRoot.setPadding(new Insets(0,0,0,0));
            totalExpenseLabel = new Text("Total Expense");
        
            mealRate = new Text("\t:\t"+Double.toString(rs.getDouble(4)));
            mealRateRoot = new VBox(10,mealRate);
            mealRateRoot.setPadding(new Insets(0,0,0,0));
            mealRateLabel = new Text("Meal Rate");
                } 
			catch (Exception e) {
            System.out.println(e);
    }
       
		
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
       
        
        	//Ok Action
        okButton.setOnAction((ActionEvent e) -> {
            MealRate rate = new MealRate();
            getMealRate(rate);
            dateRoot = new HBox(20,startDateLabel, startDatePicker, endDateLabel, endDatePicker, okButton);
            dateRoot.setPadding(new Insets(40,0,0,150));
            mealLabelRoot = new VBox(10,monthLabel,totalMealLabel,totalDepositLabel,totalExpenseLabel,mealRateLabel);
            totalRoot = new VBox(10,monthRoot,totalMealRoot,totalDepositRoot,totalExpenseRoot,mealRateRoot);
            totalRoot.setPadding(new Insets(10,0,0,10));
            totalRoot.setStyle("-fx-font: 16 arial ;");
            mealLabelRoot.setPadding(new Insets(10,0,0,10));
            mealLabelRoot.setStyle("-fx-font: 17 arial ;");

            mealRoot = new HBox(10,mealLabelRoot,totalRoot);
            headingMealRoot = new VBox(5,headingMeal,mealRoot);
            headingMealRoot.setPadding(new Insets(60,0,0,300));
            menuRoot = new VBox(5, addMemberButton, memberDetailsButton, addRentButton, rentHistoryButton, mealChargeButton, bazarChargeButton,
                                                            historyButton, changePasswordButton, updateButton, mealRateButton, signOutButton);
            menuRoot.setPadding(new Insets(-250,0,0,20));

            headingLabel = new Text("Admin");	
            headingLabel.setStyle("-fx-font: 18 arial ;");

            headingRoot = new VBox(5,headingLabel);
            headingRoot.setPadding(new Insets(20,0,0,420));

            scnery = new HBox(5,menuRoot,headingMealRoot);


            root = new VBox(headingRoot,dateRoot,headingMealRoot,scnery);
                    root.setStyle("-fx-background-color: #336699;");
           // root.setStyle(Color.AQUAMARINE);
            scene = new Scene(root,840,650,Color.CYAN);
            //stage = new Stage();

            stage.setScene(scene);
            stage.setResizable(false);
        });
        
	dateRoot = new HBox(20,startDateLabel, startDatePicker, endDateLabel, endDatePicker, okButton);
        dateRoot.setPadding(new Insets(40,0,0,150));
        mealLabelRoot = new VBox(10,monthLabel,totalMealLabel,totalDepositLabel,totalExpenseLabel,mealRateLabel);
        totalRoot = new VBox(10,monthRoot,totalMealRoot,totalDepositRoot,totalExpenseRoot,mealRateRoot);
        totalRoot.setPadding(new Insets(10,0,0,10));
        totalRoot.setStyle("-fx-font: 16 arial ;");
        mealLabelRoot.setPadding(new Insets(10,0,0,10));
        mealLabelRoot.setStyle("-fx-font: 17 arial ;");
        
        mealRoot = new HBox(10,mealLabelRoot,totalRoot);
        headingMealRoot = new VBox(5,headingMeal,mealRoot);
        headingMealRoot.setPadding(new Insets(60,0,0,300));
        menuRoot = new VBox(5, addMemberButton, memberDetailsButton, addRentButton, rentHistoryButton, mealChargeButton, bazarChargeButton,
							historyButton, changePasswordButton, updateButton, mealRateButton, signOutButton);
        menuRoot.setPadding(new Insets(-250,0,0,20));
            
        headingLabel = new Text("Admin");	
        headingLabel.setStyle("-fx-font: 18 arial ;");
	
        headingRoot = new VBox(5,headingLabel);
        headingRoot.setPadding(new Insets(20,0,0,420));
        
        scnery = new HBox(5,menuRoot,headingMealRoot);
		
		
        root = new VBox(headingRoot,dateRoot,headingMealRoot,scnery);
		root.setStyle("-fx-background-color: BURLYWOOD;");
       // root.setStyle(Color.AQUAMARINE);
        scene = new Scene(root,840,650,Color.FIREBRICK);
        //stage = new Stage();
	
        stage.setScene(scene);
        stage.setResizable(false);
        //Scene scene = new Scene(root, root.getScene().getWindow());
		stage.setTitle ("Meal Rate");
        
        stage.show();
        
        
    }
}

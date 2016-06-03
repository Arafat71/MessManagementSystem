/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Model.ModelClass;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
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
import javafx.scene.paint.Color;

public class AdminPage extends Application{
    VBox menuRoot;
    VBox headingRoot;
    HBox scnery;
    Group root;
    
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
    Label headingLabel;
    String Id;
    Scene scene;
    HBox signRoot;
    Button signOutButton;
    ModelClass model;
    public AdminPage(ModelClass model){
        this.Id = model.getId();
        System.out.println(Id);
    }
    public AdminPage(){
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
        menuRoot = new VBox(5, addMemberButton, memberDetailsButton, addRentButton, rentHistoryButton, mealChargeButton, bazarChargeButton,
							historyButton, editMealButton, changePasswordButton, updateButton, mealRateButton, signOutButton);
        menuRoot.setPadding(new Insets(0,0,0,20));
		headingLabel = new Label("Admin");
        
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
	
	scnery = new HBox(5);	
	headingLabel.setPadding (new Insets (20,10,10,350));
        headingLabel.setStyle("-fx-font: #FAEBD7;");
        headingLabel.setStyle("-fx-font: 18 arial ;");
        headingRoot = new VBox(5,headingLabel);
        headingRoot.setPadding(new Insets(20,0,0,0));
        menuRoot.setPadding(new Insets(80,0,0,20));
        root = new Group(headingRoot,menuRoot);
        root.setStyle("-fx-background-color: #336699;");
        scene = new Scene(root,750,650,Color.CYAN);
        //stage = new Stage();
        stage.setScene(scene);
        //Scene scene = new Scene(root, root.getScene().getWindow());
        stage.setTitle ("Admin Panel");
        stage.show();
        
        
    }
}
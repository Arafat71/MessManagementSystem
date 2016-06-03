/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.ModelClass;
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



public class MemberPage extends Application{
    VBox menuRoot;
    HBox headingRoot;
    HBox scnery;
    Group root;
    ModelClass model;
    
    Button addMealButton;
    Button mealHistoryButton;
    Button bazarExpenseButton;
    Button bazarHistoryButton;
    Button rentButton;
    Button changePasswordButton;
    Button updateButton;
    Button signOutButton;
    Label headingLabel;

    Scene scene;
        
    
    public void start(Stage stage){
        
        addMealButton = new Button("Add Meal");
        addMealButton.setStyle("-fx-font: 15 arial; -fx-base: #b6e7c9;");
        mealHistoryButton = new Button("Meal History");
        mealHistoryButton.setStyle("-fx-font: 15 arial; -fx-base: #b6e7c9;");
        bazarExpenseButton = new Button("Bazar Expense");
        bazarExpenseButton.setStyle("-fx-font: 15 arial; -fx-base: #b6e7c9;");
	bazarHistoryButton = new Button("Bazar History");
        bazarHistoryButton.setStyle("-fx-font: 15 arial; -fx-base: #b6e7c9;");
        rentButton = new Button("Rent");
        rentButton.setStyle("-fx-font: 15 arial; -fx-base: #b6e7c9;");
	changePasswordButton =new Button ("Change Password");
        changePasswordButton.setStyle("-fx-font: 15 arial; -fx-base: #b6e7c9;");
        updateButton = new Button("Update");
        updateButton.setStyle("-fx-font: 15 arial; -fx-base: #b6e7c9;");
        signOutButton = new Button("Sign Out");
        signOutButton.setStyle("-fx-font: 15 arial; -fx-base: #b6e7c9;");
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
        menuRoot = new VBox(5, addMealButton, mealHistoryButton, bazarExpenseButton, bazarHistoryButton, rentButton,changePasswordButton,
		updateButton,signOutButton);
        menuRoot.setPadding(new Insets(40,0,0,20));
		headingLabel = new Label("Member");
		

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
		
		
		
        
        
	//scnery = new HBox(5);	
        headingRoot = new HBox(5,headingLabel);
	headingLabel.setStyle("-fx-font: 24 Tahoma ;-fx-text-fill:  ALICEBLUE;");
		//headingLabel.setStyle("-fx-text-fill: RED;");
		//headingLabel.setFill(Color.RED);
	
        headingRoot.setPadding(new Insets(20,0,0,370));
        
	menuRoot.setPadding (new Insets (80,0,0,25));
        root = new Group();
	root.getChildren ().addAll (headingRoot,menuRoot);
        scene = new Scene(root,750,650,Color.BURLYWOOD);
        //scene.setStyle("-fx-background-color: #2f4f4f");
        //stage = new Stage();
        stage.setScene(scene);
        //Scene scene = new Scene(root, root.getScene().getWindow());
		stage.setTitle ("Member Page");
        
        stage.show();
        
        
    }
}

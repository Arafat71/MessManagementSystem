/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.ModelAccount;
import Model.ModelClass;
import Service.ServiceClass;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Calendar;
import javafx.event.EventType;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MealDetails extends Application{
    HBox root;
    HBox menuRoot;
    Button homeButton;
    Button mealDetailsButton;
    Button userGuideButton;
    Button contactButton;
    Scene scene;
    HBox signRoot;
	VBox mealDetailsRoot;
	VBox totalMealDetailsRoot;
	VBox dateTextRoot;
	String Id;
    Button signInButton;
    Button signUpButton;
	ModelClass model;
	ModelAccount modelAccount;
	TableView mealDetailsTable;
	Text dateText;
    
    public MealDetails(ModelClass model){
    this.Id = model.getId();
    }

	public MealDetails(){
    }   	
	
    public void start(Stage stage){
        homeButton = new Button("Home");
        homeButton.setStyle(" -fx-base: #b6e7c9;");
        mealDetailsButton = new Button("Meal Details");
        mealDetailsButton.setStyle("-fx-base: WHITE;");
        userGuideButton = new Button("User Guide");
        userGuideButton.setStyle(" -fx-base: #b6e7c9;");
        contactButton = new Button("Contact");
        contactButton.setStyle(" -fx-base: #b6e7c9;");
        menuRoot = new HBox(5, homeButton, mealDetailsButton, userGuideButton, contactButton);
        menuRoot.setPadding(new Insets(20,0,0,20));
        signInButton = new Button("Sign In");
        signInButton.setStyle("-fx-base: #b6e7c9;");
        signUpButton = new Button("Sign Up");
        signUpButton.setStyle("-fx-base: #b6e7c9;");
		
		
		//Home Action
        		//Home Action
        homeButton.setOnAction(new EventHandler<ActionEvent>() {
           
            public void handle(ActionEvent e) {
                HomePage homePage = new HomePage();
                homePage.start(stage);
            }
        });
      
		
		//Meal Details Action
        mealDetailsButton.setOnAction(new EventHandler<ActionEvent>() {
           
            public void handle(ActionEvent e) {
				model = new ModelClass ();
                MealDetails mealDetails = new MealDetails(model);
                mealDetails.start(stage);
            }
        });
		
		
		//Sign Up Action
		signUpButton.setOnAction(new EventHandler<ActionEvent>() {
           
            public void handle(ActionEvent e) {
                SignUp signUp = new SignUp();
                signUp.start(stage);
            }
        });
		
		//Sign in Action
		signInButton.setOnAction(new EventHandler<ActionEvent>() {
           
            public void handle(ActionEvent e) {
                SignIn signIn = new SignIn();
                signIn.start(stage);
            }
        });
		
		//Contact Action
		contactButton.setOnAction(e->{
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText("Contact");
					alert.setContentText("Email: Admin@gmail.com");
				

					alert.showAndWait().ifPresent(response -> {
					 if (response == ButtonType.OK) {
						 HomePage homePage = new HomePage ();
						 homePage.start (stage);
					 }
					});
		});
		
		//UserGuide ACtion
		userGuideButton.setOnAction(e->{
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText("User Guide");
					alert.setContentText("Home 	     : Overview of Mess Management.\n"+
										  "Meal Details : Amount of meal per day.\n"+
										  "About	     : Goal of mess management system.\n"+
										  "Sign In	     : To access this system.\n"+
										  "Sign Up	     : Only for first member it\n"+
														  "will show Sign Up form and others can be member\n"+ 
														  "via consult with Admin."
										  );
				

					alert.showAndWait().ifPresent(response -> {
					 if (response == ButtonType.OK) {
						 HomePage homePage = new HomePage ();
						 homePage.start (stage);
					 }
					});
		});
		
		
		
		//Member Table 
		 mealDetailsTable = new TableView();
		 Calendar cal = Calendar.getInstance();
		 String[] monthName = { "January", "February", "March", "April", "May", "June", "July",
								"August", "September", "October", "November", "December" };
        String month = monthName[cal.get(Calendar.MONTH)];
        //System.out.println("Month name: " + month);
        dateText = new Text(cal.get(Calendar.DATE)+"-"+month+"-"+cal.get(Calendar.YEAR));
		 //dateText = new Text("\t\t"+cal.get(Calendar.DATE));
		dateTextRoot = new VBox (5,dateText); 
        dateTextRoot.setPadding (new Insets (0,0,0,150));
        //creating Column for showing Meal Info
        TableColumn nameColumn = new TableColumn("Name");
        TableColumn breakFastColumn = new TableColumn("BreakFast");
        TableColumn launchColumn = new TableColumn("Launch");
        TableColumn dinnerColumn = new TableColumn("Dinner");
        nameColumn.setPrefWidth(150);
        nameColumn.setResizable(false);
        
   
        //Mapping columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, String>("firstName"));
        breakFastColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Integer>("breakFast"));
        launchColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Integer>("launch"));
        dinnerColumn.setCellValueFactory(new PropertyValueFactory<ModelAccount, Integer>("dinner"));
  
       
		//Adding the Columns
		mealDetailsTable.getColumns().addAll(nameColumn,breakFastColumn, launchColumn, dinnerColumn);
        
	 
        try{
            ServiceClass service = new ServiceClass();
			modelAccount = new ModelAccount ();
            // Setting contactList to table as a observable list
            mealDetailsTable.setItems(FXCollections.observableArrayList(service.getMealDetails()));
        }
        catch(Exception ex){
            
        }
		
		
		
	mealDetailsTable.setStyle("-fx-background-color: FIREBRICK;");
        signRoot = new HBox(5, signInButton, signUpButton);
        signRoot.setPadding(new Insets(20,20,0,300));
        //Image image = new Image ("image/homepage4.jpg");
        root = new HBox(5,menuRoot,signRoot);
        root.setPadding(new Insets(50,0,10,0));
		// root.setBackground(new Background(new BackgroundImage(image,BackgroundRepeat.REPEAT,
                                                                  // BackgroundRepeat.REPEAT,
                                                                  // BackgroundPosition.DEFAULT,
                                                                  // BackgroundSize.DEFAULT)));
        
		mealDetailsRoot = new VBox (15,dateTextRoot,mealDetailsTable);
		mealDetailsRoot.setPadding (new Insets (70,200,15,200));
		mealDetailsRoot.setPrefWidth(-150);
		mealDetailsRoot.setPrefHeight(300);
		// root.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,
                                                             // null,new BorderWidths(3))));
        
		totalMealDetailsRoot = new VBox (10,root,mealDetailsRoot);
                totalMealDetailsRoot.setStyle("-fx-background-color: BURLYWOOD;");
		scene = new Scene(totalMealDetailsRoot,750,650,Color.BURLYWOOD);
        //stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Meal Details");
		stage.setResizable(false); 
        stage.show();
        
        
    }
}

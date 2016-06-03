/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

//import com.sun.prism.paint.Color;
import Model.ModelClass;
import Service.ServiceClass;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import static javafx.scene.paint.Color.BLUE;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;

public class HomePage extends Application{
    HBox root;
    HBox menuRoot;
    Button homeButton;
    Button mealDetailsButton;
    Button userGuideButton;
    Button contactButton;
    Scene scene;
    HBox signRoot;
	String Id;
    Button signInButton;
    Button signUpButton;
	ModelClass model;
    
    public HomePage(ModelClass model){
    this.Id = model.getId();
    }

	public HomePage(){
    }   	
	
    public void start(Stage stage){
        homeButton = new Button("Home");
        homeButton.setStyle(" -fx-base: WHITE;");
        mealDetailsButton = new Button("Meal Details");
        mealDetailsButton.setStyle("-fx-base: #b6e7c9;");
        userGuideButton = new Button("User Guide");
        userGuideButton.setStyle(" -fx-base: #b6e7c9;");
        contactButton = new Button("Contact");
        contactButton.setStyle(" -fx-base: #b6e7c9;");
        menuRoot = new HBox(5, homeButton, mealDetailsButton, userGuideButton, contactButton);
        menuRoot.setPadding(new Insets(0,0,0,20));
        signInButton = new Button("Sign In");
        signInButton.setStyle("-fx-base: #b6e7c9;");
        signUpButton = new Button("Sign Up");
        signUpButton.setStyle("-fx-base: #b6e7c9;");
		
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
       //ServiceClass service = new ServiceClass();
        try{
                     String sql = "SELECT COUNT(1) FROM MEmber WHERE MEMberTYpe = 'Admin'";
       
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//step2 create  the connection object  s
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");  
			//step3 create the statement object  
			Statement stmt=con.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        rs.next();
                        System.out.println(rs.getInt(1));
                signUpButton.setOnAction((ActionEvent e) -> {
                         try {
                             if(rs.getInt(1)==0){
                                 SignUp signUp = new SignUp();
                                 signUp.start(stage);
                             }
                             else{
                                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText("Contact With Admin");
					

					alert.showAndWait().ifPresent(response -> {
					 if (response == ButtonType.OK) {
//						 HomePage homePage = new HomePage ();
//						 homePage.start (stage);
                                             contactButton.pressedProperty();
					 }
					});
                             }
                         } catch (SQLException ex) {
                             Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                         }
                });
                    }
                catch(Exception ex){
                            ex.printStackTrace();
                            System.out.println(ex);
                            }
		
       
		
		//Sign in Action
      
	signInButton.setOnAction((ActionEvent e) -> {
            signInButton.setStyle(" -fx-base: WHITE;");
            homeButton.setStyle(" -fx-base: #b6e7c9;");     
            SignIn signIn = new SignIn();
            signIn.start(stage);
//            popup.show(stage);
//            popup.centerOnScreen();
        });
		
		//Contact Action
    contactButton.setOnAction((ActionEvent e)->{
        contactButton.setStyle(" -fx-base: WHITE;");
        homeButton.setStyle(" -fx-base: #b6e7c9;");    		
            try {
                String sql = "";
                sql = "select FIRSTNAME,ID,DATEOFBIRTH,BLOODGROUP,MOBILENO,EMAIL,JOINDATE,MEMBERTYPE from MEMBER Where MEMBERTYPE = 'Admin'";
                
                //step1 load the driver class
                Class.forName("oracle.jdbc.driver.OracleDriver");  
                //step2 create  the connection object  s
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MMS","tiger");
                //step3 create the statement object  
                Statement stmt=con.createStatement();
                        
                ResultSet rs = stmt.executeQuery(sql);
                //int g =0;
                rs.next();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Contact Information");
                alert.setHeaderText("Admin");
                alert.setContentText("Email\t:\t"+ rs.getString(6)+"\nMobile\t:\t"+rs.getString(5));
              
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        HomePage homePage = new HomePage ();
                        homePage.start (stage);
                    }
                });
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            }
		});
		
		//UserGuide ACtion
		userGuideButton.setOnAction(e->{
                    userGuideButton.setStyle(" -fx-base: WHITE;");
                    homeButton.setStyle(" -fx-base: #b6e7c9;");
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
        signRoot = new HBox(5, signInButton, signUpButton);
        signRoot.setPadding(new Insets(0,20,0,300));
        Image image = new Image ("Data/image/homepage4.jpg");
        root = new HBox(5,menuRoot,signRoot);
        root.setPadding(new Insets(70,0,0,0));
         root.setBackground(new Background(new BackgroundImage(image,BackgroundRepeat.REPEAT,
                                                                  BackgroundRepeat.REPEAT,
                                                                  BackgroundPosition.DEFAULT,
                                                                  BackgroundSize.DEFAULT)));
        root.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,
                                                             null,new BorderWidths(3))));
        scene = new Scene(root,750,650);
        //stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Home Page");
		stage.setResizable(false);
        //Scene scene = new Scene(root, root.getScene().getWindow());
        
        stage.show();
        
        
    }
}

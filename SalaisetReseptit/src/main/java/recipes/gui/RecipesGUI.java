/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipes.gui;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import recipes.db.RecipesDB;
import recipes.domain.User;
import recipes.domain.Validation;

/**
 *
 * @author aebjork
 */
public class RecipesGUI extends Application {

    private String database;
    private RecipesDB dbase;
    private Validation check; 

    private Scene homeScene;
    private Scene beginScene;
//    TODO:
//    private Scene newUserScene;
//    private Scene loginScene;    
//    private Scene searchScene;
//    private Scene createScene;
//    private Scene modifyScene;

    private final int WIDTH = 900;
    private final int HEIGHT = 600;
    private String user_name = "XXX";

    
    @Override
    public void init() throws Exception {
        this.database = "recipesDatabase";
        this.dbase = new RecipesDB(database);
        this.check = new Validation(dbase);
        String dbpath = this.dbase.getDBPath();
        System.out.println("Database path is: " + dbpath);
        User testDBExisting = this.dbase.searchUser(0, "admin");
        if (testDBExisting == null) {
            // adding new database, if not already existing
            this.dbase.createRecipeDB();
            // adding admin user to the database
            this.dbase.addUser("admin", "secret", "a", "b", "test@email.fi");
        }
    }
    
    
    public RecipesDB getDB() {
        return this.dbase;
    }


    @Override
    public void start(Stage stage) throws Exception {

        // Home Scene
        
        Pane screen = new Pane();
        screen.setPrefSize(this.WIDTH, this.HEIGHT);

        Label title = new Label("Salaiset reseptit"); 

        Label username = new Label("Käyttäjätunnus");
        Label password = new Label("Salasana");
        TextField userField = new TextField("            ");
        PasswordField passField = new PasswordField();
        Button loginButton = new Button("Kirjaudu");
        Button registerButton = new Button("Rekisteröidy");
        Button endButton = new Button("Lopeta");

        Label info = new Label("Kirjaudu tai rekisteröidy"); 

        VBox v_user = new VBox();
        v_user.setPadding(new Insets(10, 10, 10, 10));
        v_user.getChildren().addAll(userField, username);
        VBox v_pass = new VBox();
        v_pass.setPadding(new Insets(10, 10, 10, 10));
        v_pass.getChildren().addAll(passField, password);

        HBox menu = new HBox();
        menu.getChildren().addAll(v_user, v_pass, loginButton, registerButton, endButton);
        menu.setPadding(new Insets(10, 10, 10, 10));
        menu.setSpacing(10);
        menu.setAlignment(Pos.TOP_LEFT);
        
        HBox titlebar = new HBox();
        title.minHeight(50.0);
        titlebar.getChildren().add(title);
        titlebar.setPadding(new Insets(10, 10, 10, 10));
        titlebar.setSpacing(10);        
        titlebar.setAlignment(Pos.BASELINE_CENTER);
        
        HBox infobar = new HBox();
        infobar.getChildren().add(info);
        infobar.setPadding(new Insets(10, 10, 10, 10));
        infobar.setSpacing(10);
        infobar.setAlignment(Pos.BOTTOM_RIGHT);

        BorderPane setting = new BorderPane();

        setting.setTop(menu);
        setting.setCenter(titlebar);
        setting.setBottom(infobar);
        setting.setPrefSize(this.WIDTH, this.HEIGHT);

        // ----------------------------------
        // Other scenes
        
        // Begin Scene
        Label hello = new Label("Terve, " + this.user_name + ", herkullista päivää!");
        
        Label title2 = new Label("Salaiset reseptit");
        HBox titlebar2 = new HBox();
        title2.minHeight(50.0);
        titlebar2.getChildren().add(title2);
        titlebar2.setPadding(new Insets(10, 10, 10, 10));
        titlebar2.setSpacing(10);        
        titlebar2.setAlignment(Pos.BASELINE_CENTER);
        
        HBox beginMenu = new HBox();
        Label blanco = new Label("                                                                                                    ");
        Button end2 = new Button("Lopeta");
        beginMenu.getChildren().addAll(hello, blanco, end2);
        beginMenu.setPadding(new Insets(10, 10, 10, 10));
        beginMenu.setSpacing(10);
        beginMenu.setAlignment(Pos.TOP_LEFT);
        
        Label info2 = new Label("Valitse toiminto painamalla nappia");
        
        Pane begin = new Pane();
        begin.setPrefSize(this.WIDTH, this.HEIGHT);
        BorderPane setBegin = new BorderPane();
        setBegin.setTop(beginMenu);
        setBegin.setCenter(titlebar2);
        setBegin.setBottom(info2);

        // ----------------------------------
        // Event Handlers
        
        endButton.setOnAction((event) -> {
            stage.close();
        });
        
        end2.setOnAction((event) -> {
            stage.close();
        });

        loginButton.setOnAction((event) -> {

            String inputUsername = userField.getText();
            String inputPassword = passField.getText();

            try {
                User loginUser = check.validate(inputUsername, inputPassword);
                if (loginUser != null) {
                    this.user_name = loginUser.getFirstname();
                    hello.setText("Terve, " + this.user_name + ", herkullista päivää!");
                    this.beginScene = new Scene(setBegin,this.WIDTH, this.HEIGHT);
                    stage.setScene(beginScene);
                } 
            } catch (SQLException s) {
                info.setText("Virhe: " + s.getMessage());;
            }

        });

        // ----------------------------------
        // Home Scene
        
        screen.getChildren().add(setting);
        this.homeScene = new Scene(screen);

        stage.setTitle("Secret Recipes");
        stage.setScene(homeScene);
        stage.show();

    }

    
    @Override
    public void stop() {
        System.out.println("Closing...");
    }

    
    public static void main(String[] args) {
        launch(args);
    }

}

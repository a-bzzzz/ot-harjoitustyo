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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import recipes.db.RecipesDB;
import recipes.db.UsersDB;
import recipes.domain.User;
import recipes.domain.Validation;

/**
 *
 * @author aebjork
 */
public class RecipesGUI extends Application {

    private String userDatabase;
    // private RecipesDB dbase;
    private UsersDB udbase;
    private Validation check;

    private Scene homeScene;
    private Scene beginScene;
//    TODO:
    private Scene newUserScene;
//    private Scene loginScene;    
//    private Scene searchScene;
//    private Scene createScene;
//    private Scene modifyScene;

    private final int WIDTH = 900;
    private final int HEIGHT = 600;
    private final int SMALLWIDTH = 300;
    private final int SMALLHEIGHT = 500;
    private String user_name = "XXX";

    @Override
    public void init() throws Exception {
        this.userDatabase = "UsersDatabase";
        //this.dbase = new RecipesDB(database);
        this.udbase = new UsersDB(this.userDatabase);
        this.check = new Validation(udbase);
        String udbpath = this.udbase.getDBPath();
        System.out.println("Database path is: " + udbpath);
        User testDBExisting = this.udbase.searchUser("admin");
        if (testDBExisting == null) {
            // adding new database, if not already existing
            // this.dbase.createRecipeDB();
            this.udbase.createUsersDB();
            // adding admin user to the database
            this.udbase.addUser("admin", "secret", "a", "b", "admin@email.fi");
        }
    }

    public UsersDB getDB() {
        return this.udbase;
    }

//    public RecipesDB getDB() {
//        return this.dbase;
//    }
    @Override
    public void start(Stage stage) throws Exception {

        // homeScene
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
        // newUserScene
        Pane popup = new Pane();
        popup.setPrefSize(this.SMALLWIDTH, this.SMALLHEIGHT);

        Label regtitle = new Label("Rekisteröityminen");
        title.minHeight(50.0);
        Label line1 = new Label("____________________");
        Label miniblanco = new Label("                    ");
        Label fname = new Label("Etunimi:");
        Label lname = new Label("Sukunimi:");
        Label email = new Label("Sähköpostiosoite:");
        Label uname = new Label("Käyttäjätunnus:");
        Label pword = new Label("Salasana:");
        Label line2 = new Label("____________________");
        Label line3 = new Label("____________________");
        TextField fnameField = new TextField("            ");
        TextField lnameField = new TextField("            ");
        TextField emailField = new TextField("            ");
        TextField unameField = new TextField("            ");
//        TextField fnameField = new TextField("");
//        TextField lnameField = new TextField("");
//        TextField emailField = new TextField("");
//        TextField unameField = new TextField("");
        PasswordField newPword = new PasswordField();
        Button register = new Button("REKISTERÖIDY");
        Button back = new Button("TAKAISIN");
        
        HBox poptitle = new HBox();
        poptitle.getChildren().add(regtitle);
        poptitle.setPadding(new Insets(10, 10, 10, 10));
        poptitle.setSpacing(20);
        poptitle.setAlignment(Pos.BASELINE_CENTER);

        GridPane setpop = new GridPane();
        setpop.setPrefSize(this.SMALLWIDTH, this.SMALLHEIGHT);
        setpop.setAlignment(Pos.CENTER);
        setpop.setPadding(new Insets(20, 20, 20, 20));
        setpop.add(poptitle, 0, 0);
        setpop.add(line1, 0, 1);
        setpop.add(miniblanco, 0, 2);
        setpop.add(fname, 0, 3);
        setpop.add(fnameField, 0, 4);
        setpop.add(lname, 0, 5);
        setpop.add(lnameField, 0, 6);
        setpop.add(email, 0, 7);
        setpop.add(emailField, 0, 8);
        setpop.add(uname, 0, 9);
        setpop.add(unameField, 0, 10);
        setpop.add(pword, 0, 11);
        setpop.add(newPword, 0, 12);
        setpop.add(register, 0, 13);
        setpop.add(line2, 0, 14);
        setpop.add(back, 0, 15);

        popup.getChildren().add(setpop);
        this.newUserScene = new Scene(popup);
        stage.setTitle("Registration");

        // beginScene
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
                    this.beginScene = new Scene(setBegin, this.WIDTH, this.HEIGHT);
                    stage.setScene(beginScene);
                }
            } catch (SQLException s) {
                info.setText("Kirjautumisvirhe: " + s.getMessage());
            }

        });

        registerButton.setOnAction((event) -> {            
            stage.close();
            stage.setScene(this.newUserScene);
            stage.show();
        });

        back.setOnAction((event) -> {
            stage.close();
            stage.setScene(this.homeScene);
            stage.show();
        });

        // ----------------------------------
        
        // homeScene - setting up the primary scene, the first window for the user
        screen.getChildren().add(setting);
        this.homeScene = new Scene(screen);
        stage.setTitle("Secret Recipes");
        stage.setScene(this.homeScene);
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

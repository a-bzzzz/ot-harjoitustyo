/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipes.gui;

import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import recipes.domain.Info;
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
    private String userName = "XXX";
    private boolean dbCreated = false;
    private User admin = new User("a", "b", "admin@email.fi", "admin", "secret");
    private boolean adminUserCreated;

    @Override
    public void init() {
        this.userDatabase = "UsersDatabase";
        //this.dbase = new RecipesDB(database);
        this.udbase = new UsersDB(this.userDatabase);
        this.check = new Validation(udbase);
        this.dbCreated = false;

        String udbpath = this.udbase.getDBPath();
        System.out.println("Database path is: " + udbpath);
        User dbExisting = this.udbase.searchUser("admin");
        if (dbExisting == null) {
            // adding new databases, if not already existing
            // this.dbase.createRecipeDB();
            this.dbCreated = this.udbase.createUsersDB();
            System.out.println("Tietokanta " + this.userDatabase + " on luotu: " + dbCreated);
            // adding admin user to the database
            adminUserCreated = this.udbase.addUser(admin);
            // System.out.println("Admin-käyttäjä " + this.admin.toString() + " on luotu.");
        } else {
            this.dbCreated = true;
        }

    }

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
        //title.minHeight(50.0);
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

        Label regtitle = new Label("REKISTERÖITYMINEN");
        //title.minHeight(50.0);
        Label line1 = new Label("____________________");
        Label miniblanco = new Label("                    ");
        Label fname = new Label("Etunimi:");
        Label lname = new Label("Sukunimi:");
        Label email = new Label("Sähköpostiosoite:");
        Label uname = new Label("Käyttäjätunnus:");
        Label pword = new Label("Salasana:");
        Label line2 = new Label("____________________");
        Label line3 = new Label("____________________");
        Label line4 = new Label("____________________");
        Label info_pop = new Label("            ");
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
        Button end3 = new Button("LOPETA");

        VBox poptitle = new VBox();
        poptitle.getChildren().addAll(regtitle, line1);
        poptitle.setPadding(new Insets(20, 20, 20, 20));
        poptitle.setSpacing(20);
        //poptitle.setAlignment(Pos.BASELINE_CENTER);
        poptitle.setAlignment(Pos.TOP_CENTER);

        HBox popinfo = new HBox();
        popinfo.getChildren().add(info_pop);
        popinfo.setPadding(new Insets(10, 10, 10, 10));
        popinfo.setSpacing(10);
        popinfo.setAlignment(Pos.BOTTOM_CENTER);

        GridPane setpop = new GridPane();
        setpop.setPrefSize(this.SMALLWIDTH, this.SMALLHEIGHT);
        setpop.setAlignment(Pos.CENTER);
        setpop.setPadding(new Insets(10, 10, 10, 10));
        setpop.add(miniblanco, 1, 2);
        setpop.add(fname, 1, 3);
        setpop.add(fnameField, 1, 4);
        setpop.add(lname, 1, 5);
        setpop.add(lnameField, 1, 6);
        setpop.add(email, 1, 7);
        setpop.add(emailField, 1, 8);
        setpop.add(uname, 1, 9);
        setpop.add(unameField, 1, 10);
        setpop.add(pword, 1, 11);
        setpop.add(newPword, 1, 12);
        setpop.add(register, 1, 13);
        setpop.add(line2, 1, 14);
        setpop.add(back, 1, 15);
        setpop.add(line3, 1, 16);
        setpop.add(end3, 1, 17);
        setpop.add(line4, 1, 18);

        popup.getChildren().addAll(poptitle, setpop, popinfo);
        this.newUserScene = new Scene(popup);
        stage.setTitle("Registration");

        // beginScene
        Label hello = new Label("Terve, " + this.userName + ", herkullista päivää!");

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
        // Closing application in different scenes
        endButton.setOnAction((event) -> {  // in homeScene
            stage.close();
        });

        end2.setOnAction((event) -> {       // in beginScene
            stage.close();
        });

        end3.setOnAction((event) -> {       // in newUserScene
            stage.close();
        });

        // Login action: from homeScene to beginScene, if successfull
        loginButton.setOnAction((event) -> {

            String inputUsername = userField.getText().strip();
            String inputPassword = passField.getText().strip();

            try {
                Info<User, String> loginInfo = this.check.validate(inputUsername, inputPassword);
                User loginUser = loginInfo.getUser();
                String infotext = loginInfo.getText();
                if (loginUser != null) {
                    this.userName = loginUser.getFirstname();
                    hello.setText("Terve, " + this.userName + ", herkullista päivää!");
                    this.beginScene = new Scene(setBegin, this.WIDTH, this.HEIGHT);
                    stage.setScene(beginScene);
                } else {
                    info.setText(infotext);
                }
            } catch (SQLException s) {
                info.setText("Kirjautumisvirhe: " + s.getMessage());
            }

        });

        // From homeScene to newUserScene
        registerButton.setOnAction((event) -> {
            stage.close();
            stage.setScene(this.newUserScene);
            stage.show();
        });

        // Back to homeScene from newUserScene       
        back.setOnAction((event) -> {
            stage.close();
            stage.setScene(this.homeScene);
            stage.show();
        });

        // Registration in newUserScene (adds a new user), if successfull -> beginScene 
        register.setOnAction((event) -> {

            String inFname = fnameField.getText().strip();
            String inLname = lnameField.getText().strip();
            String inEmail = emailField.getText().strip();
            String inUname = unameField.getText().strip();
            String inPword = newPword.getText().strip();
            info_pop.setText("");

            boolean valid = false;
            User newUser = new User(inFname, inLname, inEmail, inUname, inPword);
            valid = this.check.newValidate(newUser);
            System.out.println("Täytetyt kentät ovat oikein: " + valid);
            if (valid) {
                User searchedUser = this.udbase.searchUser(inUname);
                System.out.println("Tarkistetaan löytyykö jo lisättävä käyttäjä " + searchedUser);
                if (searchedUser == null) {
                    System.out.println("Lisättävää ei löytynyt vielä rekisteristä.");
                }
            }
//            try {
            if (valid && this.udbase.searchUser(inUname) == null) {
                boolean addingSucceeded = this.udbase.addUser(newUser);
                System.out.println("Lisäys onnistui: " + addingSucceeded);
                if (addingSucceeded) {
                    hello.setText("Terve, " + inUname + ", herkullista päivää!");
                    stage.close();
                    this.beginScene = new Scene(setBegin, this.WIDTH, this.HEIGHT);
                    stage.setScene(this.beginScene);
                    stage.show();
                } else {
                    info_pop.setText("REKISTERÖITYMINEN EPÄONNISTUI!");
                }
            } else {
                info_pop.setText("TÄYTÄ KAIKKI KENTÄT!");
            }
            // stage.close();
//            stage.setScene(this.newUserScene);
//            stage.show();
//            } catch (SQLException s) {
//                // info.setText("Rekisteröitymisvirhe: " + s.getMessage());
//                info.setText("Rekisteröitymisvirhe: Username is already in use");
            fnameField.setText("");
            lnameField.setText("");
            emailField.setText("");
            unameField.setText("");
            newPword.setText("");

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

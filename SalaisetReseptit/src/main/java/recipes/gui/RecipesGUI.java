package recipes.gui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
import recipes.domain.Recipe;
import recipes.domain.RecipeBook;
import recipes.domain.User;
import recipes.domain.Validation;

/**
 * Secret Recipes application RecipesGUI class presents the graphical user
 * interface of the application.
 */
public class RecipesGUI extends Application {

    private String userDatabase;
    private String recipeDatabase;
    private RecipesDB dbase;
    private UsersDB udbase;
    private Validation check;
    private RecipeBook book;

    private Scene homeScene;
    private Scene beginScene;
    private Scene newUserScene;
    private Scene searchScene;
    private Scene createScene;
    private Scene modifyScene;
    private Scene recipeScene;
    private GridPane searchOp;
    private Label infoSearch;
    private Label infoRecipe;
    private ListView<String> stuffDetails;
    private ListView<String> amountDetails;
    private ListView<String> guidelines;

    private final int WIDTH = 900;
    private final int HEIGHT = 600;
    private final int SMALLWIDTH = 300;
    private final int SMALLHEIGHT = 500;
    private String userName = "XXX";
    private boolean udbCreated = false; // checks the existance of Users database
    private boolean rdbCreated = false; // checks the existance of Recipes database
    private final User admin = new User("a", "b", "admin@email.fi", "admin", "secret");
    private Recipe recipe = new Recipe("kaakao", 1, "juomat");
    private boolean adminUserCreated;
    private boolean recipeCreated;

    private String recipeName;
    private Recipe newRecipe;
    private Map<String, Recipe> recipes;
    private List<String> recipeNames;

    @Override
    public void init() {
        this.userDatabase = "UsersDatabase";
        this.recipeDatabase = "RecipesDatabase";
        this.udbase = new UsersDB(this.userDatabase);
        this.dbase = new RecipesDB(this.recipeDatabase);
        this.check = new Validation(udbase);
        this.udbCreated = false;
        this.recipeName = "";
        this.book = new RecipeBook();
        this.newRecipe = null;
        this.recipes = new HashMap<>();
        this.recipeNames = new ArrayList<>();
        this.searchOp = new GridPane();
        this.infoSearch = new Label();
        this.stuffDetails = new ListView<String>();
        this.amountDetails = new ListView<String>();
        this.guidelines = new ListView<String>();

        String udbpath = this.udbase.getDBPath();
        System.out.println("Database path is: " + udbpath);
        User dbExisting = this.udbase.searchUser("admin");
        if (dbExisting == null) {
            // adding new databases, if not already existing
            this.udbCreated = this.udbase.createUsersDB();
            System.out.println("Database " + this.userDatabase + " has been created: " + this.udbCreated);
            // adding admin user to user database, when new database is created
            adminUserCreated = this.udbase.addUser(admin);
            if (adminUserCreated) {
                System.out.println("Admin user " + this.admin.toString() + "\n has been created.");
            }
            this.rdbCreated = this.dbase.createRecipeDB();
            System.out.println("Database " + this.recipeDatabase + " has been created: " + this.rdbCreated);

            // adding an initial recipe to recipe database
            this.recipe.setIngredient("maito", "2,5 dl");
            this.recipe.setIngredient("kaakaojauhe", "2 rkl");
            this.recipe.setInstruction("Kuumenna maito lähes kiehuvaksi.");
            this.recipe.setInstruction("Lisää kaakaojauhe ja sekoita.");
            this.recipeCreated = this.dbase.addRecipe(recipe, this.recipe.getIngredientsAndAmounts(), this.recipe.getInstructions());
            if (this.recipeCreated) {
                System.out.println("The first recipe, " + this.recipe.toString() + ", has been created.");
            }
        } else {
            this.udbCreated = true;
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

        // Scene set-ups =======================================================
        // homeScene - 1st scene, contais login and registration ---------------
        Pane screen = new Pane();
        screen.setPrefSize(this.WIDTH, this.HEIGHT);

        Label titleHome = new Label("Salaiset reseptit");

        Label username = new Label("Käyttäjätunnus");
        Label password = new Label("Salasana");
        TextField userField = new TextField("            ");
        PasswordField passField = new PasswordField();
        Button loginButton = new Button("Kirjaudu");
        Button registerButton = new Button("Rekisteröidy");
        Button endHome = new Button("Lopeta");
        Label infoHome = new Label("Kirjaudu tai rekisteröidy");

        VBox v_user = new VBox();
        v_user.setPadding(new Insets(10, 10, 10, 10));
        v_user.getChildren().addAll(userField, username);
        VBox v_pass = new VBox();
        v_pass.setPadding(new Insets(10, 10, 10, 10));
        v_pass.getChildren().addAll(passField, password);

        HBox menuHome = new HBox();
        menuHome.getChildren().addAll(v_user, v_pass, loginButton, registerButton, endHome);
        menuHome.setPadding(new Insets(10, 10, 10, 10));
        menuHome.setSpacing(10);
        menuHome.setAlignment(Pos.TOP_LEFT);

        GridPane titleHomeGrid = new GridPane();
        titleHomeGrid.add(titleHome, 2, 1);
        titleHomeGrid.setAlignment(Pos.BASELINE_CENTER);

        HBox infobarHome = new HBox();
        infobarHome.getChildren().add(infoHome);
        infobarHome.setPadding(new Insets(10, 10, 10, 10));
        infobarHome.setSpacing(10);
        infobarHome.setAlignment(Pos.BOTTOM_RIGHT);

        BorderPane setting = new BorderPane();

        setting.setTop(menuHome);
        setting.setCenter(titleHomeGrid);
        setting.setBottom(infobarHome);
        setting.setPrefSize(this.WIDTH, this.HEIGHT);

        // newUserScene - Registration -----------------------------------------
        Pane popup = new Pane();
        popup.setPrefSize(this.SMALLWIDTH, this.SMALLHEIGHT);

        Label titleRegLabel = new Label("REKISTERÖITYMINEN");
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
        Label infoReg = new Label("            ");
        TextField fnameField = new TextField("            ");
        TextField lnameField = new TextField("            ");
        TextField emailField = new TextField("            ");
        TextField unameField = new TextField("            ");

        PasswordField newPword = new PasswordField();
        Button register = new Button("REKISTERÖIDY");
        Button backReg = new Button("TAKAISIN");
        Button endReg = new Button("LOPETA");

        VBox titleReg = new VBox();
        titleReg.getChildren().addAll(titleRegLabel, line1);
        titleReg.setPadding(new Insets(20, 20, 20, 20));
        titleReg.setSpacing(20);
        titleReg.setAlignment(Pos.TOP_CENTER);

        HBox infoRegBar = new HBox();
        infoRegBar.getChildren().add(infoReg);
        infoRegBar.setPadding(new Insets(10, 10, 10, 10));
        infoRegBar.setSpacing(10);
        infoRegBar.setAlignment(Pos.BOTTOM_CENTER);

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
        setpop.add(backReg, 1, 15);
        setpop.add(line3, 1, 16);
        setpop.add(endReg, 1, 17);
        setpop.add(line4, 1, 18);

        popup.getChildren().addAll(titleReg, setpop, infoReg);
        this.newUserScene = new Scene(popup, this.SMALLWIDTH, this.SMALLHEIGHT);

        // beginScene - contains the main menu ---------------------------------
        Label hello = new Label("Terve, " + this.userName + ", herkullista päivää!");

        Label titleBegin = new Label("Salaiset reseptit");
        HBox titlebarBegin = new HBox();
        titlebarBegin.getChildren().add(titleBegin);
        titlebarBegin.setPadding(new Insets(10, 10, 10, 10));
        titlebarBegin.setSpacing(10);
        titlebarBegin.setAlignment(Pos.BASELINE_CENTER);

        HBox beginMenu = new HBox();
        Label blancoBegin = new Label("                         ");
        Button endBegin = new Button("Lopeta");
        beginMenu.getChildren().addAll(hello, blancoBegin, endBegin);
        beginMenu.setPadding(new Insets(10, 10, 10, 10));
        beginMenu.setSpacing(10);
        beginMenu.setAlignment(Pos.TOP_LEFT);

        Button searchButton = new Button("Hae reseptejä");
        Button createButton = new Button("Luo uusi resepti");
        Button listButton = new Button("Listaa kaikki reseptit");
        Label one = new Label("1.");
        Label two = new Label("2.");
        Label three = new Label("3.");
        Label toRemove = new Label("RESEPTIN POISTO: Hae ensin resepti ja paina näkymässä Poista-painiketta");
        Label bpick = new Label("Valitse listalta reseptin numero");
        TextField bpickField = new TextField("    ");
        Button bpickButton = new Button("Hae");
        ListView<String> recipeListView = new ListView<String>();
        GridPane startOp = new GridPane();
        startOp.setAlignment(Pos.CENTER_LEFT);
        startOp.setVgap(10);
        startOp.setHgap(10);
        startOp.setPadding(new Insets(10, 10, 10, 10));
        startOp.add(titlebarBegin, 1, 1);
        startOp.add(blancoBegin, 2, 1);
        startOp.add(one, 0, 3);
        startOp.add(searchButton, 1, 3);
        startOp.add(two, 0, 4);
        startOp.add(createButton, 1, 4);
        startOp.add(three, 0, 5);
        startOp.add(listButton, 1, 5);
        startOp.add(bpick, 3, 3);
        startOp.add(bpickField, 3, 4);
        startOp.add(bpickButton, 4, 4);
        bpick.setVisible(false);
        bpickField.setVisible(false);
        bpickButton.setVisible(false);
        startOp.add(toRemove, 1, 6);
        startOp.add(recipeListView, 3, 7);
        recipeListView.setVisible(false);

        Label infoBegin = new Label("Valitse toiminto painamalla nappia");

        Pane begin = new Pane();
        begin.setPrefSize(this.WIDTH, this.HEIGHT);
        BorderPane setBegin = new BorderPane();
        setBegin.setTop(beginMenu);
        setBegin.setCenter(startOp);
        setBegin.setBottom(infoBegin);

        this.beginScene = new Scene(setBegin, this.WIDTH, this.HEIGHT);

        // searchScene ---------------------------------------------------------
        HBox searchMenu = new HBox();
        Label recipeSearch = new Label("Reseptihaku");
        Label blancoSearch = new Label("                         ");
        Button toStartSearch = new Button("Alkuun");
        Button endSearch = new Button("Lopeta");
        searchMenu.getChildren().addAll(recipeSearch, blancoSearch, toStartSearch, endSearch);
        searchMenu.setPadding(new Insets(10, 10, 10, 10));
        searchMenu.setSpacing(10);
        searchMenu.setAlignment(Pos.TOP_LEFT);
        Label infoSearch = new Label("");
        ListView<String> recipeView = new ListView<String>();

        Label rname = new Label("Reseptin nimi");
        Label rstuff = new Label("Raaka-ainehaku");
        Label rpick = new Label("Valitse listalta reseptin numero");
        TextField recipenameField = new TextField("                                        ");
        TextField stuffField = new TextField("                                        ");
        TextField pickField = new TextField("    ");
        Button searchByName = new Button("Hae");
        Button searchByStuff = new Button("Hae");
        Button pickButton = new Button("Hae");
        this.searchOp.setAlignment(Pos.CENTER_LEFT);
        this.searchOp.setVgap(10);
        this.searchOp.setHgap(10);
        this.searchOp.setPadding(new Insets(10, 10, 10, 10));
        this.searchOp.add(rname, 1, 1);
        this.searchOp.add(recipenameField, 2, 1);
        this.searchOp.add(searchByName, 3, 1);
        this.searchOp.add(rstuff, 1, 2);
        this.searchOp.add(stuffField, 2, 2);
        this.searchOp.add(searchByStuff, 3, 2);
        this.searchOp.add(rpick, 1, 3);
        this.searchOp.add(pickField, 2, 3);
        this.searchOp.add(pickButton, 3, 3);
        this.searchOp.add(recipeView, 4, 4);
        rpick.setVisible(false);
        pickField.setVisible(false);
        pickButton.setVisible(false);
        recipeView.setVisible(false);

        Pane searchPane = new Pane();
        searchPane.setPrefSize(this.WIDTH, this.HEIGHT);
        BorderPane setSearch = new BorderPane();
        setSearch.setTop(searchMenu);
        setSearch.setCenter(this.searchOp);
        setSearch.setBottom(this.infoSearch);
        searchPane.getChildren().add(setSearch);

        this.searchScene = new Scene(searchPane, this.WIDTH, this.HEIGHT);

        // createScene ---------------------------------------------------------
        Pane createPane = new Pane();
        createPane.setPrefSize(this.WIDTH, this.HEIGHT);

        HBox createMenu = new HBox();
        Label titleCreate = new Label("Reseptin lisäys");
        Label blancoCreate = new Label("                         ");
        Label blancoCreate2 = new Label("                         ");
        Button toStartCreate = new Button("Alkuun");
        Button endCreate = new Button("Lopeta");
        createMenu.getChildren().addAll(titleCreate, blancoCreate, toStartCreate, endCreate);

        Label progTitleHome = new Label("Salaiset reseptit");
        Label recipeName = new Label("Reseptin nimi");
        Label portions = new Label("Annosmäärä");
        Label category = new Label("Kategoria (ks. vaihtoehdot)");
        Label ingredient = new Label("Raaka-aine");
        Label amount = new Label("Määrä");
        Label guideline = new Label("Valmistusvaihe");
        Label categoriesTitle = new Label("Kategoriat");
        TextField nameField = new TextField("            ");
        TextField portionsField = new TextField("            ");
        TextField categoryField = new TextField("            ");
        TextField ingredientField = new TextField("            ");
        TextField amountField = new TextField("            ");
        TextField guideField = new TextField("            ");
        Button addRecipeDetails = new Button("Lisää tiedot");
        Button clearRecipeDetails = new Button("Tyhjennä");
        Button addStuff = new Button("Lisää aine");
        Button newStuff = new Button("Uusi aine");
        Button addPhase = new Button("Lisää vaihe");
        Button newPhase = new Button("Uusi vaihe");
        Button addRecipe = new Button("Lisää resepti");
        Button newRecipeButton = new Button("Uusi resepti");
        ListView<String> categories = new ListView<String>();
        ObservableList<String> categoryList = FXCollections.observableArrayList(this.book.getCategories());
        categories.setItems(categoryList);

        HBox createRecipeView = new HBox();
        GridPane createRecipe = new GridPane();
        createRecipe.add(progTitleHome, 1, 1);
        createRecipe.add(recipeName, 1, 2);
        createRecipe.add(nameField, 2, 2);
        createRecipe.add(portions, 1, 3);
        createRecipe.add(portionsField, 2, 3);
        createRecipe.add(category, 3, 3);
        createRecipe.add(categoryField, 4, 3);
        createRecipe.add(addRecipeDetails, 3, 4);
        createRecipe.add(clearRecipeDetails, 4, 4);
        createRecipe.add(ingredient, 1, 5);
        createRecipe.add(ingredientField, 2, 5);
        createRecipe.add(amount, 1, 6);
        createRecipe.add(amountField, 2, 6);
        createRecipe.add(addStuff, 3, 6);
        createRecipe.add(newStuff, 4, 6);
        createRecipe.add(guideline, 1, 7);
        createRecipe.add(guideField, 2, 7);
        createRecipe.add(addPhase, 3, 8);
        createRecipe.add(newPhase, 4, 8);
        createRecipe.add(addRecipe, 1, 9);
        createRecipe.add(newRecipeButton, 3, 9);
        createRecipe.add(blancoCreate2, 1, 10);
        createRecipe.add(categoriesTitle, 5, 0);

        createRecipeView.getChildren().addAll(createRecipe, categories);

        Label infoCreate = new Label("Täytä uuden reseptin perus-, raaka-aine- ja ohjetiedot");

        BorderPane setCreate = new BorderPane();
        setCreate.setTop(createMenu);
        setCreate.setCenter(createRecipeView);
        setCreate.setBottom(infoCreate);

        this.createScene = new Scene(setCreate, this.WIDTH, this.HEIGHT);

        // recipeScene ---------------------------------------------------------
        HBox recipeMenu = new HBox();
        Label recipename = new Label(this.recipeName);
        Label blancoMenuRecipe = new Label("                         ");
        Button backRecipe = new Button("Takaisin");
        Button toStartRecipe = new Button("Alkuun");
        Button endRecipe = new Button("Lopeta");
        recipeMenu.getChildren().addAll(recipename, blancoMenuRecipe, backRecipe, toStartRecipe, endRecipe);
        recipeMenu.setPadding(new Insets(10, 10, 10, 10));
        recipeMenu.setSpacing(10);
        recipeMenu.setAlignment(Pos.TOP_LEFT);

        Label stuff = new Label("Raaka-aineet");
        Label blancoDetailsRecipe = new Label("                    ");
        Label amountRecipe = new Label("Määrä");
        Label guide = new Label("Ohje - " + this.recipe.getPortionAmount() + " annosta");

        HBox optionBar = new HBox();
        Button modify = new Button("Muokkaa");
        Button remove = new Button("Poista");
        this.infoRecipe = new Label("Resepti: " + this.recipe);
        Button removeButton = new Button("POISTA");
        Button noRemoveButton = new Button("PERUUTA");
        removeButton.visibleProperty().setValue(false);
        noRemoveButton.visibleProperty().setValue(false);
        optionBar.getChildren().addAll(modify, remove, this.infoRecipe, removeButton, noRemoveButton);

        GridPane rDetails = new GridPane();
        rDetails.add(stuff, 1, 1);
        rDetails.add(blancoDetailsRecipe, 2, 1);
        rDetails.add(amountRecipe, 3, 1);
        rDetails.add(guide, 4, 1);
        rDetails.add(this.stuffDetails, 1, 3);
        rDetails.add(this.amountDetails, 3, 3);
        rDetails.add(this.guidelines, 4, 3);

        Pane recipePane = new Pane();
        recipePane.setPrefSize(this.WIDTH, this.HEIGHT);
        BorderPane setRecipe = new BorderPane();
        setRecipe.setTop(recipeMenu);
        setRecipe.setCenter(rDetails);
        setRecipe.setBottom(optionBar);

        recipePane.getChildren().add(setRecipe);
        this.recipeScene = new Scene(recipePane, this.WIDTH, this.HEIGHT);

        // Event Handlers ======================================================
        // Start: Login & registration -----------------------------------------
        // homeScene -> beginScene - Login action, if successful 
        loginButton.setOnAction((event) -> {
            String inputUsername = userField.getText().trim();
            String inputPassword = passField.getText().trim();
            try {
                Info<User, String> loginInfo = this.check.validate(inputUsername, inputPassword);
                User loginUser = loginInfo.getUser();
                String infotext = loginInfo.getText();
                if (loginUser != null) {
                    this.userName = loginUser.getFirstname();
                    hello.setText("Terve, " + this.userName + ", herkullista päivää!");
                    stage.setTitle("Secret Recipes - main menu");
                    stage.setScene(beginScene);
                } else {
                    infoHome.setText(infotext);
                }
            } catch (SQLException s) {
                infoHome.setText("Kirjautumisvirhe: " + s.getMessage());
            }
        });

        // homeScene -> newUserScene - To registration 
        registerButton.setOnAction((event) -> {
            stage.setTitle("Registration");
            stage.setScene(this.newUserScene);
        });

        // newUserScene -> beginScene - Registration action, adds a new user, and if successful, advances to main menu
        register.setOnAction((event) -> {

            String inFname = fnameField.getText().trim();
            String inLname = lnameField.getText().trim();
            String inEmail = emailField.getText().trim();
            String inUname = unameField.getText().trim();
            String inPword = newPword.getText().trim();
            infoReg.setText("");

            boolean valid = false;
            User newUser = new User(inFname, inLname, inEmail, inUname, inPword);
            valid = this.check.newValidate(newUser);
            System.out.println("The validity of filled fields: " + valid);
            if (valid) {
                User searchedUser = this.udbase.searchUser(inUname);
                if (searchedUser == null) {
                    System.out.println("The new user is not yet found from the register");
                }
            }

            if (valid && this.udbase.searchUser(inUname) == null) {
                boolean addingSucceeded = this.udbase.addUser(newUser);
                if (addingSucceeded) {
                    hello.setText("Terve, " + inUname + ", herkullista päivää!");
                    stage.close();
                    stage.setScene(this.beginScene);
                    stage.show();
                } else {
                    infoReg.setText("REKISTERÖITYMINEN EPÄONNISTUI!");
                }
            } else {
                infoReg.setText("TÄYTÄ KAIKKI KENTÄT!");
            }

            fnameField.setText("");
            lnameField.setText("");
            emailField.setText("");
            unameField.setText("");
            newPword.setText("");

        });

        // Main actions --------------------------------------------------------
        // beginScene -> searchScene - To recipe search
        searchButton.setOnAction((event) -> {
            this.infoSearch.setText("Valitse hakutapa: reseptin nimi tai raaka-aine");
            this.recipesToList(stage, this.infoSearch, recipeView);
            recipenameField.setText("                                        ");
            stuffField.setText("                                        ");
            pickField.setText("    ");
            stage.setTitle("Recipe search");
            stage.setScene(this.searchScene);
        });

        // searchScene: Recipe search action, search by recipe name
        searchByName.setOnAction((event) -> {
            this.recipeName = recipenameField.getText().trim().toLowerCase();
            this.setRecipeView(stage);
        });

        // searchScene: Recipe search action, search by ingredient
        searchByStuff.setOnAction((event) -> {
            String stuffName = stuffField.getText().trim().toLowerCase();
            this.recipes = this.dbase.searchRecipebyStuff(stuffName);

            if (this.recipes.keySet().isEmpty()) {
                this.infoSearch.setText("RAAKA-AINETTA EI LÖYDY! Hae toisella raaka-aineella tai vaihda hakutapaa.");
                recipeView.setVisible(false);
            } else {
                this.infoSearch.setText("VALITSE OIKEA RESEPTIN NUMERO! Katso listalta.");
                rpick.setVisible(true);
                pickField.setVisible(true);
                pickButton.setVisible(true);

                List<String> nameList = new ArrayList<>();
                for (Recipe recipe : this.recipes.values()) {
                    String row = recipe.getId() + " - " + recipe.getRecipeName();
                    nameList.add(row);
                }
                nameList.sort(String::compareToIgnoreCase);

                ObservableList<String> recipeList = FXCollections.observableArrayList(nameList);
                recipeView.setItems(recipeList);
                rpick.setVisible(true);
                pickField.setVisible(true);
                pickButton.setVisible(true);
                recipeView.setVisible(true);

                Map<String, String> idsAndNames = new HashMap<>();
                for (Recipe recipe : this.recipes.values()) {
                    String id = String.valueOf(recipe.getId());
                    String name = recipe.getRecipeName();
                    idsAndNames.put(id, name);
                }

                pickButton.setOnAction((e) -> {
                    this.infoSearch.setText("Tarkistetaan valinta..");
                    String inputID = pickField.getText().trim().toLowerCase();
                    if (!idsAndNames.keySet().contains(inputID)) {
                        this.infoSearch.setText("VALITSE OIKEA RESEPTIN NUMERO! Katso listalta.");
                    } else {
                        this.recipeName = idsAndNames.get(inputID);
                        this.setRecipeView(stage);
                        rpick.setVisible(false);
                        pickField.setVisible(false);
                        pickButton.setVisible(false);
                        recipeView.setVisible(false);
                        pickField.setText("");
                        this.infoSearch.setText("Valittu resepti on: " + this.recipeName);
                    }
                });
            }
        });

        // beginScene -> createScene - Recipe creation action
        //   phases of creating a new recipe:
        // 1. ask for name, portions and category -> create recipe details
        // 2. ask for an ingredient name and amount -> add a pair of ingredient name & amount to ingredient list
        // 3. store the ingredient list to recipe
        // 4. ask for a line of preparation instruction -> add a guideline to instruction list
        // 5. store the instruction list to recipe
        // 6. only when all the data has been entered, the data will be stored to the database (addRecipe) with the create recipe key ("Lisää resepti")
        createButton.setOnAction((event) -> {
            nameField.setText("            ");
            portionsField.setText("            ");
            categoryField.setText("            ");
            ingredientField.setText("            ");
            amountField.setText("            ");
            guideField.setText("            ");
            infoCreate.setText("Täytä uuden reseptin perus-, raaka-aine- ja ohjetiedot");
            stage.setTitle("Recipe creation");
            stage.setScene(this.createScene);
        });

        // recipeScene: Adds recipe's basic details, if successful
        addRecipeDetails.setOnAction((event) -> {
            String nameInput = nameField.getText().trim().toLowerCase();
            int portionsInput = 0;
            if (!portionsField.getText().trim().isEmpty()) {
                portionsInput = Integer.valueOf(portionsField.getText().trim());
            }
            String categoryInput = categoryField.getText().trim().toUpperCase();
            if (nameInput.isEmpty()) {
                infoCreate.setText("RESEPTIN NIMI PUUTTUU!");
            } else if (!(portionsInput > 0)) {
                infoCreate.setText("ANNOSMÄÄRÄN OLTAVA VÄHINTÄÄN 1!");
            } else if (categoryInput.isEmpty()) {
                infoCreate.setText("VALITSE OIKEA RESEPTIKATEGORIA!");
            } else {
                this.newRecipe = new Recipe(nameInput, portionsInput, categoryInput);
                infoCreate.setText("Luotu perustiedot reseptille: " + this.newRecipe);
            }
        });

        // recipeScene: Adds recipe’s ingredients, if successful
        addStuff.setOnAction((event) -> {
            if (this.newRecipe == null) {
                infoCreate.setText("LISÄÄ ENSIN RESEPTIN PERUSTIEDOT");
            } else {
                String stuffInput = ingredientField.getText().trim().toLowerCase();
                String amountInput = amountField.getText().trim().toLowerCase();
                if (stuffInput.isEmpty()) {
                    infoCreate.setText("RAAKA-AINE PUUTTUU!");
                } else if (amountInput.isEmpty()) {
                    infoCreate.setText("LISÄÄ RAAKA-AINEEN MÄÄRÄ!");
                } else {
                    this.newRecipe.setIngredient(stuffInput, amountInput);
                    infoCreate.setText("Reseptille lisätyt raaka-aineet: " + this.newRecipe.getIngredients());
                }
            }
        });

        // recipeScene: Adds recipe’s guideline, if successful 
        addPhase.setOnAction((event) -> {
            if (this.newRecipe == null) {
                infoCreate.setText("LISÄÄ ENSIN RESEPTIN PERUSTIEDOT!");
            } else if (this.newRecipe.getIngredientsAndAmounts().isEmpty()) {
                infoCreate.setText("LISÄÄ ENSIN RESEPTIN RAAKA-AINEET!");
            } else {
                String guideInput = guideField.getText().trim().toLowerCase();
                if (guideInput.isEmpty()) {
                    infoCreate.setText("OHJERIVI PUUTTUU!");
                } else {
                    this.newRecipe.setInstruction(guideInput);
                    infoCreate.setText("Reseptille lisätyt ohjerivit: " + this.newRecipe.getInstructions());
                }
            }
        });

        // recipeScene: Adds the whole recipe to recipe book (eventually to database), if successful
        addRecipe.setOnAction((event) -> {
            if (this.newRecipe == null) {
                infoCreate.setText("LISÄÄ ENSIN RESEPTIN PERUSTIEDOT!");
            } else if (this.newRecipe.getIngredientsAndAmounts().isEmpty()) {
                infoCreate.setText("LISÄÄ ENSIN RESEPTIN RAAKA-AINEET!");
            } else if (this.newRecipe.getInstructions().isEmpty()) {
                infoCreate.setText("LISÄÄ ENSIN OHJEISTUSTA!");
            } else {
                this.recipeCreated = this.dbase.addRecipe(this.newRecipe, this.newRecipe.getIngredientsAndAmounts(), this.newRecipe.getInstructions());
                infoCreate.setText("Resepti, " + this.newRecipe.getRecipeName() + " on luotu: " + this.recipeCreated);
            }
        });

        // beginScene: Lists all recipes
        listButton.setOnAction((event) -> {
            infoBegin.setText("Listataan kaikki reseptit..");
            this.recipesToList(stage, infoBegin, recipeListView);
            bpick.setVisible(true);
            bpickField.setVisible(true);
            bpickButton.setVisible(true);

            Map<String, String> idsAndNames = new HashMap<>();
            for (Recipe recipe : this.recipes.values()) {
                String id = String.valueOf(recipe.getId());
                String name = recipe.getRecipeName();
                idsAndNames.put(id, name);
            }
            bpickButton.setOnAction((e) -> {
                String inputID = bpickField.getText().trim().toLowerCase();
                if (!idsAndNames.keySet().contains(inputID)) {
                    infoBegin.setText("VALITSE OIKEA RESEPTIN NUMERO! Katso listalta.");
                } else {
                    this.recipeName = idsAndNames.get(inputID);
                    this.setRecipeView(stage);
                    bpick.setVisible(false);
                    bpickField.setVisible(false);
                    bpickButton.setVisible(false);
                    recipeListView.setVisible(false);
                    bpickField.setText("");
                    infoBegin.setText("");
                }
            });
        });
        
        // recipeScene: No actual modifying function yet, merely informing the user how to proceed, if wants to modify a recipe in this version
        modify.setOnAction((event) -> {
            infoRecipe.setText("Poista ensin vanha resepti ja luo sitten uusi. Reseptin muokkaustoiminto tulossa ohjelman 2 versioon.");
        });

        // recipeScene: Deleting the selected recipe, if successful
        remove.setOnAction((event) -> {
            int id = this.recipe.getId();
            infoRecipe.setText("VAROITUS: Haluatko todella poistaa reseptin " + id + " - " + this.recipe.getRecipeName() + "?");
            removeButton.visibleProperty().setValue(true);
            noRemoveButton.visibleProperty().setValue(true);
            removeButton.setOnAction((eh) -> {
                String name = this.recipe.getRecipeName();
                boolean success = this.dbase.deleteRecipe(this.recipe);
                if (success) {
                    this.recipes.remove(this.recipe); 
                    infoRecipe.setText("Resepti " + name + " on poistettu.");
                    removeButton.visibleProperty().setValue(false);
                    noRemoveButton.visibleProperty().setValue(false);
                } else {
                    infoRecipe.setText("Reseptin " + name + " poistaminen epäonnistui!");
                }
            });
            noRemoveButton.setOnAction((e) -> {
                infoRecipe.setText("");
                removeButton.visibleProperty().setValue(false);
                noRemoveButton.visibleProperty().setValue(false);
            });
        });

        // Clear fields --------------------------------------------------------
        // recipeScene: Empties the input fields of recipe details
        clearRecipeDetails.setOnAction((event) -> {
            nameField.setText("");
            portionsField.setText("");
            categoryField.setText("");
            this.newRecipe = null;
        });

        // recipeScene: Empties the input fields of recipe ingredients
        newStuff.setOnAction((event) -> {
            ingredientField.setText("");
            amountField.setText("");
        });

        // recipeScene: Empties the input fields of recipe instruction
        newPhase.setOnAction((event) -> {
            guideField.setText("");
        });

        // recipeScene: Empties all the input fields of the recipe and allows to start creating a new recipe
        newRecipeButton.setOnAction((event) -> {
            nameField.setText("            ");
            portionsField.setText("            ");
            categoryField.setText("            ");
            ingredientField.setText("            ");
            amountField.setText("            ");
            guideField.setText("            ");
            infoCreate.setText("Voit aloittaa uuden reseptin luomisen");
            this.newRecipe = null;
        });

        // Back to previous ----------------------------------------------------
        // newUserScene -> homeScene 
        backReg.setOnAction((event) -> {
            stage.setScene(this.homeScene);
        });

        // recipeScene -> searchScene 
        backRecipe.setOnAction((event) -> {
            this.infoSearch.setText("Valitse hakutapa: reseptin nimi tai raaka-aine");
            recipenameField.setText("                                        ");
            stuffField.setText("                                        ");
            pickField.setText("    ");
            stage.setTitle("Recipe search");
            stage.setScene(this.searchScene);
        });

        // searchScene -> beginScene 
        toStartSearch.setOnAction((event) -> {
            infoBegin.setText("Valitse toiminto painamalla nappia");
            stage.setTitle("Secret Recipes - main menu");
            stage.setScene(this.beginScene);
        });

        // recipeScene -> beginScene     
        toStartRecipe.setOnAction((event) -> {
            infoBegin.setText("Valitse toiminto painamalla nappia");
            stage.setTitle("Secret Recipes - main menu");
            stage.setScene(this.beginScene);
        });

        // createScene -> beginScene     
        toStartCreate.setOnAction((event) -> {
            infoBegin.setText("Valitse toiminto painamalla nappia");
            stage.setTitle("Secret Recipes - main menu");
            stage.setScene(this.beginScene);
        });

        // Closing application in different scenes -----------------------------
        endHome.setOnAction((event) -> {    // in homeScene
            stage.close();
        });

        endBegin.setOnAction((event) -> {   // in beginScene
            stage.close();
        });

        endReg.setOnAction((event) -> {     // in newUserScene
            stage.close();
        });

        endSearch.setOnAction((event) -> {  // in searchScene
            stage.close();
        });

        endRecipe.setOnAction((event) -> {  // in recipeScene
            stage.close();
        });

        endCreate.setOnAction((event) -> {  // in createScene
            stage.close();
        });

        // =====================================================================
        // homeScene - setting up the primary scene, the first window for the user
        screen.getChildren().add(setting);
        this.homeScene = new Scene(screen);
        stage.setTitle("Secret Recipes");
        stage.setScene(this.homeScene);
        stage.show();

    }

    // recipeView: Creates the view of all recipe's information: basic details, ingredients and preparation guidelines
    private void setRecipeView(Stage stage) {
        this.recipe = this.dbase.searchRecipebyName(this.recipeName);
        if (this.recipe == null) {
            this.infoSearch.setText("RESEPTIÄ EI LÖYDY! Hae uudelleen tai luo resepti.");
        } else {
            this.infoRecipe.setText("");
            this.infoSearch.setText("");
            stage.close();
            stage.setTitle("Recipe - " + this.recipe.getRecipeName());
            ObservableList<String> stuffList = FXCollections.observableArrayList(this.recipe.getIngredients());
            this.stuffDetails.setItems(stuffList);
            ObservableList<String> amountList = FXCollections.observableArrayList(this.recipe.getAmounts());
            this.amountDetails.setItems(amountList);
            ObservableList<String> instructionList = FXCollections.observableArrayList(this.recipe.getInstructions());
            this.guidelines.setItems(instructionList);
            stage.close();
            stage.setScene(this.recipeScene);
            stage.show();
        }
    }

    // beginScene & searchScene: Creates the name list of all recipes to certain views
    private void recipesToList(Stage stage, Label info, ListView<String> view) {
        this.recipes = null; 
        this.recipes = this.dbase.getAllRecipes();
        if (this.recipes.keySet().isEmpty()) {
            info.setText("RESEPTIKIRJA ON TYHJÄ!");
        } else {
            List<String> nameList = new ArrayList<>();
            for (Recipe recipe : this.recipes.values()) {
                String row = recipe.getId() + " - " + recipe.getRecipeName();
                nameList.add(row);
            }
            nameList.sort(String::compareToIgnoreCase);

            ObservableList<String> allRecipesList = FXCollections.observableArrayList(nameList);
            view.setItems(allRecipesList);
            view.setVisible(true);
        }
    }

    @Override
    public void stop() {
        System.out.println("Closing...");
    }

    public static void main(String[] args) {
        launch(args);
    }

}

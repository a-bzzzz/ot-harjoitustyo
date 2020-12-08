# Arkkitehtuurikuvaus


## Rakenne

Ohjelman luokat on jaettu kolmeen eri pakkaukseen, jotka - tai joiden luokat - kutsuvat toinen toisiaan:

<img src="https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri/Pakkaus_ja_luokkakaavio-Recipes.png" width="160">

Pakkausten sisällöt ovat seuraavat:
* _recipes.gui_     : JavaFX:llä toteutettu käyttöliittymä
* _recipes.domain_  : sovelluslogiikka
* _recipes.db_       : tietojen pysyväistallennuksen huolehtivat luokat, rajapinnat varsinaisiin SQLite-tietokantoihin


## Käyttöliittymä

Käyttöliittymä sisältää useita näkymiä:
* A   - ohjelman aloitussivu eli ikkuna, joka avautuu sovelluksen käynnistyessä
* 0   - alkuvalinnat eli ensimmäinen valikkotaso
* 1   - reseptihaku eli ikkuna, jossa käyttäjä hakee reseptiä tietyillä hakutekijöillä
* 1.1 - reseptinäkymä, esittelee reseptin sisällön
* 1.2 - reseptilistaus eli ikkuna, johon listataan useamman reseptin nimi, esim. kun hakutekijöillä löytyy useampi resepti -> TULOSSA
* 2   - reseptin lisäys eli ikkuna, jossa käyttäjä syöttää reseptin tietoja ja luo uuden reseptin
* 3   - reseptin muokkaus eli ikkuna, jossa käyttäjä voi muokata reseptin tietoja, tai poistaa reseptin -> TULOSSA
* A.1 - rekisteröityminen eli popup-ikkuna, jossa uusi käyttäjä voi luoda itselleen käyttäjätunnukset ja tietonsa järjestelmään eli rekisteröityä

Lisäksi jatkossa ohjelmaan on mahdollista lisätä vaikkapa muita popup-ikkunoita, kuten:
* A.2 - kirjautuminen eli oma pikkuikkunansa pelkästään kirjautumista varten (jos on tarpeen)
* A.3 - salasana-tukipyyntö eli pikkuikkuna, jonka kautta loppukäyttäjä voi pyytää apua pääkäyttäjältä (admin) käyttäjätunnusten palauttamisen kanssa

Käyttöliittymäikkunat on toteutettu JavaFX:n Scene-olioina. Käyttöliittymän koodi löytyy _gui_-pakkauksen _RecipesGUI_-luokasta.

_RecipesGUI_-luokka puolestaan kutsuu muiden pakkausten luokkia ja käyttää yleensä apuna kutsuissa käyttäjän syöttämiä parametreja.


## Sovelluslogiikka

Sovelluksen looginen toteutus on seuraavissa luokissa:
* Main        - käynnistää sovelluksen eli kutsuu käyttöliittymäluokkaa, ei tee muuta
* RecipeBook  - kerää reseptikirjan perustiedot yhteen eli käytettävissä olevat reseptit sekä reseptien kategoriat (luokat)
* Recipe      - yhden reseptin tiedot: reseptin nimi, annosmäärä, kategoria, raaka-aineet ja ohjerivit
* User        - käyttäjän tiedot: etunimi, sukunimi, sähköpostiosoite, käyttäjätunnus ja salasana
* Validation  - käyttäjän kirjautumistiedot sekä uuden käyttäjän rekisteröitymistiedot tarkistava luokka
* Info        - pieni apuluokka käyttäjätietojen tarkistuksessa (Validation-luokan "apuri")


## Tietojen pysyväistallennus

Sovelluksen pysyviksi tarkoitetut tiedot, reseptit ja niiden raaka-aineet ja ohjerivit sekä käyttäjätiedot, talletetaan SQLite-tietokantoihin ja niiden tauluihin.

### Tietokannat ja niiden taulukot

| UsersDatabase | RecipesDatabase |
| :-------------| :---------------|
| Users         | Recipes         |
|               | Stuff           |
|               | Guidance        |

TODO: KORJAA ALLA OLEVA!...............................

Pakkauksen _todoapp.dao_ luokat _FileTodoDao_ ja _FileUserDao_ huolehtivat tietojen tallettamisesta tiedostoihin.

Luokat noudattavat [Data Access Object](https://en.wikipedia.org/wiki/Data_access_object) -suunnittelumallia ja ne on tarvittaessa mahdollista korvata uusilla toteutuksilla, jos sovelluksen datan talletustapaa päätetään vaihtaa. Luokat onkin eristetty rajapintojen _TodoDao_ ja _UserDao_ taakse ja sovelluslogiikka ei käytä luokkia suoraan.

Sovelluslogiikan testauksessa hyödynnetäänkin tätä siten, että testeissä käytetään tiedostoon tallentavien DAO-olioiden sijaan keskusmuistiin tallentavia toteutuksia.



### Päätoiminnallisuudet

Kuvataan seuraavaksi sovelluksen toimintalogiikka muutaman päätoiminnallisuuden osalta sekvenssikaaviona.

#### käyttäjän kirjaantuminen

Kun kirjautumisnäkymässä on syötekenttään kirjoitettu käyttäjätunnus ja klikataan painiketta _loginButton_ etenee sovelluksen kontrolli seuraavasti:

<img src="https://raw.githubusercontent.com/mluukkai/OtmTodoApp/master/dokumentaatio/kuvat/a-4b.png" width="750">

Painikkeen painamiseen reagoiva [tapahtumankäsittelijä](https://github.com/mluukkai/OtmTodoApp/blob/master/src/main/java/todoapp/ui/TodoUi.java#L92) kutsuu sovelluslogiikan _appService_ metodia [login](https://github.com/mluukkai/OtmTodoApp/blob/master/src/main/java/todoapp/domain/TodoService.java#L73) antaen parametriksi kirjautuneen käyttäjätunnuksen. Sovelluslogiikka selvittää _userDao_:n avulla onko käyttäjätunnus olemassa. Jos on, eli kirjautuminen onnistuu, on seurauksena se että käyttöliittymä vaihtaa näkymäksi _todoScenen_, eli sovelluksen varsinaisen päänäkymän ja renderöi näkymään kirjautuneen käyttäjän todot eli tekemättömät työt.

#### uuden käyttäjän luominen

Kun uuden käyttäjän luomisnäkymässä on syötetty käyttäjätunnus joka ei ole jo käytössä sekä nimi ja klikataan painiketta _createUser_ etenee sovelluksen kontrolli seuraavasti:

<img src="https://raw.githubusercontent.com/mluukkai/OtmTodoApp/master/dokumentaatio/kuvat/a-5.png" width="750">

[Tapahtumakäsittelijä](https://github.com/mluukkai/OtmTodoApp/blob/master/src/main/java/todoapp/ui/TodoUi.java#L138) kutsuu sovelluslogiikan metodia [createUser](https://github.com/mluukkai/OtmTodoApp/blob/master/src/main/java/todoapp/domain/TodoService.java#L111) antaen parametriksi luotavan käyttäjän tiedot. Sovelluslogiikka selvittää _userDao_:n avulla onko käyttäjätunnus olemassa. Jos ei, eli uuden käyttäjän luominen on mahdollista, luo sovelluslogiikka _User_-olion ja tallettaa sen kutsumalla _userDao_:n metodia _create_. Tästä seurauksena on se, että käyttöliittymä vaihtaa näkymäksi _loginScenen_ eli kirjautumisnäkymän.

#### Todon luominen

Uuden todon luovan _createTodo_-painikkeen klikkaamisen jälkeen sovelluksen kontrolli eteneeseuraavasti:

<img src="https://raw.githubusercontent.com/mluukkai/OtmTodoApp/master/dokumentaatio/kuvat/a-6.png" width="750">

[Tapahtumakäsittelijä](https://github.com/mluukkai/OtmTodoApp/blob/master/src/main/java/todoapp/ui/TodoUi.java#L193) kutsuu sovelluslogiikan metodia [createTodo](https://github.com/mluukkai/OtmTodoApp/blob/master/src/main/java/todoapp/domain/TodoService.java#L29) antaen parametriksi luotavan työn tiedot. Sovelluslogiikka luo uuden _Todo_-olion ja 
 tallettaa sen kutsumalla _todoDao_:n metodia _create_. Tästä seurauksena on se, että käyttöliittymä päivittää näytettävät todot kutsumalla omaa metodiaan _redrawTodolist_.

#### Muut toiminnallisuudet

Sama periaate toistoo sovelluksen kaikissa toiminnallisuuksissa, käyttöliittymän tapahtumakäsittelijä kutsuu sopivaa sovelluslogiikan metodia, sovelluslogiikka päivittää todojen tai kirjautuneen käyttäjän tilaa. Kontrollin palatessa käyttäliittymään, päivitetään tarvittaessa todojen lista sekä aktiivinen näkyvä.

## Ohjelman rakenteeseen jääneet heikkoudet

### käyttöliittymä

Graafinen käyttöliittymä on toteutettu määrittelemällä lähes koko käyttöliittymän struktuuri luokan _TodoUi_ metodissa _start_. Ainakin kaikkien sovelluksen kolmen päänäkymän rakentava koodi olisi syytä erottaa omiksi metodeikseen tai kenties luokiksi. Muuttujien nimentää olisi myös syytä parantaa. 

Käyttöliittymän rakenteen ohjelmallinen määrittely kannattaisi kenties korvata FXML-määrittelyllä, tällöin sovelluslogiikan ja käyttöliittymän tapahtumankäsittelijöiden välinen kommunikointi ei hukkuisi GUI-elementtejä rakentavan koodin sekaan.

### DAO-luokat

FileDao-toteutuksiin on jäänyt paljon toisteista koodia, molemmat mm. sisältävät hyvin samankaltaisen logiikan tiedoston lukemiseen ja tidostoon kirjoittamiseen. Tämä koodi olisi syytä eroittaa omaan luokkaansa.

DAO-toteutusten automaattiset testit tekisivät refaktoroinnin suhteellisen riskittömäksi.

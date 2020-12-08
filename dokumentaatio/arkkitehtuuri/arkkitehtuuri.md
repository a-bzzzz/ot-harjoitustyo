# Arkkitehtuurikuvaus


## Rakenne

Ohjelman luokat on jaettu kolmeen eri pakkaukseen, jotka - tai joiden luokat - kutsuvat toinen toisiaan:

<img src="https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri/Pakkaus_ja_luokkakaavio-Recipes.png" width="750">

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
* *Main*        - käynnistää sovelluksen eli kutsuu käyttöliittymäluokkaa, ei tee muuta
* *RecipeBook*  - kerää reseptikirjan perustiedot yhteen eli käytettävissä olevat reseptit sekä reseptien kategoriat (luokat)
* *Recipe*      - yhden reseptin tiedot: reseptin nimi, annosmäärä, kategoria, raaka-aineet ja ohjerivit
* *User*        - käyttäjän tiedot: etunimi, sukunimi, sähköpostiosoite, käyttäjätunnus ja salasana
* *Validation*  - käyttäjän kirjautumistiedot sekä uuden käyttäjän rekisteröitymistiedot tarkistava luokka
* *Info*        - pieni apuluokka käyttäjätietojen tarkistuksessa (Validation-luokan "apuri")


## Tietojen pysyväistallennus

Sovelluksen pysyviksi tarkoitetut tiedot, reseptit ja niiden raaka-aineet ja ohjerivit sekä käyttäjätiedot, talletetaan SQLite-tietokantoihin ja niiden tauluihin.

### Tietokannat ja niiden taulukot

| UsersDatabase | RecipesDatabase |
| :-------------| :---------------|
| Users         | Recipes         |
|               | Stuff           |
|               | Guidance        |


Pakkauksen _recipes.db_ luokat _RecipesDB_, _UsersDB_ ja _UsersInterface_ huolehtivat tietojen tallettamisesta tiedostoihin. 


### Päätoiminnallisuudet

Sekvenssikaavioita ja/tai sanallisia kuvauksia sovelluslogiikan päätoiminnallisuuksista:

#### käyttäjän kirjaantuminen

Aloitusnäkymässä käyttäjä kirjoittaa syotekenttiin käyttäjätunnuksensa ja salasanansa. Sen jälkeen klikataan  _Kirjaudu_-painiketta. Jos sekä käyttäjätunnus ja salasana ovat oikeat eli jo luotu järjestelmään, etenee sovelluksen kontrolli seuraavasti:

<img src="https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri/Sekvenssikaavio_Recipes_login.png" width="750">

#### Reseptin hakeminen reseptin nimellä

Reseptihaku-näkymässä käyttäjä kirjoittaa syötekenttään reseptin nimen. Sen jälkeen painetaan _Hae_-painiketta. Jos resepti löytyy haetulla nimellä tietokannasta, siirrytään reseptinäkymään, jossa näkyvät reseptin tiedot.

<img src="https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri/Sekvenssikaavio_Recipes-searchByName.png" width="750">

#### Reseptin lisääminen

Käyttäjä lisää reseptin lisäys -näkymässä vaiheittain reseptin tiedot:
1. reseptin nimi, annosmäärä ja kategoria
2. reseptin raaka-aine ja sen määrä reseptissä lisätään jokainen raaka-aine (määrineen) kerrallaan
3. reseptin valmistusohjeen vaiheet rivi kerrallaan
4. lopuksi reseptin tiedot lisätään reseptikirjaan, käytännössä tietokantaan



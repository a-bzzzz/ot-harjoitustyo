# Arkkitehtuurikuvaus


## Rakenne

Ohjelman luokat on jaettu kolmeen eri pakkaukseen, jotka - tai joiden luokat - kutsuvat toinen toisiaan:

<img src="https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri/Pakkaus_ja_luokkakaavio-Recipes.png" width="750">

Pakkausten sisällöt ovat seuraavat:
* _recipes.gui_     : JavaFX:llä toteutettu käyttöliittymä
* _recipes.domain_  : sovelluslogiikka
* _recipes.db_       : tietojen pysyväistallennuksen huolehtivat luokat, rajapinnat varsinaisiin SQLite-tietokantoihin


## Käyttöliittymä

Käyttöliittymä sisältää 6 erillistä näkymää, lisänä reseptilistanäkymä alkuvalikossa ja reseptihaussa:
* A   - ohjelman aloitussivu: ikkuna, joka avautuu sovelluksen käynnistyessä, ja josta voi joko kirjautua sisään ohjelmaan tai siirtyä rekisteröitymisnäkymään
* A.1 - rekisteröityminen: _popup_-ikkuna, jossa uusi käyttäjä voi luoda itselleen käyttäjätunnukset ja tietonsa järjestelmään
* 0   - alkuvalikko: sisältää päätason valikon
* 1   - reseptihaku: ikkuna, jossa käyttäjä hakee reseptiä tietyillä hakutekijöillä (tässä ohjelmaversiossa joko reseptin nimellä tai raaka-aineella)
* 1.1 - reseptilistaus: apunäkymä, johon listataan kaikkien reseptikirjaan tallennettujen reseptien nimet (mahd. tulevissa versioissa myös esim. kun hakutekijöillä löytyy useampi resepti, ts. tietty valinta kaikista resepteistä)
* 2   - uusi resepti: ikkuna, jossa käyttäjä syöttää reseptin tietoja ja luo uuden reseptin
* 3   - reseptinäkymä, esittelee reseptin sisällön, NB: reseptin voi poistaa tässä näkymässä

Jatkokehityksessä on tuleviin ohjelmaversioihin mahdollista lisätä muita näkymiä, "popup-ikkunoita" tai lisänäkymiä, kuten:
* reseptin muokkaus: ikkuna, jossa käyttäjä voi muokata reseptin tietoja, tai poistaa reseptin
* kategoriahaku: reseptin haku kategorian perusteella
* kirjautuminen: oma pikkuikkunansa pelkästään kirjautumista varten (jos on tarpeen)
* salasana-tukipyyntö: pikkuikkuna, jonka kautta loppukäyttäjä voi pyytää apua pääkäyttäjältä (_admin_) käyttäjätunnusten palauttamisen kanssa
* muita mahdollisia näkymiä riippuen tulevista lisätoiminnoista

Käyttöliittymäikkunat on toteutettu JavaFX:n Scene-olioina. Käyttöliittymän koodi löytyy _gui_-pakkauksen _RecipesGUI_-luokasta.

_RecipesGUI_-luokka puolestaan kutsuu muiden pakkausten luokkia ja käyttää yleensä apuna kutsuissa käyttäjän syöttämiä parametreja.

Katso tarkemmin erillisistä käyttöliittymädokumenteista:
* [GUI: ikkunat ja yhteyskaavio](https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/GUI/GUI-ikkunat.pdf)
* [GUI: rakenne](https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/GUI/GUI-rakenne.md)
* [GUI: kaavio](https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/GUI/GUI_SR_kaavio.png)


## Sovelluslogiikka

Sovelluksen looginen toteutus on seuraavissa luokissa:
* *Main*        - käynnistää sovelluksen eli kutsuu käyttöliittymäluokkaa, ei tee muuta
* *RecipeBook*  - kerää reseptikirjan perustiedot yhteen eli käytettävissä olevat reseptit sekä reseptien kategoriat (luokat)
* *Recipe*      - yhden reseptin tiedot: reseptin nimi, annosmäärä, kategoria, raaka-aineet ja ohjerivit
* *User*        - käyttäjän tiedot: etunimi, sukunimi, sähköpostiosoite, käyttäjätunnus ja salasana
* *Validation*  - käyttäjän kirjautumistiedot sekä uuden käyttäjän rekisteröitymistiedot tarkistava luokka
* *Info*        - pieni apuluokka käyttäjätietojen tarkistuksessa (Validation-luokan "apuri")

Katso myös yllä olevasta kuvasta pakkaus _domain_, sekä tarkempi listaus [ohjelman luokista ja niiden metodeista]() (pl. testiluokat ja niiden metodit).


## Tietojen pysyväistallennus

Sovelluksen pysyviksi tarkoitetut tiedot, reseptit ja niiden raaka-aineet ja ohjerivit sekä käyttäjätiedot, talletetaan SQLite-tietokantoihin ja niiden tauluihin.

### Tietokannat ja niiden taulukot

| UsersDatabase | RecipesDatabase |
| :-------------| :---------------|
| Users         | Recipes         |
|               | Stuff           |
|               | Guidance        |


Pakkauksen _recipes.db_ luokat _RecipesDB_, _UsersDB_ ja _UsersInterface_ huolehtivat tietojen tallettamisesta edellä näkyviin tietokantoihin, sekä tietojen hakemisesta näistä tietokannoista. 


## Päätoiminnallisuudet

Sekvenssikaavioita ja/tai sanallisia kuvauksia sovelluslogiikan päätoiminnallisuuksista:

### Käyttäjän kirjaantuminen

Aloitusnäkymässä käyttäjä kirjoittaa syotekenttiin käyttäjätunnuksensa ja salasanansa. Sen jälkeen klikataan  _Kirjaudu_-painiketta. Jos sekä käyttäjätunnus ja salasana ovat oikeat eli jo luotu järjestelmään, etenee sovelluksen kontrolli seuraavasti:

<img src="https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri/Sekvenssikaavio_Recipes_login.png" width="750">

### Reseptin hakeminen reseptin nimellä

Reseptihaku-näkymässä käyttäjä kirjoittaa syötekenttään reseptin nimen. Sen jälkeen painetaan _Hae_-painiketta. Jos resepti löytyy haetulla nimellä tietokannasta, siirrytään reseptinäkymään, jossa näkyvät reseptin tiedot.

<img src="https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri/Sekvenssikaavio_Recipes-searchByName.png" width="750">

### Reseptin lisääminen

Käyttäjä lisää reseptin lisäys -näkymässä vaiheittain reseptin tiedot:
1. reseptin nimi, annosmäärä ja kategoria
2. reseptin raaka-aine ja sen määrä reseptissä lisätään jokainen raaka-aine (määrineen) kerrallaan
3. reseptin valmistusohjeen vaiheet rivi kerrallaan
4. lopuksi reseptin tiedot lisätään reseptikirjaan, käytännössä tietokantaan



# Testausdokumentti

Ohjelman testausta on suoritettu seuraavasti:

* _domain_-pakkauksen luokkia on testattu automaattisilla yksikkötesteillä (_JUnit_).
* _gui_- ja _db_-pakkausten luokkia on testattu sekä ohjelmaan koodatuilla tulostuskomennoilla (jotka on nyt poistettu koodista checkstyle-tarkastusten vuoksi) että manuaalisella toimintojen testauksella, myös virheellisillä syötteillä.
* Lisäksi ohjelmassa on soveltuvin osin otettu kiinni poikkeusten heitot, muutettu ne mahdollisimman järkeviksi tulosteiksi, jotka palautetaan käyttäjälle nähtäväksi käyttöliittymän infokentissä.


## Yksikkötestaus


### Sovelluslogiikka <-> DB-luokat

Käyttäjätietojen testausta varten testipakkaukseen on luotu _test.db_-pakkaus ja sinne [FakeUsersDB](https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/SalaisetReseptit/src/test/java/test/db/FakeUsersDB.java)-testiluokka, jota ValidationTest-luokan yksikkötestit hyödyntävät.

Nämä varsinaiset testiluokat sijaitsevat _recipes.test_-pakkauksessa:

* [RecipeBookTest](https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/SalaisetReseptit/src/test/java/recipes/test/RecipeBookTest.java)

* [RecipeTest](https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/SalaisetReseptit/src/test/java/recipes/test/RecipeTest.java)

* [UserTest](https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/SalaisetReseptit/src/test/java/recipes/test/UserTest.java)

* [ValidationTest](https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/SalaisetReseptit/src/test/java/recipes/test/ValidationTest.java)


### Testauskattavuus

Käyttöliittymä (_RecipesGUI_) ja tietokantarajapinnat (_UsersDB_ ja _RecipesDB_) poislukien sovelluksen testauksen 

* rivikattavuus on 100 %

* haarautumakattavuus 100 %

Katso tarkemmin [testikattavuusraportista](https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/testaus/testikattavuus_Recipes.pdf).


## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti (ks. tämän dokumentin alusta).


### Asennus ja konfigurointi

Sovellus on ladattu jar-pakettina Linux Fedora 32 -työpöytäympäristöön, joka toimii MacBook pro -tietokoneella. 
Kehitysympäristön (Chubbli/Ubuntu 20.04.1 LTS) lisäksi sovellusta on testattu manuaalisesti em. ulkoisessa ympäristössä. 

Erityisesti käyttöliittymän ikkunat ovat näkyneet paremmin juuri Fedoralla kuin Ubuntulla. 

Koska sovellus luo ohjelman käynnistyessa tarvittavat tietokannat (sekä alustavat käyttäjätiedot ja ensimmäisen reseptin) jar-pakkauksen sisältävään kansioon,
ei testauksessa ole havaittu loppuversiossa ongelmia tietokantayhteyksien kanssa. Tietokantojen toimintaa on testattu sekä käynnistämällä ohjelma siten,
että tietokantoja ei ole vielä luotu, että siten, että tietokannat on jo luotu valmiiksi. 
Lopullisessa versiossa ei ole ilmennyt poikkeavuuksia tietokantojen käytössä näissä tapauksissa.


### Toiminnallisuudet

[Määrittelydokumentissa](https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md) mainitut toiminnallisuudet (release 1.0) on testattu ja todettu toimiviksi, myös virheellisillä tai puuttellisilla syötteillä.
Mikäli kuitenkin huomaat sovellusta käyttäessäsi korjaustarpeita (ja todennäköisesti niitä esiintyykin), kerrothan niistä esim. luomalla projektille [issuen](https://github.com/a-bzzzz/ot-harjoitustyo/issues). 

## Sovellukseen jääneet laatuongelmat

Pyydän virkeämmillä silmillä varustettuja henkilöitä "ilmiantamaan" sovellukseen jääneet laatuongelmat, epäloogisuudet, virhetilanteet - tai muut olennaiset kehitystarpeet:
[issues](https://github.com/a-bzzzz/ot-harjoitustyo/issues). Kiitos!

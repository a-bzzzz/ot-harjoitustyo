# Laatudokumentti

Ohjelman laatua on pyritty ylläpitämään mm. seuraavilla keinoilla

* korjaamalla kaikki huomatut ohjelman kaatavat bugit

* poistamalla kaikki huomatut poikkeusten tulostukset (myös tapauksista, jolloin ohjelma ei kaadu)

* tarkistamalla syöttökenttien toimintoja virheellisillä syötteillä

* tarkistamalla ohjelman toimintoja välitulostuksilla

* nimeämällä luokkia, metodeja, muuttujia ja muita ohjelman osia mahdollisimman kuvaavasti ja selkeästi (englanniksi)

* jakamalla eri pakkauksiin sovelluksen käyttöliittymä, logiikka ja tietokantarajapinnat (default package ei ole käytössä), mutta mahdollisesti ohjelman laajentuessa käyttäjiin liittyvät luokat voisi siirtää omaan pakkaukseensa

* jakamalla eri luokille omat vastuunsa

* yksikkötestejä on pyritty luomaan mahdollisimman monipuolisesti domain-pakkauksen luokille (testejä ei ole generoitu)

* vähentämällä toisteista koodia yleiskäyttöisempien pikkumetodien avulla, missä se on ollut mahdollista 

* edellä mainittuun liittyen, pyritty pitämään etenkin domain-pakkauksen metodit sopivan lyhyinä


Kuitenkin sekä UsersDB- että RecipesDB-luokissa on muutama metodi, joiden hajoittaminen
pienempiin osiin ei olisi ollut tarkoituksen mukaista, vaan olisi vain lisännyt koodin määrää.

Nämä "ylipitkät" metodit näkyvät seuraavasta Checkstyle-raportista:

<img src="https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/laatu/CheckstyleResults.png" width="600">




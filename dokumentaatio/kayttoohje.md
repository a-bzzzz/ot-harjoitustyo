# Käyttöohje

Ohjelmatiedoston voi ladata joko 
valitsemalla sopiva pakkaus [releases](https://github.com/a-bzzzz/ot-harjoitustyo/releases)
tai suoraan jar-paketti [recipes.jar](https://github.com/a-bzzzz/ot-harjoitustyo/releases/download/viikko5/recipes.jar)

## Konfigurointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _Recipes-1.0-SNAPSHOT.jar_


## Ohjelman käynnistäminen

Ohjelman voi suorittaa komennolla

```
java -jar jartiedoston_nimi.jar
```

"Viikko 7" -releasen (1.0) kautta haetun pakkauksen nimi on _recipes.jar_, joten sen voi suorittaa komennolla

```
java -jar recipes.jar
```

Jar-tiedosto on mahdollista suorittaa millä tahansa koneella, olettaen että koneelle on asennettu Javan versio 1.8


## Kirjautuminen

Sovellus käynnistyy aloitusnäkymään:

<img src="https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/A_aloitussivu.png" width="400">

Sovellukseen kirjaudutaan kirjoittamalla syötekenttiin käyttäjätunnus ja salasana, jotka on jo luotu, ja painamalla _Kirjaudu_.

## Uuden käyttäjän luominen

Aloitusnäkymän kautta ohjelman uusi käyttäjä voi siirtyä rekisteröitymisnäkymään panikkeella _Rekisteröidy_ ja luoda siellä itselleen käyttäjätunnukset.

Uuden käyttäjän tulee täyttää kaikki seuraavat kentät ja painaa lopuksi _REKISTERÖIDY_-nappulaa:

* etunimi
* sukunimi
* sähköpostiosoite
* käyttäjätunnus
* salasana

<img src="https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/A1_rekisteroi.png" width="400">

Jos käyttäjän luominen onnistuu, tapahtuu samalla järjestelmään sisäänkirjautuminen ja siirrytään alkuvalinnat-näkymään.

<img src="https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/0_alkuvalikko.png" width="400">

## Reseptien hakeminen 

<img src="https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/1_reseptihaku.png" width="400">

## Reseptien luominen 

<img src="https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/2_uusiresepti.png" width="400">

## Reseptien listaaminen 

<img src="https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/1.1_reseptilistaus.png" width="400">

## Reseptinäkymä ja reseptien poisto 

<img src="https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/3_reseptinakyma.png" width="400">



Klikkaamalla näkymän oikean ylänurkan painiketta _Lopeta_ käyttäjä kirjautuu ulos sovelluksesta ja kyseinen ikkuna sulkeutuu.

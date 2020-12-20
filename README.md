# **SALAISET RESEPTIT**

## Ohjelmistotekniikka, harjoitustyö, syksy 2020

--------------------------------------------------------------

Sovelluksen avulla loppukäyttäjä voi lisätä, hakea (reseptin nimellä ja raaka-aineella), listata ja poistaa ruokaohjeita eli ruokareseptejään käyttäjätunnuksilla suojatussa ympäristössä. Aluksi loppukäyttäjä voi luoda itselleen käyttäjätunnukset järjestelmään.

Sovellusta voi kokeilla ilman rekisteröitymistä seuraavilla valmiiksi järjestelmään luoduilla tunnuksilla ja reseptillä:

Käyttäjätunnus:   admin

Salasana:         secret

Reseptin nimi:    kaakao

Myös oman käyttäjätunnuksen voi luoda ja kirjautua sillä sovellukseen. Rekisteröitymistä varten paina ensin ensimmäisessä näkymässä _Rekisteröidy_-painiketta ja täytä tarvittavat tiedot avautuvassa ikkunassa.

Käyttäjän kirjautumisen tai rekisteröitymisen (ja kirjautumisen) jälkeen käyttäjä pääsee ohjelman aloitusvalikkoon, jossa toiminnon voi valita painamalla näkymässä olevia painikkeita. Useimmista näkymistä voi navigoida takaisin päin joko _Alkuun_ tai _Takaisin_ -painikkeilla, tai ohjelman voi lopettaa kokonaan _Lopeta_-painikkeella. Katso tarkemmin [käyttöohjeesta](https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md).

Ohjelman nykyisestä versiosta puuttuu reseptin muokkausmahdollisuus (paitsi poiston ja lisäyksen kautta) sekä muita mahdollisia reseptin hakutapoja, kuten esim. haku reseptin nimen osalla tai reseptin kategorialla. Ohjelma kuitenkin listaa kaikki saatavilla olevat reseptit käyttäjän näkyville, mikä helpottaa huomattavasti reseptien hakua.


## Dokumentaatio

[Käyttöohje](https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri/arkkitehtuuri.md)

[Testausdokumentaatio](https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/testaus/testaus.md)

[Laatudokumentaatio](https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/laatu/laatu.md)

[Työaikakirjanpito](https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/tunnit/tyoaikakirjanpito.pdf)


## Releaset

[Viikko 5](https://github.com/a-bzzzz/ot-harjoitustyo/releases/tag/viikko5)

[Viikko 6](https://github.com/a-bzzzz/ot-harjoitustyo/releases/tag/viikko6)

[Loppupalautus](https://github.com/a-bzzzz/ot-harjoitustyo/releases/tag/loppupalautus)


## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _Recipes-1.0-SNAPSHOT.jar_

Ohjelman voi suorittaa komennolla

```
java -jar jartiedoston_nimi.jar
```

"loppupalautus" -releasen (1.0) kautta haetun pakkauksen nimi on _recipes.jar_, joten sen voi suorittaa komennolla

```
java -jar recipes.jar
```

Jar-tiedosto on mahdollista suorittaa millä tahansa koneella, olettaen että koneelle on asennettu Javan versio 1.8



### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_


### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/SalaisetReseptit/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_




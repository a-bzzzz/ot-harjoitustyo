# **SALAISET RESEPTIT**

## Ohjelmistotekniikka, harjoitustyö, syksy 2020

--------------------------------------------------------------

Sovelluksen avulla loppukäyttäjä voi lisätä, ylläpitää, hakea ja listata ja poistaa ruokaohjeita eli ruokareseptejään käyttäjätunnuksilla suojatussa ympäristössä. Aluksi loppukäyttäjä voi luoda itselleen käyttäjätunnukset järjestelmään.

Sovellus on keskeneräinen (alkuvaiheessa), mutta sovellusta voi kokeilla seuraavilla valmiiksi järjestelmään luoduilla tunnuksilla ja reseptillä:

Käyttäjätunnus:   admin

Salasana:         secret

Reseptin nimi:    kaakao

Myös oman käyttäjätunnuksen voi luoda ja kirjautua sillä sovellukseen.

Käyttäjän kirjautumisen ja rekisteröitymisen lisäksi "Viikko 5" -releasessa toimii navigointi muutamissa ikkunoissa sekä reseptin hakeminen reseptin nimen perusteella.
Tulossa mm. reseptin lisäys ja muokkaustoiminnot, sekä toivottavasti myös lisää hakutapoja, kuten reseptin hakeminen raaka-aineella, kategorialla. Ts. hakuperusteiden mukainen lista, jolta voi valita tietyn reseptin tiedot näkymään. Mahdollisesti osa toiminnoista jää jatkokehitystarpeisiin.


## Dokumentaatio

[Vaatimusmäärittely](https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/a-bzzzz/ot-harjoitustyo/tree/master/dokumentaatio/arkkitehtuuri)

[Testausdokumentaatio](https://github.com/a-bzzzz/ot-harjoitustyo/tree/master/dokumentaatio/testaus)

[Laatudokumentaatio](https://github.com/a-bzzzz/ot-harjoitustyo/tree/master/dokumentaatio/laatu)

[Työaikakirjanpito](https://github.com/a-bzzzz/ot-harjoitustyo/tree/master/dokumentaatio/tunnit)


## Releaset

[Viikko 5](https://github.com/a-bzzzz/ot-harjoitustyo/releases/tag/viikko5)


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

"Viikko 5" -releasen kautta haetun pakkauksen nimi on _recipes.jar_, joten sen voi suorittaa komennolla

```
java -jar recipes.jar
```

Jar-tiedosto on mahdollista suorittaa millä tahansa koneella, olettaen että koneelle on asennettu Javan versio 1.8



### JavaDoc - TULOSSA VIIKOLLA 6

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_


### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/mluukkai/OtmTodoApp/blob/master/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_




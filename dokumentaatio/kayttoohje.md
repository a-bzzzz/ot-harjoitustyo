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

"loppupalautus" -releasen (1.0) kautta haetun pakkauksen nimi on _recipes.jar_, joten sen voi suorittaa komennolla

```
java -jar recipes.jar
```

Jar-tiedosto on mahdollista suorittaa millä tahansa koneella, olettaen että koneelle on asennettu Javan versio 1.8


## Kirjautuminen

Sovellus käynnistyy aloitusnäkymään:

<img src="https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/A_aloitussivu.png" width="600">

Sovellukseen kirjaudutaan kirjoittamalla syötekenttiin käyttäjätunnus ja salasana, jotka on jo luotu, ja painamalla _Kirjaudu_.

### Koekäyttö

Sovellusta voi kokeilla ilman rekisteröitymistä seuraavilla valmiiksi järjestelmään luoduilla tunnuksilla ja reseptillä:

Käyttäjätunnus: admin

Salasana: secret

Reseptin nimi: kaakao

## Uuden käyttäjän luominen - rekisteröityminen

Aloitusnäkymän kautta ohjelman uusi käyttäjä voi siirtyä rekisteröitymisnäkymään (_Registration_) panikkeella _Rekisteröidy_ ja luoda siellä itselleen käyttäjätunnukset.

Uuden käyttäjän tulee täyttää kaikki seuraavat kentät ja painaa lopuksi _REKISTERÖIDY_-nappulaa:

* etunimi
* sukunimi
* sähköpostiosoite
* käyttäjätunnus
* salasana

Voit myös joko palata aloitussivulle, _TAKAISIN_, tai lopettaa ohjelman, _LOPETA_.

<img src="https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/A1_rekisteroi.png" width="400">


## Alkuvalikko

Jos käyttäjän luominen onnistuu, tapahtuu samalla järjestelmään sisäänkirjautuminen ja siirrytään alkuvalikko-näkymään (_Secret Recipes - main menu_).

Alkuvalikossa valitaan haluttu toiminto painamalla kyseistä painiketta. Vaihtoehdot ovat:

1. Hae reseptejä

1. Luo uusi resepti

1. Listaa kaikki reseptit

Huomaa, että tässä ohjelmaversiossa ei ole mahdollista suoraan muokata reseptejä, vaan "muokkaus" tapahtuu poistamalla resepti ja lisäämällä se uudelleen.
Katso kohdat _Reseptinäkymä ja reseptien poisto_ sekä _Reseptien luominen_.

Ylävalikosta löytyy painike, jolla voit lopettaa ohjelman, _Lopeta_.

<img src="https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/0_alkuvalikko.png" width="600">


## Reseptien hakeminen 

Reseptihakunäkymässä (_Recipe search_) voit hakea reseptikirjaan tallennettuja reseptejä joko reseptin nimellä - 1. hakukenttä - tai 
reseptin raaka-aineella - 2. hakukenttä. Kirjoita jompaan kumpaan hakukenttään haettavan reseptin nimi tai reseptin sisältämä raaka-aine ja
paina kentän lopussa olevaa _Hae_-painiketta.

Huomaa, että kirjoitettava haku sana voi sisältää suuria tai pieniä kirjaimia, sekaisinkin, sillä ei ole merkitystä. Mutta muuten hakusanan on oltava juuri
samassa muodossa kuin joko reseptin nimi tai reseptin raaka-aine on sovellukseen talletettu.

Huomaa myös, että hakunäkymässä on listattu kaikkien reseptikirjaan tallennettujen reseptien nimet, joten voit siltä listalta tarkistaa, miten kirjoitat
reseptin nimen 1. hakukenttään.

Ylävalikosta löytyvät painikkeet, joilla voit joko palata alkuvalikkoon, _Alkuun_, tai lopettaa ohjelman, _Lopeta_.

<img src="https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/1_reseptihaku.png" width="600">


## Reseptien luominen 

Uusi resepti luodaan järjestelmään neljässä vaiheessa.

1. Syötä ensin reseptin perustiedot kenttiin _Reseptin nimi_, _Annosmäärä_ ja _Kategoria_. 

* Kategoriatietoa syöttäessäsi käytä hyväksesi oikeassa reunassa näkyvää kategorialistausta. Kirjainten koolla ei ole merkitystä, mutta jatkokehitystä ajatellen
olisi hyvä, että kirjoitat kategorian nimen muuten oikein.

* Jos tiedot ovat oikein ja haluat lisätä ne, paina _Lisää tiedot_-painiketta.

* Jos tiedot ovat väärin ja haluat tyhjentää kentät ennen uusien tietojen syöttämistä, paina _Tyhjennä_-painiketta.

2. Syöte seuraavaksi reseptin raaka-aineet ja niiden määrät yksi aine kerrallaan määrineen kenttiin _Raaka-aine_ ja _Määrä_.

* Raaka-aineen määrän voi kirjoittaa esim. seuraavissa muodoissa: 2 dl, 4 kpl, 1 lusikallinen jne.

* Tallentaaksesi raaka-aineen määrineen paina _Lisää aine_ -painiketta.

* Ennen seuraavan aineen tietojen lisäämistä paina _Uusi aine_ -painiketta.

3. Viimeisinä tietoina syötetään valmistusohjeet rivi kerrallaan.

* Kirjoita ohjerivi _Valmistusvaihe_-kenttään ja paina _Lisää vaihe_ -painiketta.

* Ennen seuraavan vaiheen lisäämistä paina _Uusi vaihe_ -painiketta.

4. Kun edellä mainitut vaiheet on käyty läpi ja kaikki tarvittavat tiedot on syötetty, paina lopussa olevaa _Lisää resepti_ -painiketta.

* Mikäli haluat jatkaa lisäämällä seuraavan uuden reseptin, paina alaosassa olevaa _Uusi resepti_ -painiketta.


Ylävalikosta löytyvät painikkeet, joilla voit joko palata alkuvalikkoon, _Alkuun_, tai lopettaa ohjelman, _Lopeta_.


<img src="https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/2_uusiresepti.png" width="600">

## Reseptien listaaminen 

Alkuvalikko näkymässä saat esille kaikkien reseptikirjaan talletettujen reseptien nimet painamalla kolmatta painiketta _Listaa kaikki reseptit_.
Listalla on reseptin nimen edessä sen numero. Jos haluat hakea jonkun näistä resepteistä, kirjoita reseptin numero kenttään _Valitse listalta reseptin numero_
ja paina _Hae_-painiketta.

Lisäksi reseptihakunäkymässä sama lista näkyy oikeassa reunassa eikä sitä tarvitse hakea erikseen. Mutta tässä näkymässä et pysty hakemaan reseptiä
sen numeron perusteella.

Ylävalikosta löytyvät painikkeet, joilla voit joko palata alkuvalikkoon (vain reseptihakunäkymässä), _Alkuun_, tai lopettaa ohjelman, _Lopeta_.


<img src="https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/1.1_reseptilistaus.png" width="600">


## Reseptinäkymä ja reseptien poisto 

Reseptinäkymässä näet haetun reseptin tiedot kolmessa eri sarakkeessa

* vasemmalla raaka-aineet

* keskellä raaka-aineiden määrät

* oikealla valmistusohjeet

sekä reseptin nimen ikkunan otsikkokentässä ja annosmäärän oikealla valmistusohjeiden yläpuolella.


Ylävalikosta löytyvät painikkeet, joilla voit palata hakunäkymään, _Takaisin_, tai alkuvalikkoon, _Alkuun_, tai lopettaa ohjelman, _Lopeta_.

Alavalikosta löytyvät seuraavat painikkeet

* _Muokkaa_: Ei ole varsinaisesti käytössä tässä ohjelmaversiossa, vain ilmoittaa käyttäjälle, että muokkaus on tehtävä kaksivaiheisesti: 1. poistettava resepti ja 2. lisättävä uudelleen korjattuna.

* _Poista_: Varmistaa ensin käyttäjältä haluaako todella poistaa reseptin reseptikirjasta (käytännössä myös tietokannasta).

** Käyttäjä voi joko peruuttaa poistamisen painamalla _PERUUTA_-painiketta tai vahvistaa reseptin poiston painamalla _POISTA_-painiketta.
Näkymän alaosassa olevasta infokentästä näet onko poistaminen onnistunut vai ei.


<img src="https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/3_reseptinakyma.png" width="600">


## Infokenttä ja lopetus

Huomaa, että näkymien alareunassa oleva _infokenttä_ antaa sinulle tarvittavaa tietoa mm. reseptien lisäämisten ja poistojen onnistumisista,
sekä opastaa muuten sovelluksen käyttäjää.

Klikkaamalla näkymän oikean ylänurkan painiketta _Lopeta_ käyttäjä kirjautuu ulos sovelluksesta ja kyseinen ikkuna sulkeutuu.

# VAATIMUSMÄÄRITTELY

## Salaiset reseptit


### Yleiskuvaus

Sovelluksen avulla loppukäyttäjä voi lisätä, ylläpitää, hakea ja listata ja poistaa ruokaohjeita eli ruokareseptejään käyttäjätunnuksilla suojatussa ympäristössä. Aluksi loppukäyttäjä voi luoda itselleen käyttäjätunnukset järjestelmään.


### Käyttäjäroolit

1. Loppukäyttäjä
1. Ylläpitäjä (jatkossa)


### Toiminnallisuudet

Perusversio: Tavalliset loppukäyttäjät voivat sovelluksella
* luoda käyttäjätunnuksen 
* kirjautua järjestelmään omilla käyttäjätunnuksillaan (järjestelmä ilmoittaa, jos käyttäjällä ei ole vielä tunnuksia eli oikeuksia järjetelmään pääsyyn)
* luoda reseptin tai reseptejä
* hakea reseptiä tietyillä hakukriteereillä tai tietyn reseptikategorian kautta
* listata tietyn kategorian reseptinimiä ja/tai reseptien perustietoja
* muokata luomiaan reseptejä
* poistaa haetun reseptin
* kirjautua ulos järjestelmästä

Jatkokehitys: Ylläpitäjät voivat sovelluksessa edellä mainitun lisäksi
* kirjautua järjestelmään admin-tunnuksilla
* muokata ja listata käyttäjätietoja (loppukäyttäjien käyttäjätunnuksia)
* poistaa käyttäjätietoja (loppukäyttäjien tunnuksia)
* listata reseptejä ja niiden perustietoja hieman monipuolisemmin
* ottaa raportteja järjestelmään tehdyistä muutoksista ja/tai muusta käyttäjästatistiikasta
    
    
### Toimintaympäristön rajoitteet

* ohjelmiston tulee toimia Linux- ja OSX-käyttöjärjestelmillä varustetuissa koneissa
* käyttäjien ja reseptien tiedot talletetaan paikallisen koneen levylle
    
   
### Käyttöliittymäkuvaus

Katso erillisistä käyttöliittymädokumenteista:
* [GUI-ikkunat](https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/GUI/GUI-ikkunat.pdf)
* [GUI-rakenne](https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/GUI/GUI-rakenne.md)
* [GUI-kaavio](https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/GUI/GUI-kaavio.pdf)
    
    
### Jatkokehitysideoita

* ylläpitäjän toiminnallisuudet (ks. edellä kohdasta “Toiminnallisuudet”)
* info-painikkeesta lisätietoja ko. Ikkunan käytöstä, opastekenttä/-ikkuna
* lisää, parempia virheilmoituskenttiä/-ikkunoita, myös toiminnon peruuttamisia varten
* reseptin hakutekijöihin erikoisruokavaliot / vältettävät raaka-aineet
* resepteihin voisi lisätä mm. arviointeja, kuvia ym. lisätietoja
* mahdollisuus raaka-ainevarastojen seurantaan

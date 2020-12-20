# VAATIMUSMÄÄRITTELY

## Salaiset reseptit


### Yleiskuvaus

Sovelluksen avulla loppukäyttäjä voi lisätä, hakea (reseptin nimellä ja raaka-aineella), listata ja poistaa ruokaohjeita eli ruokareseptejään käyttäjätunnuksilla suojatussa ympäristössä. Aluksi loppukäyttäjä voi luoda itselleen käyttäjätunnukset järjestelmään.


### Koekäyttö

Sovellusta voi kokeilla ilman rekisteröitymistä seuraavilla valmiiksi järjestelmään luoduilla tunnuksilla ja reseptillä:

Käyttäjätunnus: admin

Salasana: secret

Reseptin nimi: kaakao


### Käyttäjäroolit

1. Pääkäyttäjä: 1. käyttökerralla sovellus luo _admin_-tunnukset lähinnä koekäyttöä varten, mutta varsinaisen ylläpitäjäroolin toimintojen toteutus jää jatkokehitykselle
1. Loppukäyttäjä: käyttäjä voi rekisteröityä eli luoda itselleen käyttäjätunnukset sovellukseen, jolloin hän voi tehdä yleiskuvauksessa määriteltyjä perustoimintoja

Toistaiseksi molemmilla käyttäjärooleilla on yhtäläiset käyttöoikeudet, mutta sovellukselle on mahdollista jatkossa lisätä varsinaiset pääkäyttäjä- tai ylläpitäjärooli, joka oikeuttaa laajempiin käyttömahdollisuuksiin, joita voivat jatkokehitysversioissa olla mm.
* kaikkien käyttäjätietojen lisäys, poisto ja muokkaus
* mahdollisuus listata reseptitietoja laajemmin
* mahdollisuus listata käyttäjätietoja
* mahdollisuus ottaa raportteja sovelluksen käytöstä, tehdyistä muutoksista ja/tai muusta käyttäjästatistiikasta


### Toiminnallisuudet

Perusversio: Käyttäjät voivat sovelluksella (ts. seuraavat ominaisuudet on tehty sovellukseen toimiviksi)
* rekisteröityä käyttäjäksi eli luoda käyttäjätunnuksen ja salasanan kirjautumista varten
* kirjautua järjestelmään omilla käyttäjätunnuksillaan (järjestelmä ilmoittaa, jos käyttäjällä ei ole vielä tunnuksia eli oikeuksia järjetelmään pääsyyn) 
* kirjautua järjestelmään myös em. _admin_-tunnuksilla, jollei halua rekisteröityä (ks. _Koekäyttö_) 
* luoda reseptin tai reseptejä 
* hakea reseptiä reseptin kokonaisella nimellä (isot tai pienet kirjaimet eivät ole määrääviä, mutta sanan on oltava muuten oikein kirjoitettu)
* hakea reseptiä reseptiin kuuluvalla raaka-aineella (isot tai pienet kirjaimet eivät ole määrääviä, mutta sanan on oltava muuten oikein kirjoitettu)
* listata kaikkien reseptikirjaan tallennettujen reseptien nimet (NB: tämä ominaisuus auttaa merkittävästi nimihaussa)
* poistaa näkymään haetun reseptin
* kirjautua ulos järjestelmästä

    
### Toimintaympäristön rajoitteet

* ohjelmiston tulee toimia Linux- ja OSX-käyttöjärjestelmillä varustetuissa koneissa
* käyttäjien ja reseptien tiedot talletetaan paikallisen koneen levylle
    
   
### Käyttöliittymäkuvaus

Katso erillisistä käyttöliittymädokumenteista:
* [GUI-ikkunat](https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/GUI/GUI-ikkunat.pdf)
* [GUI-rakenne](https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/GUI/GUI-rakenne.md)
* [GUI-kaavio](https://github.com/a-bzzzz/ot-harjoitustyo/blob/master/dokumentaatio/GUI/GUI-kaavio.pdf)
    
    
### Jatkokehitystarpeita ja -ideoita

* kohdassa _Käyttäjäroolit_ mainitut lisätoiminnot pääkäyttäjälle
* loppukäyttäjä voisi lähettää ylläpitäjälle tukipyynnön, jos unohtaa salasanansa
* reseptien haku reseptin nimen osalla
* reseptin haku raaka-aineen nimen osalla
* reseptien haku ja listaus tietyn reseptikategorian perusteella
* reseptin tietojen muokkaus ja päivitys (muutenkin kuin poistamalla ja lisäämällä)
* muita mahdollisia reseptien haku- ja listaustapoja
* lisäkenttiä reseptin tietoihin, esim. erikoisruokavaliot/vältettävät raaka-aineet, valmistusaika, arvioinnit, kuvia, linkkejä
* em. lisätietojen perusteella lisää hakutekijöitä, kuten esim. laktoosittomat, hyviksi arvostellut tai nopeasti valmistettavat ruoat
* info-painike, jonka kautta lisätietoja ko. ikkunan käytöstä, tai muu ohje
* parannuksia käyttöliittymään näkymien ulkoasuun, visuaalisuuteen ja käytettävyyteen
* mahdollisuus raaka-ainevarastojen seuraantaan eli voi kuitata reseptin aineet käytetyiksi ja sovellus ylläpitää varastokirjanpitoa
* muuta, missä vain mielikuvitus rajana?


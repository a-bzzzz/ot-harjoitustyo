package com.mycompany.unicafe;

import com.mycompany.Kassapaate;
import com.mycompany.Maksukortti;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;
    Kassapaate kassa;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
        kassa = new Kassapaate();
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void lataaRahaaKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(-10000);
        kortti.lataaRahaa(30000);
        // speksattu niin, että miinusmerkkinen lataus "otetaan kiinni" 
        // Kassapäätteellä lataaRahaaKortille-metodissa
        assertEquals("saldo: 200.10", kortti.toString());
    }
    
    @Test
    public void rahanLataaminenKortilleKassallaKasvattaaSaldoaOikein() {
        kassa.lataaRahaaKortille(kortti,-10000);
        kassa.lataaRahaaKortille(kortti, 30000);
        assertEquals("saldo: 300.10", kortti.toString());
    }
    
    @Test
    public void maukkaidenMaksaminenKortillaToimiiOikein() {
        kassa.lataaRahaaKortille(kortti, 600);
        assertTrue(kassa.syoMaukkaasti(kortti));
        assertFalse(kassa.syoMaukkaasti(kortti));
        assertEquals("saldo: 2.10", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenToimiiOikein() {
        kassa.lataaRahaaKortille(kortti, 200);
        assertEquals("saldo: 2.10", kortti.toString());
        assertTrue(kortti.otaRahaa(200));
        assertEquals("saldo: 0.10", kortti.toString());
        assertFalse(kortti.otaRahaa(200));
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
}

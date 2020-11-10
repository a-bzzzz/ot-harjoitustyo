/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import com.mycompany.Kassapaate;
import com.mycompany.Maksukortti;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aebjork
 */
public class KassapaateTest {

//    public KassapaateTest() {        
//    }
    Kassapaate kassa;
    Maksukortti kortti;

//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
    }

//    @After
//    public void tearDown() {
//    }

    @Test
    public void kassassaAlussaTonniRahaaJaMyynnitNollilla() {
        assertThat(100000, is(equalTo(kassa.kassassaRahaa())));
        assertThat(0, is(equalTo(kassa.edullisiaLounaitaMyyty())));
        assertThat(0, is(equalTo(kassa.maukkaitaLounaitaMyyty())));
    }
    
    @Test
    public void rahallaEdullisenMaksuToimii() {
        assertThat(760, is(equalTo(kassa.syoEdullisesti(1000))));
        assertThat(1, is(equalTo(kassa.edullisiaLounaitaMyyty())));
        assertThat(100240, is(equalTo(kassa.kassassaRahaa())));
        assertThat(200, is(equalTo(kassa.syoEdullisesti(200))));
        assertThat(1, is(equalTo(kassa.edullisiaLounaitaMyyty())));
        assertThat(100240, is(equalTo(kassa.kassassaRahaa())));
    }
    
    @Test
    public void rahallaMaukkaanMaksuToimii() {
        assertThat(600, is(equalTo(kassa.syoMaukkaasti(1000))));
        assertThat(1, is(equalTo(kassa.maukkaitaLounaitaMyyty())));
        assertThat(100400, is(equalTo(kassa.kassassaRahaa())));
        assertThat(300, is(equalTo(kassa.syoMaukkaasti(300))));
        assertThat(1, is(equalTo(kassa.maukkaitaLounaitaMyyty())));
        assertThat(100400, is(equalTo(kassa.kassassaRahaa())));
    }
    
    @Test
    public void kortillaEdullisenMaksuToimii() {
        assertTrue(kassa.syoEdullisesti(kortti));
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        assertThat(4, is(equalTo(kassa.edullisiaLounaitaMyyty())));
        assertFalse(kassa.syoEdullisesti(kortti));
        assertThat(4, is(equalTo(kassa.edullisiaLounaitaMyyty())));
        assertThat(40, is(equalTo(kortti.saldo())));
        assertThat("saldo: 0.40", is(equalTo(kortti.toString())));
        assertThat(100000, is(equalTo(kassa.kassassaRahaa())));
    }
    
    @Test
    public void kortillaMaukkaanMaksuToimii() {
        assertTrue(kassa.syoMaukkaasti(kortti));
        kassa.syoMaukkaasti(kortti);
        assertThat(2, is(equalTo(kassa.maukkaitaLounaitaMyyty())));
        assertFalse(kassa.syoMaukkaasti(kortti));
        assertThat(2, is(equalTo(kassa.maukkaitaLounaitaMyyty())));
        assertThat(200, is(equalTo(kortti.saldo())));
        assertThat("saldo: 2.00", is(equalTo(kortti.toString())));
        assertThat(100000, is(equalTo(kassa.kassassaRahaa())));
    }
    
    @Test
    public void rahanLataaminenKortilleToimii() {
        kassa.lataaRahaaKortille(kortti, 2000);
        assertThat(3000, is(equalTo(kortti.saldo())));
        assertThat(102000, is(equalTo(kassa.kassassaRahaa())));
        kassa.lataaRahaaKortille(kortti, -3000);
        assertThat(3000, is(equalTo(kortti.saldo())));
        assertThat(102000, is(equalTo(kassa.kassassaRahaa())));
    }
    
}

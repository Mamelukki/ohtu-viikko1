package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriNollaaNegatiivisenTilavuuden() {
        varasto2 = new Varasto(-2);

        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriNollaaNegatiivisenSaldonJaTilavuuden() {
        varasto2 = new Varasto(-2, -4);

        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriAsettaaTilavuudenOikein() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        varasto2 = new Varasto(4, 2);
        assertEquals(2, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriAsettaaSaldonOikeinKunTilavuusOnAlleSaldon() {
        varasto2 = new Varasto(2, 4);
        assertEquals(4, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisaaTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void ylimaaraMeneeHukkaan() {
        varasto.lisaaVarastoon(12);
        
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaminenYliVapaanTilanMahdotonta() {
        varasto.lisaaVarastoon(12);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void yliSaldonOttaminenNollaaSaldon() {
        varasto.otaVarastosta(12);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaaminenEiOnnistu() {
        varasto.lisaaVarastoon(-2);
        
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttaminenEiOnnistu() {
        varasto.otaVarastosta(-2);
        
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringOnOikeanMuotoinen() {
        varasto.lisaaVarastoon(6);
        
        assertEquals("saldo = 6.0, vielä tilaa 4.0", varasto.toString());
    }

}

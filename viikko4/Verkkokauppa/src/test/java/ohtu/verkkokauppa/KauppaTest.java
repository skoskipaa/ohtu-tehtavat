package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;
   
    
    @Before
    public void aloitus() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        k = new Kauppa(varasto, pankki, viite);
    }
    
    
    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {
        when(viite.uusi()).thenReturn(42);
        //Tuote nro 1 on maito, saldo 10, hinta 5.
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());

    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(5));

    }
    
    @Test
    public void metodiaTilisiirtoKutsutaanOikeinKahdellaEriOstoksella() {
       // when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(51);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "jauhot", 5));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("hilkka", "54321");
        
        verify(pankki).tilisiirto(eq("hilkka"), anyInt(), eq("54321"), anyString(), eq(10));
    }
    @Test
    public void metodiaTilisiirtoKutsutaanOikeinKahdellaSamallaTuotteella() {
        when(varasto.saldo(1)).thenReturn(5).thenReturn(4);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("jukka", "313");
        
        
        verify(pankki).tilisiirto(eq("jukka"), anyInt(), eq("313"), anyString(), eq(10));

    }
    @Test
    public void metodiTilisiirtoToimiiOikeinKunTuoteLoppu() {
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(0);
        
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "jauhot", 5));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("maija", "666");
        
        verify(pankki).tilisiirto(eq("maija"), anyInt(), eq("666"), anyString(), eq(5));
    }
    
    @Test
    public void aloitaAsiointiNollaaEdellisenOstoksen() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(5));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(10));

    }
    
    @Test
    public void jokaisellaOstoksellaUusiViitenumero() {
        when(viite.uusi())
                .thenReturn(1)
                .thenReturn(2)
                .thenReturn(3);
        
        when(varasto.saldo(1))
                .thenReturn(5)
                .thenReturn(4)
                .thenReturn(3);
        
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(anyString(), eq(1), anyString(), anyString(), anyInt());
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("maija", "666");
        
        verify(pankki).tilisiirto(anyString(), eq(2), anyString(), anyString(), anyInt());
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("jukka", "313");
        
        verify(pankki).tilisiirto(anyString(), eq(3), anyString(), anyString(), anyInt());
    }
    
    @Test
    public void tuotteenPoistoKoristaOnnistuu() {
       when(viite.uusi()).thenReturn(42);
       when(varasto.saldo(1)).thenReturn(10).thenReturn(9);
       when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "rusinat", 3));
       
       k.aloitaAsiointi();
       k.lisaaKoriin(1);
       k.lisaaKoriin(1);
       k.poistaKorista(1);
       k.tilimaksu("jj", "555");
       
       verify(pankki).tilisiirto(eq("jj"), eq(42), eq("555"), anyString(), eq(3));
     
       
    }
}

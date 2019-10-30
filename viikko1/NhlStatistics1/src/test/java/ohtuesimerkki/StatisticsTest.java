package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void onOlemassa() {
        assertNotNull(stats);
    }

    @Test
    public void searchPlayerPalauttaaPelaajan() {
        Player pelaaja = stats.search("Kurri");
        assertEquals("Kurri", pelaaja.getName());
    }

    @Test
    public void nullJosPelaajaaEiLoydy() {
        assertNull(stats.search("Karri"));
    }

    @Test
    public void pelaajalistaPalautetaan() {
        List<Player> lista = stats.team("EDM");
        assertEquals(3, lista.size());
    }

    /*
    Testi paljasti bugin luokasta Statistics. Korjattu sinne.
    */
    @Test
    public void pistelistanPelaajatPalautetaan() {
        List<Player> topKolme = stats.topScorers(3);
        assertEquals(3, topKolme.size());
    }
    
    @Test
    public void parasPelaaja() {
        List<Player> paras = stats.topScorers(1);
        assertEquals("Gretzky", paras.get(0).getName());
    }
}

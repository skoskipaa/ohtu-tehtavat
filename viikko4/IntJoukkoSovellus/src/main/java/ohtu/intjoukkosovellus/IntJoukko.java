package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KOKO = 5; // aloitustalukon koko
    public final static int KASVATAVIIDELLA = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int lisays;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] joukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KOKO);
    }
    
    public IntJoukko(int kapasiteetti) {
        this(KOKO, KASVATAVIIDELLA);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasiteetin tulee olla positiivinen");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoon tulee olla positiivinen");
        }
        joukko = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.lisays = kasvatuskoko;
        alusta(joukko);

    }

    public boolean lisaaJoukkoon(int luku) {

        if (alkioidenLkm == 0) {
            joukko[0] = luku;
            alkioidenLkm++;
            return true;
        }

        if (!kuuluukoJoukkoon(luku)) {
            joukko[alkioidenLkm] = luku;
            alkioidenLkm++;

            if (alkioidenLkm % joukko.length == 0) {
               kasvataTaulukkoa();
            }
            return true;
        }
        return false;
    }

    public boolean kuuluukoJoukkoon(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukko[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukko[i]) {
                siirraLukujaTaaksepain(i);
                alkioidenLkm--;
                return true;
            }
        }
        return false;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        switch (alkioidenLkm) {
            case 0:
                return "{}";
            case 1:
                return "{" + joukko[0] + "}";
            default:
                String tuloste = "{";
                for (int i = 0; i < alkioidenLkm - 1; i++) {
                    tuloste += joukko[i];
                    tuloste += ", ";
                }
                tuloste += joukko[alkioidenLkm - 1];
                tuloste += "}";
                return tuloste;
        }
    }

    public int[] muodostaJoukko() {
        int[] joukkoTaulukkona = new int[alkioidenLkm];
        System.arraycopy(joukko, 0, joukkoTaulukkona, 0, joukkoTaulukkona.length);
        return joukkoTaulukkona;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        int[] joukkoA = a.muodostaJoukko();
        int[] joukkoB = b.muodostaJoukko();
        for (int i = 0; i < joukkoA.length; i++) {
            yhdiste.lisaaJoukkoon(joukkoA[i]);
        }
        for (int i = 0; i < joukkoB.length; i++) {
            yhdiste.lisaaJoukkoon(joukkoB[i]);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        int[] joukkoA = a.muodostaJoukko();
        int[] joukkoB = b.muodostaJoukko();
        
        for (int i = 0; i < joukkoA.length; i++) {
            for (int j = 0; j < joukkoB.length; j++) {
                if (joukkoA[i] == joukkoB[j]) {
                    leikkaus.lisaaJoukkoon(joukkoB[j]);
                }
            }
        }
        return leikkaus;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        int[] joukkoA = a.muodostaJoukko();
        int[] joukkoB = b.muodostaJoukko();
        
        for (int i = 0; i < joukkoA.length; i++) {
            erotus.lisaaJoukkoon(joukkoA[i]);
        }
        for (int i = 0; i < joukkoB.length; i++) {
            erotus.poista(joukkoB[i]);
        }

        return erotus;
    }

    private static void alusta(int[] joukko) {
        for (int i = 0; i < joukko.length; i++) {
            joukko[i] = 0;
        }
    }
    
    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        System.arraycopy(vanha, 0, uusi, 0, vanha.length);

    }
    
    private void kasvataTaulukkoa() {
        int[] apuTaulukko = new int[joukko.length];
                apuTaulukko = joukko;
                kopioiTaulukko(joukko, apuTaulukko);
                joukko = new int[alkioidenLkm + lisays];
                kopioiTaulukko(apuTaulukko, joukko);
    }
    
    private void siirraLukujaTaaksepain(int indeksista) {
         for (int j = indeksista; j < alkioidenLkm - 1; j++) {
                    int luku = joukko[j];
                    joukko[j] = joukko[j + 1];
                    joukko[j + 1] = luku;
                }
    }

}

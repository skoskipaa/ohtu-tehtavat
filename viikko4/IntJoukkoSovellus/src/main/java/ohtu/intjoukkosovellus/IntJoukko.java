package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int ALOITUSKOKO = 5;
    public final static int KASVATAVIIDELLA = 5;
    private int taulukonKasvatus;
    private int[] joukko; 
    private int joukonAlkioidenLkm; 

    public IntJoukko() {
        this(ALOITUSKOKO);
    }

    public IntJoukko(int aloituskoko) {
        this(ALOITUSKOKO, KASVATAVIIDELLA);
    }

    public IntJoukko(int aloituskoko, int kasvatuskoko) {
        if (aloituskoko < 0 || kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Koko ei voi olla negatiivinen");
        }
        
        joukko = new int[aloituskoko];
        joukonAlkioidenLkm = 0;
        this.taulukonKasvatus = kasvatuskoko;
        alusta(joukko);

    }

    public boolean lisaaJoukkoon(int luku) {

        if (joukonAlkioidenLkm == 0) {
            joukko[0] = luku;
            joukonAlkioidenLkm++;
            return true;
        }

        if (!kuuluukoJoukkoon(luku)) {
            joukko[joukonAlkioidenLkm] = luku;
            joukonAlkioidenLkm++;

            if (joukonAlkioidenLkm == joukko.length) {
                kasvataTaulukkoa();
            }
            return true;
        }
        return false;
    }

    public boolean kuuluukoJoukkoon(int luku) {
        for (int i = 0; i < joukonAlkioidenLkm; i++) {
            if (luku == joukko[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        for (int i = 0; i < joukonAlkioidenLkm; i++) {
            if (luku == joukko[i]) {
                siirraLukujaTaaksepain(i);
                joukonAlkioidenLkm--;
                return true;
            }
        }
        return false;
    }

    public int mahtavuus() {
        return joukonAlkioidenLkm;
    }

    @Override
    public String toString() {
        switch (joukonAlkioidenLkm) {
            case 0:
                return "{}";
            case 1:
                return "{" + joukko[0] + "}";
            default:
                String tuloste = "{";
                for (int i = 0; i < joukonAlkioidenLkm - 1; i++) {
                    tuloste += joukko[i];
                    tuloste += ", ";
                }
                tuloste += joukko[joukonAlkioidenLkm - 1];
                tuloste += "}";
                return tuloste;
        }
    }

    public int[] muodostaJoukostaTaulukko() {
        int[] joukkoTaulukkona = new int[joukonAlkioidenLkm];
        System.arraycopy(joukko, 0, joukkoTaulukkona, 0, joukkoTaulukkona.length);
        return joukkoTaulukkona;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        int[] joukkoA = a.muodostaJoukostaTaulukko();
        int[] joukkoB = b.muodostaJoukostaTaulukko();
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
        int[] joukkoA = a.muodostaJoukostaTaulukko();
        int[] joukkoB = b.muodostaJoukostaTaulukko();

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
        int[] joukkoA = a.muodostaJoukostaTaulukko();
        int[] joukkoB = b.muodostaJoukostaTaulukko();

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
        joukko = new int[joukonAlkioidenLkm + taulukonKasvatus];
        kopioiTaulukko(apuTaulukko, joukko);
    }

    private void siirraLukujaTaaksepain(int indeksista) {
        for (int j = indeksista; j < joukonAlkioidenLkm - 1; j++) {
            int luku = joukko[j];
            joukko[j] = joukko[j + 1];
            joukko[j + 1] = luku;
        }
    }

}

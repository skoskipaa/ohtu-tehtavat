package ohtu.verkkokauppa;

public interface Maksunhallinta {

    boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);
    
}

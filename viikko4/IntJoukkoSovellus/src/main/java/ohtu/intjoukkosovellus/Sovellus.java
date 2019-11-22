package ohtu.intjoukkosovellus;

import java.util.Scanner;

public class Sovellus {

    public static void main(String[] args) {

        System.out.println("Tervetuloa joukkolaboratorioon!");
        System.out.println("Käytössäsi ovat joukot A, B ja C.");
        System.out.println("Joukon nimi komentona tarkoittaa pyyntöä tulostaa joukko.");

        Scanner lukija = new Scanner(System.in);
        TekstiKayttoliittyma kayttis = new TekstiKayttoliittyma(lukija);
        kayttis.kaynnista();

    }
}

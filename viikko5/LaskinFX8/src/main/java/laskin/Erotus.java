package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Erotus extends Komento {

    private int arvo;
    private int muutos;

    public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        int vahennettava = Integer.parseInt(syotekentta.getText());
        arvo = sovellus.tulos();
        muutos = vahennettava;
        sovellus.miinus(vahennettava);
        int tulos = sovellus.tulos();
        tuloskentta.setText("" + tulos);
        nollaaKentatJaNapit();
        undo.disableProperty().set(false);
    }

    @Override
    public void peru() {
        sovellus.plus(muutos);
        tuloskentta.setText("" + arvo);
        nollaaKentatJaNapit();
        undo.disableProperty().set(true);

    }

    public void nollaaKentatJaNapit() {
        syotekentta.setText("0");
        if (sovellus.tulos() == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
    }

}

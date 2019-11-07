
package ohtu.verkkokauppa;

import java.util.ArrayList;

public interface Seuranta {

    ArrayList<String> getTapahtumat();

    void lisaaTapahtuma(String tapahtuma);
    
}

package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import org.apache.http.client.fluent.Request;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();
                
//        System.out.println("json-muotoinen data:");
//        System.out.println( bodyText );

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);
        ArrayList<Player> finnishPlayers = new ArrayList<>();
        for (int i = 0; i < players.length; i++) {
            if (players[i].getNationality().equals("FIN")) {
                finnishPlayers.add(players[i]);
            }
        }
        
        System.out.println("Players from FIN "  + LocalDateTime.now() + "\n");
        Collections.sort(finnishPlayers);
        for (Player player : finnishPlayers) {
            System.out.println(player);
        }   
    }
  
}
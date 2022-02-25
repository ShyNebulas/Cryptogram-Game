package src;

import java.util.HashMap;

public class Game {
    HashMap <Player,String> playerGameMapping = new HashMap<>();
    Player currentPlayer;

    public Game(Player p ){
        this.currentPlayer = p;

    }

    public Game(Player p, String crypType) {
        this.currentPlayer=p;
        this.playerGameMapping.put(p,crypType);
    }

}

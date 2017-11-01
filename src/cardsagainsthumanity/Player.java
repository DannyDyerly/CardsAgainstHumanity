package cardsagainsthumanity;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Player {
    private int numCards;
    private ArrayList<White> hand = new ArrayList<White>();
    
    Player(){
        
    }
    
    public void drawHand(Graphics2D g){
        int x = 10;
        for(White obj: hand){
            obj.draw(g, x);
            x += 210;
        }
    }
    
    public void drawCard(){
        White addWhite = White.getRandomWhite();
        if(addWhite == null)
            return;
        addWhite.setUsed(true);
        hand.add(addWhite);
    }
    
}

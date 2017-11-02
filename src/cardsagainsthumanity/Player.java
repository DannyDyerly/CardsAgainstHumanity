package cardsagainsthumanity;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Player {
    private boolean czar = false;
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
        if(addWhite == null || hand.size()>=9)
            return;
        addWhite.setUsed(true);
        hand.add(addWhite);
    }
    
    public void checkSelect(int xpos, int ypos){
        for(White obj : hand){
            obj.setSelected(false);
        }
        for(White obj : hand){
            obj.checkSelect(xpos, ypos);
        }
    }
    
}

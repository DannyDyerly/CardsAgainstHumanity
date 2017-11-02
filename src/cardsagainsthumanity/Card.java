package cardsagainsthumanity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.*;

public class Card {
    private int numCards;
    private int id;
    private String text;
    private Player player;
    private int numInHand;
    private boolean used;
    private int xpos;
    private int ypos;
    protected static int length = 200;
    protected static int height = 250;
    protected static ArrayList<Card> cards = new ArrayList<Card>();
    
    Card(){
        
    }
    
    Card(String _text){
        id = numCards;
        numCards++;
        text = _text;
        used = false;
    }
    
    public String getText(){
        return text;
    }
    
    public void setUsed(boolean _used){
        used = _used;
    }
    
    public boolean getUsed(){
        return used;
    }
    
    public void setXpos(int _xpos){
        xpos = _xpos;
    }
    
    public void setYpos(int _ypos){
        ypos = _ypos;
    }
    
    public int getXpos(){
        return xpos;
    }
    
    public int getYpos(){
        return ypos;
    }
    
}

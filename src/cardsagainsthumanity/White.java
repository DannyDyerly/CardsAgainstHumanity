package cardsagainsthumanity;

import java.awt.Graphics2D;
import java.awt.*;
import java.util.ArrayList;

public class White extends Card {
    private static int numWhiteCards;
    protected static ArrayList<White> whites = new ArrayList<White>();
    
    White(String _text){
        super(_text);
    }
    
    public static White Create(String _text){
        White obj = new White(_text);
        whites.add(obj);
        numWhiteCards++;
        return(obj);
    }
    
    public void draw(Graphics2D g, int x){
        g.setColor(Color.white);
        g.fillRoundRect(Window.getX(x+20), Window.getYNormal(250), 150, 200, 25, 25);
        g.setColor(Color.black);
        g.drawRoundRect(Window.getX(x+20), Window.getYNormal(250), 150, 200, 25, 25);
        g.setFont(new Font("Arial",Font.PLAIN,24));
        g.drawString("" + getText(),Window.getX(x+30),Window.getYNormal(220));
    }
    
    public static White getRandomWhite(){
        int white = (int)(Math.random()*whites.size());
        while(whites.get(white).getUsed() == true){
            white = (int)(Math.random()*whites.size());
            if(whites.get(white).getUsed() == false)
                break;
        }
        return (whites.get(white));
    }
    
}

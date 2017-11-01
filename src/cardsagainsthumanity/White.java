package cardsagainsthumanity;

import java.awt.Graphics2D;
import java.awt.*;
import java.util.ArrayList;

public class White extends Card {
    private static int numWhiteCards;
    private boolean selected;
    protected static ArrayList<White> whites = new ArrayList<White>();
    
    White(String _text){
        super(_text);
    }
    
    public static White Create(String _text){
        White obj = new White(_text);
        obj.selected = false;
        whites.add(obj);
        numWhiteCards++;
        return(obj);
    }
    
    public void draw(Graphics2D g, int x){
        if(selected)
            g.setColor(Color.blue);
        else
            g.setColor(Color.white);
        setXpos(x+20);
        setYpos(250);
        g.fillRoundRect(Window.getX(getXpos()), Window.getYNormal(getYpos()), 150, 200, 25, 25);
        g.setColor(Color.black);
        g.drawRoundRect(Window.getX(getXpos()), Window.getYNormal(getYpos()), 150, 200, 25, 25);
        g.setFont(new Font("Arial",Font.PLAIN,24));
        g.drawString("" + getText(),Window.getX(getXpos()+10),Window.getYNormal(getYpos()-30));
    }
    
    public static White getRandomWhite(){
        boolean allUsed = true;
        for(White obj : whites){
            if(obj.getUsed() == false)
                allUsed = false;
        }
        if(allUsed)
            return null;
        
        int white = (int)(Math.random()*whites.size());
        while(whites.get(white).getUsed() == true){
            white = (int)(Math.random()*whites.size());
            if(whites.get(white).getUsed() == false)
                break;
        }
        return (whites.get(white));
    }
    
    public void checkSelect(int xpos, int ypos){
        
    }
    
    //yo
    
}

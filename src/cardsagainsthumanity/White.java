package cardsagainsthumanity;

import java.awt.Graphics2D;
import java.awt.*;
import java.util.ArrayList;

public class White extends Card {
    private static int numWhiteCards;
    private boolean selected;
    private boolean mouseOver;
    protected static ArrayList<White> whites = new ArrayList<White>();
    
    White(String _text){
        super(_text);
    }
    
    public static White Create(String _text){
        White obj = new White(_text);
        obj.selected = false;
        obj.mouseOver = false;
        whites.add(obj);
        numWhiteCards++;
        return(obj);
    }
    
    public void draw(Graphics2D g, int x){
        Color selectedBlue = new Color(62, 126, 172);
        if(selected)
            g.setColor(selectedBlue);
        else
            g.setColor(Color.white);

        setXpos(x);
        setYpos(260);
        g.fillRoundRect(Window.getX(getXpos()), Window.getYNormal(getYpos()), length, height, 25, 25);
        g.setColor(Color.black);
        g.drawRoundRect(Window.getX(getXpos()), Window.getYNormal(getYpos()), length, height, 25, 25);
        g.setFont(new Font("Arial",Font.PLAIN,20));
        if(mouseOver || selected)
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
    
    public boolean checkSelect(int xpos, int ypos){
        if(xpos > Window.getX(getXpos()) && xpos < Window.getX(getXpos()+length) && ypos > Window.getYNormal(getYpos()) && ypos < Window.getYNormal(getYpos()-height)){
            selected = true;
            return true;
        }
        return false;
    }
    
    public void setSelected(boolean _selected){
        selected = _selected;
    }
    
    public boolean getSelected(){
        return selected;
    }
    
    public void checkMouseOver(int xpos, int ypos){
        if(xpos > Window.getX(getXpos()) && xpos < Window.getX(getXpos()+length) && ypos > Window.getYNormal(getYpos()) && ypos < Window.getYNormal(getYpos()-height)){
            mouseOver = true;
        }
        else
            mouseOver = false;
    }
    
}

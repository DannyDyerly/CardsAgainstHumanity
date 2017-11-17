package cardsagainsthumanity;

import java.awt.Graphics2D;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class White extends Card {
    private Player player;
    private static int numWhiteCards;
    private boolean selected;
    private boolean mouseOver;
    private boolean picked;
    protected static ArrayList<White> whites = new ArrayList<White>();
    
    White(String _text){
        super(_text);
    }
    
    public static White Create(String _text){
        White obj = new White(_text);
        obj.selected = false;
        obj.mouseOver = false;
        obj.picked = false;
        whites.add(obj);
        numWhiteCards++;
        return(obj);
    }
    
    public void draw(Graphics2D g, int x, int y, boolean hidden){
        Color selectedBlue = new Color(62, 126, 172);
        if(selected){
            g.setColor(selectedBlue);
            g.fillRoundRect(Window.getX(getXpos()), Window.getYNormal(getYpos()-260), length, 30, 25, 25);
        }
        else
            g.setColor(Color.white);

        setXpos(x);
        setYpos(y);
        g.fillRoundRect(Window.getX(getXpos()), Window.getYNormal(getYpos()), length, height, 25, 25);
        g.setColor(Color.black);
        g.drawRoundRect(Window.getX(getXpos()), Window.getYNormal(getYpos()), length, height, 25, 25);   
        
        g.setFont(new Font("Arial",Font.PLAIN,20));
        if(hidden == false || mouseOver || selected){
            if(getText().isEmpty()){
                g.drawString("_____",Window.getX(getXpos()+10),Window.getYNormal(getYpos()-30));
                
            }
            
            if (getText().length()>17){
                g.drawString("" + getText().subSequence(0, getWords(18)),Window.getX(getXpos()+10),Window.getYNormal(getYpos()-30));
                if (getText().length()>34){
                    g.drawString("" + getText().subSequence(getWords(18), getWords(35)),Window.getX(getXpos()+10),Window.getYNormal(getYpos()-60));
                    if (getText().length()>51){
                        g.drawString("" + getText().subSequence(getWords(35), getWords(52)),Window.getX(getXpos()+10),Window.getYNormal(getYpos()-90));
                        if (getText().length()>68){
                            g.drawString("" + getText().subSequence(getWords(52), getWords(69)),Window.getX(getXpos()+10),Window.getYNormal(getYpos()-120));
                            if (getText().length()>85){
                                g.drawString("" + getText().subSequence(getWords(69), getWords(86)),Window.getX(getXpos()+10),Window.getYNormal(getYpos()-150));
                                if (getText().length()>102){
                                    g.drawString("" + getText().subSequence(getWords(86), getWords(103)),Window.getX(getXpos()+10),Window.getYNormal(getYpos()-180));
                                    if (getText().length()>119){
                                        g.drawString("" + getText().subSequence(getWords(103), getWords(120)),Window.getX(getXpos()+10),Window.getYNormal(getYpos()-210));
                                        if (getText().length()>136){
                                            g.drawString("" + getText().subSequence(getWords(120), getWords(137)),Window.getX(getXpos()+10),Window.getYNormal(getYpos()-240));
                                        }
                                        else
                                            g.drawString("" + getText().subSequence(getWords(120), getText().length()),Window.getX(getXpos()+10),Window.getYNormal(getYpos()-240));
                                    }
                                    else
                                        g.drawString("" + getText().subSequence(getWords(103), getText().length()),Window.getX(getXpos()+10),Window.getYNormal(getYpos()-210));
                                }
                                else
                                    g.drawString("" + getText().subSequence(getWords(86), getText().length()),Window.getX(getXpos()+10),Window.getYNormal(getYpos()-180));
                            }
                            else
                                g.drawString("" + getText().subSequence(getWords(69), getText().length()),Window.getX(getXpos()+10),Window.getYNormal(getYpos()-150));
                        }
                        else
                            g.drawString("" + getText().subSequence(getWords(52), getText().length()),Window.getX(getXpos()+10),Window.getYNormal(getYpos()-120));
                    }
                    else
                        g.drawString("" + getText().subSequence(getWords(35), getText().length()),Window.getX(getXpos()+10),Window.getYNormal(getYpos()-90));
                }
                else
                    g.drawString("" + getText().subSequence(getWords(18), getText().length()),Window.getX(getXpos()+10),Window.getYNormal(getYpos()-60));
            }
            else
                g.drawString("" + getText(),Window.getX(getXpos()+10),Window.getYNormal(getYpos()-30));
        }
    }
    
    public int getWords(int _amount){
        for (int i = _amount; i>_amount-17;i--)
        {
            if (getText().subSequence(_amount-17, i).toString().endsWith(" ")){
                if (getText().subSequence(_amount-17, i).length()<18)
                    return(i);
            }
        }
        return(_amount);
    }
    
    public static White getRandomWhite(){
        
        boolean allUsed = true;
        for(White obj : whites){
            if(obj.getUsed() == false)
                allUsed = false;
        }
        if(allUsed){
            return null;
        }
        
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
    
    public static void resetCards(){
        for (White obj : whites){
            obj.setUsed(false);
            obj.selected = false;
        }
    }
    
    public void checkMouseOver(int xpos, int ypos){
        if(xpos > Window.getX(getXpos()) && xpos < Window.getX(getXpos()+length) && ypos > Window.getYNormal(getYpos()) && ypos < Window.getYNormal(getYpos()-height)){
            mouseOver = true;
        }
        else
            mouseOver = false;
    }
    
    public void setPlayer(Player _player){
        player = _player;
    }
    
    public Player getPlayer(){
        return player;
    }
    
    public void setPicked(boolean _picked){
        picked = _picked;
    }
    
    public static String blankCard(){
        String answer;
        answer = JOptionPane.showInputDialog(null, "What would you like your card to say?");
        String blankInput = answer;
        return answer;
    }
    
    public static Boolean getSomeSelected(){
        for (White obj : whites){
            if (obj.getSelected())
                return true;
        }
        return false;
    }
    
    public static void resetSelected(){
        for (White ptr : whites)
                    ptr.selected=false;
    }
    
    public static void resetPicked(){
        for (White ptr : whites)
                    ptr.picked=false;
    }
    
}

package cardsagainsthumanity;

import java.awt.Graphics2D;
import java.awt.*;
import java.util.ArrayList;

public class Black extends Card {
    private static int numBlackCards;
    protected static ArrayList<Black> blacks = new ArrayList<Black>();
    
    Black(String _text){
        super(_text);
        numBlackCards++;
    }
    
    public static Black Create(String _text){
        Black obj = new Black(_text);
        blacks.add(obj);
        numBlackCards++;
        return(obj);
    }
    
    public void draw(Graphics2D g){

        setXpos(10);
        setYpos(860);
        g.fillRoundRect(Window.getX(getXpos()), Window.getYNormal(getYpos()), length, height, 25, 25);
        g.setColor(Color.black);
        g.drawRoundRect(Window.getX(getXpos()), Window.getYNormal(getYpos()), length, height, 25, 25);
        
        g.setFont(new Font("Arial",Font.PLAIN,20));
        
        g.setColor(Color.white);
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
    
    public int getWords(int _amount){
        for (int i = _amount; i>_amount-18;i--)
        {
            if (getText().subSequence(_amount-18, i).toString().endsWith(" ")){
                if (getText().subSequence(_amount-18, i).length()<18)
                    return(i);
            }
        }
        return(_amount);
    }
    
    public static Black getRandomBlack(){
        
        boolean allUsed = true;
        for(Black obj : blacks){
            if(obj.getUsed() == false)
                allUsed = false;
        }
        if(allUsed)
            return null;
        
        int black = (int)(Math.random()*blacks.size());
        while(blacks.get(black).getUsed() == true){
            black = (int)(Math.random()*blacks.size());
            if(blacks.get(black).getUsed() == false)
                break;
        }
        return (blacks.get(black));
    }
    
    public static void resetCards(){
        for (Black obj : blacks){
            obj.setUsed(false);
        }
    }
    
}

package cardsagainsthumanity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Button {
    private int length;
    private int extra;
    private int height;
    private String text;
    private int xpos;
    private int ypos;
    private boolean show;
    private boolean mouseOver;
    static int name;
    private static ArrayList<Button> buttons = new ArrayList<Button>();
    
    Button(){
        
    }
    
    Button(String _text, int _xpos, int _ypos, boolean _show, int _extra){
        text = _text;
        extra = _extra;
        length = _text.length()*12-extra;
        height = 30;
        xpos = _xpos;
        ypos = _ypos;
        show = _show;
        mouseOver = false;
        name = 1;
    }
    
    public static void Create(String _text, int _xpos, int _ypos, boolean _show, int _extra){
        Button obj = new Button(_text, _xpos, _ypos, _show, _extra);
        buttons.add(obj);
    }
    
    public static void DrawButtons(Graphics2D g){
        for(Button obj : buttons){
            if(obj.show){
                Color mouseOverBlue = new Color(62, 126, 172);
                if(obj.mouseOver)
                    g.setColor(mouseOverBlue);
                else
                    g.setColor(Color.white);
                g.fillRoundRect(Window.getX(obj.xpos), Window.getYNormal(obj.ypos), obj.length, obj.height, 25, 25);
                g.setColor(Color.black);
                g.drawRoundRect(Window.getX(obj.xpos), Window.getYNormal(obj.ypos), obj.length, obj.height, 25, 25);
                g.setFont(new Font("Arial",Font.PLAIN,20));
                if(obj.text == "Confirm Selection")
                    g.drawString("" + obj.text, Window.getX(obj.xpos+22), Window.getYNormal(obj.ypos-22));
                else
                    g.drawString("" + obj.text, Window.getX(obj.xpos+9), Window.getYNormal(obj.ypos-22));
            }
        }
    }
    
    public static void checkMouseOver(int _xpos, int _ypos){
        for(Button obj : buttons){
            if(_xpos > Window.getX(obj.xpos) && _xpos < Window.getX(obj.xpos+obj.length+obj.extra) && _ypos > Window.getYNormal(obj.ypos) && _ypos < Window.getYNormal(obj.ypos-obj.height)){
                obj.mouseOver = true;
            }
            else
                obj.mouseOver = false;
        }
    }
    
    public static boolean checkStart(boolean inGame){
        if(inGame == false){
            for(Button obj : buttons){
                if(obj.text == "Start" && obj.mouseOver && Player.getNumPlayers() >= 3){
                    obj.show = false;
                    for(Button ref : buttons){
                        if(ref.text == "Add Player")
                            ref.show = false;
                    }
                    for(Button ptr : buttons){
                        if(ptr.text == "Confirm Selection")
                            ptr.show = true;
                    }
                    for(int i=0; i<9; i++){
                        Player.DrawCards();
                    }
                    return true;
                }
            }
            return false;
        }
        return true;
    }
    
    public static void confirmSelection(){
        for(Button obj : buttons){
            if(obj.text == "Confirm Selection" && obj.mouseOver && Player.getCzar()){
                Player.pickWinner();
            }
            else{
                if(obj.text == "Confirm Selection" && obj.mouseOver){
                    Player.removeCard();
                    Player.addCard();
                }
            }
        }
    }
    
    public static void addPlayer(){
        if (Player.getNumPlayers()<5){
            for(Button obj : buttons){
                if(obj.text == "Add Player" && obj.mouseOver){
                    Player.Create("Player "+name);
                    name++;
                }
            }
        }
    }
    
}

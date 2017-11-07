package cardsagainsthumanity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Player {
    private static int numPlayers;
    private static int cardCzar;
    private static int turn = 2;
    private boolean czar = false;
    private int numCards;
    private String name;
    private int number;
    private int score;
    private static ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<White> hand = new ArrayList<White>();
    private static ArrayList<White> picked = new ArrayList<White>();
    
    Player(){
        
    }
    
    Player(String _name, int _number, boolean _czar){
        name = _name;
        number = _number;
        czar = _czar;
        score=0;
    }
    
    public static void Create(String _name){
        numPlayers++;
        Player obj = null;
        if(numPlayers == 1)
            obj = new Player(_name, numPlayers, true);
        else
            obj = new Player(_name, numPlayers, false);
        players.add(obj);
    }
    
    public static void DrawHand(Graphics2D g){
        for(Player obj : players){
            if(turn == obj.number && obj.czar){
                g.setColor(Color.black);
                g.drawRoundRect(Window.getX(20), Window.getYNormal(260), 1860, 240, 25, 25);
                g.setFont(new Font("Arial",Font.BOLD,24));
                g.drawString("You are the Card Czar", Window.getX(800), Window.getYNormal(150));
            }
            else if(turn == obj.number){
                int x = 10;
                int y = 260;
                for(White ptr: obj.hand){
                    ptr.draw(g, x, y, true);
                    x += 210;
                }
            }
        }
    }
    
    public static void DrawCards(){
        for(Player obj : players){
            White addWhite = White.getRandomWhite();
            if(addWhite == null || obj.hand.size()>=9)
                return;
            addWhite.setUsed(true);
            obj.hand.add(addWhite);
            addWhite.setPlayer(obj);
        }
        
    }
    
    public static void addCard(){
        for(Player obj : players){
            if(turn == obj.number){
                White addWhite = White.getRandomWhite();
                if(addWhite == null || obj.hand.size()>=9)
                    return;
                addWhite.setUsed(true);
                obj.hand.add(addWhite);
                addWhite.setPlayer(obj);
            }
        }
    }
    
    public static void CheckSelect(int xpos, int ypos){
        for(Player obj : players){
            if(turn == obj.number){
                for(White ptr : obj.hand){
                    boolean success = ptr.checkSelect(xpos, ypos);
                    if(success){
                        for(White ref : obj.hand){
                            if(ref != ptr)
                                ref.setSelected(false);
                        }
                    }
                }
            }
        }
    }
    
    public static void CheckSelectCzar(int xpos, int ypos){
        for(Player obj : players){
            if(turn == obj.number && obj.czar){
                for(White ptr : picked){
                    boolean success = ptr.checkSelect(xpos, ypos);
                    if(success){
                        for(White ref : picked){
                            if(ref != ptr)
                                ref.setSelected(false);
                        }
                    }
                }
            }
        }
    }
    
    public static White getSelected(){
        for(Player obj : players){
            if(turn == obj.number){
                for(White ptr : obj.hand){
                    if(ptr.getSelected()){
                        return ptr;
                    }
                }
            }
        }
        return null;
    }
    
    public static void removeCard(){
        for(Player obj : players){
            if(turn == obj.number){
                if(getSelected() != null){
                    White selected = getSelected();
                    addPickedCard(selected);
                    obj.hand.remove(selected);
                    selected.setSelected(false);
                }
            }
            if (obj.hand.isEmpty()){
                White.resetCards();
                for (int i =0;i<9;i++)
                obj.fillHand();
            }
        }
    }
    
    public void fillHand(){
            White addWhite = White.getRandomWhite();
            if(addWhite == null || hand.size()>=9)
                return;
            addWhite.setUsed(true);
            hand.add(addWhite);
    }
    
    public static void drawScores(Graphics2D g){
        int x = 1590;
        int y = 890;
        for (Player obj : players){
            g.setColor(Color.BLACK);
            g.drawRect(Window.getX(x), Window.getYNormal(y), 300, 30);
            g.setFont(new Font("Arial",Font.PLAIN,20));
            g.drawString(obj.name+" - "+obj.score, Window.getX(x+7), Window.getYNormal(y-23));
            y-=30;
        }
    }
    
    public static void checkMouseOver(int xpos, int ypos){
        for(Player obj : players){
            if(turn == obj.number){
                for(White ptr : obj.hand){
                    ptr.checkMouseOver(xpos, ypos);
                }
            }
        }
    }
    
    public static void checkMouseOverCzar(int xpos, int ypos){
        for(Player obj : players){
            if(turn == obj.number && obj.czar){
                for(White ptr : picked){
                    ptr.checkMouseOver(xpos, ypos);
                }
            }
        }
    }
    
    public static void addPickedCard(White _picked){
        picked.add(_picked);
        changeTurn();
    }
    
    public static void changeTurn(){
        turn++;
        for(Player obj : players){
            if(turn == obj.number && obj.czar && turn < numPlayers)
                turn++;
        }
        if(turn > numPlayers){
            turn = 1;
        }
    }
    
    public static void DrawPickedCards(Graphics2D g){
        int x = 220;
        int y = 890;
        boolean hide = true;
        for(Player obj : players){
            if(turn == obj.number && obj.czar){
                hide = false;
            }
        }
        for(White obj : picked){
            if(hide)
                obj.draw(g, x, y, true);
            else
                obj.draw(g, x, y, false);
            x += 210;
        }
    }
    
    public static int getTurn(){
        return turn;
    }
    
    public static int getNumPlayers(){
        return numPlayers;
    }
    
    public static void printHelp(Graphics2D g){
        for(Player obj : players){
            if(turn == obj.number){
                if(obj.czar){
                    boolean pickedSelected = false;
                    for(White ptr : picked){
                        if(ptr.getSelected())
                            pickedSelected = true;
                    }
                    Color selectedBlue = new Color(62, 126, 172);
                    g.setColor(selectedBlue);
                    g.setFont(new Font("Arial",Font.PLAIN,20));
                    if(pickedSelected)
                        g.drawString("Click ''Confirm Selection'' when ready!", Window.getX(20), Window.getYNormal(400));
                    else
                        g.drawString("Read the black card filling in the spaces with the white /n cards and select the funniest one!", Window.getX(20), Window.getYNormal(400));

                }
                else{
                    boolean selected = false;
                    for(White ptr : obj.hand){
                        if(ptr.getSelected())
                            selected = true;
                    }
                    Color selectedBlue = new Color(62, 126, 172);
                    g.setColor(selectedBlue);
                    g.setFont(new Font("Arial",Font.PLAIN,20));
                    if(selected)
                        g.drawString("Click ''Confirm Selection'' when ready!", Window.getX(20), Window.getYNormal(400));
                    else
                        g.drawString("Read through your hand of white cards and select the one that fills in the black card to make it the funniest!", Window.getX(20), Window.getYNormal(400));
                }
            }
        }
    }
    
}

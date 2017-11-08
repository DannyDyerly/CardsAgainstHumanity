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
                g.drawRoundRect(Window.getX(10), Window.getYNormal(260), 1880, 250, 25, 25);
                g.setFont(new Font("Arial",Font.BOLD,24));
                g.drawString("You are the Card Czar", Window.getX(820), Window.getYNormal(140));
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
    
    public static void drawScores(Graphics2D g, boolean inGame){
        int x = 1490;
        int y = 890;
        g.setColor(Color.BLACK);
        g.drawRoundRect(Window.getX(x), Window.getYNormal(y), 400, 30,25,25);
        g.setFont(new Font("Arial",Font.PLAIN,40));
        if (inGame){
            g.drawRoundRect(Window.getX(400), Window.getYNormal(890), 1080, 80,25,25);
            g.drawRoundRect(Window.getX(10), Window.getYNormal(890), 380, 80, 25, 25);
            g.drawRoundRect(Window.getX(1060), Window.getYNormal(800), 420, 290,25,25);
            g.drawRoundRect(Window.getX(220), Window.getYNormal(540), 830, 30,25,25);
            g.drawString("Waiting for players...", Window.getX(20), Window.getYNormal(900-53));
        }
        g.setFont(new Font("Arial",Font.PLAIN,20));
        g.drawString("Scoreboard", Window.getX(x+7), Window.getYNormal(y-23));
        g.setFont(new Font("Arial",Font.PLAIN,15));
        if (inGame)
        g.drawString("The black card for this round is:", Window.getX(20), Window.getYNormal(900-80));
        g.setFont(new Font("Arial",Font.PLAIN,20));
        y-=40;
        g.drawRoundRect(Window.getX(x), Window.getYNormal(y), 400, 60,25,25);
        g.drawRoundRect(Window.getX(x), Window.getYNormal(y-70), 400, 60,25,25);
        g.drawRoundRect(Window.getX(x), Window.getYNormal(y-140), 400, 60,25,25);
        g.drawRoundRect(Window.getX(x), Window.getYNormal(y-210), 400, 60,25,25);
        g.drawRoundRect(Window.getX(x), Window.getYNormal(y-280), 400, 60,25,25);
        for (Player obj : players){
            g.drawString(obj.name, Window.getX(x+7), Window.getYNormal(y-23));
            g.drawString(obj.score + " Awesome Points",Window.getX(x+7), Window.getYNormal(y-53));
            if (obj.czar){
                g.drawString("Selecting",Window.getX(x+250), Window.getYNormal(y-36));
            }
            y-=70;
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
        int y = 800;
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
        g.setColor(Color.black);
        g.setFont(new Font("Arial",Font.BOLD,60));
        g.drawString("Cards Against Humanity", Window.getX(575), Window.getYNormal(830));
        g.setFont(new Font("Arial",Font.PLAIN,30));
        Color selectedBlue = new Color(62, 126, 172);
        g.setColor(selectedBlue);
        g.drawString("Help",Window.getX(20) , Window.getYNormal(465));
        g.drawRoundRect(Window.getX(10), Window.getYNormal(500), 1880, 230, 25, 25);
        g.setFont(new Font("Arial",Font.PLAIN,20));
        for(Player obj : players){
            if(turn == obj.number){
                if(obj.czar){
                    boolean pickedSelected = false;
                    for(White ptr : picked){
                        if(ptr.getSelected())
                            pickedSelected = true;
                    }
                    if(pickedSelected)
                        g.drawString("Click ''Confirm Selection'' when ready!", Window.getX(20), Window.getYNormal(325));
                    else{
                        g.drawString("Read the black card with the white cards as answers and select the funniest one! Click to select.", Window.getX(20), Window.getYNormal(385));
                    }
                }
                else{
                    boolean selected = false;
                    for(White ptr : obj.hand){
                        if(ptr.getSelected())
                            selected = true;
                    }
                    if(selected)
                        g.drawString("Click ''Confirm Selection'' when ready!", Window.getX(20), Window.getYNormal(325));
                    else{
                        g.drawString("Read through your hand of white cards and select the one that answers the black card the funniest! Hover over the white cards to view them. Click to select.", Window.getX(20), Window.getYNormal(385));
                    }
                }
            }
        }
    }
    
}

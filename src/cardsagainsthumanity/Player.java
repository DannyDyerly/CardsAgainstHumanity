package cardsagainsthumanity;

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
    private static ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<White> hand = new ArrayList<White>();
    private static ArrayList<White> picked = new ArrayList<White>();
    
    Player(){
        
    }
    
    Player(String _name, int _number, boolean _czar){
        name = _name;
        number = _number;
        System.out.println(number);
        czar = _czar;
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
            if(turn == obj.number){
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
        if(turn > numPlayers)
            turn = 1;
    }
    
    public static void DrawPickedCards(Graphics2D g){
        int x = 220;
        int y = 890;
        for(White obj : picked){
            obj.draw(g, x, y, false);
            x += 210;
        }
    }
    
    public static int getTurn(){
        return turn;
    }
    
}

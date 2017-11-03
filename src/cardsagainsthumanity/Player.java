package cardsagainsthumanity;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Player {
    private static int numPlayers;
    private static int cardCzar;
    private static int turn = 1;
    private boolean czar = false;
    private int numCards;
    private String name;
    private int number;
    private static ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<White> hand = new ArrayList<White>();
    
    Player(){
        
    }
    
    Player(String _name, int _number, boolean _czar){
        name = _name;
        number = _number;
        czar = _czar;
    }
    
    public static void Create(String _name, boolean _czar){
        numPlayers++;
        Player obj = new Player(_name, numPlayers, _czar);
        players.add(obj);
    }
    
    public static void DrawHand(Graphics2D g){
        for(Player obj : players){
            if(turn == obj.number){
                int x = 10;
                for(White ptr: obj.hand){
                    ptr.draw(g, x);
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
                    System.out.println(getSelected().getText() + " removed");
                    obj.hand.remove(getSelected());
                }
            }
        }
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
    
}

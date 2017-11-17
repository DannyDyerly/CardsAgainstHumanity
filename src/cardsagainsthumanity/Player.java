package cardsagainsthumanity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private static int numPlayers;
    private static int turn = 2;
    private static boolean nextRound = false;
    private boolean czar = false;
    private int numCards;
    private String name;
    private int number;
    private int score;
    private static boolean randomize = false;
    private static boolean dispWin = false;
    private static String winName;
    private static ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<White> hand = new ArrayList<White>();
    private static ArrayList<White> picked = new ArrayList<White>();
    private static ArrayList<White> picked2 = new ArrayList<White>();
    
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
                g.drawString("You are the Card Czar", Window.getX(820), Window.getYNormal(135));
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
        if(!nextRound){
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
    }
    
    public static White getSelectedCzar(){
        for(Player obj : players){
            if(turn == obj.number && obj.czar){
                for(White ptr : picked){
                    if(ptr.getSelected()){
                        return ptr;
                    }
                }
            }
        }
        return null;
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
                    if(selected.getText() == "")
                        selected.setText(White.blankCard());
                    if(selected.getText() == null){
                        selected.setText("");
                        return;
                    }
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
        if (dispWin)
            dispWin(winName,g);
        int x = 1490;
        int y = 890;
        g.setColor(Color.BLACK);
        g.drawRoundRect(Window.getX(x), Window.getYNormal(y), 400, 30,25,25);
        g.setFont(new Font("Arial",Font.PLAIN,40));
        if (inGame){
            g.drawRoundRect(Window.getX(400), Window.getYNormal(890), 1080, 80,25,25);
            g.drawRoundRect(Window.getX(10), Window.getYNormal(890), 380, 80, 25, 25);
            g.drawRoundRect(Window.getX(1060), Window.getYNormal(800), 420, 250,25,25);
            boolean asdf = false;
            for(Player obj : players){
                if(obj.number == turn && obj.czar){
                    asdf = true;
                }
            }
            if(asdf){
                if (nextRound){
                    boolean asdf1 = false;
                    for (Player obj : players){
                        if (obj.score>=5)
                            asdf1 = true;
                    }
                    if (asdf1)
                        g.drawString("Waiting for game...", Window.getX(20), Window.getYNormal(900-53));
                    else
                        g.drawString("Waiting for round...", Window.getX(20), Window.getYNormal(900-53));
                }
                else
                    g.drawString("Selecting winner...", Window.getX(20), Window.getYNormal(900-53));
            }
            else
                g.drawString("Waiting for players...", Window.getX(20), Window.getYNormal(900-53));
        }
        g.setFont(new Font("Arial",Font.PLAIN,20));
        g.drawString("Scoreboard", Window.getX(x+7), Window.getYNormal(y-23));
        g.setFont(new Font("Arial",Font.PLAIN,15));
        if (inGame)
        g.drawString("The black card for this round is:", Window.getX(20), Window.getYNormal(900-80));
        g.setFont(new Font("Arial",Font.PLAIN,20));
        y-=40;
        Color selectedBlue = new Color(62, 126, 172);
        g.setColor(selectedBlue);
        if (inGame){
        if (turn==1)
            g.fillRoundRect(Window.getX(x), Window.getYNormal(y), 400, 60,25,25);
        else if (turn==2)
            g.fillRoundRect(Window.getX(x), Window.getYNormal(y-70), 400, 60,25,25);
        else if (turn==3)
            g.fillRoundRect(Window.getX(x), Window.getYNormal(y-140), 400, 60,25,25);
        else if (turn==4)
            g.fillRoundRect(Window.getX(x), Window.getYNormal(y-210), 400, 60,25,25);
        else
            g.fillRoundRect(Window.getX(x), Window.getYNormal(y-280), 400, 60,25,25);
        }
        g.setColor(Color.black);
        g.drawRoundRect(Window.getX(x), Window.getYNormal(y), 400, 60,25,25);
        g.drawRoundRect(Window.getX(x), Window.getYNormal(y-70), 400, 60,25,25);
        g.drawRoundRect(Window.getX(x), Window.getYNormal(y-140), 400, 60,25,25);
        g.drawRoundRect(Window.getX(x), Window.getYNormal(y-210), 400, 60,25,25);
        g.drawRoundRect(Window.getX(x), Window.getYNormal(y-280), 400, 60,25,25);
        for (Player obj : players){
            if(obj.name.length()<10)
                g.drawString(obj.name, Window.getX(x+7), Window.getYNormal(y-23));
            else
                g.drawString(obj.name.substring(0, 9), Window.getX(x+7), Window.getYNormal(y-23));
            g.drawString(obj.score + " Awesome Points",Window.getX(x+7), Window.getYNormal(y-53));
            if (obj.czar){
                g.drawString("Card Czar",Window.getX(x+250), Window.getYNormal(y-36));
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
        if(turn > numPlayers){
            turn = 1;
        }
        for(Player obj : players){
            if(turn == obj.number && obj.czar)
                randomize = true;
        }
    }
    
    public static void DrawPickedCards(Graphics2D g){
        int x = 220;
        int y = 800;
        if(randomize)
            randomize();

        boolean hide = true;
        for(Player obj : players){
            if(turn == obj.number && obj.czar){
                hide = false;
            }
        }
        for(White obj : picked){
                g.drawRoundRect(Window.getX(x), Window.getYNormal(540), 200, 30,25,25);
                g.setFont(new Font("Arial",Font.PLAIN,20));
            if(hide){
                obj.draw(g, x, y, true);
            }
            else{
                obj.draw(g, x, y, false);
                g.setFont(new Font("Arial",Font.PLAIN,20));
                if (nextRound){
                    if (obj.getPlayer().name.length()<10)
                    g.drawString(""+obj.getPlayer().name, x+20, Window.getYNormal(518));
                    else
                    g.drawString(""+obj.getPlayer().name.subSequence(0, 9), x+20, Window.getYNormal(518));
                }
            }
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
        g.drawString("If your nameplate is highlighted in the scoreboard, it is your turn.",Window.getX(20) , Window.getYNormal(350));
        for(Player obj : players){
            if(turn == obj.number){
                if(obj.czar){
                    if(nextRound){
                        g.drawString("Click ''Next Round'' when ready!", Window.getX(20), Window.getYNormal(420));
                    }
                    else{
                        boolean pickedSelected = false;
                        for(White ptr : picked){
                            if(ptr.getSelected())
                                pickedSelected = true;
                        }
                        if(pickedSelected)
                            g.drawString("Click ''Confirm Selection'' when ready!", Window.getX(20), Window.getYNormal(420));
                        else{
                            g.drawString("Read the black card with the white cards as answers and select the funniest one! Click to select.", Window.getX(20), Window.getYNormal(420));
                        }
                    }
                }
                else{
                    g.drawString("White cards with no text can be selected to type whatever you want.",Window.getX(20) , Window.getYNormal(325));
                    g.drawString("Make sure no other players are peeking while it's your turn!",Window.getX(20) , Window.getYNormal(300));
                    boolean selected = false;
                    White selectedCard = null;
                    for(White ptr : obj.hand){
                        if(ptr.getSelected()){
                            selected = true;
                            selectedCard = ptr;
                        }
                    }
                    if(selected && selectedCard.getText().isEmpty())
                        g.drawString("Click ''Confirm Selection'' when ready to type in whatever you want!", Window.getX(20), Window.getYNormal(420));
                    else if(selected)
                        g.drawString("Click ''Confirm Selection'' when ready!", Window.getX(20), Window.getYNormal(420));
                    else
                        g.drawString("Read through your hand of white cards and select the one that answers the black card the funniest! Hover over the white cards to view them. Click to select.", Window.getX(20), Window.getYNormal(420));
                }
            }
        }
    }
    
    public static boolean getCzar(){
        for(Player obj : players){
            if(turn == obj.number && obj.czar)
                return true;
        }
        return false;
    }
    
    public static boolean getNextRound(){
        return nextRound;
    }
    
    public static void pickWinner(){
        for(Player obj : players){
            if(turn == obj.number && obj.czar){
                if(getSelectedCzar() != null){
                    White winner = getSelectedCzar();
                    addPoint(winner.getPlayer());
                    //winner.setSelected(false);
                    nextRound = true;
                    return;
                }
            }
        }
    }
    
    public static void nextRound(){
        nextRound = false;
        picked.clear();
        for(Player obj : players){
            if(turn == obj.number && obj.czar)
                obj.czar = false;
        }
        changeTurn();
        for(Player ptr : players){
            if(ptr.number == turn){
                ptr.czar = true;
            }
        }
        changeTurn();
    }
    
    public static void addPoint(Player _player){
        for(Player obj : players){
            if(obj == _player){
                obj.score++;
                dispWin = true;
                winName = obj.name;
            }
        }
    }
    
    public static boolean checkUniqueName(String _name){
        for(Player obj : players){
            if(obj.name.equals(_name)){
                return false;
            }
        }
        return true;
    }
    
    public static void dispWin(String winner, Graphics2D g){
        if (winner.isEmpty())
            return;
        else{
            
            g.setFont(new Font("Arial",Font.PLAIN,30));
            g.setColor(Color.black);
            for (Player obj : players){
                if (obj.name.equals(winner)&&obj.score>=5){
                    Button.setBoo(true);
                    if (winner.length()<10)
                g.drawString(winner + " is the", Window.getX(1070), Window.getYNormal(680));
                else
                    g.drawString(""+winner.subSequence(0, 9)+" is the", Window.getX(1070), Window.getYNormal(680));     
                g.drawString("winner of this game!", Window.getX(1070), Window.getYNormal(650));
                   return; 
                }
                
            }
            if (winner.length()<10)
                g.drawString(winner + " is the", Window.getX(1070), Window.getYNormal(680));
            else
                g.drawString(""+winner.subSequence(0, 9)+" is the", Window.getX(1070), Window.getYNormal(680));     
            g.drawString("winner of this round!", Window.getX(1070), Window.getYNormal(650));
        }
    }
    
    public static void resetWin(){
        dispWin = false;
    }
    
    private static void randomize(){
        Collections.shuffle(picked);
        randomize = false;
    }
    
    public static void playerCards(){
        int p = (int)(Math.random()*numPlayers);
        Black card50 = Black.Create(players.get(p).name + " enjoys _____ when he/she gets home from school.");
        p = (int)(Math.random()*numPlayers);
        Black card49 = Black.Create("We all know that " + players.get(p).name + " loves _____. It's not a secret.");
        p = (int)(Math.random()*numPlayers);
        Black card48 = Black.Create(players.get(p).name + " spent the weekend _____.");
        p = (int)(Math.random()*numPlayers);
        int p2 = 0;
        while(p2 == p){
            p2 = (int)(Math.random()*numPlayers);
        }
        Black card47 = Black.Create("I think " + players.get(p).name + " likes _____ with " + players.get(p2).name+".");
        p = (int)(Math.random()*numPlayers);
        Black card46 = Black.Create(players.get(p).name + " just can't live without _____.");
        p = (int)(Math.random()*numPlayers);
        Black card45 = Black.Create("What the heck? This tastes like " + players.get(p).name + "'s _____.");
        p = (int)(Math.random()*numPlayers); 
        Black card44 = Black.Create("" + players.get(p).name + " is the god of _____.");
        p = (int)(Math.random()*numPlayers);
        while(p2 == p){
            p2 = (int)(Math.random()*numPlayers);
        }
        Black card43 = Black.Create("" + players.get(p).name + " softly rubs _____ against " + players.get(p2).name+"'s face at night.");
        p = (int)(Math.random()*numPlayers);
        while(p2 == p){
            p2 = (int)(Math.random()*numPlayers);
        }
        Black card42 = Black.Create("On Christmas, " + players.get(p).name + " gave " + players.get(p2).name+" _____.");
        p = (int)(Math.random()*numPlayers);
        while(p2 == p){
            p2 = (int)(Math.random()*numPlayers);
        }
        Black card41 = Black.Create(players.get(p).name + " and " + players.get(p2).name + " are secretly planning on _____.");
        p = (int)(Math.random()*numPlayers);
        Black card40 = Black.Create("My name is " + players.get(p).name + ", but my family calls me _____.");
        p = (int)(Math.random()*numPlayers);
        while(p2 == p){
            p2 = (int)(Math.random()*numPlayers);
        }
        Black card39 = Black.Create(players.get(p).name + " and " + players.get(p2).name + " were _____ last night.");
        p = (int)(Math.random()*numPlayers);
        Black card38 = Black.Create(players.get(p).name + " keeps _____ in his/her closet.");
        p = (int)(Math.random()*numPlayers);
        Black card37 = Black.Create(players.get(p).name + " eats _____ for breakfast.");
        p = (int)(Math.random()*numPlayers);
        Black card36 = Black.Create(players.get(p).name + " likes _____ in the shower.");
        p = (int)(Math.random()*numPlayers);
        Black card35 = Black.Create(players.get(p).name + " has 400 photos of _____ on his/her phone.");
    }
    
    public static void remove(){
        for(Player obj : players){
            if(obj.number == numPlayers){
                players.remove(obj);
                numPlayers--;
                return;
            }
        }
    }
    
    public static void reset(){
        for (Player obj : players){
                obj.score=0;
                obj.hand.clear();
        }
        nextRound =false;
        dispWin=false;
        picked.clear();
    }
    
    public static void resetCzar(){
        for (Player obj : players){
            if (obj.czar==true)
                obj.czar=false;
            if (obj.number==1)
                obj.czar=true;
        }
        turn=2;
    }
    
}

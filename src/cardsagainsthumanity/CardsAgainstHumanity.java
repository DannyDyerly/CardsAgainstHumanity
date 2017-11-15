package cardsagainsthumanity;

import java.io.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class CardsAgainstHumanity extends JFrame implements Runnable {
    boolean animateFirstTime = true;
    Image image;
    Graphics2D g;
    
    public static boolean inGame = false;

    Black card1 = Black.Create("_____. That's how I want to die.");
    Black card2 = Black.Create("_____. Pretty fun on a Sunday.");
    Black card3 = Black.Create("If I met a genie, I would just wish for _____ three times.");
    Black card4 = Black.Create("4 out of 5 dentists recommend _____.");
    Black card5 = Black.Create("Ask your doctor if _____ is right for you.");
    Black card6 = Black.Create("I can't believe you like _____ too! We should hang out.");
    Black card7 = Black.Create("I had to write a 4000 word essay about _____.");
    Black card8 = Black.Create("I was into _____ before it was cool.");
    Black card9 = Black.Create("_____. The best thing since sliced bread.");
    Black card10 = Black.Create("I hope grandma doesn't ask me to explain _____ again.");
    Black card11 = Black.Create("Steve can carry 64 stacks of _____.");
    Black card12 = Black.Create("I broke six ribs while _____.");
    Black card13 = Black.Create("Grandma's losing it. She got me _____ for my birthday.");
    Black card14 = Black.Create("There was a riot at the _____ TED Talk (for obvious reasons).");
    Black card15 = Black.Create("Hurry, call the vet! The dog ate _____!");
    Black card16 = Black.Create("Yeah, turns out that microwaving _____ was a bad idea.");
    Black card17 = Black.Create("When the coffee machine broke, we started using _____ as an alternative.");
    Black card18 = Black.Create("When we went to the zoo the penguins were _____.");
    Black card19 = Black.Create("_____? There's an app for that.");
    Black card20 = Black.Create("Why can't I sleep at night?");
    Black card21 = Black.Create("BILLY MAYS HERE FOR _____.");
    Black card22 = Black.Create("What don't you want to find in your Chinese food?");
    Black card23 = Black.Create("Studies show that lab rats navigate mazes 50% faster after being exposed to _____.");
    Black card24 = Black.Create("What never fails to liven up the party?");
    Black card25 = Black.Create("Who ate my chicken nuggets?");
    Black card26 = Black.Create("I don't know what weapons World War III will be fought with, but World War IV will be fought with _____.");
    Black card27 = Black.Create("What are my parents hiding from me?");
    Black card28 = Black.Create("What's that sound?");
    Black card29 = Black.Create("_____. That's how I want to die.");
    Black card30 = Black.Create("_____. Pretty fun on a Sunday.");
    Black card31 = Black.Create("_____. That's how I want to die.");
    Black card32 = Black.Create("_____. Pretty fun on a Sunday.");
    Black card33 = Black.Create("_____. That's how I want to die.");
    Black card34 = Black.Create("_____. Pretty fun on a Sunday.");
    Black card35 = Black.Create("_____. That's how I want to die.");
    Black card36 = Black.Create("_____. Pretty fun on a Sunday.");
    Black card37 = Black.Create("_____. That's how I want to die.");
    Black card38 = Black.Create("_____. Pretty fun on a Sunday.");
    
    White card51 = White.Create("Coding until your fingers are bleeding.");
    White card52 = White.Create("Mr. Yee in a yoda costume.");
    White card53 = White.Create("A dragon named Harold.");
    White card54 = White.Create("Messing up your gitBash and losing 13 hours of code.");
    White card55 = White.Create("Yoda Yee.");
    White card56 = White.Create("Yeesus.");
    White card57 = White.Create("Beating your grandma with a field hockey stick.");
    White card58 = White.Create("Nothing. Life sucks.");
    White card59 = White.Create("Buying a robot kit for your daughter.");
    White card60 = White.Create("Coming to the middle.");
    White card61 = White.Create("Coming to the middle... again.");
    White card62 = White.Create("Chewing a grapefruit-sized scab off a hobo's back.");
    White card63 = White.Create("TheLengend27.");
    White card64 = White.Create("A sad fat dragon with no friends.");
    White card65 = White.Create("White people.");
    White card66 = White.Create("All-you-can-eat shrimp for $4.99.");
    White card67 = White.Create("Powerful thighs.");
    White card68 = White.Create("Multiple stab wounds.");
    White card69 = White.Create("The Make-A-Wish Foundation.");
    White card70 = White.Create("Hot Pockets.");
    White card71 = White.Create("The Kool-Aid man.");
    White card72 = White.Create("Switching to Geico.");
    White card73 = White.Create("Friends who eat all your snacks.");
    White card74 = White.Create("");
    White card75 = White.Create("");
    White card76 = White.Create("");
    White card77 = White.Create("");
    White card78 = White.Create("");
    White card79 = White.Create("");
    White card80 = White.Create("");
    White card81 = White.Create("");
    White card82 = White.Create("");
    White card83 = White.Create("");
    White card84 = White.Create("");
    White card85 = White.Create("");
    White card86 = White.Create("");
    White card87 = White.Create("");
    White card88 = White.Create("");
    White card89 = White.Create("");
    

    static CardsAgainstHumanity frame;
    public static void main(String[] args) {
        frame = new CardsAgainstHumanity();
        frame.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public CardsAgainstHumanity() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.BUTTON1 == e.getButton()) {
                    //left button

// location of the cursor.
                    int xpos = e.getX();
                    int ypos = e.getY();
                    
                    inGame = Button.checkStart(inGame);
                    Button.addPlayer();
                    Button.removePlayer();
                    
                    if(inGame){
                        Player.CheckSelect(xpos, ypos);
                        Player.CheckSelectCzar(xpos, ypos);
                        Button.confirmSelection();
                        Button.nextRound();
                    }
 
                }
                if (e.BUTTON3 == e.getButton()) {
                    //right button
                    //reset();
                }
                repaint();
            }
        });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        repaint();
      }
    });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
        int xpos = e.getX();
        int ypos = e.getY();
        Button.checkMouseOver(xpos, ypos);
        Player.checkMouseOver(xpos, ypos);
        Player.checkMouseOverCzar(xpos, ypos);
        repaint();
      }
    });

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.VK_W == e.getKeyCode()) {

                } else if (e.VK_S == e.getKeyCode()) {

                } else if (e.VK_A == e.getKeyCode()) {

                } else if (e.VK_D == e.getKeyCode()) {

                } else if (e.VK_SPACE == e.getKeyCode()) {   
                    Black.setNextRoundTrue();
                    Black.getRandomBlack();
                }
                repaint();
            }
        });
        init();
        start();
    }
    Thread relaxer;
////////////////////////////////////////////////////////////////////////////
    public void init() {
        requestFocus();
    }
////////////////////////////////////////////////////////////////////////////
    public void destroy() {
    }
////////////////////////////////////////////////////////////////////////////
    public void paint(Graphics gOld) {
        if (image == null || Window.xsize != getSize().width || Window.ysize != getSize().height) {
            Window.xsize = getSize().width;
            Window.ysize = getSize().height;
            image = createImage(Window.xsize, Window.ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }
//fill background
        g.setColor(Color.black);
        g.fillRect(0, 0, Window.xsize, Window.ysize);

        int x[] = {Window.getX(0), Window.getX(Window.getWidth2()), Window.getX(Window.getWidth2()), Window.getX(0), Window.getX(0)};
        int y[] = {Window.getY(0), Window.getY(0), Window.getY(Window.getHeight2()), Window.getY(Window.getHeight2()), Window.getY(0)};
//fill border
        g.setColor(Color.white);
        g.fillPolygon(x, y, 4);
// draw border
        g.setColor(Color.black);
        g.drawPolyline(x, y, 5);

        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
        return;
        }
        
        if(inGame == false){
            g.setFont(new Font("Arial",Font.BOLD,100));
            g.drawRoundRect(Window.getX(10), Window.getYNormal(890), 1200, 125, 25, 25);
            g.drawString("Cards Against Humanity", Window.getX(25), Window.getYNormal(795));
            
            g.setFont(new Font("Arial",Font.PLAIN,36));
            g.drawRoundRect(Window.getX(10), Window.getYNormal(750), 525, 60, 25, 25);
            g.drawString("Danny Dyerly & Pavel Demidov", Window.getX(25), Window.getYNormal(707));
            
            g.setFont(new Font("Arial",Font.PLAIN,24));
            g.drawRoundRect(Window.getX(10), Window.getYNormal(675), 280, 165, 25, 25);
            g.drawString("* Play on one computer", Window.getX(25), Window.getYNormal(645));
            g.drawString("* 3 to 5 players", Window.getX(25), Window.getYNormal(615));
            g.drawString("- add or remove players", Window.getX(25), Window.getYNormal(585));
            g.drawString("- input your real names", Window.getX(25), Window.getYNormal(555));
            g.drawString("- click start when ready", Window.getX(25), Window.getYNormal(525));
            
            g.drawRoundRect(Window.getX(10), Window.getYNormal(175), 800, 165, 25, 25);
            g.drawString("Instructions:", Window.getX(25), Window.getYNormal(145));
            g.drawString("A black card is drawn containing a sentence with blanks or a question", Window.getX(25), Window.getYNormal(115));
            g.drawString("Each player answers the black card with their funniest white card", Window.getX(25), Window.getYNormal(85));
            g.drawString("The Card Czar decides which is the funniest and that player gets a point", Window.getX(25), Window.getYNormal(55));
            g.drawString("Read the ''Help'' box in game for more details", Window.getX(25), Window.getYNormal(25));
        }
        
        Button.DrawButtons(g);
       
        if(inGame){
            Player.DrawHand(g);
            Player.DrawPickedCards(g);
            Black.getRandomBlack().draw(g);
            Player.printHelp(g);
            
        }
            Player.drawScores(g,inGame);
        gOld.drawImage(image, 0, 0, null);
    }
////////////////////////////////////////////////////////////////////////////
    public void drawCircle(int xpos,int ypos,double rot,double xscale,double yscale)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        g.setColor(Color.red);
        g.fillOval(-10,-10,20,20);

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable
    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = 0.01666;    //time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
            }
        }
    }
/////////////////////////////////////////////////////////////////////////
    public void reset() {
        
        boolean inGame = false;
        
        Button.Create("Start", 920, 500, true, 0);
        Button.Create("Confirm Selection", 10, 540, false, 5);
        Button.Create("Add Player", 1560, 500, true, 7);
        Button.Create("Next Round", 1060, 540, false, -300);
        Button.Create("Remove Player", 1680, 500, true, 7);
        
        White.resetCards();
        
    }
/////////////////////////////////////////////////////////////////////////
    public void animate() {
        if (animateFirstTime) {
            animateFirstTime = false;
            if (Window.xsize != getSize().width || Window.ysize != getSize().height) {
                Window.xsize = getSize().width;
                Window.ysize = getSize().height;
            }
            reset();                   
        }
        
    }
////////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }
////////////////////////////////////////////////////////////////////////////
    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    }

}
////////////////////////////////////////////////////////////////////////////
class Window {
    private static final int XBORDER = 0;
    
//    private static final int YBORDER = 20;
    
    private static final int TOP_BORDER = 0;
    private static final int BOTTOM_BORDER = 0;
    
    private static final int YTITLE = 30;
    private static final int WINDOW_BORDER = 8;
    static final int WINDOW_WIDTH = 2*(WINDOW_BORDER + XBORDER) + 1900;
    static final int WINDOW_HEIGHT = YTITLE + WINDOW_BORDER + 900;
    static int xsize = -1;
    static int ysize = -1;
/////////////////////////////////////////////////////////////////////////
    public static int getX(int x) {
        return (x + XBORDER + WINDOW_BORDER);
    }

    public static int getY(int y) {
//        return (y + YBORDER + YTITLE );
        return (y + TOP_BORDER + YTITLE );
        
    }

    public static int getYNormal(int y) {
//          return (-y + YBORDER + YTITLE + getHeight2());
      return (-y + TOP_BORDER + YTITLE + getHeight2());
        
    }
    
    public static int getWidth2() {
        return (xsize - 2 * (XBORDER + WINDOW_BORDER));
    }

    public static int getHeight2() {
//        return (ysize - 2 * YBORDER - WINDOW_BORDER - YTITLE);
        return (ysize - (BOTTOM_BORDER + TOP_BORDER) - WINDOW_BORDER - YTITLE);
    }    
}
/////////////////////////////////////////////////////////////////////////
class sound implements Runnable {
    Thread myThread;
    File soundFile;
    public boolean donePlaying = false;
    sound(String _name)
    {
        soundFile = new File(_name);
        myThread = new Thread(this);
        myThread.start();
    }
    public void run()
    {
        try {
        AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
        AudioFormat format = ais.getFormat();
    //    System.out.println("Format: " + format);
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        SourceDataLine source = (SourceDataLine) AudioSystem.getLine(info);
        source.open(format);
        source.start();
        int read = 0;
        byte[] audioData = new byte[16384];
        while (read > -1){
            read = ais.read(audioData,0,audioData.length);
            if (read >= 0) {
                source.write(audioData,0,read);
            }
        }
        donePlaying = true;

        source.drain();
        source.close();
        }
        catch (Exception exc) {
            System.out.println("error: " + exc.getMessage());
            exc.printStackTrace();
        }   
    }
}

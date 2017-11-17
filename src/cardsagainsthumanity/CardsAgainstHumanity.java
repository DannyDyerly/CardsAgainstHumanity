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
    Black card10 = Black.Create("I hope Grandma doesn't ask me to explain _____ again.");
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
    Black card29 = Black.Create("Jedis are able to use the force because they possess _____.");
    Black card30 = Black.Create("Mr. Yee's favorite videogame is _____.");
    Black card31 = Black.Create("Grandma's last words were ''_____''.");
    Black card32 = Black.Create("_____. Fun for the whole family!");
    Black card33 = Black.Create("I think _____ is going a little to far.");
    Black card34 = Black.Create("The Girl Scouts now have a badge for _____.");
    
    White card51 = White.Create("Coding until your fingers are bleeding.");
    White card52 = White.Create("Mr. Yee in a Yoda costume.");
    White card53 = White.Create("An ogre named Harold.");
    White card54 = White.Create("Messing up your gitBash and losing 13 hours of code.");
    White card55 = White.Create("Yoda Yee.");
    White card56 = White.Create("Yeesus.");
    White card57 = White.Create("Beating your grandma with a field hockey stick.");
    White card58 = White.Create("Nothing. Life sucks.");
    White card59 = White.Create("Buying a robot kit for your daughter.");
    White card60 = White.Create("Coming to the middle.");
    White card61 = White.Create("Coming to the middle... again.");
    White card62 = White.Create("Chewing a grapefruit-sized scab off a hobo's back.");
    White card63 = White.Create("TheLegend27.");
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
    White card90 = White.Create("");
    White card91 = White.Create("");
    White card92 = White.Create("");
    White card93 = White.Create("");
    White card94 = White.Create("");
    White card95 = White.Create("");
    White card96 = White.Create("");
    White card97 = White.Create("");
    White card98 = White.Create("");
    White card99 = White.Create("");
    White card100 = White.Create("");
    White card101 = White.Create("");
    White card102 = White.Create("");
    White card103 = White.Create("");
    White card104 = White.Create("");
    White card105 = White.Create("");
    White card106 = White.Create("");
    White card107 = White.Create("");
    White card108 = White.Create("");
    White card109 = White.Create("");
    White card110 = White.Create("");
    White card111 = White.Create("");
    White card112 = White.Create("");
    White card113 = White.Create("");
    White card114 = White.Create("");
    White card115 = White.Create("");
    White card116 = White.Create("");
    White card117 = White.Create("");
    White card118 = White.Create("");
    White card119 = White.Create("");
    White card120 = White.Create("");
    White card121 = White.Create("");
    White card122 = White.Create("");
    White card123 = White.Create("");
    White card124 = White.Create("Mr. Clean.");
    White card125 = White.Create("Going to one of Mr. Yee's clubs.");
    White card126 = White.Create("French kissing a bag of chips in a 7-Eleven.");
    White card127 = White.Create("Jesus Christ's pinky.");
    White card128 = White.Create("A bottle of urine.");
    White card129 = White.Create("I don't care that you broke your elbow.");
    White card130 = White.Create("I'm Lovin' It.");
    White card131 = White.Create("Cory in the House.");
    White card132 = White.Create("Eating Mr. Yee's spaghetti.");
    White card133 = White.Create("Eleven's death at the end of Stranger Things 2.");
    White card134 = White.Create("Eleven and Mike's love scene.");
    White card135 = White.Create("Getting stuck in the Upside Down.");
    White card136 = White.Create("Getting your cat eaten by Dart.");
    White card137 = White.Create("Pickle Rick.");
    White card138 = White.Create("Szechuan Sauce.");
    White card139 = White.Create("Mr. Yee's level 100 warlock.");
    White card140 = White.Create("The pleasure of licking your elbow.");
    White card141 = White.Create("The soft touch of an elderly man.");
    White card142 = White.Create("Sexy time.");
    White card143 = White.Create("Donald Trump.");
    White card144 = White.Create("Spy Kids 5: The Guy Returns.");
    White card145 = White.Create("Opening a Mason jar with your eyelid.");
    White card146 = White.Create("A hot dog shack called Anne's Franks.");
    White card147 = White.Create("The Mii menu music.");
    White card148 = White.Create("A fresh pair of Yeezys.");
    White card149 = White.Create("Being sponsored by Supreme.");
    White card150 = White.Create("Imagining everyone naked.");
    White card151 = White.Create("Beffrey Bee.");
    White card152 = White.Create("Texting your Roblox girlfriend and hearing your uncle's phone ring.");
    White card153 = White.Create("Purchasing a $20 Minecraft guided walkthrough.");
    White card154 = White.Create("Using your Hydro Flask to gain status in the religious heirarchy.");
    White card155 = White.Create("Supreme underwear.");
    White card156 = White.Create("Cotton candy vape juice.");
    White card157 = White.Create("The Vape Nation.");
    White card158 = White.Create("Playing spin the bottle with old men.");
    White card159 = White.Create("Having a big dictionary.");
    White card160 = White.Create("Your very own Minecraft realm.");
    White card161 = White.Create("A mayonnaise restaurant.");
    White card162 = White.Create("Clicking compile then watching your computer blow up and fly through the ceiling.");
    White card163 = White.Create("Stepping through code.");
    White card164 = White.Create("Bission Bista.");
    White card165 = White.Create("Cream cheese that's been in your fridge longer than you've been alive.");
    White card166 = White.Create("8 spiders in your pocket.");
    White card167 = White.Create("Google+.");
    White card168 = White.Create("Old ladies paying with change.");
    White card169 = White.Create("Pee Wee Herman.");
    White card170 = White.Create("A clown in the sewer.");
    White card171 = White.Create("Gucci Gang Minecraft Parody.");
    White card172 = White.Create("Content Cop - Jake Paul.");
    White card173 = White.Create("A Supreme crowbar.");
    White card174 = White.Create("Dabbing so hard that you dislocate you elbow.");
    White card175 = White.Create("Dinkleberg.");
    White card176 = White.Create("Some choccy milk.");
    White card177 = White.Create("A safety torch.");
    White card178 = White.Create("Swinging your diamond sword.");
    White card179 = White.Create("A nugget in a biscuit.");
    White card180 = White.Create("Going to the movies with Big Shaq and Kim Jong Un.");
    White card181 = White.Create("A real big doggo.");
    White card182 = White.Create("Eating hot cheetos until your stomach explodes and pours into your abdominal cavity.");
    White card183 = White.Create("Failing the Maze test.");
    White card184 = White.Create("Losing a game of Kahoot.");
    White card185 = White.Create("12 shaved ferrets squirming in a basket.");
    White card186 = White.Create("Seizing the means of production.");
    White card187 = White.Create("Selling a kidney to buy the iPhone X.");
    White card188 = White.Create("Playing Cards Against Humanity in your computer science class.");
    White card189 = White.Create("Rick Grimes.");
    White card190 = White.Create("Eating netbeans for dinner because you're poor.");
    White card191 = White.Create("Going to highshool with Barack Obama.");
    White card192 = White.Create("Lil Pump getting accepted to Harvard.");
    White card193 = White.Create("Ebola.");
    White card194 = White.Create("The MVHS football team.");
    White card195 = White.Create("An anime body pillow.");
    White card196 = White.Create("A squirt gun filled with cat pee.");
    White card197 = White.Create("Peanut butter jelly time.");
    White card198 = White.Create("Getting your baby stolen by dingos.");
    White card199 = White.Create("Faking a jellyfish sting so someone will pee on you.");
    White card200 = White.Create("Being stalked by a duck.");

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

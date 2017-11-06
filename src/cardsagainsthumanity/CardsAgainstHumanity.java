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
    
    boolean inGame = false;

    Black card1 = Black.Create("_____. That's how I want to die.");
    Black card2 = Black.Create("_____. Pretty fun on a Sunday.");
    White card51 = White.Create("Coding.");
    White card52 = White.Create("Mr. Yee.");
    White card53 = White.Create("A dragon named Harold.");
    White card54 = White.Create("Danny Dyerly's forehead.");
    White card55 = White.Create("Yoda Yee.");
    White card56 = White.Create("Yeesus.");
    White card57 = White.Create("Beating your grandma with a field hockey stick.");
    White card58 = White.Create("Nothing. Life sucks.");
    White card59 = White.Create("Buying a robot kit for your daughter.");
    White card60 = White.Create("Coming to the middle.");
    White card61 = White.Create("Coming to the middle... again.");   

    static CardsAgainstHumanity frame;
    public static void main(String[] args) {
        frame = new CardsAgainstHumanity();
        frame.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                    
                    if(inGame){
                        Player.CheckSelect(xpos, ypos);
                        Button.selectCard();
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
                    if(inGame){
                        Player.DrawCards();
                    }
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
        
        Button.DrawButtons(g);
       
        if(inGame){
            Player.DrawHand(g);
            Player.DrawPickedCards(g);
            Black.getRandomBlack().draw(g);
        }

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
        
        Button.Create("start", 910, 450, true, 10);
        Button.Create("ok", 800, 250, false, 15);
        Button.Create("cancel", 800, 550, false, 10);
        Button.Create("select card",875, 300, false, 8);
        
        Player.Create("player1", false);
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

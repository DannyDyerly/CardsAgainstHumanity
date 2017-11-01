package cardsagainsthumanity;

import java.awt.Graphics2D;
import java.awt.*;

public class Black extends Card {
    private static int numBlackCards;
    
    Black(String _text){
        super(_text);
        numBlackCards++;
    }
    
}

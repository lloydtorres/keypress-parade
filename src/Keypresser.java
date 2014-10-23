// JPANEL CLASS

// MODULES TO IMPORT
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Keypresser extends JPanel implements KeyListener {

    private final Color tGrey = new Color(61, 61, 61);
    private final Font fontL = new Font("SansSerif", Font.PLAIN, 30);
    private final Font fontS = new Font("SansSerif", Font.PLAIN, 12);
    private int sizeX, sizeY;

    private String keyDisplay = "Press something!"; // stores string to be displayed
    private ArrayList<String> prevStrings = new ArrayList<String>(); // list of previous strings pressed
    private ArrayList<String> keysPressed = new ArrayList<String>(); // list of chars currently pressed
    private boolean[] keys = new boolean[KeyEvent.KEY_LAST+1]; // array of booleans of keys currently pressed

    public Keypresser(int getX, int getY) {
        super();
        setSize(getX, getY);
        addKeyListener(this);

        sizeX = getX;
        sizeY = getY;
    }

    // update text displayed in window based on current key presses
    private void updateDisplay(){
        // add previous string to list
        if (!keyDisplay.equals("Press something!")){
            prevStrings.add(keyDisplay);
        }
        if (prevStrings.size() > 25) {
            prevStrings.remove(0);
        }

        // clear variables
        keyDisplay = "";
        keysPressed.clear();

        // get chars for each key pressed
        for (int i=1; i<keys.length; i++) { // avoid index 0 since it's not recognized
            if (keys[i]) {
                keysPressed.add(KeyEvent.getKeyText(i));
            }
        }

        // build string
        for (int j=0; j<keysPressed.size(); j++) {
            if (j != keysPressed.size()-1) {
                keyDisplay = keyDisplay + keysPressed.get(j) + "+";
            }
            else {
                keyDisplay = keyDisplay + keysPressed.get(j);
            }
        }

        // if no keys being pressed
        if (keyDisplay.equals("")) {
            keyDisplay = "Press something!";
        }

    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    // methods called when key event occurs
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        updateDisplay();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        updateDisplay();
    }

    // draw in window
    public void paintComponent(Graphics g) {
        g.setColor(tGrey); // draws background
        g.fillRect(0,0,sizeX,sizeY);

        g.setColor(Color.WHITE); // draws main text
        g.setFont(fontL);
        FontMetrics fm = g.getFontMetrics();
        Rectangle2D r = fm.getStringBounds(keyDisplay, g);
        int xPos = (int) ((sizeX - r.getWidth()) / 2); // centres text
        int yPos = (int) ((sizeY - r.getHeight() + fm.getAscent()) / 4);
        g.drawString(keyDisplay, xPos, yPos);

        // draws smaller text below main text
        g.setFont(fontS);
        fm = g.getFontMetrics();

        for (int i=0; i<prevStrings.size(); i++){
            String stringScan = prevStrings.get(prevStrings.size()-i-1);
            r = fm.getStringBounds(stringScan, g);
            int xPos2 = (int) ((sizeX - r.getWidth()) / 2);
            int yPos2 = (int) (((sizeY - r.getHeight() + fm.getAscent()) / 4) + 25 + (r.getHeight() * i));
            g.drawString(stringScan, xPos2, yPos2);
        }
    }
}

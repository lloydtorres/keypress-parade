/*
========================================
Keypress Parade by Lloyd Torres

Published 23 October 2014

This is a simple app that displays current and previous keypresses
made if the app window is in focus.
========================================
Legal:

Copyright (C) 2014 Lloyd Torres

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
========================================
 */

// MODULES TO IMPORT
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KeypressParade extends JFrame implements ActionListener {

    private javax.swing.Timer myTimer;
    private Keypresser keyPanel;

    // window size
    private int sizeX = 600;
    private int sizeY = 300;

    public KeypressParade() {
        super("Keypress Parade");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);
        setSize(sizeX,sizeY);
        setResizable(false);
        setVisible(true);

        keyPanel = new Keypresser(sizeX,sizeY);
        keyPanel.setFocusTraversalKeysEnabled(false); // override AWT TAB
        add(keyPanel);

        myTimer = new javax.swing.Timer(10,this); // update every 10 ms
        myTimer.start();
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == myTimer){
            keyPanel.repaint();
        }
    }

    public static void main(String[] args) {
        new KeypressParade();
    }

}

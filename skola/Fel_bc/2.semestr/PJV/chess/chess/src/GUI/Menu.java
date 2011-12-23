/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author sprinkler
 */
public class Menu extends JMenuBar implements ActionListener {

    private GUIGate gate;

    public Menu(GUIGate gate) {
        this.gate = gate;
        add(file());
        repaint();
    }

    private JMenu file() {
        JMenu file = new JMenu("soubor");
        file.add(createGame());
        file.add(joinGame());
        file.add(endGame());
        return file;
    }

    private JMenuItem createGame() {
        JMenuItem createGame = new JMenuItem("Vytvoøit novou hru", KeyEvent.VK_N);
        createGame.setActionCommand("newGame");
        createGame.addActionListener(this);
        createGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
        return createGame;
    }

    private JMenuItem joinGame() {
        JMenuItem joinGame = new JMenuItem("Pøipojit se ke høe", KeyEvent.VK_J);
        joinGame.setActionCommand("joinGame");
        joinGame.addActionListener(this);
        joinGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.CTRL_MASK));
        return joinGame;
    }

    private JMenuItem endGame() {
        JMenuItem endGame = new JMenuItem("Konec hry", KeyEvent.VK_K);
        endGame.setActionCommand("endGame");
        endGame.addActionListener(this);
        endGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, KeyEvent.CTRL_MASK));
        return endGame;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("newGame")) {
            new NewGameDialog(gate);
        } else if (e.getActionCommand().equals("joinGame")) {
            new IpAddressDialog(gate);
        } else if (e.getActionCommand().equals("endGame")) {
            gate.endGame();
        }
    }
}

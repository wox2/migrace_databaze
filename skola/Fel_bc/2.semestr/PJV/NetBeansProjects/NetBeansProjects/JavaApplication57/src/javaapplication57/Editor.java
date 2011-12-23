/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication57;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

/**
 *
 * @author já
 */
public class Editor extends JFrame implements ActionListener {

    private JScrollPane sp = null;
    private JTextArea ta = null;
    private JMenuBar mb = null;
    private String sep = null;

    public Editor() {
        super();
        init();
    }

    public void init() {
        sep = System.getProperty("line.separator");

        ta = new JTextArea();
        Font f = Font.decode("Monospaced");
        if (f != null) {
            ta.setFont(f);
        }

        sp = new JScrollPane(ta,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setContentPane(sp);

        JMenu menu = new JMenu("Soubor");
        menu.setMnemonic(KeyEvent.VK_S);

        JMenuItem mi = new JMenuItem("Nový", KeyEvent.VK_N);
        mi.setActionCommand("new");
        mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
        mi.addActionListener(this);
        menu.add(mi);

        mi = new JMenuItem("Otevřít...", KeyEvent.VK_O);
        mi.setActionCommand("open");
        mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
        mi.addActionListener(this);
        menu.add(mi);

        mi = new JMenuItem("Uložit...", KeyEvent.VK_U);
        mi.setActionCommand("save");
        mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
        mi.addActionListener(this);
        menu.add(mi);

        mi = new JMenuItem("Konec", KeyEvent.VK_K);
        mi.setActionCommand("quit");
        mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
        mi.addActionListener(this);
        menu.add(mi);

        mb = new JMenuBar();
        mb.add(menu);

        setJMenuBar(mb);

        setTitle("Editor");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(100, 100);
        setSize(400, 300);
    }

    public void actionPerformed(ActionEvent e) {
    String s = e.getActionCommand();
    if (s.equals("new"))
        clear();
    else if (s.equals("open"))
        load();
    else if (s.equals("save"))
        save();
    else if (s.equals("quit"))
        dispose();
}

public void clear() {
    ta.setText("");
}

public void load() {
    JFileChooser fc = new JFileChooser();
    fc.setDialogType(JFileChooser.OPEN_DIALOG);
    if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        File f = fc.getSelectedFile();
        ta.setText("");
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            StringBuilder sb = new StringBuilder();
            String s = "";
            boolean fl = false;
            while ((s = br.readLine()) != null) {
                if (!fl)
                    sb.append(sep);
                else
                    fl = true;

                sb.append(s);
            }
            br.close();
            ta.setText(sb.toString());
            ta.setCaretPosition(0);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Soubor nelze otevřít.",
                "Chyba", JOptionPane.ERROR_MESSAGE);
        }
    }
}

public void save() {
    JFileChooser fc = new JFileChooser();
    fc.setDialogType(JFileChooser.SAVE_DIALOG);
    if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
        File f = fc.getSelectedFile();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(ta.getText());
            bw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Soubor nelze uložit.",
                "Chyba", JOptionPane.ERROR_MESSAGE);
        }
    }
}


}

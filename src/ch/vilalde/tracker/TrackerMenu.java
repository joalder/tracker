package ch.vilalde.tracker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Generic menu bar with basic actions (File, Help, ..)
 */
public class TrackerMenu extends JMenuBar implements ActionListener {

    JFrame mainFrame;

    public TrackerMenu(JFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
        prepareMenu();
    }

    private void prepareMenu() {
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(this);
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(exitMenuItem);
        this.add(fileMenu);

        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(this);
        JMenu helpMenu = new JMenu("Help");
        helpMenu.add(aboutMenuItem);
        this.add(helpMenu);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Got an event! Msg: " + e.getActionCommand());
        if (e.getActionCommand().equals("About")) {
            Util.openWebpage("https://github.com/joalder/tracker");
        } else if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        }
    }
}

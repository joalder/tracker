package ch.vilalde.tracker.treemap;

import javax.swing.*;

public class MultilineLabel extends JTextArea {
    private static final long serialVersionUID = 1L;

    public MultilineLabel(String text){
        super(text);
        setEditable(false);
        setCursor(null);
        setOpaque(false);
        setFocusable(false);
        setFont(UIManager.getFont("Label.font"));
        setWrapStyleWord(true);
        setLineWrap(true);
    }
}

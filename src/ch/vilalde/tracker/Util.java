package ch.vilalde.tracker;

import java.awt.*;
import java.net.URL;

/**
 * static utility functions
 */
public class Util {
    public static void openWebpage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

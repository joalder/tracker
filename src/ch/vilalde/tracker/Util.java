package ch.vilalde.tracker;

import ch.vilalde.tracker.domain.Project;

import java.awt.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

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

    public static ArrayList<Project> loadData(String fileName) throws FileNotFoundException, ClassCastException{
        XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));

        ArrayList<Project> projects = (ArrayList<Project>)decoder.readObject();
        decoder.close();

        return projects;
    }

    public static void saveData(ArrayList<Project> projects, String fileName) throws FileNotFoundException {
        XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)));

        encoder.writeObject(projects);

        encoder.close();
    }
}

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
    /**
     * Opens given address in a local browser. On failure just prints stack trace and does nothing
     * @param urlString Address to open
     */
    public static void openWebPage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads {@link Project} and {@link ch.vilalde.tracker.domain.Task} data from given XML file using {@link XMLDecoder}
     * @param fileName File(name) to read from
     * @return Read list of projects including tasks
     * @throws FileNotFoundException If given file has not been found
     * @throws ClassCastException If the data could not be converted to the appropriate class
     */
    public static ArrayList<Project> loadData(String fileName) throws FileNotFoundException, ClassCastException{
        XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));

        ArrayList<Project> projects = (ArrayList<Project>)decoder.readObject();
        decoder.close();

        return projects;
    }

    /**
     * Saves given {@link Project} and {@link ch.vilalde.tracker.domain.Task} data to a given file location using {@link XMLEncoder}
     * @param projects List of projects to save
     * @param fileName File(name) to save it to
     */
    public static void saveData(ArrayList<Project> projects, String fileName)  {
        XMLEncoder encoder;
        try {
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)));
            encoder.writeObject(projects);
            encoder.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error on saving data to file: " + fileName);
            e.printStackTrace();
        }
    }
}

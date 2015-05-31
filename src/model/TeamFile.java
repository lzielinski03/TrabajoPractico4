package model;

import customException.FileManagerException;

import java.io.File;
import java.util.List;

/**
 * Created by Leonardo on 22/05/2015.
 */
public class TeamFile extends FileManager {

    private static TeamFile instance = null;

    private TeamFile() throws FileManagerException {
        super("files\\team.txt");
    }

    public static TeamFile getInstance() throws FileManagerException {
        if (instance == null) {
            instance = new TeamFile();
        }
        return instance;
    }

    public void loadNewFile(String fileName) {
        setPath("files\\" + fileName);
        loadFile();
        setPath("files\\team.txt");
        persistList();
    }

    public List<String> getTeams() {
        return getLineList();
    }
}
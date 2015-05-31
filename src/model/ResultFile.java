package model;

import customException.FileManagerException;

/**
 * Created by Leonardo on 31/05/2015.
 */
public class ResultFile extends FileManager {

    private static ResultFile instance = null;

    private ResultFile() throws FileManagerException {
        super("files\\result.txt");
    }

    public static ResultFile getInstance() throws FileManagerException {
        if (instance == null) {
            instance = new ResultFile();
        }
        return instance;
    }
}

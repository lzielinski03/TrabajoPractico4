package model;

import customException.*;
import view.IOMenu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leonardo on 22/05/2015.
 */
public abstract class FileManager {

    private List<FileLine> lineList = new ArrayList<>();

    private FileState state;

    private String path;
    private File file = null;

    private BufferedReader br;
    private BufferedWriter bw;

    protected FileManager(String path) throws FileManagerException {
        this.path = path;
        initialize();
        loadFile();
    }

    protected void initialize() {
        file = new File(path);
        try {
            if (!file.exists())
                file.createNewFile();
        } catch (IOException e) {
            IOMenu.printException(e);
        }
    }

    protected void loadFile() {
        try {
            openConnection();
            lineList.clear();
            String line = br.readLine();
            while(line != null) {
                lineList.add(new FileLine(lineList.size() + 1, line));
                line = br.readLine();
            }
            closeConnection();
        } catch (OpenConnectionException | IOException | CloseConnectionException e) {
            IOMenu.printException(e);
            state = FileState.NOT_FILE;
            return;
        }

        if (lineList.isEmpty()) {
            state = FileState.FILE_EMPTY;
            return;
        }

        if (lineList.size() < 3) {
            //check format
            state = FileState.WRONG_FORMAT;
            return;
        }
        state = FileState.FINISH;
    }

    protected void persistList() {
        try {
            openConnection();
            //if (state == FileState.FILE_EMPTY) {
                for (FileLine line : lineList) {
                    bw.write(line.line);
                    bw.newLine();
                }
                state = FileState.FINISH;
            //}
            closeConnection();
        } catch (OpenConnectionException | IOException | CloseConnectionException e) {
            IOMenu.printException(e);
        }
    }

    protected void persistList(List<String> lineList) {
        try {
            openConnection();
            if (state == FileState.FILE_EMPTY) { // reload?
                for (String line : lineList) {
                    bw.write(line);
                    bw.newLine();
                }
            }
            closeConnection();
        } catch (OpenConnectionException | IOException | CloseConnectionException e) {
            IOMenu.printException(e);
        }
        loadFile();
    }

    private void openConnection() throws OpenConnectionException {
        try {
            br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile(), true));

        } catch (IOException | NullPointerException e) {
            throw new OpenConnectionException();
        }
    }

    private void closeConnection() throws CloseConnectionException {
        if (br == null || bw == null)
            throw new CloseConnectionException();

        try {
            br.close();
            bw.close();
        } catch (IOException e) {
            throw new CloseConnectionException();
        }
    }

    protected List<String> getLineList() {
        List<String> listString = new ArrayList<>();
        for(FileLine line : lineList) {
            listString.add(line.line);
        }
        return listString;
    }

    public FileState getState() {
        return this.state;
    }

    protected void setPath(String path) {
        this.path = path;
        this.file = new File(path);
    }


    protected class FileLine{

        private int id;
        private String line;

        public FileLine(int id, String line) {
            this.id = id;
            this.line = line;
        }
    }
}
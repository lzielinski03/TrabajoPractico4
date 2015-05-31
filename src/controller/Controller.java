package controller;

import customException.DeleteFileLineException;
import customException.FileManagerException;
import customException.InitializeTeamException;
import customException.InsertFileLineException;
import model.*;
import view.IOMenu;

/**
 * Created by Leonardo on 22/05/2015.
 */
public class Controller {

    public Controller() {

        FileManager stepFile = null;

        TeamFile teams = null;
        FixtureFile fixture = null;

        try {
            stepFile = TeamFile.getInstance();
        } catch (FileManagerException e) {
            IOMenu.printException(e);
        }

        IOMenu iom = new IOMenu();
        String path;
        do {
            //System.out.println(stepFile.getState());
            switch (stepFile.getState()) {
                case NOT_FILE:
                    // file not found msj
                    path = iom.getTeamPath();
                    ((TeamFile)stepFile).loadNewFile(path);
                    break;
                case FILE_EMPTY:
                    if (stepFile instanceof TeamFile) {
                        path = iom.getTeamPath();
                        ((TeamFile)stepFile).loadNewFile(path);
                        break;
                    } else if (stepFile instanceof FixtureFile) {
                        ((FixtureFile) stepFile).setDay(iom.getDaysBetweenGame());
                        break;
                    } else if (stepFile instanceof ResultFile) {
                        System.out.println("Generador team file, fixture file, result file(sin completar).");
                        System.exit(1);
                        // sin terminar
                        break;
                    }

                case WRONG_FORMAT:
                    path = iom.getTeamPath();
                    ((TeamFile)stepFile).loadNewFile(path);
                    break;
                case FINISH:
                    try {
                        if (stepFile instanceof TeamFile) {
                            teams = ((TeamFile) stepFile);
                            stepFile = FixtureFile.getInstance();
                        } else if (stepFile instanceof FixtureFile) {
                            fixture = ((FixtureFile) stepFile);
                            stepFile = ResultFile.getInstance();
                        }
                    } catch (FileManagerException e) {
                        IOMenu.printException(e);
                    }
                    break;
            }

        } while (stepFile.getState() != FileState.EXIT);

        System.exit(1);
    }

}

package model;

import customException.FileManagerException;
import view.IOMenu;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Leonardo on 25/05/2015.
 */
public class FixtureFile extends FileManager{

    private static FixtureFile instance = null;

    private List<FixtureFormat> fixture = new ArrayList<>();
    private int day;

    private FixtureFile() throws FileManagerException {
        super("files\\fixture.txt");
    }

    public static FixtureFile getInstance() throws FileManagerException {
        if (instance == null)
            instance = new FixtureFile();

        return instance;
    }

    public List<String> getTeams() {
        return getLineList();
    }

    private void generateFixture() {
        try {
            List<String> teams = new ArrayList<>(TeamFile.getInstance().getTeams());
            List<FixtureFormat> fix = new ArrayList<>();

            for (int i = 0; i < teams.size(); i++) {
                List<String> aux = teams.subList(i + 1, teams.size());
                for (String subTeam : aux) {
                    fix.add(new FixtureFormat(teams.get(i), subTeam));
                }
            }
            organize(fix);

        } catch (FileManagerException e) {
            e.printStackTrace();
        }
    }

    public void organize(List<FixtureFormat> list) {
        if (list.isEmpty())
            return;

        if (fixture.isEmpty()) {
            fixture.add(list.get(0));
            list.remove(list.get(0));
        }

        int sizeAux = fixture.size();
        for (FixtureFormat element : new ArrayList<>(list)) {
            if(fixture.get(fixture.size() - 1).verifyMatch(element)) {
                fixture.add(element);
                list.remove(element);
                break;
            }
        }
        if (!list.isEmpty() && sizeAux == fixture.size()) {
            fixture.add(list.get(0));
            list.remove(0);
        }
        organize(list);
    }

    public void setDaysBetweenGame() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        for (FixtureFormat ff : fixture) {
            ff.setFecha(date);
            date = DateUtil.addDays(date, day);
        }
    }

    public void setDay(int daysBetweenGame) {
        this.day = daysBetweenGame;
        generateFixture();
        setDaysBetweenGame();
        persistList(getFixtureList());
    }

    private List<String> getFixtureList(){
        List<String> fixtureList = new ArrayList<>();
        for (FixtureFormat ff : fixture) {
            fixtureList.add(ff.getStringFormat());
        }
        return fixtureList;
    }
}

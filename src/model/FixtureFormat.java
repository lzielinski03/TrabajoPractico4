package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Leonardo on 30/05/2015.
 */
public class FixtureFormat {

    private Date fecha;
    private String equipo;
    private String equipoContrincante;

    public FixtureFormat(String equipo, String equipoContrincante) {
        this.equipo = equipo;
        this.equipoContrincante = equipoContrincante;
    }

    public boolean verifyMatch(FixtureFormat next) {
        if (equipo.equalsIgnoreCase(next.equipo))
            return false;
        if (equipo.equalsIgnoreCase(next.equipoContrincante))
            return false;
        if (equipoContrincante.equalsIgnoreCase(next.equipo))
            return false;
        if (equipoContrincante.equalsIgnoreCase(next.equipoContrincante))
            return false;

        return true;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getStringFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(fecha) + "," + equipo + "," + equipoContrincante;
    }
}

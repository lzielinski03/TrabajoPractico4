package model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Leonardo on 31/05/2015.
 */
public class DateUtil {

    public static Date addDays(Date fecha, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }
}

package it.cnr.ilc.projectx.utils;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.util.Objects.isNull;

/**
 * Description of DateUtils
 * <p>
 * Created at 22/03/2022 10:34
 * Author Bianca Barattolo (BB) - <b.barattolo@xeel.tech>
 */
public final class DateUtils {

    public static final String DATE_FORMAT_ISO_LOCAL_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String DATE_FORMAT_ddMMyyyy = "dd/MM/yyyy";
    public static final String DATE_FORMAT_dd_MM_yyyy =  "dd-MM-yyyy";
    public static final String DATE_FORMAT_ddMMyyyyHHmmss = "dd-MM-yyyy HH:mm:ss";
    public static final String DATE_FORMAT_dd_MM_yyyy_HHmmssS = "dd-MM-yyyy HH:mm:ss.S";

    public static Date getAtMidnight(Date date) {
        if (isNull(date)) {
            return null;
        }

        Calendar calendarExpirationDate = Calendar.getInstance();
        calendarExpirationDate.setTime(date);

        int year = calendarExpirationDate.get(Calendar.YEAR) + 1;
        int month = calendarExpirationDate.get(Calendar.MONTH);
        int day = calendarExpirationDate.get(Calendar.DAY_OF_MONTH);

        return Date.valueOf(LocalDate.of(year, month, day));
    }

    public static boolean checkDatesEquals(@NotNull Date date1, @NotNull Date date2) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date1);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.setTime(date2);
        int oldYear = calendar.get(Calendar.YEAR);
        int oldMonth = calendar.get(Calendar.MONTH);
        int oldDay = calendar.get(Calendar.DAY_OF_MONTH);

        return year == oldYear &&
                month == oldMonth &&
                day == oldDay;
    }

    public static boolean checkDatesEquals(@NotNull LocalDate date1, @NotNull LocalDate date2) {

        int year = date1.getYear();
        int month = date1.getMonthValue();
        int day = date1.getDayOfMonth();

        int oldYear = date2.getYear();
        int oldMonth = date2.getMonthValue();
        int oldDay = date2.getDayOfMonth();

        return year == oldYear &&
                month == oldMonth &&
                day == oldDay;
    }

    private DateUtils() {
    }
}

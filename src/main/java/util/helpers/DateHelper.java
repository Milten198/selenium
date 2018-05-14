package util.helpers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class DateHelper {

    public static LocalDate getEndDateThisWeekRange() {
        LocalDate currentDate = LocalDate.now();

        int numberCurrentDay = getNumberOfCurrentDay(currentDate);
        int differenceFromLastDayOfWeek = getDifferenceFromLastDayOfWeek(numberCurrentDay);

        return currentDate.plusDays(differenceFromLastDayOfWeek - 1);
    }

    public static LocalDate getStartDateThisWeekRange() {
        LocalDate endRangeDate = getEndDateThisWeekRange();

        return endRangeDate.minusDays(6);
    }

    public static LocalDate getStartDateLastWeekRange() {
        LocalDate currentDate = LocalDate.now();

        int numberCurrentDay = getNumberOfCurrentDay(currentDate);
        int differenceFromLastDayOfWeek = getDifferenceFromLastDayOfWeek(numberCurrentDay);

        LocalDate endRangeDate = currentDate.plusDays(differenceFromLastDayOfWeek - 1);
        LocalDate startRangeDate = endRangeDate.minusDays(6);

        return startRangeDate.minusWeeks(1);
    }

    public static LocalDate getEndDateLastWeekRange() {
        LocalDate lastWeekStartDate = getStartDateLastWeekRange();

        return lastWeekStartDate.plusDays(6);
    }

    public static LocalDate getStartDateThisMontRange() {

        return getFirstDayOfCurrentMonth();
    }

    public static LocalDate getEndDateThisMonthRange() {
        LocalDate firstDayOfCurrentMonthDate = getStartDateThisMontRange();

        Month currentMonth = firstDayOfCurrentMonthDate.getMonth();

        int numberOfCurrentMonthDay = currentMonth.maxLength();

        return firstDayOfCurrentMonthDate.plusDays(numberOfCurrentMonthDay - 1);
    }

    public static LocalDate getStartDateLastMonthRange() {
        LocalDate firstDayOfCurrentMonth = getFirstDayOfCurrentMonth();

        return firstDayOfCurrentMonth.minusMonths(1);
    }

    public static LocalDate getEndDateLastMonthRange() {
        LocalDate firstDayOfLastMonth = getStartDateLastMonthRange();

        return firstDayOfLastMonth.plusMonths(1).minusDays(1);
    }

    public static LocalDate getStartDateLastTwelveMonthsRange() {
        LocalDate currentDate = LocalDate.now();

        return currentDate.minusMonths(12);
    }

    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public static LocalDate getFirstDayDateOfAcademicYear() {
        Calendar cal = Calendar.getInstance();

        if (getCurrentDate().getMonthValue() < 8)
            cal.add(Calendar.YEAR, -1);

        cal.set(Calendar.MONTH, 7);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        return cal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDate getFirstDayDateOfCurrentYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        return cal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static int getNumberOfCurrentDay(LocalDate currentDate) {
        return currentDate.getDayOfWeek().getValue();
    }

    private static int getDifferenceFromLastDayOfWeek(int numberCurrentDay) {
        return DayOfWeek.SUNDAY.getValue() - numberCurrentDay;
    }

    private static LocalDate getFirstDayOfCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);

        return cal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static class DateFormatter {

        public static DateTimeFormatter getDateTimeFormatterFofReport() {
            return DateTimeFormatter.ofPattern("dd/MM/yyyy");
        }

        public static String getFormattedRangeForReport(LocalDate endRangeDate, LocalDate startRangeDate) {
            DateTimeFormatter formater = getDateTimeFormatterFofReport();

            String startRange = startRangeDate.format(formater);
            String endRange = endRangeDate.format(formater);

            return startRange + " " + "-" + " " + endRange;
        }

        public static String getDateTimeFormatterForDataBase(LocalDate date) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            return date.format(formatter);
        }

        public static String getFormattedDate(LocalDate date, String pattern) {
            Locale englishLocale = new Locale("en");


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, englishLocale);

            return date.format(formatter);
        }

        public static String getFormattedDate(LocalDate date, String pattern, Locale local) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, local);

            return date.format(formatter);
        }
    }
}

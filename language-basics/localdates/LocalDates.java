package localdates;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

public class LocalDates {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("Today: " + today);

        LocalDate alonzosBithday = LocalDate.of(1903, 6, 16);
        alonzosBithday = LocalDate.of(1903, Month.JUNE, 14);
        System.out.println("alonzosBithday: " + alonzosBithday);

        LocalDate programmersDay = LocalDate.of(2023, 1, 1).plusDays(255);
        System.out.println("programmersDay: " + programmersDay);

        LocalDate independenceDay = LocalDate.of(2023, Month.JULY, 4);
        LocalDate christmas = LocalDate.of(2023, Month.DECEMBER, 25);
        System.out.println("Until christmas: " + independenceDay.until(christmas));
        System.out.println("Until christmas: " + independenceDay.until(christmas, ChronoUnit.DAYS));

        System.out.println(LocalDate.of(2023, 1, 31).plusMonths(1));
        System.out.println(LocalDate.of(2023, 3, 31).minusMonths(1));

        DayOfWeek startOfLastMillennium = LocalDate.of(1900, 1,1).getDayOfWeek();
        System.out.println("startOfLastMillennium: " + startOfLastMillennium);
        System.out.println(startOfLastMillennium.getValue());
        System.out.println(DayOfWeek.SATURDAY.plus(3));

        LocalDate start = LocalDate.of(2000,1,1);
        LocalDate endExclusive = LocalDate.now();
        Stream<LocalDate> firstDaysInMonth = start.datesUntil(endExclusive, Period.ofMonths(1));
        System.out.println("firstDaysInMonth: " + firstDaysInMonth.toList());
    }
}

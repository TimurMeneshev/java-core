package basics;

import java.time.LocalDate;
import java.util.Date;

public class Dates {
    public static void main(String[] args) {
        Date date = new Date();
        LocalDate now = LocalDate.now();
        LocalDate nowPlusTenThousandDays = now.plusDays(10000);
        System.out.println(
                "Date: " + date
                +"\nLocalDate: " + now
                +"\nLocalDate dd mm yyyy: "
                + now.getDayOfMonth() + " "
                + now.getMonthValue() + " "
                + now.getYear()
                +"\nnowPlusTenThousandDays: " + nowPlusTenThousandDays
        );
    }
}

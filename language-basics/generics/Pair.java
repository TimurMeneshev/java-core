package generics;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Pair {
    private static MathContext contextQty = new MathContext(8, RoundingMode.HALF_UP);

    public static BigDecimal adjustQty(BigDecimal value) {
        if (value == null) return null;
        value = value.round(contextQty);
        //value = value.setScale(8, RoundingMode.HALF_UP);
        return value;
    }

    public static void main(String[] args) {
        BigDecimal b = BigDecimal.valueOf(74.5633388555544);

        b = adjustQty(b);

        System.out.println(b.toString());

    }

}

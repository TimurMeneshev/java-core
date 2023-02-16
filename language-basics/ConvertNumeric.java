public class ConvertNumeric {
    public static void main(String[] args) {
        byte b = Byte.MAX_VALUE;
        short s = Short.MAX_VALUE;
        char c = Character.MAX_VALUE;
        int i = Integer.MAX_VALUE;
        long l = Long.MAX_VALUE;
        float f = Float.MAX_VALUE;
        double d = Double.MAX_VALUE;

        // приведение без потери данных
        System.out.println(
               "(short)byte: " + (short) b + "\n" +
               "(char)short: " + (char) s + "\n" +
               "(int)char: " + (int) c + "\n" +
               "(long)int: " + (long) i + "\n" +
               "(double)int: " + (double) i + "\n"
        );
        System.out.println();

        // приведение с потерей точности
        System.out.println(
                "(byte)short: " + (byte) s + "\n" +
                        "(short)char: " + (short) c + "\n" +
                        "(char)int: " + (char) i + "\n" +
                        "(int)long: " + (int) l + "\n" +
                        "(float)long: " + (float) l + "\n" +
                        "(float)int: " + (float) i + "\n" +
                        "(long)double: " + (long) d + "\n"
        );
    }
}

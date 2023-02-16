public class Variables {
    byte b = 127; // 1 byte
    short s = 32767; // 2 byte
    int i = 22; // 4 byte
    long l = 33L; // 8 byte
    float f = 44F; // 4 byte
    double d = 55D; // 8 byte
    char c = 'A'; // 2 byte
    boolean bool = true; // 1 byte
    static final char aConst = 'C'; // константа класса
    enum Size { SMALL, MEDIUM, LARGE, EXTRA_LARGE } // перечислимый тип




    public static void main(String[] args) {
        Variables v = new Variables();
        System.out.println(v.b);
        System.out.println(Size.MEDIUM);
    }
}

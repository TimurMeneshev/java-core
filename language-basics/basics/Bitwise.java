package basics;

public class Bitwise {
    public static void main(String[] args) {
        int i = 1 + 4 + 16 + 64;
        int j = 2 + 8 + 32 + 128;
        System.out.println("i = " + Integer.toBinaryString(i));
        System.out.println("j = " + Integer.toBinaryString(j));
        System.out.println("i & j = " + Integer.toBinaryString(i & j));
        System.out.println("i | j = " + Integer.toBinaryString(i | j));
        System.out.println("i ^ j = " + Integer.toBinaryString(i ^ j));
        System.out.println("~i = " + Integer.toBinaryString(~i));
        System.out.println("~j = " + Integer.toBinaryString(~j));

        int h = 0x10000000;
        System.out.println(Integer.toBinaryString(h));
        for(int k = 0; k < 28; k++) {
            h >>>= 1;
            System.out.println(Integer.toBinaryString(h));
        }

        int f = -1;
        System.out.println(Integer.toBinaryString(f));
        f <<= 10;
        System.out.println(Integer.toBinaryString(f));
        for(int q = 0; q < 32; q++) {
            f >>>= 1;
            System.out.println(Integer.toBinaryString(f));
        }
    }
}

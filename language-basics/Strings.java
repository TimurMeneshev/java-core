public class Strings {
    public static void main(String[] args) {
        String repeated = "Str.".repeat(10); //повторение заданной строки
        System.out.println(repeated);

        StringBuilder sb = new StringBuilder();
        sb.append("first substring ");
        sb.append(2);
        sb.append(" second");
        String newStr = sb.toString();
        System.out.println(newStr);


    }

}

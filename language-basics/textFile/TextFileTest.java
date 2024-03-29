package textFile;

import basics.Employee;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Scanner;

public class TextFileTest {
    public static void main(String[] args) throws IOException {
        var staff = new Employee[3];

        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);

        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 11);

        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

        //записываем объекты в файл
        try (var out = new PrintWriter("Employee.txt", StandardCharsets.UTF_8)) {
            writeData(staff, out);
        }

        //читаем из файла
        try (var in = new Scanner(new FileInputStream("Employee.txt"), StandardCharsets.UTF_8)) {
            Employee[] newStaff = readData(in);
            for (Employee e : newStaff) System.out.println(e);
        }
    }

    private static void writeData(Employee[] employees, PrintWriter out) throws IOException {
        out.println(employees.length);
        for (Employee e : employees) writeEmployee(out, e);
    }

    private static Employee[] readData(Scanner in) {
        int n = in.nextInt();
        in.nextLine();

        var employees = new Employee[n];
        for (int i = 0; i < n; i++) employees[i] = readEmployee(in);
        return employees;
    }

    public static void writeEmployee(PrintWriter out, Employee e) {
        out.println(e.getName() + "|" + e.getSalary() + "|" + e.getHireDate());
    }

    public static Employee readEmployee(Scanner in) {
        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        String name = tokens[0];
        double salary = Double.parseDouble(tokens[1]);
        LocalDate hireDate = LocalDate.parse(tokens[2]);
        int year = hireDate.getYear();
        int month = hireDate.getMonthValue();
        int day = hireDate.getDayOfMonth();
        return new Employee(name, salary, year, month, day);
    }
}

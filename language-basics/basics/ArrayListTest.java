package basics;

import java.time.LocalDate;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListTest {
    public static void main(String[] args) {
        var staff = new ArrayList<Employee>(); //работает с java 10
        staff.ensureCapacity(10); //емкость массива
        staff.add(new Employee("Employee1", 50000, LocalDate.of(2000, 5, 7)));
        staff.add(new Employee("Employee2", 30000, LocalDate.of(2020, 5, 16)));
        staff.add(new Employee("Employee2", 35000, LocalDate.of(2020, 5, 16)));
        staff.trimToSize(); // размер емкости массива соответствует факт. кол-ву элементов

        Employee[] employees = new Employee[3];
        employees[0] = new Employee("Employee1", 50000, LocalDate.of(2000, 5, 7));
        employees[1] = new Employee("Employee2", 30000, LocalDate.of(2020, 5, 16));
        employees[2] = new Employee("Employee2", 35000, LocalDate.of(2020, 5, 16));
        Arrays.stream(employees).forEach(System.out::println);
        Arrays.sort(employees);
        System.out.println("after sort");
        Arrays.stream(employees).forEach(System.out::println);
        AbstractCollection abstractCollection = new ArrayList();
    }
}

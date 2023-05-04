package collections;

import basics.Employee;

import java.time.LocalDate;
import java.util.HashMap;

public class MapTest {
    public static void main(String[] args) {
        var staff = new HashMap<String, Employee>();
        staff.put("144-25-5464", new Employee("Amy Lee", 20000, LocalDate.of(2021, 3, 1)));
        staff.put("567-24-2546", new Employee("Harry Hacker", 250000, LocalDate.of(1999, 3, 10)));
        staff.put("157-62-7935", new Employee("Gary Cooper", 37000, LocalDate.of(2022, 6, 1)));
        staff.put("456-62-5527", new Employee("Francesca Cruz", 89000, LocalDate.of(2017, 10, 17)));

        System.out.println(staff);

        staff.remove("567-24-2546");

        //при перезаписи ключа возвращается старое значение
        Employee e = staff.put("456-62-5527", new Employee("Francesca Miller", 93000, LocalDate.of(2017, 10, 17)));

        System.out.println("E = " + e);

        System.out.println(staff.get("157-62-7935"));

        staff.forEach((k, v) -> System.out.println("key= " + k + ", value= " + v));
    }
}

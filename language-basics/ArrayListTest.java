import java.time.LocalDate;
import java.util.ArrayList;

public class ArrayListTest {
    public static void main(String[] args) {
        var staff = new ArrayList<Employee>(); //работает с java 10
        staff.ensureCapacity(10); //емкость массива
        staff.add(new Employee("Employee1", 50000, LocalDate.of(2000, 5, 7)));
        staff.add(new Employee("Employee2", 30000, LocalDate.of(2020, 5, 16)));
        staff.add(new Employee("Employee2", 30000, LocalDate.of(2020, 5, 16)));
        staff.trimToSize(); // размер емкости массива соответствует факт. кол-ву элементов

    }
}

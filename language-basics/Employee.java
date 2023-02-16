import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    private String name;
    private int salary;
    private LocalDate hireDate;

    public Employee(String name, int salary, LocalDate hireDate) {
        Objects.requireNonNull(name, "Name cannot be null"); // обработка null
        this.name = name;
        //this.name = Objects.requireNonNullElse(name, "unknown");
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public String getName() { return this.name; }
    public int getSalary() { return this.salary; }
    public LocalDate getHireDate() { return this.hireDate; }

    public void raiseSalary(double percent) {
        double raise = salary * percent / 100;
        salary += raise;
    }

    public static void main(String[] args) {
        Employee employee = new Employee(null, 10000, LocalDate.of(2023, 1, 15) );
        System.out.println(employee.getName() + " "
        + employee.getSalary() + " " + employee.getHireDate());
    }
}

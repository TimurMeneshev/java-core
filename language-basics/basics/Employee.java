package basics;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Employee extends Person implements Comparable<Employee> /*обобщенный интерфейс с параметром типа*/ {

    public static final int NAME_SIZE = 40;
    public static final int RECORD_SIZE = 2 * NAME_SIZE + 8 + 4 + 4 + 4;
    private double salary;
    private LocalDate hireDate;

    public Employee(String name, double salary, int year, int month, int day) {
        super(name);
        this.salary = salary;
        this.hireDate = LocalDate.of(year, month, day);
    }

    @Override
    public String getDescription() {
        return String.format("an employee with a salary of $%d", salary);
    }

    public double getSalary() { return this.salary; }
    public LocalDate getHireDate() { return this.hireDate; }

    public void raiseSalary(double percent) {
        double raise = salary * percent / 100;
        salary += raise;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if (getClass() != other.getClass()) return false;
        Employee otherEmployee = (Employee) other;

        return Objects.equals(this.getName(), otherEmployee.getName())
                && this.salary == otherEmployee.getSalary()
                && Objects.equals(this.getHireDate(), otherEmployee.getHireDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), salary, hireDate);
    }

    @Override
    public String toString() {
        return "basics.Employee{" +
                "name=" + getName() +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                '}';
    }

    public static void main(String[] args) {
        Employee employee = new Employee("Jack", 10000, 2023, 1, 15);
        System.out.println(employee.getName() + " "
        + employee.getSalary() + " " + employee.getHireDate());

        Employee employee1 = new Employee("Jack", 10000, 2023, 1, 15);
        System.out.println(employee.equals(employee1));
        System.out.println(String.format("Hash: %d  %d", employee1.hashCode(), employee.hashCode()));
        System.out.println(employee);

    }

    @Override
    public int compareTo(Employee o) {
        return Double.compare(this.getSalary(), o.getSalary());
    }
}

package objectStream;

import basics.Employee;

import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;

class ObjectStreamTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        var harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        var carl = new Manager("Carl Cracker", 75000, 1987, 12, 15);
        carl.setSecretary(harry);

        var tony = new Manager("Tony Tester", 40000,1990, 3, 15);
        tony.setSecretary(harry);

        var staff = new Employee[3];
        staff[0] = carl;
        staff[1] = harry;
        staff[2] = tony;

        try (var out = new ObjectOutputStream(new FileOutputStream("employee.ser"))) {
            out.writeObject(staff);
        }

        try (var in = new ObjectInputStream(new FileInputStream("employee.ser"))) {
            var newStaff = (Employee[]) in.readObject();
            newStaff[1].raiseSalary(20);
            Arrays.stream(newStaff).forEach(System.out::println);
        }
    }
}

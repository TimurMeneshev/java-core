package basics;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class AbstractExample {
    public static void main(String[] args) {
        Person[] people =  new Person[2];
        people[0] = new Employee("Employee1", 50000, 2019, 4, 5);
        people[1] = new Student("Student1", "computer science");

        for (Person p: people) {
            System.out.println(p.getName() + ", " + p.getDescription());
        }
    }
}

abstract class Person implements Serializable {
    private final String name;

    public Person(String name) {
        Objects.requireNonNull(name, "Name cannot be null"); // обработка null
        //this.name = Objects.requireNonNullElse(name, "unknown");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String getDescription();
}

class Student extends Person {
    private final String major;

    public Student(String name, String major) {
        super(name);
        this.major = major;
    }

    @Override
    public String getDescription() {
        return "a student majoring in " + major;
    }
}
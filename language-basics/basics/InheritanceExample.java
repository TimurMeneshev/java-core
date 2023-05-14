package basics;

import java.time.LocalDate;

public class InheritanceExample {
    public static void main(String[] args) {
        Manager manager = new Manager("Manager1", 90000, LocalDate.of(2009, 10, 1));
        manager.setBonus(5990);

        Employee[] employees = {
                manager, // полиморфизм
                new Employee("Employee1", 50000, LocalDate.of(2019, 4, 5)),
                new Employee("Employee2", 35000, LocalDate.of(2021, 7, 5)),
        };

        for (Employee e: employees) {
            System.out.println(e.getName() +": " + e.getSalary()); //динамическое связывание
        }

        Manager[] managers = new Manager[1];

        //employees = managers;
        //ArrayStoreException
        //employees[0] = new basics.Employee("Employee1", 50000, LocalDate.of(2019, 4, 5));
        //managers[0].setBonus(50); // попытка вызвать несуществующий метод

    }
}

class Manager extends Employee {
    private int bonus;

    public Manager(String name, int salary, LocalDate hireDate) {
        super(name, salary, hireDate);
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    @Override
    public double getSalary() {
        return super.getSalary() + this.bonus;
    }
}
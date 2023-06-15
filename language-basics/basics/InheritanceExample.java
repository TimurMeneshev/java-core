package basics;

import java.time.LocalDate;

public class InheritanceExample {
    public static void main(String[] args) {
        Manager manager = new Manager("Manager1", 90000, 2009, 10, 1);
        manager.setBonus(5990);

        Employee[] employees = {
                manager, // полиморфизм
                new Employee("Employee1", 50000, 2019, 4, 5),
                new Employee("Employee2", 35000, 2021, 7, 5),
        };

        for (Employee e: employees) {
            System.out.println(e.getName() +": " + e.getSalary()); //динамическое связывание
        }

        Manager[] managers = new Manager[1];

        //employees = managers;
        //ArrayStoreException
        //employees[0] = new basics.Employee("Employee1", 50000, 2019, 4, 5);
        //managers[0].setBonus(50); // попытка вызвать несуществующий метод

    }
}

class Manager extends Employee {

    private Employee secretary;

    public Employee getSecretary() {
        return secretary;
    }

    public void setSecretary(Employee secretary) {
        this.secretary = secretary;
    }

    private int bonus;

    public Manager(String name, int salary, int year, int month, int day) {
        super(name, salary, year, month, day);
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
package basics;

import java.lang.reflect.*;
import java.time.LocalDate;
import java.util.Arrays;

public class ReflectionTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Employee e = new Employee("Name Surname", 100, 2020, 03, 12);
        // три способа получить экземпляр типа Class
        Class cl1 = e.getClass();
        Class cl2 = Class.forName("basics.Employee");
        Class cl3 = Employee.class;

        // получение массива полей, методов, конструкторов (в т.ч. приватных)
        Arrays.stream(cl1.getDeclaredFields()).forEach(System.out::println);
        Arrays.stream(cl1.getDeclaredMethods()).forEach(System.out::println);
        Arrays.stream(cl1.getDeclaredConstructors()).forEach(System.out::println);

        Field emplName = cl1.getSuperclass().getDeclaredField("name");
        emplName.setAccessible(true); // установка признака доступности объекта для рефлексии
        System.out.println("Name before update PRIVATE field: " + emplName.get(e)); //получениие значения поля
        emplName.set(e, "NEW NAME"); //изменение значения поля
        System.out.println("Name after update PRIVATE field: " + emplName.get(e));

        Method raiseSal = cl1.getMethod("raiseSalary", double.class);
        System.out.println("basics.Employee's salary before update: " + e.getSalary());
        raiseSal.invoke(e, 25); //выполняем метод с помощью рефлексии
        System.out.println("basics.Employee's salary after update: " + e.getSalary());

        Constructor emplConstr = cl1.getConstructor(String.class, int.class, LocalDate.class);
        //вызываем конструктор с помощью рефлексии
        Employee e1 = (Employee) emplConstr.newInstance("Name Surname", 100, LocalDate.of(2020, 03, 12));
        System.out.println(e1);

    }
}

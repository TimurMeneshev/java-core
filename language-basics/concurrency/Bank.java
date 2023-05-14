package concurrency;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final double[] accounts;
    //объект блокировки
    private Lock bankLock;
    //объект условия блокировки
    private Condition sufficientFunds;

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        //заполняем массив
        Arrays.fill(accounts, initialBalance);
        //Реентрабельная блокировка (совместное использование, повторный захват блокировки)
        bankLock = new ReentrantLock();
        //инициализация объекта условия
        sufficientFunds = bankLock.newCondition();
    }

    public void transfer(int from, int to, double amount) throws InterruptedException {
        //блокируем критический участок кода
        bankLock.lock();
        try {
            // условие, если недостаточно денег для перевода, то текущий поток помещается в набор ожидания
            while (accounts[from] < amount) sufficientFunds.await();
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total balance: %10.2f%n", getTotalBalance());
            System.out.print(" State: " + Thread.currentThread().getState() + "\n");
            // после изменения состояния счета, подаем сингнал потокам в ожидании для повторной проверки условия
            sufficientFunds.signalAll();
        } finally {
            // НЕЛЬЗЯ забывать отключать блокировку в конце участка кода
            bankLock.unlock();
        }
    }

    public double getTotalBalance() {
        bankLock.lock();
        try {
            double sum = 0;
            for (double a : accounts) sum += a;
            return sum;
        } finally {
            bankLock.unlock();
        }

    }

    public int size() {
        return accounts.length;
    }
}

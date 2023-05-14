package concurrency.sync;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final double[] accounts;
    //объект блокировки

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        //заполняем массив
        Arrays.fill(accounts, initialBalance);
    }

    public synchronized void transfer(int from, int to, double amount) throws InterruptedException {
        //условие блокировки
        while (accounts[from] < amount) wait();
        System.out.print(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf(" %10.2f from %d to %d", amount, from, to);
        accounts[to] += amount;
        System.out.printf(" Total balance: %10.2f%n", getTotalBalance());
        System.out.print(" State: " + Thread.currentThread().getState() + "\n");
        // после изменения состояния счета, подаем сингнал потокам в ожидании для повторной проверки условия
        notifyAll();
    }

    public synchronized double getTotalBalance() {
        double sum = 0;
        for (double a : accounts) sum += a;
        return sum;
    }

    public int size() {
        return accounts.length;
    }
}

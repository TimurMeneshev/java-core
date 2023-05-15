package concurrency.forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.DoublePredicate;

public class ForkJoinTest {
    public static void main(String[] args) {
        final int SIZE = 10000000;
        var numbers = new double[SIZE];
        for (int i = 0; i < SIZE; i++) numbers[i] = Math.random();
        var counter = new Counter(numbers, 0, numbers.length, x -> x > 0.5);
        var pool = new ForkJoinPool();
        pool.invoke(counter);
        System.out.println(counter.join());
    }

}

class Counter extends RecursiveTask<Integer> {

    public static final int THRESHOLD = 1000;
    private double[] values;
    private int from;
    private int to;
    private DoublePredicate filter;

    public Counter(double[] values, int from, int to, DoublePredicate filter) {
        this.values = values;
        this.from = from;
        this.to = to;
        this.filter = filter;
    }

    //метод должен формировать и вызовать подзадачи, затем объединять результат выполнения
    @Override
    protected Integer compute() {
        //если задачу можно решить в одном потоке, то решаем в одном
        if (to - from < THRESHOLD) {
            int count = 0;
            for (int i = 0; i < to; i++) {
                if (filter.test(values[i])) count++;
            }
            return count;
        } else { //параллелим вычисления
            int mid = (from + to) / 2;
            Counter first = new Counter(values, from, mid, filter);
            Counter second = new Counter(values, mid, to, filter);
            //одновременный запуск задач
            invokeAll(first, second);
            //объединение результатов
            return first.join() + second.join();
        }
    }
}
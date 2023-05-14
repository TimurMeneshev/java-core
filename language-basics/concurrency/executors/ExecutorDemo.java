package concurrency.executors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExecutorDemo {

    //подсчет кол-ва вхождений слова в файле
    public static long occurrences(String word, Path path) {
        try (var in = new Scanner(path)) {
            int count = 0;
            while (in.hasNext()) {
                if (in.next().equals(word)) count++;
            }
            return count;
        } catch (IOException e) {
            return 0L;
        }
    }

    //возвращает множество файлов в заданном каталоге
    public static Set<Path> descendants(Path rootDir) throws IOException {
        try (Stream<Path> entries = Files.walk(rootDir)) {
            return entries.filter(Files::isRegularFile)
                    .collect(Collectors.toSet());
        }
    }

    //выдает задачу поиска слова в файле
    public static Callable<Path> searchForTask(String word, Path path) {
        return () -> {
          try (var in = new Scanner(path)) {
              while (in.hasNext()) {
                  if (in.next().equals(word)) return path;
                  if (Thread.currentThread().isInterrupted()) {
                      System.out.println("Search in " + path
                        + " canceled.");
                      return null;
                  }
              }
              throw new NoSuchElementException();
          }
        };
    }

    public static void main(String[] args) throws InterruptedException,
            ExecutionException, IOException {
        try (var in = new Scanner(System.in)) {
            System.out.println("Enter base directory: ");
            String start = in.nextLine();
            System.out.println("Enter keyword: ");
            String word = in.nextLine();

            Set<Path> files = descendants(Path.of(start));
            //наполнение списка задач на подсчет вхождений
            var tasks = new ArrayList<Callable<Long>>();
            for (Path file : files) {
                Callable<Long> task = () -> occurrences(word, file);
                tasks.add(task);
            }

            ExecutorService executor = Executors.newCachedThreadPool();
            //ExecutorService executor = Executors.newSingleThreadExecutor();

            Instant startTime = Instant.now();
            //выполнение списка задач и получение ВСЕХ результатов (подсчет вхождений слова)
            List<Future<Long>> results = executor.invokeAll(tasks);
            long total = 0;
            for (Future<Long> result : results) total += result.get();
            Instant endTime = Instant.now();
            System.out.println("Occurrences of " + word + ": " + total);
            System.out.println("Time elapsed: "
                    + Duration.between(startTime, endTime).toMillis() + " ms");

            //наполнение списка задач на поиск слова
            var searchTasks = new ArrayList<Callable<Path>>();
            for (Path file : files) searchTasks.add(searchForTask(word, file));

            //выполнение списка задач и получение любого ПЕРВОГО резульатата
            Path found = executor.invokeAny(searchTasks);
            System.out.println(word + " occurs in: " + found);

            if (executor instanceof ThreadPoolExecutor) {
                System.out.println("Largest pool size: "
                        + ((ThreadPoolExecutor) executor).getLargestPoolSize());
                executor.shutdown();
            }
        }
    }
}

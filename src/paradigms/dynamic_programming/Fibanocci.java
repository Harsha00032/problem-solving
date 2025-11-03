package paradigms.dynamic_programming;

import java.util.HashMap;
import java.util.AbstractMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Demonstrates several Fibonacci implementations and measures their runtime using
 * the RunTimeCalculator utility. This class intentionally keeps multiple
 * implementations to compare complexity and performance:
 *
 * - getFibonacci: naive recursive implementation (exponential time) - useful
 *   to show how slow recursion is without memoization.
 * - getFibonacciMemoization: top-down DP using a HashMap to cache results
 *   (linear time, linear space).
 * - getFibonacciTabulization: bottom-up DP (tabulation) using an array
 *   (linear time, linear space).
 * - getFibonacciIterative: iterative O(n) time, O(1) space implementation.
 *
 * Notes / assumptions:
 * - Input n is expected to be >= 0. For n <= 0 an IllegalArgumentException is
 *   thrown. For n == 0 the Fibonacci value 0 is returned.
 * - Results are computed using the `long` type. Fibonacci values grow quickly
 *   and will overflow long for n > 92; this class does not attempt to detect
 *   overflow - it's focused on runtime demonstrations.
 * - The RunTimeCalculator helper is used via its static measurement helpers to
 *   keep the timing code concise and safe.
 */
public class Fibanocci {

    /**
     * Naive recursive Fibonacci implementation.
     * Time complexity: O(2^n) (exponential)
     * Space complexity: O(n) recursion depth
     *
     * WARNING: extremely slow for n > ~40. Keep only for comparison/demonstration.
     *
     * @param n fibonacci index (n >= 0)
     * @return fibonacci number at index n
     */
    private static long getFibonacci(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");
        if (n == 0) return 0;
        if (n <= 2) return 1;
        return getFibonacci(n - 1) + getFibonacci(n - 2);
    }

    /**
     * Public entry for memoized (top-down) Fibonacci. This method creates the
     * memo cache and delegates to the helper which performs recursion using the
     * cache to avoid duplicated work.
     *
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * @param n fibonacci index (n >= 0)
     * @return fibonacci number at index n
     */
    private static long getFibonacciMemoization(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");
        HashMap<Integer, Long> memo = new HashMap<>();
        // base cases
        memo.put(0, 0L);
        memo.put(1, 1L);
        return getFibonacciMemoizationHelper(n, memo);
    }

    // Helper that performs recursion with a provided memo map.
    private static long getFibonacciMemoizationHelper(int n, HashMap<Integer, Long> memo) {
        if (memo.containsKey(n)) return memo.get(n);
        long value = getFibonacciMemoizationHelper(n - 1, memo) + getFibonacciMemoizationHelper(n - 2, memo);
        memo.put(n, value);
        return value;
    }

    /**
     * Bottom-up tabulation (dynamic programming) implementation.
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * Corrects common bugs: ensures base values are initialized before using them
     * in the loop.
     *
     * @param n fibonacci index (n >= 0)
     * @return fibonacci number at index n
     */
    private static long getFibonacciTabulization(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");
        if (n == 0) return 0;
        if (n <= 2) return 1;

        long[] results = new long[n + 1];
        results[0] = 0L;
        results[1] = 1L;
        // results[2] will be computed in the loop below (or set explicitly)

        for (int i = 2; i <= n; i++) {
            results[i] = results[i - 1] + results[i - 2];
        }

        return results[n];
    }

    /**
     * Iterative O(1)-space Fibonacci implementation using two variables.
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * Useful when only the final value is needed and memory should be minimized.
     *
     * @param n fibonacci index (n >= 0)
     * @return fibonacci number at index n
     */
    private static long getFibonacciIterative(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");
        if (n == 0) return 0;
        if (n <= 2) return 1;

        long a = 1; // F(1)
        long b = 1; // F(2)
        for (int i = 3; i <= n; i++) {
            long c = a + b; // next value
            a = b;
            b = c;
        }
        return b;
    }

    /**
     * Run each Fibonacci implementation concurrently on its own thread and measure
     * the execution time of the computation itself. We use an
     * ExecutorService + CompletionService so tasks run concurrently and we can
     * print results as each completes (instead of blocking in submission order).
     *
     * Each submitted Callable returns a Map.Entry<String, Measurement<Long>>
     * where the key is the human-friendly name of the implementation and the
     * value is the measurement produced by RunTimeCalculator. Using
     * AbstractMap.SimpleEntry avoids adding extra types to the project.
     */
    public static void main(String[] args) {
        // Allow optional command-line override: `java ... Fibanocci 30`
        int n = 48; // default example. Keep <= 92 to avoid long overflow.
        if (args != null && args.length > 0) {
            try {
                n = Integer.parseInt(args[0]);
            } catch (NumberFormatException ex) {
                System.err.println("Invalid integer provided as first argument. Using default n=" + n);
            }
        }

        // Lambdas used below must capture a final or effectively-final variable.
        final int finalN = n;

        // Prepare named tasks first. We size the thread pool to the number of
        // tasks (bounded by available processors) so each task can run on its
        // own thread when possible. This improves concurrency for short tests.
        List<Map.Entry<String, Callable<Map.Entry<String, RunTimeCalculator.Measurement<Long>>>>> tasks = new ArrayList<>();

        // Helper factory to build callables that run and measure the computation.
        tasks.add(new AbstractMap.SimpleEntry<>("Naive recursive", (Callable<Map.Entry<String, RunTimeCalculator.Measurement<Long>>>) () -> {
            RunTimeCalculator.Measurement<Long> m = RunTimeCalculator.measure((java.util.function.Supplier<Long>) () -> getFibonacci(finalN));
            return new AbstractMap.SimpleEntry<>("Naive recursive", m);
        }));

        tasks.add(new AbstractMap.SimpleEntry<>("Memoization (top-down)", (Callable<Map.Entry<String, RunTimeCalculator.Measurement<Long>>>) () -> {
            RunTimeCalculator.Measurement<Long> m = RunTimeCalculator.measure((java.util.function.Supplier<Long>) () -> getFibonacciMemoization(finalN));
            return new AbstractMap.SimpleEntry<>("Memoization (top-down)", m);
        }));

        tasks.add(new AbstractMap.SimpleEntry<>("Tabulation (bottom-up)", (Callable<Map.Entry<String, RunTimeCalculator.Measurement<Long>>>) () -> {
            RunTimeCalculator.Measurement<Long> m = RunTimeCalculator.measure((java.util.function.Supplier<Long>) () -> getFibonacciTabulization(finalN));
            return new AbstractMap.SimpleEntry<>("Tabulation (bottom-up)", m);
        }));

        tasks.add(new AbstractMap.SimpleEntry<>("Iterative O(1) space", (Callable<Map.Entry<String, RunTimeCalculator.Measurement<Long>>>) () -> {
            RunTimeCalculator.Measurement<Long> m = RunTimeCalculator.measure((java.util.function.Supplier<Long>) () -> getFibonacciIterative(finalN));
            return new AbstractMap.SimpleEntry<>("Iterative O(1) space", m);
        }));

        // Now create an executor sized to the number of tasks (but do not exceed
        // the available processor count). This ensures each task can execute on
        // its own thread where possible.
        int poolSize = Math.min(tasks.size(), Runtime.getRuntime().availableProcessors());
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        CompletionService<Map.Entry<String, RunTimeCalculator.Measurement<Long>>> completion = new ExecutorCompletionService<>(executor);

        // Submit all tasks to the completion service
        for (Map.Entry<String, Callable<Map.Entry<String, RunTimeCalculator.Measurement<Long>>>> e : tasks) {
            completion.submit(e.getValue());
        }

        // Collect and print results as tasks finish
        int remaining = tasks.size();
        try {
            while (remaining-- > 0) {
                Future<Map.Entry<String, RunTimeCalculator.Measurement<Long>>> future = completion.take(); // blocks until a task completes
                Map.Entry<String, RunTimeCalculator.Measurement<Long>> result = future.get();
                String name = result.getKey();
                RunTimeCalculator.Measurement<Long> measurement = result.getValue();

                System.out.println(name + ": F(" + finalN + ") = " + measurement.getResult());
                System.out.println("Time taken (" + name.toLowerCase() + "): " + measurement.formatted() + "\n");
            }
        } catch (Exception ex) {
            // If something unexpected happens we at least print the stack for debugging.
            ex.printStackTrace();
        } finally {
            executor.shutdownNow();
        }
    }
}

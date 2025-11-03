package paradigms.dynamic_programming;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

/**
 * Utility for measuring elapsed time of code paths.
 * Key points:
 * - Uses System.nanoTime() for high-resolution, monotonic timestamps.
 * - Instance-based (no static start/stop) so multiple timers can run concurrently.
 * - Implements AutoCloseable so it can be used with try-with-resources to auto-stop.
 * - Provides convenience static helpers for measuring Runnable/Callables and returning
 *   both the result and measured duration.
 * Typical usage:
 * try (RunTimeCalculator t = RunTimeCalculator.start()) {
 *     // code to measure
 * }
 * System.out.println("Elapsed ms: " + t.toMillis()); // after stop/close
 *
 * Measurement helpers (static):
 * - measure(Runnable) -> Measurement<Void>
 * - measure(Callable<T>) -> Measurement<T>
 * - measure(Supplier<T>) -> Measurement<T>
 */
public final class RunTimeCalculator implements AutoCloseable {

    // The timestamp (in nanoseconds) captured when this instance was created.
    // We capture this once and base all elapsed calculations off it.
    private final long startNanos;

    // The timestamp (in nanoseconds) when stop() was called. Volatile because it may
    // be written by one thread and read by another.
    private volatile long endNanos;

    // Whether the timer has been stopped. Volatile for safe concurrent reads.
    private volatile boolean stopped;

    // Private constructor to force use of the factory start() method. When created
    // we immediately capture the start time using System.nanoTime().
    private RunTimeCalculator() {
        this.startNanos = System.nanoTime();
    }

    /**
     * Create and start a new RunTimeCalculator.
     *
     * @return a started RunTimeCalculator instance
     */
    public static RunTimeCalculator start() {
        return new RunTimeCalculator();
    }

    /**
     * Stop the timer.
     *
     * <p>This method is safe to call multiple times. The first call records the
     * end timestamp; subsequent calls do nothing. Synchronization is used to ensure
     * endNanos is set exactly once when stop() is invoked concurrently.
     */
    public void stop() {
        if (!stopped) {
            synchronized (this) {
                if (!stopped) {
                    // Record the end time in nanoseconds and mark stopped.
                    endNanos = System.nanoTime();
                    stopped = true;
                }
            }
        }
    }

    /**
     * Get the elapsed time in nanoseconds.
     *
     * <p>If stop() was called, the difference is endNanos - startNanos. Otherwise
     * the method takes the current time so callers can poll the running elapsed
     * time without stopping the timer.
     *
     * @return elapsed time in nanoseconds
     */
    public long toNanos() {
        long currentEnd = stopped ? endNanos : System.nanoTime();
        return currentEnd - startNanos;
    }

    /**
     * Get the elapsed time in milliseconds (truncated).
     *
     * @return elapsed time in milliseconds
     */
    public long toMillis() {
        return toNanos() / 1_000_000L;
    }

    /**
     * Convert the elapsed time into a java.time.Duration.
     *
     * @return Duration representing the elapsed time
     */
    public Duration duration() {
        return Duration.ofNanos(toNanos());
    }

    public String getFormattedDuration() {
        return formatted(duration());
    }

    /**
     * Produce a human-friendly formatted string describing the elapsed time.
     * Examples: "1ms", "2s 50ms", "3m 10s 5ms", "1h 2m 3s 4ms".
     * <p>
     * The formatting uses Duration's convenience accessors (toHours(), toMinutesPart(), etc.)
     * which are available on Java 9+. If you are on an earlier Java version, consider
     * changing the formatting logic accordingly.
     *
     * @return formatted human-readable elapsed time
     */
    public String formatted(Duration d) {
        long hours = d.toHours();
        int minutes = d.toMinutesPart();
        int seconds = d.toSecondsPart();
        int millis = d.toMillisPart();

        if (hours > 0) {
            return String.format("%dh %dm %ds %dms", hours, minutes, seconds, millis);
        } else if (minutes > 0) {
            return String.format("%dm %ds %dms", minutes, seconds, millis);
        } else if (seconds > 0) {
            return String.format("%ds %dms", seconds, millis);
        } else {
            return String.format("%dms", millis);
        }
    }

    /**
     * AutoCloseable implementation: stop the timer when used in try-with-resources.
     * This allows the pattern:
     * {@code try (RunTimeCalculator t = RunTimeCalculator.start()) { // measure }}
     * // t.stop() is called implicitly by close()
     */
    @Override
    public void close() {
        stop();
    }

    /* --------------------------------------------------------------------- */
    /* Convenience static utilities for quick measurements without creating an  */
    /* instance. These validate inputs and return simple metrics or a          */
    /* Measurement wrapper that includes both result and duration.            */
    /* --------------------------------------------------------------------- */

    /**
     * Measure how many milliseconds a Runnable takes to execute.
     *
     * <p>Useful for one-off measurements. The Runnable must not be null. The
     * return value is truncated to milliseconds (i.e., fractional milliseconds
     * are discarded).
     *
     * @param task the Runnable to execute and measure
     * @return elapsed milliseconds
     * @throws NullPointerException if task is null
     */
    public static long measureMillis(Runnable task) {
        Objects.requireNonNull(task, "task");
        long s = System.nanoTime();
        task.run();
        return (System.nanoTime() - s) / 1_000_000L;
    }

    /**
     * Measure a Callable, returning both the task result and measured Duration.
     *
     * <p>Useful when you need the return value from the measured operation. The
     * method forwards any exception thrown by the Callable.
     *
     * @param task the Callable to execute and measure
     * @param <T> the Callable's return type
     * @return a Measurement wrapping the result and duration
     * @throws Exception any exception thrown by the Callable
     * @throws NullPointerException if task is null
     */
    public static <T> Measurement<T> measure(Callable<T> task) throws Exception {
        Objects.requireNonNull(task, "task");
        long s = System.nanoTime();
        T result = task.call();
        long nanos = System.nanoTime() - s;
        return new Measurement<>(result, Duration.ofNanos(nanos));
    }

    /**
     * Measure a Supplier, returning both the supplier result and measured Duration.
     *
     * <p>Convenience overload for code that uses Supplier instead of Callable.
     * Supplier.get() cannot throw a checked exception; this method wraps the
     * call and returns a Measurement just like the Callable variant.
     *
     * @param supplier the Supplier to execute and measure
     * @param <T> the Supplier's return type
     * @return a Measurement wrapping the result and duration
     * @throws NullPointerException if supplier is null
     */
    public static <T> Measurement<T> measure(Supplier<T> supplier) {
        Objects.requireNonNull(supplier, "supplier");
        long s = System.nanoTime();
        T result = supplier.get();
        long nanos = System.nanoTime() - s;
        return new Measurement<>(result, Duration.ofNanos(nanos));
    }

    /**
     * Measure a Runnable and return a Measurement<Void> (result is null).
     *
     * <p>This is useful when you want a Measurement object for code that doesn't
     * return a value. The returned Measurement has a null result and the measured Duration.
     *
     * @param task the Runnable to execute and measure
     * @return Measurement<Void> where getResult() == null
     * @throws NullPointerException if task is null
     */
    public static Measurement<Void> measureRunnable(Runnable task) {
        Objects.requireNonNull(task, "task");
        long s = System.nanoTime();
        task.run();
        long nanos = System.nanoTime() - s;
        return new Measurement<>(null, Duration.ofNanos(nanos));
    }

    /**
     * Simple holder of a measured result and its duration.
     *
     * @param <T> the type of the measured computation result
     */
    public static final class Measurement<T> {
        private final T result;
        private final Duration duration;

        /**
         * Create a Measurement.
         * @param result the Callable's return value (maybe null)
         * @param duration the measured duration (non-null)
         */
        public Measurement(T result, Duration duration) {
            this.result = result;
            this.duration = duration;
        }

        /**
         * The original result produced by the measured task. May be null if the
         * Callable returned null.
         */
        public T getResult() {
            return result;
        }

        /**
         * The measured duration as a java.time.Duration.
         */
        public Duration getDuration() {
            return duration;
        }

        /**
         * Convenience to get the duration as milliseconds (truncated).
         */
        public long toMillis() {
            return duration.toMillis();
        }

        /**
         * Minimal formatted representation of the duration (milliseconds + "ms").
         */
        public String formatted() {
            long millis = duration.toMillis();
            return millis + "ms";
        }
    }
}

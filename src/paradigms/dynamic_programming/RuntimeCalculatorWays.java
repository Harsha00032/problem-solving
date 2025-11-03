package paradigms.dynamic_programming;

public class RuntimeCalculatorWays {

    private static long fibonacci(int n) {
        if (n <= 2 )
            return 1;
        return fibonacci(n-1) + fibonacci(n-2);
    }

    public static void main(String[] args) {
        int n = 30; // smaller n for demo so it finishes quickly

        // 1) Instance-based timer with try-with-resources (Java 9+ variable form)
        RunTimeCalculator rtc = RunTimeCalculator.start();
        long result;
        try (rtc) {
            result = fibonacci(n);
        }
        // rtc is closed (stop() invoked). Print result and formatted duration.
        System.out.println("[instance] Fibonacci(" + n + ") = " + result);
        System.out.println("[instance] duration = " + rtc.getFormattedDuration());

        // 2) measure(Callable) -> returns Measurement<T>
        try {
            RunTimeCalculator.Measurement<Long> mCallable = RunTimeCalculator.measure((java.util.concurrent.Callable<Long>) () -> fibonacci(n));
            System.out.println("[callable] Fibonacci(" + n + ") = " + mCallable.getResult());
            System.out.println("[callable] duration = " + mCallable.formatted());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 3) measure(Supplier) -> returns Measurement<T>
        RunTimeCalculator.Measurement<Long> mSupplier = RunTimeCalculator.measure((java.util.function.Supplier<Long>) () -> fibonacci(n));
        System.out.println("[supplier] Fibonacci(" + n + ") = " + mSupplier.getResult());
        System.out.println("[supplier] duration = " + mSupplier.formatted());

        // 4) measureRunnable(Runnable) -> returns Measurement<Void>
        RunTimeCalculator.Measurement<Void> mRunnable = RunTimeCalculator.measureRunnable(() -> {
            // run the computation but discard the result
            fibonacci(n);
        });
        System.out.println("[runnable] duration = " + mRunnable.formatted());

        // 5) measureMillis(Runnable) -> returns primitive ms
        long ms = RunTimeCalculator.measureMillis(() -> fibonacci(n));
        System.out.println("[measureMillis] duration = " + ms + "ms");

        // 6) Direct use of instance to poll elapsed while running (example)
        RunTimeCalculator poller = RunTimeCalculator.start();
        // do some quick work
        fibonacci(10);
        // poll elapsed without stopping
        System.out.println("[poll] elapsed (ms) = " + poller.toMillis());
        poller.stop();
    }
}

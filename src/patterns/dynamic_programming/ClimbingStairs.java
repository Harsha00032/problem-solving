package patterns.dynamic_programming;

/*You are climbing a staircase. It takes n steps to reach the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?*/
public class ClimbingStairs {

    private static long waysToClimb(int n) {
        if (n <= 2)
            return n;

        long prev = 1;
        long curr = 2;
        long res = 0;

        for (int i=3; i<=n ; i++) {

            res = prev + curr;
            prev = curr;
            curr = res;
        }

        return res;
    }

    public static void main(String[] args) {
        int n = 50;
        System.out.println("The give n is => "+ n);
        System.out.println("The given ways to climb are => "+ waysToClimb(n));
    }
}

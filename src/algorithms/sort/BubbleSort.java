package algorithms.sort;

/*
Just like the movement of air bubbles in the water that rise up to the surface,
each element of the array move to the end in each iteration. Therefore, it is called a bubble sort.
 */
public class BubbleSort {

    private static void bubbleSort(int[] nums) {

        if (nums == null || nums.length <= 1)
            return;

        for (int i = 0; i<nums.length; i++) {
            for (int j=1; j<nums.length -i; j++) {
                if (nums[j] < nums[j-1]) {
                    int temp = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {43, 42, 41, 40, 39};
        bubbleSort(arr);
        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}

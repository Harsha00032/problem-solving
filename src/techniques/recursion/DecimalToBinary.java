package techniques.recursion;

public class DecimalToBinary {

    private static String decimalToBinary(int num){
        //base condition
        if (num < 2)
            return String.valueOf(num);
        int carry = num % 2;
        return decimalToBinary(num/2) + carry;
    }

    public static void main(String[] args) {

        int n = 513;
        System.out.println("Decimal number given =>"+ n);
        System.out.println("Binary representation of "+n+" is =>"+ decimalToBinary(n));
    }
}

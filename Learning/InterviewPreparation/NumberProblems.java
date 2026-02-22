package Learning.InterviewPreparation;

import java.util.*;

/**
 * Number Problems for Interview Preparation
 */

public class NumberProblems {
    
    /**
     * 1. Check if Number is Prime
     */
    public static boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        
        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }
    
    /**
     * 2. Generate Prime Numbers (Sieve of Eratosthenes)
     */
    public static List<Integer> sieveOfEratosthenes(int n) {
        List<Integer> primes = new ArrayList<>();
        boolean[] isPrime = new boolean[n + 1];
        
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }
        
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        
        return primes;
    }
    
    /**
     * 3. Fibonacci Series
     */
    public static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    /**
     * 4. Fibonacci Series (Optimized with Memoization)
     */
    public static int fibonacciMemo(int n, Map<Integer, Integer> memo) {
        if (n <= 1) return n;
        
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        
        int result = fibonacciMemo(n - 1, memo) + fibonacciMemo(n - 2, memo);
        memo.put(n, result);
        return result;
    }
    
    /**
     * 5. Find GCD (Greatest Common Divisor)
     */
    public static int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    /**
     * 6. Find LCM (Least Common Multiple)
     */
    public static int findLCM(int a, int b) {
        return (a * b) / findGCD(a, b);
    }
    
    /**
     * 7. Check if Number is Palindrome
     */
    public static boolean isPalindromeNumber(int num) {
        int original = num;
        int reversed = 0;
        
        while (num > 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num /= 10;
        }
        
        return original == reversed;
    }
    
    /**
     * 8. Count Number of Digits
     */
    public static int countDigits(int num) {
        if (num == 0) return 1;
        
        int count = 0;
        while (num > 0) {
            count++;
            num /= 10;
        }
        return count;
    }
    
    /**
     * 9. Sum of Digits
     */
    public static int sumOfDigits(int num) {
        int sum = 0;
        
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        
        return sum;
    }
    
    /**
     * 10. Check if Number is Armstrong (Narcissistic)
     * Example: 153 = 1^3 + 5^3 + 3^3
     */
    public static boolean isArmstrong(int num) {
        int numDigits = countDigits(num);
        int sum = 0;
        int original = num;
        
        while (num > 0) {
            int digit = num % 10;
            sum += Math.pow(digit, numDigits);
            num /= 10;
        }
        
        return sum == original;
    }
    
    /**
     * 11. Find Factorial
     */
    public static long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }
    
    /**
     * 12. Check if Number is Perfect Square
     */
    public static boolean isPerfectSquare(int num) {
        if (num < 0) return false;
        
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }
    
    /**
     * 13. Sum of First N Natural Numbers
     */
    public static int sumOfNaturalNumbers(int n) {
        return (n * (n + 1)) / 2;
    }
    
    /**
     * 14. Find Power without using Math.pow()
     */
    public static long findPower(int base, int exponent) {
        long result = 1;
        
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        
        return result;
    }
    
    /**
     * 15. Count Set Bits (1s in binary representation)
     */
    public static int countSetBits(int num) {
        int count = 0;
        
        while (num > 0) {
            count += num & 1;
            num >>= 1;
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Number Interview Problems ===\n");
        
        System.out.println("1. Check if Prime:");
        System.out.println("   17 is prime: " + isPrime(17));
        System.out.println("   20 is prime: " + isPrime(20));
        
        System.out.println("\n2. Generate Primes (up to 30):");
        System.out.println("   " + sieveOfEratosthenes(30));
        
        System.out.println("\n3. Fibonacci (position 7):");
        System.out.println("   " + fibonacci(7));
        
        System.out.println("\n4. Fibonacci with Memoization (position 10):");
        System.out.println("   " + fibonacciMemo(10, new HashMap<>()));
        
        System.out.println("\n5. GCD of 48 and 18:");
        System.out.println("   " + findGCD(48, 18));
        
        System.out.println("\n6. LCM of 48 and 18:");
        System.out.println("   " + findLCM(48, 18));
        
        System.out.println("\n7. Is 121 Palindrome:");
        System.out.println("   " + isPalindromeNumber(121));
        System.out.println("   Is 123 Palindrome: " + isPalindromeNumber(123));
        
        System.out.println("\n8. Count Digits in 12345:");
        System.out.println("   " + countDigits(12345));
        
        System.out.println("\n9. Sum of Digits in 12345:");
        System.out.println("   " + sumOfDigits(12345));
        
        System.out.println("\n10. Armstrong Numbers (100 to 500):");
        for (int i = 100; i <= 500; i++) {
            if (isArmstrong(i)) {
                System.out.println("    " + i);
            }
        }
        
        System.out.println("\n11. Factorial of 5:");
        System.out.println("   " + factorial(5));
        
        System.out.println("\n12. Perfect Squares (1 to 100):");
        for (int i = 1; i <= 100; i++) {
            if (isPerfectSquare(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        
        System.out.println("\n13. Sum of First 10 Natural Numbers:");
        System.out.println("   " + sumOfNaturalNumbers(10));
        
        System.out.println("\n14. Power (2^5):");
        System.out.println("   " + findPower(2, 5));
        
        System.out.println("\n15. Set Bits in 13 (binary: 1101):");
        System.out.println("   " + countSetBits(13));
    }
}


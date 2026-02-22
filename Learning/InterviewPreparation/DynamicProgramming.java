package Learning.InterviewPreparation;

import java.util.*;

/**
 * Dynamic Programming Problems for Interview Preparation
 */

public class DynamicProgramming {
    
    /**
     * 1. Fibonacci with DP (Bottom-up approach)
     */
    public static int fibonacciDP(int n) {
        if (n <= 1) return n;
        
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
    }
    
    /**
     * 2. 0/1 Knapsack Problem
     */
    public static int knapsack01(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(
                        values[i - 1] + dp[i - 1][w - weights[i - 1]],
                        dp[i - 1][w]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        
        return dp[n][capacity];
    }
    
    /**
     * 3. Longest Common Subsequence (LCS)
     */
    public static int lcs(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[m][n];
    }
    
    /**
     * 4. Longest Increasing Subsequence (LIS)
     */
    public static int lis(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        int maxLIS = 0;
        for (int len : dp) {
            maxLIS = Math.max(maxLIS, len);
        }
        
        return maxLIS;
    }
    
    /**
     * 5. Edit Distance (Levenshtein Distance)
     */
    public static int editDistance(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                        Math.min(dp[i - 1][j], dp[i][j - 1]),
                        dp[i - 1][j - 1]
                    );
                }
            }
        }
        
        return dp[m][n];
    }
    
    /**
     * 6. Coin Change Problem
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }
        
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
    
    /**
     * 7. Maximum Subarray Sum (Kadane's Algorithm)
     */
    public static int maxSubarraySum(int[] arr) {
        int maxSum = arr[0];
        int currentSum = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            currentSum = Math.max(arr[i], currentSum + arr[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }
    
    /**
     * 8. House Robber Problem
     */
    public static int houseRobber(int[] houses) {
        if (houses.length == 0) return 0;
        if (houses.length == 1) return houses[0];
        
        int[] dp = new int[houses.length];
        dp[0] = houses[0];
        dp[1] = Math.max(houses[0], houses[1]);
        
        for (int i = 2; i < houses.length; i++) {
            dp[i] = Math.max(houses[i] + dp[i - 2], dp[i - 1]);
        }
        
        return dp[houses.length - 1];
    }
    
    /**
     * 9. Minimum Path Sum in Grid
     */
    public static int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols];
        
        dp[0][0] = grid[0][0];
        
        // Fill first row
        for (int j = 1; j < cols; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        
        // Fill first column
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        
        // Fill rest of the grid
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        
        return dp[rows - 1][cols - 1];
    }
    
    /**
     * 10. Number of Ways to Make Change
     */
    public static int countWaysToMakeChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        
        return dp[amount];
    }
    
    public static void main(String[] args) {
        System.out.println("=== Dynamic Programming Interview Problems ===\n");
        
        System.out.println("1. Fibonacci (DP):");
        System.out.println("   fib(10) = " + fibonacciDP(10));
        
        System.out.println("\n2. 0/1 Knapsack:");
        int[] weights = {2, 3, 4, 5};
        int[] values = {3, 4, 5, 6};
        int capacity = 8;
        System.out.println("   Max value: " + knapsack01(weights, values, capacity));
        
        System.out.println("\n3. Longest Common Subsequence:");
        System.out.println("   LCS('AGGTAB', 'GXTXAYB') = " + lcs("AGGTAB", "GXTXAYB"));
        
        System.out.println("\n4. Longest Increasing Subsequence:");
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("   LIS length: " + lis(arr));
        
        System.out.println("\n5. Edit Distance:");
        System.out.println("   Edit distance('horse', 'ros') = " + editDistance("horse", "ros"));
        
        System.out.println("\n6. Coin Change:");
        int[] coins = {1, 2, 5};
        System.out.println("   Min coins for amount 5: " + coinChange(coins, 5));
        
        System.out.println("\n7. Maximum Subarray Sum:");
        int[] subArray = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("   Max sum: " + maxSubarraySum(subArray));
        
        System.out.println("\n8. House Robber:");
        int[] houses = {1, 2, 3, 1};
        System.out.println("   Max money: " + houseRobber(houses));
        
        System.out.println("\n9. Minimum Path Sum:");
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println("   Min path sum: " + minPathSum(grid));
        
        System.out.println("\n10. Ways to Make Change:");
        System.out.println("   Ways to make 5 with [1,2,5]: " + 
                          countWaysToMakeChange(coins, 5));
    }
}


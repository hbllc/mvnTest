package com.llc.test;

/**
 * @author lilichuan
 */
public class A {
        // weights[]代表每个物品的重量，values[]代表每个物品的价值，capacity是背包的容量
        public static int knapSack(int weights[], int values[], int capacity) {
            int numOfItems = weights.length;
            int[][] dp = new int[numOfItems + 1][capacity + 1];

            // 动态规划填充表格
            for (int i = 0; i <= numOfItems; i++) {
                for (int w = 0; w <= capacity; w++) {
                    if (i == 0 || w == 0)
                        dp[i][w] = 0; // 初始化边界条件
                    else if (weights[i - 1] <= w)
                        dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                    else
                        dp[i][w] = dp[i - 1][w];
                }
            }

            return dp[numOfItems][capacity]; // 返回最大价值
        }

        public static void main(String args[]) {
            int values[] = new int[]{60, 100, 120};  // 物品的价值
            int weights[] = new int[]{10, 20, 30};  // 物品的重量
            int capacity = 50;  // 背包的容量
            System.out.println(knapSack(weights, values, capacity));
        }

}



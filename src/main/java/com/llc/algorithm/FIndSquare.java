package com.llc.algorithm;

/**
 * @author lilichuan
 */
public class FIndSquare {
    public static void main(String[] args) {

        int length = 1680;
        int width = 640;

        System.out.println(findSquare(length, width));

    }

    /**
     * 找到最合适的正方形
     * @param length 长度
     * @param width 宽度
     * @return 最大的正方形
     */
    private static int findSquare(int length, int width) {
        if(length %width == 0){
            return width;
        }

        return findSquare(width, length % width);
    }
}

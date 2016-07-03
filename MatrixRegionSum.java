import java.awt.Point;

public class Solution {
    private int[][] cache;
    
    public Solution(int[][] matrix) {
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        cache = new int[rowLen][colLen];
        // precompute the resion sum where top left is fixed at the origin
        cache[0][0] = matrix[0][0];
        
        for (int col = 1; col < colLen; col++) {
            cache[0][col] = matrix[0][col] + cache[0][col - 1];
        }
        
        for (int row = 1; row < rowLen; row++) {
            cache[row][0] = matrix[row][0] + cache[row - 1][0];
        }
        
        for (int row = 1; row < rowLen; row++) {
            for (int col = 1; col < colLen; col++) {
                cache[row][col] = matrix[row][col] + cache[row - 1][col] + cache[row][col - 1] - cache[row - 1][col - 1];
            }
        }
    }
    
    public int regionSum(Point topLeft, Point bottomRight) {
        if (topLeft.x == 0 && topLeft.y == 0) {
            return cache[bottomRight.x][bottomRight.y];
        } else if (topLeft.x == 0) {
            return cache[bottomRight.x][bottomRight.y] - cache[bottomRight.x][topLeft.y - 1];
        } else if (topLeft.y == 0) {
            return cache[bottomRight.x][bottomRight.y] - cache[topLeft.x - 1][bottomRight.y];
        }
        return cache[bottomRight.x][bottomRight.y] - cache[bottomRight.x][topLeft.y - 1] - cache[topLeft.x - 1][bottomRight.y] + cache[topLeft.x - 1][topLeft.y - 1];
    }
    
    public static void main(String[] args) {
        int[][] arr = { {1, 2, -1, -4, -20},
                        {-8, -3, 4, 2, 1},
                        {3, 8, 10, 1, 3},
                        {-4, -1, 1, 7, -6} };
        
        Solution s = new Solution(arr);
        // System.out.println(s.regionSum(new Point(1, 1), new Point(2, 3)));
        // System.out.println(s.regionSum(new Point(1, 0), new Point(2, 3)));
        // System.out.println(s.regionSum(new Point(0, 1), new Point(2, 3)));
        // System.out.println(s.regionSum(new Point(0, 0), new Point(2, 3)));
        System.out.println(s.regionSum(new Point(1, 0), new Point(1, 0)));
        System.out.println(s.regionSum(new Point(0, 2), new Point(0, 2)));
    }
}

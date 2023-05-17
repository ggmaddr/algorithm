//Let's define a rhombic area frame of size r as the set of all cells exactly r - 1 step away (up, down, left, or right) from a given center cell in a matrix. The number r is called the radius of the rhombic area frame. In the image below, the black cell is the center of the rhombic area, and every cell contains a number representing the distance to the center - the radius. All cells with number 1 in them represent a rhombic area frame of size 1, all cells with number 2 in them represent a rhombic area frame of size 2, etc. Cells with the same radius are colored in the same color. The distance between a center cell (centerX, centerY) and another cell (cellX, cellY) is calculated as follows distance = abs(centerX - cellX) + abs(centerY - cellY) + 1, where abs(x) is an absolute value of x.
//given a rectangular matrix  arr[][] of integer matrix and an integer radius r, your task is the following, for each possible center cell of the matrix find the sum of all elements of the rhombic area frame of size radius. from among these sums, find the highest value, return the integer representing the highest sum
public class Rhombic {
    public static int highestRhombicFrameSum(int[][] matrix, int radius) {
        int maxSum = Integer.MIN_VALUE;

        for (int i = radius - 1; i < matrix.length - (radius - 1); i++) {
            for (int j = radius - 1; j < matrix[0].length - (radius - 1); j++) {
                System.out.println("current center: " + i + " "+ j);
                int sum = rhombicFrameSum(matrix, i, j, radius);
                System.out.println(sum);
                maxSum = Math.max(sum, maxSum);
            }
        }
        return maxSum;
    }

    public static int rhombicFrameSum(int[][] matrix, int centerX, int centerY, int radius) {
        int sum = 0;

        int[] top = {centerX-(radius-1), centerY}; //top(x,y)
        int[] down = {centerX+radius-1, centerY};//down(x,y)

        int yLeft = top[1]-1; //init with topY
        int yRight = top[1]+1;
        sum+=matrix[top[0]][top[1]]; //get top
        for(int x = top[0]+1; x < down[0];x++) //go from top+1 -> down-1
        {
            sum+= matrix[x][yLeft] + matrix[x][yRight] ;
            if (x < centerX) //if upper half, go out
            {
                yLeft--;
                yRight++;
            }
            else {  //if upper half, go in
                yLeft++;
                yRight--;
            }
        }
        sum+=matrix[down[0]][down[1]]; //get down
        return sum;
    }

    public static void main(String[] args) {
//        int[][] matrix = {{10,0,4,2,7,3}, //31
//                {6,2,7,2,1,1},
//                {4,1,4,5,3,0},
//                {3,5,2,1,4,6},
//                {3,4,6,1,0,4},
//                {3,4,6,1,0,4},};
//        int radius = 3;
        int[][] matrix = {{4,5,10,7,2,9,1,6,6,7}, //32
                {6,0,1,8,10,0,10,6,10,5},
                {5,1,2,0,1,2,2,0,6,8},
                {8,2,7,0,6,8,6,8,10,4},
                {1,9,5,1,8,10,0,6,10,9},
                {0,9,3,7,2,10,1,7,5,6},
                {9,2,0,1,3,10,3,8,7,9},
                {8,8,4,5,10,2,9,8,7,2},
                {8,8,4,5,10,2,9,8,7,2},
                {7,8,8,3,10,1,4,8,0,8},
        };
        int radius = 2;

        System.out.println("Answer: = "+highestRhombicFrameSum(matrix, radius));


    }
}

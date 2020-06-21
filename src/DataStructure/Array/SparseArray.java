package DataStructure.Array;

/**
 * 稀疏数组
 * 当一个数组中大部分元素为０，或者为同一个值的数组时，可以使用稀疏数组来保存该数组
 *
 * 1) 记录数组一共有几行几列，有多少个不同的值
 * 2) 把具有不同值的元素的行、列及值记录在一个小规模的数组中，从而缩小程序的规模
 * @author yzz
 * @create 2020-06-21 21:15
 */
public class SparseArray {

    /**
     * 将二维数组转为稀疏数组
     * @param arr
     * @return
     */
    public static int[][] toSparseArray(int[][] arr) {

        int row = arr.length;
        int column = arr[0].length;

        int sum = 0;                 // arr中非0数据个数
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if(arr[i][j] != 0) {
                    sum++;
                }
            }
        }

        // 创建对应的稀疏数组
        int[][] sparseArr = new int[sum+1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = row;
        sparseArr[0][1] = column;
        sparseArr[0][2] = sum;

        // 遍历二维数组，将非0的值存放到 sparseArr中
        int count = 0;             // 用于记录是第几个非0数据
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if(arr[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = arr[i][j];
                }
            }
        }
        return sparseArr;
    }

    /**
     * 将稀疏数组 恢复成 原始的二维数组
     * @param sparseArr
     * @return
     */
    public static int[][] revertSparseArray(int[][] sparseArr) {
        //1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int arr[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        //2. 在读取稀疏数组后几行的数据(从第二行开始)，并赋给 原始的二维数组 即可
        for(int i = 1; i < sparseArr.length; i++) {
            arr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        return arr;
    }

    public static void main(String[] args) {

        // 创建一个原始的二维数组 11 * 11
        // 0: 表示没有棋子， 1 表示 黑子 2 表蓝子
        int chessArr[][] = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[4][5] = 2;
        // 输出原始的二维数组
        System.out.println("原始的二维数组~~");
        for (int[] row : chessArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        int[][] sparseArr = toSparseArray(chessArr);
        // 输出稀疏数组的形式
        System.out.println();
        System.out.println("得到稀疏数组为~~~~");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }
        System.out.println();


        chessArr = revertSparseArray(sparseArr);
        // 输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组");
        for (int[] row : chessArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

}

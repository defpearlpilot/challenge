package test;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    /*
 * Complete the function below.
 */

    static int maxDifference(int[] a) {
        int max = Integer.MIN_VALUE;

        //7 = 1
        for (int j = a.length - 1; j > 0; j--)
        {
            //6 = 8
            for (int i = j - 1; i > 0; i--)
            {
                System.out.println("i(" + i + ")=" + a[i] + " j(" + j + ")=" + a[j]);
                if (a[i] > a[j])
                {
                    System.out.println("breaking...");
                    break;
                }

                int diff = a[j] - a[i];
                System.out.println("" + diff);

                max = Math.max(max, diff);
            }
            System.out.println("next...");
        }

        return max;
    }

    public static void main(String[] args) throws IOException {
//        Scanner in = new Scanner(System.in);
//        final String fileName = System.getenv("OUTPUT_PATH");
//        BufferedWriter bw = new BufferedWriter(new FileWriter("out.val"));
//        int res;
//
//        int _a_size = 0;
//        _a_size = Integer.parseInt(in.nextLine());
//        int[] _a = new int[_a_size];
//        int _a_item;
//        for(int _a_i = 0; _a_i < _a_size; _a_i++) {
//            _a_item = Integer.parseInt(in.nextLine());
//            _a[_a_i] = _a_item;
//        }

        int [] a = new int[]{3,2,5,7,8,1};
        int res = maxDifference(a);

        System.out.println(res);
//        bw.write(String.valueOf(res));
//        bw.newLine();
//
//        bw.close();
    }
}
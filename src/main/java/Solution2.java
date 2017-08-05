import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution2 {


    static long sumOfIntegers(int[] arr) {

        int index = 1;

        long sum = 0;

        while (index < arr.length - 1) {
            long temp = arr[index] + arr[index+1];
            sum += temp;
        }

        return sum;

    }

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        long res;

        int _arr_size = Integer.parseInt(in.nextLine());
        int[] _arr = new int[_arr_size];
        int _arr_item;
        for(int _arr_i = 0; _arr_i < _arr_size; _arr_i++) {
            _arr_item = Integer.parseInt(in.nextLine());
            _arr[_arr_i] = _arr_item;
        }

        res = sumOfIntegers(_arr);
        bw.write(String.valueOf(res));
        bw.newLine();

        bw.close();
    }
}
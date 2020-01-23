package elementsofjava.chapter5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultiplyIntegerArrays {

    /*
    3920
     123

    11760
    78400
   392000


     123
    3920

     000
    2460
  110700
  369000

  482160

    12
     3


     */
    public static List<Integer> multiply(List<Integer> num, List<Integer> other) {

        List<Integer> result = new ArrayList<>(Collections.nCopies(num.size() + other.size(), 0));
        for (int n = num.size() - 1; n >= 0; n--) {
//            System.out.println(num.get(n));
            for (int o =  other.size() - 1; o >= 0; o--) {
//                System.out.println(other.get(o));
                int resultIndex = o + n + 1;

                int first = num.get(n);
                int second = other.get(o);
                int product = first * second;

                int x = result.get(resultIndex) + product;
                System.out.println(first + " * " + second + " = " + product);
                result.set(o + n + 1, product);

            }
        }

        return new ArrayList<>();
    }


    public static void main(String[] args) {
        multiply(List.of(3, 9, 2, 0), List.of(1, 2, 3));
    }
}

package lambdas;


import java.io.*;
import java.net.URL;
import java.util.StringTokenizer;


/**
 * Created by andrew on 11/13/16.
 */
public class Lambda
{
    interface PerformOperation
    {
        boolean check(int a);
    }


    static class MyMath
    {


        static boolean checker ( PerformOperation p, int num )
        {
            return p.check( num );
        }

        static PerformOperation is_odd()
        {
            return (int a) -> a % 2 == 1;
        }


        static PerformOperation is_even()
        {
            return (int a) -> a % 2 == 0;
        }

        static PerformOperation is_prime()
        {
            return (int num) -> {
                if (num < 2) return false;
                if (num == 2) return true;
                if (num % 2 == 0) return false;
                for (int i = 3; i * i <= num; i += 2)
                    if (num % i == 0) return false;
                return true;
            };
        }

        static PerformOperation is_palindrome()
        {
            return (int a) -> {
                String s = String.valueOf( a );
                int length = s.length( ) - 1;

                for (int i = 0; i <= length / 2 ; i++)
                {
                    if (s.charAt( i ) != s.charAt( length - i ))
                    {
                        return false;
                    }
                }

                return true;
            };
        }
    }

    public static void main(String[] args) throws
                                           IOException
    {
        MyMath ob = new MyMath();
        URL resource = Lambda.class.getResource( "input.txt" );

        File fis = new File( resource.getFile( ) );

        BufferedReader br = new BufferedReader( new FileReader(fis));
        int T = Integer.parseInt(br.readLine());
        PerformOperation op;
        boolean ret = false;
        String ans = null;
        while (T--> 0) {
            String s = br.readLine().trim();
            StringTokenizer st = new StringTokenizer( s);
            int ch = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if (ch == 1) {
                op = ob.is_odd();
                ret = ob.checker(op, num);
                ans = (ret) ? "ODD" : "EVEN";
            } else if (ch == 2) {
                op = ob.is_prime();
                ret = ob.checker(op, num);
                ans = (ret) ? "PRIME" : "COMPOSITE";
            } else if (ch == 3) {
                op = ob.is_palindrome();
                ret = ob.checker(op, num);
                ans = (ret) ? "PALINDROME" : "NOT PALINDROME";

            }
            System.out.println(ans);
        }
    }


}

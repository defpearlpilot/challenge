package easy.servicelane;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;


public class ServiceLane
{

    private static FileInputStream readFromFile()
    {
        try
        {
            URL resource = ServiceLane.class.getResource( "testcase1" );
            File file = new File( resource.getFile( ) );
            return new FileInputStream( file );

        }
        catch ( IOException e )
        {
            return null;
        }
    }


    private static int computeMax(int enter, int exit, int[] width)
    {
        int min = 4;

        System.out.format("enter: %d  exit: %d\n", enter, exit);

        for (int index = enter; index <= exit; index++)
        {
            int item = width[ index ];
            min = Math.min( min, item);

            System.out.format("index %d  item: %d, min: %d\n", index, item, min);
        }

        return min;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(readFromFile());
        int n = in.nextInt();
        int t = in.nextInt();
        int width[] = new int[n];
        for(int width_i=0; width_i < n; width_i++){
            width[width_i] = in.nextInt();
        }
        for(int a0 = 0; a0 < t; a0++){
            int i = in.nextInt();
            int j = in.nextInt();

//            System.out.println( i + " " + j );
            System.out.println(computeMax( i, j, width ) );
        }
    }


}

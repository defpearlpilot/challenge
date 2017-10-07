package careercup.gaps;


public class ReverseBinaryTree
{

    public static void main(String[] args)
    {
        Integer[] tree = {3, 9, 20, null, null, 15, 7};

        Double depth = Math.ceil( Math.sqrt( tree.length ) ) - 1.0;


        while (depth >= 0.0)
        {
            int length = ((Double) Math.pow(2.0, depth)).intValue();
            int start = length - 1;
            int end = start + length;

            while (start < end)
            {
                Integer value = tree[start];
                if (value != null)
                {
                    System.out.print( tree[start] + " ");
                }
                start++;
            }

            depth -= 1.0;
            System.out.println();
        }
    }
}

package elementsofjava.chapter5;

public class DutchFlagPartition {

    static void swap(int[] array, int s, int o) {
        int temp = array[s];
        array[s] = array[o];
        array[o] = temp;
    }

    static int[] partitionN(int[] array, int index) {
        int[] p = new int[array.length];

        int toCompare = array[index];

        for (int i = 0; i <= index; i++) {
            int item = array[i];
            if (item <= toCompare) {
                p[i] = item;
            } else {
                swap(array, i, index);
                p[i] = array[i];
            }
        }

        for (int i = index; i < array.length; i++) {
            p[i] = array[i];
        }

        return p;
    }


    public static void main(String[] args) {
        partitionN(new int[]{0,1,2,0,2,1}, 3);
        //{0,0,1,2,2,1,1}
    }
}

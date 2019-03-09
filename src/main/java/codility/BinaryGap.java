package codility;

public class BinaryGap {
    private static void print(Object output) {
        System.out.println(output);
    }

    private static boolean isZeroB(char c) {
        return c == '0';
    }

    private static boolean isOneB(char c) {
        return c == '1';
    }

    private static int computeGap(String binary) {
        print(binary);

        boolean inGap = false;
        int maxGap = 0;
        int currentCount = 0;

        for (int index = 0; index < binary.length(); index++) {
            char c = binary.charAt(index);

            if (isZeroB(c)) {
                if (inGap) {
                    currentCount++;
                }
            }
            else if (isOneB(c)) {
                if (inGap) {
                    maxGap = Math.max(currentCount, maxGap);
                    currentCount = 0;
                } else {
                    inGap = true;
                }
            } else {
                // encountered non-binary character
                return 0;
            }
        }

        return maxGap;
    }

    public static void main(String[] args) {
//        print(computeGap(Integer.toBinaryString(9)));
//        print(computeGap(Integer.toBinaryString(20)));
//        print(computeGap(Integer.toBinaryString(32)));
        print(computeGap(Integer.toBinaryString(1041)));


    }
}

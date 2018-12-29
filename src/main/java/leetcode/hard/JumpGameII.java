package leetcode.hard;

public class JumpGameII {

    private int memoJumps(int position, int[] nums, int[] memo) {
        if (position >= nums.length - 1) {
            return 0;
        }

        if (memo[position] != 0) {
            return memo[position];
        }

        int min = memoJumps(position + 1, nums, memo);

        // check that we can actually move forward
        if (nums[position] != 0) {
            int jumpCount = memoJumps(position + nums[position], nums, memo);
            min = Math.min(min, jumpCount);
        }

        memo[position] = 1 + min;

        return memo[position];
    }

    public int jump(int[] nums) {
        int[] memoized = new int[nums.length];
        memoized[nums.length - 1] = 0;

        return memoJumps(0, nums, memoized);
    }

    public static void main(String[] args) {
        JumpGameII game = new JumpGameII();
//        System.out.println(game.jump(new int[]{2,3,1,1,4}));
//        System.out.println(game.jump(new int[]{2,3,0,1,4}));
        System.out.println(game.jump(new int[]{4,1,1,3,1,1,1}));
    }
}

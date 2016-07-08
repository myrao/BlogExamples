/**
 * Created by raomengyang on 7/6/16.
 */
public class TwoSums {
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int k = 0; k < nums.length; k++) {
            for (int l = k + 1; l < nums.length; l++) {
                if (nums[k] + nums[l] == target) {
                    for (int p : nums) {
                        if (nums[k] == p) {
                            result[0] = k;
                        }
                        if (nums[l] == p) {
                            result[1] = l;
                        }
                    }
                    return result;
                }
            }
        }
        return result;
    }
}

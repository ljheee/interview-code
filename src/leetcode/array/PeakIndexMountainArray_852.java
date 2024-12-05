package leetcode.array;

/**
 * 找到峰值
 * https://leetcode.com/problems/peak-index-in-a-mountain-array/
 */
public class PeakIndexMountainArray_852 {


    public int peakIndexInMountainArray(int[] A) {
        int peekIdx = 0;
        for (int i = 1; i <= A.length - 1; i++) {
            if (A[i] >= A[peekIdx]) {
                peekIdx = i;
                continue;
            } else if (peekIdx > 0) {
                return peekIdx;
            }
        }
        return 0;
    }

    /**
     * https://leetcode.com/problems/find-peak-element/submissions/
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int peekIdx = 0;
        for (int i = 1; i <= nums.length - 1; i++) {
            if (nums[i] >= nums[peekIdx]) {
                peekIdx = i;
                continue;
            } else if (peekIdx > 0) {
                return peekIdx;
            }
        }
        return peekIdx;
    }


    interface MountainArray {
        public int get(int index);

        public int length();
    }

    /**
     *
     * "--------------------------------------
     *          7
     *        5    6
     *     3         4
     *  2               1
     * 1                    0
     * --------------------------------------"
     *
     *
     * https://leetcode-cn.com/problems/find-in-mountain-array/
     *
     * 先二分找峰值，再到两边找target
     *
     * @param target
     * @param mountainArr
     * @return
     */
    public int findInMountainArray(int target, MountainArray mountainArr) {

        int length = mountainArr.length();
        int peekIndex = findPeek( mountainArr, 0, length - 1);
        if(target == mountainArr.get(peekIndex)){
            return peekIndex;
        }
        int ans = findTarget(target, mountainArr, 0, peekIndex-1);

        if(ans!= -1){
            return ans;
        }
         ans = findTargetRight(target, mountainArr, peekIndex+1, length-1);

        return ans;
    }

    private int findTargetRight(int target, MountainArray mountainArr, int from, int to) {
        int mid = (from + to) / 2;
        int midValue = mountainArr.get(mid);
        if (midValue == target) {
            return mid;
        }
        if (mountainArr.get(to) == target) {
            return to;
        }
        if (from == to && midValue != target) {
            return -1;
        }
        if (mid == from && mountainArr.get(to) != target) {
            return -1;
        }

        if(target>mid){
            return findTarget(target,mountainArr,from,mid-1);
        }else {
            return findTarget(target,mountainArr,mid+1,to);
        }
    }

    private int findPeek(MountainArray mountainArr, int from, int to) {

        if (from == to) {
            return from;
        }
        int mid = (from + to) / 2;
        int midValue = mountainArr.get(mid);

        if (mid == from) {
            if(mountainArr.get(to)> midValue){
                return to;
            }else {
                return from;
            }
        }

        int mid1Value = mountainArr.get(mid-1);
        int mid2Value = mountainArr.get(mid+1);

        int ans = -1;
        if(mid1Value<midValue&& midValue<mid2Value){
            // 递增
            ans = findPeek(mountainArr,mid+1,to);
        }else  if(mid1Value>midValue&& midValue>mid2Value){
            // 递减
            ans = findPeek(mountainArr,from,mid-1);
        }else {
            ans = mid;
        }
        return ans;
    }

    private int findTarget(int target, MountainArray mountainArr, int from, int to) {
//        int mid = (from + to) / 2;
//        int midValue = mountainArr.get(mid);
//        if (midValue == target) {
//            return mid;
//        }
//        if (mountainArr.get(to) == target) {
//            return to;
//        }
//        if (from == to && midValue != target) {
//            return -1;
//        }
//        if (mid == from && mountainArr.get(to) != target) {
//            return -1;
//        }
//
//        if(target>mid){
//            return findTarget(target,mountainArr,mid+1,to);
//        }else {
//            return findTarget(target,mountainArr,from,mid-1);
//        }

        while (from<to){
            int mid = (from + to) / 2;
            int midValue = mountainArr.get(mid);
            if (midValue == target) {
                return mid;
            }
            if(midValue < target){
                from = from+1;
            }else {
                to=mid-1;
            }
        }
        return -1;

    }


}

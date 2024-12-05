package some;

import java.util.Arrays;

/**
 * Created by lijianhua04 on 2020/4/23.
 */
public class KthMin {

    public static int my(int[] arr, int left, int right, int k) {

        if (right - left + 1 <= 5) {
            Arrays.sort(arr, left, right + 1);
            return arr[left + k - 1];
        }


        int n = (right - left + 1) / 5;
        int[] media = new int[n];
        for (int i = 0; i < n; i++) {
            int begin = left + i * 5;
            int end = begin + 5;
            Arrays.sort(arr, begin, end);//不包含end
            media[i] = arr[begin + 2];
        }
        Arrays.sort(media);
        int povit = media[n / 2];

        System.out.println(Arrays.toString(media));
        partition(arr, left, right, povit);

        System.out.println(Arrays.toString(arr));


        return 0;
    }

    private static void partition(int[] data, int left, int right, int povit) {

        int ll = left;  //记录当前左边 位置
        int rr = right; //记录当前右边 位置
        int base = povit;  //当前base

        while (ll < rr) {

            // 从右边开始找 第一个小于base的数
            while (ll < rr && data[rr] >= base) {       // 大于就跳过
                rr--;
            }
            if (ll < rr) {  //找到了，交换
                int temp = data[rr];
                data[rr] = data[ll];
                data[ll] = temp;
                ll++;
            }

            // 从左边找 第一个大于base的数
            while (ll < rr && data[ll] < base) {       // 小于就跳过
                ll++;
            }
            if (ll < rr) {  //找到了
                int temp = data[rr];
                data[rr] = data[ll];
                data[ll] = temp;
                rr--;
            }
        }
    }




    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
        System.out.println(my(arr, 0, arr.length - 1, 3));
    }
}

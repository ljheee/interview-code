package string;

/**
 */
public class FindAnagram {


    /**
     * 找出a 中包含 b的同分异构词
     * 返回第一次出现的下标
     *
     * @param a
     * @param b
     * @return
     */
    public int findAnagram(String a, String b) {

        int n = a.length();
        int m = b.length();
        if (n < m) return -1;


        // 建立 b 的负债表
        int[] count = new int[256];
        for (int i = 0; i < m; i++) {
            count[b.charAt(i)]++;
        }

        int inValid = 0;

        // 第一次形成窗口
        for (int i = 0; i < m; i++) {
            if (count[a.charAt(i)]-- == 0) {
                inValid++;
            }
        }

        if (inValid == 0) return 0;

        for (int i = 1; i < n - m + 1; i++) {
            if (count[a.charAt(m + i - 1)]-- == 0) {
                inValid++;
            }
            if (count[a.charAt(i - 1)]++ == -1) {
                inValid--;
            }
            if (inValid == 0) {
                return i;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        System.out.println(new FindAnagram().findAnagram("abccapdd", "accab"));
        System.out.println(new FindAnagram().findAnagram("mabccadd", "accab"));
        System.out.println(new FindAnagram().findAnagram("aqaccabdd", "accab"));
        System.out.println(new FindAnagram().findAnagram("aqaaccbdd", "accab"));
    }

}

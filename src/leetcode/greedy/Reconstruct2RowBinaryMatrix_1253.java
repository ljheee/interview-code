package leetcode.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 重构出 二进制矩阵
 * https://leetcode.com/problems/reconstruct-a-2-row-binary-matrix/submissions/
 * Created by lijianhua04 on 2020/2/12.
 */
public class Reconstruct2RowBinaryMatrix_1253 {


    public static void main(String[] args) {
        new Reconstruct2RowBinaryMatrix_1253().reconstructMatrix(5, 5, new int[]{2, 1, 2, 0, 1, 0, 1, 2, 0, 1});
    }

    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {

        int twoCount = 0;
        int zeroCount = 0;
        int oneCount = 0;
        for (int i : colsum) {
            if (i == 2) {
                twoCount++;
            }
            if (i == 0) {
                zeroCount++;
            }
            if (i == 1) {
                oneCount++;
            }
        }

        if (upper + lower != 2 * twoCount + oneCount) {
            return Collections.emptyList();
        }

        List<List<Integer>> listList = new ArrayList<>();

        for (int i = 0; i < colsum.length; i++) {
            listList.add(new ArrayList<>());
            if (colsum[i] == 2) {
                listList.get(i).add(1);
                listList.get(i).add(1);
                continue;
            }
            if (colsum[i] == 0) {
                listList.get(i).add(0);
                listList.get(i).add(0);
                continue;
            }
            if (colsum[i] == 1 && upper > twoCount) {
                listList.get(i).add(1);
                listList.get(i).add(0);
                twoCount++;
            } else {
                listList.get(i).add(0);
                listList.get(i).add(1);
            }
        }

        // 验证第二行 是否符合要求
        Integer reduce = listList.stream().map(e -> e.get(1)).reduce((sum, a) -> sum += a).get();
        if (reduce != lower) {
            return Collections.emptyList();
        }

        // 转化结果
        List<Integer> row = listList.stream().map(e -> e.get(0)).collect(Collectors.toList());
        List<Integer> col = listList.stream().map(e -> e.get(1)).collect(Collectors.toList());
        listList.clear();
        listList.add(row);
        listList.add(col);

        return listList;
    }
}

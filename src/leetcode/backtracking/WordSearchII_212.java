package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijianhua04 on 2020/1/5.
 */
public class WordSearchII_212 {

    public List<String> findWords(char[][] board, String[] words) {

        List<String> list = new ArrayList<>();
        for (String word : words) {
            if (exist(board, word)) {
                step = 1;//每次 搜索一个新词，重置
                list.add(word);
            }
        }
        return list;
    }


    class Point {
        int x;
        int y;
    }

    // 记录已经搜索到word的哪一位上了，（第0位是 exist()方法双重for找到的）
    int step = 1;
    boolean mark[][]; //标记是否走过

    public boolean exist(char[][] board, String word) {

        int n = board.length;
        int m = board[0].length;

        char[] chars = word.toCharArray();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // board[i][j]作为起点，进行dfs
                if (board[i][j] == chars[0]) {
                    // board[i][j]作为起点，是次新探索，置空mark
                    mark = new boolean[n][m];
                    boolean exist = search(i, j, board, chars);
                    if (exist) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean search(int x, int y, char[][] board, char[] chars) {

        if (chars.length == 1) {
            return true;
        }
        int n = board.length;
        int m = board[0].length;


        mark[x][y] = true;
        int next[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};//四个方向


//        if (board[x][y] == chars[step] && step == chars.length - 1) {//是否已到达终点(递归的终止条件)
//            return true;
//        }

        boolean dfs = false;

        //遍历四个方向
        for (int i = 0; i < 4; i++) {
            int nextX = x + next[i][0];
            int nextY = y + next[i][1];
            if (nextX < 0 || nextX > n - 1 || nextY < 0 || nextY > m - 1) {//(下一步)是否越界
                continue;
            }

            if (board[nextX][nextY] == chars[step] && !mark[nextX][nextY]) {//(下一步)非障碍物，且还没走过
                mark[nextX][nextY] = true;
                if (step == chars.length - 1) {//是否已到达终点(递归的终止条件)
                    return true;
                }
                step++;
                dfs = search(nextX, nextY, board, chars);//递归
                if (dfs) {
                    return true;
                }
                mark[nextX][nextY] = false;//回溯
                step--;
            }
        }

        return false;
    }

}

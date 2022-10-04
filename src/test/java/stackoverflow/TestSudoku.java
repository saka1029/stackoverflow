package stackoverflow;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class TestSudoku {

    static int[][] deepCopy(int[][] matrix) {
        int n = matrix.length;
        int[][] r = new int[n][];
        for (int i = 0; i < n; ++i)
            r[i] = Arrays.copyOf(matrix[i], matrix[i].length);
        return r;
    }

    static class Sudoku {
        static final int SIZE = 9, BOX = 3;
        final int[][] grid;

        public Sudoku(int[][] grid) {
            this.grid = deepCopy(grid);
        }

        boolean possible(int x, int y, int n) {
            for (int i = 0; i < SIZE; ++i)
                if (grid[y][i] == n)
                    return false;
            for (int i = 0; i < SIZE; ++i)
                if (grid[i][x] == n)
                    return false;
            int x0 = x / BOX * BOX, y0 = y / BOX * BOX;
            for (int i = 0; i < BOX; ++i)
                for (int j = 0; j < BOX; ++j)
                    if (grid[y0 + i][x0 + j] == n)
                        return false;
            return true;
        }

        void solve() {
            for (int y = 0; y < SIZE; ++y)
                for (int x = 0; x < SIZE; ++x)
                    if (grid[y][x] == 0) {
                        for (int n = 1; n <= SIZE; ++n)
                            if (possible(x, y, n)) {
                                grid[y][x] = n;
                                solve();
                                grid[y][x] = 0;
                            }
                        return;
                    }
            System.out.println(toString());
        }

        static final String NL = System.lineSeparator();

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int[] row : grid)
                sb.append(Arrays.toString(row)).append(NL);
            return sb.toString();
        }
    }

    static final int[][] grid = {{5, 3, 0, 0, 7, 0, 0, 0, 0},
        {6, 0, 0, 1, 9, 5, 0, 0, 0},
        {0, 9, 8, 0, 0, 0, 0, 6, 0},
        {8, 0, 0, 0, 6, 0, 0, 0, 3},
        {4, 0, 0, 8, 0, 3, 0, 0, 1},
        {7, 0, 0, 0, 2, 0, 0, 0, 6},
        {0, 6, 0, 0, 0, 0, 2, 8, 0},
        {0, 0, 0, 4, 1, 9, 0, 0, 5},
        {0, 0, 0, 0, 8, 0, 0, 7, 9}};

    @Test
    public void testPossible() {
        Sudoku sudoku = new Sudoku(grid);
        assertFalse(sudoku.possible(4, 4, 3));
        assertTrue(sudoku.possible(4, 4, 5));
    }

    @Test
    public void testToString() {
        Sudoku sudoku = new Sudoku(grid);
        System.out.println(sudoku);
    }

    @Test
    public void testSolve() {
        Sudoku sudoku = new Sudoku(grid);
        sudoku.solve();
    }

    @Test
    public void testSolve2Solutions() {
        int[][] grid1 = deepCopy(grid);
//        grid1[8][8] = 0;
        grid1[8][7] = 0;
        Sudoku sudoku = new Sudoku(grid1);
        sudoku.solve();
    }

}

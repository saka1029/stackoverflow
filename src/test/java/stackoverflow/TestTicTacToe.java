package stackoverflow;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTicTacToe {
	
	static class TicTacToe {
		static final int X = 1, O = 2;
		static final int[][] ROWS = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6},
		};
		final int n, x, o, winner;
		final byte[] board = new byte[9];
		
		TicTacToe(int n) {
			this.n = n;
			int xx = 0, oo = 0;
			for (int i = 0; n > 0 && i < 9; ++i, n /= 3) {
				byte j= (byte) (n % 3);
				board[i] = j;
				switch (j) {
				case X: ++xx; break;
				case O: ++oo; break;
				}
			}
			this.x = xx;
			this.o = oo;
			int winner = 0;
			for (int[] row : ROWS) {
			    int a = board[row[0]], b = board[row[1]], c = board[row[2]];
			    if (a != 0 && a == b && a == c)
			        if (winner == 0)
			            winner = a;
			        else {
			            winner = 0;
			            break;
			        }
			}
			this.winner = winner;
		}
		
		static final String[] DISPLAY = {".", "X", "O"}; 
		static final String NL = "%n".formatted();
		
		int next() {
			return switch (x - o) {
			case 1 -> O;
			case -1 -> X;
			case 0 -> 0; // both
			default -> -1;
			};
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("n=").append(n)
				.append(" X=").append(x)
				.append(" O=").append(o)
				.append(" win=").append(winner)
				.append(NL);
			for (int r = 0; r < 9; r += 3) {
				for (int c = 0; c < 3; ++c)
					sb.append(DISPLAY[board[r + c]]);
				sb.append(NL);
			}
			return sb.toString();
		}
			
	}

//	static final int ALL = 3 * 3 * 3 *3 * 3 * 3 * 3 * 3 * 3;
	static final int ALL = 100;

	@Test
	public void test() {
		TicTacToe[] all = new TicTacToe[ALL];
		int av = 0;
		for (int i = 0; i < ALL; ++i) {
			TicTacToe e = new TicTacToe(i);
			System.out.println(e);
		}
	}
}


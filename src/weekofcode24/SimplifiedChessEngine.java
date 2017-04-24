package weekofcode24;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class SimplifiedChessEngine {

    private static InputReader in;
    private static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        if (Arrays.asList(args).contains("-local")) {
            try {
                in = new InputReader(new FileInputStream("in.txt"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            in = new InputReader(System.in);
        }

        new Problem().solve();

        out.close();
    }

    private static class Problem {

        private boolean pieceEmpty(char p) {
            return p == '\0';
        }

        private boolean pieceWhite(char p) {
            return 'a' <= p && p <= 'z';
        }

        private boolean pieceBlack(char p) {
            return 'A' <= p && p <= 'Z';
        }

        private ArrayList<int[]> pieceStraightMoves(char[][] a, int r, int c) {
            ArrayList<int[]> list = new ArrayList<>();
            boolean f = pieceWhite(a[r][c]);

            for (int i = -1; i >= -3; i--) {
                int ri = r + i;

                if (ri < 0 || 3 < ri) {
                    break;
                }

                char q = a[ri][c];

                if (pieceEmpty(q)) {
                    list.add(new int[]{ri, c});
                } else {
                    if (pieceWhite(q) != f) {
                        list.add(new int[]{ri, c});
                    }

                    break;
                }
            }

            for (int i = 1; i <= 3; i++) {
                int ri = r + i;

                if (ri < 0 || 3 < ri) {
                    break;
                }

                char q = a[ri][c];

                if (pieceEmpty(q)) {
                    list.add(new int[]{ri, c});
                } else {
                    if (pieceWhite(q) != f) {
                        list.add(new int[]{ri, c});
                    }

                    break;
                }
            }

            for (int i = -1; i >= -3; i--) {
                int ci = c + i;

                if (ci < 0 || 3 < ci) {
                    break;
                }

                char q = a[r][ci];

                if (pieceEmpty(q)) {
                    list.add(new int[]{r, ci});
                } else {
                    if (pieceWhite(q) != f) {
                        list.add(new int[]{r, ci});
                    }

                    break;
                }
            }

            for (int i = 1; i <= 3; i++) {
                int ci = c + i;

                if (ci < 0 || 3 < ci) {
                    break;
                }

                char q = a[r][ci];

                if (pieceEmpty(q)) {
                    list.add(new int[]{r, ci});
                } else {
                    if (pieceWhite(q) != f) {
                        list.add(new int[]{r, ci});
                    }

                    break;
                }
            }

            return list;
        }

        private ArrayList<int[]> pieceDiagonalMoves(char[][] a, int r, int c) {
            ArrayList<int[]> list = new ArrayList<>();
            boolean f = pieceWhite(a[r][c]);

            for (int i = -1; i >= -3; i--) {
                int ri = r + i,
                        ci = c + i;

                if (ri < 0 || 3 < ri
                        || ci < 0 || 3 < ci) {
                    break;
                }

                char q = a[ri][ci];

                if (pieceEmpty(q)) {
                    list.add(new int[]{ri, ci});
                } else {
                    if (pieceWhite(q) != f) {
                        list.add(new int[]{ri, ci});
                    }

                    break;
                }
            }

            for (int i = 1; i <= 3; i++) {
                int ri = r + i,
                        ci = c + i;

                if (ri < 0 || 3 < ri
                        || ci < 0 || 3 < ci) {
                    break;
                }

                char q = a[ri][ci];

                if (pieceEmpty(q)) {
                    list.add(new int[]{ri, ci});
                } else {
                    if (pieceWhite(q) != f) {
                        list.add(new int[]{ri, ci});
                    }

                    break;
                }
            }

            for (int i = 1; i <= 3; i++) {
                int ri = r + -i,
                        ci = c + i;

                if (ri < 0 || 3 < ri
                        || ci < 0 || 3 < ci) {
                    break;
                }

                char q = a[ri][ci];

                if (pieceEmpty(q)) {
                    list.add(new int[]{ri, ci});
                } else {
                    if (pieceWhite(q) != f) {
                        list.add(new int[]{ri, ci});
                    }

                    break;
                }
            }

            for (int i = 1; i <= 3; i++) {
                int ri = r + i,
                        ci = c + -i;

                if (ri < 0 || 3 < ri
                        || ci < 0 || 3 < ci) {
                    break;
                }

                char q = a[ri][ci];

                if (pieceEmpty(q)) {
                    list.add(new int[]{ri, ci});
                } else {
                    if (pieceWhite(q) != f) {
                        list.add(new int[]{ri, ci});
                    }

                    break;
                }
            }

            return list;
        }

        private ArrayList<int[]> pieceMoves(char[][] a, int r, int c) {
            ArrayList<int[]> list = new ArrayList<>();
            char p = a[r][c];

            switch (Character.toLowerCase(p)) {
                case 'q':
                    list.addAll(pieceStraightMoves(a, r, c));
                    list.addAll(pieceDiagonalMoves(a, r, c));
                    break;

                case 'n':
                    boolean f = pieceWhite(a[r][c]);

                    for (int[] o : new int[][]{
                            {-2, -1}, {-2, 1},
                            {2, -1}, {2, 1},
                            {-1, -2}, {1, -2},
                            {-1, 2}, {1, 2}
                    }) {
                        int ri = r + o[0],
                                ci = c + o[1];

                        if (ri < 0 || 3 < ri
                                || ci < 0 || 3 < ci) {
                            continue;
                        }

                        char q = a[ri][ci];

                        if (pieceEmpty(q) || pieceWhite(q) != f) {
                            list.add(new int[]{ri, ci});
                        }
                    }

                    break;

                case 'b':
                    list = pieceDiagonalMoves(a, r, c);
                    break;

                case 'r':
                    list = pieceStraightMoves(a, r, c);
                    break;
            }

            return list;
        }

        private int minimax(char[][] a, int m, int mi) {
            if (mi > m) {
                return 0;
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    char p = a[i][j];

                    if (!pieceEmpty(p)) {
                        if (mi % 2 == 1) {
                            if (pieceWhite(p)) {
                                for (int[] o : pieceMoves(a, i, j)) {
                                    int r = o[0],
                                            c = o[1];
                                    char q = a[r][c];

                                    if (q == 'Q') {
                                        return 1;
                                    }

                                    a[i][j] = '\0';
                                    a[r][c] = p;

                                    int s = minimax(a, m, mi + 1);

                                    a[i][j] = p;
                                    a[r][c] = q;

                                    if (s == 1) {
                                        return 1;
                                    }
                                }
                            }
                        } else {
                            if (pieceBlack(p)) {
                                for (int[] o : pieceMoves(a, i, j)) {
                                    int r = o[0],
                                            c = o[1];
                                    char q = a[r][c];

                                    if (q == 'q') {
                                        return -1;
                                    }

                                    a[i][j] = '\0';
                                    a[r][c] = p;

                                    int s = minimax(a, m, mi + 1);

                                    a[i][j] = p;
                                    a[r][c] = q;

                                    if (s <= 0) {
                                        return -1;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return mi % 2 == 1 ? -1 : 1;
        }

        public void solve() {
            int g = in.nextInt();

            for (int i = 0; i < g; i++) {
                int w = in.nextInt(),
                        b = in.nextInt(),
                        m = in.nextInt();

                char[] wt = new char[w],
                        wc = new char[w];
                int[] wr = new int[w];

                for (int j = 0; j < w; j++) {
                    wt[j] = in.nextChar();
                    wc[j] = in.nextChar();
                    wr[j] = in.nextInt();
                }

                char[] bt = new char[b],
                        bc = new char[b];
                int[] br = new int[b];

                for (int j = 0; j < b; j++) {
                    bt[j] = in.nextChar();
                    bc[j] = in.nextChar();
                    br[j] = in.nextInt();
                }

                char[][] a = new char[4][4];

                for (int j = 0; j < w; j++) {
                    a[4 - wr[j]][wc[j] - 'A'] = (char) (wt[j] - 'A' + 'a');
                }

                for (int j = 0; j < b; j++) {
                    a[4 - br[j]][bc[j] - 'A'] = bt[j];
                }

                out.println(minimax(a, m, 1) == 1 ? "YES" : "NO");
            }
        }

    }

    @SuppressWarnings("UnusedDeclaration")
    private static class InputReader {

        private BufferedReader bufferedReader;
        private StringTokenizer stringTokenizer;

        public InputReader(InputStream inputStream) {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        }

        public String next() {
            while (stringTokenizer == null || !stringTokenizer.hasMoreTokens()) {
                try {
                    stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return stringTokenizer.nextToken();
        }

        public char nextChar() {
            return next().charAt(0);
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public float nextFloat() {
            return Float.parseFloat(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public String nextLine() {
            if (stringTokenizer != null && stringTokenizer.hasMoreTokens()) {
                return stringTokenizer.nextToken("");
            }

            try {
                return bufferedReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}

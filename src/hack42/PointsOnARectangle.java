package hack42;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class PointsOnARectangle {

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

        public void solve() {
            int q = in.nextInt();

            for (int i = 0; i < q; i++) {
                int n = in.nextInt();

                int[] x = new int[n],
                        y = new int[n];

                for (int j = 0; j < n; j++) {
                    x[j] = in.nextInt();
                    y[j] = in.nextInt();
                }

                int x1 = Integer.MAX_VALUE,
                        x2 = Integer.MIN_VALUE,
                        y1 = Integer.MAX_VALUE,
                        y2 = Integer.MIN_VALUE;

                for (int j = 0; j < n; j++) {
                    if (x[j] < x1) {
                        x1 = x[j];
                    }

                    if (x[j] > x2) {
                        x2 = x[j];
                    }

                    if (y[j] < y1) {
                        y1 = y[j];
                    }

                    if (y[j] > y2) {
                        y2 = y[j];
                    }
                }

                boolean f = true;

                for (int j = 0; j < n; j++) {
                    if (((x[j] == x1 || x[j] == x2) && y1 <= y[j] && y[j] <= y2)
                            || ((y[j] == y1 || y[j] == y2) && x1 <= x[j] && x[j] <= x2)) {
                        continue;
                    }

                    f = false;
                    break;
                }

                out.println(f ? "YES" : "NO");
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

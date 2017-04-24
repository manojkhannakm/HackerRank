package weekofcode23;

import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class Lighthouse {

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

        private boolean circle(char[][] a, int n, int x, int y, int r) {
            if (x - r < 0 || x + r > n - 1
                    || y - r < 0 || y + r > n - 1) {
                return false;
            }

            Point originPoint = new Point(x, y);

            for (int i = x - r; i <= x + r; i++) {
                for (int j = y - r; j <= y + r; j++) {
                    if (new Point(i, j).distance(originPoint) <= r && a[i][j] != '.') {
                        return false;
                    }
                }
            }

            return true;
        }

        public void solve() {
            int n = in.nextInt();

            char[][] a = new char[n][];

            for (int i = 0; i < n; i++) {
                String ai = in.nextLine();

                a[i] = ai.toCharArray();
            }

            int mr = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    while (circle(a, n, i, j, mr + 1)) {
                        mr++;
                    }
                }
            }

            out.println(mr);
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

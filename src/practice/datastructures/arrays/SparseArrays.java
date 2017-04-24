package practice.datastructures.arrays;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class SparseArrays {

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

        private int find(String[] s, int n, String x) {
            for (int i = 0; i < n; i++) {
                if (s[i].equals(x)) {
                    return i;
                }
            }

            return -1;
        }

        public void solve() {
            int n = in.nextInt();

            String[] s = new String[n];
            int[] c = new int[n];
            int x = 0;

            for (int i = 0; i < n; i++) {
                String si = in.nextLine();

                int j = find(s, x, si);

                if (j == -1) {
                    s[x] = si;
                    c[x] = 1;
                    x++;
                } else {
                    c[j]++;
                }
            }

            int q = in.nextInt();

            for (int i = 0; i < q; i++) {
                String t = in.nextLine();

                int j = find(s, x, t);

                if (j == -1) {
                    out.println(0);
                } else {
                    out.println(c[j]);
                }
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

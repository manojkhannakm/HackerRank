package worldcodesprint7;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class SummingPieces {

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

        private static final int MOD = 1000000007;

        private long modPow(long n, long p, long m) {
            long x = 1;

            while (p > 0) {
                if (p % 2 != 0) {
                    x = x * n % m;
                }

                n = n * n % m;
                p /= 2;
            }

            return x;
        }

        public void solve() {
            int n = in.nextInt();

            int[] a = new int[n];

            for (int i = 0; i < n; i++) {
                int ai = in.nextInt();

                a[i] = ai;
            }

            long[] c = new long[n];
            c[0] = c[n - 1] = modPow(2, n, MOD) - 1;

            for (int i = 1; i <= (n - 1) / 2; i++) {
                c[i] = c[n - i - 1] = c[i - 1] + modPow(2, n - (i + 1), MOD) - modPow(2, i - 1, MOD);
            }

            for (int i = 0; i < n; i++) {
                c[i] = c[i] % MOD;
            }

            long s = 0;

            for (int i = 0; i < n; i++) {
                s = (s + c[i] * a[i]) % MOD;
            }

            out.println(s);
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

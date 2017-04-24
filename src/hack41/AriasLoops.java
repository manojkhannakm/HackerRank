package hack41;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class AriasLoops {

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

        private long modInverse(long n, long m) {
            return modPow(n, m - 2, m);
        }

        private long modBinomialCoefficient(long n, long k, long m) {
            long a = 1,
                    b = 1;

            for (long i = 0; i < k; i++) {
                a = a * (n - i) % m;
                b = b * (i + 1) % m;
            }

            return modInverse(b, m) * a % m;
        }

        public void solve() {
            long n = in.nextInt(),
                    k = in.nextInt();

            long d = n - ((k - 1) * (k - 2) / 2);

            out.println(d >= k ? modBinomialCoefficient(d, k, MOD) : 0);
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

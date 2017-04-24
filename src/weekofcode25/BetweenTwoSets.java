package weekofcode25;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class BetweenTwoSets {

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

        private int gcd(int a, int b) {
            while (b > 0) {
                int t = b;
                b = a % b;
                a = t;
            }

            return a;
        }

        public void solve() {
            int n = in.nextInt(),
                    m = in.nextInt();

            int[] a = new int[n];

            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }

            int[] b = new int[m];

            for (int i = 0; i < m; i++) {
                b[i] = in.nextInt();
            }

            int l = a[0];

            for (int i = 1; i < n; i++) {
                l = l * a[i] / gcd(l, a[i]);

                if (l > 100) {
                    break;
                }
            }

            int o = 0;

            if (l <= 100) {
                int g = b[0];

                for (int i = 1; i < m; i++) {
                    g = gcd(g, b[i]);
                }

                for (int i = 1; i * l <= 100; i++) {
                    if (g % (i * l) == 0) {
                        o++;
                    }
                }
            }

            out.println(o);
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

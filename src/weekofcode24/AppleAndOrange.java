package weekofcode24;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class AppleAndOrange {

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
            int s = in.nextInt(),
                    t = in.nextInt();

            int a = in.nextInt(),
                    b = in.nextInt();

            int m = in.nextInt(),
                    n = in.nextInt();

            int[] x = new int[m];

            for (int i = 0; i < m; i++) {
                x[i] = in.nextInt();
            }

            int[] y = new int[n];

            for (int i = 0; i < n; i++) {
                y[i] = in.nextInt();
            }

            int c = 0;

            for (int i = 0; i < m; i++) {
                int ax = a + x[i];

                if (ax >= s && ax <= t) {
                    c++;
                }
            }

            out.println(c);

            c = 0;

            for (int i = 0; i < n; i++) {
                int by = b + y[i];

                if (by >= s && by <= t) {
                    c++;
                }
            }

            out.println(c);
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

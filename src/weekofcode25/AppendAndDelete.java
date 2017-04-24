package weekofcode25;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class AppendAndDelete {

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
            char[] s = in.nextLine().toCharArray();

            char[] t = in.nextLine().toCharArray();

            int k = in.nextInt();

            int n = s.length,
                    m = t.length,
                    c = 0;

            for (int i = 0; i < n && i < m; i++) {
                if (s[i] != t[i]) {
                    break;
                }

                c++;
            }

            int d = n - c + m - c;

            if (d == k) {
                out.println("Yes");
            } else if (d < k) {
                if ((k - d) % 2 == 0) {
                    out.println("Yes");
                } else if (n + m <= k) {
                    out.println("Yes");
                } else {
                    out.println("No");
                }
            } else if (d > k) {
                out.println("No");
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

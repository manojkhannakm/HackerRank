package weekofcode24;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class HappyLadybugs {

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
            int g = in.nextInt();

            for (int i = 0; i < g; i++) {
                int n = in.nextInt();

                char[] b = in.nextLine().toCharArray();

                int[] c = new int[27];

                for (int j = 0; j < n; j++) {
                    if (b[j] != '_') {
                        c[b[j] - 'A']++;
                    } else {
                        c[26]++;
                    }
                }

                boolean h = true;

                if (c[26] == 0) {
                    boolean[] f = new boolean[26];

                    for (int j = 0, k; j < n; ) {
                        if (f[b[j] - 'A']) {
                            h = false;
                            break;
                        }

                        f[b[j] - 'A'] = true;

                        k = j + 1;

                        while (k < n && b[k] == b[j]) {
                            k++;
                        }

                        if (k - j == 1) {
                            h = false;
                            break;
                        }

                        j = k;
                    }
                } else {
                    for (int j = 0; j < 26; j++) {
                        if (c[j] == 1) {
                            h = false;
                            break;
                        }
                    }
                }

                out.println(h ? "YES" : "NO");
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

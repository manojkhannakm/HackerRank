package hack42;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class JohnsSubwayCommute {

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

            int n = s.length,
                    x;

            if (s[n - 1] == 'E') {
                x = n - 1;
            } else if (s[0] == 'E') {
                x = 0;
            } else {
                int mc = 0,
                        mx = 0;

                for (int i = 0; i < n; i++) {
                    if (s[i] == 'E') {
                        int c = 0;

                        for (int j = i; j < n; j++) {
                            if (s[j] == 'O') {
                                break;
                            }

                            c++;
                        }

                        if (c >= mc) {
                            mc = c;
                            mx = i;

                            if (c > 1) {
                                break;
                            }
                        }

                        i += c;
                    }
                }

                if (mc == 1) {
                    x = mx;
                } else {
                    x = mx + 1;
                }
            }

            out.println(x);
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

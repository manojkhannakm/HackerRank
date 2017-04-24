package worldcodesprint7;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class TwoCharacters {

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
            int n = in.nextInt();

            String s = in.nextLine();

            int mx = 0;

            if (n > 1) {
                for (int i = 0; i < 26; i++) {
                    for (int j = 0; j < 26; j++) {
                        if (i == j) {
                            continue;
                        }

                        boolean f = false;
                        int x = 0;

                        for (int k = 0; k < n; k++) {
                            char c = s.charAt(k);

                            if (!f) {
                                if (c == j + 'a') {
                                    x = -1;
                                    break;
                                }

                                if (c == i + 'a') {
                                    x++;

                                    f = true;
                                }
                            } else {
                                if (c == i + 'a') {
                                    x = -1;
                                    break;
                                }

                                if (c == j + 'a') {
                                    x++;

                                    f = false;
                                }
                            }
                        }

                        if (x > mx) {
                            mx = x;
                        }
                    }
                }
            }

            out.println(mx);
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

package hack41;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class WashingPlates {

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
            int n = in.nextInt(),
                    k = in.nextInt();

            int[] p = new int[n],
                    d = new int[n];

            for (int i = 0; i < n; i++) {
                int pi = in.nextInt(),
                        di = in.nextInt();

                p[i] = pi;
                d[i] = di;
            }

            Plate[] plates = new Plate[n];

            for (int i = 0; i < n; i++) {
                plates[i] = new Plate(p[i], d[i]);
            }

            Arrays.sort(plates, (o1, o2) -> {
                int c = Integer.compare(o2.p + o2.d, o1.p + o1.d);

                if (c == 0) {
                    c = Integer.compare(o2.p, o1.p);
                }

                return c;
            });

            long s = 0;

            for (int i = 0; i < (k <= n ? k : n); i++) {
                s += plates[i].p;
            }

            for (int i = k; i < n; i++) {
                s -= plates[i].d;
            }

            out.println(s > 0 ? s : 0);
        }

        private class Plate {

            private int p, d;

            public Plate(int p, int d) {
                this.p = p;
                this.d = d;
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

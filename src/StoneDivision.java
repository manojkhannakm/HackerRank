import java.io.*;
import java.util.*;

/**
 * @author Manoj Khanna
 */

public class StoneDivision {

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

        private HashMap<Long, Long> map;

        private void mapAdd(long n, long c) {
            Long v = map.get(n);

            if (v == null) {
                v = 0L;
            }

            map.put(n, v + c);
        }

        private void mapRemove(long n, long c) {
            Long v = map.get(n);

            if (v - c == 0) {
                map.remove(n);
            } else {
                map.put(n, v - c);
            }
        }

        private int minimax(long[] s, boolean p) {
            Set<Long> set = new HashSet<>(map.keySet());

            if (!p) {
                for (Long ni : set) {
                    for (long si : s) {
                        if (ni % si == 0) {
                            mapRemove(ni, 1);
                            mapAdd(ni / si, si);

                            int v = minimax(s, true);

                            mapRemove(ni / si, si);
                            mapAdd(ni, 1);

                            if (v == 1) {
                                return 1;
                            }
                        }
                    }
                }

                return -1;
            } else {
                for (Long ni : set) {
                    for (long si : s) {
                        if (ni % si == 0) {
                            mapRemove(ni, 1);
                            mapAdd(ni / si, si);

                            int v = minimax(s, false);

                            mapRemove(ni / si, si);
                            mapAdd(ni, 1);

                            if (v == -1) {
                                return -1;
                            }
                        }
                    }
                }

                return 1;
            }
        }

        public void solve() {
            long n = in.nextLong();
            int m = in.nextInt();

            long[] s = new long[m];

            for (int i = 0; i < m; i++) {
                s[i] = in.nextLong();
            }

            Arrays.sort(s);

            map = new HashMap<>();
            map.put(n, 1L);

            out.println(minimax(s, false) == 1 ? "First" : "Second");
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

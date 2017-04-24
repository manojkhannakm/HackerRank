package worldcodesprint7;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class GridlandMetro {

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
                    m = in.nextInt(),
                    k = in.nextInt();

            int[] r = new int[k],
                    c1 = new int[k],
                    c2 = new int[k];

            for (int i = 0; i < k; i++) {
                int ri = in.nextInt(),
                        c1i = in.nextInt(),
                        c2i = in.nextInt();

                r[i] = ri;
                c1[i] = c1i;
                c2[i] = c2i;
            }

            HashMap<Integer, LinkedList<Integer[]>> map = new HashMap<>();

            for (int i = 0; i < k; i++) {
                LinkedList<Integer[]> list = map.get(r[i]);

                if (list == null) {
                    list = new LinkedList<>();

                    map.put(r[i], list);
                }

                list.add(new Integer[]{c1[i], c2[i]});
            }

            for (LinkedList<Integer[]> list : map.values()) {
                list.sort((o1, o2) -> {
                    int c = Integer.compare(o1[0], o2[0]);

                    if (c == 0) {
                        c = Integer.compare(o2[1], o1[1]);
                    }

                    return c;
                });
            }

            for (LinkedList<Integer[]> list : map.values()) {
                for (int i = 1; i < list.size(); ) {
                    Integer[] t1 = list.get(i - 1),
                            t2 = list.get(i);

                    if (t1[0] <= t2[0] && t1[1] >= t2[1]) {
                        list.remove(i);
                    } else if (t2[0] <= t1[1]) {
                        t1[1] = t2[1];

                        list.remove(i);
                    } else {
                        i++;
                    }
                }
            }

            long s = (long) (n - map.size()) * m;

            for (LinkedList<Integer[]> list : map.values()) {
                int l = 0;

                for (Integer[] t : list) {
                    l += t[1] - t[0] + 1;
                }

                s += m - l;
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

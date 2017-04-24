import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class DAGQueries {

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
                    q = in.nextInt();

            int[] u = new int[m],
                    v = new int[m];

            for (int i = 0; i < m; i++) {
                u[i] = in.nextInt();
                v[i] = in.nextInt();
            }


        }

        private class Graph {

            private HashMap<Integer, Vertex> map = new HashMap<>();

            public Graph(int n, int m, int[] u, int[] v) {
                for (int i = 1; i <= n; i++) {
                    map.put(i, new Vertex());
                }

                for (int i = 0; i < m; i++) {
                    if (u[i] != v[i]) {
                        map.get(u[i]).add(map.get(v[i]));
                    }
                }
            }

            public void update(int u, int q, ) {

            }

            private class Vertex {

                private HashSet<Vertex> set = new HashSet<>();
                private Update update;

                public void add(Vertex vertex) {
                    set.add(vertex);
                }

            }

            private class Update {



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

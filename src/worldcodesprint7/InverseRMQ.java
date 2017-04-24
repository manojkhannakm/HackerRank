package worldcodesprint7;

import java.io.*;
import java.util.*;

/**
 * @author Manoj Khanna
 */

public class InverseRMQ {

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

            int[] a = new int[2 * n - 1];

            for (int i = 0; i < a.length; i++) {
                a[i] = in.nextInt();
            }

            HashMap<Integer, Node> nodeMap = new HashMap<>();

            for (int ai : a) {
                Node node = nodeMap.get(ai);

                if (node == null) {
                    node = new Node(ai);

                    nodeMap.put(ai, node);
                }

                node.c++;
            }

            if (nodeMap.size() != n) {
                out.println("NO");
                return;
            }

            TreeMap<Integer, TreeSet<Node>> setMap = new TreeMap<>();

            for (Node node : nodeMap.values()) {
                TreeSet<Node> set = setMap.get(node.c);

                if (set == null) {
                    set = new TreeSet<>();

                    setMap.put(node.c, set);
                }

                set.add(node);
            }

            int h = (int) (Math.log(n) / Math.log(2)) + 1;

            if (setMap.size() != h) {
                out.println("NO");
                return;
            }

            for (int i = 1, j = n > 1 ? n / 2 : 1; i <= h; i++, j = j > 1 ? j / 2 : j) {
                if (setMap.get(i).size() != j) {
                    out.println("NO");
                    return;
                }
            }

            out.println("YES");

            Node[] nodes = new Node[2 * n - 1];
            nodes[0] = setMap.get(h).pollFirst();

            for (int i = 1, j = h - 1, k = 1; i < n; i *= 2, j--) {
                for (int l = i == 1 ? 0 : 2 * (i / 2) - 1; l < 2 * i - 1; l++) {
                    nodes[k++] = nodes[l];

                    TreeSet<Node> set = setMap.get(j);
                    Node node = set.higher(nodes[l]);
                    nodes[k++] = node;
                    set.remove(node);
                }
            }

            for (int i = 0; i < nodes.length; i++) {
                if (i > 0) {
                    out.print(" ");
                }

                out.print(nodes[i].n);
            }
        }

        private class Node implements Comparable<Node> {

            private int n;

            private int c;

            public Node(int n) {
                this.n = n;
            }

            @Override
            public String toString() {
                return n + " -> " + c;
            }

            @Override
            public int compareTo(Node o) {
                return Integer.compare(n, o.n);
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

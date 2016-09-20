import java.io.*;
import java.util.*;

/**
 * @author Manoj Khanna
 */

public class GravityTree {

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

            int[] a = new int[n - 1];

            for (int i = 0; i < n - 1; i++) {
                int ai = in.nextInt();

                a[i] = ai;
            }

            int q = in.nextInt();

            int[] u = new int[q],
                    v = new int[q];

            for (int i = 0; i < q; i++) {
                int ui = in.nextInt(),
                        vi = in.nextInt();

                u[i] = ui;
                v[i] = vi;
            }

            Tree tree = new Tree(n, a);
            tree.dfs();

            for (int i = 0; i < q; i++) {
                out.println(tree.value(u[i], v[i]));
            }
        }

        private class Tree {

            private HashMap<Integer, Node> nodeMap;
            private Node rootNode;

            public Tree(int n, int[] a) {
                nodeMap = new HashMap<>(n);

                for (int i = 1; i <= n; i++) {
                    nodeMap.put(i, new Node(i));
                }

                for (int i = 0; i < n - 1; i++) {
                    Node parentNode = nodeMap.get(a[i]),
                            childNode = nodeMap.get(i + 2);

                    parentNode.childNodeList.add(childNode);
                    childNode.parentNode = parentNode;
                }

                rootNode = nodeMap.get(1);
            }

            private void dfs(Node node) {
                if (!node.childNodeList.isEmpty()) {
                    node.distanceMap.put(1, node.childNodeList.size());
                }

                for (Node childNode : node.childNodeList) {
                    dfs(childNode);

                    for (Map.Entry<Integer, Integer> entry : childNode.distanceMap.entrySet()) {
                        Integer distance = entry.getKey() + 1,
                                count = node.distanceMap.get(distance);

                        if (count == null) {
                            count = 0;
                        }

                        node.distanceMap.put(distance, count + entry.getValue());
                    }
                }
            }

            private ArrayList<Node> path(Node node) {
                if (node.parentNode == null) {
                    ArrayList<Node> nodeList = new ArrayList<>();
                    nodeList.add(node);

                    return nodeList;
                }

                ArrayList<Node> nodeList = path(node.parentNode);
                nodeList.add(node);

                return nodeList;
            }

            private long value(Node node, int o) {
                long s = o * o;

                for (Map.Entry<Integer, Integer> entry : node.distanceMap.entrySet()) {
                    Integer d = entry.getKey() + o,
                            c = entry.getValue();

                    s += d * d * c;
                }

                return s;
            }

            public void dfs() {
                dfs(rootNode);
            }

            public long value(int u, int v) {
                Node uNode = nodeMap.get(u),
                        vNode = nodeMap.get(v);
                ArrayList<Node> uNodeList = path(uNode),
                        vNodeList = path(vNode);
                int i = 0;

                while (i < uNodeList.size() && i < vNodeList.size()
                        && uNodeList.get(i) == vNodeList.get(i)) {
                    i++;
                }

                if (u != v && uNodeList.get(i - 1).n == v) {
                    long s = value(uNode, 0);

                    int j = uNodeList.size() - 2,
                            o = 1;

                    while (j >= 0 && uNodeList.get(j) != vNode.parentNode) {
                        s += o * o;

                        for (Node childNode : uNodeList.get(j).childNodeList) {
                            if (childNode != uNodeList.get(j + 1)) {
                                s += value(childNode, o + 1);
                            }
                        }

                        j--;
                        o++;
                    }

                    return s;
                } else {
                    return value(vNode, uNodeList.size() - i + vNodeList.size() - i);
                }
            }

            private class Node {

                private int n;

                private Node parentNode;
                private ArrayList<Node> childNodeList = new ArrayList<>();
                private HashMap<Integer, Integer> distanceMap = new HashMap<>();

                public Node(int n) {
                    this.n = n;
                }

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

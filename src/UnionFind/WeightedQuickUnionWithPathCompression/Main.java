package UnionFind.WeightedQuickUnionWithPathCompression;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Main {
    public static void main(String[] args) {
        int n = StdIn.readInt();
        WeightedQuickUnionWithPathCompressionUF uf = new WeightedQuickUnionWithPathCompressionUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                StdOut.println("connecting: " + p + " " + q);
            }
            StdOut.println("components count: " + uf.count());
        }
    }
}

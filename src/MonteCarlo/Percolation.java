package MonteCarlo;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {

    private static final int TOP_NODE_ID = 0;
    private static final int BOTTOM_NODE_ID = 1;
    private final int gridSize;
    private final boolean[] nodeIdIsOpen;
    private int openNodesCount;
    private final int[] nodeIdToParentNodeId;
    private final int[] nodesCountToNodeBeingRootId;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        gridSize = n;
        int nodesCount = gridSize * gridSize + 2;

        nodeIdToParentNodeId = new int[nodesCount];
        nodeIdIsOpen = new boolean[nodesCount];
        nodesCountToNodeBeingRootId = new int[nodesCount];

        for (int i = 0; i < nodesCount; i++) {
            nodeIdToParentNodeId[i] = i;
            nodeIdIsOpen[i] = false;
            nodesCountToNodeBeingRootId[i] = 1;
        }

        nodeIdIsOpen[TOP_NODE_ID] = true;
        nodeIdIsOpen[BOTTOM_NODE_ID] = true;
        openNodesCount = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int nodeId = getNodeId(row, col);

        if (isOpen(nodeId)) {
            return;
        }

        nodeIdIsOpen[nodeId] = true;
        openNodesCount += 1;

        // Connect to neighbour nodes
        if (row + 1 <= gridSize) {
            union(nodeId, getNodeId(row + 1, col));
        }

        if (row - 1 >= 1) {
            union(nodeId, getNodeId(row - 1, col));
        }

        if (col + 1 <= gridSize) {
            union(nodeId, getNodeId(row, col + 1));
        }

        if (col - 1 >= 1) {
            union(nodeId, getNodeId(row, col - 1));
        }

        // Connect to virtual nodes
        if (row == 1) {
            union(nodeId, TOP_NODE_ID);
        }

        if (row == gridSize) {
            union(nodeId, BOTTOM_NODE_ID);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int nodeId = getNodeId(row, col);

        return isOpen(nodeId);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int nodeId = getNodeId(row, col);

        return root(nodeId) == root(TOP_NODE_ID);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openNodesCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return root(TOP_NODE_ID) == root(BOTTOM_NODE_ID);
    }

    // test client (optional)
    public static void main(String[] args) {
        int size = StdIn.readInt();
        Percolation percolation = new Percolation(size);
        while (!StdIn.isEmpty()) {
            int row = StdIn.readInt();
            int col = StdIn.readInt();
            if (!percolation.isOpen(row, col)) {
                percolation.open(row, col);
                StdOut.println("opening: " + row + " " + col);
            }
            StdOut.println("percolates? " + percolation.percolates());
        }
    }

    private int getNodeId(int row, int col) {
        if (row > gridSize || row < 1 || col > gridSize || col < 1) {
            throw new IllegalArgumentException();
        }

        int rowOffset = (row - 1) * gridSize; // points to the first element in specified row
        int colOffset = col - 1;
        int virtualNodesOffset = 2;

        return rowOffset + colOffset + virtualNodesOffset;
    }

    private boolean isOpen(int nodeId) {
        return nodeIdIsOpen[nodeId];
    }

    private void union(int nodeId, int otherNodeId) {
        if (nodeId == otherNodeId) {
            return;
        }

        if (!isOpen(nodeId) || !isOpen(otherNodeId)) {
            return;
        }

        int nodeRootId = root(nodeId);
        int otherNodeRootId = root(otherNodeId);

        if (nodeRootId == otherNodeRootId) {
            return;
        }

        if (nodesCountToNodeBeingRootId[nodeRootId] > nodesCountToNodeBeingRootId[otherNodeRootId]) {
            nodeIdToParentNodeId[otherNodeRootId] = nodeRootId;
            nodesCountToNodeBeingRootId[nodeRootId] += nodesCountToNodeBeingRootId[otherNodeRootId];
        } else {
            nodeIdToParentNodeId[nodeRootId] = otherNodeRootId;
            nodesCountToNodeBeingRootId[otherNodeRootId] += nodesCountToNodeBeingRootId[nodeRootId];
        }
    }

    private int root(int nodeId) {
        while (nodeId != nodeIdToParentNodeId[nodeId]) {
            nodeIdToParentNodeId[nodeId] = nodeIdToParentNodeId[nodeIdToParentNodeId[nodeId]];
            nodeId = nodeIdToParentNodeId[nodeId];
        }

        return nodeId;
    }
}

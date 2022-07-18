package MonteCarlo;

public class Percolation {

    private final int gridSize;
    private final int TOP_NODE_ID = 0;
    private final int BOTTOM_NODE_ID = 1;
    private final int[] nodeIdToParentNodeId;
    private final boolean[] nodeIdIsOpen;
    private int openNodesCount;
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
        int nodeIndex = getNodeIndex(row, col);

        if (nodeIdIsOpen[nodeIndex]) {
            return;
        }

        nodeIdIsOpen[nodeIndex] = true;
        openNodesCount += 1;

        // TODO call union on neighbours
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int nodeIndex = getNodeIndex(row, col);

        return nodeIdIsOpen[nodeIndex];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int nodeIndex = getNodeIndex(row, col);

        // TODO check if is connected to TOP_NODE
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openNodesCount;
    }

    // does the system percolate?
    public boolean percolates() {
        // TODO check if TOP_NODE and BOTTOM_NODE are connected
    }

    // test client (optional)
    public static void main(String[] args) {
        // TODO implement test client
    }

    private int getNodeIndex(int row, int col) {
        if (row > gridSize || row < 1 || col > gridSize || col < 1) {
            throw new IllegalArgumentException();
        }

        int rowOffset = (row - 1) * gridSize; // points to the first element in specified row
        int colOffset = col - 1;
        int virtualNodesOffset = 2;

        return rowOffset + colOffset + virtualNodesOffset;
    }
}

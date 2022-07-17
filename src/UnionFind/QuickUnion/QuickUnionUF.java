package UnionFind.QuickUnion;

// WORST CASE TIME COMPLEXITY
// initialize: N
// union: N
// connected: N

public class QuickUnionUF {
    private final int[] objectIdToParentObjectId;

    public QuickUnionUF(int n) {
        objectIdToParentObjectId = new int[n];

        for (int i = 0; i < n; i++) {
            objectIdToParentObjectId[i] = i;
        }
    }

    public void union(int p, int q) {
        if (p == q) {
            return;
        }

        int pRootId = root(p);
        int qRootId = root(q);

        if (pRootId == qRootId) {
            return;
        }

        objectIdToParentObjectId[pRootId] = qRootId;
    }

    public boolean connected(int p, int q) {
        if (p == q) {
            return true;
        }

        int pRootId = root(p);
        int qRootId = root(q);

        return pRootId == qRootId;
    }

    int root(int objectId) {
        int parentId = objectIdToParentObjectId[objectId];

        while (objectId != parentId) {
            objectId = parentId;
            parentId = objectIdToParentObjectId[objectId];
        }

        return objectId;
    }

    int count() {
        int count = 0;

        for (int i = 0; i < objectIdToParentObjectId.length; i++) {
            if (objectIdToParentObjectId[i] == i) {
                count += 1;
            }
        }

        return count;
    }
}

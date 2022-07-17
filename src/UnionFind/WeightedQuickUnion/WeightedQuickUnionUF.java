package UnionFind.WeightedQuickUnion;

// WORST CASE TIME COMPLEXITY
// initialize: N
// union: log(N)
// connected: log(N)

public class WeightedQuickUnionUF {
    private final int[] objectIdToParentObjectId;
    private final int[] objectsCountInTheTreeRootedAt;

    public WeightedQuickUnionUF(int n) {
        objectIdToParentObjectId = new int[n];
        objectsCountInTheTreeRootedAt = new int[n];

        for (int i = 0; i < n; i++) {
            objectIdToParentObjectId[i] = i;
            objectsCountInTheTreeRootedAt[i] = 1;
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

        if (objectsCountInTheTreeRootedAt[pRootId] > objectsCountInTheTreeRootedAt[qRootId]) {
            objectIdToParentObjectId[qRootId] = pRootId;
            objectsCountInTheTreeRootedAt[pRootId] += objectsCountInTheTreeRootedAt[qRootId];
        } else {
            objectIdToParentObjectId[pRootId] = qRootId;
            objectsCountInTheTreeRootedAt[qRootId] += objectsCountInTheTreeRootedAt[pRootId];
        }
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

package UnionFind.QuickFind;

// WORST CASE TIME COMPLEXITY
// initialize: N
// union: N
// connected: 1

public class QuickFindUF {
    private final int[] objectIdToComponentId;

    public QuickFindUF(int n) {
        objectIdToComponentId = new int[n];

        for (int i = 0; i < n; i++) {
            objectIdToComponentId[i] = i;
        }
    }

    public void union(int p, int q) {
        if (p == q) {
            return;
        }

        int pComponentId = find(p);
        int qComponentId = find(q);

        if (pComponentId == qComponentId) {
            return;
        }

        for (int i = 0; i < objectIdToComponentId.length; i++) {
            if (objectIdToComponentId[i] == pComponentId) {
                objectIdToComponentId[i] = qComponentId;
            }
        }
    }

    public boolean connected(int p, int q) {
        if (p == q) {
            return true;
        }

        int pComponentIndex = find(p);
        int qComponentIndex = find(q);

        return pComponentIndex == qComponentIndex;
    }

    int find(int p) {
        return objectIdToComponentId[p];
    }

    int count() {
        int[] seenComponentIds = new int[objectIdToComponentId.length];
        int lastComponentIdIndex = -1;

        for (int componentId : objectIdToComponentId) {
            boolean hasBeenSeen = false;
            for (int seenComponentIdsIndex = 0; seenComponentIdsIndex <= lastComponentIdIndex; seenComponentIdsIndex++) {
                if (componentId == seenComponentIds[seenComponentIdsIndex]) {
                    hasBeenSeen = true;
                    break;
                }
            }

            if (!hasBeenSeen) {
                lastComponentIdIndex++;
                seenComponentIds[lastComponentIdIndex] = componentId;
            }
        }

        return lastComponentIdIndex + 1;
    }
}

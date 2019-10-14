import java.util.*;

class UnionFind {

	private int[] parents;
	private int[] ranks;

	public UnionFind(int N) {
		parents = new int[N];
		ranks = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
			ranks[i] = i;
		}
	}

	public void Union(int x, int y) {
		int x_parent = Find(x);
		int y_parent = Find(y);
		if (x_parent != y_parent) {
			if (ranks[x_parent] < ranks[y_parent]) {
				parents[y_parent] = x_parent;
			} else {
				parents[x_parent] = y_parent;
			}
		}
	}

	public int Find(int i) {
		if (i != parents[i]) {
			parents[i] = Find(parents[i]);
		}
		return parents[i];
	}

	public int numberComponent() {
		int count = 0;
		for (int i = 0; i < parents.length; i++) {
			if (i == parents[i]) count++;
		}
		return count;
	}

	public static void main(String[] args) {
		int[][] list = {{0,3}, {1,4}, {2,3}, {2,4}, {3,4}};
		UnionFind uf = new UnionFind(5);
		System.out.println(Arrays.toString(uf.parents));
		for (int[] pair: list) {
			int x = pair[0];
			int y = pair[1];
			uf.Union(x, y);
		}
		System.out.println(Arrays.toString(uf.parents));
		System.out.println("How many connected components: " + uf.numberComponent());
	}
}
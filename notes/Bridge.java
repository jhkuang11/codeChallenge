import java.util.*;

class Bridge {

	Map<Integer, List<Integer>> adj;
	boolean[] visited;
	int[] d;
	int[] low;
	int[] pred;
	static int time = 0;
	List<List<Integer>> ans;

	Bridge(int[][] edges) {
		ans = new ArrayList<>();
		adj = new HashMap<>();
		for (int[] edge: edges) {
			int from = edge[0];
			int to = edge[1];
			if (!adj.containsKey(from)) {
				adj.put(from, new ArrayList<Integer>());
			}
			if (!adj.containsKey(to)) {
				adj.put(to, new ArrayList<Integer>());
			}
			adj.get(from).add(to);
			adj.get(to).add(from);
		}
		int size = adj.size();
		visited = new boolean[size];
		pred = new int[size];
		low = new int[size];
		d = new int[size];
		for (int i = 0; i < size; i++) {
			visited[i] = false;
			pred[i] = -1;
		}
	};

	private void dfs(int key) {
		visited[key] = true;
		d[key] = low[key] = ++time;
		for (int neigh: adj.get(key)) {
			if (!visited[neigh]) {
				pred[neigh] = key;
				dfs(neigh);
				low[key] = Math.min(low[key], low[neigh]);

			} else if (neigh != pred[key]) {
				low[key] = Math.min(low[key], d[neigh]);
			}
		}
	}

	public List<List<Integer>> bridges() {
		for (int key: adj.keySet()) {
			if (!visited[key]) {
				dfs(key);
			}
		}

		for (int key: adj.keySet()) {
			int parent = pred[key];
			if (parent != -1 && d[key] == low[key]) {
				ans.add(new ArrayList<>(Arrays.asList(parent, key)));
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		int[][] edges = {{0,1}, {1,2}, {2,0}, {1,3}, {1,4}, {1,6},{3,5}, {4,5}};
		Bridge bg = new Bridge(edges);
		List<List<Integer>> ans = bg.bridges();
		for (List<Integer> list: ans) {
			System.out.println(list);
		}
	}
}
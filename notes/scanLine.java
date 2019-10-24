import java.util.*;

class Point implements Comparable<Point> {
	public int time;
	public int flag;

	public Point(int time, int flag) {
		this.time = time;
		this.flag = flag;
	}

	@Override
	public int compareTo(Point other) {
		return this.time == other.time ? this.flag - other.flag : this.time - other.time;
	}

	@Override
	public String toString() {
		return String.valueOf(time) + ',' + String.valueOf(flag);
	}
}

class scanLine {

	public static void main(String[] args) {
		/*
		String test = "cba";
		char[] temp = test.toCharArray();
		System.out.println(temp);
		Arrays.sort(temp);
        System.out.println(String.valueOf(temp));
		Queue<String> namePriorityQueue = new PriorityQueue<>(new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				return a.length() - b.length();
			}
        });
        Queue<String> namePriorityQueue = new PriorityQueue<>((a, b) -> (b.length() - a.length()));
		String[] list = {"Lisa", "Robert", "John", "Chris", "Angelina","Joe"};
		for (String name: list) {
			namePriorityQueue.offer(name);
		}
		System.out.println("Size of the queue: " + namePriorityQueue.size());
        while (!namePriorityQueue.isEmpty()) {
            System.out.println(namePriorityQueue.remove());
        }
        System.out.println("Size of the queue after removal: " + namePriorityQueue.size());
        */
        int[][] arr = {{1,5}, {2,5}, {6,7}, {5,6}, {3,8}};
        List<Point> list = new ArrayList<>();
        for (int[] pt: arr) {
        	list.add(new Point(pt[0], 1));
        	list.add(new Point(pt[1], -1));
        }
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
        int count = 0, max = 0;
        for (Point p: list) {
        	count += p.flag;
        	if (count > max) max = count;
		}
		System.out.println(max);
	}

}
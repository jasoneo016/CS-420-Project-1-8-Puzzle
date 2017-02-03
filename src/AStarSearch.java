import java.util.*;

public class AStarSearch {

	private static int numNodes = 0;

	private static PriorityQueue<Board> frontier = new PriorityQueue<Board>(100);

	private static HashSet<Board> explored = new HashSet<Board>();


	public static void search(Board board) {

		long start = System.currentTimeMillis();

		frontier.add(board);

		while (!frontier.isEmpty()) {
			Board b = frontier.poll();
			if (b.isGoalState()) {
				long end = System.currentTimeMillis() - start;
				b.printStates();
				System.out.println("Total time = " + end + " ms.");
				break;
			}

			Board temp = b.up();
			if (temp != null) {
				numNodes++;
				if (!explored.contains(temp)) {
					frontier.add(temp);
				}
			}

			temp = b.down();
			if (temp != null) {
				numNodes++;
				if (!explored.contains(temp)) {
					frontier.add(temp);
				}
			}

			temp = b.left();
			if (temp != null) {
				numNodes++;
				if (!explored.contains(temp)) {
					frontier.add(temp);
				}
			}

			temp = b.right();
			if (temp != null) {

				numNodes++;
				if (!explored.contains(temp)) {
					frontier.add(temp);
				}
			}

			explored.add(b);
		}

	}

}

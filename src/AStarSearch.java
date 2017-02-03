import java.util.*;

public class AStarSearch {

	private static int numNodes;
	private static int steps;
	private static PriorityQueue<Board> frontier = new PriorityQueue<Board>(100);
	private static HashSet<Board> explored = new HashSet<Board>();

	public static void search(Board board) {

		long start = System.currentTimeMillis();
		numNodes = 0;
		steps = 0;
		
		frontier.add(board);

		while (!frontier.isEmpty()) {
			steps++;
			Board b = frontier.poll();
			if (b.isGoalState()) {
				long end = System.currentTimeMillis() - start;
				b.printStates();
//				System.out.println("Total time = " + end + " ms.");
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
			explored.add(temp);
		}

	}
	
	public static int getNumNodes() {
		return numNodes;
	}
	
	public static int getSteps() {
		return steps;
	}

}

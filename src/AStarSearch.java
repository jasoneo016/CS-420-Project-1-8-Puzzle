import java.util.*;

public class AStarSearch {

	private int numNodes;
	private int steps;

	public void search(Board board) {
		PriorityQueue<Board> frontier = new PriorityQueue<Board>();
		HashSet<Board> explored = new HashSet<Board>();

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
			
			explored.add(b);
			
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
		}
	}

	public int getNumNodes() {
		return numNodes;
	}

	public int getSteps() {
		return steps;
	}
}

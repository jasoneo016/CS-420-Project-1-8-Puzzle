import java.util.Arrays;

public class Board implements Comparable<Board>{

	private int[] puzzleBoard;
	private final static int[] goalState = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };

	private int g;
	private int h;
	private int heuristicType;
	private int zeroIndex;
	private int hashBoard;

	private Board parent;

	public Board(int[] board, int heuristicType) {
		g = 0;
		this.puzzleBoard = board;
		this.hashBoard = Arrays.hashCode(puzzleBoard);
		this.heuristicType = heuristicType;
		if (heuristicType == 1) {
			h = hammingDistance();
		} else if (heuristicType == 2) {
			h = manhattanDistance();
		}
		this.zeroIndex = zeroIndex();
		parent = null;
	}

	public Board(Board parent, int moveIndex, int heuristicType) {
		this.puzzleBoard = Arrays.copyOf(parent.puzzleBoard, parent.puzzleBoard.length);
		this.hashBoard = Arrays.hashCode(puzzleBoard);
		this.heuristicType = heuristicType;
		puzzleBoard[parent.zeroIndex] = puzzleBoard[moveIndex];
		puzzleBoard[moveIndex] = 0;
		this.zeroIndex = moveIndex;
		this.g = parent.g + 1;
		if (heuristicType == 1) {
			h = hammingDistance();
		} else if (heuristicType == 2) {
			h = manhattanDistance();
		}
		this.parent = parent;
	}

	// h(1) = # misplaced tiles
	public int hammingDistance() {
		int numMisplacedTiles = 0;
		for (int i = 0; i < 9; i++) {
			if (puzzleBoard[i] != goalState[i]) {
				numMisplacedTiles++;
			}
		}
		return numMisplacedTiles;
	}

	// h(2) = the sum of the distances of the tiles from their goal positions
	public int manhattanDistance() {
		int sumDistance = 0;
		for (int i = 0; i < 9; i++) {
			if (puzzleBoard[i] == 0) {
				continue;
			} else {
				sumDistance += (Math.abs(puzzleBoard[i] / 3 - i / 3) + Math
						.abs(puzzleBoard[i] % 3 - i % 3));
			}
		}
		return sumDistance;
	}

	// get f(n) = g(n) + h(n)
	public int f() {
		return g + h;
	}

	public boolean isGoalState() {
		return (Arrays.equals(puzzleBoard, goalState));
	}

	public boolean isSolvable() {
		int numInversions = 0;
		for (int i = 0; i < 8; i++) {
			if (puzzleBoard[i] != 0) {
				for (int j = i + 1; j < 9; j++) {
					if (puzzleBoard[j] != 0 && puzzleBoard[i] > puzzleBoard[j]) {
						numInversions++;
					}
				}
			}
		}
		if (numInversions % 2 == 0) {
			return true;
		}
		return false;
	}

	public int zeroIndex() {
		for (int i = 0; i < puzzleBoard.length; i++) {
			if (puzzleBoard[i] == 0) {
				return i;
			}
		}
		return 0;
	}

	public Board up() {
		if (zeroIndex < 6) {
			return new Board(this, zeroIndex + 3, heuristicType);
		}
		return null;
	}

	public Board down() {
		if (zeroIndex > 2) {
			return new Board(this, zeroIndex - 3, heuristicType);
		}
		return null;
	}

	public Board left() {
		if (zeroIndex % 3 < 2) {
			return new Board(this, zeroIndex + 1, heuristicType);
		}
		return null;
	}

	public Board right() {
		if (zeroIndex % 3 > 0) {
			return new Board(this, zeroIndex - 1, heuristicType);
		}
		return null;
	}

	public void printStates() {
		if (parent != null) {
			parent.printStates();
		}
		System.out.println();
		System.out.println(this);
	}

	@Override
	public String toString() {
		String puzzleString = "\n";
		puzzleString += "f(n) = " + f() + " = g(n) + h(n) = " + g + " + " + h
				+ "\n";
		puzzleString += puzzleBoard[0] + " | " + puzzleBoard[1] + " | "
				+ puzzleBoard[2];
		puzzleString += "\n---------\n";
		puzzleString += puzzleBoard[3] + " | " + puzzleBoard[4] + " | "
				+ puzzleBoard[5];
		puzzleString += "\n---------\n";
		puzzleString += puzzleBoard[6] + " | " + puzzleBoard[7] + " | "
				+ puzzleBoard[8];
		return puzzleString;
	}

	@Override
	public int compareTo(Board b) {
		if (this.f() < b.f()) {
			return -1;
		}
		if (this.f() > b.f()) {
			return 1;
		}
		return 0;
	}
	
	@Override
	public int hashCode() {
		return hashBoard;
	}
}

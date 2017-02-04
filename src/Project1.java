import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Project1 {

	public static void main(String[] args) throws IOException {

		AStarSearch aStar = new AStarSearch();
		List<Integer> list;
		Scanner kb = new Scanner(System.in);
		test200Cases();
		System.out.println("Welcome to 8-Puzzle!\n ");
		System.out.println("Would you like to: ");
		System.out.println("1. Randomly Generate an 8-Puzzle Problem");
		System.out.println("2. Enter Your Own 8-Puzzle Configuration");

		boolean flag = true;
		String choice = kb.nextLine();
		int[] board = new int[9]; 

		do {
			switch (choice) {
			case "1":
				board = new int[9];
				list = new ArrayList<Integer>(9);
				for (int i = 0; i < 9; i++) {
					list.add(i);
				}
				Collections.shuffle(list);
				for (int i = 0; i < board.length; i++) {
					board[i] = list.get(i);
				}
				flag = false;
				break;

			case "2":
				list = new ArrayList<Integer>(9);
				board = new int[9];
				System.out
						.println("\n Enter your own 8-Puzzle Configuration of non-repeating numbers ranging from 0-8.");

				do {
					int index = list.size() + 1;
					try {
						System.out.println("Enter #" + index + ": ");
						int num = kb.nextInt();

						if (list.contains(num)) {
							System.out
									.println("This is number was already entered. Please try again.");
						} else if (num < 0 || num > 8) {
							System.out
									.println("# must be in the range of 0-8.");
						} else {
							list.add(num);
						}
					} catch (InputMismatchException e) {
						System.out.println("Not a valid number. Try again");
						kb.nextLine();
					}
				} while (list.size() < 9);

				for (int i = 0; i < board.length; i++) {
					board[i] = list.get(i);
				}
				flag = false;
				break;

			default:
				System.out.println("Please enter '1' or '2' ");
				choice = kb.nextLine();
				if (choice == "1" || choice == "2") {
					flag = false;
				}
				break;

			}
		} while (flag);

		Board puzzle = new Board(board, 1);

		if (!puzzle.isSolvable()) {
			System.out
					.println("This is not a valid 8-Puzzle and cannot be solved.");
		} else {
			aStar.search(puzzle);
		}
	}

	public static void test200Cases() throws IOException {
		AStarSearch aStar = new AStarSearch();
		int depth = 20;
		String fileName = "Depth" + depth + ".txt";
		int[] temp = new int[9];
		int numCases = 200;
		int totalNodes = 0;
		int totalSteps = 0;

		BufferedReader br = new BufferedReader(new FileReader(
				"/Users/admin/Desktop/eclipseworkspace/CS 420 Project 1 8Puzzle/src/"
						+ fileName));
		String line;

		long start = System.currentTimeMillis();

		while ((line = br.readLine()) != null) {
			if (line.contains("Depth " + depth)) {
				System.out.println("This is depth " + depth);
				continue;
			}

			for (int i = 0; i < line.length(); i++) {
				temp[i] = Character.getNumericValue(line.charAt(i));
			}

			Board tempBoard = new Board(temp, 2);
			aStar.search(tempBoard);
			totalNodes += aStar.getNumNodes();
			totalSteps += aStar.getSteps();
		}

		long end = System.currentTimeMillis() - start;
		System.out.println("\nDepth " + depth + " with Manhattan Distance");
		System.out.println("Total time = " + end + " ms.");
//		System.out.println("Average time = " + end/numCases + " ms.");
		System.out.println("Total Nodes expanded : " + totalNodes);
		System.out.println("Average Nodes expanded : " + totalNodes / numCases);
		System.out.println("Total Steps taken : " + totalSteps);
		System.out.println("Average Steps taken : " + totalSteps / numCases);

		br.close();
	}

}

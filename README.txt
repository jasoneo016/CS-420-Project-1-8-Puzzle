How to Run:

When started, prompt will ask you to choose either '1' or '2':

    1. Randomly Generate an 8-Puzzle Problem
    2. Enter Your Own 8-Puzzle Configuration
    
Any other inputs will redirect you and ask you to enter either 1 or 2 until one is successfully entered.

If 1 is selected, a random puzzle will be generated for you, with each step in the puzzle printed out along with its heuristics.

If 2 is selected, user will be asked to input non-reading numbers from 0-8 one at a time. If a repeated number or non-number is entered, 
you will be asked to enter it again until a valid input is accepted.

Ex.
Enter your own 8-Puzzle Configuration of non-repeating numbers ranging from 0-8.
Enter #1: 
1
Enter #2: 
4
Enter #3: 
2
Enter #4: 
3
Enter #5: 
0
Enter #6: 
5
Enter #7: 
6
Enter #8: 
7
Enter #9: 
8


*FOR TESTING PURPOSES*
TO CHANGE THE HEURISTIC TYPE:
On line 86 of Project1.java : 
            Board puzzle = new Board(board, 1);
Change the second parameter to either 1 for Hamming Distance, or 2 for Manhattan Distance. Likewise for line 122
			Board tempBoard = new Board(temp, 2);

TO CHANGE THE DEPTH TEST CASE FILE:
line 98 of Project1.java:
    		int depth = 2;
            
Change the value to either 2, 4, 6, 8, 10, 12, 14, 16, 18, or 20.

Edit BufferedReader according to the path of which you saved the Depth#.txt files.
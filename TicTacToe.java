/**
* This is a 2 player tic tac toe game
* that allows user input for the size of 
* the board and accurately checks the board. 
* @author Cole Miller
* @version 1.0
*/

import java.util.Scanner; 

public class TicTacToe
{

	/* Method is used for the board later on, so that the spaces
	* can be even and organized when displayed to the screen. Will take 
	* in an int provided by the user and returns back the spaces (also int). 
	*/ 
	public static int numDigits(int n)
	{
		int spaces = 1;
		while (n > 9)
		{
		
			n = n / 10;
			spaces ++;
		}
		return spaces;
		
	}

	/* This method asks the user how large or small they want the playing
	* board to be and then creates a new array for that board accordingly. 
	* Declares global array and returns an array custom to suit the 
	* user's input. 
	*/
	public static int[][] createArray()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("What size board would you like to play on? ");
		int n = keyboard.nextInt();
		int [][] arr = new int[n][n]; 
		int num = 0;
		for(int k = 0; k < arr.length; k++)
		{
			for(int p = 0; p < arr[k].length; p ++)
			{
				arr[k][p] = num;
				num++;
			}
		}

		return arr;
	}
	
	/* This method uses the array created in createArray and makes a visible 
	* and organized board to display on the screen, allowing the user
	* a clear view of the spots on the board and the current progress.
	* Takes in the int provided by the user and prints the board to 
	* the screen, does not return anything. 
	*/ 
	public static void boardCreator(int[][] arr)
	{
		for(int y = 0; y < arr[0].length * 2 + arr[0].length * 2 + arr[0].length; y++)
		{
			System.out.print("-");
		}
		System.out.println();
		for(int i = 0; i < arr.length; i++)
		{
			System.out.print("|");
			for(int j = 0; j < arr[i].length; j++)
			{
				System.out.print(" ");
				for(int o = 0; o < numDigits(arr.length * arr.length) - numDigits(arr[i][j]); o++)
				{
					System.out.print(" ");
				}

				System.out.print(arr[i][j] + "" + "|");
			}
			System.out.println();
			for(int y = 0; y < arr[i].length * 2 + arr[i].length * 2 + arr[0].length; y++)
			{
				System.out.print("-");
			}
			System.out.println();
		}
	}

	public static void main(String [] args)
	{
		symbolPlacer(createArray());
	}

	/* A method used to check the board as a player makes a move, to look 
	* for various cases which could be problematic such as if a spot
	* the user picks alread has a player's marker down, or the spot
	* is not on the board. Takes in the user's position in which they
	* have just moved to, as well as the board's array. 
	*/ 
	public static boolean checkBoard(int position, int[][] arr)
	{
		int row = position / arr.length;
		int column = position % arr.length;
		if ((position > (arr.length * arr.length) - 1))
		{
			System.out.println("That spot is not on the board.");
			return false;
		}
		if (arr[row][column] == -1 || arr[row][column] == -2)
		{	
			System.out.println("That spot had already been taken.");
			return false;
		}
		
		else
		{
			return true;
		}
	}

	/* The method that actually asks the user where they want to place 
	* their marker on the board and then changes the board both in
	* the array and visably, also using checkBoard and isGameOver for
	* each move to check whether the game is over or there is a 
	* problem with the user's move. Takes in the board's array and 
	* only changes the board and array, does not return anything. 
	*/ 
	public static void symbolPlacer(int[][] arr)
	{
		boardCreator(arr);
		while (isGameOver(arr) != true)
		{
			int player1 = -1;
			int player2 = -2;
			Scanner keyboard = new Scanner(System.in);
			boolean goTurn = false;
			int player1Placement = 0;
			while (goTurn == false)
			{
				System.out.print("Player 1, where would you like to place your marker? ");
				player1Placement = keyboard.nextInt();
				if (checkBoard(player1Placement, arr) == true)
					goTurn = true;
			}
			int row = (player1Placement / arr.length);
			int column = (player1Placement % arr.length);
			arr[row][column] = player1;
			boardCreator(arr);
			
			if (isGameOver(arr) == false)
			{
				boolean goTurn2 = false;
				int player2Placement = 0;
				while (goTurn2 == false)
				{
					System.out.print("Player 2, where would you like to place your marker? ");
					player2Placement = keyboard.nextInt();
					if (checkBoard(player2Placement, arr) == true)
						goTurn2 = true;
				}
				int row2 = player2Placement / arr.length;
				int column2 = player2Placement % arr.length;
				arr[row2][column2] = player2;
				boardCreator(arr);
			}
		}
	}

	/* This huge method checks every possible way that a player could win,
	* and then ends the game and prints the result for each case. Takes in 
	* the board array and returns either true or false based on the 
	* winning and tying patterns dectection. 
	*/ 
	public static boolean isGameOver(int [][] board)
	{
		boolean gameOver = false;
		int player1Tiles = 0;
		int player2Tiles = 0;
		int row = 0;
		for (int j = 0; j < board.length; j ++)
		{
			for (int i = 0; i < board.length; i++)
			{
				if (board[row][i] == -1)
					player1Tiles ++;
				else if (board[row][i] == -2)
					player2Tiles ++;
			}
			if (player1Tiles == board.length)
			{
				System.out.println("Player 1 Wins!");
				gameOver = true;
			
			}
		
			if (player2Tiles == board.length)
			{
				System.out.println("Player 2 Wins!");
				gameOver = true;
			}
			player1Tiles = 0;
			player2Tiles = 0;
			row ++;
			
		}
		
		player1Tiles = 0;
		player2Tiles = 0;
		int column = 0;
		for (int j = 0; j < board.length; j ++)
		{
			for (int i = 0; i < board.length; i++)
			{
				if (board[i][column] == -1)
					player1Tiles ++;
				else if (board[i][column] == -2)
					player2Tiles ++;
			}
			column ++;
			if (player1Tiles == board.length)
			{
				System.out.println("Player 1 Wins!");
				return true;
			}	
		
			if (player2Tiles == board.length)
			{
				System.out.println("Player 2 Wins!");
				return true;
			}
			player1Tiles = 0;
			player2Tiles = 0;
			
		}
		
		
		
		int player1DiagonalCounter = 0;
		int player2DiagonalCounter = 0;
		int columnCounter = 0;
		int rowCounter = 0;
		while (rowCounter < board.length)
		{
			if (board[rowCounter][columnCounter] == -1)
				player1DiagonalCounter ++;
			if (board[rowCounter][columnCounter] == -2)
				player2DiagonalCounter ++;
			columnCounter += 1;
			rowCounter += 1;
		}
		if (player1DiagonalCounter == board.length)
		{
			System.out.println("Player 1 Wins!");
			return true;
		}
		if (player2DiagonalCounter == board.length)
		{
			System.out.println("Player 2 Wins!");
			return true;
		}

		player1DiagonalCounter = 0;
		player2DiagonalCounter = 0;
		columnCounter = board.length - 1;
		rowCounter = 0;
		while (rowCounter < board.length)
		{
			if (board[rowCounter][columnCounter] == -1)
				player1DiagonalCounter ++;
			if (board[rowCounter][columnCounter] == -2)
				player2DiagonalCounter ++;
			columnCounter -= 1;
			rowCounter += 1;
		}
		if (player1DiagonalCounter == board.length)
		{
			System.out.println("Player 1 Wins!");
			return true;
		}
		if (player2DiagonalCounter == board.length)
		{
			System.out.println("Player 2 Wins!");
			return true;
		}
		
		boolean boardFull = true;
		for (int k = 0; k < board.length; k++)
		{
			for (int q = 0; q < board.length; q ++)
			{
				if (board[k][q] >= 0)
					boardFull = false;
				
				
			}
		}
		
		if (boardFull != false)
		{
			System.out.println("Tie game!");
			gameOver = true;
		
		}
		return gameOver;
	}

}
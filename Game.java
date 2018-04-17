import java.util.Scanner;

public class Game 
{
	Scanner input = new Scanner(System.in);
	
	//guess(board: int[][]): int[]
	//returns an array of the user's guess in coordinates
	public int[] guess(int[][] board)
	{
		int x = 0;
		int y = 0;
		boolean checkValues = true;
		System.out.print("\nPlease enter the coordinates of the tile you would like to uncover: ");
		
		while(checkValues = true)
		{
			x = input.nextInt();
			y = input.nextInt();
			
			if(x < 1 || x > board.length || y < 1 || y > board.length)
			{
				System.out.print("Please enter valid coordinates: ");
			}
			else
			{
				int[] guess = {x-1, y-1};
				return guess;
			}
		}
		
		int[] guess = {x-1, y-1};
		return guess;
	}

	//revealTile(board: int[][], answers: int[][], guess: int[]): int[][]
	//Processes the user's guess and reveals the Value underneath
	public int[][] revealTile(int[][]board, int[][] answers, int[] guess)
	{
		int x  = guess[0]; int y = guess[1];
		board[y][x] = answers[y][x];
		if(board[y][x] == 0)
			board[y][x] = 10;
		return board;
		
	}

	//determineGameOver(answers: int[][], guess: int[]): boolean
	//Determines whether or not the user set off a mine
	public boolean determineGameOver(int[][] answers, int[] guess)
	{
		int x  = guess[0]; int y = guess[1];
		if(answers[y][x] == 9)
		{
			System.out.println("\nBOOM! You set off a mine. Game Over!");
			return false;
		}
		else
		{
			return true;
		}
	}

	//determineVictory(difficulty: int, moves: int): boolean
	//Determines whether or not the user successfully completed the game
	public boolean determineVictory(int difficulty, int moves)
	{
		if(difficulty == 0)
		{
			if(moves == 71)
			{
				return true;
			}
		}
		else if(difficulty == 1)
		{
			if(moves == 216)
			{
				return true;
			}
		}
		else if(difficulty == 2)
		{
			if(moves == 385)
			{
				return true;
			}
		}
		
		return false;
		
	}
		
	

}

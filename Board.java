import java.util.Scanner;
import java.util.Random;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Board extends JFrame
{
	//Instantiations
	Random rand = new Random();
	Scanner input = new Scanner(System.in);

	//setDifficulty(): int
	//Determines the Difficulty of the game. Used to determine size of board
	public int setDifficulty()
	{	
		JFrame frame = new JFrame("Minesweeper");
		frame.setVisible(true);
		frame.setSize(200, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel label = new JLabel("Please Input the Difficulty you'd like to play on:\n");
		JPanel panel = new JPanel();
		frame.add(panel);
		panel.add(label);
		
		JButton button1 = new JButton("Easy");
		panel.add(button1);
		button1.addActionListener(new Action());
		
		JButton button2 = new JButton("Medium");
		panel.add(button2);
		
		JButton button3 = new JButton("Hard");
		panel.add(button3);
		
		
		//******************************
		boolean runLoop = true;
		int returnDif = 0;
		String diff = "";
			
		System.out.print("Please input the Difficulty you'd like to play on (Input Easy, Medium or Hard): ");
		while(runLoop == true)
		{
			diff = input.nextLine();
			if(diff.toLowerCase().equals("easy"))
			{
				returnDif = 0;
				runLoop = false;
			}
			else if(diff.toLowerCase().equals("medium"))
			{
				returnDif = 1;
				runLoop = false;
			}
			else if(diff.toLowerCase().equals("hard"))
			{
				returnDif = 2;
				runLoop = false;
			}
			else
				System.out.print("Please enter a valid difficulty (Easy, Medium or Hard): ");
		}
			
			return returnDif;
	}
	
	static class Action implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JFrame frame2 = new JFrame("Clicked!");
			frame2.setVisible(true);
			frame2.setSize(200, 200);
			JLabel label = new JLabel("You clicked me daddy");
			JPanel panel = new JPanel();
			frame2.add(panel);
			panel.add(label);
		}
	}


	//createBoard(difficulty: int): int[][]
	//Creates Board in the form of a 2D array. The difficulty determines how large it should be 
	public int[][] createBoard(int difficulty)
	{
		int x = 0;
		int y = 0;
		if(difficulty == 0)
		{
			x = 9; y = 9;
		}
		else if(difficulty == 1) 
		{
			x = 16; y = 16;
		}
		else if(difficulty == 2)
		{
			x = 22; y = 22;
		}
			
		int[][] myBoard = new int[x][y]; //Adds 99 Mines
			
		return myBoard;
	}
	
	//createPlayBoard(difficulty: int): int[][]
	//Creates board without anything showing - This will be the board the user plays with
	public int[][] createPlayBoard(int difficulty)
	{
		int x = 0;
		int y = 0;
		if(difficulty == 0)
		{
			x = 9; y = 9;
		}
		else if(difficulty == 1) 
		{
			x = 16; y = 16;
		}
		else if(difficulty == 2)
		{
			x = 22; y = 22;
		}
			
		int[][] myBoard = new int[x][y];
				
		return myBoard;
	}
	
	//determineMines(difficulty: int): int
	//Determines how many mines should be places around the map based on difficulty
	public int determineMines(int difficulty)
	{
		int mineNum = 0;
		if(difficulty == 0)
		{
			mineNum = 10;
		}
		else if(difficulty == 1) 
		{
			mineNum = 40;
		}
		else if(difficulty == 2)
		{
			mineNum = 99;
		}
			
		return mineNum;
	}
	
	//placeMines(board: int[][], numOfMines: int): int[][]
	//Places the mines around the board
	public int[][] placeMines(int[][] board, int numOfMines)
	{
		int randomNum = rand.nextInt(board.length);
		while(numOfMines != 0)
		{
			int randomX = rand.nextInt(board.length);
			int randomY = rand.nextInt(board.length);
			if(board[randomX][randomY] != 9)
			{
				board[randomX][randomY] = 9;
				numOfMines--;
			}
		}
		return board;
	}
	
	//printFinal(board: int[][])	
	//Prints the board after the game is over with all tiles revealed
	public void printFinal(int[][] board)
	{
		for(int x = 0; x < board.length; x++)
		{
			for(int y = 0; y < board[x].length; y++)
			{
				if(board[x][y] == 9)
				{
					System.out.print("X ");
				}
				else
				{
					System.out.print("O ");
				}
			}
			System.out.print("\n");
		}
	}
	
	
	//printBoard(board: int[][]) - HOLD	
	//Prints the board as the player can currently see it	
	public void printBoard(int[][] board)
	{
		for(int x = 0; x < board.length; x++)
		{
			for(int y = 0; y < board[x].length; y++)
			{
				if(board[x][y] == 9)
				{
					System.out.print("X ");
				}
				else if(board[x][y] == 10)
				{
					System.out.print("O ");
				}
				else if(board[x][y] >= 1 && board[x][y] <= 8)
				{
					System.out.print(board[x][y] + " ");
				}
				else
				{
					System.out.print("# ");
				}
			}
			System.out.print("\n");
		}
	}

	//declareHints(board: int[][]): int[][]
	//Gives each square values dependent on how many mines are adjacent
	public int[][] declareHints(int[][] board)
	{
		int mines = 0;
		for(int x = 0; x < board.length; x++)
		{
			for(int y = 0; y < board[x].length; y++)
			{
				mines += findMine(board, y - 1, x - 1);  // NW
				mines += findMine(board, y - 1, x);      // N
				mines += findMine(board, y - 1, x + 1);  // NE
				mines += findMine(board, y, x - 1);      // W
				mines += findMine(board, y, x + 1);      // E
				mines += findMine(board, y + 1, x - 1);  // SW
				mines += findMine(board, y + 1, x);      // S
				mines += findMine(board, y + 1, x + 1);  // SE
				    
				if(mines > 0 && board[y][x] != 9)
				{
				    board[y][x] = mines;
				    mines = 0;
				}
			    else
				{
				    mines = 0;
				}
			}
		}
		return board;
	}
	
	//findMine(board: int[][], y: int, x: int)
	//Used by the declareHints method to figure out whether a mine exists in a space
	public int findMine(int[][] board, int y, int x)
	{
		if(y >= 0 && y < board.length && x >= 0 && x < board.length && board[y][x] == 9) 
		{
			return 1;
		} 
		else 
		{
			return 0;
		}
			  
	}


}


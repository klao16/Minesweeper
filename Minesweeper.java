import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;

public class Minesweeper
{
	
	public static void main(String[] args)
	{	
		//Instantiations
		int[] myGuess = {0,0};
		boolean runLoop = true; boolean victory = false;
		Board myBoard = new Board();
		Game myGame = new Game();
		
		//Sets up answer board
		int difficulty = myBoard.setDifficulty();
		int[][] gameAnswers = myBoard.createBoard(difficulty);
		int numOfMines = myBoard.determineMines(difficulty);
		gameAnswers = myBoard.placeMines(gameAnswers, numOfMines); //printBoard(gameAnswers);	
		gameAnswers = myBoard.declareHints(gameAnswers);
		
		//Set up play board
		int[][] gameBoard = myBoard.createPlayBoard(difficulty);
		
		System.out.println("\n\nWelcome to Minesweeper");
		myBoard.printBoard(gameBoard);
		//For checking purposes: printBoard(gameAnswers);
		
		int playerMoves = 0;
		while(runLoop == true)
		{
			myGuess = myGame.guess(gameBoard);
			runLoop = myGame.determineGameOver(gameAnswers, myGuess);
			if(runLoop == false)
			{
				System.out.println("Final Answers: ");
				myBoard.printFinal(gameAnswers);
			}
			else
			{
				gameBoard = myGame.revealTile(gameBoard, gameAnswers, myGuess);
				myBoard.printBoard(gameBoard);
				victory = myGame.determineVictory(difficulty, playerMoves);
				if(victory == true)
				{
					runLoop = false;
				}
				else
				{
					playerMoves++;
				}
				
			}
		}
		
		if(victory == true)
		{
			System.out.println("Congratulations! You Win!");
		}
		
	}
	
}

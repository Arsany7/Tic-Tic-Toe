package ttt;

import java.util.Scanner;

public class GameSession { // this class to create the game statues
	private Player currentplayer;// this is the player who plays now
	private Board board;// object from class board
	private Player player1;// object from class player to represent first player
	private Player player2;// object from class player to represent second player
	private boolean EndGame = false;// game current state

	public GameSession(Board board, Player p1, Player p2) {// constructor
		this.board = board;
		this.player1 = p1;
		this.player2 = p2;
		this.currentplayer = p1;

	}


	public void init() {
		Scanner numbers = new Scanner(System.in);
		while (true) {
			// check whether the input is valid or not and sends the valid input to be
			// stored in the array

			board.displayBoard();
			System.out.print(currentplayer.getSymbol() + " Enter a row number (1,2,3,4,5,6): ");
			int row = numbers.nextInt();
			System.out.print("Enter a column number (1,2,3,4,5,6,7): ");
			int col = numbers.nextInt();

			if (row - 1 < 0 || col - 1 < 0 || row > board.getROWS() || col > board.getCOLUMNS()) {
				System.out.println("This position is off the bounds of the board! Try again.");
				this.init();
			}
			// Check if the position on the board the user entered is empty (has a -) or not
			else if (board.getBoard()[row - 1][col - 1] != '-') {
				System.out.println("Someone has already made a move at this position! Try again.");
				this.init();
			} else {
				board.putSymbolXorO(row, col, currentplayer.getSymbol());
			}
			checkWinner(row, col);
			if (this.EndGame == true)
				break;
			if (this.currentplayer.equals(player1)) {
				this.currentplayer = player2;
			} else {
				this.currentplayer = player1;
			}
		}
	}

	public void checkWinner(int row, int col) {
		// Check to see if either player has won
		if ((checkWin(row - 1, col - 1) == 1) && currentplayer.getSymbol() == player1.getSymbol()) {
			System.out.println("Player 1 has won!");
			board.displayBoard();
			EndGame = true;
		} else if ((checkWin(row - 1, col - 1) == 1) && currentplayer.getSymbol() == player2.getSymbol()) {
			System.out.println("Player 2 has won!");
			board.displayBoard();
			EndGame = true;
		} else {

			// If neither player has won, check to see if there has been a tie (if the board
			// is full)
			if (isBoardFull()) {
				System.out.println("It's a tie!");
				EndGame = true;
			}
		}

	}

	// Make a function to check if all of the positions on the board have been
	// filled
	public boolean isBoardFull() {
		for (int i = 0; i < board.getROWS(); i++) {
			for (int j = 0; j < board.getCOLUMNS(); j++) {
				if (board.getBoard()[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	public  int checkWin(int rowNum,int colNum) 
	{
	//  For checking whether any win or lose condition is reached. Returns 1 if win or lose is reached. else returns 0
	//  colNum is the column number where the last symbol was placed
	//  rowNum is the row number where the last symbol was placed
	
		
		int count=0;

		// Horizontal check
		for (int i=0;i<board.getCOLUMNS();i++)
		{
		    if (this.board.getBoard()[rowNum][i]==currentplayer.getSymbol())
		        count++;
		    else
		        count=0;

		    if (count>=3)
		        return 1;
		}
		//Vertical check
		count=0;
		for (int i=0;i<board.getROWS();i++)
		{
		    if (this.board.getBoard()[i][colNum]==currentplayer.getSymbol())
		        count++;
		    else
		        count=0;

		    if (count>=3)
		        return 1;
		}
		count=0; 
 // top-left to bottom-right - (  lower triangle  )
    for( int rowStart = 0; rowStart < board.getROWS()-3; rowStart++){
        count = 0;
        int row, col;
        for( row = rowStart, col = 0; row < board.getROWS() && col < board.getCOLUMNS(); row++, col++ ){
            if(this.board.getBoard()[row][col] == currentplayer.getSymbol()){
                count++;
                if(count >=3) return 1;
            }
            else {
                count = 0;
            }
        }
    }
	count=0; 
    // top-left to bottom-right ( upper triangle )
    for( int colStart = 1; colStart < board.getCOLUMNS()-3; colStart++){
        count = 0;
        int row, col;
        for( row = 0, col = colStart; row < board.getROWS() && col < board.getCOLUMNS(); row++, col++ ){
            if(this.board.getBoard()[row][col] == currentplayer.getSymbol()){
                count++;
                if(count >=3) return 1;
            }
            else {
                count = 0;
            }
        }
    }
    
			if(DiagonalTwoMid(board.getBoard(), rowNum,colNum))return 1;
			if(DiagonalTwoDown(board.getBoard(), rowNum,colNum)) return 1;
			if(DiagonalTwoUp(board.getBoard(), rowNum,colNum)) return 1;
			
		
	return 0;
}
	private boolean DiagonalTwoUp(char[][] boardd, int i, int j) {
		boolean win = false;
		if (i != board.getROWS()  - 2 && i != board.getROWS()  - 1 && j != 0 && j != 1)
			if (boardd[i][j] == boardd[i + 1][j - 1] && boardd[i][j] == boardd[i + 2][j - 2] &&boardd[i][j]==currentplayer.getSymbol())
				win = true;
		return win;
	}

	private boolean DiagonalTwoDown(char[][] boardd, int i, int j) {
		boolean win = false;
		if (i != 0 && i != 1 && j != board.getCOLUMNS() - 2 && j != board.getCOLUMNS() - 1)
			if (boardd[i][j] == boardd[i - 1][j + 1] && boardd[i][j] == boardd[i - 2][j + 2]&&boardd[i][j]==currentplayer.getSymbol())
				win = true;
		return win;
	}

	private boolean DiagonalTwoMid(char[][] boardd, int i, int j) {
		{
			boolean win = false;
			if (i != 0 && i != board.getROWS()  - 1 && j != 0 && j != board.getCOLUMNS()  - 1)
				if (boardd[i][j] == boardd[i - 1][j + 1] && boardd[i][j] == boardd[i + 1][j - 1]&&boardd[i][j]==currentplayer.getSymbol())
					win = true;
			return win;
		}
	}
}


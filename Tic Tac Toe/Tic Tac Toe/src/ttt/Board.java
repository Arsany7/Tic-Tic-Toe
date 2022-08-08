package ttt;

public class Board {
	// Create a 6x7 array that represents the board
	private final int ROWS = 6;
	private final int COLUMNS = 7;
	private char[][] board = new char[ROWS][COLUMNS];

	// -----------------------------------------------------

	// Initialize the board with dashes(-)(as empty positions)
	public Board() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				board[i][j] = '-';
			}
		}
	}

	// Getters and Setters

	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	public int getROWS() {
		return ROWS;
	}

	public int getCOLUMNS() {
		return COLUMNS;
	}

	// Make a function to draw the tic tac toe board (also after each modification)
	public void displayBoard() {
		System.out.println("Board:");
		for (int i = 0; i < ROWS; i++) {
			// The inner for loop prints out each row of the board
			for (int j = 0; j < COLUMNS; j++) {
				System.out.print(board[i][j]);
			}
			// This print statement makes a new line so that each row is on a separate line
			System.out.println();
		}
	}

// storing the symbol in the 2D Array.
	public void putSymbolXorO(int row, int column, char symbol) {
			this.board[row - 1][column - 1] = symbol;
	}

}

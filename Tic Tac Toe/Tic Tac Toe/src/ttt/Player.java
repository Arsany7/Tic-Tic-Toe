package ttt;

public class Player {//this class to create players with a specific symbol either X or O
	private char symbol;
	public Player(char symbol) {// constructor
		this.symbol=symbol;
	}
	// Getters and Setters
	public char getSymbol() {
		return symbol;
	}
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	};

}

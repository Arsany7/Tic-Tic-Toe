package ttt;

public class Game {
// main class to test and play the game.
	// it just creates objects and call the function init to start play
	public static void main(String[] args) {
		Board board = new Board();
		Player playerOne=new Player('X');
		Player playerTwo=new Player('O');
		GameSession gamesession=new GameSession(board,playerOne,playerTwo);
		gamesession.init();
		

}
}


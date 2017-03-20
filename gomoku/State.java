package gomoku;

import java.util.ArrayList;
import java.util.Arrays;

public class State {
	private Player playerNow;
	private ArrayList<Move> move;
	public int[][] board;
	
	public State(Player playerNow)
	{
		this.playerNow = playerNow;
		move = new ArrayList<Move>();
		board = new int[GUI.numOfRow][GUI.numOfCol];
	}
	
	public void addChess(Chess chess)
	{
		int x = chess.getcRow();
		int y = chess.getcCol();
		
		board[x][y] = playerNow.getNum();
		move.add(new Move(chess,playerNow));
		playerNow = playerNow.getOpponent(); //đánh xong thì người chơi
	}
	
	public void removeChess(Chess chess)
	{
		int x = chess.getcRow();
		int y = chess.getcCol();
		
		board[x][y] = 0;
		move.remove(move.size() - 1);
		playerNow = playerNow.getOpponent();
	}
	
	public boolean isNotOut(int x, int y)
	{
		if (x < 0 || x >= GUI.numOfRow) 
			return false;
		if (y < 0 || y >= GUI.numOfCol)
			return false;
		return true;
	}
	
	public ArrayList<Chess> getSuccessors()
	{
		ArrayList<Chess> successors = new ArrayList<Chess>();
		boolean[][] mark = new boolean[GUI.numOfRow][GUI.numOfCol];
		for (int i = 0;i < GUI.numOfRow; i++)
		{
			Arrays.fill(mark[i], false);
		}
		for (Move moves : move)
		{
			int x = moves.getChess().getcRow();
			int y = moves.getChess().getcCol();
			for (int i = -2; i <= 2; i++)
			{
				for (int j = -2; j <= 2; j++)
				{
					int X = x + i;
					int Y = y + j;
					if (isNotOut(X,Y) && board[X][Y] == 0)
					{
						mark[X][Y] = true;
					}
				}				
			}		
		}
		for (int x = 0; x < GUI.numOfRow; x++)
		{
			for (int y = 0; y < GUI.numOfCol; y++)
			{
				if (mark[x][y])
				{
					successors.add(new Chess(x,y));
				}
			}
		}
		return successors;
	}

	public Player getPlayerNow() {
		return playerNow;
	}

	public void setPlayerNow(Player playerNow) {
		this.playerNow = playerNow;
	}

	public ArrayList<Move> getMove() {
		return move;
	}

	public void setMove(ArrayList<Move> move) {
		this.move = move;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}
}

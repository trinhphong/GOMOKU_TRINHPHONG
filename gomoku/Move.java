package gomoku;

public class Move {
	private Chess chess;
	private Player player;
	
	public Move(Chess chess, Player player)
	{
		this.chess = chess;
		this.player = player;
	}
	
	public Chess getChess()
	{
		return chess;
	}
	
	public void setChess(Chess chess)
	{
		this.chess = chess;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}	
}

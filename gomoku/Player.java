package gomoku;

public enum Player {
	X("X",1), O("O",2);
	
	private String type;
	private int num;
	//Khởi tạo Player
	Player(String type, int num)
	{
		this.type = type;
		this.num = num;
	}
	
	//Chuyển lượt đi
	public Player getOpponent()
	{
		if (this == X)
		{
			return Player.O;
		}
		return Player.X;
	}

	public String getType() {
		return type;
	}

	public int getNum() {
		return num;
	}
}

package gomoku;

public class Chess {
	private int cRow;
	private int cCol;
	private boolean isMark;
	
	public Chess()
	{
		cRow = 0;
		cCol = 0;
		isMark = false;
	}
	
	public Chess(int x, int y)
	{
		this.cRow = x;
		this.cCol = y;
		this.isMark = true;
	}
	
	public boolean isMark()
	{
		return isMark;
	}
	
	public void setMark(boolean isMark)
	{
		this.isMark = isMark;
	}
	
	public int getcRow()
	{
		return cRow;
	}
	
	public int getcCol()
	{
		return cCol;
	}
}

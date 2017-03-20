package gomoku;

public class Evaluate {
	public static int numberFive, numberStraightFour, numberFour, numberThree, numberBrokenThree, numberTwo, numberSingle;
	public static int numberDia;
	public static int[][] hashRow, hashColum, hashDia;
	public static boolean isHaveThreat = false;

	public static void init() {
		hashRow = new int[GUI.numOfRow][GUI.numOfCol];
		hashColum = new int[GUI.numOfCol][GUI.numOfRow];
		hashDia = new int[2*(GUI.numOfRow + GUI.numOfCol)][GUI.numOfRow + GUI.numOfCol];
		numberFive = 0;
		numberStraightFour = 0;
		numberFour = 0;
		numberThree = 0;
		numberBrokenThree = 0;
		numberTwo = 0;
		numberSingle = 0;
		numberDia = 0;
	}
	
	public static void hashRow(int[][] board)
	{
		for (int i = 0; i < board.length; i++)
		{
			hashRow[i][0] = board[i][0];
			for(int j = 1; j < board[i].length; j++)
			{
				hashRow[i][j] = hashRow[i][j - 1] * 3 + board[i][j];
			}
		}
	}
	
	public static void hashCol(int[][] board)
	{
		for (int i = 0; i < board.length; i++) {
			hashColum[i][0] = board[0][i];
			for (int j = 1; j < board[i].length; j++) {
				hashColum[i][j] = hashColum[i][j - 1] * 3 + board[j][i];
			}
		}
	}
	
	public static void hashDia1(int[][] board)
	{
		for (int i = 0; i < board.length - 1; i++) {
			hashDia[numberDia][0] = board[i][0];
			for (int j = 1; j < board.length - i; j++) {
				hashDia[numberDia][j] = hashDia[numberDia][j - 1] * 3 + board[i + j][j];
			}
			numberDia++;
		}
	}
	
	public static void hashDia2(int[][] board)
	{
		for (int i = 0; i < board.length - 1; i++) {
			hashDia[numberDia][0] = board[i][board.length - 1];
			for (int j = 1; j < board.length - i; j++)  {
				hashDia[numberDia][j] = hashDia[numberDia][j - 1] * 3 + board[i + j][board.length - 1 - j];
			}
			numberDia++;
		}
	}
	
	public static void hashDia3(int[][] board)
	{
		for (int j = 1; j < board[0].length - 1; j++) {
			hashDia[numberDia][0] = board[0][j];
			for (int i = 1; i < board[0].length - j; i++) {
				hashDia[numberDia][i] = hashDia[numberDia][i - 1] * 3 + board[i][i + j];
			}
			numberDia++;
		}
	}
	
	public static void hashDia4(int[][] board)
	{
		for (int j =1; j < board[0].length - 1; j++) {
			hashDia[numberDia][0] = board[0][board.length - 1 - j];
			for (int i = 1; i < board.length - j; i++) {
				hashDia[numberDia][i] = hashDia[numberDia][i - 1] * 3 + board[i][board.length - 1 - (i + j)];
			}
			numberDia++;
		}
	}

	public static void hashBoard(int[][] board) {
		hashRow(board);
		hashCol(board);
		hashDia1(board);
		hashDia2(board);
		hashDia3(board);
		hashDia4(board);
	}
	
	public static void checkThreatFive(int value, int index)
	{
		int threatFive = 121;
		if (index == 0)
		{
			if (value == threatFive)
			{
				numberFive++;
				setHaveThreat(true);
			}
		}
		else if (index == 1)
		{
			if (value == threatFive * 2)
			{
				numberFive++;
				setHaveThreat(true);
			}
		}
	}

	public static void checkThreatThree(int value, int index) 
	{	
		int threatThree = 39;
		if (index == 0)
		{
			if (value == threatThree)
			{
				numberThree++;
				setHaveThreat(true);
			}
		}
		else if (index == 1)
		{
			if (value == threatThree * 2)
			{
				numberThree++;
				setHaveThreat(true);
			}
		}
	}
	
	public static void checkThreatStraightFour(int value, int index)
	{
		int threatStraightFour = 120;
		if (index == 0)
		{
			if (value == threatStraightFour)
			{
				numberStraightFour++;
				setHaveThreat(true);
			}
		}
		else if (index == 1)
		{
			if (value == threatStraightFour * 2)
			{
				numberStraightFour++;
				setHaveThreat(true);
			}
		}
	}
	
	public static void checkThreatFour(int value, int index)
	{
		if (index == 0)
		{
			if (value == 606 || value == 122)
			{
				numberFour++;
				setHaveThreat(true);
			}
		}
		else if (index == 1)
		{
			if (value == 483 || value == 241)
			{
				numberFour++;
				setHaveThreat(true);
			}
		}
	}

	public static void checkThreatBrokenThree(int value, int index) {	
		int threatBrokenThree1 = 111;
		int threatBrokenThree2 = 93;
		if (index == 0)
		{
			if (value == threatBrokenThree1 || value == threatBrokenThree2)
			{
				numberBrokenThree++;
				setHaveThreat(true);
			}
		}
		else if (index == 1)
		{
			if (value == threatBrokenThree1 * 2 || value == threatBrokenThree2 * 2)
			{
				numberBrokenThree++;
				setHaveThreat(true);
			}
		}
	}

	public static void checkThreatTwo(int value, int index) {
		int threatTwo1 = 4;
		int threatTwo2 = 10;
		if (index == 0)
		{
			if (value == threatTwo1 || value == threatTwo2)
			{
				numberTwo++;
				setHaveThreat(true);
			}
		}
		else if (index == 1)
		{
			if (value == threatTwo1 * 2 || value == threatTwo2 * 2)
			{
				numberTwo++;
				setHaveThreat(true);
			}
		}
	}

	public static void computeThreatOnRowOrColum(int[][] board, int index, int[][] hash) {
		for (int i = 0; i < board.length; i++) {
			if (hash[i][board[i].length - 1] == 0)
				continue;
			setHaveThreat(false);
			int value;
			for (int j = 0; j < board[i].length - 4; j++) {
				if (j == 0)
					value = hash[i][j + 4];
				else
					value = hash[i][j + 4] - hash[i][j - 1] * (int)(Math.pow(3, 5));
				checkThreatFive(value, index);
				checkThreatThree(value, index);
				if (isHaveThreat())
					break;
			}
			if (isHaveThreat())
				continue;
			for (int j = 0; j < board[i].length - 5; j++) {
				if (j == 0)
					value = hash[i][j + 5];
				else
					value = hash[i][j + 5] - hash[i][j - 1] * (int)(Math.pow(3, 6));
				checkThreatStraightFour(value, index);
				checkThreatFour(value, index);
				checkThreatThree(value, index);
				checkThreatBrokenThree(value, index);
				if (isHaveThreat())
					break;
			}
			if (isHaveThreat())
				continue;
			for (int j = 0; j < board[i].length - 1; j++) {
				if (j == 0)
					value = hash[i][j + 1];
				else
					value = hash[i][j + 1] - hash[i][j - 1] * (int)(Math.pow(3, 2));
				checkThreatTwo(value, index);
				if (isHaveThreat())
					break;
			}
		}
	}

	public static void computeThreatOnDiagonal(int[][] board, int index, int count, int first, int last) {
		for (int i = first; i < last; i++) {
			count++;
			if (count == numberDia)
				break;
			setHaveThreat(false);
			int value;
			if (hashDia[count][board.length - i - 1] == 0)
				continue;
			for (int j = 0; j < board[i].length - i - 4; j++) {
				if (j == 0)
					value = hashDia[count][j + 4];
				else
					value = hashDia[count][j + 4] - hashDia[count][j - 1] * (int)(Math.pow(3, 5));
				checkThreatThree(value, index);
				checkThreatFive(value, index);
				if (isHaveThreat())
					break;
			}
			if (isHaveThreat())
				continue;
			for (int j = 0; j < board[i].length - i - 5; j++) {
				if (j == 0)
					value = hashDia[count][j + 5];
				else
					value = hashDia[count][j + 5] - hashDia[count][j - 1] * (int)(Math.pow(3, 6));
				checkThreatStraightFour(value, index);
				checkThreatFour(value, index);
				checkThreatThree(value, index);
				checkThreatBrokenThree(value, index);
				if (isHaveThreat())
					break;
			}
			if (isHaveThreat())
				continue;
			for (int j = 0; j < board[i].length - i - 1; j++) {
				if (j == 0)
					value = hashDia[count][j + 1];
				else
					value = hashDia[count][j + 1] - hashDia[count][j - 1] * (int)(Math.pow(3, 2));
				checkThreatTwo(value, index);
				if (isHaveThreat())
					break;
			}
		}
	}

	public static void computeThreatSingle(int[][] board, int index) {
		if (numberFive + numberStraightFour + numberFour + numberThree + numberBrokenThree + numberTwo == 0)
			for (int i = 0; i < board.length; i++)
				for (int j = 0; j < board[i].length; j++)
					if (board[i][j] == index)
						numberSingle++;
	}

	public static boolean isHaveThreat() {
		return isHaveThreat;
	}

	public static void setHaveThreat(boolean isHaveThreat) {
		Evaluate.isHaveThreat = isHaveThreat;
	}

	public static int evaluate(State state, Player player) {
		int index = player.getNum() - 1;
		int[][] board = state.getBoard();
		init();
		hashBoard(board);
		computeThreatOnRowOrColum(board, index, hashRow);
		computeThreatOnRowOrColum(board, index, hashColum);
		computeThreatOnDiagonal(board, index, -1, 0, GUI.numOfRow - 1);
		computeThreatOnDiagonal(board, index, GUI.numOfRow - 2, 0, GUI.numOfCol - 1);
		computeThreatOnDiagonal(board, index, 2*GUI.numOfRow - 3, 1, GUI.numOfCol - 1);
		computeThreatOnDiagonal(board, index, 3*GUI.numOfRow - 5, 1, GUI.numOfCol - 1);
		int heuristics = numberFive * 10000 + numberStraightFour * 3000 + numberFour * 30
				+ numberThree * 12 + numberBrokenThree * 12	+ numberTwo * 6;
		return heuristics;
	}
}
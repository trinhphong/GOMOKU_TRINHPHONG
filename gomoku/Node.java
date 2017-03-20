package gomoku;

public class Node implements Comparable<Node>{
	
	private Chess chess;
	private int heuristicValue;
	
	public Node(Chess chess, int hValue)
	{
		this.chess = chess;
		this.heuristicValue = hValue;
	}
		
	public Chess getChess() {
		return chess;
	}

	public void setChess(Chess chess) {
		this.chess = chess;
	}

	public int getHeuristicValue() {
		return heuristicValue;
	}

	public void setHeuristicValue(int heuristicValue) {
		this.heuristicValue = heuristicValue;
	}

	@Override
	public int compareTo(Node node) {
		// TODO Auto-generated method stub
		return node.getHeuristicValue() - this.heuristicValue;
	}
	
}

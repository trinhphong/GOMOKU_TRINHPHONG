package gomoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class View extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int numOfRow = 11;
	public static final int numOfCol = 11;
	public static final int boxSize = 50;
	public static final int depth = 3;
	private State state;
	private JButton[][] table;
	private int winner;
	
	public View()
	{
		state = new State(Player.X);
		winner = -1;
		state.addChess(new Chess(numOfRow/2,numOfCol/2));
		table = new JButton[numOfRow][numOfCol];
		initComponent();
	}
	
	private void initComponent(){
		this.setSize(700, 700);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		displayBoard();
		this.setLayout(null);
		this.setVisible(true);
	}
	
	private void displayBoard()
	{
		int x = 0;
		int y = 0;
		for (int i = 0; i < numOfRow; i++)
		{
			for (int j = 0; j < numOfCol; j++)
			{
				table[i][j] = new JButton();
				table[i][j].setSize(boxSize, boxSize);
				table[i][j].setLocation(x, y);
				this.add(table[i][j]);
				table[i][j].setActionCommand(i + " " + j);
				table[i][j].addActionListener(this);
				x += boxSize;
			}
			x = 0;
			y += boxSize;
		}		
		ArrayList<Move> move = state.getMove();
		
		for (int i = 0; i < move.size(); i++)
		{
			Move nextMove = move.get(i);
			click(nextMove.getChess().getcRow(),nextMove.getChess().getcCol(), nextMove.getPlayer());
		}
	}
	
	public void markChess()
	{
		ArrayList<Move> move = state.getMove();
		
		for (int i = 0; i < move.size(); i++)
		{
			Move nextMove = move.get(i);
			click(nextMove.getChess().getcRow(),nextMove.getChess().getcCol(), nextMove.getPlayer());
		}
	}
	
	public void click(int x, int y, Player player){	
		table[x][y].setEnabled(false);
		table[x][y].setText(player.getType());
		if (player.getType() == "X")
		{
			table[x][y].setBackground(Color.blue);
		}
		else if (player.getType() == "O")
		{
			table[x][y].setBackground(Color.red);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String pos[] = e.getActionCommand().split(" ");
		int x = Integer.parseInt(pos[0]);
		int y = Integer.parseInt(pos[1]);
		Chess chess = new Chess(x,y);
		if (state.getPlayerNow() == Player.O)
		{
			int[][] board = state.getBoard();
			if(board[x][y] == 0)
			{
				state.addChess(chess);				
				Node node = AI.minimax(state, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
				if (node.getChess() == null)
				{
					if (Evaluate.evaluate(state, Player.O) >= 10000)
					{
						winner = 2;
						JOptionPane.showMessageDialog(null, "HUMAN" + " win");
					}
					else
					{
						winner = 0;
						JOptionPane.showMessageDialog(null, "DRAW");
					}
				}
				else
				{
					state.addChess(node.getChess());
					if (Evaluate.evaluate(state, Player.X) >= 10000)
					{
						winner = 1;
						JOptionPane.showMessageDialog(null, "AI" + " win");
					}
				}
			}
		}
		this.markChess();
	}
}

package gomoku;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI extends JPanel implements MouseListener, MouseMotionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int numOfRow = 11;
	public static final int numOfCol = 11;
	public static final int boxSize = 50;
	public static final int depth = 3;
	private State state;
	private int winner;
	private int drawRow;
	private int drawCol;
	private static boolean win = false;
	
	public GUI()
	{
		state = new State(Player.X);
		winner = -1;
		state.addChess(new Chess(numOfRow/2,numOfCol/2));
		addMouseMotionListener(this);
		addMouseListener(this);
	}
	
	@Override
	public void paintComponent(Graphics gra)
	{
		super.paintComponent(gra);
		for (int i = 0; i <= numOfRow; i++)
		{
			gra.drawLine(boxSize * i, 0, boxSize * i + 0, boxSize * numOfCol);
		}
		for (int j = 0; j <= numOfCol ; j++)
		{
			gra.drawLine(0, boxSize * j, boxSize * numOfRow, boxSize * j );
		}
		
		ArrayList<Move> move = state.getMove();
		for (int i = 0; i < move.size(); i++)
		{
			Move nextMove = move.get(i);
			//gra.drawImage(nextMove.getPlayer().getImg(), boxSize * nextMove.getChess().getcRow() , boxSize * nextMove.getChess().getcCol(), this);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = (e.getX() - 0) / boxSize;
		int y = (e.getY() - 0) / boxSize;
		drawRow = x;
		drawCol = y;
		repaint();
	}
	
	public boolean isFinal(int winner)
	{
		if (winner == 1 || winner == 2 || winner == 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		int x = (e.getX()) / boxSize;
		int y = (e.getY()) / boxSize;
		Chess chess = new Chess(x,y);
		if (state.getPlayerNow() == Player.O && e.getButton() == 1)
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
		repaint();		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

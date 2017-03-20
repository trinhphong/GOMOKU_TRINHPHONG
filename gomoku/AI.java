package gomoku;

import java.util.ArrayList;
import java.util.Collections;

public class AI {		
		public static Node minimax(State state, int alpha, int beta, int depth){
			ArrayList<Chess> newBoard = state.getSuccessors();
			if (depth == 0 || newBoard.size() == 0 || Evaluate.evaluate(state, Player.X) >= 10000 || Evaluate.evaluate(state, Player.O) >= 10000){
				int x = Evaluate.evaluate(state, Player.X);
				int o = Evaluate.evaluate(state, Player.O);
				if (state.getPlayerNow() == Player.X){x = x * 2;}
				else{o = o * 2;}
				return new Node(null, x - o);
			}
			ArrayList<Chess> successors = state.getSuccessors();
			ArrayList<Node> nextMoves = new ArrayList<Node>();
			for (Chess chess: successors){
				state.addChess(chess);
				int x = Evaluate.evaluate(state, state.getPlayerNow().getOpponent());
				nextMoves.add(new Node(chess,x));
				state.removeChess(chess);
			}
			Collections.sort(nextMoves);
			Chess bestChess = null;
			if (state.getPlayerNow() == Player.X){
				int min = Integer.MIN_VALUE;								
				for (Node node : nextMoves){
					Chess chess = node.getChess();
					state.addChess(chess);
					Node tryMove = minimax(state, alpha, beta, depth - 1);
					if (min < tryMove.getHeuristicValue()){
						min = tryMove.getHeuristicValue();
						bestChess = chess;
					}
					if (min >= beta){
						state.removeChess(chess);
						return new Node(bestChess, min);
					}
					alpha = Math.max(alpha, min);
					state.removeChess(chess);
				}
				return new Node(bestChess, min);
			}
			else{
				int max = Integer.MAX_VALUE;
				for (Node node : nextMoves){
					Chess chess = node.getChess();				
					state.addChess(chess);
					Node tryMove = minimax(state, alpha, beta, depth - 1);
					if (max > tryMove.getHeuristicValue()){
						max = tryMove.getHeuristicValue();
						bestChess = chess;
					}
					if (max <= alpha){
						state.removeChess(chess);
						return new Node(bestChess, max);
					}
					beta = Math.min(beta, max);
					state.removeChess(chess);
				}
				return new Node(bestChess, max);
			}
		}
}

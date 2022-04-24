package src.linesofaction;

import java.util.ArrayList;
import java.util.Random;

import static src.linesofaction.Heuristic.*;
import static src.linesofaction.Rules.*;

public class Minimax {
    public double minimax(int[][] board, int depth, int turn) {
        String bestplay;

        if(depth == 0){
            double piecePosition = piecePosition(board, turn), area = area(board, turn);
            Random random = new Random();
            double val = 1000*piecePosition + 2000*area;
            val = val + random.nextInt(50);
            return val;
        }

        int[][] childrenBoard = new int[8][8];
        resetToParent(board, childrenBoard);

        //get all possible moves as children
        ArrayList<int[][]> children = new ArrayList<>();

        for(int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (board[i][j] == turn) {
                    for (int k = 0; k < 8; k++) {
                        for (int l = 0; l < 8; l++) {
                            if(IsLegal(GetString(i,j), GetString(k,l))){
                                //moves piece
                                childrenBoard[k][l] = childrenBoard[i][j];
                                childrenBoard[i][j] = -1;
                                //adds this board state to children
                                children.add(childrenBoard);
                                //resets back to parent state
                                resetToParent(board, childrenBoard);
                            }
                        }
                    }
                }
            }
        }

        if(turn == 1) {
            double maxEval = Double.MIN_VALUE;
            for (int i = 0; i < children.size(); i++) {
                double eval = minimax(children.get(i), depth - 1, 0);
                maxEval = Math.max(maxEval, eval);
            }
            return maxEval;
        }
        else {
            double minEval = Double.MAX_VALUE;
            for (int i=0; i < children.size(); i++){
                double eval = minimax(children.get(i), depth - 1, 1);
                minEval = Math.min(minEval, eval);
            }
            return minEval;
        }
    }

    // resets children board back to parent state
    public void resetToParent(int[][] board, int[][] childrenBoard){
        for(int i = 0; i < 8; i++) {
            System.arraycopy(board[i], 0, childrenBoard[i], 0, 8);
        }
    }

}
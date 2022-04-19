package src.linesofaction;

import java.util.Random;

public class Minimax {
    public double minimax(int[][] board, int depth, double alpha, double beta, int turn){
        if(depth == 0) {
            double piecePosition = Heuristic.piecePosition(board, turn);
            double area = Heuristic.area(board, turn);
            Random random = new Random();
            double val = (double)(1000*piecePosition + 2000*area);
            val = val + random.nextInt(50);
            return val;
        }

        int[][] childrenBoard = new int[8][8];

        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                childrenBoard[i][j] = board[i][j];
            }
        }

        /*
        (...) to be finished, will possibly need 4 separate methods on Rules to validate vertical, horizontal, and diagonal
              in order to calculate children
        */
    }
}

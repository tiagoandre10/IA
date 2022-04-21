package src.linesofaction;

import java.util.Random;

import static src.linesofaction.Heuristic.*;

public class Minimax {
    public double minimax(int[][] board, int depth, double alpha, double beta, int turn) {
        if(depth == 0) {
            double piecePosition = piecePosition(board, turn), area = area(board, turn);
            Random random = new Random();
            double val = 1000*piecePosition + 2000*area;
            val = val + random.nextInt(50);
            return val;
        }

        int[][] childrenBoard = new int[8][8];

        for(int i = 0; i < 8; i++) {
            System.arraycopy(board[i], 0, childrenBoard[i], 0, 8);
        }

        return 0;
    }
}
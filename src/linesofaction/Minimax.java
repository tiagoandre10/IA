package src.linesofaction;

import java.util.ArrayList;
import java.util.Random;

import static src.linesofaction.Heuristic.*;
import static src.linesofaction.Rules.*;

public class Minimax {
    public static ArrayList<Double> cost;
    public static ArrayList<int[][]> boardStateTracker;
    public static int[][] finalMove;
    public static int totalDepth;

    public Minimax(int depth){
        cost = new ArrayList<>();
        boardStateTracker = new ArrayList<>();
        finalMove = new int[8][8];
        totalDepth = depth;
    }

    public double minimax(int[][] board, int depth, int turn) {
        if(depth == 0 || GameOver(board) != -1){
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

        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(turn == 1) {
                    if (board[i][j] > 0 && board[i][j] < 13) {
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                if(IsLegal(GetString(i,j), GetString(k,l))) {
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
                else if(turn == 2) {
                    if (board[i][j] > 12 && board[i][j] < 25) {
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                if(IsLegal(GetString(i,j), GetString(k,l))) {
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
        }

        if(turn == 1) {
            double maxEval = Double.MIN_VALUE;
            for (int i = 0; i < children.size(); i++) {
                double eval = minimax(children.get(i), depth - 1, 2);
                if(depth == totalDepth) {
                    cost.add(eval);
                    boardStateTracker.add(children.get(i));
                }
                maxEval = Math.max(maxEval, eval);
            }
            if(depth == totalDepth) {
                int prob = 0;
                for (int i = 0; i < cost.size(); i++) {
                    if (cost.get(i) == maxEval) {
                        finalMove = boardStateTracker.get(i);
                        prob=1;
                        break;
                    }
                }
                if(prob == 0){
                    System.out.println("Problem");
                }

                cost.clear();
                boardStateTracker.clear();

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

    public int[][] bestMove(int[][] board, int depth, int turn){
        double costOfMove = minimax(board,depth,1);
        return finalMove;
    }

    // resets children board back to parent state
    public void resetToParent(int[][] board, int[][] childrenBoard){
        for(int i = 0; i < 8; i++) {
            System.arraycopy(board[i], 0, childrenBoard[i], 0, 8);
        }
    }

}
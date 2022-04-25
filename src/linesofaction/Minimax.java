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
            double eval = 2*area + 5*piecePosition;
            eval = eval + random.nextInt(500);
            //System.out.println(eval);
            return eval;
        }

        int[][] childrenBoard = new int[8][8];
        resetToParent(board, childrenBoard);

        //get all possible moves as children
        ArrayList<int[][]> children = new ArrayList<>();

        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(turn == 1 || turn == -1) {
                    if (childrenBoard[i][j] > 12 && childrenBoard[i][j] < 25) {
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                if(IsLegal(GetString(i,j), GetString(k,l)) && k != i && j != l) {
                                    //moves piece
                                    childrenBoard[k][l] = childrenBoard[i][j];
                                    childrenBoard[i][j] = -1;
                                    //adds this board state to children
                                    int[][] helper = new int[8][8];
                                    for(int m=0; m<8; m++){
                                        for(int n=0; n<8; n++){
                                            helper[m][n] = childrenBoard[m][n];
                                        }
                                    }
                                    children.add(helper);
                                    //resets back to parent state
                                    resetToParent(board, childrenBoard);
                                }
                            }
                        }
                    }
                }
                else if(turn == 2 || turn == 0) {
                    if (childrenBoard[i][j] > 0 && childrenBoard[i][j] < 13) {
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                if(IsLegal(GetString(i,j), GetString(k,l))&& k != i && j != l) {
                                    //moves piece
                                    childrenBoard[k][l] = childrenBoard[i][j];
                                    childrenBoard[i][j] = -1;
                                    //adds this board state to children
                                    int[][] helper = new int[8][8];
                                    for(int m=0; m<8; m++){
                                        for(int n=0; n<8; n++){
                                            helper[m][n] = childrenBoard[m][n];
                                        }
                                    }
                                    children.add(helper);
                                    //resets back to parent state
                                    resetToParent(board, childrenBoard);
                                    /*
                                    System.out.println("    A   B   C   D   E   F   G   H");
                                    for(int m = 0; m < 8; m++) {
                                        System.out.print((char)(56-i) + " | ");
                                        for(int n = 0; n < 8; n++) {
                                            if(childrenBoard[m][n] > 12 && childrenBoard[m][n] < 25)
                                                System.out.print('X' + " | ");

                                            else if(childrenBoard[m][n] > 0  && childrenBoard[m][n] < 13)
                                                System.out.print('0' + " | ");

                                            else
                                                System.out.print(' ' + " | ");
                                        }
                                        System.out.println();
                                    }
                                    */
                                }
                            }
                        }
                    }
                }
            }
        }
        //player1 maximizado (black)
        if(turn == 1) {
            double maxEval = Double.MIN_VALUE;
            for (int i = 0; i < children.size(); i++) {
                double eval = minimax(children.get(i), depth - 1, 0);
                if(depth == totalDepth) {
                    cost.add(eval);
                    boardStateTracker.add(children.get(i));
                }
                maxEval = Math.max(maxEval, eval);
            }
            if(depth == totalDepth) {
                for (int i = 0; i < cost.size(); i++) {
                    if (cost.get(i) == maxEval) {
                        finalMove = boardStateTracker.get(i);
                        break;
                    }
                }

                cost.clear();
                boardStateTracker.clear();

            }
            return maxEval;
        }
        //player2 minimizado (white)
        else if(turn == 0){
            double minEval = Double.MAX_VALUE;
            for (int i=0; i < children.size(); i++){
                double eval = minimax(children.get(i), depth - 1, 1);
                minEval = Math.min(minEval, eval);
            }
            return minEval;
        }
        //player2 maximizado (white)
        if(turn == 2) {
            double maxEval = Double.MIN_VALUE;
            for (int i = 0; i < children.size(); i++) {
                double eval = minimax(children.get(i), depth - 1, -1);
                if(depth == totalDepth) {
                    cost.add(eval);
                    boardStateTracker.add(children.get(i));
                }
                maxEval = Math.max(maxEval, eval);
            }
            if(depth == totalDepth) {
                for (int i = 0; i < cost.size(); i++) {
                    if (cost.get(i) == maxEval) {
                        finalMove = boardStateTracker.get(i);
                        break;
                    }
                }

                cost.clear();
                boardStateTracker.clear();

            }
            return maxEval;
        }
        //player1 minimizado (black)
        else {
            double minEval = Double.MAX_VALUE;
            for (int i=0; i < children.size(); i++){
                double eval = minimax(children.get(i), depth - 1, 2);
                minEval = Math.min(minEval, eval);
            }
            return minEval;
        }
    }

    public int[][] bestMove(int[][] board, int depth, int turn){
        double costOfMove = minimax(board,depth,turn);
        return finalMove;
    }

    // resets children board back to parent state
    public void resetToParent(int[][] board, int[][] childrenBoard){
        for(int i = 0; i < 8; i++) {
            System.arraycopy(board[i], 0, childrenBoard[i], 0, 8);
        }
    }

}
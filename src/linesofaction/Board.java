package src.linesofaction;

public class Board {
    static char[] pieces = new char[]{'X', 'O', ' '};
    static int[][] board = new int[8][8];
    static int whiteCounter = 1;
    static int blackCounter = 13;

    static void initialize() {
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++) {
                if((i == 0 || i == 7) && (j >= 1 && j <= 6)) {
                    board[i][j] = 1;
                    //board[i][j] = blackCounter;
                    //blackCounter++;
                }

                else if((j == 0 || j == 7) && (i >= 1 && i <= 6)) {
                    board[i][j] = 0;
                    //board[i][j] = whiteCounter;
                    //whiteCounter++;
                }

                else
                    board[i][j] = -1;
            }
    }

    static void status() {
        System.out.println("    A   B   C   D   E   F   G   H");
        for(int i = 0; i < 8; i++) {
            System.out.print((char)(56-i) + " | ");
            for(int j = 0; j < 8; j++) {
                if(board[i][j] == 1)
                    System.out.print(pieces[0] + " | ");

                else if(board[i][j] == 0)
                    System.out.print(pieces[1] + " | ");

                else
                    System.out.print(pieces[2] + " | ");
            }
            System.out.println();
        }
    }
}

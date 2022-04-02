public class Game {
    char white_piece = 'O', black_piece = 'X', blank = ' ';
    char[][] board = new char[8][8];

    Game() {
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++) {
                if((i == 0 || i == 7) && (j >= 1 && j <= 6))
                    board[i][j] = black_piece;
                else if((j == 0 || j == 7) && (i >= 1 && i <= 6))
                    board[i][j] = white_piece;
                else
                    board[i][j] = blank;
            }
    }

    public void PrintBoard() {
        System.out.println("    A   B   C   D   E   F   G   H");
        for(int i = 0; i < 8; i++) {
            System.out.print((char)(56-i) + " | ");
            for(int j = 0; j < 8; j++)
                System.out.print(board[i][j] + " | ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Game temp = new Game();
        temp.PrintBoard();
    }
}

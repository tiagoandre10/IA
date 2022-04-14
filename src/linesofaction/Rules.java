package linesofaction;

public class Rules {
    static int GetColumn(String move) {
        return move.toUpperCase().charAt(0) - 'A' + 1;
    }

    static int GetRow(String move) {
        return move.toUpperCase().charAt(1) - '0';
    }

    static boolean InBounds(int col, int row) {
        return 1 <= col && col <= 8 && 1 <= row && row <= 8;
    }

    static boolean IsLegal(String move, String play) {
        int possible_move = PiecesCountAlong(move, play);
        int old_col = GetColumn(move), old_row = GetRow(play);
        int new_col = GetColumn(move), new_row = GetRow(play);
        int required_move = Math.max(Math.abs(new_col - old_col), Math.abs(new_row - old_row));

        return ((possible_move == required_move) && !blocked(move, play));
    }

    static int PiecesCountAlong(String move, String play) {
        int dCol = GetColumn(play) - GetColumn(move);
        int dRow = GetRow(move) - GetRow(play);
        int col = GetColumn(move), row = GetRow(move), count = 0;

        for(Direction dir : Direction.values()) {
            for(int i = 1; i <= 7; i++) {
                if(dCol == dir.dir_col * i && dRow == dir.dir_row * i) {
                    col--;
                    row--;

                    if(dir == Direction.NOWHERE)
                        return (!(Board.board[row][col] == -1) ? 1 : 0);

                    else if(dir == Direction.S || dir == Direction.N) {
                        for(int j = 0; j < 8; j++)
                            if(!(Board.board[j][col] == -1))
                                count++;
                    }

                    else if (dir == Direction.E || dir == Direction.W) {
                        for(int j = 0; j < 8; j++)
                            if(!(Board.board[row][j] == -1))
                                count++;
                     }

                    else if (dir == Direction.NE || dir == Direction.SW) {
                        for(int j = -Math.min(col, row); j <= Math.min(7 - col, 7 - row); j++)
                            if(!(Board.board[row + j][col + j] == -1))
                                count++;
                    }

                    else if (dir == Direction.NW || dir == Direction.SE) {
                        for (int j = -Math.min(col, 7 - row); j <= Math.min(7 - col, row); j++)
                            if (!(Board.board[row -j][col + j] == -1))
                                count++;
                    }
                }
            }
        }

        return count;
    }

    static boolean blocked(String move, String play) {
        if(move.equals(play))
            return true;

        int old_col = GetColumn(move), new_col = GetColumn(play);
        int old_row = GetRow(move), new_row = GetRow(play);
        int dCol = new_col - old_col, dRow = new_row - old_row;

        for (Direction dir : Direction.values()) {
            for (int i = 1; i <= 7; i++) {
                if (dCol == dir.dir_col * i && dRow == dir.dir_row * i) {
                    for (int j = 1; j < i; j++) {
                        if (Board.board[new_row][new_col] == 1 && Board.board[old_row + dir.dir_row * j][old_col + dir.dir_col * j] == 0)
                            return true;

                        if (Board.board[new_row][new_col] == 0 && Board.board[old_row + dir.dir_row * j][old_col + dir.dir_col * j] == 1)
                            return true;
                    }

                    return false;
                }
            }
        }

        return true;
    }

    static boolean GameOver() {return false;}
}

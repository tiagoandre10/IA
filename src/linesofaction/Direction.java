package linesofaction;

enum Direction {
    NOWHERE(0, 0),
    N(0, 1),
    NE(1, 1),
    E(1, 0),
    SE(1, -1),
    S(0, -1),
    SW(-1, -1),
    W(-1, 0),
    NW(-1, 1);

    Direction(int col, int row) {
        dir_col = col;
        dir_row = row;
    }

    final int dir_col, dir_row;
}

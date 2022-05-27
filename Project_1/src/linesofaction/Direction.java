package Projeto_1.src.linesofaction;

enum Direction {
    NOWHERE(0, 0),
    N(1, 0),
    S(-1, 0),
    E(0, 1),
    W(0, -1),
    NE(1, 1),
    NW(1, -1),
    SE(-1, 1),
    SW(-1, -1);

    Direction(int _row, int _col) {
        row = _row;
        col = _col;
    }

    final int row, col;
}

## Group members 57_2F
- André Cruz (up201705768@edu.fc.up.pt)
- Mafalda Magalhães (up201707066@edu.fe.up.pt)
- Tiago André (up201606796@edu.fc.up.pt)

## Lines Of Action
### Objective
- Lines of Action (or LOA) is an abstract strategy board game for two players. The objective of the game is to bring all of one's checkers together into a contiguous body so that they are connected vertically, horizontally or diagonally.

### Rules
- Players alternate moves, with Black having the first move;
- Checkers move horizontally, vertically, or diagonally;
- A checker moves exactly as many spaces as there are checkers (both friendly and enemy) on the line in which it is moving;
- You may jump over your own pieces;
- You may not jump over your opponents pieces, but you can capture them by landing on them.

### Example of a winning board
![Winning Board](/docs/LOA.png)

### Game
- The game can be compiled and executed in a IDE such as IntelliJ, so there's no need to compile and execute the project manually from the terminal;
- When you run the game, you will be welcomed by the start screen;
```bash
%----------  LINES OF ACTION  ----------%
  
1. Player vs Player
2. Player vs Computer (Minimax Alpha Beta Pruning Algorithm)
3. Computer vs Computer (Minimax Algorithm)
4. Computer vs Computer (Minimax Alpha Beta Pruning Algorithm)
5. Exit
  
Option: 
```
- If you select the option 1 it starts the traditional way to play the game. That is, two players sharing the same terminal window and playing against each other;

```bash
    A   B   C   D   E   F   G   H
8 |   | X | X | X | X | X | X |   | 
7 | O |   |   |   |   |   |   | O | 
6 | O |   |   |   |   |   |   | O | 
5 | O |   |   |   |   |   |   | O | 
4 | O |   |   |   |   |   |   | O | 
3 | O |   |   |   |   |   |   | O | 
2 | O |   |   |   |   |   |   | O | 
1 |   | X | X | X | X | X | X |   | 

(PLAYER 1)

Piece to move: B1
Piece destination: B3
Play: B1 -> B3

    A   B   C   D   E   F   G   H
8 |   | X | X | X | X | X | X |   | 
7 | O |   |   |   |   |   |   | O | 
6 | O |   |   |   |   |   |   | O | 
5 | O |   |   |   |   |   |   | O | 
4 | O |   |   |   |   |   |   | O | 
3 | O | X |   |   |   |   |   | O | 
2 | O |   |   |   |   |   |   | O | 
1 |   |   | X | X | X | X | X |   | 

(PLAYER 2)

Piece to move: A7
Piece destination: C7
Play: A7 -> C7

    A   B   C   D   E   F   G   H
8 |   | X | X | X | X | X | X |   | 
7 |   |   | O |   |   |   |   | O | 
6 | O |   |   |   |   |   |   | O | 
5 | O |   |   |   |   |   |   | O | 
4 | O |   |   |   |   |   |   | O | 
3 | O | X |   |   |   |   |   | O | 
2 | O |   |   |   |   |   |   | O | 
1 |   |   | X | X | X | X | X |   | 
```

- If the option selected is 2 the game is played against the IA with three different levels of difficulty as the follow;

```bash
1. Easy
2. Medium
3. Hard
  
Option:
```
- In the easy mode the IA uses the board area as main heuristic function to be applied in the Minimax Alpha Beta Pruning algorithm;
- In the medium mode besides the board area, is also used a value table simulating the board with negative and positive values, where the purpose is to force the IA to play in the middle of the board to get a better chance of winning the game;
- In the hard mode besides of what is used in the previous mode, is added the total pieces connected and the total opponent pieces, so the IA uses more is "brain" before making a move;

- If the option selected is 3 or 4 the game is exclusivly played by the IA with the Minimax algorithm and his variation for a better perfomance (Minimax Alpha Beta Pruning algorithm), with the heuristics functions used previous;

- Each character defines the available piece on the board:
  - 'X' is the black pieces and consecutively Player 1;
  - 'O' is the white pieces and consecutively Player 2.

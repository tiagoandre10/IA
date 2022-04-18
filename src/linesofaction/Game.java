package src.linesofaction;

import java.util.*;
import src.linesofaction.graph.*;
import static src.linesofaction.Board.*;
import static src.linesofaction.Rules.*;

public class Game {
  static List<Graph> _graphs = new ArrayList<>();
  static List<Node> _nodes = new ArrayList<>();
  static int _blackPieces = 12;
  static int _whitePieces = 12;
  static int _totalGraphs = 4;

  Game() {
    initialize();
    //startGraphs();
  }

  public static List<Graph> getGraphs(){
    return _graphs;
  }

  public static int getBlackPieces(){
    return _blackPieces;
  }

  public static int getWhitePieces(){
    return _whitePieces;
  }

  public void startGraphs(){
    //White pieces
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    Node node4 = new Node(4);
    Node node5 = new Node(5);
    Node node6 = new Node(6);
    Node node7 = new Node(7);
    Node node8 = new Node(8);
    Node node9 = new Node(9);
    Node node10 = new Node(10);
    Node node11 = new Node(11);
    Node node12 = new Node(12);

    _nodes.add(node1);
    _nodes.add(node2);
    _nodes.add(node3);
    _nodes.add(node4);
    _nodes.add(node5);
    _nodes.add(node6);
    _nodes.add(node7);
    _nodes.add(node8);
    _nodes.add(node9);
    _nodes.add(node10);
    _nodes.add(node11);
    _nodes.add(node12);

    //Black pieces
    Node node13 = new Node(13);
    Node node14 = new Node(14);
    Node node15 = new Node(15);
    Node node16 = new Node(16);
    Node node17 = new Node(17);
    Node node18 = new Node(18);
    Node node19 = new Node(19);
    Node node20 = new Node(20);
    Node node21 = new Node(21);
    Node node22 = new Node(22);
    Node node23 = new Node(23);
    Node node24 = new Node(24);

    _nodes.add(node13);
    _nodes.add(node14);
    _nodes.add(node15);
    _nodes.add(node16);
    _nodes.add(node17);
    _nodes.add(node18);
    _nodes.add(node19);
    _nodes.add(node20);
    _nodes.add(node21);
    _nodes.add(node22);
    _nodes.add(node23);
    _nodes.add(node24);

    //White team starts with 2 graphs
    Graph g1 = new Graph(6, 1);
    //Initial left side of the board with white pieces
    g1.addNode(node1);
    g1.addNode(node3);
    g1.addNode(node5);
    g1.addNode(node7);
    g1.addNode(node9);
    g1.addNode(node11);

    node1.setGraph(1);
    node3.setGraph(1);
    node5.setGraph(1);
    node7.setGraph(1);
    node9.setGraph(1);
    node11.setGraph(1);

    Graph g2 = new Graph(6, 2);
    //Initial right side of the board with white pieces
    g2.addNode(node2);
    g2.addNode(node4);
    g2.addNode(node6);
    g2.addNode(node8);
    g2.addNode(node10);
    g2.addNode(node12);

    node2.setGraph(2);
    node4.setGraph(2);
    node6.setGraph(2);
    node8.setGraph(2);
    node10.setGraph(2);
    node12.setGraph(2);

    //Black team starts with 2 graphs
    Graph g3 = new Graph(6, 3);
    //Initial top side of the board with black pieces
    g3.addNode(node13);
    g3.addNode(node14);
    g3.addNode(node15);
    g3.addNode(node16);
    g3.addNode(node17);
    g3.addNode(node18);

    node13.setGraph(3);
    node14.setGraph(3);
    node15.setGraph(3);
    node16.setGraph(3);
    node17.setGraph(3);
    node18.setGraph(3);

    Graph g4 = new Graph(6, 4);
    //Initial right side of the board with white pieces
    g2.addNode(node19);
    g2.addNode(node20);
    g2.addNode(node21);
    g2.addNode(node22);
    g2.addNode(node23);
    g2.addNode(node24);

    node19.setGraph(4);
    node20.setGraph(4);
    node21.setGraph(4);
    node22.setGraph(4);
    node23.setGraph(4);
    node24.setGraph(4);

    _graphs.add(g1);
    _graphs.add(g2);
    _graphs.add(g3);
    _graphs.add(g4);
  }

  public static void readjustGraphs(String move){
    int column = GetColumn(move);
    int row = GetRow(move);
    int piece = board[row][column];

    //Removes the piece from the graph from which it belonged
    for (Graph value : _graphs)
      value.contains(piece);

    //Checks if there is any other pieces in its surroundings
    // DUVIDA -> player 1 é o branco?????????????????????????????????????????????????????
    List<Integer> surroundings = new ArrayList<>();
    int aux;

    if(0 < piece && piece < 12)
      aux = 1;

    else
      aux = 2;

    //Up move
    if( (0 < board[row+1][column] && board[row+1][column] < 13 && aux == 1) || (12 < board[row+1][column] && board[row+1][column] < 25 && aux == 2))
      surroundings.add(board[row+1][column]);

    //Down move
    if( (0 < board[row-1][column] && board[row-1][column] < 13 && aux == 1) || (12 < board[row-1][column] && board[row-1][column] < 25 && aux == 2))
      surroundings.add(board[row-1][column]);

    //Diagonal down right move
    if( (0 < board[row-1][column+1] && board[row-1][column+1] < 13 && aux == 1) || (12 < board[row-1][column+1] && board[row-1][column+1] < 25 && aux == 2))
      surroundings.add(board[row-1][column+1]);

    //Diagonal down left move
    if( (0 < board[row-1][column-1] && board[row-1][column-1] < 13 && aux == 1) || (12 < board[row-1][column-1] && board[row-1][column-1] < 25 && aux == 2))
      surroundings.add(board[row-1][column-1]);

    //Left move
    if( (0 < board[row][column-1] && board[row][column-1] < 13 && aux == 1) || (12 < board[row][column-1] && board[row][column-1] < 25 && aux == 2))
      surroundings.add(board[row][column-1]);

    //Right move
    if( (0 < board[row][column+1] && board[row][column+1] < 13 && aux == 1) || (12 < board[row][column+1] && board[row][column+1] < 25 && aux == 2))
      surroundings.add(board[row][column+1]);

    //Diagonal up right move
    if( (0 < board[row+1][column+1] && board[row+1][column+1] < 13 && aux == 1) || (12 < board[row+1][column+1] && board[row+1][column+1] < 25 && aux == 2))
      surroundings.add(board[row+1][column+1]);

    //Diagonal up left move
    if( (0 < board[row+1][column-1] && board[row+1][column-1] < 13 && aux ==1) || (12 < board[row+1][column-1] && board[row+1][column-1] < 25 && aux ==2))
      surroundings.add(board[row+1][column-1]);

    if(surroundings.size() == 0) {
      _totalGraphs++;
      Graph newGraph = new Graph(1, _totalGraphs);
      Node node = _nodes.get(piece-1);
      newGraph.addNode(node);
      _graphs.add(newGraph);
    }

    else {
      int firstSurrounding = surroundings.get(0);
      Node node = _nodes.get(firstSurrounding-1);
      int graph = node.getGraph();

      //Puts the piece in the graph of the first surrounding
      _graphs.get(graph-1).addNode(_nodes.get(piece-1));
      surroundings.remove(surroundings.get(0));

      //Joins the other surroundings in the same graph
      while(surroundings.size() != 0) {
        int nextSurrounding = surroundings.get(0);
        Node nextNode = _nodes.get(nextSurrounding-1);
        int nextGraph = nextNode.getGraph();

        for ( int i = 0; i < _graphs.get(nextGraph-1).getNodes().size(); i++) {
          //Adds the node to the previous graph
          _graphs.get(graph-1).addNode(nextNode);
          //Removes the node from the current graph
          _graphs.get(nextGraph-1).removeNode(nextNode);
        }

        surroundings.remove(surroundings.get(0));
      }
    }
  }

  static void PlayerPlayer() {
      Scanner stdin = new Scanner(System.in);
      int player = 1;
      String move, play;
      boolean gameFinished = false;

      System.out.print("\033[H\033[2J");
      System.out.flush();

      new Game();

      while(!gameFinished) {
          status();
          System.out.print("\n(PLAYER " + player + ")\n\n" +
                  "Piece to move: ");
          move = stdin.next();
          System.out.print("Piece destination: ");
          play = stdin.next();

          while(!IsLegal(move, play)) {
              System.out.print("""

                      Invalid play! Try again!
                      Piece to move:\s""");
              move = stdin.next();
              System.out.print("Piece destination: ");
              play = stdin.next();
          }

          board[GetRow(play)][GetColumn(play)] = board[GetRow(move)][GetColumn(move)];
          board[GetRow(move)][GetColumn(move)] = -1;
          //readjustGraphs(move);
          //gameFinished = GameOver();

          System.out.println("Play: " + move.toUpperCase() + " -> " + play.toUpperCase());

          if(player == 1)
              player = 2;

          else
              player = 1;
      }
  }

  static void PlayerComputer() {
      System.out.print("\033[H\033[2J");
      System.out.flush();
  }

  static void ComputerComputer() {
      System.out.print("\033[H\033[2J");
      System.out.flush();
  }
}

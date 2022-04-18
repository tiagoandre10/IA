package src.linesofaction.graph;

public class Node {
  int _value;
  int _team;
  int _graph;

  public Node(int value)  {
    _value = value;
    if (value <= 12){
      _team = 1;
    }
    else{
      _team = 2;
    }
  }

  public Node(int value, int graph) {
    _value = value;
    _graph = graph;
  }

  public void setGraph(int graph){
    _graph = graph;
  }

  public int getValue(){
    return _value;
  }

  public int getGraph(){
    return _graph;
  }
}

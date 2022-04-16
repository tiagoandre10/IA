package src.linesofaction.graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
  int _value;
  int _team;
  int _graph;
  List<Edge> _adj = new ArrayList<>();

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

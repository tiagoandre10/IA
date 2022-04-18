package src.linesofaction.graph;

import java.util.*;

public class Graph {
  int _id;
  int _team;
  int _numberOfNodes;
  List<Node> _nodes = new ArrayList<>();

  public Graph(int numberOfNodes, int id) {
    _id = id;
    _numberOfNodes= numberOfNodes;
  }

  public List<Node> getNodes(){
    return _nodes;
  }

  public Node getFirstNode(){
    return _nodes.get(0);
  }

  public void addNode(Node node){
    _nodes.add(node);
  }

  public void removeNode(Node node){
    _nodes.remove(node);
  }

  public void contains(int nodeId) {
    for(int i = 0; i < _nodes.size(); i++)
      if(_nodes.get(i).getValue() == nodeId)
        _nodes.remove(_nodes.get(i));
  }

<<<<<<< HEAD
=======
  public void addEdge(Edge edge) {
    //to complete
  }
>>>>>>> db64e951791c4905f60fef8a19be1eb1a60ef88e
}

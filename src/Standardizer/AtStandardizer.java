package Standardizer;

import java.util.List;

import Node.Node;

public class AtStandardizer extends AbstractStandardizer{
    @Override
    protected boolean standardizeImplementation(Node node) {
        if (node.getToken().equals("@")) {
            // TODO: check number of children = 3
            List<Node> children = node.getChildren();
            Node E1 = children.get(0);
            Node N = children.get(1);
            Node E2 = children.get(2);
            node.setToken("gamma");
            // Node gamma = new Node("gamma");

            return true;
        } else {
            return false;
        }
    }
    
}
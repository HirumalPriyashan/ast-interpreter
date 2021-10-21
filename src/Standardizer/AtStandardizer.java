package Standardizer;

import java.util.ArrayList;
import java.util.Arrays;
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
            Node gamma = new Node("gamma", node, node.getDepth() + 1);
            N.setDepth(gamma.getDepth() + 1);
            N.setParent(gamma);
            E1.setDepth(gamma.getDepth() + 1);
            E1.setParent(gamma);
            gamma.setChildren(new ArrayList<Node>(Arrays.asList(N, E1)));
            node.setChildren(new ArrayList<Node>(Arrays.asList(gamma, E2)));
            return true;
        } else {
            return false;
        }
    }
    
}

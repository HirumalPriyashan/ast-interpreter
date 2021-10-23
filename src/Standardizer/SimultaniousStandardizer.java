package Standardizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Node.Node;

public class SimultaniousStandardizer extends AbstractStandardizer{
    @Override
    protected boolean standardizeImplementation(Node node) {
        if (node.getToken().equals("and")) {
            // TODO: number of children > 2, each children must be = nodes
            List<Node> children = node.getChildren();
            Node comma = new Node(",");
            Node tau = new Node("tau");
            node.setToken("=");
            node.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(comma, tau)));
            for (Node child : children) {
                // TODO: check each child got two children
                Node X = child.getChildren().get(0);
                Node E = child.getChildren().get(1);
                comma.addChildWithDepth(X);
                tau.addChildWithDepth(E);
            }
            new Standardizer().standardize(tau);
        }
        return false;
    }
    
}

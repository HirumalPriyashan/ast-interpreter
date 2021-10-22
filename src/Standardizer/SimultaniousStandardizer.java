package Standardizer;

import java.util.ArrayList;
import java.util.Arrays;

import Node.Node;

public class SimultaniousStandardizer extends AbstractStandardizer{
    @Override
    protected boolean standardizeImplementation(Node node) {
        if (node.getToken().equals("and")) {
            // TODO: number of children > 2, each children must be = nodes
            Node comma = new Node(",", node, node.getDepth() + 1);
            Node tau = new Node("tau", node, node.getDepth() + 1);
            node.setToken("=");
            for (Node child : node.getChildren()) {
                // TODO: check each child got two children
                Node X = child.getChildren().get(0);
                Node E = child.getChildren().get(1);
                comma.addChild(X);
                X.increaseDepthBy(comma.getDepth() + 1 - X.getDepth());
                tau.addChild(E);
                E.increaseDepthBy(tau.getDepth() + 1 - E.getDepth());
            }
            new Standardizer().standardize(tau);
            node.setChildren(new ArrayList<Node>(Arrays.asList(comma, tau)));
        }
        return false;
    }
    
}

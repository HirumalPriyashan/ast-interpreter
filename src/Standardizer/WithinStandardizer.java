package Standardizer;

import java.util.ArrayList;
import java.util.Arrays;

import Node.Node;

public class WithinStandardizer extends AbstractStandardizer {
    @Override
    protected boolean standardizeImplementation(Node node) {
        if (node.getToken().equals("within")) {
            Node equal1 = node.getChildren().get(0);
            Node X1 = equal1.getChildren().get(0);
            Node E1 = equal1.getChildren().get(1);
            Node equal2 = node.getChildren().get(1);
            Node X2 = equal2.getChildren().get(0);
            Node E2 = equal2.getChildren().get(1);
            node.setToken("=");
            Node gamma = new Node("gamma", node, node.getDepth() + 1);
            Node lambda = new Node("lambda", gamma, gamma.getDepth() + 1);
            lambda.setChildren(new ArrayList<Node>(Arrays.asList(X1, E2)));
            X1.setParent(lambda);
            X1.increaseDepthBy(lambda.getDepth() - X1.getDepth() + 1);
            E2.setParent(lambda);
            E2.increaseDepthBy(lambda.getDepth() - E2.getDepth() + 1);
            gamma.setChildren(new ArrayList<Node>(Arrays.asList(lambda, E1)));
            E1.setParent(gamma);
            E1.increaseDepthBy(gamma.getDepth() - E1.getDepth() + 1);
            node.setChildren(new ArrayList<Node>(Arrays.asList(X2, gamma)));
            X2.setParent(node);
            X2.increaseDepthBy(node.getDepth() - X2.getDepth() + 1);
            return true;
        } else {
            return false;
        }
    }
    
}

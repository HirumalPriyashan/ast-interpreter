package Standardizer;

import java.util.ArrayList;
import java.util.Arrays;

import Node.Node;

public class RecStandardizer extends AbstractStandardizer{
    @Override
    protected boolean standardizeImplementation(Node node) {
        if (node.getToken().equals("rec")) {
            // TODO check child is = and = have 2 children
            Node equal = node.getChildren().get(0);
            Node X = equal.getChildren().get(0);
            Node E = equal.getChildren().get(1);
            node.setToken("=");
            Node gamma = new Node("gamma");
            node.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(X, gamma)));
            Node lambda = new Node("lambda");
            gamma.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(new Node("Ystar"), lambda)));
            lambda.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(X, E)));
            return true;
        }
        return false;
    }
    
}

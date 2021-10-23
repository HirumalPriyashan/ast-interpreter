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
            Node gamma = new Node("gamma");
            Node lambda = new Node("lambda");
            node.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(X2, gamma)));
            gamma.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(lambda, E1)));
            lambda.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(X1, E2)));
            return true;
        } else {
            return false;
        }
    }
    
}

package Standardizer;

import java.util.ArrayList;
import java.util.Arrays;

import Node.Node;

public class LetStandardizer extends AbstractStandardizer {
    @Override
    protected boolean standardizeImplementation(Node node) {
        if (node.getToken().equals("let")) {
            Node equal = node.getChildren().get(0);
            Node P = node.getChildren().get(1);
            Node X = equal.getChildren().get(0);
            Node E = equal.getChildren().get(1);
            // restructure
            node.setToken("gamma");
            Node lambda = new Node("lambda");
            node.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(lambda, E)));
            lambda.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(X, P)));
            new Standardizer().standardize(lambda);
            return true;
        } else {
            return false;
        }
    }
}
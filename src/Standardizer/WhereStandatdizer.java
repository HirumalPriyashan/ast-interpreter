package Standardizer;

import java.util.ArrayList;
import java.util.Arrays;

import Node.Node;

public class WhereStandatdizer extends AbstractStandardizer {
    @Override
    protected boolean standardizeImplementation(Node node) {
        if (node.getToken().equals("where")) {
            Node equal = node.getChildren().get(1);
            Node P = node.getChildren().get(0);
            Node X = equal.getChildren().get(0);
            Node E = equal.getChildren().get(1);
            // restructure
            node.setToken("gamma");
            Node lambda = new Node("lambda", node, node.getDepth() + 1);
            P.increaseDepthBy(1);
            X.setParent(lambda);
            P.setParent(lambda);
            lambda.setChildren(new ArrayList<Node>(Arrays.asList(X, P)));
            E.increaseDepthBy(-1);
            E.setParent(node);
            node.setChildren(new ArrayList<Node>(Arrays.asList(lambda, E)));
            return true;
        } else {
            return false;
        }
    } 
}
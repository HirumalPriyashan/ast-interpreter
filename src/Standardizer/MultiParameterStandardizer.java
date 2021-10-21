package Standardizer;

import java.util.ArrayList;
import java.util.List;

import Node.Node;

public class MultiParameterStandardizer extends AbstractStandardizer {
    @Override
    protected boolean standardizeImplementation(Node node) {
        if (node.getToken().equals("lambda")) {
            if (node.getChildren().size() > 2) {
                List<Node> children = node.getChildren();
                node.setChildren(new ArrayList<Node>());
                int numberOfChildren = children.size();
                Node E  = children.get(numberOfChildren - 1);
                Node currentLambda = node;
                for (Node child : children.subList(0, numberOfChildren - 1)) {
                    currentLambda.addChild(child);
                    if (children.indexOf(child) != numberOfChildren - 2) {
                        Node lambda = new Node("lambda");
                        currentLambda.addChild(lambda);
                        currentLambda = lambda;
                    }
                }
                currentLambda.addChild(E);
            }
            return true;
        } else {
            return false;
        }
    }
    
}

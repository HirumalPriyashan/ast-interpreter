package Standardizer;

import java.util.List;

import Node.Node;

public class MultiParameterStandardizer extends AbstractStandardizer {
    @Override
    protected boolean standardizeImplementation(Node node) {
        if (node.getToken().equals("lambda") && node.getChildren().size() > 2) {
                List<Node> children = node.getChildren();
                // restructure
                node.removeChildren();
                int numberOfChildren = children.size();
                Node E  = children.get(numberOfChildren - 1);
                Node currentLambda = node;
                for (Node child : children.subList(0, numberOfChildren - 1)) {
                    currentLambda.addChildWithDepth(child);
                    if (children.indexOf(child) != numberOfChildren - 2) {
                        Node lambda = new Node("lambda");
                        currentLambda.addChildWithDepth(lambda);
                        currentLambda = lambda;
                    }
                }
                currentLambda.addChildWithDepth(E);
            return true;
        }
        return false;
    }
    
}

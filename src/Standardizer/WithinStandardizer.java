package Standardizer;

import java.util.ArrayList;
import java.util.Arrays;

import Node.Node;

/**
 * Standardizer for 'within'
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class WithinStandardizer extends AbstractStandardizer {
    /**
     * Applies the standardizing 'within' gamma
     * 
     *         within     =>          =
     *          /   \                / \
     *         =     =              X2 gamma
     *        / \   / \                 / \
     *       X1 E1 X2 E2            lambda E1
     *                               / \
     *                              X1 E2 
     *
     * @param node node to be standardize 
     * @return  <b>true</b> if handled by one of the handlers
     *          otherwise <b>false</b>
     */
    @Override
    protected boolean standardizeImplementation(Node node) {
        if (
            node.getToken().equals("within")
            && node.getChildren().size() == 2
            && node.getChildren().get(0).getToken().equals("=")
            && node.getChildren().get(1).getToken().equals("=")
        ) {
            Node equal1 = node.getChildren().get(0);
            Node X1 = equal1.getChildren().get(0);
            Node E1 = equal1.getChildren().get(1);
            Node equal2 = node.getChildren().get(1);
            Node X2 = equal2.getChildren().get(0);
            Node E2 = equal2.getChildren().get(1);
            // restructure
            Node gamma = new Node("gamma");
            Node lambda = new Node("lambda");
            node.setToken("=");
            node.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(X2, gamma)));
            gamma.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(lambda, E1)));
            lambda.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(X1, E2)));
            return true;
        }
        return false;
    }
    
}

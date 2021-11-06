package Standardizer;

import java.util.ArrayList;
import java.util.Arrays;

import Node.Node;

/**
 * Standardizer for where
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class WhereStandatdizer extends AbstractStandardizer {
    /**
     * Applies the standardizing the 'where' gamma
     * 
     *      where              gamma
     *       / \               /  \ 
     *      P   =       =>   lambda E 
     *         / \            / \ 
     *        X   E          X   P 
     * 
     * @param node node to be standardize 
     * @return  <b>true</b> if handled by one of the handlers
     *          otherwise <b>false</b>
     */
    @Override
    protected boolean standardizeImplementation(Node node) {
        if (node.getToken().equals("where")) {
            Node equal = node.getChildren().get(1);
            Node P = node.getChildren().get(0);
            Node X = equal.getChildren().get(0);
            Node E = equal.getChildren().get(1);
            // restructure
            node.setToken("gamma");
            Node lambda = new Node("lambda");
            lambda.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(X, P)));
            node.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(lambda, E)));
            return true;
        }
        return false;
    } 
}
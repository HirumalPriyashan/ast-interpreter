package Standardizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Node.Node;

/**
 * Standardizer for '@' operations
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class AtStandardizer extends AbstractStandardizer{
    /**
     * Applies the standardizing the '@' Operator gamma
     * 
     *      @      =>   gamma
     *    / | \          / \
     *   E1 N E2      gamma E2
     *                 / \
     *                N  E1
     * 
     * @param node node to be standardize 
     * @return  <b>true</b> if handled by one of the handlers
     *          otherwise <b>false</b>
     */
    @Override
    protected boolean standardizeImplementation(Node node) {
        if (
            node.getToken().equals("@")
            && node.getChildren().size() == 3
        ) {
            List<Node> children = node.getChildren();
            Node E1 = children.get(0);
            Node N = children.get(1);
            Node E2 = children.get(2);
            // restructure
            node.setToken("gamma");
            Node gamma = new Node("gamma");
            node.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(gamma, E2)));
            gamma.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(N, E1)));
            return true;
        }
        return false;
    }
    
}

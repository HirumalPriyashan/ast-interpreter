package Standardizer;

import java.util.ArrayList;
import java.util.Arrays;

import Node.Node;

/**
 * Standardizer for 'rec'
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class RecStandardizer extends AbstractStandardizer{
    /**
     * Applies the standardizing the 'rec' gamma
     * 
     *      rec     =>       =
     *       |              / \
     *       =             X  gamma
     *      / \                /  \
     *     X   E            Ystar lambda
     *                              / \
     *                             X   E
     * 
     * @param node node to be standardize 
     * @return  <b>true</b> if handled by one of the handlers
     *          otherwise <b>false</b>
     */
    @Override
    protected boolean standardizeImplementation(Node node) {
        if (
            node.getToken().equals("rec")
            && node.getChildren().size() == 1
            && node.getChildren().get(0).getToken().equals("=")
            && node.getChildren().get(0).getChildren().size() == 2
        ) {
            Node equal = node.getChildren().get(0);
            Node X = equal.getChildren().get(0);
            Node XCopy = new Node(X.getToken());
            Node E = equal.getChildren().get(1);
            // restructure
            node.setToken("=");
            Node gamma = new Node("gamma");
            node.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(X, gamma)));
            Node lambda = new Node("lambda");
            gamma.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(new Node("Ystar"), lambda)));
            lambda.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(XCopy, E)));
            return true;
        }
        return false;
    }
    
}

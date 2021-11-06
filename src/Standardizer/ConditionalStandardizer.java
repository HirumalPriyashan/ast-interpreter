package Standardizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Node.Node;

/**
 * Standardizer for Conditional Operator
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class ConditionalStandardizer extends AbstractStandardizer{
    /**
     * Applies the standardizing the Conditional Operator gamma
     * 
     *      →        =>         gamma
     *    / | \                 /  \
     *   B  T  F            gamma  nil
     *                     /    \
     *                  gamma    lambda
     *                  /  \         / \
     *               gamma lambda   ()  F
     *                / \     / \
     *              Cond B   ()  T
     * 
     * @param node node to be standardize 
     * @return  <b>true</b> if handled by one of the handlers
     *          otherwise <b>false</b>
     */
    @Override
    protected boolean standardizeImplementation(Node node) {
        if (
            node.getToken().equals("->")
            && node.getChildren().size() == 3
        ) {
            List<Node> children = node.getChildren();
            Node B = children.get(0);
            Node T = children.get(1);
            Node F = children.get(2);
            // restructure
            node.setToken("gamma");
            Node gammaInner = new Node("gamma");
            Node gammaInnerSecond = new Node("gamma");
            Node gammaInnerMost = new Node("gamma");
            Node lambda1 = new Node("lambda");
            Node lambda2 = new Node("lambda");
            node.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(gammaInner, new Node("nil"))));
            gammaInner.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(gammaInnerSecond, lambda1)));
            lambda1.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(new Node("()"), F)));
            gammaInnerSecond.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(gammaInnerMost, lambda2)));
            gammaInnerMost.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(new Node("Cond"), B)));
            lambda2.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(new Node("()"), T)));
            return true;            
        }
        return false;
    }
    
}

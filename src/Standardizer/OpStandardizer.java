package Standardizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Node.Node;

/**
 * Standardizer for Unary and Binary Operators
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class OpStandardizer extends AbstractStandardizer{
    /**
     * Applies the standardizing the Unary and Binary Operators gamma
     * 
     *      Uop    =>    gamma
     *       |           /  \
     *       E          Uop  E 
     *
     *       Op    =>    gamma
     *      / \          /  \
     *     E1  E2     gamma  E2
     *                 / \
     *                Op  E1
     *
     * 
     * @param node node to be standardize 
     * @return  <b>true</b> if handled by one of the handlers
     *          otherwise <b>false</b>
     */
    @Override
    protected boolean standardizeImplementation(Node node) {
        List<String> Uops = new ArrayList<String>(Arrays.asList("not", "neg"));
        List<String> Ops = new ArrayList<String>(Arrays.asList("aug", "or", "&", "+", "-", "*", "/", "**", "eq","ne", "ls", "le","gr", "ge", "<", ">", "<=", ">="));
        if (
            Ops.contains(node.getToken())
            && node.getChildren().size() == 2
        ) {
            List<Node> children = node.getChildren();
            Node E1 = children.get(0);
            Node E2 = children.get(1);
            // restructure
            Node gamma1 = new Node("gamma");
            gamma1.setDepth(node.getDepth());
            Node gamma2 = new Node("gamma");
            Node Op = new Node(node.getToken());
            gamma1.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(gamma2, E2)));
            gamma2.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(Op, E1)));
            node.setToken(gamma1.getToken());
            node.setDepth(gamma1.getDepth());
            node.setChildren(gamma1.getChildren());
            return true;
        } else if(
            Uops.contains(node.getToken())
            && node.getChildren().size() == 1
        ) {
            Node gamma = new Node("gamma");
            gamma.setDepth(node.getDepth());
            Node Uop = new Node(node.getToken());
            Node E = node.getChildren().get(0);
            gamma.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(Uop, E)));
            node.setToken(gamma.getToken());
            node.setDepth(gamma.getDepth());
            node.setChildren(gamma.getChildren());
            return true;
        }
        return false;
    }
    
}

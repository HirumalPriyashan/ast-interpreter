package Standardizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Node.Node;

/**
 * Standardizer for Tuples
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class TupleStandardizer extends AbstractStandardizer {
    /**
     * Applies the standardizing Tuple gamma
     * 
     *           tau     =>     ++gamma
     *            |              /   \
     *           E++           gamma  E
     *                          / \
     *                        aug .nil 
     *
     * @param node node to be standardize 
     * @return  <b>true</b> if handled by one of the handlers
     *          otherwise <b>false</b>
     */
    @Override
    protected boolean standardizeImplementation(Node node) {
        if (
            node.getToken().equals("tau")
            && node.getChildren().size() > 1
        ) {
            int curretDepth = node.getDepth();
            List<Node> Es = node.getChildren();
            // restructure
            Node nil = new Node("nil");
            nil.setDepth(Es.size()*2 + curretDepth);
            Node appendiNode = nil;
            for (Node e : Es) {
                Node gamma = new Node("gamma");
                Node upperGamma = new Node("gamma");
                upperGamma.setDepth(appendiNode.getDepth() - 2);
                upperGamma.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(gamma,e)));
                Node aug = new Node("aug");
                gamma.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(aug)));
                gamma.addChild(appendiNode);
                appendiNode = upperGamma;
            }
            node.setToken("gamma");
            node.setChildren(appendiNode.getChildren());
            return true;
        }
        return false;
    }
    
}

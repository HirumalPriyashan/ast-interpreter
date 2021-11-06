package Standardizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Node.Node;

/**
 * Standardizer for Simultaneous Definitions
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class SimultaniousStandardizer extends AbstractStandardizer{
    Standardizer standardizer;

    public SimultaniousStandardizer(Standardizer standardizer){
        this.standardizer = standardizer;
    }

    /**
     * Applies the standardizing Simultaneous Definitions gamma
     * 
     *           and      =>        =
     *            |                / \
     *           =++              ,  tau
     *           / \              |   |
     *          X  E             X++ E++
     *
     * @param node node to be standardize 
     * @return  <b>true</b> if handled by one of the handlers
     *          otherwise <b>false</b>
     */
    @Override
    protected boolean standardizeImplementation(Node node) {
        if (
            node.getToken().equals("and")
            && node.getChildren().size() > 1
            && node.getChildren().get(0).getToken().equals("=")
        ) {
            List<Node> children = node.getChildren();
            Node comma = new Node(",");
            Node tau = new Node("tau");
            node.setToken("=");
            node.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(comma, tau)));
            for (Node child : children) {
                if (child.getChildren().size() == 2) {
                    Node X = child.getChildren().get(0);
                    Node E = child.getChildren().get(1);
                    comma.addChildWithDepth(X);
                    tau.addChildWithDepth(E);
                }
            }
            this.standardizer.standardize(tau);
        }
        return false;
    }
    
}

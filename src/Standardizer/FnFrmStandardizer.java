package Standardizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Node.Node;

/**
 * Standardizer for 'fcn_form'
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class FnFrmStandardizer extends AbstractStandardizer {
    /**
     * Applies the standardizing the 'fcn_form' gamma
     * 
     *      fcn_form             =
     *        / | \             / \
     *       P  V+ E     =>    P +lambda
     *                             / \
     *                            V  .E 
     * 
     * @param node node to be standardize 
     * @return  <b>true</b> if handled by one of the handlers
     *          otherwise <b>false</b>
     */
    @Override
    protected boolean standardizeImplementation(Node node) {
        if (
            node.getToken().equals("function_form")
            && node.getChildren().size() > 1
        ) {
            Node P = node.getChildren().get(0);
            List<Node> Vs = node.getChildren().subList(1, node.getChildren().size() - 1);
            Node E = node.getChildren().get(node.getChildren().size() - 1);
            // restructure
            node.setToken("=");
            Node rootLambda = new Node("lambda");
            node.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(P, rootLambda)));
            Node currentLambda = rootLambda;
            for (Node v : Vs) {
                currentLambda.addChildWithDepth(v);
                if (Vs.indexOf(v) == Vs.size() - 1) {
                    currentLambda.addChildWithDepth(E);
                } else {
                    Node nextLamda = new Node("lambda");
                    currentLambda.addChildWithDepth(nextLamda);
                    currentLambda = nextLamda;
                }
            }
            return true;            
        }
        return false;
    }
    
}

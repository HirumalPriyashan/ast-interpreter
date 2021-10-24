package Standardizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Node.Node;

public class FnFrmStandardizer extends AbstractStandardizer {
    @Override
    protected boolean standardizeImplementation(Node node) {
        if (node.getToken().equals("function_form")) {
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

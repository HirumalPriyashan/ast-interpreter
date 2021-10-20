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
            Node rootLambda = new Node("lambda", node, node.getDepth() + 1);
            Node currentLambda = rootLambda;
            for (Node v : Vs) {
                currentLambda.addChild(v);
                v.setParent(currentLambda);
                v.setDepth(currentLambda.getDepth() + 1);
                if (Vs.indexOf(v) == Vs.size() - 1) {
                    E.increaseDepthBy(Vs.size());
                    currentLambda.addChild(E);
                    E.setParent(currentLambda);
                } else {
                    Node nextLamda = new Node("lambda", currentLambda, currentLambda.getDepth() + 1);
                    currentLambda.addChild(nextLamda);
                    currentLambda = nextLamda;
                }
            }
            node.setChildren(new ArrayList<Node>(Arrays.asList(P,rootLambda)));
            return true;            
        } else {
            return false;
        }
    }
    
}

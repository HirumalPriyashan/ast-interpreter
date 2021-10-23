package Standardizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Node.Node;

public class ConditionalStandardizer extends AbstractStandardizer{
    @Override
    protected boolean standardizeImplementation(Node node) {
        if (node.getToken().equals("->")) {
            // TODO: check node has 3 children
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

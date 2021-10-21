package Standardizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Node.Node;

public class TupleStandardizer extends AbstractStandardizer {
    @Override
    protected boolean standardizeImplementation(Node node) {
        if (node.getToken().equals("tau")) {
            int curretDepth = node.getDepth();
            List<Node> Es = node.getChildren();
            // restructure
            Node nil = new Node("nil", null, Es.size()*2 + curretDepth);
            Node appendiNode = nil;
            for (Node e : Es) {
                Node gamma = new Node("gamma", null, appendiNode.getDepth() - 1);
                Node aug = new Node("aug", gamma, appendiNode.getDepth());
                gamma.setChildren(new ArrayList<Node>(Arrays.asList(aug,appendiNode)));
                appendiNode.setParent(gamma);
                Node upperGamma = new Node("gamma", null, appendiNode.getDepth() - 2);
                upperGamma.setChildren(new ArrayList<Node>(Arrays.asList(gamma,e)));
                e.setParent(upperGamma);
                e.increaseDepthBy(upperGamma.getDepth() + 1 - e.getDepth());
                gamma.setParent(upperGamma);
                appendiNode = upperGamma;
            }
            node.setToken("gamma");
            node.setChildren(appendiNode.getChildren());
            return true;
        } else {
            return false;
        }
    }
    
}
// gamma
// .<ID:print>
// .tau
// ..<INT:1>
// ..tau
// ...<INT:2>
// ...<INT:3>
// ..<INT:4>
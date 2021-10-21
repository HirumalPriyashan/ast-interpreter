package Standardizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Node.Node;

public class OpStandardizer extends AbstractStandardizer{
    @Override
    protected boolean standardizeImplementation(Node node) {
        List<String> Uops = new ArrayList<String>(Arrays.asList("not", "neg"));
        // TODO:add other operations
        List<String> Ops = new ArrayList<String>(Arrays.asList("aug", "or", "&", "+", "-", "/", "**", "gr"));
        if (Ops.contains(node.getToken())) {
            List<Node> children = node.getChildren();
            Node E1 = children.get(0);
            Node E2 = children.get(1);
            Node gamma1 = new Node("gamma", node.getParent(), node.getDepth());
            Node gamma2 = new Node("gamma", gamma1, gamma1.getDepth() + 1);
            Node Op = new Node(node.getToken(), gamma2, gamma2.getDepth() + 1);
            E1.setParent(gamma2);
            E1.increaseDepthBy(gamma2.getDepth() + 1 - E1.getDepth());
            gamma2.setChildren(new ArrayList<Node>(Arrays.asList(Op, E1)));
            gamma1.setChildren(new ArrayList<Node>(Arrays.asList(gamma2, E2)));
            // if (node.getParent() != null) {
            //     node.getParent().getChildren().set(node.getParent().getChildren().indexOf(node), gamma1);
            // }
            node.setToken(gamma1.getToken());
            node.setDepth(gamma1.getDepth());
            node.setChildren(gamma1.getChildren());
            return true;
        } else if(Uops.contains(node.getToken())) {
            Node gamma = new Node("gamma", node.getParent(), node.getDepth());
            Node Uop = new Node(node.getToken(), gamma, gamma.getDepth() + 1);
            Node E = node.getChildren().get(0);
            E.increaseDepthBy(gamma.getDepth() + 1 - E.getDepth());
            E.setParent(gamma);
            gamma.setChildren(new ArrayList<Node>(Arrays.asList(Uop, E)));
            // if (node.getParent() != null) {
            //     node.getParent().getChildren().set(node.getParent().getChildren().indexOf(node), gamma);
            // }
            node.setToken(gamma.getToken());
            node.setDepth(gamma.getDepth());
            node.setChildren(gamma.getChildren());
            return true;
        }
        return false;
    }
    
}

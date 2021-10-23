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
            Node gamma1 = new Node("gamma");
            gamma1.setDepth(node.getDepth());
            Node gamma2 = new Node("gamma");
            Node Op = new Node(node.getToken());
            gamma1.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(gamma2, E2)));
            gamma2.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(Op, E1)));
            // if (node.getParent() != null) {
            //     node.getParent().getChildren().set(node.getParent().getChildren().indexOf(node), gamma1);
            // }
            node.setToken(gamma1.getToken());
            node.setDepth(gamma1.getDepth());
            node.setChildren(gamma1.getChildren());
            return true;
        } else if(Uops.contains(node.getToken())) {
            Node gamma = new Node("gamma");
            gamma.setDepth(node.getDepth());
            Node Uop = new Node(node.getToken());
            Node E = node.getChildren().get(0);
            gamma.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(Uop, E)));
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

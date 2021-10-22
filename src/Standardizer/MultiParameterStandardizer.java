package Standardizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Node.Node;

public class MultiParameterStandardizer extends AbstractStandardizer {
    @Override
    protected boolean standardizeImplementation(Node node) {
        if (node.getToken().equals("lambda")) {
            if (node.getChildren().size() > 2) {
                List<Node> children = node.getChildren();
                node.setChildren(new ArrayList<Node>());
                int numberOfChildren = children.size();
                Node E  = children.get(numberOfChildren - 1);
                Node currentLambda = node;
                for (Node child : children.subList(0, numberOfChildren - 1)) {
                    currentLambda.addChild(child);
                    child.setDepth(currentLambda.getDepth() + 1);
                    child.setParent(currentLambda);
                    if (children.indexOf(child) != numberOfChildren - 2) {
                        Node lambda = new Node("lambda", currentLambda, currentLambda.getDepth() + 1);
                        currentLambda.addChild(lambda);
                        currentLambda = lambda;
                    }
                }
                currentLambda.addChild(E);
                E.increaseDepthBy(currentLambda.getDepth() + 1 - E.getDepth());
                E.setParent(currentLambda);
            }
            if (node.getChildren().size() == 2 && node.getChildren().get(0).getToken().equals(",")) {
                Node comma = node.getChildren().get(0);
                List<Node> commasChildrenList = comma.getChildren();
                Node E = node.getChildren().get(1);
                Node appendieNode = E;
                for (Node child : commasChildrenList) {
                    int childIndex = commasChildrenList.indexOf(child);
                    Node gammaOuter = new Node("gamma", null, node.getDepth() + (commasChildrenList.size() - childIndex)*2 - 1);
                    Node gammaInner = new Node("gamma", gammaOuter, gammaOuter.getDepth() + 1);
                    gammaInner.setChildren(new ArrayList<Node>(Arrays.asList(
                        new Node("Temp", gammaInner, gammaInner.getDepth() +1),
                        new Node("<INT:" + (childIndex + 1) + ">", gammaInner, gammaInner.getDepth() +1)
                    )));
                    Node lambda = new Node("lambda", gammaOuter, gammaOuter.getDepth() + 1);
                    lambda.setChildren(new ArrayList<Node>(Arrays.asList(child, appendieNode)));
                    child.setParent(lambda);
                    child.increaseDepthBy(lambda.getDepth() + 1 - child.getDepth());
                    appendieNode.setParent(lambda);
                    appendieNode.increaseDepthBy(lambda.getDepth() + 1 - appendieNode.getDepth());
                    gammaOuter.setChildren(new ArrayList<Node>(Arrays.asList(lambda, gammaInner)));
                    appendieNode = gammaOuter;
                }
                node.setChildren(new ArrayList<Node>(Arrays.asList(
                    new Node("Temp", node, node.getDepth() +1),
                    appendieNode
                )));
            }
            return true;
        } else {
            return false;
        }
    }
    
}

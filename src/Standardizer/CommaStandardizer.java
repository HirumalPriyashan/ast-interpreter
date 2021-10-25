package Standardizer;

import Node.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommaStandardizer extends AbstractStandardizer{
    @Override
    protected boolean standardizeImplementation(Node node) {
        if (node.getToken().equals("lambda") && node.getChildren().size() == 2 && node.getChildren().get(0).getToken().equals(",")) {
            Node comma = node.getChildren().get(0);
            List<Node> commasChildrenList = comma.getChildren();
            Node E = node.getChildren().get(1);
            // restructure
            Node appendieNode = E;
            for (Node child : commasChildrenList) {
                int childIndex = commasChildrenList.indexOf(child);
                Node gammaOuter = new Node("gamma");
                gammaOuter.setDepth( node.getDepth() + (commasChildrenList.size() - childIndex)*2 - 1);
                Node lambda = new Node("lambda");
                Node gammaInner = new Node("gamma");
                gammaOuter.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(lambda, gammaInner)));
                gammaInner.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(
                        new Node("Temp"),
                        new Node("<INT:" + (childIndex + 1) + ">")
                )));
                lambda.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(child, appendieNode)));
                appendieNode = gammaOuter;
            }
            node.setChildrenWithDepth(new ArrayList<Node>(Arrays.asList(
                    new Node("Temp"),
                    appendieNode
            )));
            return true;
        }
        return false;
    }
}

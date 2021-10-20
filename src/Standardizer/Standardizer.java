package Standardizer;

import Node.Node;

public class Standardizer {
    private AbstractStandardizer standardizer;

    public Standardizer() {
        this.standardizer = new LetStandardizer();
        this.standardizer
        .setSuccessor(new WhereStandatdizer())
        .setSuccessor(new FnFrmStandardizer());
    }

    public void standardize(Node node) {
        if (!node.getIsStandardized()) {
            for (Node child : node.getChildren()) {
                this.standardizer.standardize(child);
            }
            this.standardizer.standardize(node);
            node.setIsStandardized(true);
        }
        return;
    }
    
}

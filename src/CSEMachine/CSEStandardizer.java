package CSEMachine;

import Node.Node;
import Standardizer.*;

public class CSEStandardizer implements  Standardizer{
    private AbstractStandardizer standardizer;

    public CSEStandardizer() {
        this.standardizer = new LetStandardizer(this);
        this.standardizer
                .setSuccessor(new WhereStandatdizer())
                .setSuccessor(new FnFrmStandardizer())
                .setSuccessor(new MultiParameterStandardizer())
                .setSuccessor(new WithinStandardizer())
                .setSuccessor(new AtStandardizer())
                .setSuccessor(new SimultaniousStandardizer(this))
                .setSuccessor(new RecStandardizer());
    }

    public void standardize(Node node) {
        if (!node.getIsStandardized()) {
            for (Node child : node.getChildren()) {
                this.standardize(child);
            }
            this.standardizer.standardize(node);
            node.setIsStandardized(true);
        }
        return;
    }
}


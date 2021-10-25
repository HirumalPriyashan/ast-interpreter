package ASTInterpreter;

import Node.Node;
import Standardizer.*;

public class ASTStandardizer implements  Standardizer{
    private AbstractStandardizer standardizer;

    public ASTStandardizer() {
        this.standardizer = new LetStandardizer(this);
        this.standardizer
                .setSuccessor(new WhereStandatdizer())
                .setSuccessor(new FnFrmStandardizer())
                .setSuccessor(new TupleStandardizer())
                .setSuccessor(new MultiParameterStandardizer())
                .setSuccessor(new WithinStandardizer())
                .setSuccessor(new OpStandardizer())
                .setSuccessor(new AtStandardizer())
                .setSuccessor(new SimultaniousStandardizer(this))
                // .setSuccessor(new ConditionalStandardizer())
                .setSuccessor(new CommaStandardizer())
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

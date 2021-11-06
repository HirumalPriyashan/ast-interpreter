package ASTInterpreter;

import Node.Node;
import Standardizer.*;

/**
 * ASTStandardizer is the class for standardizing Abstract Syntax Tree
 * It provides the functionality to standardize a given AST and produce
 * desugared node
 * 
 * @author Hirumal Priyshan
 * @version 1.0
 * @since 1.0
 */
public class ASTStandardizer implements  Standardizer{
    // Fisrt standadardizer to desugar the AST
    private AbstractStandardizer standardizer;

    /** 
    * Class constructor.
    */
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

    @Override
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

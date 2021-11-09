package Standardizer;

import Node.Node;

/**
 * AbstractStandardizer is the base abstract class for standardizers 
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 * @see https://sourcemaking.com/design_patterns/chain_of_responsibility
 */
public abstract class AbstractStandardizer {
    protected AbstractStandardizer successor;

    /**
     * Sets successer for the current handler
     *
     * @param  successor next handler
     * @return successor  
     */
    public AbstractStandardizer setSuccessor(AbstractStandardizer successor) {
        this.successor = successor;
        return this.successor;
    }

    /**
     * Abstract protected method for standardize gamma
     * @param node node to be standardize 
     * @return  <b>true</b> if handled by one of the handlers
     *          otherwise <b>false</b>
     */
    protected abstract boolean standardizeImplementation(Node node);
    
    /**
     * Apply the standardize gamma if can be handled otherwise pass to the 
     * successor
     * @param node node to be standardize 
     * @return  <b>true</b> if handled by one of the handlers
     *          otherwise <b>false</b>
     */
    public final void standardize(Node node) {
        boolean handledByThis = this.standardizeImplementation(node);
        if (successor != null && !handledByThis) {
            successor.standardize(node);
        }
    }
}

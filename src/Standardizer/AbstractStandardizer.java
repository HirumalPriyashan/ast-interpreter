package Standardizer;

import Node.Node;

public abstract class AbstractStandardizer {
    protected AbstractStandardizer successor;

    public AbstractStandardizer setSuccessor(AbstractStandardizer successor) {
        this.successor = successor;
        return this.successor;
    }

    protected abstract boolean standardizeImplementation(Node node);

    public final void standardize(Node node) {
        boolean handledByThis = this.standardizeImplementation(node);
        if (successor != null && !handledByThis) {
            successor.standardize(node);
        }
    }
}

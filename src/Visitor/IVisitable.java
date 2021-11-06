package Visitor;

/**
 * Abstraction for visitable in the Visitor design pattern 
 * 
 * @author Hirumal Priyshan
 * @version 1.0
 * @since 1.0
 * @see https://sourcemaking.com/design_patterns/visitor
 */
public interface IVisitable {

    /**
     * Accept the visitor
     * @param visitor visitor for ivisitable node
     */
    public void accept(IVisitor visitor);
}

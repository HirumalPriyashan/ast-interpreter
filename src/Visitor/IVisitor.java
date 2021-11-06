package Visitor;

/**
 * Abstraction for visitor in the Visitor design pattern 
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 * @see https://sourcemaking.com/design_patterns/visitor
 */
public interface IVisitor {

    /**
     * Visit the visitable
     * @param iVisitable item to visit
     */
    public void visit(IVisitable iVisitable);
}

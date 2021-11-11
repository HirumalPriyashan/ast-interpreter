package CSEMachine.CSERules;

import CSEMachine.Symbols.Delta;
import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Symbol;

import java.util.List;

/**
 * AbstractRule is the base abstract class for cse rules 
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 * @see "https://sourcemaking.com/design_patterns/chain_of_responsibility"
 */
public abstract class AbstractRule {
    // next handler in the chain
    protected AbstractRule successor;

    /**
    * Sets successer for the current handler
    *
    * @param  successor next handler
    * @return successor  
    */
    public AbstractRule setSuccessor(AbstractRule successor) {
        this.successor = successor;
        return this.successor;
    }

    /**
    * Abstract protected method for CSERule implementation
    * 
    * @param control - current control
    * @param stack - current stack
    * @param environments - list of available environments
    * @param deltas - list of delta nodes
    * @return   handler number if handled by one of the handlers
    *           otherwise 0 
    */
    protected abstract int applyRuleImplementation(
        List<Symbol> control, List<Symbol> stack, 
        List<Environment> environments, List<Delta> deltas
    );

    /**
    * Apply the abstract rule if can be handled otherwise pass to the 
    * successor
    * 
    * @param control - current control
    * @param stack - current stack
    * @param environments - list of available environments
    * @param deltas - list of delta nodes
    * @return   handler number if handled by one of the handlers
    *           otherwise 0 
    */
    public final int applyRule(
        List<Symbol> control, List<Symbol> stack,
        List<Environment> environments, List<Delta> deltas
    ) {
        int handledByThis = this.applyRuleImplementation(
            control, stack, environments, deltas);
        if (successor != null && handledByThis == 0) {
            return successor.applyRule(control, stack, environments, deltas);
        }
        return handledByThis;
    }
}

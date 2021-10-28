package CSEMachine.CSERules;

import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Symbol;

import java.util.List;

public abstract class AbstractRule {
    protected AbstractRule successor;

    public AbstractRule setSuccessor(AbstractRule successor) {
        this.successor = successor;
        return this.successor;
    }

    protected abstract boolean applyRuleImplementation(List<Symbol> control, List<Symbol> stack, List<Environment> environments);

    public final void applyRule(List<Symbol> control, List<Symbol> stack, List<Environment> environments) {
        boolean handledByThis = this.applyRuleImplementation(control, stack, environments);
        if (successor != null && !handledByThis) {
            successor.applyRule(control, stack, environments);
        }
    }
}

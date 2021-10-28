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

    protected abstract boolean applyRuleImplementation(List<Symbol> control, List<Symbol> stack, List<Environment> environments, List<List<Symbol>> deltas);

    public final void applyRule(List<Symbol> control, List<Symbol> stack, List<Environment> environments, List<List<Symbol>> deltas) {
        boolean handledByThis = this.applyRuleImplementation(control, stack, environments, deltas);
        if (successor != null && !handledByThis) {
            successor.applyRule(control, stack, environments, deltas);
        }
    }
}

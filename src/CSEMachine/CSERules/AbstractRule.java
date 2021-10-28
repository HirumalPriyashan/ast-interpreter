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

    protected abstract boolean standardizeImplementation(List<Symbol> control, List<Symbol> stack, Environment environment);

    public final void standardize(List<Symbol> control, List<Symbol> stack, Environment environment) {
        boolean handledByThis = this.standardizeImplementation(control, stack, environment);
        if (successor != null && !handledByThis) {
            successor.standardize(control, stack, environment);
        }
    }
}

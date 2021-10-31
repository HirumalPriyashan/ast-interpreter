package CSEMachine.CSERules;

import java.util.List;

import CSEMachine.Symbols.Beta;
import CSEMachine.Symbols.Bool;
import CSEMachine.Symbols.Delta;
import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Symbol;

public class CSERule8 extends AbstractRule{
    /**
    * Modify the control and stack according to CSE Rule 3
    *                  Control                                 Stack      Env
    *                 ├-----------------------------------------------├
    * CSE Rule 8      │....delta_then delta_else beta         true....│ 
    * (conditional)   │....delta_then                             ....│ 
    *                 ├╴----------------------------------------------│
    *                 │....delta_then delta_else beta        false....│ 
    *                 │....delta_else                             ....│ 
    *                 └╴----------------------------------------------├
    * @param control
    * @param stack
    * @param environment
    */
    @Override
    protected boolean applyRuleImplementation(
        List<Symbol> control, 
        List<Symbol> stack, 
        List<Environment> environments, 
        List<List<Symbol>> deltas
    ) {
        if (
            control.get(control.size() - 1) instanceof Beta
            && control.get(control.size() - 2) instanceof Delta
            && control.get(control.size() - 3) instanceof Delta
            && stack.get(0) instanceof Bool
        ){
            Bool bool = (Bool) stack.get(0);
            stack.remove(0);
            List<Symbol> nextDelta;
            if (bool.isTrue()) {
                nextDelta = ((Delta) control.get(control.size() - 3)).getSymbols();
            } else {
                nextDelta = ((Delta) control.get(control.size() - 2)).getSymbols();
            }
            control.remove(control.size() - 1);
            control.remove(control.size() - 2);
            control.remove(control.size() - 3);
            for (Symbol symbol : nextDelta) {
                control.add(symbol);
            }
            return true;
        }
        return false;
    }
}

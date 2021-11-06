package CSEMachine.CSERules;

import java.util.List;

import CSEMachine.Symbols.Beta;
import CSEMachine.Symbols.Bool;
import CSEMachine.Symbols.Delta;
import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Lambda;
import CSEMachine.Symbols.Symbol;

/**
 * Class for CSE Rule 8
 * 
 * @author Hirumal Priyshan
 * @version 1.0
 * @since 1.0
 */
public class CSERule8 extends AbstractRule{
    /**
    * Modify the control and stack according to CSE Rule 8
    *                  Control                                 Stack      Env
    *                 ├-----------------------------------------------├
    * CSE Rule 8      │....delta_then delta_else beta         true....│ 
    * (conditional)   │....delta_then                             ....│ 
    *                 ├╴----------------------------------------------│
    *                 │....delta_then delta_else beta        false....│ 
    *                 │....delta_else                             ....│ 
    *                 └╴----------------------------------------------├
    * 
    * @param control - current control
    * @param stack - current stack
    * @param environment - list of available environments
    * @param deltas - list of delta nodes
    * @return   8 if can handled by this method
    *           otherwise 0
    */
    @Override
    protected int applyRuleImplementation(
        List<Symbol> control, 
        List<Symbol> stack, 
        List<Environment> environments, 
        List<Delta> deltas
    ) {
        if (
            control.size() > 2
            && stack.size() > 0
            && control.get(control.size() - 1) instanceof Beta
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
            control.remove(control.size() - 1);
            control.remove(control.size() - 1);
            for (Symbol symbol : nextDelta) {
                if (symbol instanceof Lambda) {
                    ((Lambda) symbol).setEnvironment(-1);
                }
                control.add(symbol);
            }
            return 8;
        }
        return 0;
    }
}

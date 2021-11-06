package CSEMachine.CSERules;

import java.util.List;

import CSEMachine.Symbols.Delta;
import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Gamma;
import CSEMachine.Symbols.Int;
import CSEMachine.Symbols.Symbol;
import CSEMachine.Symbols.Tuple;

/**
 * Class for CSE Rule 10
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class CSERule10 extends AbstractRule{
    /**
    * Modify the control and stack according to CSE Rule 10
    *                      Control                 Stack      Env
    *                     ├--------------------------------├
    * CSE Rule 10         │....gamma      (V1,...,Vn) I....│ 
    * (tuple selection)   │....                      Vi....│
    *                     └╴-------------------------------├
    *
    * @param control - current control
    * @param stack - current stack
    * @param environment - list of available environments
    * @param deltas - list of delta nodes
    * @return   10 if can handled by this method
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
            control.size() > 0 
            && stack.size() > 1
            && control.get(control.size() - 1) instanceof Gamma
            && stack.get(0) instanceof Tuple 
            && stack.get(1) instanceof Int
        ) {
            control.remove(control.size() - 1);
            Tuple tuple = (Tuple) stack.get(0);
            stack.remove(0);
            int i = Integer.parseInt(stack.get(0).getToken());
            stack.remove(0);
            stack.add(0, tuple.getSymbols().get(i-1));
            return 10;
        }
        return 0;
    }    
}

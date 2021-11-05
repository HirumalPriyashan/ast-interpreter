package CSEMachine.CSERules;

import java.util.List;

import CSEMachine.Symbols.Delta;
import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Symbol;
import CSEMachine.Symbols.Tau;
import CSEMachine.Symbols.Tuple;

public class CSERule9 extends AbstractRule{
    /**
    * Modify the control and stack according to CSE Rule 9
    *                      Control                 Stack      Env
    *                     ├--------------------------------├
    * CSE Rule 9          │....tau-n            V1...Vn....│ 
    * (tuple formation)   │....             (V1,...,Vn)....│
    *                     └╴-------------------------------├
    * @param control
    * @param stack
    * @param environment
    * @param deltas
    */
    @Override
    protected boolean applyRuleImplementation(
        List<Symbol> control, 
        List<Symbol> stack, 
        List<Environment> environments,
        List<Delta> deltas
    ) {
        if (
            control.size() > 0 
            && stack.size() > 0
            && control.get(control.size() - 1) instanceof Tau
        ) {
            System.out.println("Applying Rule 9");
            Tau tau = (Tau) control.get(control.size() - 1);
            control.remove(control.size() - 1);
            Tuple tuple = new Tuple();
            for (int i = 0; i < tau.getN(); i++) {
                tuple.addSymbol(stack.get(0));
                stack.remove(0);
            }
            stack.add(0, tuple);
            return true;
        }
        return false;
    }
}

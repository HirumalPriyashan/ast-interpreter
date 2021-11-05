package CSEMachine.CSERules;

import java.util.List;

import CSEMachine.Symbols.Delta;
import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Symbol;

public class CSERule5 extends AbstractRule{
    /**
    * Modify the control and stack according to CSE Rule 5
    *                  Control                Stack      Env
    *                 ├------------------------------├
    * CSE Rule 5      │....En            value En....│ 
    * (exit env)      │....                 value....│     
    *                 └╴-----------------------------├
    * @param control
    * @param stack
    * @param environment
    * @param deltas
    */
    @Override
    protected int applyRuleImplementation(
        List<Symbol> control, 
        List<Symbol> stack, 
        List<Environment> environments, 
        List<Delta> deltas
    ) {
        if (control.get(control.size() - 1)  instanceof Environment) {
            environments.get(((Environment) control.get(control.size() - 1)).getIndex()).setIsRemoved(true);
            stack.remove(1);
            control.remove(control.size() - 1);
            return 5;
        }            
        return 0;
    }
}

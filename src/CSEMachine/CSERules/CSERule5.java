package CSEMachine.CSERules;

import java.util.List;

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
    */
    @Override
    protected boolean applyRuleImplementation(
        List<Symbol> control, 
        List<Symbol> stack, 
        List<Environment> environments, 
        List<List<Symbol>> deltas
    ) {
        if (control.get(control.size() - 1)  instanceof Environment) {
            System.out.println("Appling Rule 5");              
            environments.get(((Environment) control.get(control.size() - 1)).getIndex()).setIsRemoved(true);
            stack.remove(1);
            control.remove(control.size() - 1);
        }            
        return false;
    }
}

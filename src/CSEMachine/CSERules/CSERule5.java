package CSEMachine.CSERules;

import java.util.List;

import CSEMachine.Symbols.Delta;
import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Symbol;

/**
 * Class for CSE Rule 5
 * 
 * @author Hirumal Priyashannn
 * @version 1.0
 * @since 1.0
 */
public class CSERule5 extends AbstractRule{
    /**
    * Modify the control and stack according to CSE Rule 5
    *                  Control                Stack      Env
    *                 ├------------------------------├
    * CSE Rule 5      │....En            value En....│ 
    * (exit env)      │....                 value....│     
    *                 └╴-----------------------------├
    * 
    * @param control - current control
    * @param stack - current stack
    * @param environments - list of available environments
    * @param deltas - list of delta nodes
    * @return   5 if can handled by this method
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
            && control.get(control.size() - 1)  instanceof Environment
        ) {
            // environments.get(((Environment) control.get(control.size() - 1)).getIndex()).setIsRemoved(true);
            stack.remove(1);
            control.remove(control.size() - 1);
            return 5;
        }            
        return 0;
    }
}

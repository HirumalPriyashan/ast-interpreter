package CSEMachine.CSERules;

import java.util.List;

import CSEMachine.Symbols.Delta;
import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Gamma;
import CSEMachine.Symbols.Rand;
import CSEMachine.Symbols.Rator;
import CSEMachine.Symbols.Symbol;

/**
 * Class for CSE Rule 3
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class CSERule3 extends AbstractRule{
    /**
    * Modify the control and stack according to CSE Rule 3
    *                  Control                Stack      Env
    *                 ├------------------------------├
    * CSE Rule 3      │....gamma       Rator Rand....│ 
    * (apply rator)   │....                Result....│ Result = Apply(Rator, Rand)    
    *                 └╴-----------------------------├
    * 
    * @param control - current control
    * @param stack - current stack
    * @param environment - list of available environments
    * @param deltas - list of delta nodes
    * @return   3 if can handled by this method
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
            && stack.get(0) instanceof Rator 
            && stack.get(1) instanceof Rand
        ){
            control.remove(control.size()-1);
            Rand rand =(Rand) stack.get(1);
            Rator rator =(Rator) stack.get(0);
            Symbol result = rator.apply(rand);
            stack.remove(0);
            stack.remove(0);
            stack.add(0, result);
            return 3;
        }
        return 0;
    }
}

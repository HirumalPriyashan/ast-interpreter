package CSEMachine.CSERules;

import java.util.List;

import CSEMachine.Symbols.Delta;
import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Gamma;
import CSEMachine.Symbols.Rand;
import CSEMachine.Symbols.Rator;
import CSEMachine.Symbols.Symbol;

public class CSERule3 extends AbstractRule{
    /**
    * Modify the control and stack according to CSE Rule 3
    *                  Control                Stack      Env
    *                 ├------------------------------├
    * CSE Rule 3      │....gamma       Rator Rand....│ 
    * (apply rator)   │....                Result....│ Result = Apply(Rator, Rand)    
    *                 └╴-----------------------------├
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
            control.get(control.size() - 1) instanceof Gamma //check last symbol is a gamma
            && stack.get(0) instanceof Rator 
            && stack.get(1) instanceof Rand
        ){
            System.out.println("Applying Rule 3");
            control.remove(control.size()-1);
            Rand rand =(Rand) stack.get(1);
            Rator rator =(Rator) stack.get(0);
            Symbol result = rator.apply(rand);
            stack.remove(0);
            stack.remove(0);
            stack.add(0, result);
            return true;
        }
        return false;
    }
}

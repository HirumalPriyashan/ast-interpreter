package CSEMachine.CSERules;

import java.util.List;

import CSEMachine.Symbols.Delta;
import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Rand;
import CSEMachine.Symbols.Symbol;
import CSEMachine.Symbols.UOp;

public class CSERule7 extends AbstractRule{
    /**
    * Modify the control and stack according to CSE Rule 7
    *                  Control                Stack      Env
    *                 ├------------------------------├
    * CSE Rule 7      │....unop         Rand Rand....│ 
    * (unop)          │....                Result....│ Result = Apply(unop,Rand)    
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
        if (
            control.get(control.size() - 1) instanceof UOp
            && stack.get(0) instanceof Rand
        ){
            Rand rand =(Rand) stack.get(0);
            UOp uOp = (UOp) control.get(control.size() - 1);
            control.remove(control.size()-1);
            Symbol result = uOp.apply(rand);
            stack.remove(0);
            stack.add(0, result);
            return 7;
        }
        return 0;
    }
}

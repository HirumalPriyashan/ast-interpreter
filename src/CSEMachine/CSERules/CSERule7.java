package CSEMachine.CSERules;

import java.util.List;

import CSEMachine.Symbols.Delta;
import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Rand;
import CSEMachine.Symbols.Symbol;
import CSEMachine.Symbols.UOp;

public class CSERule7 extends AbstractRule{
    /**
    * Modify the control and stack according to CSE Rule 3
    *                  Control                Stack      Env
    *                 ├------------------------------├
    * CSE Rule 6      │....unop         Rand Rand....│ 
    * (unop)          │....                Result....│ Result = Apply(unop,Rand)    
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
        List<Delta> deltas
    ) {
        if (
            control.get(control.size() - 1) instanceof UOp
            && stack.get(0) instanceof Rand
        ){
            System.out.println("Appling Rule 7");
            Rand rand =(Rand) stack.get(0);
            UOp uOp = (UOp) control.get(control.size() - 1);
            control.remove(control.size()-1);
            Symbol result = uOp.apply(rand);
            stack.remove(0);
            stack.add(0, result);
            return true;
        }
        return false;
    }
}

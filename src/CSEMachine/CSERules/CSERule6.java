package CSEMachine.CSERules;

import java.util.List;

import CSEMachine.Symbols.Delta;
import CSEMachine.Symbols.BOp;
import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Rand;
import CSEMachine.Symbols.Symbol;

public class CSERule6 extends AbstractRule{
    /**
    * Modify the control and stack according to CSE Rule 3
    *                  Control                Stack      Env
    *                 ├------------------------------├
    * CSE Rule 6      │....binop        Rand Rand....│ 
    * (binop)         │....                Result....│ Result = Apply(binop,Rand, Rand)    
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
            control.get(control.size() - 1) instanceof BOp //check last symbol is a gamma
            && stack.get(0) instanceof Rand
            && stack.get(1) instanceof Rand
        ){
            System.out.println("Appling Rule 6");
            Rand rand1 =(Rand) stack.get(0);
            Rand rand2 =(Rand) stack.get(1);
            BOp bOp = (BOp) control.get(control.size() - 1);
            control.remove(control.size()-1);
            Symbol result = bOp.apply(rand1, rand2);
            stack.remove(0);
            stack.remove(0);
            stack.add(0, result);
            return true;
        }
        return false;
    }
}

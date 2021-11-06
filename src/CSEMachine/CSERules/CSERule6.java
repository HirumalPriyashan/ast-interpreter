package CSEMachine.CSERules;

import java.util.List;

import CSEMachine.Symbols.Delta;
import CSEMachine.Symbols.BOp;
import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Rand;
import CSEMachine.Symbols.Symbol;

/**
 * Class for CSE Rule 6
 * 
 * @author Hirumal Priyashannn
 * @version 1.0
 * @since 1.0
 */
public class CSERule6 extends AbstractRule{
    /**
    * Modify the control and stack according to CSE Rule 6
    *                  Control                Stack      Env
    *                 ├------------------------------├
    * CSE Rule 6      │....binop        Rand Rand....│ 
    * (binop)         │....                Result....│ Result = Apply(binop,Rand, Rand)    
    *                 └╴-----------------------------├
    * 
    * @param control - current control
    * @param stack - current stack
    * @param environment - list of available environments
    * @param deltas - list of delta nodes
    * @return   6 if can handled by this method
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
            && control.get(control.size() - 1) instanceof BOp
            && stack.get(0) instanceof Rand
            && stack.get(1) instanceof Rand
        ){
            Rand rand1 =(Rand) stack.get(0);
            Rand rand2 =(Rand) stack.get(1);
            BOp bOp = (BOp) control.get(control.size() - 1);
            control.remove(control.size()-1);
            Symbol result = bOp.apply(rand1, rand2);
            stack.remove(0);
            stack.remove(0);
            stack.add(0, result);
            return 6;
        }
        return 0;
    }
}

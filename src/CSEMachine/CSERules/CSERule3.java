package CSEMachine.CSERules;

import java.util.List;

import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Symbol;

public class CSERule3 extends AbstractRule{
    /**
    * Modify the control and stack according to CSE Rule 3
    *                  Control                Stack      Env
    *                 ├------------------------------├╴
    * CSE Rule 3      │....gamma       Rator Rand....│ 
    * (apply rator)   │....                Result....│ Result = Apply(Rator, Rand)    
    *                 └╴-----------------------------├╴
    * @param control
    * @param stack
    * @param environment
    */
    @Override
    protected boolean standardizeImplementation(List<Symbol> control, List<Symbol> stack, Environment environment) {
        return false;
    }
}

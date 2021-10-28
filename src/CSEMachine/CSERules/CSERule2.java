package CSEMachine.CSERules;

import java.util.List;

import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Symbol;

public class CSERule2 extends AbstractRule{
    /**
    * Modify the control and stack according to CSE Rule 2
    *                  Control                Stack      Env
    *                 ├------------------------------├╴
    * CSE Rule 2      │....lambda x k            ....│ 
    * (Stack lambda)  │....          c lambda x k....│ Ec : current environment    
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

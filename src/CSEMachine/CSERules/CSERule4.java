package CSEMachine.CSERules;

import java.util.List;

import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Symbol;

public class CSERule4 extends AbstractRule{
    /**
    * Modify the control and stack according to CSE Rule 4
    *                  Control                      Stack      Env
    *                 ├------------------------------------├╴
    * CSE Rule 4      │....gamma      c lambda x k Rand....│ 
    * (apply lambda)  │....En delta k                En....│ En = [Rand/x]Ec   
    *                 └╴-----------------------------------├╴
    * @param control
    * @param stack
    * @param environment
    */
    @Override
    protected boolean standardizeImplementation(List<Symbol> control, List<Symbol> stack, Environment environment) {
        return false;
    }
}

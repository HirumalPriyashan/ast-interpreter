package CSEMachine.CSERules;

import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Symbol;

import java.util.List;

public class CSERule1 extends AbstractRule{
    /**
    * Modify the control and stack according to CSE Rule 1
    *                  Control                Stack      Env
    *                 ├------------------------------├
    * CSE Rule 1      │....Name                  ....│ Ob = Lookup(Name, Ec)
    * (Stack a name)  │....                    Ob....│ Ec : current environment    
    *                 └╴-----------------------------├
    * @param control
    * @param stack
    * @param environment
    */
    @Override
    protected boolean applyRuleImplementation(List<Symbol> control, List<Symbol> stack, List<Environment> environments) {
        if (
            control.get(control.size() - 1).getToken().startsWith("<ID:")  //check last symbol is a name
        ){
            // Symbol Ob = environment.lookup(control.get(control.size() - 1).getToken());
            // stack.add(Ob);
        }
        return false;
    }
}

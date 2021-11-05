package CSEMachine.CSERules;

import CSEMachine.Symbols.Delta;
import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Id;
import CSEMachine.Symbols.Rand;
import CSEMachine.Symbols.Rator;
import CSEMachine.Symbols.Symbol;
import CSEMachine.Symbols.YStar;

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
            control.get(control.size() - 1) instanceof Id //check last symbol is a id
        ){
            System.out.println("Applying Rule 1");
            Symbol Ob = environments.get(0).lookup((Id) control.get(control.size() - 1));
            control.remove(control.size()-1);
            stack.add(0, Ob);
            return true;
        } else if (
            control.get(control.size() - 1 ) instanceof Rand
            // || control.get(control.size() - 1 ) instanceof Rator
            || control.get(control.size() - 1 ) instanceof YStar
        ){
            System.out.println("Applying Rule 1");
            stack.add(0, control.get(control.size() - 1));
            control.remove(control.size() - 1);
            return true;
        }
        return false;
    }
}

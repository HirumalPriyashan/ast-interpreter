package CSEMachine.CSERules;

import CSEMachine.Symbols.Delta;
import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Id;
import CSEMachine.Symbols.Rand;
import CSEMachine.Symbols.Symbol;
import CSEMachine.Symbols.YStar;
import Logger.Logger;

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
            Logger.log("Applying Rule 1");
            Symbol Ob = getCurrentEnvironment(control, environments).lookup((Id) control.get(control.size() - 1));
            control.remove(control.size()-1);
            stack.add(0, Ob);
            return true;
        } else if (
            control.get(control.size() - 1 ) instanceof Rand
            // || control.get(control.size() - 1 ) instanceof Rator
            || control.get(control.size() - 1 ) instanceof YStar
        ){
            Logger.log("Applying Rule 1");
            stack.add(0, control.get(control.size() - 1));
            control.remove(control.size() - 1);
            return true;
        }
        return false;
    }   
    
    private Environment getCurrentEnvironment(List<Symbol> control, List<Environment> environments){
        for (int i = control.size() - 1; i >= 0; i--) {
            if (control.get(i) instanceof Environment) {
                return (Environment) control.get(i);
            }
        }
        return environments.get(0);
    }
}

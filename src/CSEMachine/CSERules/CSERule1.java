package CSEMachine.CSERules;

import CSEMachine.Symbols.Delta;
import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Id;
import CSEMachine.Symbols.Lambda;
import CSEMachine.Symbols.Rand;
import CSEMachine.Symbols.Symbol;
import CSEMachine.Symbols.YStar;

import java.util.List;

/**
 * Class for CSE Rule 1
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class CSERule1 extends AbstractRule{
    /**
    * Modify the control and stack according to CSE Rule 1
    *                  Control                Stack      Env
    *                 ├------------------------------├
    * CSE Rule 1      │....Name                  ....│ Ob = Lookup(Name, Ec)
    * (Stack a name)  │....                    Ob....│ Ec : current environment    
    *                 └╴-----------------------------├
    *  
    * @param control - current control
    * @param stack - current stack
    * @param environments - list of available environments
    * @param deltas - list of delta nodes
    * @return   1 if can handled by this method
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
            && control.get(control.size() - 1) instanceof Id 
        ){
            Symbol Ob = getCurrentEnvironment(control, environments).lookup((Id) control.get(control.size() - 1));
            control.remove(control.size()-1);
            stack.add(0, Ob);
            return 1;
        } else if (
            (
                control.get(control.size() - 1 ) instanceof Rand
                && !(control.get(control.size() - 1 ) instanceof Lambda)
            )
            // || control.get(control.size() - 1 ) instanceof Rator
            || control.get(control.size() - 1 ) instanceof YStar
        ){
            stack.add(0, control.get(control.size() - 1));
            control.remove(control.size() - 1);
            return 1;
        }
        return 0;
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

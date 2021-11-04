package CSEMachine.CSERules;

import java.util.List;

import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Gamma;
import CSEMachine.Symbols.Lambda;
import CSEMachine.Symbols.Rand;
import CSEMachine.Symbols.Symbol;

public class CSERule4 extends AbstractRule{
    /**
    * Modify the control and stack according to CSE Rule 4
    *                  Control                      Stack      Env
    *                 ├------------------------------------├
    * CSE Rule 4      │....gamma      c lambda x k Rand....│ 
    * (apply lambda)  │....En delta k                En....│ En = [Rand/x]Ec   
    *                 └╴-----------------------------------├
    * @param control
    * @param stack
    * @param environment
    */
    @Override
    protected boolean applyRuleImplementation(
        List<Symbol> control, 
        List<Symbol> stack, 
        List<Environment> environments, 
        List<List<Symbol>> deltas
    ) {
        if (
            control.get(control.size() - 1) instanceof Gamma //check last symbol is a gamma
            && stack.get(0) instanceof Lambda 
            && stack.get(1) instanceof Rand
        ){
            System.out.println("Appling Rule 4");
            control.remove(control.size()-1);
            Environment newEnvironment =new Environment(environments.size());
            newEnvironment.setParent(environments.get(0));
            environments.add(0, newEnvironment);
            Lambda lambda =(Lambda) stack.get(0);
            // newEnvironment.addValue(lambda.getX().getToken(), stack.get(1));
            control.add(newEnvironment);
            stack.remove(0);
            stack.remove(0);
            stack.add(0, newEnvironment);
            List<Symbol> delta = deltas.get(lambda.getIndex());
            for (Symbol symbol : delta) {
                control.add(symbol);
            }
            return true;
        }
        return false;
    }
}

package CSEMachine.CSERules;

import java.util.List;

import CSEMachine.Symbols.Delta;
import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Lambda;
import CSEMachine.Symbols.Symbol;
import Logger.Logger;

public class CSERule2 extends AbstractRule{
    /**
    * Modify the control and stack according to CSE Rule 2
    *                  Control                Stack      Env
    *                 ├------------------------------├
    * CSE Rule 2      │....lambda_x_k            ....│ 
    * (Stack lambda)  │....          c-lambda_x_k....│ Ec : current environment    
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
            control.get(control.size() - 1) instanceof Lambda //check last symbol is a lambda
        ){
            Logger.log("Applying Rule 2");
            Lambda lambda = (Lambda) control.get(control.size() - 1);
            control.remove(control.size()-1);
            lambda.setEnvironment(getCurrentEnvironment(control, environments).getIndex());
            stack.add(0, lambda);
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

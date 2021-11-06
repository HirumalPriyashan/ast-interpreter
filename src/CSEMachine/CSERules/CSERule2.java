package CSEMachine.CSERules;

import java.util.List;

import CSEMachine.Symbols.Delta;
import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Lambda;
import CSEMachine.Symbols.Symbol;

/**
 * Class for CSE Rule 2
 * 
 * @author Hirumal Priyshan
 * @version 1.0
 * @since 1.0
 */
public class CSERule2 extends AbstractRule{
    /**
    * Modify the control and stack according to CSE Rule 2
    *                  Control                Stack      Env
    *                 ├------------------------------├
    * CSE Rule 2      │....lambda_x_k            ....│ 
    * (Stack lambda)  │....          c-lambda_x_k....│ Ec : current environment    
    *                 └╴-----------------------------├
    * 
    * @param control - current control
    * @param stack - current stack
    * @param environment - list of available environments
    * @param deltas - list of delta nodes
    * @return   2 if can handled by this method
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
            && control.get(control.size() - 1) instanceof Lambda 
        ){
            Lambda lambda = (Lambda) control.get(control.size() - 1);
            control.remove(control.size()-1);
            lambda.setEnvironment(getCurrentEnvironment(control, environments).getIndex());
            stack.add(0, lambda);
            return 2;
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

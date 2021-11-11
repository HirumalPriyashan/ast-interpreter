package CSEMachine.CSERules;

import java.util.List;

import CSEMachine.Symbols.Delta;
import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Eta;
import CSEMachine.Symbols.Gamma;
import CSEMachine.Symbols.Lambda;
import CSEMachine.Symbols.Symbol;
import CSEMachine.Symbols.YStar;

/**
 * Class for CSE Rule 12
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class CSERule12 extends AbstractRule{
    /**
    * Modify the control and stack according to CSE Rule 12
    *                  Control                       Stack      Env
    *                ├-------------------------------------├
    * CSE Rule 12    │....gamma      YStar c_lambda_V_i....│ 
    * (applying Y)   │....                    c_eta_V_i....│ 
    *                └╴------------------------------------├
    * 
    * @param control - current control
    * @param stack - current stack
    * @param environments - list of available environments
    * @param deltas - list of delta nodes
    * @return   12 if can handled by this method
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
            && stack.size() > 1
            && control.get(control.size() - 1) instanceof Gamma
            && stack.get(0) instanceof YStar 
            && stack.get(1) instanceof Lambda
        ) {
            control.remove(control.size() - 1);
            stack.remove(0);
            Lambda lambda = (Lambda) stack.get(0);
            stack.remove(0);
            Eta eta = new Eta();
            eta.setIndex(lambda.getIndex());
            eta.setEnvironment(lambda.getEnvironment());
            eta.setIdentifier(lambda.getIdentifiers().get(0));
            eta.setLambda(lambda);
            stack.add(0, eta);
            return 12;
        }
        return 0;
    }    
    
}

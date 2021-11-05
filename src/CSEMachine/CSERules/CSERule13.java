package CSEMachine.CSERules;

import java.util.List;

import CSEMachine.Symbols.Delta;
import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Eta;
import CSEMachine.Symbols.Gamma;
import CSEMachine.Symbols.Rand;
import CSEMachine.Symbols.Symbol;

public class CSERule13 extends AbstractRule{
    /**
    * Modify the control and stack according to CSE Rule 13
    *                  Control                                  Stack         Env
    *                ├----------------------------------------------------├
    * CSE Rule 13    │....gamma                         c_eta_V_i Rand....│ 
    * (applying f.p) │....gamma gamma      c_lambda_V_i c_eta_V_i Rand....│ 
    *                └╴---------------------------------------------------├
    * @param control
    * @param stack
    * @param environment
    * @param deltas
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
            && stack.get(0) instanceof Eta
            && stack.get(1) instanceof Rand
        ) {
            control.add(new Gamma());
            Eta eta = (Eta) stack.get(0);
            stack.add(0, eta.getLambda());
            return 13;
        }
        return 0;
    }    
    
}

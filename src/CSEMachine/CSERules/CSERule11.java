package CSEMachine.CSERules;

import java.util.List;

import CSEMachine.Symbols.Delta;
import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Gamma;
import CSEMachine.Symbols.Id;
import CSEMachine.Symbols.Lambda;
import CSEMachine.Symbols.Symbol;
import CSEMachine.Symbols.Tuple;
import Logger.Logger;

public class CSERule11 extends AbstractRule{
    /**
    * Modify the control and stack according to CSE Rule 11
    *                      Control                           Stack        Env
    *                     ├------------------------------------------├
    * CSE Rule 11         │....gamma      c_lambda_V1...Vn_k Rand....│ 
    * (n-ary function)    │....Em delta_k                      Em....│  Em = [Rand1/V1]...[Randn/Vn]Ec
    *                     └╴-----------------------------------------├
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
            control.size() > 0 
            && stack.size() > 1
            && control.get(control.size() - 1) instanceof Gamma
            && stack.get(0) instanceof Lambda 
            && stack.get(1) instanceof Tuple
        ) {
            // TODO:
            Logger.log("Applying Rule 11");
            control.remove(control.size()-1);
            Environment newEnvironment =new Environment(environments.size());
            newEnvironment.setParent(getCurrentEnvironment(control, environments));
            environments.add(0, newEnvironment);
            Lambda lambda =(Lambda) stack.get(0);
            stack.remove(0);
            Tuple tuple =(Tuple) stack.get(0);
            stack.remove(0);
            int i = 0;
            for (Id id : lambda.getIdentifiers()) {
                newEnvironment.addValue(id, tuple.getSymbol(i++));
            }
            control.add(newEnvironment);
            stack.add(0, newEnvironment);
            List<Symbol> delta = deltas.get(lambda.getIndex()).getSymbols();
            for (Symbol symbol : delta) {
                if (symbol instanceof Lambda) {
                    ((Lambda) symbol).setEnvironment(-1);
                }
                control.add(symbol);
            }
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

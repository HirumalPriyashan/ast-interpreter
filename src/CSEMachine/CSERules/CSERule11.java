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

/**
 * Class for CSE Rule 11
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class CSERule11 extends AbstractRule{
    /**
    * Modify the control and stack according to CSE Rule 11
    *                      Control                           Stack        Env
    *                     ├------------------------------------------├
    * CSE Rule 11         │....gamma      c_lambda_V1...Vn_k Rand....│ 
    * (n-ary function)    │....Em delta_k                      Em....│  Em = [Rand1/V1]...[Randn/Vn]Ec
    *                     └╴-----------------------------------------├
    * 
    * @param control - current control
    * @param stack - current stack
    * @param environments - list of available environments
    * @param deltas - list of delta nodes
    * @return   11 if can handled by this method
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
            && stack.get(0) instanceof Lambda 
            && stack.get(1) instanceof Tuple
        ) {
            control.remove(control.size()-1);
            Lambda lambda =(Lambda) stack.get(0);
            Environment newEnvironment =new Environment(environments.size());
            newEnvironment.setParent(getEnvironmentByLambdaIndex(lambda.getEnvironment(), environments));
            environments.add(0, newEnvironment);
            stack.remove(0);
            Tuple tuple =(Tuple) stack.get(0);
            stack.remove(0);
            int i = 0;
            for (Id id : lambda.getIdentifiers()) {
                newEnvironment.addValue(id, tuple.getSymbol(i++));
            }
            control.add(newEnvironment);
            stack.add(0, newEnvironment);
            Logger.log("Creating new environment");
            newEnvironment.printEnvironment();
            List<Symbol> delta = lambda.getDelta().getSymbols();
            for (Symbol symbol : delta) {
                if (symbol instanceof Lambda) {
                    ((Lambda) symbol).setEnvironment(-1);
                }
                control.add(symbol);
            }
            return 11;
        }
        return 0;
    }    
    
    private Environment getEnvironmentByLambdaIndex(int index,List<Environment> environments){
        for (Environment environment : environments) {
            if (environment.getIndex() == index) {
                return environment;
            }
        }
        return environments.get(0);
    }
}

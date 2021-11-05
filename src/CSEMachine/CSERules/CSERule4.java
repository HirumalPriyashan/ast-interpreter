package CSEMachine.CSERules;

import java.util.List;

import CSEMachine.Symbols.Delta;
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
            control.get(control.size() - 1) instanceof Gamma //check last symbol is a gamma
            && stack.get(0) instanceof Lambda 
            && stack.get(1) instanceof Rand
            && ((Lambda) stack.get(0)).getIdentifiers().size() == 1
        ){
            control.remove(control.size() - 1);
            Lambda lambda =(Lambda) stack.get(0);
            Environment newEnvironment =new Environment(environments.size());
            newEnvironment.setParent(getEnvironmentByLambdaIndex(lambda.getEnvironment(), environments));
            environments.add(0, newEnvironment);
            newEnvironment.addValue(lambda.getX(), stack.get(1));
            control.add(newEnvironment);
            stack.remove(0);
            stack.remove(0);
            stack.add(0, newEnvironment);
            List<Symbol> delta = deltas.get(lambda.getIndex()).getSymbols();
            for (Symbol symbol : delta) {
                if (symbol instanceof Lambda) {
                    ((Lambda) symbol).setEnvironment(-1);
                }
                control.add(symbol);
            }
            return 4;
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

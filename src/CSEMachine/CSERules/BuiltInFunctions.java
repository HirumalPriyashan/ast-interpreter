package CSEMachine.CSERules;

import java.util.List;

import CSEMachine.Symbols.Bool;
import CSEMachine.Symbols.Delta;
import CSEMachine.Symbols.Dummy;
import CSEMachine.Symbols.Environment;
import CSEMachine.Symbols.Gamma;
import CSEMachine.Symbols.Int;
import CSEMachine.Symbols.Lambda;
import CSEMachine.Symbols.StringSymbol;
import CSEMachine.Symbols.Symbol;
import CSEMachine.Symbols.Tuple;

/**
 * Class for Built in functions
 * 
 * @author Hirumal Priyshan
 * @version 1.0
 * @since 1.0
 */
public class BuiltInFunctions extends AbstractRule{
    /**
    * Modify the control and stack by appling built in functions

    * @param control - current control
    * @param stack - current stack
    * @param environment - list of available environments
    * @param deltas - list of delta nodes
    * @return   14 if can handled by this method
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
            && stack.size() > 0
            && control.get(control.size() - 1) instanceof Gamma
        ) {
            Symbol symbol = stack.get(0);
            String token = symbol.getToken();
            if (token.equals("Print")) {
                control.remove(control.size() - 1);
                stack.remove(0);
                return 14;
            } else if (token.equals("Stem")) {
                control.remove(control.size() - 1);
                stack.remove(0);
                Symbol str = stack.get(0);
                stack.remove(0);
                str.setToken(str.getToken().substring(0, 1));
                stack.add(0, str);
                return 14;
            } else if (token.equals("Stern")) {
                control.remove(control.size() - 1);
                stack.remove(0);
                Symbol str = stack.get(0);
                stack.remove(0);
                str.setToken(str.getToken().substring(1));
                stack.add(0, str);
                return 14;
            } else if (token.equals("Conc")) {
                control.remove(control.size() - 1);
                stack.remove(0);
                Symbol str1 = stack.get(0);
                Symbol str2 = stack.get(1);
                stack.remove(0);
                stack.remove(0);
                str1.setToken(str1.getToken() + str2.getToken());
                stack.add(0, str1);                                          
                return 14;
            } else if (token.equals("Order")) {
                control.remove(control.size() - 1);
                stack.remove(0);
                Tuple tuple = (Tuple) stack.get(0);
                stack.remove(0);
                Int order = new Int(Integer.toString(tuple.getSymbols().size()));
                stack.add(0, order);
                return 14;
            } else if (token.equals("Isinteger")) {
                control.remove(control.size() - 1);
                stack.remove(0);
                if (stack.get(0) instanceof Int) {
                    stack.add(0, new Bool("true"));
                } else {
                    stack.add(0, new Bool("false"));
                }
                stack.remove(1);
                return 14;
            } else if (token.equals("Isstring")) {
                control.remove(control.size() - 1);
                stack.remove(0);
                if (stack.get(0) instanceof StringSymbol) {
                    stack.add(0, new Bool("true"));
                } else {
                    stack.add(0, new Bool("false"));
                }
                stack.remove(1);                        
                return 14;
            } else if (token.equals("Istuple")) {
                control.remove(control.size() - 1);
                stack.remove(0);
                if (stack.get(0) instanceof Tuple) {
                    stack.add(0, new Bool("true"));
                } else {
                    stack.add(0, new Bool("false"));
                }
                stack.remove(1);
                return 14;
            } else if (token.equals("Isdummy")) {
                control.remove(control.size() - 1);
                stack.remove(0);
                if (stack.get(0) instanceof Dummy) {
                    stack.add(0, new Bool("true"));
                } else {
                    stack.add(0, new Bool("false"));
                }
                stack.remove(1);
                return 14;
            } else if (token.equals("Istruthvalue")) {
                control.remove(control.size() - 1);
                stack.remove(0);
                if (stack.get(0) instanceof Bool) {
                    stack.add(0, new Bool("true"));
                } else {
                    stack.add(0, new Bool("false"));
                }
                stack.remove(1);
                return 14;
            } else if (token.equals("Isfunction")) {
                stack.remove(0);
                if (stack.get(0) instanceof Lambda) {
                    stack.add(0, new Bool("true"));
                } else {
                    stack.add(0, new Bool("false"));
                }
                stack.remove(1);
                return 14;
            }
            // TODO: Null, Itos
            return 0;
        }
        return 0;
    }     
}

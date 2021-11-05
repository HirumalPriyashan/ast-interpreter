package CSEMachine;

import java.util.List;

import CSEMachine.CSERules.*;
import CSEMachine.Symbols.*;
import Logger.Logger;
import Node.Node;

public class CSEMachine {
    private List<Delta> deltas;
    private SymbolFactory symbolFactory;
    private AbstractRule cseRules;
    private List<Symbol> control;
    private List<Symbol> stack;
    private List<Environment> envs;

    public CSEMachine(Node root){
        this.symbolFactory = new SymbolFactory(root);
        this.deltas = symbolFactory.getDeltas();
        this.control = deltas.get(0).getSymbols();
        this.stack = symbolFactory.getStack();
        this.envs = symbolFactory.getEnvironment();
        this.cseRules = getRules();
    }
    
    public void printDeltas(){
        Logger.log("-----------------------");
        Logger.log("Control structures:");
        for (Delta delta: deltas) {
            List<Symbol> list  = delta.getSymbols();
            Logger.logInLine("   " + delta + " = ");
            for (Symbol symbol : list.subList(0, list.size() - 1)) {
                Logger.logInLine(symbol + " ");
            }
            Logger.log(list.get(list.size() - 1));
        }
        Logger.log("-----------------------");
    }

    public void run(){
        this.printStatus();
        int ruleNumber = 1;
        while (!this.control.isEmpty() && ruleNumber > 0) {
            ruleNumber = this.cseRules.applyRule(this.control, this.stack, this.envs, this.deltas);
            if (ruleNumber == 14) {
                Logger.log("Applying Builtin functions ");                
            } else {
                Logger.log("Applying Rule " + ruleNumber);
            }
            this.printStatus();
        }
    }

    public String getAnswer() {
        return stack.get(0).toString();
    }

    private AbstractRule getRules() {
        AbstractRule rules = new CSERule1();
        rules.setSuccessor(new CSERule2())
        .setSuccessor(new CSERule3())
        .setSuccessor(new CSERule4())
        .setSuccessor(new CSERule5())
        .setSuccessor(new CSERule6())
        .setSuccessor(new CSERule7())
        .setSuccessor(new CSERule8())
        .setSuccessor(new CSERule9())
        .setSuccessor(new CSERule10())
        .setSuccessor(new CSERule11())
        .setSuccessor(new CSERule12())
        .setSuccessor(new CSERule13())
        .setSuccessor(new BuiltInFunctions());
        return rules;
    }

    private void printStatus() {
        Logger.logInLine("Control: ");
        if (this.control.size() > 0){
            for (Symbol symbol : control.subList(0, control.size() - 1)) {
                Logger.logInLine(symbol);
                Logger.logInLine(" ");
            }
            Logger.log(control.get(control.size() - 1));
        } else{
            Logger.logNewLine();
        }
        Logger.logInLine("Stack: ");
        for (Symbol symbol : stack.subList(0, stack.size() - 1)) {
            Logger.logInLine(symbol);
            Logger.logInLine(" ");
        }
        Logger.log(stack.get(stack.size() - 1));
        Logger.log("-----------------------------------------------------");
    }
}

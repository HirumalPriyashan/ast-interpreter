package CSEMachine;

import java.util.List;

import CSEMachine.CSERules.*;
import CSEMachine.Symbols.*;
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
        System.out.println("-----------------------");
        System.out.println("Control structures:");
        for (Delta delta: deltas) {
            List<Symbol> list  = delta.getSymbols();
            System.out.print("   ");
            System.out.print(delta);
            System.out.print(" = ");
            for (Symbol symbol : list.subList(0, list.size() - 1)) {
                System.out.print(symbol);
                System.out.print(" ");
            }
            System.out.println(list.get(list.size() - 1));
        }
        System.out.println("-----------------------");
    }

    public void run(){
        this.printStatus();
        while (!this.control.isEmpty()) {
            this.cseRules.applyRule(this.control, this.stack, this.envs, this.deltas);
            this.printStatus();
        }
    }

    private AbstractRule getRules() {
        AbstractRule rules = new CSERule8();
        rules.setSuccessor(new CSERule2())
        .setSuccessor(new CSERule3())
        .setSuccessor(new CSERule4())
        .setSuccessor(new CSERule5())
        .setSuccessor(new CSERule6())
        .setSuccessor(new CSERule7())
        .setSuccessor(new CSERule1());
        return rules;
    }

    private void printStatus() {
        System.out.print("Control: ");
        if (this.control.size() > 0){
            for (Symbol symbol : control.subList(0, control.size() - 1)) {
                System.out.print(symbol);
                System.out.print(" ");
            }
            System.out.println(control.get(control.size() - 1));
        } else{
            System.out.println();
        }
        System.out.print("Stack: ");
        for (Symbol symbol : stack.subList(0, stack.size() - 1)) {
            System.out.print(symbol);
            System.out.print(" ");
        }
        System.out.println(stack.get(stack.size() - 1));
        System.out.println("-----------------------------------------------------");
    }
}

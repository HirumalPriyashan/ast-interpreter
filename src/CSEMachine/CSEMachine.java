package CSEMachine;

import java.util.ArrayList;
import java.util.List;

import CSEMachine.CSERules.*;
import CSEMachine.Symbols.*;
import Node.Node;
import Visitor.*;

public class CSEMachine implements IVisitor {
    List<Symbol> control;
    List<Symbol> currentControl;
    int lambdaIndex = 1;
    List<Symbol> stack;
    List<Lambda> lambdasFound;
    List<List<Symbol>> deltas;
    List<Environment> envs;
    SymbolFactory symbolFactory;
    AbstractRule cseRules;

    public CSEMachine(Node root){
        this.symbolFactory = new SymbolFactory();
        this.cseRules = getRules();
        this.envs = configEnvironment();
        this.control = cofigControl();
        this.currentControl = this.control;
        this.stack = configStack();
        this.lambdasFound = new ArrayList<Lambda>();
        root.accept(this);
        deltas = new ArrayList<List<Symbol>>();
        deltas.add(currentControl);
        while (this.lambdasFound.size() > 0){
            currentControl = new ArrayList<Symbol>();
            Lambda lambda = this.popFromLambdasFound();
            Node node = lambda.getRightChild();
            node.accept(this);
            deltas.add(currentControl);
        }
    }

    private AbstractRule getRules() {
        AbstractRule rules = new CSERule1();
        rules.setSuccessor(new CSERule2())
        .setSuccessor(new CSERule3())
        .setSuccessor(new CSERule4())
        .setSuccessor(new CSERule5());
        return rules;
    }

    private List<Symbol> cofigControl() {
        List<Symbol> control = new ArrayList<Symbol>();
        control.add(this.envs.get(0));
        return  control;
    }

    private List<Symbol> configStack() {
        List<Symbol> stack = new ArrayList<Symbol>();
        stack.add(this.envs.get(0));
        return  stack;
    }

    private List<Environment> configEnvironment() {
        List<Environment> environments = new ArrayList<Environment>();
        environments.add(new Environment(0));
        return  environments;
    }

    public Symbol popFromControl(){
        return this.control.remove(this.control.size() - 1);
    }

    public Lambda popFromLambdasFound(){
        return this.lambdasFound.remove(0);
    }

    public Symbol popFromStack(){
        return this.stack.remove(this.stack.size() - 1);
    }

    public void run(){
        this.printStatus();
        while (!this.control.isEmpty()) {
            this.cseRules.applyRule(this.control, this.stack, this.envs, this.deltas);
            this.printStatus();
        }
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

    public void printDeltas(){
        for (List<Symbol> list: deltas) {
            for (Symbol symbol : list.subList(0, list.size() - 1)) {
                System.out.print(symbol);
                System.out.print(" ");
            }
            System.out.println(list.get(list.size() - 1).getToken());
        }
    }

    @Override
    public void visit(IVisitable iVisitable) {
        Node node = (Node) iVisitable;
        Symbol symbol = this.symbolFactory.getSymbol(node, this.lambdaIndex);
        this.currentControl.add(symbol);
        if (symbol instanceof Lambda) {
            this.lambdaIndex++;
            this.lambdasFound.add((Lambda) symbol);
        }
    }
}

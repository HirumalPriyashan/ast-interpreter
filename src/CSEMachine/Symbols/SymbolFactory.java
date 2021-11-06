package CSEMachine.Symbols;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Logger.Logger;
import Node.Node;
import Visitor.*;

public class SymbolFactory implements IVisitor{
    private List<Delta> deltas;
    private List<Symbol> symbols;
    private List<Environment> envs;
    private List<Symbol> stack;
    
    public SymbolFactory(Node root) {
        this.envs = configEnvironment();
        this.symbols = configControl();
        this.stack = configStack();
        this.deltas = new ArrayList<Delta>();
        Delta initialDelta = new Delta(null);
        root.accept(this);
        initialDelta.setSymbols(this.symbols);
        this.deltas.add(initialDelta);
        boolean isDone = false;
        while (!isDone) {
            isDone = true;
            List<Delta> newDeltas = new ArrayList<Delta>();         
            for (Delta delta : deltas) {
                if (!delta.isPassed()) {
                    isDone = false;
                    for (Symbol symbol : delta.getSymbols()) {
                        if (symbol instanceof Lambda) {
                            this.symbols = new ArrayList<Symbol>();
                            Lambda lambda = (Lambda) symbol;
                            Delta newDelta = new Delta(null);
                            Node node = lambda.getLambdaNode();
                            if (",".equals(node.getChildren().get(0).getToken())) {
                                for (Node identifier: node.getChildren().get(0).getChildren()) {
                                    lambda.addIdentifier(new Id(identifier.getToken().substring(4, identifier.getToken().length()-1)));
                                }
                            } else {
                                lambda.addIdentifier(new Id(node.getChildren().get(0).getToken().substring(4, node.getChildren().get(0).getToken().length()-1)));
                            }
                            lambda.getRightChild().accept(this);
                            newDelta.setSymbols(this.symbols);
                            lambda.setDelta(newDelta);
                            newDeltas.add(newDelta);
                        } else if (symbol instanceof B){
                            this.symbols = new ArrayList<Symbol>();
                            B b = (B) symbol;
                            Node node = b.getNode();
                            node.accept(this);
                            b.setSymbols(this.symbols);
                        } else if (symbol instanceof Delta){
                            Delta delt = (Delta) symbol;
                            if (delt.getNode() != null){
                                this.symbols = new ArrayList<Symbol>();
                                Node node  = delt.getNode();
                                node.accept(this);
                                delt.setSymbols(this.symbols);
                                newDeltas.add(delt);
                            }
                        }
                    }
                    delta.setIsPassed(true);
                }
            }
            for (Delta delt : newDeltas) {
                this.deltas.add(delt);
            }
        }
        this.replaceB();
    }

    private void replaceB() {
        for (Delta delta : deltas) {
            int index = -1;
            for (Symbol symbol : delta.getSymbols()) {
                if (symbol instanceof B) {
                    index = delta.getSymbols().indexOf(symbol);
                }
            }
            if(index != -1){
                B b = (B) delta.getSymbols().get(index);
                delta.removeSymbol(b);
                List<Symbol> symbolList = b.getSymbols();
                for (int i = symbolList.size() - 1; i >= 0 ; i--) {
                    delta.addSymbol(index, symbolList.get(i));
                }
            }
        }
    }

    public List<Delta> getDeltas() {
        return this.deltas;
    }

    public List<Symbol> getStack() {
        return this.stack;
    }

    public List<Environment> getEnvironment() {
        return this.envs;
    }

    public Symbol getSymbol(Node node) {
        String token = node.getToken();
        List<String> UOps = new ArrayList<String>(Arrays.asList("not", "neg"));
        List<String> Ops = new ArrayList<String>(Arrays.asList("aug", "or", "&", "+", "-", "*", "/", "**", "eq","ne", "ls", "le","gr", "ge", "<", ">", "<=", ">="));
        if (token.equals("lambda")){
            return new Lambda(node);
        } else if (token.equals("gamma")){
            return new Gamma();
        } else if (Ops.contains(token)){
            return new BOp(token);
        } else if (UOps.contains(token)){
            return new UOp(token);
        } else if (token.equals("Ystar")){
            return new YStar();
        } else if (token.equals("tau")){
            return new Tau(node.getChildren().size());
        } else if (token.startsWith("<ID:")){
            return new Id(token.substring(4, token.length() - 1));
        } else if (token.startsWith("<INT:")){
            return new Int(token.substring(5, token.length() - 1));
        } else if (token.startsWith("<STR:")){
            return new Int(token.substring(6, token.length() - 2));
        } else if (token.startsWith("<nil")) {                    
            return new Tuple();
        } else if (token.equals("<true>")) {                    
            return new Bool("true");
        } else if (token.equals("<false>")) {                    
            return new Bool("false");
        } else if (token.equals("<dummy>")) {                    
            return new Dummy();
        } else {
            Logger.log("Err node: " + token);
            return new Error(token);
        } 
    }

    @Override
    public void visit(IVisitable iVisitable) {
        Node node = (Node) iVisitable;
        String token = node.getToken();
        if (token.equals("->")) {
            this.symbols.add(new Delta(node.getChildren().get(1)));
            this.symbols.add(new Delta(node.getChildren().get(2)));
            this.symbols.add(new Beta());
            this.symbols.add(new B(node.getChildren().get(0)));
        } else {
            this.symbols.add(this.getSymbol(node));
        }
    }
    
    private List<Environment> configEnvironment() {
        List<Environment> environments = new ArrayList<Environment>();
        environments.add(new Environment(0));
        return  environments;
    }
    
    private List<Symbol> configControl() {
        List<Symbol> control = new ArrayList<Symbol>();
        control.add(this.envs.get(0));
        return  control;
    }
    
    private List<Symbol> configStack() {
        List<Symbol> stack = new ArrayList<Symbol>();
        stack.add(this.envs.get(0));
        return  stack;
    }    
}

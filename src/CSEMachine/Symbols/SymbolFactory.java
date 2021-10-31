package CSEMachine.Symbols;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import CSEMachine.CSEMachine;
import Node.Node;
import Visitor.IVisitable;
import Visitor.IVisitor;

public class SymbolFactory implements IVisitor{
    List<Symbol> symbols;
    CSEMachine cseMachine;
    public SymbolFactory(CSEMachine cseMachine){
        this.cseMachine = cseMachine;
    }
    public Symbol getSymbol(Node node) {
        String token = node.getToken();
        List<String> UOps = new ArrayList<String>(Arrays.asList("not", "neg"));
        List<String> Ops = new ArrayList<String>(Arrays.asList("aug", "or", "&", "+", "-", "*", "/", "**", "eq","ne", "ls", "le","gr", "ge"));
        if (token.equals("lambda")){
            return this.cseMachine.addLambda(
                new Lambda(
                    token, 
                    new Id( node.getChildren().get(0).getToken().substring(4, token.length() - 1)), 
                    node.getChildren().get(1)
                )
            );
        }  else if (token.equals("gamma")){
            return new Gamma(token);
        } else if (Ops.contains(token)){
            return new BOp(token);
        } else if (UOps.contains(token)){
            return new UOp(token);
        } else if (token.equals("Ystar")){
            return new YStar();
        } else if (token.startsWith("<ID:")){
            return new Id(token.substring(4, token.length() - 1));
        } else if (token.startsWith("<INT:")){
            return new Int(token.substring(5, token.length() - 1));
        } else if (token.startsWith("<STR:")){
            return new Int(token.substring(6, token.length() - 2));
        } else if (token.startsWith("<nil")) {                    
            return new Tuple("");
        } else if (token.startsWith("<true>")) {                    
            return new Bool("true");
        } else if (token.startsWith("<false>")) {                    
            return new Bool("false");
        } else {
            System.out.println("Err node: " + token);
            return new Error(token);
        } 
    }

    public B getB(Node node) {
        B b = new B("B"); 
        this.symbols = new ArrayList<Symbol>();
        b.setSymbols(this.symbols);
        return b;
    }

    public Beta getBeta(Node node) {
        return new Beta("");
    }

    public Delta getDelta(Node node) {
        return null;
    }

    @Override
    public void visit(IVisitable iVisitable) {
        Node node = (Node) iVisitable;
        if (node.getToken().equals("->")) {
            this.symbols.add(this.getDelta(node.getChildren().get(1)));
            this.symbols.add(this.getDelta(node.getChildren().get(2)));
            this.symbols.add(this.getBeta(node.getChildren().get(2)));
            B b = this.getB(node.getChildren().get(0));
            this.symbols.add(b);
        } else {
            Symbol symbol = this.getSymbol(node);
            this.symbols.add(symbol);
        }
    }
}

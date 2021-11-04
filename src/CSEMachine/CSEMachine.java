package CSEMachine;

import java.util.List;

import CSEMachine.Symbols.*;
import Node.Node;

public class CSEMachine {
    List<Delta> deltas;
    SymbolFactory symbolFactory;

    public CSEMachine(Node root){
        this.symbolFactory = new SymbolFactory(root);
        this.deltas = symbolFactory.getDeltas();
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
}

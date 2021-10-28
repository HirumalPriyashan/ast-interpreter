package CSEMachine.Symbols;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Node.Node;

public class SymbolFactory {
    public Symbol getSymbol(Node node, int lambdaIndex) {
        String token = node.getToken();
        List<String> UOps = new ArrayList<String>(Arrays.asList("not", "neg"));
        List<String> Ops = new ArrayList<String>(Arrays.asList("aug", "or", "&", "+", "-", "*", "/", "**", "eq","ne", "ls", "le","gr", "ge"));
        if (token.equals("lambda")){
            return new Lambda(token, lambdaIndex, new Id(node.getChildren().get(0).getToken().substring(4, token.length() - 1)) , node.getChildren().get(1));
        } else if (token.equals("gamma")){
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
        } else if (token.startsWith("<true>")) {                    
            return new Bool("true");
        } else if (token.startsWith("<false>")) {                    
            return new Bool("false");
        } else {
            return new Symbol(token);
        }
    }
}

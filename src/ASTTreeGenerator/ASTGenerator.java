package ASTTreeGenerator;

import java.util.List;

import MyExeptions.ASTNotGeneratedExeption;
import Node.Node;

public class ASTGenerator {
    Node root;

    public ASTGenerator() {
        this.root = null;
    }

    public Node generateAST(List<String> astList){
        for (String line : astList) {
            String token = line.replace(".", "");
            int depth = line.length() - token.length();
            if (depth == 0) {
                this.root = new Node(token);
                continue;
            }
            Node previous = this.root.getNextToAppend(depth);
            previous.addChild(new Node(token));
        }
        return this.root;
    }

    public void printTree() {
        try {
            if (this.root == null) {
                throw new ASTNotGeneratedExeption("AST need to be generated first");
            }
            this.root.printNode();
        } catch (ASTNotGeneratedExeption e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
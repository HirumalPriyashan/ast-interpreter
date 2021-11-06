package ASTTreeGenerator;

import java.util.List;

import MyExeptions.ASTNotGeneratedException;
import Node.Node;

/**
 * ASTGenerator is the class for generate Abstract Syntax Tree from the 
 * file content
 * 
 * @author Hirumal Priyshan
 * @version 1.0
 * @since 1.0
 */
public class ASTGenerator {
    Node root;

    /** 
    * Class constructor.
    */
    public ASTGenerator() {
        this.root = null;
    }

    /**
    * Gerarate Tree stucture for the content read from the input file which
    * has passed as a list of strings
    *
    * @param  astList list of line content from the input file 
    * @return root node of the tree
    */
    public Node generateAST(List<String> astList){
        Node previous = null;
        for (String line : astList) {
            String token = line.replace(".", "");
            int depth = line.length() - token.length();
            if (depth == 0) {
                this.root = new Node(token);
                continue;
            }
            previous = this.root.getNextToAppend(depth);
            previous.addChildWithDepth(new Node(token));
        }
        return this.root;
    }

    /**
    * Prints the AST in order from root node to leaf nodes
    * node is represented using a set of dots which are equls to the 
    * depth * followed by the token of the node
    * 
    * @throws ASTNotGeneratedException  If AST is not generated
    */
    public void printTree() {
        try {
            if (this.root == null) {
                throw new ASTNotGeneratedException("AST need to be generated first");
            }
            this.root.printNode();
        } catch (ASTNotGeneratedException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
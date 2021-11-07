package CSEMachine.Symbols;

import java.util.ArrayList;
import java.util.List;

import Node.Node;

/**
 * Class representation for Lambda symbols in control stack
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class Lambda extends Rand{
    private static int i = 1;
    private int index;
    private int environment;
    private List<Id> identifiers;
    private Node rightChild;
    private Node lambdaNode;
    private Delta delta;
    
    /**
     * Class constructor.
     * 
     * @param node starting node for Lambda symbol
     */
    public Lambda(Node node) {
        super("lambda");
        this.index = i++;
        this.identifiers = new ArrayList<Id>();
        this.rightChild = node.getChildren().get(1);
        this.lambdaNode = node;
        this.environment = -1;
    }
    
    /**
     * Getter for index
     * 
     * @return the index of the Lambda symbol
     */
    public int getIndex() {
        return this.index;
    }
    
    /**
     * Setter for environment
     * 
     * @param e index of the environment symbol
     */
    public void setEnvironment(int n) {
        this.environment = n;
    }
    
    /**
     * Getter for environment
     * 
     * @return the environment of the Lambda symbol
     */
    public int getEnvironment() {
        return this.environment;
    }
    
    /**
     * Setter for delta
     * 
     * @param delta delta of the Lambda symbol
     */
    public void setDelta(Delta delta) {
        this.delta = delta;
    }
    
    /**
     * Getter for delta
     * 
     * @return the delta of the Lambda symbol
     */
    public Delta getDelta() {
        return this.delta;
    }

    /**
     * Add new identifier to the iidentifiers list
     * 
     * @param id Id symbol to be added
     */
    public void addIdentifier(Id id){
        this.identifiers.add(id);
    }

    /**
     * Getter for right child of the starting node for Lambda symbol
     * 
     * @return starting node
     */
    public Node getRightChild() {
        return rightChild;
    }

    /**
     * Setter for right child of the starting node for Lambda symbol
     * 
     * @params rightChild right child of the starting node
     */
    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * Getter for starting node for Lambda symbol
     * 
     * @return starting node
     */
    public Node getLambdaNode() {
        return this.lambdaNode;
    }
    
    /**
     * Getter for first identifier
     * 
     * @return the first identifier of the Lambda symbol
     */
    public Id getX() {
        return this.identifiers.get(0);
    }
    
    /**
     * Getter for identifiers
     * 
     * @return the identifiers of the Lambda symbol
     */
    public List<Id> getIdentifiers() {
        return this.identifiers;
    }
    
    @Override
    public String toString() {
        String str = "lambda|";
        if (this.environment >= 0) {
            str = this.environment + "|" + str;
        }
        // if (this.delta != null) {
        //     str += "Delta=" + delta.toString() +"|";
        // }
        for (Id id : identifiers) {
            str += id.getToken() + "|";
        }
        return str + this.index;
    }
}

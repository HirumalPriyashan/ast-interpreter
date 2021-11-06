package Node;

import Visitor.IVisitable;
import Visitor.IVisitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Logger.Logger;

/**
 * Class representation for node in syntax tree
 * 
 * @author Hirumal Priyshan
 * @version 1.0
 * @since 1.0
 */
public class Node implements IVisitable {
    private String token;
    private List<Node> children;
    private Node parent;
    private int depth;
    private boolean isStandardized;

    /**
     * Class constructor.
     * 
     * @param token token for the node
     */
    public Node(String token) {
        this.token = token;
        this.children = new ArrayList<Node>();
        this.parent = null;
        this.depth = 0;
        this.isStandardized = false;
    }

    /**
     * Getter for token
     * 
     * @return token in the node
     */
    public String getToken() {
        return this.token;
    }

    /**
     * Getter for token
     * 
     * @param token token to set for symbol
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Getter for children
     * 
     * @return children list for the node
     */
    public List<Node> getChildren() {
        return this.children;
    }

    /**
     * Setter for children
     * 
     * @param children list of children
     */
    public void setChildren(List<Node> children) {
        this.removeChildren();
        for (Node node : children) {
            this.addChild(node);
        }
    }

    /**
     * Setter for children with editing their depths
     * 
     * @param children list of children
     */
    public void setChildrenWithDepth(List<Node> children) {
        this.removeChildren();
        for (Node node : children) {
            this.addChildWithDepth(node);
        }
    }

    /**
     * Add a child to the children list
     * 
     * @param child child to be add
     */
    public void addChild(Node child) {
        child.setParent(this);
        this.children.add(child);
    }

    /**
     * Add a child with changing thire depth to the children list
     * 
     * @param child child to be add
     */
    public void addChildWithDepth(Node child) {
        child.setParent(this);
        child.increaseDepthBy(this.getDepth() - child.getDepth() + 1);
        this.children.add(child);
    }

    /**
     * Remove all children from children list
     */
    public void removeChildren() {
        this.children = new ArrayList<Node>();
        return;
    }

    /**
     * Getter for parent
     * 
     * @return parent node of the node
     */
    public Node getParent() {
        return this.parent;
    }

    /**
     * Setter for parent
     * 
     * @param parent node to be set as parent
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }

    /**
     * Getter for depth
     * 
     * @return the depth of the node
     */
    public int getDepth() {
        return this.depth;
    }

    /**
     * Setter for depth
     * 
     * @param depth depth to set for the node
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * increase the depth of node by given marging
     * 
     * @param margin size to increase or decrease the depth
     */
    public void increaseDepthBy(int margin) {
        this.depth += margin;
        for (Node node : children) {
            node.increaseDepthBy(margin);
        }
    }

    /**
     * Getter for isStandardized for node
     * isStandardized will have value of <b>true</b> if the node has 
     * been standardized
     * 
     * @return value of isStandardized
     */
    public boolean getIsStandardized() {
        if (this.children.size() == 0){
            this.isStandardized = true;
        }
        return this.isStandardized;
    }

    /**
     * Setter for isStandardized
     * 
     * @param isStandardized value to set for isStandardized
     */
    public void setIsStandardized(boolean isStandardized) {
        this.isStandardized = isStandardized;
    }

    /**
     * Returns the next node to append the new incoming node by depth
     * 
     * @param depth depth of the incoming node
     * @return next node which should be the parent of new node
     */
    public Node getNextToAppend(int depth){
        if (depth == this.depth + 1) {
            return this;
        }
        return this.children.get(this.children.size() - 1).getNextToAppend(depth);
    }

    /**
     * Prings the structure for the node with its successors
     */
    public void printNode() {
        Logger.log(this);
        for (Node node : this.children) {
            node.printNode();
        }
    }

    @Override
    public String toString() {
        return ".".repeat(this.depth * 1) + this.token ;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
        if (!this.getToken().equals("lambda") && !this.getToken().equals("->")) {
            for (Iterator<Node> it = this.children.iterator(); it.hasNext(); )
                ((Node) it.next()).accept(visitor);
        }
    }
}

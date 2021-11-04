package Node;

import Visitor.IVisitable;
import Visitor.IVisitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Node implements IVisitable {
    private String token;
    private List<Node> children;
    private Node parent;
    private int depth;
    private boolean isStandardized;

    public Node(String token) {
        this.token = token;
        this.children = new ArrayList<Node>();
        this.parent = null;
        this.depth = 0;
        this.isStandardized = false;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Node> getChildren() {
        return this.children;
    }

    public void setChildren(List<Node> children) {
        this.removeChildren();
        for (Node node : children) {
            this.addChild(node);
        }
    }

    public void setChildrenWithDepth(List<Node> children) {
        this.removeChildren();
        for (Node node : children) {
            this.addChildWithDepth(node);
        }
    }

    public void addChild(Node child) {
        child.setParent(this);
        this.children.add(child);
    }

    public void addChildWithDepth(Node child) {
        child.setParent(this);
        child.increaseDepthBy(this.getDepth() - child.getDepth() + 1);
        this.children.add(child);
    }

    public void removeChildren() {
        this.children = new ArrayList<Node>();
        return;
    }

    public Node getParent() {
        return this.parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getDepth() {
        return this.depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void increaseDepthBy(int margin) {
        this.depth += margin;
        for (Node node : children) {
            node.increaseDepthBy(margin);
        }
    }

    public boolean getIsStandardized() {
        if (this.children.size() == 0){
            this.isStandardized = true;
        }
        return this.isStandardized;
    }

    public void setIsStandardized(boolean isStandardized) {
        this.isStandardized = isStandardized;
    }

    public Node getNextToAppend(int depth){
        if (depth == this.depth + 1) {
            return this;
        }
        return this.children.get(this.children.size() - 1).getNextToAppend(depth);
    }

    @Override
    public String toString() {
        return ".".repeat(this.depth * 1) + this.token ;
    }

    public void printNode() {
        System.out.println(this);
        for (Node node : this.children) {
            node.printNode();
        }
    }

    public List<String> preOrderTraverse(List<String> nodes) {
        nodes.add(this.getToken());
        for (Node node : this.children) {
            nodes = node.preOrderTraverse(nodes);
        }
        return nodes;
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

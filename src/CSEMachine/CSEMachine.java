package CSEMachine;

import java.util.ArrayList;
import java.util.List;

import Node.Node;

public class CSEMachine {
    List<String> control;
    List<String> stack;
    List<Object> envs;

    public CSEMachine(Node root) {
        this.control = root.preOrderTraverse(new ArrayList<String>());
        this.stack = new ArrayList<String>();
    }

    public void evaluate(){
        while (this.control.size() > 0) {
            String token = this.popFromControl();
            // if token is a name push in to stack
            // if gamma
            String rator = this.popFromStack();
            String rand = this.popFromStack();
            this.stack.add(this.apply(rator, rand));
        }
    }

    private String apply(String rator, String rand) {
        return null;
    }

    public String popFromControl(){
        return this.control.remove(this.control.size() - 1);
    }

    public String popFromStack(){
        return this.stack.remove(this.stack.size() - 1);
    }

    public void printControl(){
        for (String string : control) {
            System.out.print(string + " ");
        }
    }
}

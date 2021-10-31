import ASTInterpreter.ASTInterpreter;

public class Main {
    public static void main(String[] args) {
        String filename = "";
        if (args.length > 0){
            filename = args[0];
        }
        else{
            filename = "inputs/inputs-cse/input-4.txt";
        }
        ASTInterpreter.interpret(filename);
    }
}

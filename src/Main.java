import ASTInterpreter.ASTInterpreter;
import CSEMachine.CSEMachineWrapper;
import Logger.Logger;

public class Main {
    public static void main(String[] args) {
        String filename = "";
        if (args.length > 0){
            filename = args[0];
        }
        else{
            filename = "inputs/inputs/substring.txt";
        }
        Logger.disableLogger();
        ASTInterpreter.interpret(filename);
        // Logger.enableLogger();
        String answer = CSEMachineWrapper.execute(filename);
        Logger.enableLogger();
        Logger.log(answer);
    }
}

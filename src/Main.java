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
            filename = "inputs/inputs/pairs2.txt";
        }
        Logger.disableLogger();
        ASTInterpreter.interpret(filename);
        String answer = CSEMachineWrapper.execute(filename);
        Logger.enableLogger();
        Logger.log(answer);
    }
}

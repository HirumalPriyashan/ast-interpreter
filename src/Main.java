import ASTInterpreter.ASTInterpreter;
import CSEMachine.CSEMachineWrapper;
import Logger.Logger;

public class Main {
    public static void main(String[] args) {
        if (args.length <= 0){
            printUsage();
            return;
        }
        String filename = "inputs/inputs/ast1.txt";
        filename = args[0];
        boolean isASTLoggingEnabled = false;
        boolean isCSELoggingEnabled = false;
        for (int i = 1; i < args.length; i++) {
            final String arg = args[i];
            if (arg.equals("-a")) {
                isASTLoggingEnabled = true;
            } else if (arg.equals("-c")) {
                isCSELoggingEnabled = true;
            } else {
                printUsage();
                return;
            }
            
        }
        Logger.disableLogger();
        if (isASTLoggingEnabled) {
            Logger.enableLogger();
            ASTInterpreter.interpret(filename);
        }
        Logger.disableLogger();
        if (isCSELoggingEnabled) {
            Logger.enableLogger();
        }
        String answer = CSEMachineWrapper.execute(filename);
        Logger.enableLogger();
        Logger.log(answer);
    }

    private static void printUsage() {
        Logger.log("Usage: java Main <input-file-path> [-a] [-c]");
        Logger.log("input-file-name         absolute or relative path to the input file");
        Logger.log("-a                      enable logging standardized tree");
        Logger.log("-c                      enable logging cse machine execution");
    }
}

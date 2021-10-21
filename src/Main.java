import FileReader.FileReader;
import FileReader.TextFileReader;
import Node.Node;
import Standardizer.Standardizer;

import java.util.ArrayList;

import ASTTreeGenerator.ASTGenerator;

public class Main {

    // let check x = 
    //     not Isinteger x -> 'error' |
    //     (x ls 0 -> 'negative' | 'positive')
    // in
    // print(check (-8), check 8)
    public static void main(String[] args) {
        String filename = "";
        if (args.length > 0){
            filename = args[0];
        }
        else{
            filename = "inputs/input.txt";
        }
        ASTInterpreter.interprete(filename);
    }
}
